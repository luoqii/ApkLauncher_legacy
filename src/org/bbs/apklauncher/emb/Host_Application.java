package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.LazyContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

public class Host_Application extends 
Application
{
	private static final String TAG = Host_Application.class.getSimpleName();;
	Application mTargetAppliction;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	public void attachBundleAplication(Application app, Context baseCcontext){
		ReflectUtil.ActivityReflectUtil.attachBaseContext(app, baseCcontext);
		mTargetAppliction = app;
		mTargetAppliction.onCreate();
	}
	
	public Application onPrepareApplictionStub(ApplicationInfo appInfo, 
			ClassLoader classLoader, PackageManager pm	) {
		String apkPath = appInfo.publicSourceDir;
		Application app = InstalledAPks.getApplication(apkPath);
		if (null == app) {
			String appClassName = appInfo.className;
			if (!TextUtils.isEmpty(appClassName)) {
				try {

					LazyContext appBaseContext = new LazyContext(this);
					Resources appRes = BundleActivity.loadApkResource(apkPath);
					appRes = new ResourcesMerger(appRes, getResources());
					appBaseContext.resReady(appRes);
					int appTheme = appInfo.theme;
					if (appTheme  > 0) {
					} else {
					}
					appTheme = ReflectUtil.ResourceUtil.selectDefaultTheme(appRes, appTheme, appInfo.targetSdkVersion);
					Log.d(TAG, "resolved application theme: " + appTheme);
					appBaseContext.themeReady(appTheme);

					appBaseContext.packageManagerReady(new PackageManagerProxy(pm));
					appBaseContext.packageNameReady(appInfo.packageName);

					Class clazz = classLoader.loadClass(appClassName);

					app = (Application) clazz.newInstance();
					appBaseContext.applicationContextReady(app);

					attachBundleAplication(app, appBaseContext);

					InstalledAPks.putApplication(apkPath, (app));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("error in create application: " + appClassName , e);
				}
			}
		}
		return app;
	}

	/**
	 * @deprecated Use {@link Util#onProcessStartActivityIntent(Intent,ClassLoader,Context)} instead
	 */
	public static void onProcessStartActivityIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Util.onProcessStartActivityIntent(intent, classLoader, realContext);
	}	
	
	
	/**
	 * @deprecated Use {@link Util#onProcessStartServiceIntent(Intent,ClassLoader,Context)} instead
	 */
	public static void onProcessStartServiceIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Util.onProcessStartServiceIntent(intent, classLoader, realContext);
	}
	
	public void onTerminate() {
		if (null != mTargetAppliction) {
			mTargetAppliction.onTerminate();
		}
	}

	public void onConfigurationChanged(Configuration newConfig) {
		if (null != mTargetAppliction) {
			mTargetAppliction.onConfigurationChanged(newConfig);;
		}
	}

	public void onLowMemory() {
		if (null != mTargetAppliction) {
			mTargetAppliction.onLowMemory();
		}
	}

	public void onTrimMemory(int level) {
		if (null != mTargetAppliction) {
			mTargetAppliction.onTrimMemory(level);
		}
	}
	
}
