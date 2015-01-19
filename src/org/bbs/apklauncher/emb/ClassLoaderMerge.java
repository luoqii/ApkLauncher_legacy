package org.bbs.apklauncher.emb;

import android.util.Log;

public class ClassLoaderMerge extends ClassLoader {
	
	private static final String TAG = ClassLoaderMerge.class.getSimpleName();
	
	private ClassLoader mHost;
	private ClassLoader mTarget;

	public ClassLoaderMerge(ClassLoader targetClassLoader, ClassLoader hostClassLoader) {
		mTarget = targetClassLoader;
		mHost = hostClassLoader;
	}
	
	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		Class c =  null;;

		 //==========12345678901234567890
		 Log.d(TAG, "try to  load class: " + className);
		 
		if (className.startsWith("android")
				|| className.startsWith("org.bbs")
				) {
			c = mHost.loadClass(className);
		} else {
			c = mTarget.loadClass(className);
		}

		 //==========12345678901234567890
		 Log.d(TAG, "load class success: " + c + " classloader: " + c.getClassLoader());
		return c;
	}
	
}
