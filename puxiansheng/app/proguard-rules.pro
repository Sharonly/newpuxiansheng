# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-renamesourcefileattribute SourceFile

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-dontwarn com.squareup.picasso.*
-keep class com.squareup.picasso.** { *;}
# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class **.R$* {*;}
-keepclassmembers enum * { *;}

-keep class com.support.v7.widget.** {*;}
-keep public class com.puxiansheng.www.R$*{
public static final int *;
}
-keep public class * extends android.view.View
-keep class com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity{*;}
-keep class com.puxiansheng.www.ui.order.SucceseVideoDetailActivity{*;}
-keep class com.puxiansheng.www.ui.order.dialog.** {*;}

-dontwarn  okhttp3.**
-keep class okhttp3.** { *;}

-dontwarn  okio.**
-keep class okio.** { *;}

-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
#
#-keep public class com.puxiansheng.www.databinding.R$*{
# public static final int *;
# }

#
-keep class com.uc.** {*;}
#
#-keepclassmembers class * {
#   public <init> (org.json.JSONObject);
#}
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
-keep class com.zui.** {*;}
-keep class com.miui.** {*;}
-keep class com.heytap.** {*;}
-keep class a.** {*;}
-keep class com.vivo.** {*;}
#umeng
-keep class com.umeng.** { *; }
# 友盟统计分析
-keepclassmembers class * { public <init>(org.json.JSONObject);}
-keepclassmembers enum com.umeng.analytics.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class * extends com.umeng.**
-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet, int);
}
-dontwarn  org.greenrobot.greendao.**
-keep class org.greenrobot.greendao.**{*;}
-keep class com.puxiansheng.logic.bean.http.**{*;}

-keepclassmembers class ** {
    public void on*Event(...);
}

-keep class c.t.**{*;}
-keep class com.tencent.map.geolocation.**{*;}

-dontwarn  org.eclipse.jdt.annotation.**
-dontwarn  c.t.**

-keep class com.tencent.tencentmap.**{*;}
-keep class com.tencent.map.**{*;}
-keep class com.tencent.beacontmap.**{*;}
-keep class navsns.**{*;}
-dontwarn com.qq.**
-dontwarn com.tencent.**



## 指定代码的压缩级别 0 - 7(指定代码进行迭代优化的次数，在Android里面默认是5，这条指令也只有在可以优化时起作用。)
#-optimizationpasses 5
## 混淆时不会产生形形色色的类名(混淆时不使用大小写混合类名)
#-dontusemixedcaseclassnames
## 指定不去忽略非公共的库类(不跳过library中的非public的类)
#-dontskipnonpubliclibraryclasses
## 指定不去忽略包可见的库类的成员
#-dontskipnonpubliclibraryclassmembers
##不进行优化，建议使用此选项，
#-dontoptimize
# # 不进行预校验,Android不需要,可加快混淆速度。
#-dontpreverify
#
## 屏蔽警告
#-ignorewarnings
#
## 指定混淆是采用的算法，后面的参数是一个过滤器
## 这个过滤器是谷歌推荐的算法，一般不做更改
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
## 保护代码中的Annotation不被混淆
#-keepattributes *Annotation*
## 避免混淆泛型, 这在JSON实体映射时非常重要
#-keepattributes Signature
## 抛出异常时保留代码行号
#-keepattributes SourceFile,LineNumberTable
#-allowaccessmodification
#
##当有优化和使用-repackageclasses时才适用。
#-repackageclasses ''
#
# # 包含有类名->混淆后类名的映射关系
#-verbose
#
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Appliction
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keep public class * extends android.view.View
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#-keep class android.support.** {*;}## 保留support下的所有类及其内部类


# 保留继承的
-keep public class * extends android.support.v4.**

-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**