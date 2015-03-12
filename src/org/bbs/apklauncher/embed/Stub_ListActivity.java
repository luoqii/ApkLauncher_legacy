package org.bbs.apklauncher.embed;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.apklauncher.emb.Util;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.osgi.activity.ActivityAgent;
import org.bbs.osgi.activity.IActivityAgent;
import org.bbs.osgi.activity.InstrumentationWrapper;
import org.bbs.osgi.activity.InstrumentationWrapper.CallBack;
import org.bbs.osgi.activity.TargetContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

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

public class Stub_ListActivity extends 
//AbsBundleActivity
StubBase_ListActivity
implements CallBack {
	
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_ACTIVITY_CLASS_NAME = "EXTRA_ACTIVITY_CLASS_NAME";
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_LIB_PATH = "EXTRA_LIB_PATH";
	
	static final String TAG = Stub_ListActivity.class.getSimpleName();
	
	public static Map<String, WeakReference<ClassLoader>> sApk2ClassLoaderMap = new HashMap<String, WeakReference<ClassLoader>>();
	public static Map<String, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<String, WeakReference<ResourcesMerger>>();
	public static Map<String, WeakReference<Context>> sApk2ContextMap = new HashMap<String, WeakReference<Context>>();	
	public static Map<String, WeakReference<ApkBase_Application>> sApk2ApplicationtMap = new HashMap<String, WeakReference<ApkBase_Application>>();
	private ClassLoader mClassLoader;
	private String mApplicationClassName;
	private String mActivityClassName;
	private String mApkPath;
//	private Activity mTargetActivity;
	private ResourcesMerger mResourceMerger;
	private TargetContext mTargetContext;
	private int mTargetThemeId;
//	private Theme mTargetTheme;
	private ActivityInfoX mActInfo;
	private static ClassLoader sLastClassLoader;

//	private int mThemeResource = android.R.style.Theme_Black;
	private Theme mTheme;
	private PackageManager mPackageManager;
	private Resources mTargetResource;
	private String mLibPath;
	private PackageManager mSysPm;
	private Context mRealBaseContext;
	private String mPackageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		mThemeResource = (Integer) ReflectUtil.getFiledValue(ContextThemeWrapper.class, this, "mThemeResource");
//		getThemeResId();
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
//		super.attachBaseContext(newBase);
		
		mTargetContext = new TargetContext(newBase);
		super.attachBaseContext(mTargetContext);
		mSysPm = getPackageManager();
	}

	protected IActivityAgent onPrepareActivityStub() {
		
		Intent intent = getIntent();
		
		// how to get classloader berfore parse intent.
		if (sLastClassLoader != null) {
//			mTargetContext.classLoaderReady(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
		}
		
		mActivityClassName = intent.getStringExtra(EXTRA_ACTIVITY_CLASS_NAME);
		mLibPath = intent.getStringExtra(EXTRA_LIB_PATH);
		
		mActInfo = InstalledAPks.getInstance().getActivityInfo(mActivityClassName);
		mApplicationClassName = mActInfo.applicationInfo.className;
		mTargetThemeId = mActInfo.theme;
//		mApkPath = mActInfo.mApkPath;
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
//		mTargetContext.classLoaderReady(mClassLoader);
//		mTargetContext.packageManagerReady(new PakcageMangerPolicy(mSysPm));
//		mTargetContext.packageNameReady(mActInfo.packageName);
		
		// do appliction init. must before activity init.
		ApkBase_Application app = null;
		WeakReference<ApkBase_Application> rp = sApk2ApplicationtMap.get(mApkPath);
		if (rp != null && rp.get() != null) {
			app = rp.get();
		} else {
			if (!TextUtils.isEmpty(mApplicationClassName)) {
				try {
					
					TargetContext appBaseContext = new TargetContext(getApplication());
					Resources appRes = Util.loadApkResource(mApkPath);
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
					Constructor c = clazz.getConstructor(new Class[]{Context.class});

//					app = (Application) c.newInstance(new Object[]{appBaseContext});
					app = (ApkBase_Application) c.newInstance(new Object[]{getApplication()});
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<ApkBase_Application>(app));
//					((ApkLauncherApplication)getApplication()).attachBundleAplication(app, appBaseContext);
					
					sApk2ApplicationtMap.put(mApkPath, new WeakReference<ApkBase_Application>(app));
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
				mTargetResource = Util.loadApkResource(mApkPath);
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
			
			
//			mTargetContext.packageManagerReady(new PakcageMangerPolicy(mSysPm));
//			mTargetContext.packageNameReady(mPackageName);
			
			mTargetContext.resReady(mResourceMerger);
			
//			EmbeddedActivityAgent.copyContext(this, mTargetActivity, mResourceMerger);
//			if (null == app) {
//				throw new IllegalStateException("target apk app is null.");
//			}
//			ReflectUtil.ActivityReflectUtil.setApplication(mTargetActivity, app);
//			ReflectUtil.ActivityReflectUtil.setResource(this, mResourceMerger);
//			ReflectUtil.ActivityReflectUtil.setResource(mTargetActivity, mResourceMerger);
//			ReflectUtil.ActivityReflectUtil.setBaseContext(mTargetActivity, mTargetContext);
//			ReflectUtil.ActivityReflectUtil.setWindowContext(getWindow(), mTargetContext);

//			Object instance = mClassLoader.loadClass(mActivityClassName).newInstance();
//			agent = (ActivityAgent) instance;
//			mTargetActivity =  (Activity) instance;
			Class clazz = mClassLoader.loadClass(mActivityClassName);
			Constructor c = clazz.getConstructor(new Class[]{Context.class});
//			app = ((Application) clazz.newInstance());
			
//			Object o = c.newInstance(new Object[]{mTargetContext});
			Object o = c.newInstance(new Object[]{this});
			
			agent = (IActivityAgent) o;
			agent.setTheme(mTargetThemeId);

			dumpActivityType(agent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error in create activity: " + mActivityClassName , e);
		}
		
		return agent;
	}

	private void dumpActivityType(Object obj) {
		Class clazz = obj.getClass();
		//==========+++++++++++
		Log.d(TAG, "class      : " + clazz + " name: " + clazz.getName());
		while (!clazz.getName().equals(Object.class.getName())) {
			clazz = clazz.getSuperclass();

			//==========+++++++++++
			Log.d(TAG, "super class: " + clazz);
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
			intent.putExtra(EXTRA_ACTIVITY_CLASS_NAME, c);
			if (!TextUtils.isEmpty(c)) {
				intent.setComponent(new ComponentName(mRealBaseContext.getPackageName(), Stub_ListActivity.class.getCanonicalName()));
				intent.putExtra(Stub_ListActivity.EXTRA_LIB_PATH, mLibPath);
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
	
//	@Override
//	public View onCreateView(String name, Context context, AttributeSet attrs) {
////		Log.d(TAG, "onCreateView(). name: " + name);
//		return super.onCreateView(name, context, attrs);
//	}

}
