package org.bbs.apklauncher;

import java.io.File;

import org.bbs.apklauncher.emb.Host_Application;
import org.bbs.apklauncher.emb.Util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class ApkLauncherApplication extends 
//Application
Host_Application 
{
	private static final String TAG = ApkLauncherApplication.class.getSimpleName();
	public static final String APK_LAUNCHER_DIR = "apklauncher";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Util.attachExceptionHandler(this);
		
		File apkDir = getDir(APK_LAUNCHER_DIR, 0);
		apkDir = new File(Environment.getExternalStorageDirectory(), "apk");
		
		Log.d(TAG, "apkDir: " + apkDir);
		InstalledAPks apks = InstalledAPks.getInstance();
		apks.init(this, apkDir);
	}
	
//	public void attachBundleAplication(Application app, Context baseContext){
//		ReflectUtil.ApplicationUtil.callAttach(app, baseContext);
//		
//		callStubOnCreate(app);
//		
//		mAgents.add(app);
//	}
	
	@Override
	public Context getApplicationContext() {
		// TODO Auto-generated method stub
		return super.getApplicationContext();
	}
	
	

	
}
