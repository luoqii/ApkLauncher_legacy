package org.bbs.apklauncher.emb;

import android.util.Log;

public class ClassLoaderMerger extends ClassLoader {
	
	private static final String TAG = ClassLoaderMerger.class.getSimpleName();
	
	private ClassLoader mHost;
	private ClassLoader mTarget;

	private int mLevel;

	public ClassLoaderMerger(ClassLoader targetClassLoader, ClassLoader hostClassLoader) {
		mTarget = targetClassLoader;
		mHost = hostClassLoader;
	}
	
	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		Class c =  null;;

		 //==========12345678901234567890
		 Log.d(TAG, "#" + mLevel + " try to  load class: " + className);
		 mLevel++;
		 
		if (className.startsWith("android")
				|| className.startsWith("org.bbs")
				) {
			c = mHost.loadClass(className);
		} else {
			c = mTarget.loadClass(className);
		}

		 //==========12345678901234567890
		 Log.d(TAG, "#" + mLevel + " load class success: " + c + " classloader: " + c.getClassLoader());
		 mLevel--;
		return c;
	}
	
}
