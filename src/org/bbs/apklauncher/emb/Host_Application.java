package org.bbs.apklauncher.emb;

import org.bbs.osgi.activity.ReflectUtil;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class Host_Application extends 
Application
{
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

	public void onTerminate() {
		mTargetAppliction.onTerminate();
	}

	public void onConfigurationChanged(Configuration newConfig) {
		mTargetAppliction.onConfigurationChanged(newConfig);;
	}

	public void onLowMemory() {
		mTargetAppliction.onLowMemory();
	}

	public void onTrimMemory(int level) {
		mTargetAppliction.onTrimMemory(level);
	}
	
	
	
}
