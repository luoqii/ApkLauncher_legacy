package org.bbs.felix.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bbs.felix.util.PackageParser.ManifestInfoX.ActivityInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.ApplicationInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.IntentInfoX;
import org.bbs.felix.util.PackageParser.ManifestInfoX.UseSDkX;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class PackageParser {
	private static final String ATTR_THEME = "theme";

	private static final String ATTR_LABEL = "label";

	private static final String TAG = PackageParser.class.getSimpleName();

	private static final String TAG_CATEGORY = "category";
	private static final String TAG_INTENT_FILTER = "intent-filter";
	private static final String TAG_ACTION = "action";
	private static final String TAG_ACTIVITY = "activity";
	private static final String ATTR_VERSION_NAME = "versionName";
	private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
	private static final String ATTR_VERSION_CODE = "versionCode";
	private static final String TAG_APPLICATION = "application";
	private static final String ATTR_NAME = "name";
	private static final String ATTR_PACKAGE = "package";
	private static final String TAG_MANIFEST = "manifest";

	public static ManifestInfoX parseAPk(Context context, String apkFile) {
		ManifestInfoX info = new ManifestInfoX();
		info.mApkLocation = apkFile;

		DisplayMetrics metrics = new DisplayMetrics();
		// metrics.setToDefaults();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		Resources res = null;
		AssetManager assets;
		XmlResourceParser parser = null;
		try {
			// Class<?> apkParser =
			// Class.forName("android.content.pm.PackageParser");
			// String methodName = "parsePackage";
			// EmbeddedActivityAgent.ReflectUtil.dumpMethod(apkParser,
			// methodName);
			// Method m = apkParser.getDeclaredMethod(methodName, new
			// Class[]{File.class, int.class});
			// Object apkPackage = m.invoke(apkParser.newInstance(), new
			// Object[]{new File(apkFile), 0});
			// Log.d(TAG, "apkPackage: " + apkPackage);

			// System.setProperty("java.boot.class.path",
			// System.getProperty("java.boot.class.path") +
			// ":/system/framework/framework-res.apk");
			// Method m =
			// Class.forName("com.android.packageinstaller.PackageUtil").getDeclaredMethod("getPackageInfo",
			// new Class[]{String.class});
			// Method m =
			// Class.forName("com.android.internal.R.styleable").getDeclaredMethod("getPackageInfo",
			// new Class[]{String.class});
			// Object apkParser = m.invoke(null, new Object[]{apkFile});
			// Log.d(TAG, "apkParser: " + apkParser);
			assets = AssetManager.class.getConstructor(null).newInstance(null);
			Method method = assets.getClass().getMethod("addAssetPath",
					new Class[] { String.class });
			res = new Resources(assets, metrics, null);
			int cookie = (Integer) method.invoke(assets, apkFile);
			parser = assets
					.openXmlResourceParser(cookie, "AndroidManifest.xml");

//			 dumpParser(parser);

			parseApk(parser, info);
			doPostParseAPk(info);

			return info;
		} catch (InstantiationException e) {
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static void doPostParseAPk(ManifestInfoX info) {
		if (info.mUseSdk != null) {
			UseSDkX sdk = info.mUseSdk;
			if (sdk.mMaxSdkVersion > 0) {
				ApplicationInfoX app = info.mAppliction;
				app.targetSdkVersion = sdk.mMinSdkVersion;
			}
		}
	}

	private static void parseApk(XmlResourceParser parser, ManifestInfoX info) {
		int eventType;
		try {
			eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = parser.getName();
				if (eventType == XmlPullParser.START_DOCUMENT) {
				} else if (eventType == XmlPullParser.START_TAG) {
					if (TAG_MANIFEST.equals(tag)) {
						parserManifest(parser, info);
					} 
				} else if (eventType == XmlPullParser.END_TAG) {
				} else if (eventType == XmlPullParser.TEXT) {
				}

				final int attCount = parser.getAttributeCount();
				for (int i = 0; i < attCount; i++) {
					String attName = parser.getAttributeName(i);
					String attValue = parser.getAttributeValue(i);
				}

				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void parserManifest(XmlResourceParser parser,
			ManifestInfoX info) throws XmlPullParserException, IOException {
		// do NOT work ???
		// info.mPackage = parser.getAttributeValue("", ATTR_PACKAGE);
		// info.mVersionName = parser.getAttributeValue(ANDROID_NS,
		// ATTR_VERSION_NAME);
		// info.mVersionName = parser.getAttributeValue("android",
		// ATTR_VERSION_NAME);
		// info.mVersionCode = parser.getAttributeIntValue(ANDROID_NS,
		// ATTR_VERSION_CODE, -1);

		// parse attr
		final int attCount = parser.getAttributeCount();
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if (ATTR_PACKAGE.equals(attName)) {
				info.packageName = attValue;
			} else if (ATTR_VERSION_NAME.equals(attName)) {
				info.versionName = attName;
			} else if (ATTR_VERSION_CODE.equals(attName)) {
				info.versionCode = Integer.parseInt(attValue);
			}
		}

		// parse sub-element
		int type;
		int outerDepth = parser.getDepth();
		while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
			if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
				continue;
			}

			String tagName = parser.getName();
			if (TAG_APPLICATION.equals(tagName)) {
				parserApplication(parser, info);
			} else if ("use-sdk".equals(tagName)) {
				parserUseSdk(parser, info);
			}
		}

	}

	private static void parserUseSdk(XmlResourceParser parser,
			ManifestInfoX info) {// parse attr
		UseSDkX sdk = new UseSDkX();
		final int attCount = parser.getAttributeCount();
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if ("minSdkVersion".equals(attName)) {
				sdk.mMinSdkVersion = Integer.parseInt(attValue);
			} else if ("maxSdkVersion".equals(attName)) {
				sdk.mMaxSdkVersion = Integer.parseInt(attValue);
			} else if ("targetSdkVersion".equals(attName)) {
				sdk.mTargetSdkVersion = Integer.parseInt(attValue);
			} 
		}
		
		info.mUseSdk = sdk;
		
	}

	private static void parserApplication(XmlResourceParser parser,
			ManifestInfoX info) throws XmlPullParserException, IOException {
		info.mAppliction = new ApplicationInfoX();
		ApplicationInfoX app = info.mAppliction;
		// parse attr
		final int attCount = parser.getAttributeCount();
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if (ATTR_NAME.equals(attName)) {
				String cName = attValue;
				app.className = cName;
				if (!TextUtils.isEmpty(cName) && 
						!cName.contains(".")) {
					app.className = info.packageName + "." + cName;
				}
				if (!TextUtils.isEmpty(cName) && cName.startsWith(".")) {
					app.className = info.packageName + cName;
				}
			} else if (ATTR_LABEL.equals(attName)) {
				if (attValue.startsWith("@")) {
					app.labelRes = Integer.parseInt(attValue.substring(1));
				} else {
					app.nonLocalizedLabel = attValue;
				}
			} else if ("icon".equals(attName)) {
				if (attValue.startsWith("@")) {
					app.icon = Integer.parseInt(attValue.substring(1));
				}
			}
		}

		// parse sub-element
		int type;
		int outerDepth = parser.getDepth();
		while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
			if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
				continue;
			}

			String tagName = parser.getName();

			if (TAG_ACTIVITY.equals(tagName)) {
				parserActivity(parser, info);
			}
		}

	}

	private static void parserActivity(XmlResourceParser parser,
			ManifestInfoX info) throws XmlPullParserException, IOException {
		ActivityInfoX a = new ActivityInfoX();
		a.mApkPath = info.mApkLocation;
		a.mApplication = info.mAppliction;
		// parse attr
		final int attCount = parser.getAttributeCount();
		boolean hasLabel = false;
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if (ATTR_NAME.equals(attName)) {
				String cName = attValue;
				a.name = cName;
				if (!TextUtils.isEmpty(cName)) {
					if (cName.startsWith(".")) {
						a.name = info.packageName + cName;
					} else if (!cName.contains(".")) {
						a.name = info.packageName + "." + cName;
					}
				}
			} else if (ATTR_LABEL.equals(attName)) {
				if (attValue.startsWith("@")) {
					a.labelRes = Integer.parseInt(attValue.substring(1));
				} else {
					a.nonLocalizedLabel = attValue;
				}
				
				hasLabel = true;
			} else if (ATTR_THEME.equals(attName)) {
				a.theme = Integer.parseInt(attValue.substring(1));
			} 
		}
		if (!hasLabel) {
			a.labelRes = info.mAppliction.labelRes;
			a.nonLocalizedLabel = info.mAppliction.nonLocalizedLabel;
		}
		a.packageName = info.packageName;
		a.mPackageClassName = info.mAppliction != null ? info.mAppliction.className : "";

		// parse sub-element
		int type;
		int outerDepth = parser.getDepth();
		while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
			if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
				continue;
			}

			String tagName = parser.getName();

			if (TAG_INTENT_FILTER.equals(tagName)) {
				parserIntentFilter(parser, info, a);
			}
		}

		if (info.mAppliction.mActivities == null) {
			info.mAppliction.mActivities = new ActivityInfoX[1];

			info.mAppliction.mActivities[0] = a;
		} else {
			int len = info.mAppliction.mActivities.length;
			ActivityInfoX[] as = new ActivityInfoX[len + 1];
			System.arraycopy(info.mAppliction.mActivities, 0, as, 0, len);
			as[len] = a;

			info.mAppliction.mActivities = as;
		}
	}

	private static void parserIntentFilter(XmlResourceParser parser,
			ManifestInfoX info, ActivityInfoX a) throws XmlPullParserException,
			IOException {
		IntentInfoX i = new IntentInfoX();
		// parse attr

		// parse sub-element
		int type;
		int outerDepth = parser.getDepth();
		while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
			if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
				continue;
			}

			String tagName = parser.getName();

			if (TAG_ACTION.equals(tagName)) {
				parserAction(parser, info, i);
			} else if (TAG_CATEGORY.equals(tagName)) {
				parseCategory(parser, info, i);
			}
		}

		if (a.mIntents == null) {
			a.mIntents = new IntentInfoX[1];

			a.mIntents[0] = i;
		} else {
			int len = a.mIntents.length;
			IntentInfoX[] as = new IntentInfoX[len + 1];
			System.arraycopy(a.mIntents, 0, as, 0, len);
			as[len] = i;

			a.mIntents = as;
		}

	}

	private static void parseCategory(XmlResourceParser parser,
			ManifestInfoX info, IntentInfoX intentInfo) {
		// parse attr
		final int attCount = parser.getAttributeCount();
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if (ATTR_NAME.equals(attName)) {
				String category = attValue;
				intentInfo.addCategory(category);
			}
		}
	}

	private static void parserAction(XmlResourceParser parser,
			ManifestInfoX info, IntentInfoX intentInfo) {
		// parse attr
		final int attCount = parser.getAttributeCount();
		for (int i = 0; i < attCount; i++) {
			String attName = parser.getAttributeName(i);
			String attValue = parser.getAttributeValue(i);
			if (ATTR_NAME.equals(attName)) {
				String action = attValue;
				intentInfo.addAction(action);
			}
		}
	}

	private static void dumpParser(XmlResourceParser parser) {
		int depth = 0;
		int eventType;
		try {
			eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					Log.d(TAG, makePrefix(depth) + "Start document");
				} else if (eventType == XmlPullParser.START_TAG) {
					depth++;
					Log.d(TAG,
							makePrefix(depth) + "Start tag " + parser.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					Log.d(TAG,
							makePrefix(depth) + "End tag " + parser.getName());
					depth--;
				} else if (eventType == XmlPullParser.TEXT) {
					Log.d(TAG, makePrefix(depth) + "Text " + parser.getText());
				}
				final int attCount = parser.getAttributeCount();
				for (int i = 0; i < attCount; i++) {
					String attName = parser.getAttributeName(i);
					String attValue = parser.getAttributeValue(i);
					Log.d(TAG, makePrefix(depth + 1) + "" + i + " " + attName
							+ " : " + attValue);
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String makePrefix(int depth) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			b.append(" ");
		}
		return b.toString();
	}

	/**
	 * all member MUST has a 'm" prefix.
	 * 
	 * @author bysong
	 *
	 */
	public static class ManifestInfoX extends PackageInfo {
		public ApplicationInfoX mAppliction;
		public String mApkLocation;
		public UseSDkX mUseSdk;

		public static class ApplicationInfoX extends ApplicationInfo {
			public ActivityInfoX[] mActivities;
		}

		public static class ActivityInfoX extends ActivityInfo implements
				Parcelable {
			public ApplicationInfoX mApplication;
			public String mPackageClassName;
			public IntentInfoX[] mIntents;
			public String mApkPath;

			public int describeContents() {
				return 0;
			}

			public void writeToParcel(Parcel out, int flags) {
				super.writeToParcel(out, flags);
				out.writeParcelableArray(mIntents, flags);
			}

			public static final Parcelable.Creator<ActivityInfoX> CREATOR = new Parcelable.Creator<ActivityInfoX>() {
				public ActivityInfoX createFromParcel(Parcel in) {
					return new ActivityInfoX(in);
				}

				public ActivityInfoX[] newArray(int size) {
					return new ActivityInfoX[size];
				}
			};
			
			public ActivityInfoX(){
				
			}

			private ActivityInfoX(Parcel in) {
//				super(in);
//				mData = in.readInt();
			}

		}

		public static class IntentInfoX extends IntentFilter {
			public String[] mActions;
		}
		
		public static class UseSDkX {
			public int mMinSdkVersion;
			public int mTargetSdkVersion;
			public int mMaxSdkVersion;
		}
	}
}
