package org.bbs.felix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bbs.felix.util.AndroidUtil;
import org.bbs.felix.util.ApkManifestParser;
import org.bbs.felix.util.ApkManifestParser.PackageInfoX;
import org.bbs.felix.util.OsgiUtil;
import org.bbs.osgi.activity.embed.EmbeddedActivityAgent;
import org.knopflerfish.framework.FrameworkFactoryImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 
 * @see <a href="http://felix.apache.org/site/apache-felix-framework-and-google-android.html">felist-on-android</a>
 * @see {felix source}/main/src/main/java/org/apache/felix/main/Main.java
 * @author bangbang.song@gmail.com
 *
 */
public class FelixWrapper{
	private static final String TAG = FelixWrapper.class.getSimpleName();

	private static final String TMP_DIR = "tmp";
	private static final String OSGI_BUNDLE_DIR = "osgi_bundle";
	private static final String OSGI_BUNDLE_CACHE_DIR = "osgi_bundlecache";

	private static final String ASSERT_PRELOAD_BUNDLE_DIR = "felix/preloadbundle";
	private static final String ASSERT_AUTO_EXTRACT_DIR = "autoExtract";
	
	private static final String BUNDLE_FILE_INSTALL_DIR = "fileInstall";
	
	private static FelixWrapper sInstance;
	private Framework mFramework;
	private String mTmpDir;
	private String mCacheDir;
	private String mBundleDir;
	
	private String mFileInstallDir;

	private Context mContext;

	private FelixWrapper(Context context){
		mContext = context;
		mTmpDir = context.getDir(TMP_DIR, Context.MODE_WORLD_WRITEABLE).toString();
		mCacheDir = context.getDir(OSGI_BUNDLE_CACHE_DIR, Context.MODE_WORLD_WRITEABLE).toString();
		mBundleDir = context.getDir(OSGI_BUNDLE_DIR, Context.MODE_WORLD_WRITEABLE).toString();
		
		mFileInstallDir = context.getDir(BUNDLE_FILE_INSTALL_DIR, Context.MODE_WORLD_WRITEABLE).toString();
		
		extractAssets(context);
		
		HashMap<String, String> configMap = new HashMap<String, String>();
		configMap.put(Constants.FRAMEWORK_STORAGE, mCacheDir);
		configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, ANDROID_PACKAGES_FOR_EXPORT_EXTRA);
		Log.d(TAG, "ANDROID_PACKAGE : " + ANDROID_PACKAGE);
		Log.d(TAG, "ANDROID_PACKAGE_V4 : " + ANDROID_PACKAGE_V4);
		Log.d(TAG, "APP_PACKAGE : " + APP_PACKAGE);
		Log.d(TAG, "THIRD_PACKAGE : " + THIRD_PACKAGE);
		Log.d(TAG, Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA + ": " + ANDROID_PACKAGES_FOR_EXPORT_EXTRA);
		
		// felix config
//		configMap.put(FelixConstants.LOG_LEVEL_PROP, 4 + "");
		System.setProperty("felix.fileinstall.dir", mFileInstallDir);
		System.setProperty("felix.fileinstall.log.level", 4 + "");
		
		// knoperfish config
//		configMap.put("org.knopflerfish.framework.debug.classloader", "true");
		
		// equix config
		
		mFramework = new FrameworkFactoryImpl().newFramework(configMap);
		
		Log.d(TAG, "init & start osgi." );
		try {
			mFramework.init();
			Bundle[] bundles = mFramework.getBundleContext().getBundles();
			
			// for re-deploy bundle.
			for (Bundle b : bundles) {
				if (0 != b.getBundleId()) {
					b.uninstall();
				}
			}
			
			mFramework.start();
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, "OSGi framework running, state: " + OsgiUtil.bundleState2Str(mFramework.getState()));

		registerListener(mFramework);
		
		extractPreloadBundle(context);
		installPreloadBundle(mFramework.getBundleContext(), mBundleDir);
		
		List<String> packageNames = new ArrayList<String>();
		packageNames.add("com.youku.tv");
//		packageNames.add("com.example.android.apis");
		installAppAsBundle(mFramework.getBundleContext(), packageNames);
		
		Bundle[] bundles = mFramework.getBundleContext().getBundles();
		for (Bundle b : bundles) {
			Log.d(TAG, "b: " + b.getBundleId() + " " + OsgiUtil.bundleState2Str(b.getState()));
		}
	}
	
	private void installAppAsBundle(BundleContext bundleContext, List<String> packageNames) {
		for (String p : packageNames) {
			String apk = AndroidUtil.getInstallApkPath(mContext, p);
			PackageInfo pInfo = mContext.getPackageManager().getPackageArchiveInfo(apk, PackageManager.GET_ACTIVITIES);
			Log.d(TAG,  "pInfo: " + pInfo);
			PackageInfoX info = ApkManifestParser.parseAPk(mContext, apk);
			Log.d(TAG, "mInfo: " + info);
			try {
				bundleContext.installBundle("file://" + apk).start();
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Framework getFramework(){
		return mFramework;
	}
	
	public static void registerListener(Framework f) {
		f.getBundleContext().addBundleListener(new BundleListener(){

			@Override
			public void bundleChanged(BundleEvent e) {
				Log.d(TAG, "bundleChanged. e:" + e);
				
			}});
	}
	
	private void extractAssets(Context context) {
		try {
			AssetManager assetsM = context.getResources().getAssets();
			String[] files = assetsM.list(ASSERT_AUTO_EXTRACT_DIR);
			for (String aFile: files) {
				String assertFile = ASSERT_AUTO_EXTRACT_DIR + "/" + aFile;
				Log.d(TAG, "prepare bundle: " + aFile);
				InputStream in = assetsM.open(assertFile);
				String bFile = "/sdcard/autoextract/" + aFile;
				File f = new File(bFile);
				f.getParentFile().mkdirs();
				OutputStream out = 
//						context.openFileOutput(mBundleDir + "/" + aFile, 0);
						
						new FileOutputStream(bFile);
				
				int byteCount = 8096;
				byte[] buffer = new byte[byteCount];
				int count = 0;
				while ((count = in.read(buffer, 0, byteCount)) != -1){
					out.write(buffer, 0, count);
				}
				
				in.close();
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	private void extractPreloadBundle(Context context) {
			try {
				AssetManager assetsM = context.getResources().getAssets();
				String[] files = assetsM.list(ASSERT_PRELOAD_BUNDLE_DIR);
				for (String aFile: files) {
					String assertFile = ASSERT_PRELOAD_BUNDLE_DIR + "/" + aFile;
					Log.d(TAG, "prepare bundle: " + aFile);
					InputStream in = assetsM.open(assertFile);
					String bFile = mBundleDir + "/" + aFile;
					if (aFile.endsWith("apk")) {
						bFile = bFile + ".jar";
					}
					OutputStream out = 
	//						context.openFileOutput(mBundleDir + "/" + aFile, 0);
							
							new FileOutputStream(bFile);
					
					int byteCount = 8096;
					byte[] buffer = new byte[byteCount];
					int count = 0;
					while ((count = in.read(buffer, 0, byteCount)) != -1){
						out.write(buffer, 0, count);
					}
					
					in.close();
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	private void installPreloadBundle(BundleContext bundleContext, String bundleDir) {
		String[] files = new File(bundleDir).list();
		for (String f : files ) {
			try {
				bundleContext.installBundle(new File("file://" + mBundleDir + "/" + f).getPath()).start();
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static FelixWrapper getInstance(Context context) {
		if (null == sInstance) {
			sInstance = new FelixWrapper(context);
		}
		return sInstance;
	}
	
	// copied from http://code.google.com/p/felix-on-android/
	private static String ANDROID_PACKAGES_FOR_EXPORT_EXTRA = null;
	private static String THIRD_PACKAGE  =
	        // this no ';' or ',' , shit.
	        "";
	
	private static String APP_PACKAGE = 
			"org.bbs.osgi.activity," +
			"org.bbs.osgi.activity.embed," +
			"org.bbs.felix.activity.ActivityAgent," +
			"org.bbs.felix.activity.bundlemanager," + 
			"org.bbs.felixonandroid,"
			;

	private static String ANDROID_PACKAGE_V4 = 
	        ""
			;
	
	//see http://developer.android.com/reference/android/package-summary.html
	// api level 21
	private static String ANDROID_PACKAGE = 
        "android, " + 
        "android.app," + 
        "android.animation," + 
        "android.content," + 
        "android.content.res," + 
        "android.content.pm," + 
        "android.database," + 
        "android.database.sqlite," + 
        "android.graphics, " + 
        "android.graphics.drawable, " + 
        "android.graphics.drawable.shapes," +
        "android.graphics.pdf," +
        "android.hardware, " + 
        "android.hardware.camera2, " + 
        "android.hardware.camera2.params, " + 
        "android.hardware.display, " + 
        "android.hardware.usb, " + 
        "android.inputmethodservice, " + 
        "android.location, " + 
        "android.media, " + 
        "android.media.audiofx, " + 
        "android.media.browse, " + 
        "android.media.effect, " + 
        "android.media.projection, " + 
        "android.media.session, " + 
        "android.media.tv, " + 
        "android.mtp, " + 
        "android.net, " + 
        "android.net.http, " + 
        "android.net.nsd, " + 
        "android.net.rtp, " + 
        "android.net.sip, " + 
        "android.net.wifi, " + 
        "android.net.wifi.p2p, " + 
        "android.net.wifi.psp.nsd, " + 
        "android.nfc, " + 
        "android.nfc.cardemulation, " + 
        "android.nfc.tech, " + 
        "android.opengl, " + 
        "android.os, " + 
        "android.os.storage, " + 
        "android.preference, " +
        "android.print, " +
        "android.print.pdf, " +
        "android.printservice, " +
        "android.provider, " + 
        "android.renderscript, " + 
        "android.sax, " + 
        "android.security, " + 
        "android.service.dreams, " + 
        "android.service.notification, " + 
        "android.service.restriction, " + 
        "android.service.textservice, " + 
        "android.service.voice, " + 
        "android.service.wallpaper, " + 
        "android.speech, " + 
        "android.speech.tss, " + 
        "android.system, " + 
        "android.tellecom, " + 
        "android.telephony, " + 
        "android.telephony.cdma, " + 
        "android.telephony.gsm, " + 
        "android.test, " + 
        "android.test.mock, " + 
        "android.test.suitebuilder, " + 
        "android.test.suitebuilder.annotation, " + 
        "android.text, " + 
        "android.text.format, " + 
        "android.text.method, " + 
        "android.text.style, " + 
        "android.text.util, " + 
        "android.transition, " + 
        "android.util, " + 
        
        "android.view, " + 
        "android.view.accessibility, " + 
        "android.view.animation, " + 
        "android.view.inputmethod, " + 
        "android.view.textservice, " + 
        "android.webkit, " + 
        "android.widget, " + 

        "com.android.internal.backup, " + 
        "com.android.internal.os, " + 
        "com.android.internal.statusbar, " + 
        "com.android.internal.widget, " + 
        "com.android.test.runner, " + 

        "dalvik.annotation, " +  
        "dalvik.bytecode, " +  
        "dalvik.system, " +  
        
        "javax.crypto, " + 
        "javax.crypto.interfaces, " + 
        "javax.crypto.spec, " + 
        "javax.microedition.khronos.egl, " + 
        "javax.microedition.khronos.opengles, " + 
        "javax.net, " + 
        "javax.net.ssl, " + 
        "javax.security.auth, " + 
        "javax.security.auth.callback, " + 
        "javax.security.auth.login, " + 
        "javax.security.auth.x500, " + 
        "javax.security.cert, " + 
        "javax.sql, " + 
        "javax.xml, " + 
        "javax.xml.datatype, " + 
        "javax.xml.namespace, " + 
        "javax.xml.parsers, " + 
        "javax.xml.transfrom, " + 
        "javax.xml.transfrom.dom, " + 
        "javax.xml.transfrom.sax, " + 
        "javax.xml.transfrom.stream, " + 
        "javax.xml.validation, " + 
        "javax.xml.xpath, " + 
        
        "junit.framework, " + 
        "junit.runner, " + 
        
	    "org.apache.http, " + 
	    "org.apache.http.auth, " + 
	    "org.apache.http.auth.params, " + 
	    "org.apache.http.client, " + 
	    "org.apache.http.client.entity, " + 
	    "org.apache.http.client.methods, " + 
	    "org.apache.http.client.params, " + 
	    "org.apache.http.client.protocol, " + 
	    "org.apache.http.client.utils, " + 
	    "org.apache.http.conn, " + 
	    "org.apache.http.conn.params, " + 
	    "org.apache.http.conn.routing, " + 
	    "org.apache.http.conn.scheme, " + 
	    "org.apache.http.conn.ssl, " + 
	    "org.apache.http.conn.util, " + 
	    "org.apache.http.cookie, " + 
	    "org.apache.http.cookie.params, " + 
	    "org.apache.http.entity, " + 
	    "org.apache.http.impl, " + 
	    "org.apache.http.impl.auth, " + 
	    "org.apache.http.impl.client, " + 
	    "org.apache.http.impl.conn, " + 
	    "org.apache.http.impl.conn.tsccm, " + 
	    "org.apache.http.impl.cookie, " + 
	    "org.apache.http.impl.entity, " + 
	    "org.apache.http.impl.io, " + 
	    "org.apache.http.io, " + 
	    "org.apache.http.message, " + 
	    "org.apache.http.params, " + 
	    "org.apache.http.protocol, " + 
	    "org.apache.http.util, " + 	  
	    
	    "org.json, " + 
	    
	    "org.w3c.dom, " + 
	    "org.w3c.dom.ls, " + 
	    
	    "org.xml.sax, " + 
	    "org.xml.sax.extv, " + 
	    "org.xml.sax.helpers, " + 
	    "org.xmlpull.v1, " + 
	    "org.xmlpull.v1.sax2, " + 
	    
	    
	    
	    ""
	;
	
	static {
		ANDROID_PACKAGES_FOR_EXPORT_EXTRA += "," + APP_PACKAGE;
		ANDROID_PACKAGES_FOR_EXPORT_EXTRA += ANDROID_PACKAGE;
//		ANDROID_PACKAGES_FOR_EXPORT_EXTRA += ANDROID_PACKAGE_V4;
		ANDROID_PACKAGES_FOR_EXPORT_EXTRA += THIRD_PACKAGE;

		ANDROID_PACKAGES_FOR_EXPORT_EXTRA = ANDROID_PACKAGES_FOR_EXPORT_EXTRA.replace("\\s*,\\s*,\\s*", "");
		ANDROID_PACKAGES_FOR_EXPORT_EXTRA = ANDROID_PACKAGES_FOR_EXPORT_EXTRA.replace("\\s*,\\s*$", "");
	}
}
