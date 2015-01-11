package org.bbs.osgi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;

public interface IActivityAgent {

	public abstract void onRestoreInstanceState(Bundle savedInstanceState);

	public abstract void onPostCreate(Bundle savedInstanceState);

	public abstract void onRestart();

	// content view
	public abstract void setContentView(int layoutResID);

	public abstract void setContentView(View view);

	public abstract void setContentView(View view, LayoutParams params);

	public abstract void setTitle(int titleId);

	public abstract void setTitle(CharSequence title);

	// menu.
	//	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
	//		return false;
	//	}
	public abstract boolean onCreateOptionsMenu(Menu menu);

	public abstract boolean onOptionsItemSelected(MenuItem item);

	public abstract boolean onPrepareOptionsMenu(Menu menu);

	// start activity
	public abstract void startActivityFromFragment(Fragment fragment,
			Intent intent, int requestCode);

	public abstract void startActivity(Intent intent);

	@SuppressLint("NewApi")
	public abstract void startActivity(Intent intent, Bundle options);

	public abstract boolean startActivityIfNeeded(Intent intent, int requestCode);

	@SuppressLint("NewApi")
	public abstract boolean startActivityIfNeeded(Intent intent,
			int requestCode, Bundle options);

	public abstract void startActivityFromChild(Activity child, Intent intent,
			int requestCode);

	@SuppressLint("NewApi")
	public abstract void startActivityFromChild(Activity child, Intent intent,
			int requestCode, Bundle options);

	@SuppressLint("NewApi")
	public abstract void startActivityFromFragment(
			android.app.Fragment fragment, Intent intent, int requestCode);

	@SuppressLint("NewApi")
	public abstract void startActivityFromFragment(
			android.app.Fragment fragment, Intent intent, int requestCode,
			Bundle options);

	public abstract void startActivityForResult(Intent intent, int requestCode);

	// activity result.
	public abstract void onActivityResult(int arg0, int arg1, Intent arg2);

	// res
	public abstract Resources getResources();

	public abstract boolean dispatchKeyEvent(KeyEvent event);

	public abstract boolean dispatchGenericMotionEvent(MotionEvent ev);

	public abstract boolean dispatchTrackballEvent(MotionEvent ev);

	public abstract boolean dispatchTouchEvent(MotionEvent ev);

	public abstract void onConfigurationChanged(Configuration newConfig);

	public abstract void onContentChanged();

	public abstract void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo);

	public abstract boolean onContextItemSelected(MenuItem item);

	public abstract void onContextMenuClosed(Menu menu);

	// notifiy bundle that a resource which has bundle res is ready.
	public abstract void onBundleResourceReady(Resources mSourceMerger);

	public abstract void onStop();

	public abstract void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState);

	public abstract void onStart();

	public abstract void onPostResume();

	public abstract Dialog onCreateDialog(int id);

	public abstract Dialog onCreateDialog(int id, Bundle args);

	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	public abstract boolean onKeyLongPress(int keyCode, KeyEvent event);

	public abstract boolean onKeyUp(int keyCode, KeyEvent event);

	public abstract boolean onKeyMultiple(int keyCode, int repeatCount,
			KeyEvent event);

	public abstract boolean onKeyShortcut(int keyCode, KeyEvent event);

	public abstract boolean dispatchKeyShortcutEvent(KeyEvent event);

	public abstract void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState);

	public abstract void onPrepareDialog(int id, Dialog dialog);

	public abstract void onPrepareDialog(int id, Dialog dialog, Bundle args);

	public abstract boolean onCreatePanelMenu(int arg0, Menu arg1);

	public abstract boolean onMenuItemSelected(int featureId, MenuItem item);

	public abstract boolean onPrepareOptionsPanel(View view, Menu menu);

	public abstract boolean onPreparePanel(int arg0, View arg1, Menu arg2);

	public abstract View onPreparePonCreatePanelViewanel(int featureId);

	public abstract boolean onMenuOpened(int featureId, Menu menu);

	public abstract void onOptionsMenuClosed(Menu menu);

	public abstract void onPanelClosed(int featureId, Menu menu);

	public abstract View onCreatePanelView(int featureId);

	public abstract void onAttachedToWindow();

	public abstract void onDetachedFromWindow();

	public abstract void onTitleChanged(CharSequence title, int color);

	public abstract void onUserLeaveHint();

	public abstract void onUserInteraction();

	public abstract void onWindowFocusChanged(boolean hasFocus);

	public abstract void onWindowAttributesChanged(
			android.view.WindowManager.LayoutParams params);

}