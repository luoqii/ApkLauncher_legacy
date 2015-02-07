package org.bbs.osgi.activity;

import org.bbs.apklauncher.emb.Host_Application;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.text.TextUtils;
import android.util.Log;

/**
 * when bundle resource is ready, return this, otherwise, return normally.
 * @author bysong
 *
 */
public class LazyContext extends 
ContextWrapper 
//ContextThemeWrapper
{

	private static final String TAG = LazyContext.class.getSimpleName();
	private static String mPackageName;
	private Resources mResource;
	private ClassLoader mClassLoader;
	private ClassLoader mMergedClassLoader;
	private PackageManager mPackageManager;
	private Context mAppContext;
	private Theme mTargetTheme;
	private int mTargetThemeId;
	private Activity mHostActivity;
	
	public LazyContext(Context base) {
		super(base);
//		this(base, 0);
	}

//	public LazyContext(Context base, int themeResId) {
//		super(base, themeResId);
//	}
	
	public static void bundleReady(LazyContext LazyContext, Bundle bundle, Resources res, String packageName) {
		// trivas build error
//		LazyContext.mClassLoader = bundle.adapt(BundleWiring.class).getClassLoader();
		LazyContext.mResource = res;
		mPackageName = packageName;
	}	
	
	public void packageManagerReady(PackageManager pm) {
		mPackageManager = pm;
	}
	
	public void applicationContextReady(Context appContext){
		mAppContext = appContext;
	}
	
	public void packageNameReady(String packageName) {
		mPackageName = packageName;
	}
	
	public void themeReady(int theme) {
		mTargetThemeId = theme;
	}
	
	public void resReady(Resources res) {
		mResource = res;
	}
	
	public void classLoaderReady(ClassLoader classloader) {
		mClassLoader = classloader;
	}
	
	public void hostActivityReady(Activity activity) {
		mHostActivity = activity;
	}
	
	public Activity getHostActivity() {
		return mHostActivity;
	}
	
	@Override
	public Theme getTheme() {
		if (null != mTargetTheme) {
			return mTargetTheme;
		}
		if (mResource != null) {
			if (mTargetThemeId > 0) {
				if (mTargetTheme == null) {
					mTargetTheme = mResource.newTheme();
				}
				mTargetTheme.applyStyle(mTargetThemeId, true);

				return mTargetTheme;
			}
		}
		
		return super.getTheme();
		
	}

	@Override
	public String getPackageName() {
		String pName = doGetPackageName();
//		Log.d(TAG, "getPackageName(). packageName: " + pName);
		
		return pName;
	}	
	
	public String doGetPackageName() {
//		new Exception("stack info").printStackTrace();
		if (!TextUtils.isEmpty(mPackageName)) {
			return mPackageName;
		}
		return super.getPackageName();
	}	
	
	@Override	
	public Resources getResources() {
		if (null == mResource) {
			return super.getResources();
		} else {
			return mResource;
		}
	}
	
	@Override
	public AssetManager getAssets() {
		if (null != mResource) {
			if (mResource instanceof ResourcesMerger) {
				Resources r = ((ResourcesMerger)mResource).mFirst;
				
				return getAsset(r);
			}
		}
		return super.getAssets();
	}
	
	private AssetManager getAsset(Resources r) {
		return (AssetManager) ReflectUtil.getFiledValue(Resources.class, r, "mAssets");
	}

	@Override
	public ClassLoader getClassLoader() {
		ClassLoader cl = super.getClassLoader();
		if (mClassLoader != null) {
			if (mMergedClassLoader == null) {
				mMergedClassLoader = new MergedClassLoader(cl, mClassLoader);
			}
			
			cl = mMergedClassLoader;
		}
		
		return cl;
	}
	
	@Override
	public PackageManager getPackageManager() {
		PackageManager pm =  doGetPackageManager();
//		Log.d(TAG, "pm: " + pm);
			
		return pm;
	}	
	
	public PackageManager doGetPackageManager() {
//		new Exception("stack info").printStackTrace();
		if (mPackageManager != null) {
			return mPackageManager;
		}

		return super.getPackageManager();
	}
	
	@Override
	public Context getApplicationContext() {
		if (mAppContext != null) {
			return mAppContext;
		}
		return super.getApplicationContext();
	}
	
	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {
		SharedPreferences pref =  super.getSharedPreferences(name, mode);
		
		Log.d(TAG, "SharedPreferences(). name: " + name + " pref: " + pref);
		return pref;
	}
	
	@Override
	public ComponentName startService(Intent service) {
		((Host_Application)getApplicationContext()).onProcessStartServiceIntent(service, mClassLoader, getBaseContext());
		return super.startService(service);
//		Log.w(TAG, "startService not implemented.");
//		return null;
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		((Host_Application)getApplicationContext()).onProcessStartServiceIntent(service, mClassLoader, getBaseContext());
		return super.bindService(service, conn, flags);
//		Log.w(TAG, "bindService not implemented.");
//		return false;
	}

	@Override
	public boolean stopService(Intent service) {
		((Host_Application)getApplicationContext()).onProcessStartServiceIntent(service, mClassLoader, getBaseContext());

		return super.stopService(service);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		// TODO Auto-generated method stub
		super.unbindService(conn);
	}

	class MergedAssetManager 
//	extends AssetManager 
	{
		
	}
	
	class MergedClassLoader extends ClassLoader {
		private ClassLoader mMinor;
		private ClassLoader mMajor;

		MergedClassLoader(ClassLoader major, ClassLoader minor) {
			mMajor = major;
			mMinor = minor;
		}
		
		@Override
		protected Class<?> loadClass(String className, boolean resolve)
				throws ClassNotFoundException {
			try {
				return mMajor.loadClass(className);
			} catch (Exception e) {
				return mMinor.loadClass(className);
			}
		}
		
	}
}