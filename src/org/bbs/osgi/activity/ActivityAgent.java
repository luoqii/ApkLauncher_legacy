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
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 *  define method consistency with {@link Activity}.
 *  <p>
 *  {@link #mHostActivity} will be inited (!= null) before {@link #onCreate(Bundle)}, after
 *  {@link #onDestroy()}, it will be un-inited ( == null).
 *  
 *  <p>
 * when add new function, keep it in section, in order.
 * @author luoqii
 *
 * @see {@link BundleActivity}
 */
public class ActivityAgent extends ComponentAgent {
	
	protected FragmentActivity mHostActivity;

	// life-cycle
	protected void onCreate(Bundle savedInstanceState) {
	}
	protected void onResume() {
	}
	protected void onPause() {
	}
	protected void onDestroy() {
		mHostActivity = null;
	}
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	}
	public void onPostCreate(Bundle savedInstanceState) {
	}
	public void onRestart() {
	}
	
	// content view
	public void setContentView(int layoutResID) {
		mHostActivity.setContentView(layoutResID);
	}
	public void setContentView(View view) {
		mHostActivity.setContentView(view);
	}
	public void setContentView(View view, LayoutParams params) {
		mHostActivity.setContentView(view, params);
	}
	public void setTitle(int titleId) {
		mHostActivity.setTitle(titleId);
	}
	public void setTitle(CharSequence title) {
		mHostActivity.setTitle(title);
	}	
		
	// menu.
//	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
//		return false;
//	}
	public boolean onCreateOptionsMenu(Menu menu){
		return false;
	}
	public boolean onOptionsItemSelected(MenuItem item){
        return false;
	}
	public boolean onPrepareOptionsMenu(Menu menu){
		return false;
	}

	// start activity
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}
	public void startActivity(Intent intent) {
		mHostActivity.startActivity(intent);
	}
	@SuppressLint("NewApi")
	public void startActivity(Intent intent, Bundle options) {
		mHostActivity.startActivity(intent, options);
	}
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode);
	}
	@SuppressLint("NewApi")
	public boolean startActivityIfNeeded(Intent intent, int requestCode,
			Bundle options) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode, options);
	}
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromChild(child, intent, requestCode);
	}
	@SuppressLint("NewApi")
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode, Bundle options) {
		mHostActivity.startActivityFromChild(child, intent, requestCode, options);
	}
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode, Bundle options) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode, options);
	}
	public void startActivityForResult(Intent intent, int requestCode) {
		mHostActivity.startActivityForResult(intent, requestCode);
	}
	
	// activity result.
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	}
	
	// res
	public Resources getResources() {
		return mHostActivity.getResources();
	}	
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean dispatchGenericMotionEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean dispatchTrackballEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
	}
	
	public void onContentChanged() {
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
	}
	public boolean onContextItemSelected(MenuItem item) {
		return false;
	}
	public void onContextMenuClosed(Menu menu) {
		
	}
	public void onClick(View view) {
		
	}
	
	// notifiy bundle that a resource which has bundle res is ready.
	public void onBundleResourceReady(Resources mSourceMerger) {
		
	}
	public void onStop() {
		// TODO Auto-generated method stub
		
	}
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// TODO Auto-generated method stub
		
	}
	public void onStart() {
		// TODO Auto-generated method stub
		
	}
	public void onPostResume() {
		// TODO Auto-generated method stub
		
	}
	public Dialog onCreateDialog(int id) {
		return null;
	}
	public Dialog onCreateDialog(int id, Bundle args) {
		return null;
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// TODO Auto-generated method stub
		
	}
	public void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		
	}
	public void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		// TODO Auto-generated method stub
		
	}
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}
	void onMenuItemSelected(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		
	}
	public boolean onPrepareOptionsPanel(View view, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	public View onPreparePonCreatePanelViewanel(int featureId) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		
	}
	public void onPanelClosed(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		
	}
	public View onCreatePanelView(int featureId) {
		// TODO Auto-generated method stub
		return null;
	}
}
