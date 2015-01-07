package org.bbs.osgi.activity;

import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * when bundle resource is ready, return this, otherwise, return normally.
 * @author bysong
 *
 */
public class LazyContext extends ContextWrapper {

	private Resources mResource;
	private ClassLoader mClassLoader;
	private ClassLoader mMergedClassLoader;

	public LazyContext(Context base) {
		super(base);
	}
	
	public static void bundleReady(LazyContext LazyContext, Bundle bundle, Resources res) {
		LazyContext.mClassLoader = bundle.adapt(BundleWiring.class).getClassLoader();
		LazyContext.mResource = res;
	}	
	
	public void resReady(Resources res) {
		mResource = res;
	}
	
	public void classLoaderReady(ClassLoader classloader) {
		mClassLoader = classloader;
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
		return (AssetManager) ActivityReflectUtil.getFiledValue(Resources.class, r, "mAssets");
//		try {
//			java.lang.reflect.Field f = Resources.class.getDeclaredField("mAssets");
//			f.setAccessible(true);
//			return (AssetManager) f.get(r);
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
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