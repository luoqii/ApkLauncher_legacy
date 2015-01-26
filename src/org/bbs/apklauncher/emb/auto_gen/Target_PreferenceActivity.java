package org.bbs.apklauncher.emb.auto_gen;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;
import org.bbs.osgi.activity.LazyContext;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager.TaskDescription;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TaskStackBuilder;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.SharedElementCallback;
import android.app.TabActivity;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceActivity.Header;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toolbar;

@SuppressLint("NewApi")
public class Target_PreferenceActivity extends 
//SUPER_CLAsS
ContextWrapper
{
	private static final String TAG = Target_PreferenceActivity.class.getSimpleName();

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
	public static final int RESULT_OK = Activity.RESULT_OK;
	public static final int DEFAULT_KEYS_DISABLE = Activity.DEFAULT_KEYS_DISABLE;
	public static final int DEFAULT_KEYS_SEARCH_LOCAL = Activity.DEFAULT_KEYS_SEARCH_LOCAL;
	
	protected Host_PreferenceActivity mHostActivity;
	
	// contructor
	public Target_PreferenceActivity() {
		this(null);
	}
	public Target_PreferenceActivity(Context base) {
		super(base);
		
		mHostActivity = (Host_PreferenceActivity) base;
	}
	
	public Host_PreferenceActivity getHostActivity() {
		return mHostActivity;
	}
	
	protected void attachBaseContext(Context base) {
		mHostActivity = getHostActivityFromBaseContext(base);
		super.attachBaseContext(base);
	}
	
	private Host_PreferenceActivity getHostActivityFromBaseContext(Context base) {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		//return (Host_PreferenceActivity) base;	
		if (base instanceof Host_PreferenceActivity) {
			return (Host_PreferenceActivity) base;
		}
		return (Host_PreferenceActivity) ((LazyContext)base).getHostActivity();
	}
	
    public final Application getApplication() {
        return mHostActivity.getApplication();
    }

    public final boolean isChild() {
        return mHostActivity.isChild();
    }

    public final Activity getParent() {
        return mHostActivity.getParent();
    }
    
    public final Cursor managedQuery(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
    	return mHostActivity.managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }
    
    public final void startManagingCursor(Cursor c) {
    	mHostActivity.startManagingCursor(c);
    }
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
    
    public final void stopManagingCursor(Cursor c) {
    	mHostActivity.stopManagingCursor(c);
    }
    
    public final void setDefaultKeyMode(int mode) {
    	mHostActivity.setDefaultKeyMode(mode);
    }
    
    public final boolean requestWindowFeature(int featureId) {
        return mHostActivity.requestWindowFeature(featureId);
    }
    
    public final void setFeatureDrawableResource(int featureId, int resId) {
        mHostActivity.setFeatureDrawableResource(featureId, resId);
    }

    public final void setFeatureDrawableUri(int featureId, Uri uri) {
        mHostActivity.setFeatureDrawableUri(featureId, uri);
    }

    public final void setFeatureDrawable(int featureId, Drawable drawable) {
        mHostActivity.setFeatureDrawable(featureId, drawable);
    }

    public final void setFeatureDrawableAlpha(int featureId, int alpha) {
        mHostActivity.setFeatureDrawableAlpha(featureId, alpha);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
    }
	    
	public final void setResult(int resultCode) {
        mHostActivity.setResult(resultCode);
    }
    
    public final void setResult(int resultCode, Intent data) {
    	mHostActivity.setResult(resultCode, data);
    }
    
    public CharSequence getTitle() {
        return mHostActivity.getTitle();
    }

    public int getTitleColor() {
        return mHostActivity.getTitleColor();
    }
    
    public final void setProgressBarVisibility(boolean visible) {
        mHostActivity.setProgressBarVisibility(visible);
    }
    
    public final void setProgressBarIndeterminateVisibility(boolean visible) {
    	mHostActivity.setProgressBarIndeterminateVisibility(visible);
    }
    
    public final void setProgressBarIndeterminate(boolean indeterminate) {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
    	mHostActivity.setProgressBarIndeterminate(indeterminate);
    }
    
    public final void setProgress(int progress) {
    	mHostActivity.setProgress(progress);
    }
    
    public final void setSecondaryProgress(int secondaryProgress) {
    	mHostActivity.setSecondaryProgress(secondaryProgress);
    }
    
    public final void setVolumeControlStream(int streamType) {
    	mHostActivity.setVolumeControlStream(streamType);
    }
    
    public final int getVolumeControlStream() {
    	return mHostActivity.getVolumeControlStream();
    }
    
    public final void setMediaController(MediaController controller) {
    	mHostActivity.setMediaController(controller);
    }
    
    public final MediaController getMediaController() {
    	return mHostActivity.getMediaController();
    }
    
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
    public final void runOnUiThread(Runnable action) {
    	mHostActivity.runOnUiThread(action);
    }
	
	public Intent getIntent() {
		return mHostActivity.getIntent();
	}

	
	public void setIntent(Intent newIntent) {
		mHostActivity.setIntent(newIntent);
	}

	
	public WindowManager getWindowManager() {
		return mHostActivity.getWindowManager();
	}

	
	public Window getWindow() {
		return mHostActivity.getWindow();
	}

	
	public LoaderManager getLoaderManager() {
		return mHostActivity.getLoaderManager();
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	
	public View getCurrentFocus() {
		return mHostActivity.getCurrentFocus();
	}

	
	protected void onCreate(Bundle savedInstanceState) {
	}

	
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}

	
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	}

	
	public void onRestoreInstanceState(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}

	
	protected void onPostCreate(Bundle savedInstanceState) {
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
	}

	
	protected void onStart() {
	}

	
	protected void onRestart() {
	}

	
	protected void onResume() {
	}

	
	protected void onPostResume() {
	}

	
	protected void onNewIntent(Intent intent) {
	}

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	protected void onSaveInstanceState(Bundle outState) {
	}

	
	public void onSaveInstanceState(Bundle outState,
			PersistableBundle outPersistentState) {
	}

	
	protected void onPause() {
	}

	
	protected void onUserLeaveHint() {
	}

	
	public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
		return false;
	}

	
	public CharSequence onCreateDescription() {
		return null;
	}

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void onProvideAssistData(Bundle data) {
	}

	
	protected void onStop() {
	}

	
	protected void onDestroy() {
	}

	
	public void reportFullyDrawn() {
		mHostActivity.reportFullyDrawn();
	}

	
	public void onConfigurationChanged(Configuration newConfig) {
	}

	
	public int getChangingConfigurations() {
		return mHostActivity.getChangingConfigurations();
	}

	
	public Object getLastNonConfigurationInstance() {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		return null;
	}

	
	public Object onRetainNonConfigurationInstance() {
		return null;
	}

	
	public void onLowMemory() {
	}

	
	public void onTrimMemory(int level) {
	}

	
	public FragmentManager getFragmentManager() {
		return mHostActivity.getFragmentManager();
	}
	
	public void onAttachFragment(Fragment fragment) {
	}
	
	public View findViewById(int id) {
		return mHostActivity.findViewById(id);
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	
	public ActionBar getActionBar() {
		return mHostActivity.getActionBar();
	}

	
	public void setActionBar(Toolbar toolbar) {
		mHostActivity.setActionBar(toolbar);
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

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void addContentView(View view, LayoutParams params) {
		mHostActivity.addContentView(view, params);
	}

	
	public TransitionManager getContentTransitionManager() {
		return mHostActivity.getContentTransitionManager();
	}

	
	public void setContentTransitionManager(TransitionManager tm) {
		mHostActivity.setContentTransitionManager(tm);
	}

	
	public Scene getContentScene() {
		return mHostActivity.getContentScene();
	}

	
	public void setFinishOnTouchOutside(boolean finish) {
		mHostActivity.setFinishOnTouchOutside(finish);
	}

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
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

	
	public void onBackPressed() {
	}

	
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		return false;
	}

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	
	public boolean onTrackballEvent(MotionEvent event) {
		return false;
	}

	
	public boolean onGenericMotionEvent(MotionEvent event) {
		return false;
	}

	
	public void onUserInteraction() {
	}

	
	public void onWindowAttributesChanged(
			android.view.WindowManager.LayoutParams params) {
	}

	
	public void onContentChanged() {
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public void onWindowFocusChanged(boolean hasFocus) {
	}

	
	public void onAttachedToWindow() {
	}

	
	public void onDetachedFromWindow() {
	}

	
	public boolean hasWindowFocus() {
		return mHostActivity.hasWindowFocus();
	}

	
	public boolean dispatchKeyEvent(KeyEvent event) {
		return false;
	}

	
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		return false;
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return false;
	}

	
	public boolean dispatchTrackballEvent(MotionEvent ev) {
		return false;
	}

	
	public boolean dispatchGenericMotionEvent(MotionEvent ev) {
		return false;
	}

	
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
		return false;
	}

	public View onCreatePanelView(int featureId) {
		return null;
	}

	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		return false;
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public boolean onPreparePanel(int featureId, View view, Menu menu) {
		return false;
	}
	
	public boolean onMenuOpened(int featureId, Menu menu) {
		return false;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return false;
	}

	public void onPanelClosed(int featureId, Menu menu) {
	}

	
	public void invalidateOptionsMenu() {
		mHostActivity.invalidateOptionsMenu();
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	
	public boolean onNavigateUp() {
		return false;
	}

	
	public boolean onNavigateUpFromChild(Activity child) {
		return false;
	}

	
	public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
	}

	
	public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
	}

	public void onOptionsMenuClosed(Menu menu) {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}

	
	public void openOptionsMenu() {
		mHostActivity.openOptionsMenu();
	}

	
	public void closeOptionsMenu() {
		mHostActivity.closeOptionsMenu();
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	}

	public void registerForContextMenu(View view) {
		mHostActivity.registerForContextMenu(view);
	}

	public void unregisterForContextMenu(View view) {
		mHostActivity.unregisterForContextMenu(view);
	}
	
	public void openContextMenu(View view) {
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public void closeContextMenu() {
		mHostActivity.closeContextMenu();
	}
	
	public boolean onContextItemSelected(MenuItem item) {
//		return super.onContextItemSelected(item);
		return false;
	}
	
	public void onContextMenuClosed(Menu menu) {
	}
	
    public final void showDialog(int id) {
    	mHostActivity.showDialog(id, null);
    }
    
    public final boolean showDialog(int id, Bundle args) {
    	return mHostActivity.showDialog(id, args);
    }
    
    public final void dismissDialog(int id) {
    	mHostActivity.dismissDialog(id);
    }
    
    public final void removeDialog(int id) {
    	mHostActivity.removeDialog(id);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
    }

	protected Dialog onCreateDialog(int id) {
//		return super.onCreateDialog(id);
		return null;
	}

	protected Dialog onCreateDialog(int id, Bundle args) {
//		return super.onCreateDialog(id, args);
		return null;
	}

	
	protected void onPrepareDialog(int id, Dialog dialog) {
	}

	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
	}
	
	public boolean onSearchRequested() {
		return false;
	}

	
	public void startSearch(String initialQuery, boolean selectInitialQuery,
			Bundle appSearchData, boolean globalSearch) {
		mHostActivity.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}

	
	public void triggerSearch(String query, Bundle appSearchData) {
		mHostActivity.triggerSearch(query, appSearchData);
	}

	
	public void takeKeyEvents(boolean get) {
		mHostActivity.takeKeyEvents(get);
	}

	
	public LayoutInflater getLayoutInflater() {
		return mHostActivity.getLayoutInflater();
	}

	
	public MenuInflater getMenuInflater() {
		return mHostActivity.getMenuInflater();
	}

	
	protected void onApplyThemeResource(Theme theme, int resid, boolean first) {
	}

	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void startActivityForResult(Intent intent, int requestCode) {
		mHostActivity.startActivityForResult(intent, requestCode);
	}

	
	public void startActivityForResult(Intent intent, int requestCode,
			Bundle options) {
		mHostActivity.startActivityForResult(intent, requestCode, options);
	}

	
	public void startIntentSenderForResult(IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags) throws SendIntentException {
		mHostActivity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
				flagsValues, extraFlags);
	}

	
	public void startIntentSenderForResult(IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags, Bundle options)
			throws SendIntentException {
		mHostActivity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
				flagsValues, extraFlags, options);
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public void startActivity(Intent intent) {
		mHostActivity.startActivity(intent);
	}

	
	public void startActivity(Intent intent, Bundle options) {
		mHostActivity.startActivity(intent, options);
	}

	
	public void startActivities(Intent[] intents) {
		mHostActivity.startActivities(intents);
	}

	
	public void startActivities(Intent[] intents, Bundle options) {
		mHostActivity.startActivities(intents, options);
	}

	
	public void startIntentSender(IntentSender intent, Intent fillInIntent,
			int flagsMask, int flagsValues, int extraFlags)
			throws SendIntentException {
		mHostActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
				extraFlags);
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	
	public void startIntentSender(IntentSender intent, Intent fillInIntent,
			int flagsMask, int flagsValues, int extraFlags, Bundle options)
			throws SendIntentException {
		mHostActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
				extraFlags, options);
	}

	
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode);
	}

	
	public boolean startActivityIfNeeded(Intent intent, int requestCode,
			Bundle options) {
		return mHostActivity.startActivityIfNeeded(intent, requestCode, options);
	}

	
	public boolean startNextMatchingActivity(Intent intent) {
		return mHostActivity.startNextMatchingActivity(intent);
	}

	
	public boolean startNextMatchingActivity(Intent intent, Bundle options) {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		return mHostActivity.startNextMatchingActivity(intent, options);
	}

	
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromChild(child, intent, requestCode);
	}

	
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode, Bundle options) {
		mHostActivity.startActivityFromChild(child, intent, requestCode, options);
	}

	
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode);
	}

	
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode, Bundle options) {
		mHostActivity.startActivityFromFragment(fragment, intent, requestCode, options);
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public void startIntentSenderFromChild(Activity child, IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags) throws SendIntentException {
		mHostActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
				flagsMask, flagsValues, extraFlags);
	}

	
	public void startIntentSenderFromChild(Activity child, IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags, Bundle options)
			throws SendIntentException {
		mHostActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
				flagsMask, flagsValues, extraFlags, options);
	}

	
	public void overridePendingTransition(int enterAnim, int exitAnim) {
		Log.w(TAG, "overridePendingTransition() is not impled.");
		mHostActivity.overridePendingTransition(enterAnim, exitAnim);
	}

	
	public String getCallingPackage() {
		return mHostActivity.getCallingPackage();
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	
	public ComponentName getCallingActivity() {
		return mHostActivity.getCallingActivity();
	}

	
	public void setVisible(boolean visible) {
		mHostActivity.setVisible(visible);
	}

	
	public boolean isFinishing() {
		return mHostActivity.isFinishing();
	}

	
	public boolean isDestroyed() {
		return mHostActivity.isDestroyed();
	}
	
	public boolean isChangingConfigurations() {
		return mHostActivity.isChangingConfigurations();
	}
	
	public void recreate() {
		mHostActivity.recreate();
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}

	public void finish() {
		mHostActivity.finish();
	}
	
	public void finishAffinity() {
		mHostActivity.finishAffinity();
	}

	public void finishFromChild(Activity child) {
		mHostActivity.finishFromChild(child);
	}
	
	public void finishAfterTransition() {
		mHostActivity.finishAfterTransition();
	}

	public void finishActivity(int requestCode) {
		mHostActivity.finishActivity(requestCode);
	}

	public void finishActivityFromChild(Activity child, int requestCode) {
		mHostActivity.finishActivityFromChild(child, requestCode);
	}
	
	public void finishAndRemoveTask() {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		mHostActivity.finishAndRemoveTask();
	}

	public boolean releaseInstance() {
		return mHostActivity.releaseInstance();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	public void onActivityReenter(int resultCode, Intent data) {
	}
	
	public PendingIntent createPendingResult(int requestCode, Intent data,
			int flags) {
		return mHostActivity.createPendingResult(requestCode, data, flags);
	}

	public void setRequestedOrientation(int requestedOrientation) {
		mHostActivity.setRequestedOrientation(requestedOrientation);
	}

	public int getRequestedOrientation() {
		return mHostActivity.getRequestedOrientation();
	}
	
	public int getTaskId() {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		return mHostActivity.getTaskId();
	}
	
	public boolean isTaskRoot() {
		return mHostActivity.isTaskRoot();
	}
	
	public boolean moveTaskToBack(boolean nonRoot) {
		return mHostActivity.moveTaskToBack(nonRoot);
	}
	
	public String getLocalClassName() {
		return mHostActivity.getLocalClassName();
	}
	
	public ComponentName getComponentName() {
		return mHostActivity.getComponentName();
	}
	
	public SharedPreferences getPreferences(int mode) {
		return mHostActivity.getPreferences(mode);
	}

	public Object getSystemService(String name) {
		return mHostActivity.getSystemService(name);
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void setTitle(CharSequence title) {
		mHostActivity.setTitle(title);
	}

	
	public void setTitle(int titleId) {
		mHostActivity.setTitle(titleId);
	}

	public void setTitleColor(int textColor) {
		mHostActivity.setTitleColor(textColor);
	}

	protected void onTitleChanged(CharSequence title, int color) {
	}

	protected void onChildTitleChanged(Activity childActivity,
			CharSequence title) {
	}

	public void setTaskDescription(TaskDescription taskDescription) {
		mHostActivity.setTaskDescription(taskDescription);
	}
	
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return null;
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		return null;
	}

	
	public void dump(String prefix, FileDescriptor fd, PrintWriter writer,
			String[] args) {
		mHostActivity.dump(prefix, fd, writer, args);
	}

	public boolean isImmersive() {
		return mHostActivity.isImmersive();
	}
	public boolean requestVisibleBehind(boolean visible) {
		return mHostActivity.requestVisibleBehind(visible);
	}

	public void onVisibleBehindCanceled() {
	}
	
	public void onEnterAnimationComplete() {
	}
	
	public void setImmersive(boolean i) {
		mHostActivity.setImmersive(i);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}

	public ActionMode startActionMode(Callback callback) {
		return mHostActivity.startActionMode(callback);
	}
	
	public ActionMode onWindowStartingActionMode(Callback callback) {
		return null;
	}

	public void onActionModeStarted(ActionMode mode) {
	}
	
	public void onActionModeFinished(ActionMode mode) {
	}

	public boolean shouldUpRecreateTask(Intent targetIntent) {
		return mHostActivity.shouldUpRecreateTask(targetIntent);
	}

	
	public boolean navigateUpTo(Intent upIntent) {
		return mHostActivity.navigateUpTo(upIntent);
	}
	
	public boolean navigateUpToFromChild(Activity child, Intent upIntent) {
		return mHostActivity.navigateUpToFromChild(child, upIntent);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}
	
	public Intent getParentActivityIntent() {
		return mHostActivity.getParentActivityIntent();
	}

	public void setEnterSharedElementCallback(SharedElementCallback callback) {
		mHostActivity.setEnterSharedElementCallback(callback);
	}

	
	public void setExitSharedElementCallback(SharedElementCallback callback) {
		mHostActivity.setExitSharedElementCallback(callback);
	}
	
	public void postponeEnterTransition() {
		mHostActivity.postponeEnterTransition();
	}

	public void startPostponedEnterTransition() {
		mHostActivity.startPostponedEnterTransition();
	}
	
	public void startLockTask() {
		mHostActivity.startLockTask();
	}
	
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void stopLockTask() {
		mHostActivity.stopLockTask();
	}
	
	// tag_start:ActivityGroup
	// tag_end:ActivityGroup
	
	// tag_start:ListActivity
	protected void onListItemClick(ListView l, View v, int position, long id) {
	}
	public void setListAdapter(ListAdapter adapter) {
		mHostActivity.setListAdapter(adapter);
	}
	public void setSelection(int position) {
		mHostActivity.setSelection(position);
	}
	public int getSelectedItemPosition() {
		return mHostActivity.getSelectedItemPosition();
	}

	public long getSelectedItemId() {
		return mHostActivity.getSelectedItemId();
	}
	public ListView getListView() {
		return mHostActivity.getListView();
	}
	public ListAdapter getListAdapter() {
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		return mHostActivity.getListAdapter();
	}
	// tag_end:ListActivity
	
	// tag_start:FragmentActivity
	// tag_end:FragmentActivity
	
	// tag_start:PreferenceActivity
	public boolean hasHeaders() {
		return mHostActivity.hasHeaders();
	}

	public boolean isMultiPane() {
		return mHostActivity.isMultiPane();
	}

	public boolean onIsMultiPane() {
		return mHostActivity.onIsMultiPane();
	}

	public boolean onIsHidingHeaders() {
//		return super.onIsHidingHeaders();
		return false;
	}

	public Header onGetInitialHeader() {
//		return super.onGetInitialHeader();
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
		return null;
	}

	public Header onGetNewHeader() {
//		return super.onGetNewHeader();
		return null;
	}

	public void onBuildHeaders(List<Header> target) {
		
	}

	public void invalidateHeaders() {
		mHostActivity.invalidateHeaders();
	}

	public void loadHeadersFromResource(int resid, List<Header> target) {
		mHostActivity.loadHeadersFromResource(resid, target);
	}

	protected boolean isValidFragment(String fragmentName) {
		return ActivityReflectUtil.isValidFragment(mHostActivity, fragmentName);
	}

	public void setListFooter(View view) {
		mHostActivity.setListFooter(view);
	}
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template

	public void onHeaderClick(Header header, int position) {
//		super.onHeaderClick(header, position);
	}

	public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args,
			int titleRes, int shortTitleRes) {
		return mHostActivity.onBuildStartFragmentIntent(fragmentName, args, titleRes,
				shortTitleRes);
	}

	public void startWithFragment(String fragmentName, Bundle args,
			Fragment resultTo, int resultRequestCode) {
		mHostActivity.startWithFragment(fragmentName, args, resultTo, resultRequestCode);
	}

	public void startWithFragment(String fragmentName, Bundle args,
			Fragment resultTo, int resultRequestCode, int titleRes,
			int shortTitleRes) {
		mHostActivity.startWithFragment(fragmentName, args, resultTo, resultRequestCode,
				titleRes, shortTitleRes);
	}

	public void showBreadCrumbs(CharSequence title, CharSequence shortTitle) {
		mHostActivity.showBreadCrumbs(title, shortTitle);
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public void setParentTitle(CharSequence title, CharSequence shortTitle,
			OnClickListener listener) {
		mHostActivity.setParentTitle(title, shortTitle, listener);
	}

	public void switchToHeader(String fragmentName, Bundle args) {
		mHostActivity.switchToHeader(fragmentName, args);
	}

	public void switchToHeader(Header header) {
		mHostActivity.switchToHeader(header);
	}

	public void startPreferenceFragment(Fragment fragment, boolean push) {
		mHostActivity.startPreferenceFragment(fragment, push);
	}

	public void startPreferencePanel(String fragmentClass, Bundle args,
			int titleRes, CharSequence titleText, Fragment resultTo,
			int resultRequestCode) {
		mHostActivity.startPreferencePanel(fragmentClass, args, titleRes, titleText, resultTo,
				resultRequestCode);
	}

	public void finishPreferencePanel(Fragment caller, int resultCode,
			Intent resultData) {
		mHostActivity.finishPreferencePanel(caller, resultCode, resultData);
//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	}

	public boolean onPreferenceStartFragment(PreferenceFragment caller,
			Preference pref) {
//		return super.onPreferenceStartFragment(caller, pref);
		return false;
	}

	public PreferenceManager getPreferenceManager() {
		return mHostActivity.getPreferenceManager();
	}

	public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
		mHostActivity.setPreferenceScreen(preferenceScreen);
	}

	public PreferenceScreen getPreferenceScreen() {
		return mHostActivity.getPreferenceScreen();
	}
	public void addPreferencesFromIntent(Intent intent) {
		mHostActivity.addPreferencesFromIntent(intent);
	}

	public void addPreferencesFromResource(int preferencesResId) {
		mHostActivity.addPreferencesFromResource(preferencesResId);
	}

//do NOT edit this file, auto-generated by createTemplage.groovy from Target_Activity.java.template
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		return mHostActivity.onPreferenceTreeClick(preferenceScreen, preference);
	}

	public Preference findPreference(CharSequence key) {
		return mHostActivity.findPreference(key);
	}	
	// tag_end:PreferenceActivity
	
	// tag_start:ExpandableListActivity
	// tag_end:ExpandableListActivity
	
	// tag_start:TabActivity
	// tag_end:TabActivity

	// tag_start:ActionBarActivity
	// tag_end:ActionBarActivity
}
