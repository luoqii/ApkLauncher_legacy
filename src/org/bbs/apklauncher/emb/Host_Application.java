package org.bbs.apklauncher.emb;

import org.bbs.osgi.activity.ReflectUtil;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;

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
	
	@Override
	public Theme getTheme() {
		return getBaseContext().getTheme();
	}

	// for Window to get target's resource
	public Resources getResources() {
		return getBaseContext().getResources();
	}	
	
}
