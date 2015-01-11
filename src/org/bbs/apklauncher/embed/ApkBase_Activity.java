package org.bbs.apklauncher.embed;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * this is not a activity actually.
 * @author luoiqq
 *
 */
public class ApkBase_Activity extends ContextWrapper {

	public ApkBase_Activity(Context base) {
		super(base);
	}

}
