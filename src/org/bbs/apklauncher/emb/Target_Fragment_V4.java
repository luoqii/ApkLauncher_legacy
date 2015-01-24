package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.emb.auto_gen.Host_FragmentActivity;
import org.bbs.apklauncher.emb.auto_gen.Target_FragmentActivity;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class Target_Fragment_V4 extends Fragment {
//	public Activity getHostActivity() {
//		return getActivity();
//	}
//	
//	public Activity getTargetActivity() {
//		return getActivity();
//	}
	
	public Host_FragmentActivity getHostActivity() {
		return (Host_FragmentActivity) getActivity();
	}
	
	public Target_FragmentActivity getTargetActivity() {
		return getHostActivity().getTargetActivity();
	}
}
