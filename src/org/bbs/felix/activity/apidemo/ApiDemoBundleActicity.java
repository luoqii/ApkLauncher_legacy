package org.bbs.felix.activity.apidemo;

import org.bbs.osgi.activity.embed.EmbeddedBundleActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * embed android api demo app.
 * 
 * @author bysong
 *
 */
public class ApiDemoBundleActicity extends EmbeddedBundleActivity {
	private static final String LAUNCHER_ACTIVITY_NAME = "com.example.android.apis.Activator$EmbeddedApiDemos";
	private static final String TAG = ApiDemoBundleActicity.class.getSimpleName();

	protected String getDefaultLauncherServiceName() {
		return LAUNCHER_ACTIVITY_NAME;
	}

	@Override
	public void processIntent(Intent intent) {
		Log.d(TAG, "processIntent. intent: " + intent);
		if (!intent.getBooleanExtra(EXTRA_INTENT_HAS_PROCESSED, false)) {
			ComponentName com = intent.getComponent();
			if (null != com) {
				String c = com.getClassName();
				intent.putExtra(EXTRA_EMBEDED_ACTIVITY_CLASS_NAME, c);
				c = LAUNCHER_ACTIVITY_NAME;
				if (!TextUtils.isEmpty(c)) {
					intent.setComponent(new ComponentName(getPackageName(), ApiDemoBundleActicity.class.getCanonicalName()));
					intent.putExtra(EXTRA_SERVICE_NAME, c);
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
