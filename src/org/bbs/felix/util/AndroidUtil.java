package org.bbs.felix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class AndroidUtil {
	public static String getInstallApkPath(Context context, String packageName) {
		String path = "";
		try {
			ApplicationInfo pInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
			path = pInfo.sourceDir;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	public static void copyFile(File src, File dest) {
		if (null == src || null == dest) {
			return;
		}
		
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dest);
			int byteCount = 8096;
			byte[] buffer = new byte[byteCount];
			int count = 0;
			while ((count = in.read(buffer, 0, byteCount)) != -1){
				out.write(buffer, 0, count);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
