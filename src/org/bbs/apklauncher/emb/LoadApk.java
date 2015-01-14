package org.bbs.apklauncher.emb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bbs.apklauncher.emb.gen.Target_Activity;
import org.bbs.apklauncher.emb.gen.Target_ActivityGroup;
import org.bbs.apklauncher.emb.gen.Target_FragmentActivity;
import org.bbs.apklauncher.emb.gen.Target_ListActivity;
import org.bbs.apklauncher.emb.gen.Target_PreferenceActivity;
import org.bbs.felix.util.PackageParser.PackageInfoX;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class LoadApk {
	private static final String TAG = LoadApk.class.getSimpleName();
	
	static HashMap<String, String> sActivitySuperClassNameMap = new HashMap<String, String>();
	
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
		
		ClassLoader c = new DexClassLoader(mApkInfo.mApkPath, mDexCacheDir, libPath, parentClassLoader);
		Log.d(TAG, "new classloader for apk: " + c);
		mClassLoader = c;
		return c;
	}
	
	public static String getActivitySuperClassName(ClassLoader classloader, String activityClassName) {
		if (sActivitySuperClassNameMap.containsKey(activityClassName)) {
			return sActivitySuperClassNameMap.get(activityClassName);
		}
		String cName = null;
		try {
			Class<?> clazz = classloader.loadClass(activityClassName);
			List<String> superClassNames = new ArrayList<String>();
			dumpActivityType(clazz, superClassNames);
			if (superClassNames.contains(Target_FragmentActivity.class.getName())) {
				cName = Target_FragmentActivity.class.getName();
			} else if (superClassNames.contains(Target_ListActivity.class.getName())) {
				cName = Target_ListActivity.class.getName();
			} else if (superClassNames.contains(Target_Activity.class.getName())) {
				cName = Target_Activity.class.getName();
			} else if (superClassNames.contains(Target_PreferenceActivity.class.getName())){
				cName = Target_PreferenceActivity.class.getName();
			} else if (superClassNames.contains(Target_ActivityGroup.class.getName())){
				cName = Target_ActivityGroup.class.getName();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!TextUtils.isEmpty(cName)) {
			sActivitySuperClassNameMap.put(activityClassName, cName);
		} else {
			throw new RuntimeException("no usefull super class for activity: " + activityClassName);
		}
		return cName;
	}
	
	private static void dumpActivityType(Class clazz, List<String> superClassName) {
		Log.d(TAG, "class      : " + clazz + " name: " + clazz.getName());
		while (!clazz.getName().equals(Object.class.getName())) {
			clazz = clazz.getSuperclass();

			//==========+++++++++++
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
