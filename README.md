[![Build Status](https://travis-ci.org/luoqii/ApkLauncher.svg)](https://travis-ci.org/luoqii/ApkLauncher)

felix-on-android
================

felix-on-android osgi android

see:http://code.google.com/p/felix-on-android/

宿主平台，目标应用

优点：
  * 实现静黙升级(not root/google play), 只需动态加载一个apk；
  * 对升级的完全控制，大大减少了对第三方分发平台的依赖；

缺点：
  * 因为android的静态声明模型（permission, activity, service, action...）,某些功能需要主应用的配合才能实现。
  * 如果目标应用的AndroidManifest.xml描述发生变化，宿主平台也需要同时升级；
  * 更加复杂的后台?
  * 对传统android开发人员的挑战（引入额外的开发模型，工具的支持（ant, adt, gradle?），osgi 相关知识）;

HOW 2 USE DEMO
=======
adb shell mkdir /sdcard/apk
adb push sample/ApiDemo/bin/ApiDemo.apk /sdcard/apk/
# apidemo use ACTION to retrive Activity info, so we must install it. at runntime, activity which be loaded is 
# located an /sdcard/apk/ApiDemo.apk
adb install sample/ApiDemo/bin/ApiDemo.apk 

PROBLEM
=======
notification  getHostContext() getHostIdentifier()
do not user Activity.this (directly or indirectly) in embedded activiy.
overridePendingTransition(0, 0)
asset only one (bundel or host) useable.
Fragment.startActvity()
native lib
start activity by custom action.
intent.putSeriable() (less then 5.0) android has bug which will not use classload we support by Intent.getExtr().setClassLoader().

CRASH:
com.example.android.apis.graphics.BitmapDecode (n6)
com.example.android.apis.graphics.ColorFilters (n6)
java.lang.ClassNotFoundException: Didn't find class "com.example.android.apis.Purgeable" (n6)

TRICK
=====
shit package.extra whit ';' and ','
there is ONE (yes 1 not 2 or 3 ...) BLANK line in manifest.mf

TODO
====
how to build felix?
to add all android demo app (now only has ApiDemo.)

NOTE
====
do not denpendent some specific osgi impl, just use stardard osgi feature.

SEE
===
https://android-review.googlesource.com/#/c/119402/

