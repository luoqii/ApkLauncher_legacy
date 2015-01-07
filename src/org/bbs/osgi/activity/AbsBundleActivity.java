package org.bbs.osgi.activity;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
import android.view.MotionEvent;
import android.view.View;

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
public abstract class AbsBundleActivity extends FragmentActivity {
	private static final String TAG = AbsBundleActivity.class.getSimpleName();
	private static boolean LOG = true;
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	// keep stub lowcase.
	private static boolean LOG_stub = true && LOG;
	protected static boolean LOG_LIFECYCLE = false && LOG;
	protected static boolean LOG_MENU = true && LOG;
	protected static boolean LOG_CONTENT = true && LOG;
	protected static boolean LOG_DIALOG = true && LOG;
	protected static boolean LOG_KEY_EVENT = false && LOG;
	protected static boolean LOG_MOTTION_EVENT = false && LOG;
	
	protected ActivityAgent mActivityStub;


	// life-cycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		mActivityStub = onPrepareActivityStub();
		
		mActivityStub.onCreate(savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub oncreate(). savedInstanceState: " + savedInstanceState);
		}
	}
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState,
			android.os.PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
		mActivityStub.onCreate(savedInstanceState, persistentState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onCreate(). persistentState: " + persistentState);
		}
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	abstract protected ActivityAgent onPrepareActivityStub();
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActivityStub.onPostCreate(savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostCreate(). savedInstanceState: " + savedInstanceState);
		}
	}		
	@SuppressLint("NewApi")
	@Override
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		super.onPostCreate(savedInstanceState, persistentState);
		mActivityStub.onPostCreate(savedInstanceState, persistentState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostCreate(). savedInstanceState: " + savedInstanceState + " persistentState: " + persistentState);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		}
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mActivityStub.onRestoreInstanceState(savedInstanceState);
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onRestoreInstanceState(). savedInstanceState: " + savedInstanceState);
		}
	}
	@Override
	protected void onStart() {
		super.onStart();
		mActivityStub.onStart();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onStart().");
		}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		mActivityStub.onRestart();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onRestart(). ");
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		mActivityStub.onResume();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onResume().");
		}
	}    
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	protected void onPostResume() {
		super.onPostResume();
		mActivityStub.onPostResume();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPostResume().");
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		mActivityStub.onPause();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onPause().");
		}
	}
	@Override
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	protected void onStop() {
		super.onStop();		
		mActivityStub.onStop();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onStop().");
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mActivityStub.onDestroy();
		if (LOG_LIFECYCLE) {
		    _log(TAG, "call stub onDestroy().");
		}
	}


//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
    // menu
    @Override
	public boolean onCreatePanelMenu(int arg0, Menu arg1) {
		boolean show = mActivityStub.onCreatePanelMenu(arg0, arg1);
		if (LOG_MENU) {
			_log(TAG, "call stub onCreatePanelMenu(). return: " + show + " arg0: " + arg0 + " arg1: " + arg1);
		}
		return show || super.onCreatePanelMenu(arg0, arg1);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		boolean show = mActivityStub.onMenuItemSelected(featureId, item);
		if (LOG_MENU) {
			_log(TAG, "call stub onMenuItemSelected(). return: " + show + " featureId: " + featureId + " item: " + item);
		}
		return show || super.onMenuItemSelected(featureId, item);
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public void onPanelClosed(int featureId, Menu menu) {
		mActivityStub.onPanelClosed(featureId, menu);
		if (LOG_MENU) {
			_log(TAG, "call stub onMenuItemSelected(). menu: " + menu);
		}
		super.onPanelClosed(featureId, menu);
	}
	@Override
	protected boolean onPrepareOptionsPanel(View view, Menu menu) {
		boolean show = mActivityStub.onPrepareOptionsPanel(view, menu);
		if (LOG_MENU) {
			_log(TAG, "call stub onPrepareOptionsPanel(). return: " + show + " view: " + view + " menu: " + menu);
		}
		return show || super.onPrepareOptionsPanel(view, menu);
	}
	@Override
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
		boolean show = mActivityStub.onPreparePanel(arg0, arg1, arg2);
		if (LOG_MENU) {
			_log(TAG, "call stub onPreparePanel(). return: " + show + " arg0: " + arg0 + " arg1: " + arg1 + " arg2: " + arg2);
		}
		return show || super.onPreparePanel(arg0, arg1, arg2);
	}
	@Override
	public View onCreatePanelView(int featureId) {
		View view = mActivityStub.onPreparePonCreatePanelViewanel(featureId);
		if (LOG_MENU) {
			_log(TAG, "call stub onPreparePonCreatePanelViewanel(). return: " + view + " featureId: " + featureId);
		}
		
		return view;
		
//		return super.onCreatePanelView(featureId);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	}
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		boolean show = mActivityStub.onMenuOpened(featureId, menu);
		if (LOG_MENU) {
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
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean show = mActivityStub.onCreateOptionsMenu(menu);
		if (LOG_MENU) {
		    _log(TAG, "call stub onCreateOptionsMenu(). menu: " + menu + " return: " + show);
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
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		mActivityStub.onCreateContextMenu(menu, v, menuInfo);
		if (LOG_MENU) {
		    _log(TAG, "call stub onCreateContextMenu(). v: " + v + " menuInfo: " + menuInfo);
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
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		mActivityStub.onContextMenuClosed(menu);
		if (LOG_MENU) {
		    _log(TAG, "call stub onContextItemSelected(). menu: " + menu);
		}
	}
	
	
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
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	public void onContentChanged() {
		super.onContentChanged();
		
		//XXX why we need check???
		if (null != mActivityStub) {
			mActivityStub.onContentChanged();
			if (LOG_CONTENT) {
		    	_log(TAG, "call stub onContentChanged(). ");
			}
		}
	}
	
	
	// dialog
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = mActivityStub.onCreateDialog(id);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " dialog: " + dialog);
		}
		
		return dialog;
	}
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = mActivityStub.onCreateDialog(id, args);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " args: " + args + " dialog: " + dialog);
		}
		return dialog;
	}
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		mActivityStub.onPrepareDialog(id, dialog);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onPrepareDialog(). id: " + id + " dialog: " + dialog);
		}
	}
	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		mActivityStub.onPrepareDialog(id, dialog, args);
		if (LOG_DIALOG) {
		    _log(TAG, "call stub onCreateDialog(). id: " + id + " args: " + args + " dialog: " + dialog);
		}
	}
		
		
	// key/motion event
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyDown(keyCode, event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyDown(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return  handled&& super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyLongPress(keyCode, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyLongPress(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return handled && super.onKeyLongPress(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyUp(keyCode, event);
		if (LOG_KEY_EVENT) {
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		    _log(TAG, "call stub onKeyUp(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return handled && super.onKeyUp(keyCode, event);
	}
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		boolean handled = mActivityStub.onKeyMultiple(keyCode, repeatCount, event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyMultiple(). handled: " + handled + " keyCode: " + keyCode 
		    								+ " repeatCount: " + repeatCount + " event: " + event);
		}
		return handled && super.onKeyMultiple(keyCode, repeatCount, event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		boolean handled = mActivityStub.onKeyShortcut(keyCode, event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub onKeyShortcut(). handled: " + handled + " keyCode: " + keyCode + " event: " + event);
		}
		return handled && super.onKeyShortcut(keyCode, event);
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		boolean handled = mActivityStub.dispatchKeyEvent( event);
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub dispatchKeyEvent(). handled: " + handled + " event: " + event);
		}
		return handled && super.dispatchKeyEvent( event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		boolean handled = mActivityStub.dispatchKeyShortcutEvent( event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_KEY_EVENT) {
		    _log(TAG, "call stub dispatchKeyShortcutEvent(). handled: " + handled + " event: " + event);
		}
		return handled && super.dispatchKeyShortcutEvent( event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchTouchEvent( event);
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchTouchEvent(). handled: " + handled + " event: " + event);
		}
		return handled && super.dispatchTouchEvent(event);
	}
	@Override
	public boolean dispatchTrackballEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchTrackballEvent(event);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchTrackballEvent(). handled: " + handled + " event: " + event);
		}
		return handled && super.dispatchTrackballEvent(event);
	}
	@SuppressLint("NewApi")
	@Override
	public boolean dispatchGenericMotionEvent(MotionEvent event) {
		boolean handled = mActivityStub.dispatchGenericMotionEvent(event);
		if (LOG_MOTTION_EVENT) {
		    _log(TAG, "call stub dispatchGenericMotionEvent(). handled: " + handled + " event: " + event);
		}
		return handled && super.dispatchGenericMotionEvent(event);
	}
	
	
	// auxiliary function
//do NOT edit this file, auto-generated by createTemplage.groovy from StubActivity.java.template
	private void _log(String tag, String message) {
	   logD(tag, message);
	   if (LOG_stub) {
	       logD(tag, "call stub:" + mActivityStub);
	   }
    }
    protected void logD(String tag, String message) {
	   android.util.Log.d(tag, message);
    }
}
