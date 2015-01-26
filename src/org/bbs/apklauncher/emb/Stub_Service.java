package org.bbs.apklauncher.emb;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.felix.util.ApkManifestParser.PackageInfoX.ServiceInfoX;
import org.bbs.osgi.activity.BundleActivity;
import org.bbs.osgi.activity.LazyContext;
import org.bbs.osgi.activity.ReflectUtil;
import org.bbs.osgi.activity.ResourcesMerger;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.BaseBundle;

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
	public static Map<String, WeakReference<ResourcesMerger>> sApk2ResourceMap = new HashMap<String, WeakReference<ResourcesMerger>>();
	private ResourcesMerger mResourceMerger;
	private Resources mTargetResource;
	private LazyContext mTargetContext;
	private Context mRealBaseContext;
	private PackageManager mSysPm;

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	protected void attachBaseContext(Context newBase) {
		mRealBaseContext = newBase;
		mTargetContext = new LazyContext(newBase);
		super.attachBaseContext(mTargetContext);
		mSysPm = getPackageManager();
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
		
		try {
			WeakReference<ResourcesMerger> rr = sApk2ResourceMap.get(mApkPath);
			if (rr != null && rr.get() != null) {
				mResourceMerger = rr.get();
				mTargetResource = mResourceMerger.mFirst;
			} else {
				mTargetResource = BundleActivity.loadApkResource(mApkPath);
				mResourceMerger = new ResourcesMerger(mTargetResource, getResources());
				sApk2ResourceMap.put(mApkPath, new WeakReference<ResourcesMerger>(mResourceMerger));
			}

//			if (mTargetThemeId  > 0) {
//			} else {
//			}
//			mTargetThemeId = ReflectUtil.ResourceUtil.selectDefaultTheme(mResourceMerger, mTargetThemeId, mActInfo.applicationInfo.targetSdkVersion);
//			Log.d(TAG, "resolved activity theme: " + mTargetThemeId);
//			mTargetContext.setTheme(mTargetThemeId);
//			mTargetContext.themeReady(mTargetThemeId);
			
			mTargetContext.resReady(mResourceMerger);
			mTargetContext.packageNameReady(s.applicationInfo.packageName);
			
			Application app = ((Host_Application)getApplication()).onPrepareApplictionStub(s.applicationInfo, mClassLoader, null);
			ReflectUtil.ActivityReflectUtil.setServiceApplication(this, app);
			
			mTargetService = (Target_Service) mClassLoader.loadClass(mServiceClassNmae).newInstance();
			if (!mCallOnCreate) {
//				ReflectUtil.ActivityReflectUtil.setApplication(this, app);
				ReflectUtil.ActivityReflectUtil.attachBaseContext(mTargetService, mTargetContext);
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
	
	@Override
	public Theme getTheme() {
		if (null != mTargetContext) {
			return mTargetContext.getTheme();
		} else {
			return super.getTheme();
		}
	}

	// for Window to get target's resource
	public Resources getResources() {
		if (null != mTargetContext ) {
			return mTargetContext.getResources();
		} else {
			return super.getResources();
		}
	}	
	
}
