package org.bbs.apklauncher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.ManifestInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ApplicationInfoX;

import android.content.Context;
import android.content.pm.ActivityInfo;

public class InstalledAPks {
	private static InstalledAPks sInstance;
	private ArrayList<ManifestInfoX> mInfos;
	
	private InstalledAPks() {
		
	}
	
	public void init(Context context, File apkDir){
		mInfos = new ArrayList<PackageParser.ManifestInfoX>();
		if (null == apkDir) {
			return ;
		}
		String[] files = apkDir.list();
		for (String f : files) {
			File file = new File(apkDir.getAbsolutePath() + "/" + f);
			if (file.exists() && file.getAbsolutePath().endsWith("apk")){
				ManifestInfoX info = PackageParser.parseAPk(context, file.getAbsolutePath());
				mInfos.add(info);
			}
		}
	}
	
	public ApplicationInfoX getApplication(String applicationName) {
		ApplicationInfoX a = null;
		boolean has = false;
		for (ManifestInfoX m : mInfos) {
			if (applicationName.equals(m.mApplictionInfo.name)) {
				has = true;
				a = m.mApplictionInfo;
				break;
			}
		}
		
		return a;
	}
	
	public boolean hasApplication(String applicationName) {
		boolean has = false;
		for (ManifestInfoX m : mInfos) {
			if (applicationName.equals(m.mApplictionInfo.name)) {
				has = true;
				break;
			}
		}
		
		return has;
	}
	
	public ActivityInfoX getActivity(String activityName) {
		ActivityInfoX aInfo = null;
		boolean has = false;
		for (ManifestInfoX m : mInfos) {
			if (m.mApplictionInfo.mActivities != null) {
				for (ActivityInfoX a : m.mApplictionInfo.mActivities) {
					if (activityName.equals(a.name)) {
						has = true;
						aInfo = a;
						break;
					}
				}
			}
		}
		
		return aInfo;
	}
	
	public boolean hasActivity(String activityName) {
		boolean has = false;
		for (ManifestInfoX m : mInfos) {
			if (m.mApplictionInfo.mActivities != null) {
				for (ActivityInfoX a : m.mApplictionInfo.mActivities) {
					if (activityName.equals(a.name)) {
						has = true;
						break;
					}
				}
			}
		}
		
		return has;
	}
	
	public List<ManifestInfoX> getAllApks(){
		return mInfos;
	}
	
	public ActivityInfoX getActivityInfo(String activityClassName) {
		ActivityInfoX info = null;
		for (ManifestInfoX m : mInfos) {
			if (m.mApplictionInfo != null) { 
				if (m.mApplictionInfo.mActivities != null &&  m.mApplictionInfo.mActivities.length > 0) {
					final int count = m.mApplictionInfo.mActivities.length;
					for (int i = 0 ; i < count; i++){
						ActivityInfoX a = m.mApplictionInfo.mActivities[i];
						if (activityClassName.equals(a.name)) {
							return a;
						}
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
