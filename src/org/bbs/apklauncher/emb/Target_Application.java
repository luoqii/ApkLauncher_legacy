package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.ApkLuncherActivity;
import org.bbs.apklauncher.InstalledAPks;
import org.bbs.felix.util.ApkManifestParser.PackageInfoX.ActivityInfoX;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application.OnProvideAssistDataListener;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.text.TextUtils;
import android.util.Log;

@SuppressLint("NewApi")
public class Target_Application extends Application
{
	private static final String TAG = Target_Application.class.getSimpleName();
	
	@Override
	public Theme getTheme() {
		return getBaseContext().getTheme();
	}

	public Resources getResources() {
		return getBaseContext().getResources();
	}	
}
