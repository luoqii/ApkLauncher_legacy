package org.bbs.apklauncher.emb;

import android.util.Log;

public class LogClassLoader extends ClassLoader {

	private static final String TAG = LogClassLoader.class.getSimpleName();

	public LogClassLoader(ClassLoader classLoader) {
		super(classLoader);
	}

	@Override
	protected Class<?> findClass(String className)
			throws ClassNotFoundException {
		Class c = super.findClass(className);
		Log.d(TAG, "load class: " + className);
		return c;
	}

}
