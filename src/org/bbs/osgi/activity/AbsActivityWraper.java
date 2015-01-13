package org.bbs.osgi.activity;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;

/**
 * if android call us we call through to {@link #mActivityStub};
 * otherwise call super or do ourself.
 * 
 * <p>
 * when add new function, keep it in section, in order.
 * 
 * @author luoqii
 *
 * @see {@link ActivityAgent}
 */
public abstract class AbsActivityWraper extends FragmentActivity {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	private static final String TAG = AbsActivityWraper.class.getSimpleName();
	private static boolean LOG = true;
	// keep stub lowcase.
	private static boolean LOG_stub = true && LOG;
	protected static boolean LOG_LIFECYCLE = false && LOG;
	protected static boolean LOG_MENU = true && LOG;
	protected static boolean LOG_CONTENT = true && LOG;
	protected static boolean LOG_DIALOG = true && LOG;
	protected static boolean LOG_KEY_EVENT = true && LOG;
	protected static boolean LOG_MOTTION_EVENT = true && LOG;
	
	protected Activity mActivityStub;


	// life-cycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		super.onCreate(savedInstanceState);
		
		mActivityStub = onPrepareActivityStub();
		
		ActivityReflectUtil.onCreate(mActivityStub, savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub oncreate(). savedInstanceState: " + savedInstanceState);
		}
	}
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState,
			android.os.PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
		ActivityReflectUtil.onCreate(mActivityStub, savedInstanceState, persistentState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onCreate(). persistentState: " + persistentState);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
	}
	abstract protected Activity onPrepareActivityStub();
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ActivityReflectUtil.onPostCreate(mActivityStub, savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostCreate(). savedInstanceState: " + savedInstanceState);
		}
	}		
	@SuppressLint("NewApi")
	@Override
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		super.onPostCreate(savedInstanceState, persistentState);
		ActivityReflectUtil.onPostCreate(mActivityStub, savedInstanceState, persistentState);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostCreate(). savedInstanceState: " + savedInstanceState + " persistentState: " + persistentState);
		}
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		ActivityReflectUtil.onRestoreInstanceState(mActivityStub, savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onRestoreInstanceState(). savedInstanceState: " + savedInstanceState);
		}
	}
	@Override
	protected void onStart() {
		super.onStart();
		ActivityReflectUtil.onStart(mActivityStub);
		if (LOG_LIFECYCLE) {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		    _log(TAG, "call stub onStart().");
		}
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		ActivityReflectUtil.onRestart(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onRestart(). ");
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		ActivityReflectUtil.onResume(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onResume().");
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
	}    
	@Override
	protected void onPostResume() {
		super.onPostResume();
		ActivityReflectUtil.onPostResume(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostResume().");
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		ActivityReflectUtil.onPause(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPause().");
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	protected void onStop() {
		super.onStop();		
		ActivityReflectUtil.onStop(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onStop().");
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityReflectUtil.onDestroy(mActivityStub);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onDestroy().");
		}
	}    
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void finish() {
		super.finish();
		mActivityStub.finish();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finish().");
		}
	}
	@SuppressLint("NewApi")
	@Override
	public void finishAffinity() {
		super.finishAffinity();
		mActivityStub.finishAffinity();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishAffinity().");
		}
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void finishFromChild(Activity child) {
		super.finishFromChild(child);
		mActivityStub.finishFromChild(child);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishFromChild(). child: " + child);
		}
	}
	@SuppressLint("NewApi")
	@Override
	public void finishAfterTransition() {
		super.finishAfterTransition();
		mActivityStub.finishAfterTransition();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishAfterTransition().");
		}
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void finishActivity(int requestCode) {
		super.finishActivity(requestCode);
		mActivityStub.finishActivity(requestCode);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishActivity(). requestCode: " + requestCode);
		}
	}
	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		super.finishActivityFromChild(child, requestCode);
		mActivityStub.finishActivityFromChild(child, requestCode);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishActivityFromChild(). child: " + child + " requestCode: " + requestCode);
		}
	}
	@SuppressLint("NewApi")
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void finishAndRemoveTask() {
		super.finishAndRemoveTask();
		mActivityStub.finishAndRemoveTask();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub finishAndRemoveTask().");
		}
	}

    // menu
    @Override
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		boolean show = mActivityStub.onCreatePanelMenu(arg0, arg1);
		if (LOG_MENU) {
			_log(TAG, "call stub onCreatePanelMenu(). return: " + show + " arg0: " + arg0 + " arg1: " + arg1);
		}
		return show || super.onCreatePanelMenu(arg0, arg1);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		boolean show = ActivityReflectUtil.onMenuItemSelected(mActivityStub, featureId, item);
		if (LOG_MENU) {
			_log(TAG, "call stub onMenuItemSelected(). return: " + show + " featureId: " + featureId + " item: " + item);
		}
		return show || super.onMenuItemSelected(featureId, item);
	}
	@Override
	public void onPanelClosed(int featureId, Menu menu) {
		mActivityStub.onPanelClosed(featureId, menu);
		if (LOG_MENU) {
			_log(TAG, "call stub onMenuItemSelected(). menu: " + menu);
		}
		super.onPanelClosed(featureId, menu);
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	
	// FragmentActivity
	@Override
	protected boolean onPrepareOptionsPanel(View view, Menu menu) {
		boolean show = ActivityReflectUtil.onPrepareOptionsPanel(mActivityStub, view, menu);
		if (LOG_MENU) {
			_log(TAG, "call stub onPrepareOptionsPanel(). return: " + show + " view: " + view + " menu: " + menu);
		}
		return show || super.onPrepareOptionsPanel(view, menu);
	}
	
	@Override
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		boolean show = mActivityStub.onPreparePanel(arg0, arg1, arg2);
		if (LOG_MENU) {
			_log(TAG, "call stub onPreparePanel(). return: " + show + " arg0: " + arg0 + " arg1: " + arg1 + " arg2: " + arg2);
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		return show || super.onPreparePanel(arg0, arg1, arg2);
	}
	@Override
	public View onCreatePanelView(int featureId) {
		View view = mActivityStub.onCreatePanelView(featureId);
		if (LOG_MENU) {
			_log(TAG, "call stub onCreatePanelView(). return: " + view + " featureId: " + featureId);
		}
		
		return view;
		
//		return super.onCreatePanelView(featureId);
	}
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		boolean show = mActivityStub.onMenuOpened(featureId, menu);
		if (LOG_MENU) {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
			_log(TAG, "call stub onMenuOpened(). return: " + show + " featureId: " + featureId + " menu: " + menu);
		}
		return show || super.onMenuOpened(featureId, menu);
	}
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		mActivityStub.onOptionsMenuClosed(menu);
		if (LOG_MENU) {
			_log(TAG, "call stub onOptionsMenuClosed(). menu: " + menu);
		}
		super.onOptionsMenuClosed(menu);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean show = mActivityStub.onCreateOptionsMenu(menu);
		if (LOG_MENU) {
		    _log(TAG, "call stub onCreateOptionsMenu(). menu: " + menu + " return: " + show);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
		return show;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean show =  mActivityStub.onOptionsItemSelected(item);
		if (LOG_MENU) {
		    _log(TAG, "call stub onOptionsItemSelected(). item: " + item + " return: " + show);
		}
		return show;
	}	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		mActivityStub.onCreateContextMenu(menu, v, menuInfo);
		if (LOG_MENU) {
		    _log(TAG, "call stub onCreateContextMenu(). v: " + v + " menuInfo: " + menuInfo);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		boolean show =  mActivityStub.onContextItemSelected(item);
		if (LOG_MENU) {
		    _log(TAG, "call stub onContextItemSelected(). item: " + item);
		}
		return show;
	}
	@Override
	public void onContextMenuClosed(Menu menu) {
		mActivityStub.onContextMenuClosed(menu);
		if (LOG_MENU) {
		    _log(TAG, "call stub onContextItemSelected(). menu: " + menu);
		}
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	
	
	// content
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mActivityStub.onConfigurationChanged(newConfig);
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onConfigurationChanged(). newConfig: " + newConfig);
		}
	}
	@Override
	public void onContentChanged() {
		super.onContentChanged();
		
		//XXX why we need check???
		if (null != mActivityStub) {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
			mActivityStub.onContentChanged();
			if (LOG_CONTENT) {
		    	_log(TAG, "call stub onContentChanged(). ");
			}
		}
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		
		mActivityStub.onAttachedToWindow();
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onAttachedToWindow(). ");
		}
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		
		mActivityStub.onDetachedFromWindow();
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onDetachedFromWindow(). ");
		}
	}

	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		super.onTitleChanged(title, color);
		
		ActivityReflectUtil.onTitleChanged(mActivityStub, title, color);
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onTitleChanged(). title: " + title + " color: " + color);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
	}

	@Override
	public void onUserInteraction() {
		super.onUserInteraction();

		mActivityStub.onUserInteraction();
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onUserInteraction(). ");
		}
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		ActivityReflectUtil.onUserLeaveHint(mActivityStub);
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onUserLeaveHint(). ");
		}
	}

	@Override
	public void onWindowAttributesChanged(LayoutParams params) {
		super.onWindowAttributesChanged(params);
		
		mActivityStub.onWindowAttributesChanged(params);
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onWindowAttributesChanged(). params: " + params);
		}
	}

	@Override
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		mActivityStub.onWindowFocusChanged(hasFocus);
		if (LOG_CONTENT) {
		    _log(TAG, "call stub onWindowFocusChanged(). hasFocus: " + hasFocus);
		}
	}
	
	
	// dialog
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = ActivityReflectUtil.onCreateDialog(mActivityStub, id);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " dialog: " + dialog);
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		
		return dialog;
	}
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = ActivityReflectUtil.onCreateDialog(mActivityStub, id, args);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " args: " + args + " dialog: " + dialog);
		}
		return dialog;
	}
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		ActivityReflectUtil.onPrepareDialog(mActivityStub, id, dialog);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onPrepareDialog(). id: " + id + " dialog: " + dialog);
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		ActivityReflectUtil.onPrepareDialog(mActivityStub, id, dialog, args);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " args: " + args + " dialog: " + dialog);
		}
	}
		
		
	// key/motion event
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyDown(keyCode, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyDown(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		return  !handled&& super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyLongPress(keyCode, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyLongPress(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return !handled && super.onKeyLongPress(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyUp(keyCode, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyUp(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return !handled && super.onKeyUp(keyCode, event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		boolean handled = mActivityStub.onKeyMultiple(keyCode, repeatCount, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyMultiple(). handled: " + handled + " keyCode: " + keyCode 
		    								+ " repeatCount: " + repeatCount + " event: " + event);
		}
		return !handled && super.onKeyMultiple(keyCode, repeatCount, event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyShortcut(keyCode, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyShortcut(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		return !handled && super.onKeyShortcut(keyCode, event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean handled = mActivityStub.onTouchEvent(event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onTouchEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.onTouchEvent(event);
	}
	@Override
	public boolean onTrackballEvent(MotionEvent event) {
		boolean handled = mActivityStub.onTrackballEvent(event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onTrackballEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.onTrackballEvent(event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		boolean handled = mActivityStub.dispatchKeyEvent( event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub dispatchKeyEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.dispatchKeyEvent( event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		boolean handled = mActivityStub.dispatchKeyShortcutEvent( event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub dispatchKeyShortcutEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.dispatchKeyShortcutEvent( event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchTouchEvent( event);
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchTouchEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.dispatchTouchEvent(event);
	}
	@Override
	public boolean dispatchTrackballEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchTrackballEvent(event);
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchTrackballEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.dispatchTrackballEvent(event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchGenericMotionEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchGenericMotionEvent(event);
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchGenericMotionEvent(). handled: " + handled + " event: " + event);
		}
		return !handled && super.dispatchGenericMotionEvent(event);
	}
	
	// auxiliary function
	private void _log(String tag, String message) {
	   logD(tag, message);
	   if (LOG_stub) {
	       logD(tag, "sssssstub:" + mActivityStub);
	   }
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
    }
    protected void logD(String tag, String message) {
	   android.util.Log.d(tag, message);
    }
}
