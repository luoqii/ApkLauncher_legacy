package org.bbs.apklauncher.emb;

import java.io.File;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bbs.apklauncher.InstalledAPks;
import org.bbs.apklauncher.emb.auto_gen.Stub_Activity;
import org.bbs.apkparser.PackageInfoX.ActivityInfoX;
import org.bbs.apkparser.PackageInfoX.ServiceInfoX;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class Util {
	private static final String TAG = Util.class.getSimpleName();;

    public static final String ACTIVITY_EXTRA_COMPONENT_CLASS_NAME = "EXTRA_COMPONENT_CLASS_NAME";
	
	// TODO
	public static final String toMemoryLevel(int level) {
		String levelStr = "";
		levelStr = level + "";
		
		return levelStr;
	}

	public static void onProcessStartActivityIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = "";
//				superClassName = LoadedApk.getActivitySuperClassName(classLoader, c);
				com = new ComponentName(realContext.getPackageName(), Stub_Activity.class.getName());
                // inject and replace with our component.
				intent.setComponent(com);
				ActivityInfoX a = InstalledAPks.getInstance().getActivityInfo(c);
				if (a != null) {
//					ApkLuncherActivity.putExtra(a, intent);
					intent.putExtra(Stub_Activity.EXTRA_COMPONENT_CLASS_NAME, a.name);
				}
			} 
		} else {
			Log.w(TAG, "can not handle intent:  "  + intent);
		}
	}

	public static void onProcessStartServiceIntent(Intent intent, ClassLoader classLoader, Context realContext) {
		Log.d(TAG, "processIntent. intent: " + intent);
		ComponentName com = intent.getComponent();
		if (null != com) {
			String c = com.getClassName();
			if (!TextUtils.isEmpty(c)) {
				String superClassName = "";
//				String superClassName = LoadedApk.getServiceSuperClassName(classLoader, c);
				com = new ComponentName(realContext.getPackageName(), Stub_Service.class.getName());
                // inject and replace with our component.
				intent.setComponent(com);
				ServiceInfoX a = InstalledAPks.getInstance().getServiceInfo(c);
				if (a != null) {
//					intent.putExtra(Stub_Service.EXTRA_COMPONENT, new ComponentName(a.packageName, a.name));
					intent.putExtra(Stub_Activity.EXTRA_COMPONENT_CLASS_NAME, a.name);
				}
			} 
		} else {
			Log.w(TAG, "can not handle intent:  "  + intent);
		}
	}
	
	public static Object getTargetActivity(Context context) {
		Object o = null;
		try {
			Method m = context.getClass().getMethod("getTargetActivity", (Class[])null);
			o = m.invoke(context, (Object[]) null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
	@SuppressLint("NewApi")
	public static Object getTargetActivityFormFrag(android.app.Fragment f) {
		Object o = null;
		Activity a = f.getActivity();
		try {
			Method m = a.getClass().getMethod("getTargetActivity", (Class[])null);
			o = m.invoke(a, (Object[]) null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
//	public static Object getTargetActivityFormFrag_V4(android.support.v4.app.Fragment f) {
//		Object o = null;
//		Activity a = f.getActivity();
//		try {
//			Method m = a.getClass().getMethod("getTargetActivity", (Class[])null);
//			o = m.invoke(a, (Object[]) null);
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return o;
//	}
	
	public static List<String> gatherSuperClassName(Object object) {
		List<String> list = new ArrayList<>();
		Class clazz = object.getClass();
		while (null != clazz) {
			list.add(clazz.getName());
			clazz = clazz.getSuperclass();
		}
		return list;
	}
	
	public static boolean isFragmentActivityV4(Activity activity){
		return gatherSuperClassName(activity).contains("android.support.v4.app.FragmentActivity");
	}

	public static  Resources loadApkResource(String apkFilePath, Context context) {
		AssetManager assets = null;
		try {
			assets = AssetManager.class.getConstructor(null).newInstance(null);
			Method method = assets.getClass().getMethod("addAssetPath", new Class[]{String.class});
			Object r = method.invoke(assets, apkFilePath);
			DisplayMetrics metrics = new DisplayMetrics();
			((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
			// TODO add config & metrics
			Configuration config = context.getResources().getConfiguration();
			Resources res = new Resources(assets, metrics, config);
			res.updateConfiguration(config, metrics);
			return res;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void attachExceptionHandler(final Context context) {
		final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread
				.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@SuppressLint("WorldReadableFiles")
			@Override
			public void uncaughtException(Thread thread, Throwable ex) {
				PrintStream writer;
				File crashFile = null;
				String name = "00_" + context.getPackageName() + "_crash.log.txt";
				try {
					crashFile = context.getFileStreamPath(name);
					crashFile.delete();
					context.openFileOutput(name, Context.MODE_WORLD_READABLE);
					crashFile = context.getFileStreamPath(name);
					crashFile.createNewFile();

					writer = new PrintStream(crashFile);
					writer.append("crash at: " + new Date().toString());
					writer.append("\n");
					writer.flush();

					ex.printStackTrace(writer);
					ex.printStackTrace();
					writer.flush();
					writer.close();

					Intent view = new Intent(Intent.ACTION_VIEW);
					view.setDataAndType(Uri.fromFile(crashFile), "text/*");
					view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					view.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
					view.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(view);
				} catch (Exception e) {
					e.printStackTrace();
				}

				defaultUncaughtExceptionHandler.uncaughtException(thread, ex);
			}
		});

		if (false) {
			npe();
		}
	}

	static void npe() {
		String nullStr = null;
		if (nullStr.length() > 0) {
		}
	}
	
	public static  void dumpStackTrace() {
		new Exception("stack info").printStackTrace();
	}	
}
