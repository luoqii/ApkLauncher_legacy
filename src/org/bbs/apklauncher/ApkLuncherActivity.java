package org.bbs.apklauncher;
import java.util.ArrayList;
import java.util.List;

import org.bbs.apklauncher.emb.auto_gen.Stub_Activity;
import org.bbs.apkparser.ApkManifestParser;
import org.bbs.apkparser.PackageInfoX;
import org.bbs.apkparser.PackageInfoX.ActivityInfoX;
import org.bbs.felixonandroid.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import dalvik.system.DexClassLoader;

public class ApkLuncherActivity extends Activity {
	private static final String TAG = ApkLuncherActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apk_launcher);
		
		InstalledAPks apks = InstalledAPks.getInstance();
		ListAdapter adapter = new ArrayAdapter<org.bbs.apkparser.PackageInfoX.ActivityInfoX>(this, android.R.layout.simple_list_item_1, parseLauncher(apks.getAllApks())){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v =  super.getView(position, convertView, parent);
				
				v.setTag(getItem(position));
				v.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						PackageInfoX.ActivityInfoX a = (ActivityInfoX) v.getTag();
						
						Log.d(TAG, "onClick. activity: " + a);

						ClassLoader cl = new DexClassLoader(a.applicationInfo.publicSourceDir, getDir("tmp", 0).getPath(), null, getClassLoader());
						String superClassName = "";
//						superClassName = LoadedApk.getActivitySuperClassName(cl, a.name);
						Intent launcher = new Intent();

		                // inject and replace with our component.
						String comClassName = superClassName.replace("Target", "Stub");
						ComponentName com= new ComponentName(getPackageName(), Stub_Activity.class.getName());
						launcher.setComponent(com);
						launcher.putExtra(Stub_Activity.EXTRA_COMPONENT_CLASS_NAME, a.name);
//						putExtra(a, launcher);
						
//						launcher.putExtra(IntentHelper.EXTRA_INJECT, false);
						startActivity(launcher);
					}
				});
				return v ;
			}
		};
		((ListView)findViewById(R.id.apk_container)).setAdapter(adapter);;
	}	

	private List<PackageInfoX.ActivityInfoX> parseLauncher(List<PackageInfoX> ms) {
		List<PackageInfoX.ActivityInfoX> launchers = new ArrayList<PackageInfoX.ActivityInfoX>();
		for (PackageInfoX m : ms) {
			
			if (m.activities != null) {
				for (ActivityInfo a : m.activities) {
					PackageInfoX.ActivityInfoX aX = (ActivityInfoX) a;
					if (aX.mIntents != null) {
						for (PackageInfoX.IntentInfoX i : aX.mIntents) {
							if (i.hasAction(Intent.ACTION_MAIN) && i.hasCategory(Intent.CATEGORY_LAUNCHER)) {
								launchers.add(aX);
								break;
							}
						}
					}
				}
			}
		}
		return launchers;
	}

	public static  void putExtra(PackageInfoX.ActivityInfoX a,
			Intent launcher) {
		launcher.putExtra(Stub_Activity.EXTRA_COMPONENT_CLASS_NAME, new ComponentName(a.packageName, a.name));
	}
	
}
