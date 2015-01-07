package org.bbs.apklauncher;

import dalvik.system.DexClassLoader;

public class AndroidResClassLoader extends DexClassLoader {

	public AndroidResClassLoader(String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
	}

}
