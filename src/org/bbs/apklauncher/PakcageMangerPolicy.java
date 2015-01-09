package org.bbs.apklauncher;

import java.util.List;

import org.bbs.felix.util.PackageParser.PackageInfoX.ApplicationInfoX;

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
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;

public class PakcageMangerPolicy extends PackageManager {
	
	private PackageManager mPolicy;

	public PakcageMangerPolicy(PackageManager policy) {
		mPolicy = policy;
	}

	@Override
	public PackageInfo getPackageInfo(String packageName, int flags)
			throws NameNotFoundException {
		return mPolicy.getPackageInfo(packageName, flags);
	}

	@Override
	public String[] currentToCanonicalPackageNames(String[] names) {
		return mPolicy.currentToCanonicalPackageNames(names);
	}

	@Override
	public String[] canonicalToCurrentPackageNames(String[] names) {
		return mPolicy.canonicalToCurrentPackageNames(names);
	}

	@Override
	public Intent getLaunchIntentForPackage(String packageName) {
		return mPolicy.getLaunchIntentForPackage(packageName);
	}

	@Override
	public Intent getLeanbackLaunchIntentForPackage(String packageName) {
		return mPolicy.getLeanbackLaunchIntentForPackage(packageName);
	}

	@Override
	public int[] getPackageGids(String packageName)
			throws NameNotFoundException {
		return mPolicy.getPackageGids(packageName);
	}

	@Override
	public PermissionInfo getPermissionInfo(String name, int flags)
			throws NameNotFoundException {
		return mPolicy.getPermissionInfo(name, flags);
	}

	@Override
	public List<PermissionInfo> queryPermissionsByGroup(String group,
			int flags) throws NameNotFoundException {
		return mPolicy.queryPermissionsByGroup(group, flags);
	}

	@Override
	public PermissionGroupInfo getPermissionGroupInfo(String name, int flags)
			throws NameNotFoundException {
		return mPolicy.getPermissionGroupInfo(name, flags);
	}

	@Override
	public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
		return mPolicy.getAllPermissionGroups(flags);
	}

	@Override
	public ApplicationInfo getApplicationInfo(String packageName, int flags)
			throws NameNotFoundException {
		Log.d(StubActivity.TAG, "getApplicationInfo(). packageName: " + packageName + " flags: " + flags);
		InstalledAPks apks = InstalledAPks.getInstance();
		if (apks.hasApplication(packageName)
				&& (flags | PackageManager.GET_META_DATA) != 0) {
			ApplicationInfoX aInfo = apks.getApplication(packageName);

			Log.d(StubActivity.TAG, "use pased ApplicationInfoX: " + aInfo);
			return aInfo;
		}
		
		return mPolicy.getApplicationInfo(packageName, flags);
	}

	@Override
	public ActivityInfo getActivityInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mPolicy.getActivityInfo(component, flags);
	}

	@Override
	public ActivityInfo getReceiverInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mPolicy.getReceiverInfo(component, flags);
	}

	@Override
	public ServiceInfo getServiceInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mPolicy.getServiceInfo(component, flags);
	}

	@Override
	public ProviderInfo getProviderInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mPolicy.getProviderInfo(component, flags);
	}

	@Override
	public List<PackageInfo> getInstalledPackages(int flags) {
		return mPolicy.getInstalledPackages(flags);
	}

	@Override
	public List<PackageInfo> getPackagesHoldingPermissions(
			String[] permissions, int flags) {
		return mPolicy.getPackagesHoldingPermissions(permissions, flags);
	}

	@Override
	public int checkPermission(String permName, String pkgName) {
		return mPolicy.checkPermission(permName, pkgName);
	}

	@Override
	public boolean addPermission(PermissionInfo info) {
		return mPolicy.addPermission(info);
	}

	@Override
	public boolean addPermissionAsync(PermissionInfo info) {
		return mPolicy.addPermission(info);
	}

	@Override
	public void removePermission(String name) {
		mPolicy.removePermission(name);
	}

	@Override
	public int checkSignatures(String pkg1, String pkg2) {
		return mPolicy.checkSignatures(pkg1, pkg2);
	}

	@Override
	public int checkSignatures(int uid1, int uid2) {
		return mPolicy.checkSignatures(uid1, uid2);
	}

	@Override
	public String[] getPackagesForUid(int uid) {
		return mPolicy.getPackagesForUid(uid);
	}

	@Override
	public String getNameForUid(int uid) {
		return mPolicy.getNameForUid(uid);
	}

	@Override
	public List<ApplicationInfo> getInstalledApplications(int flags) {
		return mPolicy.getInstalledApplications(flags);
	}

	@Override
	public String[] getSystemSharedLibraryNames() {
		return mPolicy.getSystemSharedLibraryNames();
	}

	@Override
	public FeatureInfo[] getSystemAvailableFeatures() {
		return mPolicy.getSystemAvailableFeatures();
	}

	@Override
	public boolean hasSystemFeature(String name) {
		return mPolicy.hasSystemFeature(name);
	}

	@Override
	public ResolveInfo resolveActivity(Intent intent, int flags) {
		return mPolicy.resolveActivity(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
		return mPolicy.queryIntentActivities(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivityOptions(
			ComponentName caller, Intent[] specifics, Intent intent,
			int flags) {
		return mPolicy.queryIntentActivityOptions(caller, specifics, intent, flags);
	}

	@Override
	public List<ResolveInfo> queryBroadcastReceivers(Intent intent,
			int flags) {
		return mPolicy.queryBroadcastReceivers(intent, flags);
	}

	@Override
	public ResolveInfo resolveService(Intent intent, int flags) {
		return mPolicy.resolveService(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
		return mPolicy.queryIntentServices(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentContentProviders(Intent intent,
			int flags) {
		return mPolicy.queryIntentContentProviders(intent, flags);
	}

	@Override
	public ProviderInfo resolveContentProvider(String name, int flags) {
		return mPolicy.resolveContentProvider(name, flags);
	}

	@Override
	public List<ProviderInfo> queryContentProviders(String processName,
			int uid, int flags) {
		return mPolicy.queryContentProviders(processName, uid, flags);
	}

	@Override
	public InstrumentationInfo getInstrumentationInfo(
			ComponentName className, int flags)
			throws NameNotFoundException {
		return mPolicy.getInstrumentationInfo(className, flags);
	}

	@Override
	public List<InstrumentationInfo> queryInstrumentation(
			String targetPackage, int flags) {
		return mPolicy.queryInstrumentation(targetPackage, flags);
	}

	@Override
	public Drawable getDrawable(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mPolicy.getDrawable(packageName, resid, appInfo);
	}

	@Override
	public Drawable getActivityIcon(ComponentName activityName)
			throws NameNotFoundException {
		return mPolicy.getActivityIcon(activityName);
	}

	@Override
	public Drawable getActivityIcon(Intent intent)
			throws NameNotFoundException {
		return mPolicy.getActivityIcon(intent);
	}

	@Override
	public Drawable getActivityBanner(ComponentName activityName)
			throws NameNotFoundException {
		return mPolicy.getActivityBanner(activityName);
	}

	@Override
	public Drawable getActivityBanner(Intent intent)
			throws NameNotFoundException {
		return mPolicy.getActivityBanner(intent);
	}

	@Override
	public Drawable getDefaultActivityIcon() {
		return mPolicy.getDefaultActivityIcon();
	}

	@Override
	public Drawable getApplicationIcon(ApplicationInfo info) {
		return mPolicy.getApplicationIcon(info);
	}

	@Override
	public Drawable getApplicationIcon(String packageName)
			throws NameNotFoundException {
		return mPolicy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getApplicationBanner(ApplicationInfo info) {
		return mPolicy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationBanner(String packageName)
			throws NameNotFoundException {
		return mPolicy.getApplicationBanner(packageName);
	}

	@Override
	public Drawable getActivityLogo(ComponentName activityName)
			throws NameNotFoundException {
		return mPolicy.getActivityLogo(activityName);
	}

	@Override
	public Drawable getActivityLogo(Intent intent)
			throws NameNotFoundException {
		return mPolicy.getActivityLogo(intent);
	}

	@Override
	public Drawable getApplicationLogo(ApplicationInfo info) {
		return mPolicy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationLogo(String packageName)
			throws NameNotFoundException {
		return mPolicy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
		return mPolicy.getUserBadgedIcon(icon, user);
	}

	@Override
	public Drawable getUserBadgedDrawableForDensity(Drawable drawable,
			UserHandle user, Rect badgeLocation, int badgeDensity) {
		return mPolicy.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity);
	}

	@Override
	public CharSequence getUserBadgedLabel(CharSequence label,
			UserHandle user) {
		return mPolicy.getUserBadgedLabel(label, user);
	}

	@Override
	public CharSequence getText(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mPolicy.getText(packageName, resid, appInfo);
	}

	@Override
	public XmlResourceParser getXml(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mPolicy.getXml(packageName, resid, appInfo);
	}

	@Override
	public CharSequence getApplicationLabel(ApplicationInfo info) {
		return mPolicy.getApplicationLabel(info);
	}

	@Override
	public Resources getResourcesForActivity(ComponentName activityName)
			throws NameNotFoundException {
		return mPolicy.getResourcesForActivity(activityName);
	}

	@Override
	public Resources getResourcesForApplication(ApplicationInfo app)
			throws NameNotFoundException {
		return mPolicy.getResourcesForApplication(app);
	}

	@Override
	public Resources getResourcesForApplication(String appPackageName)
			throws NameNotFoundException {
		return mPolicy.getResourcesForApplication(appPackageName);
	}

	@Override
	public void verifyPendingInstall(int id, int verificationCode) {
		mPolicy.verifyPendingInstall(id, verificationCode);
	}

	@Override
	public void extendVerificationTimeout(int id,
			int verificationCodeAtTimeout, long millisecondsToDelay) {
		mPolicy.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay);
	}

	@Override
	public void setInstallerPackageName(String targetPackage,
			String installerPackageName) {
		mPolicy.setInstallerPackageName(targetPackage, installerPackageName);
	}

	@Override
	public String getInstallerPackageName(String packageName) {
		return mPolicy.getInstallerPackageName(packageName);
	}

	@Override
	public void addPackageToPreferred(String packageName) {
		mPolicy.addPackageToPreferred(packageName);
	}

	@Override
	public void removePackageFromPreferred(String packageName) {
		mPolicy.removePackageFromPreferred(packageName);
	}

	@Override
	public List<PackageInfo> getPreferredPackages(int flags) {
		return mPolicy.getPreferredPackages(flags);
	}

	@Override
	public void addPreferredActivity(IntentFilter filter, int match,
			ComponentName[] set, ComponentName activity) {
		mPolicy.addPreferredActivity(filter, match, set, activity);
	}

	@Override
	public void clearPackagePreferredActivities(String packageName) {
		mPolicy.clearPackagePreferredActivities(packageName);
	}

	@Override
	public int getPreferredActivities(List<IntentFilter> outFilters,
			List<ComponentName> outActivities, String packageName) {
		return mPolicy.getPreferredActivities(outFilters, outActivities, packageName);
	}

	@Override
	public void setComponentEnabledSetting(ComponentName componentName,
			int newState, int flags) {
		mPolicy.setComponentEnabledSetting(componentName, newState, flags);
	}

	@Override
	public int getComponentEnabledSetting(ComponentName componentName) {
		return mPolicy.getComponentEnabledSetting(componentName);
	}

	@Override
	public PackageInfo getPackageArchiveInfo(String archiveFilePath,
			int flags) {
		return mPolicy.getPackageArchiveInfo(archiveFilePath, flags);
	}

	@Override
	public void setApplicationEnabledSetting(String packageName,
			int newState, int flags) {
		mPolicy.setApplicationEnabledSetting(packageName, newState, flags);
	}

	@Override
	public int getApplicationEnabledSetting(String packageName) {
		return mPolicy.getApplicationEnabledSetting(packageName);
	}

	@Override
	public boolean isSafeMode() {
		return mPolicy.isSafeMode();
	}

	@Override
	public PackageInstaller getPackageInstaller() {
		return mPolicy.getPackageInstaller();
	}
	
}