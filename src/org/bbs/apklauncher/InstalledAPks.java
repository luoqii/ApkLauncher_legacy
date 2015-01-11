package org.bbs.apklauncher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.PackageInfoX;
import org.bbs.felix.util.PackageParser.PackageInfoX.ActivityInfoX;
import org.bbs.felix.util.PackageParser.PackageInfoX.ApplicationInfoX;

import android.content.Context;
import android.content.pm.ActivityInfo;

public class InstalledAPks {
	private static InstalledAPks sInstance;
	private ArrayList<PackageInfoX> mInfos;
	
	private InstalledAPks() {
		
	}
	
	public void init(Context context, File apkDir){
		mInfos = new ArrayList<PackageParser.PackageInfoX>();
		if (null == apkDir) {
			return ;
		}
		String[] files = apkDir.list();
		for (String f : files) {
			File file = new File(apkDir.getAbsolutePath() + "/" + f);
			if (file.exists() && file.getAbsolutePath().endsWith("apk")){
				PackageInfoX info = PackageParser.parseAPk(context, file.getAbsolutePath());
				mInfos.add(info);
			}
		}
	}
	
	public ApplicationInfoX getApplication(String applicationName) {
		ApplicationInfoX a = null;
		boolean has = false;
		for (PackageInfoX m : mInfos) {
			if (applicationName.equals(m.applicationInfo.name)) {
				has = true;
				a = (ApplicationInfoX) m.applicationInfo;
				break;
			}
		}
		
		return a;
	}
	
	public boolean hasApplication(String applicationName) {
		boolean has = false;
		for (PackageInfoX m : mInfos) {
			if (applicationName.equals(m.applicationInfo.packageName)) {
				has = true;
				break;
			}
		}
		
		return has;
	}
	
	public ActivityInfoX getActivity(String activityName) {
		ActivityInfoX aInfo = null;
		boolean has = false;
		for (PackageInfoX m : mInfos) {
			if (m.activities != null) {
				for (ActivityInfo a : m.activities) {
					ActivityInfoX aX = (ActivityInfoX) a;
					if (activityName.equals(a.name)) {
						has = true;
						aInfo = aX;
						break;
					}
				}
			}
		}
		
		return aInfo;
	}
	
	public boolean hasActivity(String activityName) {
		boolean has = false;
		for (PackageInfoX m : mInfos) {
			if (m.activities != null) {
				for (ActivityInfo a : m.activities) {
					if (activityName.equals(a.name)) {
						has = true;
						break;
					}
				}
			}
		}
		
		return has;
	}
	
	public List<PackageInfoX> getAllApks(){
		return mInfos;
	}
	
	public ActivityInfoX getActivityInfo(String activityClassName) {
		ActivityInfoX info = null;
		for (PackageInfoX m : mInfos) {
				if (m.activities != null &&  m.activities.length > 0) {
					final int count = m.activities.length;
					for (int i = 0 ; i < count; i++){
						ActivityInfoX a = (ActivityInfoX) m.activities[i];
						if (activityClassName.equals(a.name)) {
							return a;
						}
				}}
		}
		
		return info;
	}
	
	public static InstalledAPks getInstance() {
		if (null == sInstance) {
			sInstance = new InstalledAPks();
		}
		
		return sInstance;
	}
}
