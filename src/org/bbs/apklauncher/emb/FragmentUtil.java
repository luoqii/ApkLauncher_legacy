package org.bbs.apklauncher.emb;

import org.bbs.apklauncher.emb.auto_gen.StubBase_FragmentActivity;
import org.bbs.apklauncher.emb.auto_gen.Target_FragmentActivity;

import android.support.v4.app.Fragment;

public class FragmentUtil {
	public static StubBase_FragmentActivity getHostActvity(Fragment fragment) {
		return ((StubBase_FragmentActivity)(fragment.getActivity()));
	}
	
	public static Target_FragmentActivity getTargetActvity(Fragment fragment) {
		return ((StubBase_FragmentActivity)(fragment.getActivity())).getTargetActivity();
	}
}
