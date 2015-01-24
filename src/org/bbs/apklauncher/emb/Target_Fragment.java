package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.emb.auto_gen.Host_Activity;
import org.bbs.apklauncher.emb.auto_gen.Target_Activity;

import android.annotation.SuppressLint;
import android.app.Fragment;

@SuppressLint("NewApi")
public class Target_Fragment extends Fragment {
//	public Activity getHostActivity() {
//		return getActivity();
//	}
//	
//	public Activity getTargetActivity() {
//		return getActivity();
//	}
	
	public Host_Activity getHostActivity() {
		return (Host_Activity) getActivity();
	}
	
	public Target_Activity getTargetActivity() {
		return getHostActivity().getTargetActivity();
	}
}
