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
public class ActivityAgent extends ComponentAgent implements IActivityAgent {
	
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
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onRestoreInstanceState(android.os.Bundle)
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPostCreate(android.os.Bundle)
	 */
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onRestart()
	 */
	@Override
	public void onRestart() {
	}
	
	// content view
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#setContentView(int)
	 */
	@Override
	public void setContentView(int layoutResID) {
		mHostActivity.setContentView(layoutResID);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#setContentView(android.view.View)
	 */
	@Override
	public void setContentView(View view) {
		mHostActivity.setContentView(view);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
	 */
	@Override
	public void setContentView(View view, LayoutParams params) {
		mHostActivity.setContentView(view, params);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#setTitle(int)
	 */
	@Override
	public void setTitle(int titleId) {
		mHostActivity.setTitle(titleId);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#setTitle(java.lang.CharSequence)
	 */
	@Override
	public void setTitle(CharSequence title) {
		mHostActivity.setTitle(title);
	}	
		
	// menu.
//	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
//		return false;
//	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
        return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
		return false;
	}

	// start activity
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityFromFragment(android.support.v4.app.Fragment, android.content.Intent, int)
	 */
	@Override
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivity(android.content.Intent)
	 */
	@Override
	public void startActivity(Intent intent) {
		mHostActivity.startActivity(intent);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivity(android.content.Intent, android.os.Bundle)
	 */
	@Override
	@SuppressLint("NewApi")
	public void startActivity(Intent intent, Bundle options) {
		mHostActivity.startActivity(intent, options);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityIfNeeded(android.content.Intent, int)
	 */
	@Override
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityIfNeeded(android.content.Intent, int, android.os.Bundle)
	 */
	@Override
	@SuppressLint("NewApi")
	public boolean startActivityIfNeeded(Intent intent, int requestCode,
			Bundle options) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode, options);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityFromChild(android.app.Activity, android.content.Intent, int)
	 */
	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromChild(child, intent, requestCode);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityFromChild(android.app.Activity, android.content.Intent, int, android.os.Bundle)
	 */
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode, Bundle options) {
		mHostActivity.startActivityFromChild(child, intent, requestCode, options);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityFromFragment(android.app.Fragment, android.content.Intent, int)
	 */
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityFromFragment(android.app.Fragment, android.content.Intent, int, android.os.Bundle)
	 */
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode, Bundle options) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode, options);
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#startActivityForResult(android.content.Intent, int)
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		mHostActivity.startActivityForResult(intent, requestCode);
	}
	
	// activity result.
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	}
	
	// res
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#getResources()
	 */
	@Override
	public Resources getResources() {
		return mHostActivity.getResources();
	}	
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#dispatchKeyEvent(android.view.KeyEvent)
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#dispatchGenericMotionEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchGenericMotionEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#dispatchTrackballEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTrackballEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	}
	
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onContentChanged()
	 */
	@Override
	public void onContentChanged() {
	}
	
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onContextMenuClosed(android.view.Menu)
	 */
	@Override
	public void onContextMenuClosed(Menu menu) {
		
	}
	public void onClick(View view) {
		
	}
	
	// notifiy bundle that a resource which has bundle res is ready.
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onBundleResourceReady(android.content.res.Resources)
	 */
	@Override
	public void onBundleResourceReady(Resources mSourceMerger) {
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onStop()
	 */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreate(android.os.Bundle, android.os.PersistableBundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPostResume()
	 */
	@Override
	public void onPostResume() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreateDialog(int)
	 */
	@Override
	public Dialog onCreateDialog(int id) {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreateDialog(int, android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(int id, Bundle args) {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onKeyLongPress(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onKeyUp(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onKeyMultiple(int, int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onKeyShortcut(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#dispatchKeyShortcutEvent(android.view.KeyEvent)
	 */
	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPostCreate(android.os.Bundle, android.os.PersistableBundle)
	 */
	@Override
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPrepareDialog(int, android.app.Dialog)
	 */
	@Override
	public void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPrepareDialog(int, android.app.Dialog, android.os.Bundle)
	 */
	@Override
	public void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreatePanelMenu(int, android.view.Menu)
	 */
	@Override
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}
	void onMenuItemSelected(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPrepareOptionsPanel(android.view.View, android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsPanel(View view, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPreparePanel(int, android.view.View, android.view.Menu)
	 */
	@Override
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPreparePonCreatePanelViewanel(int)
	 */
	@Override
	public View onPreparePonCreatePanelViewanel(int featureId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onMenuOpened(int, android.view.Menu)
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onOptionsMenuClosed(android.view.Menu)
	 */
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onPanelClosed(int, android.view.Menu)
	 */
	@Override
	public void onPanelClosed(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onCreatePanelView(int)
	 */
	@Override
	public View onCreatePanelView(int featureId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onAttachedToWindow()
	 */
	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onDetachedFromWindow()
	 */
	@Override
	public void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onTitleChanged(java.lang.CharSequence, int)
	 */
	@Override
	public void onTitleChanged(CharSequence title, int color) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onUserLeaveHint()
	 */
	@Override
	public void onUserLeaveHint() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onUserInteraction()
	 */
	@Override
	public void onUserInteraction() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onWindowFocusChanged(boolean)
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.bbs.osgi.activity.IActivityAgent#onWindowAttributesChanged(android.view.WindowManager.LayoutParams)
	 */
	@Override
	public void onWindowAttributesChanged(
			android.view.WindowManager.LayoutParams params) {
		// TODO Auto-generated method stub
		
	}
}
