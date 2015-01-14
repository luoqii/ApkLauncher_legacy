package org.bbs.apklauncher;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import org.bbs.apklauncher.emb.LoadApk;
import org.bbs.felix.util.AndroidUtil;
import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.PackageInfoX;
import org.bbs.felix.util.PackageParser.PackageInfoX.ActivityInfoX;
import org.bbs.felix.util.PackageParser.PackageInfoX.ApplicationInfoX;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;

public class InstalledAPks {
	private static InstalledAPks sInstance;
	
	public static Map<ComponentName, WeakReference<ClassLoader>> sApk2ClassLoaderMap = new HashMap<ComponentName, WeakReference<ClassLoader>>();
	public static Map<ComponentName, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<ComponentName, WeakReference<ResourcesMerger>>();
	public static Map<ComponentName, WeakReference<Context>> sApk2ContextMap = new HashMap<ComponentName, WeakReference<Context>>();	
	public static Map<ComponentName, WeakReference<Application>> sApk2ApplicationtMap = new HashMap<ComponentName, WeakReference<Application>>();

	private ArrayList<PackageInfoX> mInfos;
	private Application mContext;
	
	private InstalledAPks() {
		
	}
	
	public static ClassLoader getClassLoader(ComponentName compName) {
		WeakReference<ClassLoader> weakReference = sApk2ClassLoaderMap.get(compName);
		if (null != weakReference) {
			return weakReference.get();
		}
		return null;
	}
	
	public static void putClassLoader(ComponentName compName, ClassLoader classLoader) {
		sApk2ClassLoaderMap.put(compName, new WeakReference<ClassLoader>(classLoader));
	}
	
	public void init(Application context, File apkDir){
		mContext = context;
		mInfos = new ArrayList<PackageParser.PackageInfoX>();
		
		scanApkDir(apkDir);
	}
	
	public void scanApkDir(File apkDir) {
		if (null == apkDir) {
			return ;
		}
		String[] files = apkDir.list();
		for (String f : files) {
			File file = new File(apkDir.getAbsolutePath() + "/" + f);
			if (file.exists() && file.getAbsolutePath().endsWith("apk")){
				PackageInfoX info = PackageParser.parseAPk(mContext, file.getAbsolutePath());
				mInfos.add(info);
				
				try {
					File dataDir = mContext.getDir("plugin", 0);
					File destDir = new File(dataDir, info.packageName + "/lib");
					AndroidUtil.extractZipEntry(new ZipFile(info.mApkPath), "lib/armeabi", destDir);
					AndroidUtil.extractZipEntry(new ZipFile(info.mApkPath), "lib/armeabi-v7a", destDir);
					info.mLibPath = destDir.getPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	public PackageInfoX getApk(ComponentName comName){
		PackageInfoX p = null;
		for (PackageInfoX a : mInfos) {
			if (a.packageName.equals(comName.getPackageName())){
				p = a;
				break;
			}
		}
		
		return p;
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
	
	// after call this, must init it.
	public static InstalledAPks getInstance() {
		if (null == sInstance) {
			sInstance = new InstalledAPks();
		}
		
		return sInstance;
	}
}
