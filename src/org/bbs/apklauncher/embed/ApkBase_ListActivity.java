package org.bbs.apklauncher.embed;

import org.bbs.osgi.activity.IActivityAgent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.SimpleAdapter;

/**
 * this is not a activity actually.
 * @author luoiqq
 *
 */
public class ApkBase_ListActivity extends ContextWrapper 
implements IActivityAgent
{

	private ListActivity mHostActivity;
	public ApkBase_ListActivity(Context base) {
		super(base);
	}
	public void attachActivity(ListActivity activity) {
		mHostActivity = activity;
	}	
	
	public void onCreate(Bundle savedInstanceState) {
	}
	
    protected Intent getIntent() {
		return mHostActivity.getIntent();
	}

    // ListActivity
    protected AbsListView getListView() {
		return mHostActivity.getListView();
	}
    protected void setListAdapter(SimpleAdapter simpleAdapter) {
		// TODO Auto-generated method stub
		mHostActivity.setListAdapter(simpleAdapter);
	}
    
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		
	}
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		
	}
	@Override
	public void onRestart() {
		
	}
	@Override
	public void setContentView(int layoutResID) {
		mHostActivity.setContentView(layoutResID);
	}
	@Override
	public void setContentView(View view) {
		mHostActivity.setContentView(view);
	}
	@Override
	public void setContentView(View view, LayoutParams params) {
		mHostActivity.setContentView(view, params);
	}
	@Override
	public void setTitle(int titleId) {
		mHostActivity.setTitle(titleId);
	}
	@Override
	public void setTitle(CharSequence title) {
		mHostActivity.setTitle(title);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}
	@Override
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
//		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
		notImp();
	}
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}
	@Override
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode);
	}
	@Override
	@SuppressLint("NewApi")
	public boolean startActivityIfNeeded(Intent intent, int requestCode,
			Bundle options) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode, options);
	}
	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromChild(child, intent, requestCode);
	}
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode, Bundle options) {
		mHostActivity.startActivityFromChild(child, intent, requestCode, options);
	}
	@Override
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode, Bundle options) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode, options);
	}
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		mHostActivity.startActivityForResult(intent, requestCode);
	}
	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return false;
	}
	@Override
	public boolean dispatchGenericMotionEvent(MotionEvent ev) {
		return false;
	}
	@Override
	public boolean dispatchTrackballEvent(MotionEvent ev) {
		return false;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return false;
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	}
	@Override
	public void onContentChanged() {
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return false;
	}
	@Override
	public void onContextMenuClosed(Menu menu) {
	}
	@Override
	public void onBundleResourceReady(Resources mSourceMerger) {
	}
	@Override
	public void onStop() {
	}
	@Override
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}
	@Override
	public void onStart() {
	}
	@Override
	public void onPostResume() {
	}
	@Override
	public Dialog onCreateDialog(int id) {
		return null;
	}
	@Override
	public Dialog onCreateDialog(int id, Bundle args) {
		return null;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return false;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return false;
	}
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return false;
	}
	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		return false;
	}
	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		return false;
	}
	@Override
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}
	@Override
	public void onPrepareDialog(int id, Dialog dialog) {
	}
	@Override
	public void onPrepareDialog(int id, Dialog dialog, Bundle args) {
	}
	@Override
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		return false;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return false;
	}
	@Override
	public boolean onPrepareOptionsPanel(View view, Menu menu) {
		return false;
	}
	@Override
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		return false;
	}
	@Override
	public View onPreparePonCreatePanelViewanel(int featureId) {
		return null;
	}
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		return false;
	}
	@Override
	public void onOptionsMenuClosed(Menu menu) {
	}
	@Override
	public void onPanelClosed(int featureId, Menu menu) {
	}
	@Override
	public View onCreatePanelView(int featureId) {
		return null;
	}
	@Override
	public void onAttachedToWindow() {
	}
	@Override
	public void onDetachedFromWindow() {
	}
	@Override
	public void onTitleChanged(CharSequence title, int color) {
	}
	@Override
	public void onUserLeaveHint() {
	}
	@Override
	public void onUserInteraction() {
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	}
	@Override
	public void onWindowAttributesChanged(
			android.view.WindowManager.LayoutParams params) {
	}
	
	public void notImp() {
		throw new RuntimeException("need impl this interface");
	}
}
