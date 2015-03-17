package org.bbs.apklauncher;
import java.io.File;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bbs.apklauncher.emb.IntentHelper;
import org.bbs.apklauncher.emb.LoadedApk;
import org.bbs.apklauncher.emb.auto_gen.Stub_Activity;
import org.bbs.apkparser.ApkManifestParser;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX;
import org.bbs.apkparser.ApkManifestParser.PackageInfoX.ActivityInfoX;
import org.bbs.felix.util.AndroidUtil;
import org.bbs.felixonandroid.R;

import dalvik.system.DexClassLoader;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BackUpActivity extends Activity {
	private static final String TAG = BackUpActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apk_launcher);

		ListAdapter adapter = new ArrayAdapter<ApplicationInfo>(this, android.R.layout.simple_list_item_1, (getPackageManager().getInstalledApplications(0))){
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				View v =  super.getView(position, convertView, parent);
				
				v.setTag(getItem(position));
				v.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						v.setTag(getItem(position));
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
				return v ;
			}
		};
		((ListView)findViewById(R.id.apk_container)).setAdapter(adapter);;
	}
	
}
