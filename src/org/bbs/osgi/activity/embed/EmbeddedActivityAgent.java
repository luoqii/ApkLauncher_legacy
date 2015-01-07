package org.bbs.osgi.activity.embed;

import org.bbs.osgi.activity.ActivityAgent;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 *  all method will call through {@link #mTargetActivity}, so, we can
 *  "embed' an exist activity to {@link BundleActivity}.
 * @author luoqii
 * 
 * @see {@link BundleActivity}
 *
 */
public abstract class EmbeddedActivityAgent extends ActivityAgent {
	static final String TAG = EmbeddedActivityAgent.class.getSimpleName();
	
	protected Activity mTargetActivity;

	private Resources mBundleResource;
	
	public abstract Activity getTargetActivity();
	
	protected void onCreate(Bundle savedInstanceState) {
		mTargetActivity = getTargetActivity();
		
		if (null == mTargetActivity) {
			throw new IllegalStateException("target activity is null");
		}
		
		copyContext(mHostActivity, mTargetActivity, mBundleResource);
		
		ActivityReflectUtil.onCreate(mTargetActivity, savedInstanceState);

	}

	public static void copyContext(Activity hostActivity, Activity targetActivity, Resources newResource) {
		// we become target's base context.
		Context baseContext = new ContextWrapper(hostActivity);
		baseContext = hostActivity;
		ActivityReflectUtil.copyBaseContext(targetActivity, baseContext);
		
		// prepare new activity.
//		ActivityUtil.attach(mHostActivity, mTargetActivity);
//		ActivityReflectUtil.attachBaseContext(targetActivity, hostActivity.getApplication());
		
		ActivityReflectUtil.copyFields(hostActivity, targetActivity);
		
		// do this before activity.onCreate()
		if (null != targetActivity && null != newResource) {
			ActivityReflectUtil.copyNewResouce(targetActivity, newResource);
		}
	}
	
	protected void onResume() {
		ActivityReflectUtil.onResume(mTargetActivity);
	}

	protected void onPause() {
		ActivityReflectUtil.onPause(mTargetActivity);
	}
	
	protected void onDestroy() {
		ActivityReflectUtil.onDestroy(mTargetActivity);
	}
	
	@Override
	public void onBundleResourceReady(Resources source) {
		mBundleResource = source;
	}
	
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		return mTargetActivity.onPreparePanel(arg0, arg1, arg2);
	}
	public boolean onPrepareOptionsMenu(Menu menu) {
		return mTargetActivity.onPrepareOptionsMenu(menu);
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		return mTargetActivity.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return mTargetActivity.onOptionsItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		mTargetActivity.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return mTargetActivity.onContextItemSelected(item);
	}
	@Override
	public void onContextMenuClosed(Menu menu) {
		mTargetActivity.onContextMenuClosed(menu);
	}

	public void onActivityResult(int arg0, int arg1, Intent arg2) {
		ActivityReflectUtil.onActivityResult(mTargetActivity, arg0, arg1, arg2);
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
		mTargetActivity.onConfigurationChanged(newConfig);
	}
	
	public void onContentChanged() {
		mTargetActivity.onContentChanged();
	}

	public void onClick(View view) {
		ActivityReflectUtil.onClick(mTargetActivity, view);
	}
	
	
	
	
}
