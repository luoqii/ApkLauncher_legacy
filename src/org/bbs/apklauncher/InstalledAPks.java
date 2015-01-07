package org.bbs.apklauncher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.ManifestInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;

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
	
	public List<ManifestInfoX> getAllApks(){
		return mInfos;
	}
	
	public ActivityInfoX getActivityInfo(String activityClassName) {
		ActivityInfoX info = null;
		for (ManifestInfoX m : mInfos) {
			if (m.mAppliction != null) { 
				if (m.mAppliction.mActivities != null &&  m.mAppliction.mActivities.length > 0) {
					final int count = m.mAppliction.mActivities.length;
					for (int i = 0 ; i < count; i++){
						ActivityInfoX a = m.mAppliction.mActivities[i];
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
