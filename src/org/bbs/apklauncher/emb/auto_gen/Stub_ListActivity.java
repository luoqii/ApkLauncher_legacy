package org.bbs.apklauncher.emb.auto_gen;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.apklauncher.emb.LoadApk;
import org.bbs.apklauncher.emb.LogClassLoader;
import org.bbs.apklauncher.emb.Host_Application;
import org.bbs.apklauncher.emb.LoadApk;
import org.bbs.apklauncher.emb.LogClassLoader;
import org.bbs.apklauncher.emb.Util;
import org.bbs.apklauncher.emb.ViewCreater;
import org.bbs.apklauncher.emb.auto_gen.Target_Activity;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.IActivityAgent;
import org.bbs.osgi.activity.InstrumentationWrapper;
import org.bbs.osgi.activity.InstrumentationWrapper.CallBack;
import org.bbs.osgi.activity.LazyContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
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

public class Stub_ListActivity extends 
StubBase_ListActivity
implements CallBack {

	/**
	 * type {@link ComponentName}
	 */
	public static final String EXTRA_COMPONENT = "EXTRA_COMPONENT";
	
	private static final String TAG = Stub_ListActivity.class.getSimpleName();
	
	public static Map<String, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<String, WeakReference<ResourcesMerger>>();
	public static Map<String, WeakReference<Context>> sApk2ContextMap = new HashMap<String, WeakReference<Context>>();	
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
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

	private LogClassLoader mLogClassLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		onPrepareActivityStub();
		
		super.onCreate(savedInstanceState);
		
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
		updateTitle();
	}

	private ClassLoader onCreateClassLoader(String apkPath, String libPath) {	
		return InstalledAPks.createClassLoader(apkPath, libPath, mRealBaseContext);
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
		if (InstalledAPks.sLastClassLoader != null) {
			sLastClassLoader = InstalledAPks.sLastClassLoader;
		}
		if (sLastClassLoader != null) {
//			mTargetContext.classLoaderReady(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
			intent.getExtras().setClassLoader(sLastClassLoader);
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
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
		
		mClassLoader = InstalledAPks.getClassLoader(mApkPath);
		if (null == mClassLoader) {
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
			mClassLoader = onCreateClassLoader(mApkPath, mLibPath);
			InstalledAPks.putClassLoader(mApkPath, (mClassLoader));
		}
		sLastClassLoader = mClassLoader;
		mTargetContext.classLoaderReady(mClassLoader);
//		mTargetContext.packageManagerReady(new PackageManagerProxy(mSysPm));
//		mTargetContext.packageNameReady(mActInfo.packageName);
		
		// do appliction init. must before activity init.
		Application app = ((Host_Application)getApplication()).onPrepareApplictionStub(mActInfo.applicationInfo, mClassLoader, mSysPm);
		
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
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
			}
			mTargetThemeId = ReflectUtil.ResourceUtil.selectDefaultTheme(mResourceMerger, mTargetThemeId, mActInfo.applicationInfo.targetSdkVersion);
			Log.d(TAG, "resolved activity theme: " + mTargetThemeId);
			mTargetContext.setTheme(mTargetThemeId);
			mTargetContext.themeReady(mTargetThemeId);
			
			mTargetContext.resReady(mResourceMerger);
			
			ReflectUtil.ActivityReflectUtil.setActivityApplication(this, app);
			Class clazz = mClassLoader.loadClass(mActivityClassName);
			mTargetActivity = (Target_ListActivity) clazz.newInstance();
			mTargetContext.hostActivityReady(this);
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
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
		}
		if (!TextUtils.isEmpty(title)) {
			setTitle(title);
		}
	}

	// XXX are we need this really???
	@Override
	public Theme getTheme() {
		return mTargetContext.getTheme();
	}
	// for Window to get target's resource
	public Resources getResources() {
		return mTargetContext.getResources();
	}	
	
	@Override
	public ClassLoader getClassLoader() {
		if (mLogClassLoader == null) {
			mLogClassLoader = new LogClassLoader(super.getClassLoader());
		}
		return mLogClassLoader;
		
//		return super.getClassLoader();
	}

	@Override
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
	public void processIntent(Intent intent) {
		 Util.onProcessStartActivityIntent(intent, mClassLoader, mRealBaseContext);
	}
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		View view = super.onCreateView(name, context, attrs);
		return view != null ? view : ViewCreater.onCreateView(name, context, attrs, mClassLoader, mTargetActivity);
	}
}
