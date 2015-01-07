package org.bbs.apklauncher;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ApplicationInfoX;
import org.bbs.osgi.activity.AbsActivityWraper;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.InstrumentationWrapper;
import org.bbs.osgi.activity.InstrumentationWrapper.CallBack;
import org.bbs.osgi.activity.LazyContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;
import org.bbs.osgi.activity.embed.EmbeddedActivityAgent;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dalvik.system.DexClassLoader;

public class StubActivity extends 
AbsActivityWraper 
//Activity
implements CallBack {
	
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_APPLICATION_CLASS_NAME = "EXTRA_APPLICATION_CLASS_NAME";
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_ACTIVITY_CLASS_NAME = "EXTRA_ACTIVITY_CLASS_NAME";
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_ACTIVITY_THEME = "EXTRA_ACTIVITY_THEME";
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_APK_PATH = "EXTRA_APK_PATH";
	/**
	 * type {@link ActivityInfoX}
	 */
	public static final String EXTRA_ACTIVITYX_INFO = "EXTRA_ACTIVITYX_INFO";
	private static final String TAG = StubActivity.class.getSimpleName();
	
	public static Map<String, WeakReference<ClassLoader>> sApk2ClassLoaderMap = new HashMap<String, WeakReference<ClassLoader>>();
	public static Map<String, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<String, WeakReference<ResourcesMerger>>();
	public static Map<String, WeakReference<Context>> sApk2ContextMap = new HashMap<String, WeakReference<Context>>();	
	public static Map<String, WeakReference<Application>> sApk2ApplicationtMap = new HashMap<String, WeakReference<Application>>();
	private ClassLoader mClassLoader;
	private String mApplicationClassName;
	private String mActivityClassName;
	private String mApkPath;
	private Activity mTargetActivity;
	private ResourcesMerger mResourceMerger;
	private LazyContext mLazyContext;
	private int mTargetThemeId;
	private Theme mTargetTheme;
	private ActivityInfoX mActInfo;
	private static ClassLoader sLastClassLoader;

	private int mThemeResource = android.R.style.Theme_Black;
	private Theme mTheme;
	private PackageManager mPackageManager;
	private Resources mTargetResource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		getPackageManager();
		super.onCreate(savedInstanceState);
		
//		Log.d(TAG, "stub onCreate(). " + this);
	}

	private ClassLoader onCreateClassLoader(String apkPath) {
		ClassLoader c = new DexClassLoader(apkPath, getDir("apk_code_cache", 0).getPath(), null, getClassLoader());
		return c;
	}
	
	@Override
	protected void attachBaseContext(Context newBase) {
		mLazyContext = new LazyContext(newBase);
		super.attachBaseContext(mLazyContext);
	}

	protected Activity onPrepareActivityStub() {
		
		Intent intent = getIntent();
		
		// how to get classloader berfore parse intent.
		if (sLastClassLoader != null) {
			mLazyContext.classLoaderReady(sLastClassLoader);
//			intent.getExtras().setClassLoader(mLazyContext.getClassLoader());
			intent.getExtras().setClassLoader(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
//			intent.setExtrasClassLoader(mLazyContext.getClassLoader());
		}
		
		mActivityClassName = intent.getStringExtra(EXTRA_ACTIVITY_CLASS_NAME);
//		mApplicationClassName = intent.getStringExtra(EXTRA_APPLICATION_CLASS_NAME);
//		mThemeId = intent.getIntExtra(EXTRA_ACTIVITY_THEME, -1);
//		mApkPath = intent.getStringExtra(EXTRA_APK_PATH);
		
		mActInfo = InstalledAPks.getInstance().getActivityInfo(mActivityClassName);
		mApplicationClassName = mActInfo.mPackageClassName;
		mTargetThemeId = mActInfo.theme;
		mApkPath = mActInfo.mApkPath;
		if (TextUtils.isEmpty(mApplicationClassName)){
			mApplicationClassName = Application.class.getCanonicalName();
			Log.d(TAG, "no packageName, user default.");
		}
		
		Log.d(TAG, "mApplicationClassName: " + mApplicationClassName);
		Log.d(TAG, "mActivityClassName   : " + mActivityClassName);
		Log.d(TAG, "mThemeId             : " + mTargetThemeId);
		Log.d(TAG, "mApkPath             : " + mApkPath);
		
		WeakReference<ClassLoader> r = sApk2ClassLoaderMap.get(mApkPath);
		if (r != null && r.get() != null) {
			mClassLoader  = r.get();
		} else {
			mClassLoader = onCreateClassLoader(mApkPath);
			sApk2ClassLoaderMap.put(mApkPath, new WeakReference<ClassLoader>(mClassLoader));
		}
		sLastClassLoader = mClassLoader;
		mLazyContext.classLoaderReady(mClassLoader);
		mLazyContext.packageManagerReady(new PakcageMangerPolicy(super.getPackageManager()));
		
		// do appliction init. must before activity init.
		WeakReference<Application> rp = sApk2ApplicationtMap.get(mApkPath);
		if (rp != null && rp.get() != null) {
			
		} else {
			if (!TextUtils.isEmpty(mApplicationClassName)) {
				try {
					Application app = ((Application) mClassLoader.loadClass(mApplicationClassName).newInstance());
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<Application>(app));
					
					mLazyContext.applicationReady(app);

					((ApkLauncherApplication)getApplication()).attachBundleAplication(app, mResourceMerger, mLazyContext);
					
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<Application>(app));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("error in create application: " + mApplicationClassName , e);
				}
			}
		}
		
		// do activity init
		InstrumentationWrapper.injectInstrumentation(this, this);
		try {
			mTargetActivity =  (Activity) mClassLoader.loadClass(mActivityClassName).newInstance();
			WeakReference<ResourcesMerger> rr = sApk2ResourceMap.get(mApkPath);
			if (rr != null && rr.get() != null) {
				mResourceMerger = rr.get();
				mTargetResource = mResourceMerger.mFirst;
			} else {
				mTargetResource = BundleActivity.loadApkResource(mApkPath);
				mResourceMerger = new ResourcesMerger(mTargetResource, getResources());
				sApk2ResourceMap.put(mApkPath, new WeakReference<ResourcesMerger>(mResourceMerger));
			}

			if (mTargetThemeId  > 0) {
			} else {
				mTargetThemeId = ReflectUtil.ResourceUtil.selectDefaultTheme(mResourceMerger, mThemeResource, mActInfo.mApplication.targetSdkVersion);
			}
			setTheme(mTargetThemeId);
			Log.d(TAG, "set stub mTargetThemeId: " + mTargetThemeId);
			mLazyContext.themeReady(mTargetThemeId);
			
			mLazyContext.resReady(mResourceMerger);
			EmbeddedActivityAgent.copyContext(this, mTargetActivity, mResourceMerger);

			ReflectUtil.ActivityReflectUtil.setApplication(mTargetActivity, sApk2ApplicationtMap.get(mApkPath).get());
			
			CharSequence title = "";
			if (mActInfo.labelRes  > 0) {
				title = mResourceMerger.getString(mActInfo.labelRes);
			}
			if (TextUtils.isEmpty(title)) {
				title = mActInfo.nonLocalizedLabel;
			}
			if (!TextUtils.isEmpty(title)) {
				//mTargetActivity.onCreate() is not called.
//				mTargetActivity.setTitle(title);
				setTitle(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error in create activity: " + mActivityClassName , e);
		}
		
		
		return mTargetActivity;
	}
	
	@Override
	public Theme getTheme() {
//		return super.getTheme();
		if (null != mTargetTheme) {
			return mTargetTheme;
		}
		if (mTargetResource != null) {
			if (mTargetTheme == null) {
				mTargetTheme = mTargetResource.newTheme();
			}
			if (mTargetThemeId > 0) {
				mTargetTheme.applyStyle(mTargetThemeId, true);

				return mTargetTheme;
			}
		}
		
		boolean first = mTheme == null;
		if (first) {
            mThemeResource = ReflectUtil.ResourceUtil.selectDefaultTheme(getResources(), mThemeResource,
                    getApplicationInfo().targetSdkVersion);
			mTheme = getResources().newTheme();
		}
		
		mTheme.applyStyle(mThemeResource, true);
		
		return mTheme;
	}
	
	public Resources getResources() {
		return mLazyContext.getResources();
	}	
	
	@Override
	public ClassLoader getClassLoader() {
		if (null != mLazyContext){
			return mLazyContext.getClassLoader();
		}
		return super.getClassLoader();
	}
	
	@Override
	public Object getSystemService(String name) {
		return super.getSystemService(name);
	}
	
	@Override
	public PackageManager getPackageManager() {
//		if (mPackageManager == null) {
//			mPackageManager = new PakcageMangerPolicy(super.getPackageManager());
//		}
//		return mPackageManager;
		
		Log.d(TAG, "getPackageManager" + new Exception().fillInStackTrace());
		return super.getPackageManager();
	}
	
	@Override
	public ApplicationInfo getApplicationInfo() {
		return super.getApplicationInfo();
	}

	@Override
	public void processIntent(Intent intent) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			intent.putExtra(EXTRA_ACTIVITY_CLASS_NAME, c);
			if (!TextUtils.isEmpty(c)) {
				intent.setComponent(new ComponentName(getPackageName(), StubActivity.class.getCanonicalName()));
				ActivityInfoX a = InstalledAPks.getInstance().getActivityInfo(c);
				if (a != null) {
					ApkLuncherActivity.putExtra(a, intent);
				}
			} 
		} else {
			ResolveInfo a = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
			Log.d(TAG, "ResolveInfo a: " + a);
			//				a.activityInfo.
		}
	}
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		Log.d(TAG, "onCreateView(). name: " + name);
		return super.onCreateView(name, context, attrs);
	}
	
	public static class PakcageMangerPolicy extends PackageManager {
		
		private PackageManager mPolicy;

		public PakcageMangerPolicy(PackageManager policy) {
			mPolicy = policy;
		}

		@Override
		public PackageInfo getPackageInfo(String packageName, int flags)
				throws NameNotFoundException {
			return mPolicy.getPackageInfo(packageName, flags);
		}

		@Override
		public String[] currentToCanonicalPackageNames(String[] names) {
			return mPolicy.currentToCanonicalPackageNames(names);
		}

		@Override
		public String[] canonicalToCurrentPackageNames(String[] names) {
			return mPolicy.canonicalToCurrentPackageNames(names);
		}

		@Override
		public Intent getLaunchIntentForPackage(String packageName) {
			return mPolicy.getLaunchIntentForPackage(packageName);
		}

		@Override
		public Intent getLeanbackLaunchIntentForPackage(String packageName) {
			return mPolicy.getLeanbackLaunchIntentForPackage(packageName);
		}

		@Override
		public int[] getPackageGids(String packageName)
				throws NameNotFoundException {
			return mPolicy.getPackageGids(packageName);
		}

		@Override
		public PermissionInfo getPermissionInfo(String name, int flags)
				throws NameNotFoundException {
			return mPolicy.getPermissionInfo(name, flags);
		}

		@Override
		public List<PermissionInfo> queryPermissionsByGroup(String group,
				int flags) throws NameNotFoundException {
			return mPolicy.queryPermissionsByGroup(group, flags);
		}

		@Override
		public PermissionGroupInfo getPermissionGroupInfo(String name, int flags)
				throws NameNotFoundException {
			return mPolicy.getPermissionGroupInfo(name, flags);
		}

		@Override
		public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
			return mPolicy.getAllPermissionGroups(flags);
		}

		@Override
		public ApplicationInfo getApplicationInfo(String packageName, int flags)
				throws NameNotFoundException {
			InstalledAPks apks = InstalledAPks.getInstance();
			if (apks.hasApplication(packageName)
					&& (flags | PackageManager.GET_META_DATA) != 0) {
				ApplicationInfoX aInfo = apks.getApplication(packageName);
				
				return aInfo;
			}
			
			return mPolicy.getApplicationInfo(packageName, flags);
		}

		@Override
		public ActivityInfo getActivityInfo(ComponentName component, int flags)
				throws NameNotFoundException {
			return mPolicy.getActivityInfo(component, flags);
		}

		@Override
		public ActivityInfo getReceiverInfo(ComponentName component, int flags)
				throws NameNotFoundException {
			return mPolicy.getReceiverInfo(component, flags);
		}

		@Override
		public ServiceInfo getServiceInfo(ComponentName component, int flags)
				throws NameNotFoundException {
			return mPolicy.getServiceInfo(component, flags);
		}

		@Override
		public ProviderInfo getProviderInfo(ComponentName component, int flags)
				throws NameNotFoundException {
			return mPolicy.getProviderInfo(component, flags);
		}

		@Override
		public List<PackageInfo> getInstalledPackages(int flags) {
			return mPolicy.getInstalledPackages(flags);
		}

		@Override
		public List<PackageInfo> getPackagesHoldingPermissions(
				String[] permissions, int flags) {
			return mPolicy.getPackagesHoldingPermissions(permissions, flags);
		}

		@Override
		public int checkPermission(String permName, String pkgName) {
			return mPolicy.checkPermission(permName, pkgName);
		}

		@Override
		public boolean addPermission(PermissionInfo info) {
			return mPolicy.addPermission(info);
		}

		@Override
		public boolean addPermissionAsync(PermissionInfo info) {
			return mPolicy.addPermission(info);
		}

		@Override
		public void removePermission(String name) {
			mPolicy.removePermission(name);
		}

		@Override
		public int checkSignatures(String pkg1, String pkg2) {
			return mPolicy.checkSignatures(pkg1, pkg2);
		}

		@Override
		public int checkSignatures(int uid1, int uid2) {
			return mPolicy.checkSignatures(uid1, uid2);
		}

		@Override
		public String[] getPackagesForUid(int uid) {
			return mPolicy.getPackagesForUid(uid);
		}

		@Override
		public String getNameForUid(int uid) {
			return mPolicy.getNameForUid(uid);
		}

		@Override
		public List<ApplicationInfo> getInstalledApplications(int flags) {
			return mPolicy.getInstalledApplications(flags);
		}

		@Override
		public String[] getSystemSharedLibraryNames() {
			return mPolicy.getSystemSharedLibraryNames();
		}

		@Override
		public FeatureInfo[] getSystemAvailableFeatures() {
			return mPolicy.getSystemAvailableFeatures();
		}

		@Override
		public boolean hasSystemFeature(String name) {
			return mPolicy.hasSystemFeature(name);
		}

		@Override
		public ResolveInfo resolveActivity(Intent intent, int flags) {
			return mPolicy.resolveActivity(intent, flags);
		}

		@Override
		public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
			return mPolicy.queryIntentActivities(intent, flags);
		}

		@Override
		public List<ResolveInfo> queryIntentActivityOptions(
				ComponentName caller, Intent[] specifics, Intent intent,
				int flags) {
			return mPolicy.queryIntentActivityOptions(caller, specifics, intent, flags);
		}

		@Override
		public List<ResolveInfo> queryBroadcastReceivers(Intent intent,
				int flags) {
			return mPolicy.queryBroadcastReceivers(intent, flags);
		}

		@Override
		public ResolveInfo resolveService(Intent intent, int flags) {
			return mPolicy.resolveService(intent, flags);
		}

		@Override
		public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
			return mPolicy.queryIntentServices(intent, flags);
		}

		@Override
		public List<ResolveInfo> queryIntentContentProviders(Intent intent,
				int flags) {
			return mPolicy.queryIntentContentProviders(intent, flags);
		}

		@Override
		public ProviderInfo resolveContentProvider(String name, int flags) {
			return mPolicy.resolveContentProvider(name, flags);
		}

		@Override
		public List<ProviderInfo> queryContentProviders(String processName,
				int uid, int flags) {
			return mPolicy.queryContentProviders(processName, uid, flags);
		}

		@Override
		public InstrumentationInfo getInstrumentationInfo(
				ComponentName className, int flags)
				throws NameNotFoundException {
			return mPolicy.getInstrumentationInfo(className, flags);
		}

		@Override
		public List<InstrumentationInfo> queryInstrumentation(
				String targetPackage, int flags) {
			return mPolicy.queryInstrumentation(targetPackage, flags);
		}

		@Override
		public Drawable getDrawable(String packageName, int resid,
				ApplicationInfo appInfo) {
			return mPolicy.getDrawable(packageName, resid, appInfo);
		}

		@Override
		public Drawable getActivityIcon(ComponentName activityName)
				throws NameNotFoundException {
			return mPolicy.getActivityIcon(activityName);
		}

		@Override
		public Drawable getActivityIcon(Intent intent)
				throws NameNotFoundException {
			return mPolicy.getActivityIcon(intent);
		}

		@Override
		public Drawable getActivityBanner(ComponentName activityName)
				throws NameNotFoundException {
			return mPolicy.getActivityBanner(activityName);
		}

		@Override
		public Drawable getActivityBanner(Intent intent)
				throws NameNotFoundException {
			return mPolicy.getActivityBanner(intent);
		}

		@Override
		public Drawable getDefaultActivityIcon() {
			return mPolicy.getDefaultActivityIcon();
		}

		@Override
		public Drawable getApplicationIcon(ApplicationInfo info) {
			return mPolicy.getApplicationIcon(info);
		}

		@Override
		public Drawable getApplicationIcon(String packageName)
				throws NameNotFoundException {
			return mPolicy.getApplicationIcon(packageName);
		}

		@Override
		public Drawable getApplicationBanner(ApplicationInfo info) {
			return mPolicy.getApplicationBanner(info);
		}

		@Override
		public Drawable getApplicationBanner(String packageName)
				throws NameNotFoundException {
			return mPolicy.getApplicationBanner(packageName);
		}

		@Override
		public Drawable getActivityLogo(ComponentName activityName)
				throws NameNotFoundException {
			return mPolicy.getActivityLogo(activityName);
		}

		@Override
		public Drawable getActivityLogo(Intent intent)
				throws NameNotFoundException {
			return mPolicy.getActivityLogo(intent);
		}

		@Override
		public Drawable getApplicationLogo(ApplicationInfo info) {
			return mPolicy.getApplicationBanner(info);
		}

		@Override
		public Drawable getApplicationLogo(String packageName)
				throws NameNotFoundException {
			return mPolicy.getApplicationIcon(packageName);
		}

		@Override
		public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
			return mPolicy.getUserBadgedIcon(icon, user);
		}

		@Override
		public Drawable getUserBadgedDrawableForDensity(Drawable drawable,
				UserHandle user, Rect badgeLocation, int badgeDensity) {
			return mPolicy.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity);
		}

		@Override
		public CharSequence getUserBadgedLabel(CharSequence label,
				UserHandle user) {
			return mPolicy.getUserBadgedLabel(label, user);
		}

		@Override
		public CharSequence getText(String packageName, int resid,
				ApplicationInfo appInfo) {
			return mPolicy.getText(packageName, resid, appInfo);
		}

		@Override
		public XmlResourceParser getXml(String packageName, int resid,
				ApplicationInfo appInfo) {
			return mPolicy.getXml(packageName, resid, appInfo);
		}

		@Override
		public CharSequence getApplicationLabel(ApplicationInfo info) {
			return mPolicy.getApplicationLabel(info);
		}

		@Override
		public Resources getResourcesForActivity(ComponentName activityName)
				throws NameNotFoundException {
			return mPolicy.getResourcesForActivity(activityName);
		}

		@Override
		public Resources getResourcesForApplication(ApplicationInfo app)
				throws NameNotFoundException {
			return mPolicy.getResourcesForApplication(app);
		}

		@Override
		public Resources getResourcesForApplication(String appPackageName)
				throws NameNotFoundException {
			return mPolicy.getResourcesForApplication(appPackageName);
		}

		@Override
		public void verifyPendingInstall(int id, int verificationCode) {
			mPolicy.verifyPendingInstall(id, verificationCode);
		}

		@Override
		public void extendVerificationTimeout(int id,
				int verificationCodeAtTimeout, long millisecondsToDelay) {
			mPolicy.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay);
		}

		@Override
		public void setInstallerPackageName(String targetPackage,
				String installerPackageName) {
			mPolicy.setInstallerPackageName(targetPackage, installerPackageName);
		}

		@Override
		public String getInstallerPackageName(String packageName) {
			return mPolicy.getInstallerPackageName(packageName);
		}

		@Override
		public void addPackageToPreferred(String packageName) {
			mPolicy.addPackageToPreferred(packageName);
		}

		@Override
		public void removePackageFromPreferred(String packageName) {
			mPolicy.removePackageFromPreferred(packageName);
		}

		@Override
		public List<PackageInfo> getPreferredPackages(int flags) {
			return mPolicy.getPreferredPackages(flags);
		}

		@Override
		public void addPreferredActivity(IntentFilter filter, int match,
				ComponentName[] set, ComponentName activity) {
			mPolicy.addPreferredActivity(filter, match, set, activity);
		}

		@Override
		public void clearPackagePreferredActivities(String packageName) {
			mPolicy.clearPackagePreferredActivities(packageName);
		}

		@Override
		public int getPreferredActivities(List<IntentFilter> outFilters,
				List<ComponentName> outActivities, String packageName) {
			return mPolicy.getPreferredActivities(outFilters, outActivities, packageName);
		}

		@Override
		public void setComponentEnabledSetting(ComponentName componentName,
				int newState, int flags) {
			mPolicy.setComponentEnabledSetting(componentName, newState, flags);
		}

		@Override
		public int getComponentEnabledSetting(ComponentName componentName) {
			return mPolicy.getComponentEnabledSetting(componentName);
		}

		@Override
		public void setApplicationEnabledSetting(String packageName,
				int newState, int flags) {
			mPolicy.setApplicationEnabledSetting(packageName, newState, flags);
		}

		@Override
		public int getApplicationEnabledSetting(String packageName) {
			return mPolicy.getApplicationEnabledSetting(packageName);
		}

		@Override
		public boolean isSafeMode() {
			return mPolicy.isSafeMode();
		}

		@Override
		public PackageInstaller getPackageInstaller() {
			return mPolicy.getPackageInstaller();
		}
		
	}

}
