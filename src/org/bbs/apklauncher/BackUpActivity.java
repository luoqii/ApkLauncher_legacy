package org.bbs.apklauncher;
import java.io.File;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bbs.felix.util.AndroidUtil;
import org.bbs.felix.util.PackageParser;
import org.bbs.felix.util.PackageParser.PackageInfoX;
import org.bbs.felix.util.PackageParser.PackageInfoX.ActivityInfoX;
import org.bbs.felixonandroid.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
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
import android.widget.Toast;

public class BackUpActivity extends Activity {
	private static final String TAG = BackUpActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apk_launcher);
		
		
		RecyclerView recycleView = (RecyclerView) findViewById(R.id.apk_container);
		LayoutManager layoutM = new LinearLayoutManager(this);
		recycleView.setLayoutManager(layoutM);
		Adapter adapter = new ApkAdapter(this, getPackageManager().getInstalledApplications(0));
		recycleView.setAdapter(adapter);
		;
	}
	
	class VH extends RecyclerView.ViewHolder {
		public ImageView icon;
		public TextView title;
		public VH(View itemView) {
			super(itemView);
			
		}
		
	}
	class ApkAdapter extends RecyclerView.Adapter<VH> {

		private List<ApplicationInfo> mApks;

		public ApkAdapter(BackUpActivity apkLuncherActivity,
				List<ApplicationInfo> list) {
			mApks = list;
		}

		@Override
		public int getItemCount() {
			return mApks.size();
		}

		@Override
		public void onBindViewHolder(VH arg0, int arg1) {
			ApplicationInfo a = mApks.get(arg1);
			
			arg0.title.setText(a.packageName);
			
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
					ApplicationInfo a = (ApplicationInfo) v.getTag();
					
					Log.d(TAG, "onClick. activity: " + a);
					File apkFile = new File(a.sourceDir);
					File DIR = new File("/sdcard/apk");
					DIR.mkdirs();
					File backupFile = new File(DIR, a.packageName + ".apk");
					AndroidUtil.copyFile(apkFile, backupFile);
					Toast.makeText(BackUpActivity.this, "scr:" + apkFile + " backFile: " + backupFile, Toast.LENGTH_LONG).show();;
					
				}
			});
			return vh;
		}
		
	}
	
}
