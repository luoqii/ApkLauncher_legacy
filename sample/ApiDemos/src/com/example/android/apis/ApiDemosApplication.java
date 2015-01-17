/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis;

import android.app.Application;
import android.util.Log;

/**
 * This is an example of a {@link android.app.Application} class.  This can
 * be used as a central repository for per-process information about your app;
 * however it is recommended to use singletons for that instead rather than merge
 * all of these globals from across your application into one place here.
 * 
 * In this case, we have not defined any specific work for this Application.
 * 
 * See samples/ApiDemos/tests/src/com.example.android.apis/ApiDemosApplicationTests for an example
 * of how to perform unit tests on an Application object.
 */
public class ApiDemosApplication extends 
Application 
//Target_Application
{
//    public ApiDemosApplication(Context base) {
//		super(base);
//		// TODO Auto-generated constructor stub
//	}
//    public ApiDemosApplication() {
//    	super(null);
//	}

	private static final String TAG = ApiDemosApplication.class.getSimpleName();;

	@Override
    public void onCreate()  {
		Log.d(TAG, "px0: " + getBaseContext().getResources().getDimensionPixelOffset(R.dimen.px0));
		Log.d(TAG, "px10: " + getBaseContext().getResources().getDimensionPixelOffset(R.dimen.px1));
		Log.d(TAG, "px1_1: " + getBaseContext().getResources().getDimensionPixelOffset(R.dimen.px1_1));
//		gets
    }
}
