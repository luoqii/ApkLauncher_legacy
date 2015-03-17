package org.bbs.apklauncher;

import java.util.List;

import org.bbs.apklauncher.emb.Util;
import org.bbs.apkparser.PackageInfoX;
import org.bbs.apkparser.PackageInfoX.ActivityInfoX;
import org.bbs.apkparser.PackageInfoX.ApplicationInfoX;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;

public class PackageManagerProxy extends PackageManager {
	
	private static final String TAG = PackageManagerProxy.class.getSimpleName();
	private static final boolean LOG = true;
	private PackageManager mProxy;

	public PackageManagerProxy(PackageManager policy) {
		mProxy = policy;
	}

	@Override
	public PackageInfo getPackageInfo(String packageName, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getPackageInfo(). packageName: " + packageName + " flags: " + flags + toGetFlagString(flags));
		}		
		if (null == packageName) {
			return null;
		}
		InstalledAPks apks = InstalledAPks.getInstance();
		if (apks.hasApplicationInfo(packageName)) {
			PackageInfoX pInfo = apks.getPackageInfo(packageName);

			if (LOG) {
				Log.d(TAG, "use parsed pInfo: " + pInfo);
			}
			return pInfo;
		}
		
		return mProxy.getPackageInfo(packageName, flags);
	}

	private String toGetFlagString(int flags) {
		String str = "";
		if ((flags & GET_ACTIVITIES) == GET_ACTIVITIES) {
			str += " GET_ACTIVITIES";
		}
		if ((flags & GET_CONFIGURATIONS) == GET_CONFIGURATIONS) {
			str += " GET_CONFIGURATIONS";
		}
		if ((flags & GET_DISABLED_COMPONENTS) == GET_DISABLED_COMPONENTS) {
			str += " GET_ACTIVITIES";
		}
		if ((flags & GET_DISABLED_UNTIL_USED_COMPONENTS) == GET_DISABLED_UNTIL_USED_COMPONENTS) {
			str += " GET_DISABLED_UNTIL_USED_COMPONENTS";
		}
		if ((flags & GET_GIDS) == GET_GIDS) {
			str += " GET_GIDS";
		}
		if ((flags & GET_INSTRUMENTATION) == GET_INSTRUMENTATION) {
			str += " GET_INSTRUMENTATION";
		}
		if ((flags & GET_INTENT_FILTERS) == GET_INTENT_FILTERS) {
			str += " GET_INTENT_FILTERS";
		}
		if ((flags & GET_META_DATA) == GET_META_DATA) {
			str += " GET_META_DATA";
		}
		if ((flags & GET_PERMISSIONS) == GET_PERMISSIONS) {
			str += " GET_PERMISSIONS";
		}
		if ((flags & GET_PROVIDERS) == GET_PROVIDERS) {
			str += " GET_PROVIDERS";
		}
		if ((flags & GET_RECEIVERS) == GET_RECEIVERS) {
			str += " GET_RECEIVERS";
		}
		if ((flags & GET_SERVICES) == GET_SERVICES) {
			str += " GET_SERVICES";
		}
		if ((flags & GET_SHARED_LIBRARY_FILES) == GET_SHARED_LIBRARY_FILES) {
			str += " GET_SHARED_LIBRARY_FILES";
		}
		if ((flags & GET_SIGNATURES) == GET_SIGNATURES) {
			str += " GET_SIGNATURES";
		}
		if ((flags & GET_UNINSTALLED_PACKAGES) == GET_UNINSTALLED_PACKAGES) {
			str += " GET_UNINSTALLED_PACKAGES";
		}
		if ((flags & GET_URI_PERMISSION_PATTERNS) == GET_URI_PERMISSION_PATTERNS) {
			str += " GET_URI_PERMISSION_PATTERNS";
		}
		
		str = "[" + str.replaceAll("^ ", "") + "]";
		return str;
	}

	@Override
	public String[] currentToCanonicalPackageNames(String[] names) {
		if (LOG) {
			Log.d(TAG, "currentToCanonicalPackageNames(). ");
		}		
		return mProxy.currentToCanonicalPackageNames(names);
	}

	@Override
	public String[] canonicalToCurrentPackageNames(String[] names) {
		if (LOG) {
			Log.d(TAG, "canonicalToCurrentPackageNames(). ");
		}		
		return mProxy.canonicalToCurrentPackageNames(names);
	}

	@Override
	public Intent getLaunchIntentForPackage(String packageName) {
		if (LOG) {
			Log.d(TAG, "getLaunchIntentForPackage(). packageName: " + packageName);
		}		
		return mProxy.getLaunchIntentForPackage(packageName);
	}

	@Override
	public Intent getLeanbackLaunchIntentForPackage(String packageName) {
		if (LOG) {
			Log.d(TAG, "getLeanbackLaunchIntentForPackage(). ");
		}		
		return mProxy.getLeanbackLaunchIntentForPackage(packageName);
	}

	@Override
	public int[] getPackageGids(String packageName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getPackageGids(). ");
		}		
		return mProxy.getPackageGids(packageName);
	}

	@Override
	public PermissionInfo getPermissionInfo(String name, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getPermissionInfo(). ");
		}		
		return mProxy.getPermissionInfo(name, flags);
	}

	@Override
	public List<PermissionInfo> queryPermissionsByGroup(String group,
			int flags) throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "queryPermissionsByGroup(). ");
		}		
		return mProxy.queryPermissionsByGroup(group, flags);
	}

	@Override
	public PermissionGroupInfo getPermissionGroupInfo(String name, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getPermissionGroupInfo(). ");
		}		
		return mProxy.getPermissionGroupInfo(name, flags);
	}

	@Override
	public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
		if (LOG) {
			Log.d(TAG, "getAllPermissionGroups(). ");
		}		
		return mProxy.getAllPermissionGroups(flags);
	}

	@Override
	public ApplicationInfo getApplicationInfo(String packageName, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getApplicationInfo(). packageName: " + packageName + " flags: " + flags + toGetFlagString(flags));
		}
		InstalledAPks apks = InstalledAPks.getInstance();
		if (apks.hasApplicationInfo(packageName)) {
			ApplicationInfoX aInfo = apks.getApplicationInfo(packageName);

			if (LOG) {
				Log.d(TAG, "use parsed ApplicationInfoX: " + aInfo);
			}
			return aInfo;
		}
		
		return mProxy.getApplicationInfo(packageName, flags);
	}

	@Override
	public ActivityInfo getActivityInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityInfo(). component: " + component + " flags: " + flags + toGetFlagString(flags));
			Util.dumpStackTrace();
		}
		if (null == component) {
			return null;
		}
		
		String packageName = component.getPackageName();
		String className = component.getClassName();
		InstalledAPks apks = InstalledAPks.getInstance();
		if (apks.hasApplicationInfo(packageName)) {
			ActivityInfoX info = apks.getActivityInfo(className);			
			
			if (LOG) {
				Log.d(TAG, "use parsed pInfo: " + info);
			}
			return info;
		}
		
		return mProxy.getActivityInfo(component, flags);
	}

	@Override
	public ActivityInfo getReceiverInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getReceiverInfo(). component: " + component + " flags: " + flags + toGetFlagString(flags));
		}
		return mProxy.getReceiverInfo(component, flags);
	}

	@Override
	public ServiceInfo getServiceInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getServiceInfo(). component: " + component + " flags: " + flags + toGetFlagString(flags));
		}
		return mProxy.getServiceInfo(component, flags);
	}

	@Override
	public ProviderInfo getProviderInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getProviderInfo(). component: " + component + " flags: " + flags + toGetFlagString(flags));
		}
		return mProxy.getProviderInfo(component, flags);
	}

	@Override
	public List<PackageInfo> getInstalledPackages(int flags) {
		if (LOG) {
			Log.d(TAG, "getInstalledPackages(). ");
		}		
		return mProxy.getInstalledPackages(flags);
	}

	@Override
	public List<PackageInfo> getPackagesHoldingPermissions(
			String[] permissions, int flags) {
		if (LOG) {
			Log.d(TAG, "getPackagesHoldingPermissions(). ");
		}		
		return mProxy.getPackagesHoldingPermissions(permissions, flags);
	}

	@Override
	public int checkPermission(String permName, String pkgName) {
		if (LOG) {
			Log.d(TAG, "checkPermission(). ");
		}		
		return mProxy.checkPermission(permName, pkgName);
	}

	@Override
	public boolean addPermission(PermissionInfo info) {
		if (LOG) {
			Log.d(TAG, "addPermission(). ");
		}		
		return mProxy.addPermission(info);
	}

	@Override
	public boolean addPermissionAsync(PermissionInfo info) {
		if (LOG) {
			Log.d(TAG, "addPermissionAsync(). ");
		}		
		return mProxy.addPermission(info);
	}

	@Override
	public void removePermission(String name) {
		if (LOG) {
			Log.d(TAG, "removePermission(). ");
		}		
		mProxy.removePermission(name);
	}

	@Override
	public int checkSignatures(String pkg1, String pkg2) {
		if (LOG) {
			Log.d(TAG, "checkSignatures(). ");
		}		
		return mProxy.checkSignatures(pkg1, pkg2);
	}

	@Override
	public int checkSignatures(int uid1, int uid2) {
		if (LOG) {
			Log.d(TAG, "checkSignatures(). ");
		}		
		return mProxy.checkSignatures(uid1, uid2);
	}

	@Override
	public String[] getPackagesForUid(int uid) {
		if (LOG) {
			Log.d(TAG, "getPackagesForUid(). ");
		}		
		return mProxy.getPackagesForUid(uid);
	}

	@Override
	public String getNameForUid(int uid) {
		if (LOG) {
			Log.d(TAG, "getNameForUid(). ");
		}		
		return mProxy.getNameForUid(uid);
	}

	@Override
	public List<ApplicationInfo> getInstalledApplications(int flags) {
		if (LOG) {
			Log.d(TAG, "getInstalledApplications(). ");
		}		
		return mProxy.getInstalledApplications(flags);
	}

	@Override
	public String[] getSystemSharedLibraryNames() {
		if (LOG) {
			Log.d(TAG, "getSystemSharedLibraryNames(). ");
		}		
		return mProxy.getSystemSharedLibraryNames();
	}

	@Override
	public FeatureInfo[] getSystemAvailableFeatures() {
		if (LOG) {
			Log.d(TAG, "getSystemAvailableFeatures(). ");
		}		
		return mProxy.getSystemAvailableFeatures();
	}

	@Override
	public boolean hasSystemFeature(String name) {
		if (LOG) {
			Log.d(TAG, "hasSystemFeature(). ");
		}		
		return mProxy.hasSystemFeature(name);
	}

	@Override
	public ResolveInfo resolveActivity(Intent intent, int flags) {
		if (LOG) {
			Log.d(TAG, "resolveActivity(). ");
		}		
		return mProxy.resolveActivity(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
		if (LOG) {
			Log.d(TAG, "queryIntentActivities(). ");
		}		
		return mProxy.queryIntentActivities(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivityOptions(
			ComponentName caller, Intent[] specifics, Intent intent,
			int flags) {
		if (LOG) {
			Log.d(TAG, "queryIntentActivityOptions(). ");
		}		
		return mProxy.queryIntentActivityOptions(caller, specifics, intent, flags);
	}

	@Override
	public List<ResolveInfo> queryBroadcastReceivers(Intent intent,
			int flags) {
		if (LOG) {
			Log.d(TAG, "queryBroadcastReceivers(). ");
		}		
		return mProxy.queryBroadcastReceivers(intent, flags);
	}

	@Override
	public ResolveInfo resolveService(Intent intent, int flags) {
		if (LOG) {
			Log.d(TAG, "resolveService(). ");
		}		
		return mProxy.resolveService(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
		if (LOG) {
			Log.d(TAG, "queryIntentServices(). ");
		}		
		return mProxy.queryIntentServices(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentContentProviders(Intent intent,
			int flags) {
		if (LOG) {
			Log.d(TAG, "queryIntentContentProviders(). ");
		}		
		return mProxy.queryIntentContentProviders(intent, flags);
	}

	@Override
	public ProviderInfo resolveContentProvider(String name, int flags) {
		if (LOG) {
			Log.d(TAG, "resolveContentProvider(). ");
		}		
		return mProxy.resolveContentProvider(name, flags);
	}

	@Override
	public List<ProviderInfo> queryContentProviders(String processName,
			int uid, int flags) {
		if (LOG) {
			Log.d(TAG, "queryContentProviders(). ");
		}		
		return mProxy.queryContentProviders(processName, uid, flags);
	}

	@Override
	public InstrumentationInfo getInstrumentationInfo(
			ComponentName className, int flags)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "InstrumentationInfo(). ");
		}		
		return mProxy.getInstrumentationInfo(className, flags);
	}

	@Override
	public List<InstrumentationInfo> queryInstrumentation(
			String targetPackage, int flags) {
		if (LOG) {
			Log.d(TAG, "InstrumentationInfo(). ");
		}		
		return mProxy.queryInstrumentation(targetPackage, flags);
	}

	@Override
	public Drawable getDrawable(String packageName, int resid,
			ApplicationInfo appInfo) {
		if (LOG) {
			Log.d(TAG, "getDrawable(). ");
		}		
		return mProxy.getDrawable(packageName, resid, appInfo);
	}

	@Override
	public Drawable getActivityIcon(ComponentName activityName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityIcon(). ");
		}		
		return mProxy.getActivityIcon(activityName);
	}

	@Override
	public Drawable getActivityIcon(Intent intent)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityIcon(). ");
		}		
		return mProxy.getActivityIcon(intent);
	}

	@Override
	public Drawable getActivityBanner(ComponentName activityName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityBanner(). ");
		}		
		return mProxy.getActivityBanner(activityName);
	}

	@Override
	public Drawable getActivityBanner(Intent intent)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityBanner(). ");
		}		
		return mProxy.getActivityBanner(intent);
	}

	@Override
	public Drawable getDefaultActivityIcon() {
		if (LOG) {
			Log.d(TAG, "getDefaultActivityIcon(). ");
		}		
		return mProxy.getDefaultActivityIcon();
	}

	@Override
	public Drawable getApplicationIcon(ApplicationInfo info) {
		if (LOG) {
			Log.d(TAG, "getApplicationIcon(). ");
		}		
		return mProxy.getApplicationIcon(info);
	}

	@Override
	public Drawable getApplicationIcon(String packageName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getApplicationIcon(). ");
		}		
		return mProxy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getApplicationBanner(ApplicationInfo info) {
		if (LOG) {
			Log.d(TAG, "getApplicationBanner(). ");
		}		
		return mProxy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationBanner(String packageName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getApplicationBanner(). ");
		}		
		return mProxy.getApplicationBanner(packageName);
	}

	@Override
	public Drawable getActivityLogo(ComponentName activityName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityLogo(). ");
		}		
		return mProxy.getActivityLogo(activityName);
	}

	@Override
	public Drawable getActivityLogo(Intent intent)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getActivityLogo(). ");
		}		
		return mProxy.getActivityLogo(intent);
	}

	@Override
	public Drawable getApplicationLogo(ApplicationInfo info) {
		if (LOG) {
			Log.d(TAG, "getApplicationLogo(). ");
		}		
		return mProxy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationLogo(String packageName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getApplicationLogo(). ");
		}		
		return mProxy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
		if (LOG) {
			Log.d(TAG, "getUserBadgedIcon(). ");
		}		
		return mProxy.getUserBadgedIcon(icon, user);
	}

	@Override
	public Drawable getUserBadgedDrawableForDensity(Drawable drawable,
			UserHandle user, Rect badgeLocation, int badgeDensity) {
		if (LOG) {
			Log.d(TAG, "getUserBadgedDrawableForDensity(). ");
		}		
		return mProxy.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity);
	}

	@Override
	public CharSequence getUserBadgedLabel(CharSequence label,
			UserHandle user) {
		if (LOG) {
			Log.d(TAG, "getUserBadgedLabel(). ");
		}		
		return mProxy.getUserBadgedLabel(label, user);
	}

	@Override
	public CharSequence getText(String packageName, int resid,
			ApplicationInfo appInfo) {
		if (LOG) {
			Log.d(TAG, "getText(). ");
		}		
		return mProxy.getText(packageName, resid, appInfo);
	}

	@Override
	public XmlResourceParser getXml(String packageName, int resid,
			ApplicationInfo appInfo) {
		if (LOG) {
			Log.d(TAG, "getXml(). ");
		}		
		return mProxy.getXml(packageName, resid, appInfo);
	}

	@Override
	public CharSequence getApplicationLabel(ApplicationInfo info) {
		if (LOG) {
			Log.d(TAG, "getApplicationLabel(). info: " + info.packageName);
		}		
		return mProxy.getApplicationLabel(info);
	}

	@Override
	public Resources getResourcesForActivity(ComponentName activityName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getResourcesForActivity(). ");
		}		
		return mProxy.getResourcesForActivity(activityName);
	}

	@Override
	public Resources getResourcesForApplication(ApplicationInfo app)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getResourcesForApplication(). ");
		}		
		return mProxy.getResourcesForApplication(app);
	}

	@Override
	public Resources getResourcesForApplication(String appPackageName)
			throws NameNotFoundException {
		if (LOG) {
			Log.d(TAG, "getResourcesForApplication(). ");
		}		
		return mProxy.getResourcesForApplication(appPackageName);
	}

	@Override
	public void verifyPendingInstall(int id, int verificationCode) {
		if (LOG) {
			Log.d(TAG, "verifyPendingInstall(). ");
		}		
		mProxy.verifyPendingInstall(id, verificationCode);
	}

	@Override
	public void extendVerificationTimeout(int id,
			int verificationCodeAtTimeout, long millisecondsToDelay) {
		if (LOG) {
			Log.d(TAG, "extendVerificationTimeout(). ");
		}		
		mProxy.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay);
	}

	@Override
	public void setInstallerPackageName(String targetPackage,
			String installerPackageName) {
		if (LOG) {
			Log.d(TAG, "setInstallerPackageName(). ");
		}		
		mProxy.setInstallerPackageName(targetPackage, installerPackageName);
	}

	@Override
	public String getInstallerPackageName(String packageName) {
		packageName = "org.bbs.felixonandroid";
		if (LOG) {
			Log.d(TAG, "getInstallerPackageName(). packageName: " + packageName);
		}		
		return mProxy.getInstallerPackageName(packageName);
	}

	@Override
	public void addPackageToPreferred(String packageName) {
		if (LOG) {
			Log.d(TAG, "addPackageToPreferred(). ");
		}		
		mProxy.addPackageToPreferred(packageName);
	}

	@Override
	public void removePackageFromPreferred(String packageName) {
		if (LOG) {
			Log.d(TAG, "removePackageFromPreferred(). ");
		}		
		mProxy.removePackageFromPreferred(packageName);
	}

	@Override
	public List<PackageInfo> getPreferredPackages(int flags) {
		if (LOG) {
			Log.d(TAG, "getPreferredPackages(). ");
		}		
		return mProxy.getPreferredPackages(flags);
	}

	@Override
	public void addPreferredActivity(IntentFilter filter, int match,
			ComponentName[] set, ComponentName activity) {
		if (LOG) {
			Log.d(TAG, "addPreferredActivity(). ");
		}		
		mProxy.addPreferredActivity(filter, match, set, activity);
	}

	@Override
	public void clearPackagePreferredActivities(String packageName) {
		if (LOG) {
			Log.d(TAG, "clearPackagePreferredActivities(). ");
		}		
		mProxy.clearPackagePreferredActivities(packageName);
	}

	@Override
	public int getPreferredActivities(List<IntentFilter> outFilters,
			List<ComponentName> outActivities, String packageName) {
		if (LOG) {
			Log.d(TAG, "getPreferredActivities(). ");
		}		
		return mProxy.getPreferredActivities(outFilters, outActivities, packageName);
	}

	@Override
	public void setComponentEnabledSetting(ComponentName componentName,
			int newState, int flags) {
		if (LOG) {
			Log.d(TAG, "setComponentEnabledSetting(). ");
		}		
		mProxy.setComponentEnabledSetting(componentName, newState, flags);
	}

	@Override
	public int getComponentEnabledSetting(ComponentName componentName) {
		if (LOG) {
			Log.d(TAG, "getComponentEnabledSetting(). ");
		}		
		return mProxy.getComponentEnabledSetting(componentName);
	}

	@Override
	public PackageInfo getPackageArchiveInfo(String archiveFilePath,
			int flags) {
		if (LOG) {
			Log.d(TAG, "getPackageArchiveInfo(). ");
		}		
		return mProxy.getPackageArchiveInfo(archiveFilePath, flags);
	}

	@Override
	public void setApplicationEnabledSetting(String packageName,
			int newState, int flags) {
		if (LOG) {
			Log.d(TAG, "setApplicationEnabledSetting(). ");
		}		
		mProxy.setApplicationEnabledSetting(packageName, newState, flags);
	}

	@Override
	public int getApplicationEnabledSetting(String packageName) {
		if (LOG) {
			Log.d(TAG, "getApplicationEnabledSetting(). ");
		}		
		return mProxy.getApplicationEnabledSetting(packageName);
	}

	@Override
	public boolean isSafeMode() {
		if (LOG) {
			Log.d(TAG, "isSafeMode(). ");
		}		
		return mProxy.isSafeMode();
	}

	@Override
	public PackageInstaller getPackageInstaller() {
		if (LOG) {
			Log.d(TAG, "getPackageInstaller(). ");
		}		
		return mProxy.getPackageInstaller();
	}
	
}