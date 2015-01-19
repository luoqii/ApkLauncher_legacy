package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.emb.auto_gen.Host_FragmentActivity;
import org.bbs.apklauncher.emb.auto_gen.Target_FragmentActivity;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class FragmentUtil {
	public static Host_FragmentActivity getHostActvity(Fragment fragment) {
		return ((Host_FragmentActivity)(fragment.getActivity()));
	}
	
	public static Target_FragmentActivity getTargetActvity(Fragment fragment) {
		return ((Host_FragmentActivity)(fragment.getActivity())).getTargetActivity();
	}
}
