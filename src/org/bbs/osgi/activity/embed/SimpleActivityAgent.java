package org.bbs.osgi.activity.embed;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import android.app.Activity;

/**
 *
 * @author luoqii
 *
 */
public class SimpleActivityAgent extends EmbeddedActivityAgent {
	private static final String TAG = SimpleActivityAgent.class.getSimpleName();
	private String mName;
	private Bundle mBundle;
	
	public SimpleActivityAgent(String targetActivityClassName){
		mName = targetActivityClassName;
	}
	
	public SimpleActivityAgent(Bundle bundle, String targetActivityClassName) {
		this(targetActivityClassName);
		mBundle = bundle;
	}
	
	public Activity getTargetActivity(){
		try {
			if (null == mBundle) {
				return (Activity) Class.forName(mName).newInstance();
			} else {
				return (Activity) mBundle.loadClass(mName).newInstance();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	};
	
}
