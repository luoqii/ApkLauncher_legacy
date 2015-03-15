package org.bbs.apklauncher.emb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ServiceInfoX;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;

public class Util {
	private static final String TAG = Util.class.getSimpleName();;
	
	// TODO
	public static final String toMemoryLevel(int level) {
		String levelStr = "";
		levelStr = level + "";
		
		return levelStr;
	}
	
	public static  String getStubClassName(String superClassName) {
		String cName = "";
		if (superClassName.equals(ActionBarActivity.class.getName())) {
			cName = ActionBarActivity.class.getSimpleName();
		} else if (superClassName.equals(FragmentActivity.class.getName())) {
			cName = FragmentActivity.class.getSimpleName();
		} else if (superClassName.equals(ListActivity.class.getName())) {
			cName = ListActivity.class.getSimpleName();
		} else if (superClassName.equals(ExpandableListActivity.class.getName())) {
			cName = ExpandableListActivity.class.getSimpleName();
		} else if (superClassName.equals(Activity.class.getName())) {
			cName = Activity.class.getSimpleName();
		} else if (superClassName.equals(PreferenceActivity.class.getName())){
			cName = PreferenceActivity.class.getSimpleName();
		} else if (superClassName.equals(TabActivity.class.getName())){
			cName = TabActivity.class.getSimpleName();
		} else if (superClassName.equals(ActivityGroup.class.getName())){
			cName = ActivityGroup.class.getSimpleName();
		} else {
			throw new RuntimeException("invalid class: " + superClassName);
		}
		return "org.bbs.apklauncher.emb.auto_gen" + ".Stub_" + cName;
	}

	public static void onProcessStartActivityIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = LoadApk.getActivitySuperClassName(classLoader, c);
				superClassName = getStubClassName(superClassName);
				com = new ComponentName(realContext.getPackageName(), superClassName.replace("Target", "Stub"));
				intent.setComponent(com);
				ActivityInfoX a = InstalledAPks.getInstance().getActivityInfo(c);
				if (a != null) {
					ApkLuncherActivity.putExtra(a, intent);
				}
			} 
		} else {
			Log.w(TAG, "can not handle intent:  "  + intent);
		}
	}

	public static void onProcessStartServiceIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = LoadApk.getServiceSuperClassName(classLoader, c);
				com = new ComponentName(realContext.getPackageName(), superClassName.replace("Target", "Stub"));
				intent.setComponent(com);
				ServiceInfoX a = InstalledAPks.getInstance().getServiceInfo(c);
				if (a != null) {
					intent.putExtra(Stub_Service.EXTRA_COMPONENT, new ComponentName(a.packageName, a.name));
				}
			} 
		} else {
			Log.w(TAG, "can not handle intent:  "  + intent);
		}
	}
	
	@SuppressLint("NewApi")
	public static Object getTargetActivityFormFrag(android.app.Fragment f) {
		Object o = null;
		Activity a = f.getActivity();
		try {
			Method m = a.getClass().getMethod("getTargetActivity", (Class[])null);
			o = m.invoke(a, (Object[]) null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
	public static Object getTargetActivityFormFrag_V4(android.support.v4.app.Fragment f) {
		Object o = null;
		Activity a = f.getActivity();
		try {
			Method m = a.getClass().getMethod("getTargetActivity", (Class[])null);
			o = m.invoke(a, (Object[]) null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
	
}
