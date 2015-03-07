package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ServiceInfoX;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

	public static void onProcessStartActivityIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = LoadApk.getActivitySuperClassName(classLoader, c);
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
	
	
}
