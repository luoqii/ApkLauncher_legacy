package org.bbs.apklauncher.emb;

import android.util.Log;
import dalvik.system.DexClassLoader;

public class TargetClassLoader extends DexClassLoader {
	private static final String TAG = TargetClassLoader.class.getSimpleName();

	private static final boolean DEBUG = false;
	
	int mLevel = 0;
	public TargetClassLoader(String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
	}

	 @Override
	 protected Class<?> findClass(String className)
			 throws ClassNotFoundException {
		 //==========12345678901234567890
//		 Log.d(TAG, "#" + mLevel + " try to  find class: " + className);
		 mLevel++;
		 Class c = null;
		 c = super.findClass(className);
		 //==========12345678901234567890
//		 Log.d(TAG, "#" + mLevel + " find class success: " + c + " classloader: " + c.getClassLoader());
		 mLevel--;

		 return c;
	 }
	 
	 @Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		return super.loadClass(className);
	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve)
			throws ClassNotFoundException {
		 mLevel++;
		 //==========1234567890123456789012345678901234567890
		 if (DEBUG) {
			 Log.d(TAG,    "#  class: " + className);
		 }
		 Class c =  super.loadClass(className, resolve);
		 //==========1234567890123456789012345678901234567890
		 if (DEBUG) {
			 Log.d(TAG,    "#  class: " + c + " classloader: " + c.getClassLoader());
		 }
		 mLevel--;
		 
		 return c;
	}

	static  boolean shouldLoadByHost(String className) {
		if (className.startsWith("org.bbs.apklauncher")
				|| (className.startsWith("android") 
//						&& !className.startsWith("android.support")
						)) {
			return true;
		}
		return false;
	}
	
	public static class RestrictClassLoader extends ClassLoader {

		private static final String TAG = RestrictClassLoader.class.getSimpleName();
		int mLevel;
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
				 //========123456789012345678901234567890123
				 Log.d(TAG, "# load class by hostClassLoader class: " + className);
				 mLevel++;
				c = mHostClassLoader.loadClass(className);
				 //========123456789012345678901234567890123
				 Log.d(TAG, "# class  loaded  by  hostClassLoader   : " + c + " classloader: " + c.getClassLoader());
				 mLevel--;

				return c;
			}
			 //==========12345678901234567890
//			 Log.d(TAG, "#" + mLevel + " try to  find class: " + className);
			 mLevel++;
			c =  super.findClass(className);
			 //==========12345678901234567890
//			 Log.d(TAG, "#" + mLevel + " find class success: " + c + " classloader: " + c.getClassLoader());
			 mLevel--;
			return c;
		}

		 @Override
		public Class<?> loadClass(String className) throws ClassNotFoundException {
			return super.loadClass(className);
		}

		 @Override
		 protected Class<?> loadClass(String className, boolean resolve)
				 throws ClassNotFoundException {
			 //==========12345678901234567890
//			 Log.d(TAG, "#"  +   " try to  find class: " + className);
			 Class c =  super.loadClass(className, resolve);
			 //==========12345678901234567890
//			 Log.d(TAG, "#"  +  " find class success: " + c + " classloader: " + c.getClassLoader());
			 
			 return c;
		 }
		
	}

}
