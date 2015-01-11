package org.bbs.osgi.activity;

import org.bbs.osgi.activity.embed.EmbeddedApplictionAgent;
import org.osgi.framework.Bundle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.view.LayoutInflater;

/**
 * if android call us we call through to {@link #mAgents};
 * otherwise call super or do ourself.
 * 
 * <p>
 * when add new function, keep it in section, in order.
 * 
 * @author luoqii
 *
 * @see {@link ApplicationAgent}
 */
public class BundleApplication extends AbsApplication
{

	private static final String TAG = BundleApplication.class.getSimpleName();
	protected LazyContext mLazyContext;
	
	public void attachBundleAplication(ApplicationAgent agent, Bundle bundle, Resources res, Context baseContext){
		agent.mHostApplicion = this;
		if (null != res) {
			if (mLazyContext == null) {
				mLazyContext = new LazyContext(baseContext);
			}
			LazyContext.bundleReady(mLazyContext, bundle, res, null);
		}
		if (agent instanceof EmbeddedApplictionAgent) {
			Application app = ((EmbeddedApplictionAgent)agent).mBundelApp;
//			ApplicationUtil.copyFields(agent.mHostApplicion, app);
			ReflectUtil.ApplicationUtil.callAttach(app, baseContext);
		}
		
		callStubOnCreate(agent);
		
		mAgents.add(agent);
	}

	@Override 
    protected void attachBaseContext(Context newBase) {
    	mLazyContext = new LazyContext(newBase);
        super.attachBaseContext(mLazyContext);
    }

	public Resources getResources() {
		// this will call before onCreate().
//		initActivityAgent();
//		return super.getResources();
		return mLazyContext.getResources();
//		return mSourceMerger == null ? super.getResources() : mSourceMerger;
	}
	
}
