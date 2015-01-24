felix-on-android
================

felix-on-android osgi android

see:http://code.google.com/p/felix-on-android/

优点：
  * 在有限情况(主应用不需要更新)下，实现静黙升级(not root)；
  * 快速分发，升级；
  * 动态加载(扩展)应用功能（需要定义良好的框架）;
  * 向第三方开放接口，我们成为平台；

缺点：
  * 更加复杂的升级方案（apk升级，bundle升级）；
  * 更加复杂的后台?
  * 对传统android开发人员的挑战（引入额外的开发模型，工具的支持（ant, adt, gradle?），osgi 相关知识）;
  * native支持
  * 多应用支持
  * 某些功能需要主应用的配合才能实现。

NOTE
====
do not denpendent some specific osgi impl, just use stardard osgi feature.

PROBLEM
=======
do not user Activity.this (directly or indirectly) in embedded activiy.
overridePendingTransition(0, 0)
asset only one (bundel or host) useable.
Fragment.startActvity()
native lib
start activity by custom action.
intent.putSeriable() (less then 5.0) android has bug which will not use classload we support by Intent.getExtr().setClassLoader().

TRICK
=====
shit package.extra whit ';' and ','
there is ONE (yes 1 not 2 or 3 ...) BLANK line in manifest.mf

TODO
====
how to build felix?

SEE
===
https://android-review.googlesource.com/#/c/119402/
