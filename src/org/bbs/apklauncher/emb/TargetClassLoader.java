package org.bbs.apklauncher.emb;

import android.util.Log;
import dalvik.system.DexClassLoader;

public class TargetClassLoader extends DexClassLoader {

	private static final String TAG = TargetClassLoader.class.getSimpleName();
	
	int mLevel = 0;
	public TargetClassLoader(String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
	}

	 @Override
	 protected Class<?> findClass(String className)
			 throws ClassNotFoundException {
		 //==========12345678901234567890
		 Log.d(TAG, "#" + mLevel + " try to  load class: " + className);
		 mLevel++;
		 Class c = null;
		 c = super.findClass(className);
		 //==========12345678901234567890
		 Log.d(TAG, "#" + mLevel + " load class success: " + c + " classloader: " + c.getClassLoader());
		 mLevel--;

		 return c;
	 }
	 
	 @Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		return super.loadClass(className);
	}

	static  boolean shouldLoadByHost(String className) {
		if (className.startsWith("org.bbs.apklauncher.emb.auto_gen.")
				|| (className.startsWith("android") 
//						&& !className.startsWith("android.support")
						)) {
			return true;
		}
		return false;
	}
	
	public static class RestrictClassLoader extends ClassLoader {

		private ClassLoader mHostClassLoader;

		public RestrictClassLoader() {
			super();
		}
		
		public void setHostClassLoader(ClassLoader classLoader) {
			mHostClassLoader = classLoader;
		}

		@Override
		protected Class<?> findClass(String className)
				throws ClassNotFoundException {
			Class c = null;
			if (shouldLoadByHost(className)) {
//				Log.d(TAG, "try to  + class: " + className + " by classLoader: " + mHostClassLoader);
				c = mHostClassLoader.loadClass(className);
//				Log.d(TAG, "class   loaded   by  classLoader: " + mHostClassLoader);
				return c;
			}
//			Log.d(TAG, "try to clsss: " + className + " by classLoader: " + this);
			c =  super.findClass(className);
//			Log.d(TAG, "class   loaded   by  classLoader: " + mHostClassLoader);
			return c;
		}
		
		
	}

}
