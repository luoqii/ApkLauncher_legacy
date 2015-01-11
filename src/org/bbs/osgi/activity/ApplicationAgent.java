package org.bbs.osgi.activity;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application.OnProvideAssistDataListener;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 *  define method consistency with {@link Application}.
 *  <p>
 *  
 *  <p>
 * when add new function, keep it in section, in order.
 * @author luoqii
 *
 * @see {@link BundleActivity}
 */
public class ApplicationAgent 
extends Application implements IApplicationAgent
{

	Application mHostApplicion;
	
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
	public void registerComponentCallbacks(ComponentCallbacks callback) {
		super.registerComponentCallbacks(callback);
	}

	@Override
	public void unregisterComponentCallbacks(ComponentCallbacks callback) {
		super.unregisterComponentCallbacks(callback);
	}

	@Override
	public void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		super.registerActivityLifecycleCallbacks(callback);
	}

	@Override
	public void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		super.unregisterActivityLifecycleCallbacks(callback);
	}

	@Override
	public void registerOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		super.registerOnProvideAssistDataListener(callback);
	}

	@Override
	public void unregisterOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		super.unregisterOnProvideAssistDataListener(callback);
	}

	// life-cycle
	@Override
	public void onCreate() {
	}

	public Resources getResources() {
		return mHostApplicion.getResources();
	}
		
}
