package org.bbs.apklauncher.embed;

import org.bbs.osgi.activity.IApplicationAgent;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application.OnProvideAssistDataListener;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;

/**
 * this is not a activity actually.
 * @author luoiqq
 *
 */
public class ApkBase_Application extends ContextWrapper 
implements IApplicationAgent
{
	private Application mHostApplication;
	public ApkBase_Application(Context base) {
		super(base);
		
		mHostApplication = (Application) base;
	}

	@Override
	public void onTerminate() {
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	}
	@Override
	public void onLowMemory() {
	}
	@Override
	public void onTrimMemory(int level) {
	}

	@Override
	public void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		mHostApplication.registerActivityLifecycleCallbacks(callback);
	}

	@Override
	public void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		mHostApplication.unregisterActivityLifecycleCallbacks(callback);
	}

	@Override
	public void registerOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		mHostApplication.registerOnProvideAssistDataListener(callback);
	}

	@Override
	public void unregisterOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		mHostApplication.unregisterOnProvideAssistDataListener(callback);
	}

	@Override
	public void onCreate() {
		
	}

}
