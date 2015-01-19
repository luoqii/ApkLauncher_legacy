package org.bbs.osgi.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.bbs.felix.App;
import org.bbs.felix.FelixWrapper;
import org.bbs.osgi.activity.ReflectUtil.ActivityReflectUtil;
import org.bbs.osgi.activity.embed.EmbeddedApplictionAgent;
import org.bbs.osgi.activity.embed.EmbeddedBundleActivity;
import org.bbs.osgi.activity.embed.SimpleActivityAgent;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.wiring.BundleWiring;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * if android call us we call through to {@link #mActivityStub};
 * otherwise call super or do ourself.
 * 
 * <p>
 * when add new function, keep it in section, in order.
 * 
 * @author luoqii
 *
 * @see {@link ActivityAgent}
 */
public class BundleActivity extends 
AbsBundleActivity
implements InstrumentationWrapper.CallBack
{
	private static final String RES_PATH_APK_RES = "res.apk";

	private static final String TAG = BundleActivity.class.getSimpleName();
	
	/**
	 * type {@link String}
	 */
	public static final String EXTRA_SERVICE_NAME = ".extra_service_name";
	 /**
	  *  type {@link String}
	  */
	public static final String EXTRA_SERVICE_FILTER = ".extra_service_filter_name";
	public static final String DEFAULT_LAUNCHER_SERVICE_NAME = 
//			"org.bbs.bundlemgr.BundleList" 
//			"org.bbs.bundlemgr.SimpleBundleList"
//			"com.example.android.apis.ApiDemos"
			"com.youku.tv.osgi.Activator$EmbeddedApiDemos"
			
			;
	public static final String DEFAULT_LAUNCHER_SERVICE_FILTER = "";

	public static final String EXTRA_EMBEDED_ACTIVITY_CLASS_NAME = ".extra_embed_activity_class_name";
	public static final String EXTRA_EMBEDED_BUNDLE_ID = ".EXTRA_EMBEDED_BUNDLE_ID";
	public static final String EXTRA_INTENT_HAS_PROCESSED = ".EXTRA_INTENT_HAS_PROCESSED";

	private static final boolean FORCE_CLOSE = true;

	private static final boolean DEBUG_CREATE_VIEW = false;
	
	private String mServiceName;
	private String mServiceFilter;
	private Resources mSourceMerger;

	private LazyContext mLazyContext;
	
//	private List<String> mExtendWidgetReg;

	protected org.osgi.framework.Bundle mBundle;

	private static ClassLoader sBundleClassLoader;
	
	private static Map<WeakReference<org.osgi.framework.Bundle>, WeakReference<Resources>> sBundle2ResMap;
	private static Map<WeakReference<org.osgi.framework.Bundle>, WeakReference<Application>> sBundle2AppMap;
	private static Map<WeakReference<org.osgi.framework.Bundle>, WeakReference<ClassLoader>> sBundle2Classloader;
	
	static {
		sBundle2ResMap = new HashMap<WeakReference<org.osgi.framework.Bundle>, WeakReference<Resources>>();
		sBundle2AppMap = new HashMap<WeakReference<org.osgi.framework.Bundle>, WeakReference<Application>>();
		sBundle2Classloader = new HashMap<WeakReference<org.osgi.framework.Bundle>, WeakReference<ClassLoader>>();
	}

	// XXX why we need this???
//	protected List<String> getExtendWidgetPackageReg() {
//		List<String> w = new ArrayList<String>();
//		w.add("com.youku.lib.widget.*");
//		w.add("com.youku.tv.widget.*");
//		w.add("com.youku.tv.ui.*");
//		w.add("com.baseproject.volley.toolbox.*");
//		w.add("com.youku.lib.focuslayer.*");
//		return w;
//	}
	
    @Override 
    protected void attachBaseContext(Context newBase) {
    	mLazyContext = new LazyContext(newBase);
        super.attachBaseContext(mLazyContext);
    }
	
	@Override
	public void setTheme(int resid) {
		super.setTheme(resid);
	}
	
	@Override
	public Theme getTheme() {
		return super.getTheme();
	}

	public Resources getResources() {
		// this will call before onCreate().
//		initActivityAgent();
//		return super.getResources();
		return mLazyContext.getResources();
//		return mSourceMerger == null ? super.getResources() : mSourceMerger;
	}	

	// life-cycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// call this as early as possible.
		InstrumentationWrapper.injectInstrumentation(this, this);
		super.onCreate(savedInstanceState);
		
		if (null == mActivityStub)  {
			TextView t = new TextView(this);
			t.setText("no service avaiable: \n" + "serviceName: " + mServiceName
						+ " serviceFilter: " + mServiceFilter);
			setContentView(t);
			if (FORCE_CLOSE) {
				throw new IllegalArgumentException("no ActivityAgent avaiable.");
			}
		}
	}

	// private method.
	protected ActivityAgent onPrepareActivityStub() {
		ActivityAgent agent = null;
		Intent intent = getIntent();
		
		// FIXME get a correct classloader for extra.
		if (null != sBundleClassLoader) {
			intent.setExtrasClassLoader(sBundleClassLoader);
		}

		String activityClassName = intent.getStringExtra(EXTRA_EMBEDED_ACTIVITY_CLASS_NAME);
		long bundleId = intent.getLongExtra(EXTRA_EMBEDED_BUNDLE_ID, -1);
		Log.d(TAG, "embed activityName: " + activityClassName);
		Log.d(TAG, "embed bundle id: " + bundleId);

		BundleContext bundleContext = FelixWrapper.getInstance(null).getFramework().getBundleContext();
		mBundle = bundleContext.getBundle(bundleId);

		if (null != mBundle) {
			// FIXME update theme.
			WeakReference<Resources> r = sBundle2ResMap.get(mBundle);
			if (null != r) {
				mSourceMerger = r.get();
				LazyContext.bundleReady(mLazyContext, mBundle, mSourceMerger, null);
			} 
			if (null == mSourceMerger) {
				Resources bundleRes = getBundleResources(mBundle);
				if (bundleRes != null) {
					mSourceMerger = new ResourcesMerger(bundleRes, super.getResources());
					mLazyContext.bundleReady(mLazyContext,mBundle, mSourceMerger, null);

					sBundle2ResMap.put(new WeakReference(mBundle), new WeakReference(mSourceMerger));
				}
			}

			ClassLoader cl = mBundle.adapt(BundleWiring.class).getClassLoader();
			if (null != cl) {
				getIntent().setExtrasClassLoader(cl);
			}
			
			agent = new SimpleActivityAgent(mBundle, activityClassName);
		} else {

			ClassLoader cl = null;
			Reference<ClassLoader>  rc = sBundle2Classloader.get(new WeakReference(mBundle));
			if (null != rc) {
				cl = rc.get();
			}
			if (null != cl) {
				intent.setExtrasClassLoader(cl);;
			}

			mServiceName =  intent.getStringExtra(EXTRA_SERVICE_NAME);
			if (TextUtils.isEmpty(mServiceName)) {
				mServiceName = getDefaultLauncherServiceName();;
			}
			mServiceFilter =  intent.getStringExtra(EXTRA_SERVICE_FILTER);
			ServiceReference<?> s = null;
			if (TextUtils.isEmpty(mServiceFilter)) {
				s = bundleContext.getServiceReference(mServiceName);
			} else {
				try {
					s = bundleContext.getServiceReferences(mServiceName, mServiceFilter)[0];
				} catch (InvalidSyntaxException e) {
					e.printStackTrace();
				}
			}

			if (null != s) {
				mBundle = s.getBundle();
				if (null == cl) {
					cl = mBundle.adapt(BundleWiring.class).getClassLoader();
					sBundleClassLoader = cl;
					if (null != cl) {
						sBundle2Classloader.put(new WeakReference(mBundle), new WeakReference(cl));
					}
				}
				Log.d(TAG, "bundle classloader: " + cl);

				// FIXME update theme.
				WeakReference<Resources> r = sBundle2ResMap.get(mBundle);
				if (null != r) {
					mSourceMerger = r.get();
					mLazyContext.bundleReady(mLazyContext, mBundle, mSourceMerger, null);
				} 
				if (null == mSourceMerger) {
					Resources bundleRes = getBundleResources(s.getBundle());
					if (bundleRes != null) {
						mSourceMerger = new ResourcesMerger(bundleRes, super.getResources());
						mLazyContext.bundleReady(mLazyContext,mBundle, mSourceMerger, null);

						sBundle2ResMap.put(new WeakReference(mBundle), new WeakReference(mSourceMerger));
					}
				}

				agent = (ActivityAgent) bundleContext.getService(s);
			}
		}

		// update app map
		if (null != agent) {
			WeakReference<Application> r = sBundle2AppMap.get(mBundle);
			if (null == r) {
				Application app = agent.getBundleApplication();
				if (null != app) {
					((App)getApplication()).attachBundleAplication(new EmbeddedApplictionAgent(app), mBundle, mSourceMerger, mLazyContext);
					sBundle2AppMap.put(new WeakReference(mBundle), new WeakReference(app));
				}
			}
		}
		
//		agent.mHostActivity = this;
//		if (null != mSourceMerger) {
//			agent.onBundleResourceReady(mSourceMerger);
//		}

		return agent;
	}

	protected String getDefaultLauncherServiceName() {
		return DEFAULT_LAUNCHER_SERVICE_NAME;
	}

//	@Override
//	public View onCreateView(String name, Context context, AttributeSet attrs) {
//		// TODO Auto-generated method stub
//		
//		if (DEBUG_CREATE_VIEW) {
//			////////////1234567890123456789
//			Log.d(TAG, "onCreateView. name: " + name);
//		}
//		return super.onCreateView(name, context, attrs);
//	}
//
//	@SuppressLint("NewApi")
//	@Override
//	public View onCreateView(View parent, String name, Context context,
//			AttributeSet attrs) {
//		if (mExtendWidgetReg == null){
//			mExtendWidgetReg = getExtendWidgetPackageReg();
//		}
//		
//		if (DEBUG_CREATE_VIEW) {
//			////////////1234567890123456789
//			Log.d(TAG, "onCreateView. name: " + name + " parent: " + parent);
//		}
//		
//		if (null != mExtendWidgetReg) {
//			for (String r : mExtendWidgetReg) {
//				if (name.matches(r)){
//					try {
//						if (DEBUG_CREATE_VIEW) {
//							////////////1234567890123456789012345
//							Log.d(TAG, "try to load  class. name: " + name);
//						}
//						
//						Class clazz = mBundle.loadClass(name);
//						Constructor c = clazz.getDeclaredConstructor(new Class[]{Context.class, AttributeSet.class});
//						
//						if (DEBUG_CREATE_VIEW) {
//							////////////1234567890123456789012345
//							Log.d(TAG, "load class success. name: " + name);
//						}
//						
//						return (View) c.newInstance(new Object[]{context, attrs});
//					} catch (ClassNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (NoSuchMethodException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (InstantiationException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IllegalArgumentException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (InvocationTargetException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		return super.onCreateView(parent, name, context, attrs);
//	}

	private Resources getBundleResources(org.osgi.framework.Bundle bundle) {
			File resApk = getFileStreamPath("id" + bundle.getBundleId() + "_v" + bundle.getVersion());

			//debug
			resApk.delete();
			
			if (!resApk.exists()) {
				URL url = bundle.getResource(".");
				try {
					InputStream ins = url.openStream();
					OutputStream ous = new FileOutputStream(resApk);
					final int LEN = 8 * 1024;
					byte[] buff = new byte[LEN];
					int read = -1;
					while ((read = ins.read(buff)) != -1){
						ous.write(buff, 0, read);
	//					Log.d(TAG, "" + new String(buff, 0, read));
					}
					ins.close();
					ous.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return loadApkResource(resApk.getAbsolutePath());
		}

	public static  Resources loadApkResource(String apkFilePath) {
		AssetManager assets = null;
		try {
			assets = AssetManager.class.getConstructor(null).newInstance(null);
			Method method = assets.getClass().getMethod("addAssetPath", new Class[]{String.class});
			Object r = method.invoke(assets, apkFilePath);
			DisplayMetrics metrics = null;
			Configuration config = null;
			// TODO add confic & metrics
			Resources res = new Resources(assets, metrics, config);
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

	public void processIntent(Intent intent) {
		Log.d(TAG, "processIntent. intent: " + intent);
		if (!intent.getBooleanExtra(EXTRA_INTENT_HAS_PROCESSED, false)) {
			ComponentName com = intent.getComponent();
			if (null != com) {
				String c = com.getClassName();
				c = "com.youku.tv.osgi.Activator$Home";
				if (!TextUtils.isEmpty(c)) {
					intent.setComponent(new ComponentName(com.getPackageName(), EmbeddedBundleActivity.class.getCanonicalName()));
					intent.putExtra(EXTRA_SERVICE_NAME, c);
					intent.putExtra(EXTRA_INTENT_HAS_PROCESSED, true);
				}
			}
		}
	}
	
	@Override
	public ClassLoader getClassLoader() {
		if (null != mLazyContext){
			return mLazyContext.getClassLoader();
		}
		return super.getClassLoader();
	}
	
	@Override
	public void overridePendingTransition(int enterAnim, int exitAnim) {
		Log.w(TAG, "overridePendingTransition. ignore this call now.");
		// FIXME
//		super.overridePendingTransition(enterAnim, exitAnim);
	}
	
}
