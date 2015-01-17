package org.bbs.apklauncher.emb.auto_gen;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.apklauncher.emb.Host_Application;
import org.bbs.apklauncher.emb.LoadApk;
import org.bbs.apklauncher.emb.ViewCreater;
import org.bbs.apklauncher.emb.auto_gen.Host_Activity;
import org.bbs.apklauncher.emb.auto_gen.Target_Activity;
import org.bbs.felix.util.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.IActivityAgent;
import org.bbs.osgi.activity.InstrumentationWrapper;
import org.bbs.osgi.activity.InstrumentationWrapper.CallBack;
import org.bbs.osgi.activity.LazyContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dalvik.system.DexClassLoader;

public class Stub_ActionBarActivity extends 
Host_ActionBarActivity
implements CallBack {

	/**
	 * type {@link ComponentName}
	 */
	public static final String EXTRA_COMPONENT = "EXTRA_COMPONENT";
	
	private static final String TAG = Stub_ActionBarActivity.class.getSimpleName();
	
	public static Map<String, WeakReference<ClassLoader>> sApk2ClassLoaderMap = new HashMap<String, WeakReference<ClassLoader>>();
	public static Map<String, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<String, WeakReference<ResourcesMerger>>();
	public static Map<String, WeakReference<Context>> sApk2ContextMap = new HashMap<String, WeakReference<Context>>();	
	public static Map<String, WeakReference<Application>> sApk2ApplicationtMap = new HashMap<String, WeakReference<Application>>();
	private ClassLoader mClassLoader;
	private String mApplicationClassName;
	private String mActivityClassName;
	private String mApkPath;
	private ComponentName mComponent;
	private ResourcesMerger mResourceMerger;
	private LazyContext mTargetContext;
	private int mTargetThemeId;
	private ActivityInfoX mActInfo;
	private static ClassLoader sLastClassLoader;

	private Theme mTheme;
	private PackageManager mPackageManager;
	private Resources mTargetResource;
	private String mLibPath;
	private PackageManager mSysPm;
	private Context mRealBaseContext;
	private String mPackageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		onPrepareActivityStub();
		
		super.onCreate(savedInstanceState);
		
		updateTitle();
	}

	private ClassLoader onCreateClassLoader(String apkPath, String libPath) {
		ClassLoader c = new DexClassLoader(apkPath, getDir("apk_code_cache", 0).getPath(), libPath, mRealBaseContext.getClassLoader());
		Log.d(TAG, "new classloader for apk: " + c);
		return c;
	}
	
	@Override
	protected void attachBaseContext(Context newBase) {
		mRealBaseContext = newBase;
		mTargetContext = new LazyContext(newBase);
		super.attachBaseContext(mTargetContext);
		mSysPm = getPackageManager();
	}

	protected void onPrepareActivityStub() {
		
		Intent intent = getIntent();
		
		// how to get classloader berfore parse intent.
		if (sLastClassLoader != null) {
//			mTargetContext.classLoaderReady(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
		}
		
		mComponent = intent.getParcelableExtra(EXTRA_COMPONENT);
		
		mActivityClassName = mComponent.getClassName();
		
		mActInfo = InstalledAPks.getInstance().getActivityInfo(mActivityClassName);
		mLibPath = mActInfo.mPackageInfo.mLibPath;
		mApplicationClassName = mActInfo.applicationInfo.className;
		mTargetThemeId = mActInfo.theme;
		mApkPath = mActInfo.applicationInfo.publicSourceDir;
		if (TextUtils.isEmpty(mApplicationClassName)){
			mApplicationClassName = Application.class.getCanonicalName();
			Log.d(TAG, "no packageName, user default.");
		}
		mPackageName = mActInfo.packageName;

		Log.d(TAG, "host activity        : " + this);
		Log.d(TAG, "mApplicationClassName: " + mApplicationClassName);
		Log.d(TAG, "mPackageName         : " + mPackageName);
		Log.d(TAG, "mActivityClassName   : " + mActivityClassName);
		Log.d(TAG, "mThemeId             : " + mTargetThemeId);
		Log.d(TAG, "mApkPath             : " + mApkPath);
		Log.d(TAG, "mLibPath             : " + mLibPath);
		
		WeakReference<ClassLoader> r = sApk2ClassLoaderMap.get(mApkPath);
		if (r != null && r.get() != null) {
			mClassLoader  = r.get();
		} else {
			mClassLoader = onCreateClassLoader(mApkPath, mLibPath);
			sApk2ClassLoaderMap.put(mApkPath, new WeakReference<ClassLoader>(mClassLoader));
		}
		sLastClassLoader = mClassLoader;
		mTargetContext.classLoaderReady(mClassLoader);
//		mTargetContext.packageManagerReady(new PackageManagerProxy(mSysPm));
//		mTargetContext.packageNameReady(mActInfo.packageName);
		
		// do appliction init. must before activity init.
		Application app = null;
		WeakReference<Application> rp = sApk2ApplicationtMap.get(mApkPath);
		if (rp != null && rp.get() != null) {
			app = rp.get();
		} else {
			if (!TextUtils.isEmpty(mApplicationClassName)) {
				try {
					
					LazyContext appBaseContext = new LazyContext(getApplication());
					Resources appRes = BundleActivity.loadApkResource(mApkPath);
					appRes = new ResourcesMerger(appRes, getResources());
					appBaseContext.resReady(appRes);
					int appTheme = mActInfo.applicationInfo.theme;
					if (appTheme  > 0) {
					} else {
					}
					appTheme = ReflectUtil.ResourceUtil.selectDefaultTheme(appRes, appTheme, mActInfo.applicationInfo.targetSdkVersion);
					Log.d(TAG, "resolved application theme: " + appTheme);
					appBaseContext.themeReady(appTheme);

					appBaseContext.packageManagerReady(new PackageManagerProxy(mSysPm));
					appBaseContext.packageNameReady(mPackageName);

					Class clazz = mClassLoader.loadClass(mApplicationClassName);
					
					app = (Application) clazz.newInstance();
					appBaseContext.applicationContextReady(app);
					
					((Host_Application)getApplication()).attachBundleAplication(app, appBaseContext);
					
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<Application>(app));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("error in create application: " + mApplicationClassName , e);
				}
			}
		}
		
		// do activity init
		IActivityAgent agent = null;
		InstrumentationWrapper.injectInstrumentation(this, this);
		try {
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
			}
			mTargetThemeId = ReflectUtil.ResourceUtil.selectDefaultTheme(mResourceMerger, mTargetThemeId, mActInfo.applicationInfo.targetSdkVersion);
			Log.d(TAG, "resolved activity theme: " + mTargetThemeId);
			mTargetContext.setTheme(mTargetThemeId);
			mTargetContext.themeReady(mTargetThemeId);
			
			mTargetContext.resReady(mResourceMerger);
			
			ReflectUtil.ActivityReflectUtil.setApplication(this, app);
			Class clazz = mClassLoader.loadClass(mActivityClassName);
			mTargetActivity = (Target_ActionBarActivity) clazz.newInstance();
			ReflectUtil.ActivityReflectUtil.attachBaseContext(mTargetActivity, this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error in create activity: " + mActivityClassName , e);
		}
		
	}

	private void updateTitle() {
		CharSequence title = "";
		if (mActInfo.labelRes  > 0) {
			title = mResourceMerger.getString(mActInfo.labelRes);
		}
		if (TextUtils.isEmpty(title)) {
			title = mActInfo.nonLocalizedLabel;
		}
		if (!TextUtils.isEmpty(title)) {
			setTitle(title);
		}
	}
	
	@Override
	public Theme getTheme() {
		return mTargetContext.getTheme();
	}

	// for Window to get target's resource
	public Resources getResources() {
		return mTargetContext.getResources();
	}	

	@Override
	public void processIntent(Intent intent) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = LoadApk.getActivitySuperClassName(mClassLoader, c);
				com = new ComponentName(mRealBaseContext.getPackageName(), superClassName.replace("Target", "Stub"));
				intent.setComponent(com);
				ActivityInfoX a = InstalledAPks.getInstance().getActivityInfo(c);
				if (a != null) {
					ApkLuncherActivity.putExtra(a, intent);
				}
			} 
		} else {
			ResolveInfo a = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
			Log.d(TAG, "ResolveInfo a: " + a);
		}
	}
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
//		return super.onCreateView(name, context, attrs);
		return ViewCreater.onCreateView(name, context, attrs, mClassLoader, mTargetActivity);
	}
}
