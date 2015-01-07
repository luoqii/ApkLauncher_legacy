package org.bbs.osgi.activity.embed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.bbs.felix.FelixWrapper;
import org.bbs.felix.activity.apidemo.ApiDemoBundleActicity;
import org.bbs.osgi.activity.BundleActivity;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 *  all method will call through {@link #mTargetActivity}, so, we can
 *  "embed' an exist activity to {@link BundleActivity}.
 * @author luoqii
 *
 */
public class EmbeddedBundleActivity extends BundleActivity
{
	private static final String TAG = EmbeddedBundleActivity.class.getSimpleName();
	
	private int mThemeResource = android.R.style.Theme_Black;
	private Theme mTheme;

	protected PackageManager mPackageManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mPackageManager = getPackageManager();
	}

	@Override
	public void setTheme(int resid) {
		super.setTheme(resid);
		
//		mThemeResource = resid;
	}

	@Override
	public Theme getTheme() {
//		return super.getTheme();
		boolean first = mTheme == null;
		if (first) {
//            mThemeResource = Resources.selectDefaultTheme(mThemeResource,
//                    getApplicationInfo().targetSdkVersion);
			mTheme = getResources().newTheme();
			// TODO how to get an un-installed apk's theme.
			mTheme.applyStyle(mThemeResource, true);
		}
		
		return mTheme;
	}
	
	@Override
	public void processIntent(Intent intent) {
		Log.d(TAG, "processIntent. intent: " + intent);
		if (!intent.getBooleanExtra(EXTRA_INTENT_HAS_PROCESSED, false)) {
			ComponentName com = intent.getComponent();
			if (null != com) {
				String c = com.getClassName();
				intent.putExtra(EXTRA_EMBEDED_ACTIVITY_CLASS_NAME, c);
				if (!TextUtils.isEmpty(c)) {
					intent.setComponent(new ComponentName(getPackageName(), EmbeddedBundleActivity.class.getCanonicalName()));
					intent.putExtra(EXTRA_INTENT_HAS_PROCESSED, true);
					intent.putExtra(EXTRA_EMBEDED_BUNDLE_ID, mBundle != null ? mBundle.getBundleId() : -1);
				} 
			} else {
				ResolveInfo a = mPackageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
				Log.d(TAG, "ResolveInfo a: " + a);
				//				a.activityInfo.
			}
		}

		super.processIntent(intent);
	}
}
