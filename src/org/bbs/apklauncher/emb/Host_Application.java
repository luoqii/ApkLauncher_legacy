package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.PackageManagerProxy;
import org.bbs.apklauncher.emb.IntentHelper.PersistentObject;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;
import org.bbs.osgi.activity.TargetContext;

import android.app.Application;
import android.content.Context;
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
	private PersistentObject mPersistent;
	
	public void attachBundleAplication(Application app, Context baseCcontext){
		ReflectUtil.ActivityReflectUtil.attachBaseContext(app, baseCcontext);
		mTargetAppliction = app;
		
		mTargetAppliction.onCreate();
	}
	
	public /*static*/ Application onPrepareApplictionStub(ApplicationInfo appInfo, 
			ClassLoader classLoader, PackageManager pm) {
		String apkPath = appInfo.publicSourceDir;
		Application app = InstalledAPks.getApplication(appInfo.packageName);
		if (null == app) {
			mPersistent.init(this, classLoader);
			
			String appClassName = appInfo.className;
			if (!TextUtils.isEmpty(appClassName)) {
				try {

					TargetContext appBaseContext = new TargetContext(this);
					Resources appRes = Util.loadApkResource(apkPath, this);
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

					InstalledAPks.putApplication(appInfo.packageName, app);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("error in create application: " + appClassName , e);
				}
			}
		}
		
		return app;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mPersistent = PersistentObject.getsInstance();
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
