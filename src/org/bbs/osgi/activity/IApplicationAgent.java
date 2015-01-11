package org.bbs.osgi.activity;

import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application.OnProvideAssistDataListener;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

public interface IApplicationAgent {

	public abstract void onTerminate();

	public abstract void onConfigurationChanged(Configuration newConfig);

	public abstract void onLowMemory();

	public abstract void onTrimMemory(int level);

	public abstract void registerComponentCallbacks(ComponentCallbacks callback);

	public abstract void unregisterComponentCallbacks(
			ComponentCallbacks callback);

	public abstract void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback);

	public abstract void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback);

	public abstract void registerOnProvideAssistDataListener(
			OnProvideAssistDataListener callback);

	public abstract void unregisterOnProvideAssistDataListener(
			OnProvideAssistDataListener callback);

	// life-cycle
	public abstract void onCreate();

}