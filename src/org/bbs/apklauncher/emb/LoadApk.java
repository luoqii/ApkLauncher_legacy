package org.bbs.apklauncher.emb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bbs.apklauncher.emb.auto_gen.Target_FragmentActivity;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.Application;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class LoadApk {
	private static final String TAG = LoadApk.class.getSimpleName();

	static HashMap<String, String> sActivitySuperClassNameMap = new HashMap<String, String>();
	static HashMap<String, String> sSuperClassNameMap = new HashMap<String, String>();
	
	PackageInfoX mApkInfo;
	private String mDexCacheDir;

	private ClassLoader mClassLoader;

	public LoadApk(Application appContext, PackageInfoX apkInfo){
		mApkInfo = apkInfo;
		
		mDexCacheDir = appContext.getDir("apk_code_cache", 0).getPath();
	}
	
	public ClassLoader getClassLoader(ClassLoader parentClassLoader, String libPath) {
		if (mClassLoader != null) {
			return mClassLoader;
		}
		
		ClassLoader c = new DexClassLoader(mApkInfo.applicationInfo.publicSourceDir, mDexCacheDir, libPath, parentClassLoader);
		Log.d(TAG, "new classloader for apk: " + c);
		mClassLoader = c;
		return c;
	}
	
	public static String getActivitySuperClassName(ClassLoader classloader, String activityClassName) {
		String cName = null;
		if (sActivitySuperClassNameMap.containsKey(activityClassName)) {
			cName = sActivitySuperClassNameMap.get(activityClassName);
		}
		if (TextUtils.isEmpty(cName)) {
			try {
				Class<?> clazz = classloader.loadClass(activityClassName);
				List<String> superClassNames = new ArrayList<String>();
				dumpClassType(clazz, superClassNames);
				if (superClassNames.contains(ActionBarActivity.class.getName())) {
					cName = ActionBarActivity.class.getName();
				} else if (superClassNames.contains(FragmentActivity.class.getName())) {
					cName = FragmentActivity.class.getName();
				} else if (superClassNames.contains(ListActivity.class.getName())) {
					cName = ListActivity.class.getName();
				} else if (superClassNames.contains(ExpandableListActivity.class.getName())) {
					cName = ExpandableListActivity.class.getName();
				} else if (superClassNames.contains(Activity.class.getName())) {
					cName = Activity.class.getName();
				} else if (superClassNames.contains(PreferenceActivity.class.getName())){
					cName = PreferenceActivity.class.getName();
				} else if (superClassNames.contains(TabActivity.class.getName())){
					cName = TabActivity.class.getName();
				} else if (superClassNames.contains(ActivityGroup.class.getName())){
					cName = ActivityGroup.class.getName();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!TextUtils.isEmpty(cName)) {
			sActivitySuperClassNameMap.put(activityClassName, cName);
		} else {
			throw new RuntimeException("no usefull super class for activity: " + activityClassName);
		}
		
		Log.d(TAG, "superClass: " + cName + " for class: " + activityClassName);
		return cName;
	}	
	
	public static String getServiceSuperClassName(ClassLoader classloader, String serviceClassName) {
		String cName = null;
		if (sSuperClassNameMap.containsKey(serviceClassName)) {
			cName = sSuperClassNameMap.get(serviceClassName);
		}
		if (TextUtils.isEmpty(cName)) {
			try {
				Class<?> clazz = classloader.loadClass(serviceClassName);
				List<String> superClassNames = new ArrayList<String>();
				dumpClassType(clazz, superClassNames);
				if (superClassNames.contains(Target_Service.class.getName())) {
					cName = Target_Service.class.getName();
				} else if (superClassNames.contains(Target_FragmentActivity.class.getName())) {
					cName = Target_FragmentActivity.class.getName();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!TextUtils.isEmpty(cName)) {
			sSuperClassNameMap.put(serviceClassName, cName);
		} else {
			throw new RuntimeException("no usefull super class for service: " + serviceClassName);
		}
		
		Log.d(TAG, "superClass: " + cName + " for class: " + serviceClassName);
		return cName;
	}
	
	private static void dumpClassType(Class clazz, List<String> superClassName) {
		//========1234567890
		Log.d(TAG, "class        : " + clazz + " name: " + clazz.getName());
		while (!clazz.getName().equals(Object.class.getName())) {
			clazz = clazz.getSuperclass();

			//=========1234567890
			Log.d(TAG, "super class: " + clazz);
			superClassName.add(clazz.getName());
		}
	}
	
	public Class getApplictionClass() {
//		try {
//			Class clazz = Class.forName(mApkInfo.applicationInfo.className);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	
}
