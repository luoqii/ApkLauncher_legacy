package org.bbs.apklauncher;
import java.io.File;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.ManifestInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;
import org.bbs.felixonandroid.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ApkLuncherActivity extends Activity {
	private static final String TAG = ApkLuncherActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apk_launcher);
		
		
		RecyclerView recycleView = (RecyclerView) findViewById(R.id.apk_container);
		LayoutManager layoutM = new LinearLayoutManager(this);
		recycleView.setLayoutManager(layoutM);
		InstalledAPks apks = InstalledAPks.getInstance();
		Adapter adapter = new ApkAdapter(this, parseLauncher(apks.getAllApks()));
		recycleView.setAdapter(adapter);
		;
	}
	
	

	private List<ManifestInfoX.ActivityInfoX> parseLauncher(List<ManifestInfoX> ms) {
		List<ManifestInfoX.ActivityInfoX> launchers = new ArrayList<PackageParser.ManifestInfoX.ActivityInfoX>();
		for (ManifestInfoX m : ms) {
			if (m.mAppliction.mActivities != null) {
				for (ManifestInfoX.ActivityInfoX a : m.mAppliction.mActivities) {
					if (a.mIntents != null) {
						for (ManifestInfoX.IntentInfoX i : a.mIntents) {
							if (i.hasAction(Intent.ACTION_MAIN) && i.hasCategory(Intent.CATEGORY_LAUNCHER)) {
								launchers.add(a);
								break;
							}
						}
					}
				}
			}
		}
		return launchers;
	}
	
	class VH extends RecyclerView.ViewHolder {
		public ImageView icon;
		public TextView title;
		public VH(View itemView) {
			super(itemView);
			
		}
		
	}
	class ApkAdapter extends RecyclerView.Adapter<VH> {

		private List<ManifestInfoX.ActivityInfoX> mApks;

		public ApkAdapter(ApkLuncherActivity apkLuncherActivity,
				List<ManifestInfoX.ActivityInfoX> scanApks) {
			mApks = scanApks;
		}

		@Override
		public int getItemCount() {
			return mApks.size();
		}

		@Override
		public void onBindViewHolder(VH arg0, int arg1) {
			ManifestInfoX.ActivityInfoX a = mApks.get(arg1);
			
			arg0.title.setText(a.name);
			
			arg0.itemView.setTag(a);
		}

		@Override
		public VH onCreateViewHolder(ViewGroup arg0, int arg1) {
			VH vh = new VH(View.inflate(getApplicationContext(), R.layout.apk_launcher_item, null));
			vh.icon = (ImageView) vh.itemView.findViewById(R.id.icon);
			vh.title = (TextView) vh.itemView.findViewById(R.id.title);
			vh.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ManifestInfoX.ActivityInfoX a = (ActivityInfoX) v.getTag();
					
					Log.d(TAG, "onClick. activity: " + a);
					
					Intent launcher = new Intent(ApkLuncherActivity.this, StubActivity.class);
					
					putExtra(a, launcher);
					
					startActivity(launcher);
				}
			});
			return vh;
		}
		
	}

	public static  void putExtra(ManifestInfoX.ActivityInfoX a,
			Intent launcher) {
//		launcher.putExtra(StubActivity.EXTRA_APK_PATH, a.mApkPath);
//		launcher.putExtra(StubActivity.EXTRA_APPLICATION_CLASS_NAME, a.mPackageClassName);
		launcher.putExtra(StubActivity.EXTRA_ACTIVITY_CLASS_NAME, a.name);
//		launcher.putExtra(StubActivity.EXTRA_ACTIVITY_THEME, a.theme);
	}
	
}
