
package com.example.android.apis.stub;

public class Base_Service extends org.bbs.apklauncher.emb.Target_Service {

	
	public int getHostIdentifier(String name, String defType) {
		return getHostContext()
				.getResources().getIdentifier(name, defType, getHostContext().getPackageName());
	}
}
