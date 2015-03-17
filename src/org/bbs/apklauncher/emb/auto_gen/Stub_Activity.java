package org.bbs.apklauncher.emb.auto_gen;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.apklauncher.emb.Host_Application;
import org.bbs.apklauncher.emb.IntentHelper;
import org.bbs.apklauncher.emb.Util;
import org.bbs.apklauncher.emb.ViewCreater;
import org.bbs.apklauncher.emb.auto_gen.Target_Activity;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.osgi.activity.InstrumentationWrapper;
import org.bbs.osgi.activity.InstrumentationWrapper.CallBack;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;
import org.bbs.osgi.activity.TargetContext;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dalvik.system.DexClassLoader;

public class Stub_Activity extends 
StubBase_Activity
implements CallBack {

	/**
	 * type {@link ComponentName}
	 */
	public static final String EXTRA_COMPONENT_CLASS_NAME = Util.ACTIVITY_EXTRA_COMPONENT_CLASS_NAME;
	
	private static final String TAG = Stub_Activity.class.getSimpleName();
	
	private static ClassLoader sLastClassLoader;
	
	private ClassLoader mTargetClassLoader;
	private Resources mTargetResource;
	private String mTargetPackageName;
	private String mTargetApplicationClassName;
	private String mTargetActivityClassName;
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
	private String mTargetApkPath;
	private TargetContext mTargetContext;
	private int mTargetThemeId;
	private ActivityInfoX mTargetActivityInfo;

	private Theme mTheme;
	private PackageManager mPackageManager;
	private String mLibPath;
	private PackageManager mRealSysPm;
	private Context mRealBaseContext;
	
	private ResourcesMerger mResourceMerger;
	
	private IntentHelper mIntentHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		onPrepareActivityStub();
		
		super.onCreate(savedInstanceState);
		
		updateTitle();
	}

	private ClassLoader onCreateClassLoader(String apkPath, String libPath) {	
		return InstalledAPks.createClassLoader(apkPath, libPath, mRealBaseContext);
	}
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
	
	@Override
	protected void attachBaseContext(Context newBase) {
		mRealBaseContext = newBase;
		mTargetContext = new TargetContext(newBase);
		super.attachBaseContext(mTargetContext);
		
		mRealSysPm = getPackageManager();
	}

	private void onPrepareActivityStub() {
		
		Intent intent = getIntent();
		
		// how to get classloader before parse intent.
		if (InstalledAPks.sLastClassLoader != null) {
			sLastClassLoader = InstalledAPks.sLastClassLoader;
		}
		if (sLastClassLoader != null) {
//			mTargetContext.classLoaderReady(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
			intent.getExtras().setClassLoader(sLastClassLoader);
		}
		
		// get target activity info
		mTargetActivityClassName = intent.getStringExtra(EXTRA_COMPONENT_CLASS_NAME);
		mTargetActivityInfo = InstalledAPks.getInstance().getActivityInfo(mTargetActivityClassName);
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
		mLibPath = mTargetActivityInfo.mPackageInfo.mLibPath;
		mTargetApplicationClassName = mTargetActivityInfo.applicationInfo.className;
		mTargetThemeId = mTargetActivityInfo.theme;
		mTargetApkPath = mTargetActivityInfo.applicationInfo.publicSourceDir;
		if (TextUtils.isEmpty(mTargetApplicationClassName)){
			mTargetApplicationClassName = Application.class.getCanonicalName();
			Log.d(TAG, "no packageName, user default.");
		}
		mTargetPackageName = mTargetActivityInfo.packageName;

		Log.d(TAG, "host activity              : " + this);
		Log.d(TAG, "mTargetApplicationClassName: " + mTargetApplicationClassName);
		Log.d(TAG, "mTargetPackageName         : " + mTargetPackageName);
		Log.d(TAG, "mTargetActivityClassName   : " + mTargetActivityClassName);
		Log.d(TAG, "mThemeId                   : " + mTargetThemeId);
		Log.d(TAG, "mTargetApkPath             : " + mTargetApkPath);
		Log.d(TAG, "mLibPath                   : " + mLibPath);
		
		// create target classloader if necessary.
		mTargetClassLoader = InstalledAPks.getClassLoader(mTargetApkPath);
		if (null == mTargetClassLoader) {
			mTargetClassLoader = onCreateClassLoader(mTargetApkPath, mLibPath);
			InstalledAPks.putClassLoader(mTargetApkPath, (mTargetClassLoader));
		}
		sLastClassLoader = mTargetClassLoader;
		mTargetContext.classLoaderReady(mTargetClassLoader);
//		mTargetContext.packageManagerReady(new PackageManagerProxy(mRealSysPm));
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
//		mTargetContext.packageNameReady(mTargetActivityInfo.packageName);
		
		// do application init. must before activity init.
		Application app = ((Host_Application)getApplication()).onPrepareApplictionStub(mTargetActivityInfo.applicationInfo, 
																						mTargetClassLoader, mRealSysPm);
		
		// do activity init
		InstrumentationWrapper.injectInstrumentation(this, this);
		try {
			WeakReference<ResourcesMerger> rr = InstalledAPks.sApk2ResourceMap.get(mTargetApkPath);
			if (rr != null && rr.get() != null) {
				mResourceMerger = rr.get();
				mTargetResource = mResourceMerger.mFirst;
			} else {
				mTargetResource = Util.loadApkResource(mTargetApkPath);
				mResourceMerger = new ResourcesMerger(mTargetResource, getResources());
				InstalledAPks.sApk2ResourceMap.put(mTargetApkPath, new WeakReference<ResourcesMerger>(mResourceMerger));
			}

			if (mTargetThemeId  > 0) {
			} else {
			}
			mTargetThemeId = ReflectUtil.ResourceUtil.selectDefaultTheme(mResourceMerger, mTargetThemeId, 
																			mTargetActivityInfo.applicationInfo.targetSdkVersion);
			Log.d(TAG, "resolved activity theme: " + mTargetThemeId);
			mTargetContext.setTheme(mTargetThemeId);
			mTargetContext.themeReady(mTargetThemeId);
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
			
			mTargetContext.resReady(mResourceMerger);
			mTargetContext.applicationContextReady(InstalledAPks.getApplication(mTargetPackageName));
			
			ReflectUtil.ActivityReflectUtil.setActivityApplication(this, app);
			Class clazz = mTargetClassLoader.loadClass(mTargetActivityClassName);
			mTargetActivity = (Target_Activity) clazz.newInstance();
			//mTargetContext.hostActivityReady(this);
			mTargetActivity.setHostActivity(this);
			ReflectUtil.ActivityReflectUtil.attachBaseContext(mTargetActivity, this);
		} catch (Exception e) {
			throw new RuntimeException("error in create activity: " + mTargetActivityClassName , e);
		}
		
	}
	
	@Override
	public Intent getIntent() {
		if (null == mIntentHelper) {
			mIntentHelper = new IntentHelper(super.getIntent());
		}
		return mIntentHelper;
		
//		return super.getIntent();
	}

	private void updateTitle() {
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
		CharSequence title = "";
		if (mTargetActivityInfo.labelRes  > 0) {
			title = mResourceMerger.getString(mTargetActivityInfo.labelRes);
		}
		if (TextUtils.isEmpty(title)) {
			title = mTargetActivityInfo.nonLocalizedLabel;
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
	public void processIntent(Intent intent) {
		 Util.onProcessStartActivityIntent(intent, mTargetClassLoader, mRealBaseContext);
	}
	
//do NOT edit this file, auto-generated by host_target.groovy from Target_Activity.java.template
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		View view = super.onCreateView(name, context, attrs);
		return view != null ? view 
							: ViewCreater.onCreateView(name, context, attrs, mTargetClassLoader, mTargetActivity);
	}
}
