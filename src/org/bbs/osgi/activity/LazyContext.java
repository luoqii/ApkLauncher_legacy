package org.bbs.osgi.activity;

import java.nio.MappedByteBuffer;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.util.Log;

/**
 * when bundle resource is ready, return this, otherwise, return normally.
 * @author bysong
 *
 */
public class LazyContext extends ContextWrapper {

	private static final String TAG = LazyContext.class.getSimpleName();
	private Resources mResource;
	private ClassLoader mClassLoader;
	private ClassLoader mMergedClassLoader;
	private PackageManager mPackageManager;
	private Application mApp;
	private Theme mTargetTheme;
	private int mTargetThemeId;

	public LazyContext(Context base) {
		super(base);
	}
	
	public static void bundleReady(LazyContext LazyContext, Bundle bundle, Resources res) {
		LazyContext.mClassLoader = bundle.adapt(BundleWiring.class).getClassLoader();
		LazyContext.mResource = res;
	}	
	
	public void packageManagerReady(PackageManager pm) {
		mPackageManager = pm;
	}
	
	public void applicationReady(Application app){
		mApp = app;
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
		if (mPackageManager != null) {
//			return mPackageManager;
		}

		Log.d(TAG, "getPackageManager" + new Exception().fillInStackTrace());
		return super.getPackageManager();
	}
	
	@Override
	public Context getApplicationContext() {
		if (mApp != null) {
			return mApp;
		}
		return super.getApplicationContext();
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