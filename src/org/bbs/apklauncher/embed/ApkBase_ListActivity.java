package org.bbs.apklauncher.embed;

import org.bbs.osgi.activity.IListActivityAgent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * this is not a activity actually but support all activity's method..
 * @author luoiqq
 *
 */
public class ApkBase_ListActivity 
extends ContextWrapper 
implements IListActivityAgent
{

	private StubBase_ListActivity mHostActivity;
	
	public ApkBase_ListActivity(Context base) {
		super(base);
		mHostActivity = (StubBase_ListActivity) base;
	}

    // activity-method-beging:ListActivity
	public AbsListView getListView() {
		return mHostActivity.getListView();
	}
	public void setListAdapter(SimpleAdapter simpleAdapter) {
		// TODO Auto-generated method stub
		mHostActivity.setListAdapter(simpleAdapter);
	}
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    }
    // activity-method-end:ListActivity
	
	public void onCreate(Bundle savedInstanceState) {
	}
	
	public Intent getIntent() {
		return mHostActivity.getIntent();
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		
	}
	
	public void onPostCreate(Bundle savedInstanceState) {
		
	}
	
	public void onRestart() {
		
	}
	
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}
	
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}
	
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
//		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
		notImp();
	}
	
	@SuppressLint("NewApi")
	public void startActivityFromFragment(android.app.Fragment fragment,
			Intent intent, int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
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
			Intent intent, int requestCode, Bundle options) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode, options);
	}
	
	public void startActivityForResult(Intent intent, int requestCode) {
		mHostActivity.startActivityForResult(intent, requestCode);
	}
	
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	}
	
	public boolean dispatchKeyEvent(KeyEvent event) {
		return false;
	}
	
	public boolean dispatchGenericMotionEvent(MotionEvent ev) {
		return false;
	}
	
	public boolean dispatchTrackballEvent(MotionEvent ev) {
		return false;
	}
	
	public boolean dispatchTouchEvent(MotionEvent ev) {
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
	
	public void onBundleResourceReady(Resources mSourceMerger) {
	}
	
	public void onStop() {
	}
	
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}
	
	public void onStart() {
	}
	
	public void onPostResume() {
	}
	
	public Dialog onCreateDialog(int id) {
		return null;
	}
	
	public Dialog onCreateDialog(int id, Bundle args) {
		return null;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}
	
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return false;
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return false;
	}
	
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return false;
	}
	
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		return false;
	}
	
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		return false;
	}
	
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}
	
	public void onPrepareDialog(int id, Dialog dialog) {
	}
	
	public void onPrepareDialog(int id, Dialog dialog, Bundle args) {
	}
	
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		return false;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return false;
	}
	
	public boolean onPrepareOptionsPanel(View view, Menu menu) {
		return false;
	}
	
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		return false;
	}
	
	public View onPreparePonCreatePanelViewanel(int featureId) {
		return null;
	}
	
	public boolean onMenuOpened(int featureId, Menu menu) {
		return false;
	}
	
	public void onOptionsMenuClosed(Menu menu) {
	}
	
	public void onPanelClosed(int featureId, Menu menu) {
	}
	
	public View onCreatePanelView(int featureId) {
		return null;
	}
	
	public void onAttachedToWindow() {
	}
	
	public void onDetachedFromWindow() {
	}
	
	public void onTitleChanged(CharSequence title, int color) {
	}
	
	public void onUserLeaveHint() {
	}
	
	public void onUserInteraction() {
	}
	
	public void onWindowFocusChanged(boolean hasFocus) {
	}
	
	public void onWindowAttributesChanged(
			android.view.WindowManager.LayoutParams params) {
	}
	
	public void notImp() {
		throw new RuntimeException("need impl this interface");
	}

	@Override
	public void finishActivity(int requestCode) {
		mHostActivity.finishActivity(requestCode);
	}

	@SuppressLint("NewApi")
	@Override
	public void finishAfterTransition() {
		mHostActivity.finishAfterTransition();
	}

	@Override
	public void finishFromChild(Activity child) {
		mHostActivity.finishFromChild(child);
	}

	@SuppressLint("NewApi")
	@Override
	public void finishAffinity() {
		mHostActivity.finishAffinity();
	}

	@Override
	public void finish() {
		mHostActivity.finish();
	}

	@SuppressLint("NewApi")
	@Override
	public void finishAndRemoveTask() {
		mHostActivity.finishAndRemoveTask();
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		mHostActivity.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
	}
}
