package org.bbs.apklauncher.emb.auto_gen;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;

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
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
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
public class Target_TabActivity extends 
//SUPER_CLAsS
ContextWrapper
{
	private static final String TAG = Target_TabActivity.class.getSimpleName();

	public static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
	public static final int RESULT_OK = Activity.RESULT_OK;
	public static final int DEFAULT_KEYS_DISABLE = Activity.DEFAULT_KEYS_DISABLE;
	public static final int DEFAULT_KEYS_SEARCH_LOCAL = Activity.DEFAULT_KEYS_SEARCH_LOCAL;
	
	protected Host_TabActivity mHostActivity;
	
	// contructor
	public Target_TabActivity() {
		this(null);
	}
	public Target_TabActivity(Context base) {
		super(base);
		
		mHostActivity = (Host_TabActivity) base;
	}
	
	public Activity getHostActivity() {
		return mHostActivity;
	}
	
	protected void attachBaseContext(Context base) {
		mHostActivity = (Host_TabActivity) base;
		super.attachBaseContext(base);
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
		mHostActivity.overridePendingTransition(enterAnim, exitAnim);
	}

	
	public String getCallingPackage() {
		return mHostActivity.getCallingPackage();
	}

	
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
	
	public void stopLockTask() {
		mHostActivity.stopLockTask();
	}
	
	// tag_start:ActivityGroup
	// tag_end:ActivityGroup
	
	// tag_start:ListActivity
	// tag_end:ListActivity
	
	// tag_start:FragmentActivity
	// tag_end:FragmentActivity
	
	// tag_start:PreferenceActivity
	// tag_end:PreferenceActivity
	
	// tag_start:ExpandableListActivity
	// tag_end:ExpandableListActivity
	
	// tag_start:TabActivity
	public void setDefaultTab(String tag) {
		mHostActivity.setDefaultTab(tag);
	}

	public void setDefaultTab(int index) {
		mHostActivity.setDefaultTab(index);
	}

	public TabHost getTabHost() {
		return mHostActivity.getTabHost();
	}

	public TabWidget getTabWidget() {
		return mHostActivity.getTabWidget();
	}
	// tag_end:TabActivity

	// tag_start:ActionBarActivity
	// tag_end:ActionBarActivity
}
