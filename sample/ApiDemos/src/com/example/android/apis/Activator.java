package com.example.android.apis;

import org.bbs.osgi.activity.embed.EmbeddedActivityAgent;
import org.bbs.osgi.activity.embed.SimpleActivityAgent;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import android.app.Activity;
import android.content.Context;

public class Activator implements BundleActivator {
	
	public static Context getBundleHostContext(Activity activity){
		return activity.getWindow().getContext();
	}

	@Override
	public void start(BundleContext context) throws Exception {
//		context.registerService(ApiDemos.class.getName(), new ApiDemos(), null);
		context.registerService(EmbeddedApiDemos.class.getName(), new ServiceFactory<EmbeddedApiDemos>() {

			

			@Override
			public EmbeddedApiDemos getService(Bundle arg0, ServiceRegistration<EmbeddedApiDemos> arg1) {
				// TODO Auto-generated method stub
				
				
				
				return new EmbeddedApiDemos();
			}

			@Override
			public void ungetService(Bundle arg0, ServiceRegistration<EmbeddedApiDemos> arg1,
					EmbeddedApiDemos arg2) {
				// TODO Auto-generated method stub
				arg0.getLastModified();
				arg0.getState();
				arg0.getVersion();
//				arg0.getSymbolicName();
//				arg0.equals(1);
			}
		}, null);
//		context.registerService(SimpleBundleList.class.getName(), new SimpleBundleList(), null);
//		registerActivity(context, ApiDemos.class.getName());
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
	}
	
	public static void registerActivity(BundleContext context, String ActivityClassName){
		context.registerService(ActivityClassName, new SimpleActivityAgent(ActivityClassName), null);
	}
	
	public static class EmbeddedApiDemos extends EmbeddedActivityAgent {

		
		
		
		
		
		@Override
		public Activity getTargetActivity() {
			// TODO Auto-generated method stub
//			return new AutoComplete1();

			
			return new ApiDemos();
		}
		
	}

}
