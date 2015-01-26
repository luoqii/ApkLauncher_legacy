package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.felix.util.ApkManifestParser.PackageInfoX.ServiceInfoX;
import org.bbs.osgi.activity.ReflectUtil;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;

public class Stub_Service extends Host_Service {
	/**
	 * type {@link ComponentName}
	 */
	public static final String EXTRA_COMPONENT = "EXTRA_COMPONENT";
	private ClassLoader sLastClassLoader;
	private ComponentName mComponent;
	private ClassLoader mClassLoader;
	private String mApkPath;
	private String mServiceClassNmae;
	private boolean mCallOnCreate;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	protected void onPrepareServiceStub(Intent intent) {
		if (null != sLastClassLoader) {
			return ;
		}
		
		// how to get classloader berfore parse intent.
		if (InstalledAPks.sLastClassLoader != null) {
			sLastClassLoader = InstalledAPks.sLastClassLoader;
		} 
		if (sLastClassLoader != null) {
//			mTargetContext.classLoaderReady(sLastClassLoader);
			intent.setExtrasClassLoader(sLastClassLoader);
			intent.getExtras().setClassLoader(sLastClassLoader);
		}
		
		mComponent = intent.getParcelableExtra(EXTRA_COMPONENT);
		mServiceClassNmae = mComponent.getClassName();
		ServiceInfoX s = InstalledAPks.getInstance().getServiceInfo(mServiceClassNmae);
		
		mApkPath = s.applicationInfo.publicSourceDir;
		mClassLoader = InstalledAPks.getClassLoader(mApkPath);
		if (null == mClassLoader) {
			mClassLoader = onCreateClassLoader(mApkPath, s.mPackageInfo.mLibPath);
			InstalledAPks.putClassLoader(mApkPath, (mClassLoader));
		}
		sLastClassLoader = mClassLoader;
		
//		Application app = ((Host_Application)getApplication()).onPrepareApplictionStub(mActInfo.applicationInfo, mClassLoader, mSysPm);
		try {
			mTargetService = (Target_Service) mClassLoader.loadClass(mServiceClassNmae).newInstance();
			if (!mCallOnCreate) {
//				ReflectUtil.ActivityReflectUtil.setApplication(this, app);
				ReflectUtil.ActivityReflectUtil.attachBaseContext(mTargetService, this);
				mTargetService.onCreate();
				mCallOnCreate = true;
			}
		} catch (Exception e) {
			throw new RuntimeException("error in create target service.  class: " + mServiceClassNmae, e);
		}
	}
	
	private ClassLoader onCreateClassLoader(String apkPath, String libPath) {	
		return InstalledAPks.createClassLoader(apkPath, libPath, this);
	}
	
}
