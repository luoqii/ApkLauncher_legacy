package org.bbs.apklauncher;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;
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
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

		mPackageManager = getPackageManager();
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
			
			mLazyContext.resReady(mResourceMerger);
			EmbeddedActivityAgent.copyContext(this, mTargetActivity, mResourceMerger);
			
			CharSequence title = "";
			if (mActInfo.labelRes  > 0) {
				title = mResourceMerger.getString(mActInfo.labelRes);
			}
			if (TextUtils.isEmpty(title)) {
				title = mActInfo.nonLocalizedLabel;
			}
			if (!TextUtils.isEmpty(title)) {
				mTargetActivity.setTitle(title);
//				setTitle(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error in create activity: " + mActivityClassName , e);
		}
		
		WeakReference<Application> rp = sApk2ApplicationtMap.get(mApkPath);
		if (rp != null && rp.get() != null) {
			
		} else {
			if (!TextUtils.isEmpty(mApplicationClassName)) {
				try {
					Application app = ((Application) mClassLoader.loadClass(mApplicationClassName).newInstance());
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<Application>(app));

					((ApkLauncherApplication)getApplication()).attachBundleAplication(app, mResourceMerger, mLazyContext);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("error in create application: " + mApplicationClassName , e);
				}
			}
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
		// TODO Auto-generated method stub
		return super.getSystemService(name);
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
			ResolveInfo a = mPackageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
			Log.d(TAG, "ResolveInfo a: " + a);
			//				a.activityInfo.
		}
	}

}
