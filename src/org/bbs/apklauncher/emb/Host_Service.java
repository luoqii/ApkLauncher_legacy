package org.bbs.apklauncher.emb;

import org.bbs.osgi.activity.LazyContext;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.IBinder;

@SuppressLint("NewApi")
public abstract class Host_Service extends Service {
	
	Target_Service mTargetService;
	protected LazyContext mTargetContext;
	Context mRealBaseContext;
	private PackageManager mSysPm;

	@Override
	protected void attachBaseContext(Context newBase) {
		mRealBaseContext = newBase;
		mTargetContext = new LazyContext(newBase);
		super.attachBaseContext(mTargetContext);
		mSysPm = getPackageManager();
	}
	
	Context getHostContext() {
		return mRealBaseContext;
	}

	abstract protected void onPrepareServiceStub(Intent intent) ;
	
	@Override
	public IBinder onBind(Intent intent) {
		onPrepareServiceStub(intent);
		if (null != mTargetService) {
			return mTargetService.onBind(intent);
		} else {
			return null;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (null != mTargetService) {
			mTargetService.onCreate();
		}
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		onPrepareServiceStub(intent);
		super.onStart(intent, startId);
		if (null != mTargetService) {
			mTargetService.onStart(intent, startId);
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		onPrepareServiceStub(intent);
//		return super.onStartCommand(intent, flags, startId);
		return mTargetService.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (null != mTargetService) {
			mTargetService.onDestroy();
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (null != mTargetService) {
			mTargetService.onConfigurationChanged(newConfig);
		}
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if (null != mTargetService) {
			mTargetService.onLowMemory();
		}
	}
	
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		if (null != mTargetService) {
			mTargetService.onTrimMemory(level);
		}
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
//		return super.onUnbind(intent);
		if (null != mTargetService) {
			return mTargetService.onUnbind(intent);
		} else {
			return false;
		}
	}
	
	
	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		if (null != mTargetService) {
			mTargetService.onRebind(intent);
		}
	}
	
	@Override
	public void onTaskRemoved(Intent rootIntent) {
		super.onTaskRemoved(rootIntent);
		if (null != mTargetService) {
			mTargetService.onTaskRemoved(rootIntent);
		}
	}

	
	

}
