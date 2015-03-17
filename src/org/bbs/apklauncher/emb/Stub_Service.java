package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apkparser.PackageInfoX.ServiceInfoX;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

public class Stub_Service extends Host_Service {
	/**
	 * type {@link ComponentName}
	 */
	public static final String EXTRA_COMPONENT_CLASS_NAME = Util.ACTIVITY_EXTRA_COMPONENT_CLASS_NAME;
	private static final String TAG = Stub_Service.class.getSimpleName();
	private ResourcesMerger mResourceMerger;
	private Resources mTargetResource;
	private Application mRealApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	protected void onPrepareServiceStub(Intent intent) {
		// get target activity info
		String targetServiceClassName = intent.getStringExtra(EXTRA_COMPONENT_CLASS_NAME);
		ServiceInfoX s = InstalledAPks.getInstance().getServiceInfo(targetServiceClassName);
		
		String mApkPath = s.applicationInfo.publicSourceDir;		
		String libPath = s.mPackageInfo.mLibPath;
		String targetApplicationClassName = s.applicationInfo.className;
		String mTargetApkPath = s.applicationInfo.publicSourceDir;
		if (TextUtils.isEmpty(targetApplicationClassName)){
			targetApplicationClassName = Application.class.getCanonicalName();
			Log.d(TAG, "no packageName, user default.");
		}
		String targetPackageName = s.packageName;

		Log.d(TAG, "host activity              : " + this);
		Log.d(TAG, "targetApplicationClassName : " + targetApplicationClassName);
		Log.d(TAG, "targetPackageName          : " + targetPackageName);
		Log.d(TAG, "targetServiceClassName     : " + targetServiceClassName);
		Log.d(TAG, "targetApkPath              : " + mTargetApkPath);
		Log.d(TAG, "libPath                    : " + libPath);
		ClassLoader mTargetClassLoader = InstalledAPks.makeClassLoader(mRealBaseContext, mTargetApkPath, libPath);
		mTargetContext.classLoaderReady(mTargetClassLoader);
		
		// do application init. must before activity init.
		Application app = ((Host_Application)getApplication()).onPrepareApplictionStub(s.applicationInfo, 
																						mTargetClassLoader, mSysPm);
		
		try {
			ReflectUtil.ActivityReflectUtil.setServiceApplication(this, app);
			
			mTargetService = (Service) mTargetClassLoader.loadClass(targetServiceClassName).newInstance();
			ReflectUtil.ActivityReflectUtil.attachBaseContext(mTargetService,
					this
					);
			ReflectUtil.ServiceReflectUtil.copyFiled(this, mTargetService);
			mTargetService.onCreate();
		} catch (Exception e) {
			throw new RuntimeException("error in create target service.  class: " + targetServiceClassName, e);
		}
	}
	
	@Override
	public Object getSystemService(String name) {
		if (Context.NOTIFICATION_SERVICE.equals(name) && null != mRealApplication) {
			return mRealApplication.getSystemService(name);
		}
		return super.getSystemService(name);
	}

}
