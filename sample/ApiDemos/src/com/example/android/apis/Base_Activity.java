package com.example.android.apis;

import org.bbs.apklauncher.emb.gen.Target_Activity;

import android.app.Activity;

public class Base_Activity extends Target_Activity {
	public Activity getActivity() {
		return mHostActivity;
	}
}
