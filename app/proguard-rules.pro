# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\newandroid\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-keep libraryjars ../commonTools

#不去混淆aidl文件
#
#-keep class * implements android.os.IInterface {*;}
-keep class android.content.pm.**{ *;}
#jar
-keep class com.catchgift.ads.** {*; }

-keep class com.squareup.picasso.** {*; }
-keep class com.adjust.sdk.** {*; }



##不去混淆的包下
#-keep public class com.booster.memory.green.master.listviewscroll.commonservice.otherservice.** { *;}

#不去混淆BatMobi包
-keep class com.batmobi.**{ *;}
#MobVista
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mobvista.** {*; }
-keep interface com.mobvista.** {*; }
-keep class android.support.v4.** { *; }
-dontwarn com.mobvista.**
-keep class **.R$* { public static final int mobvista*; }

#忽略facebook里面的class文件
-keep class com.facebook.ads.** { *; }
-keep class com.catchgift.ads.** { *; }
-keep class com.squareup.picasso.** { *; }
-keep class com.adjust.sdk.** { *; }

-keep class flash.mobile.news.valueb.api.** { *; }
-keep class flash.mobile.news.valueb.frag.** { *; }
-keep class flash.mobile.news.valueb.model.** { *; }
-keep class flash.mobile.news.valueb.presenter.** { *; }
-keep class flash.mobile.news.valueb.act.** { *; }
-keep class flash.mobile.news.valueb.bean.** { *; }
#这是你定义的实体类

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod




#  忽略双进程守护
#    -keep class com.marswin89.marsdaemon.** { *; }
#    -libraryjars libs/LibMarsdaemon
#
#      -libraryjars ../LibMarsdaemon

-keep class com.marswin89.marsdaemon.NativeDaemonBase{*;}
-keep class com.marswin89.marsdaemon.nativ.NativeDaemonAPI20{*;}
-keep class com.marswin89.marsdaemon.nativ.NativeDaemonAPI21{*;}
-keep class com.marswin89.marsdaemon.DaemonApplication{*;}
-keep class com.marswin89.marsdaemon.DaemonClient{*;}
-keepattributes Exceptions,InnerClasses,...
-keep class com.marswin89.marsdaemon.DaemonConfigurations{*;}
-keep class com.marswin89.marsdaemon.DaemonConfigurations$*{*;}



##StepSerVice当中注解部分的不混淆
-keep class com.booster.memory.green.master.listviewscroll.myutils.widget.customcount.countstep.stepdatabase.** {*; }
## 数据库的枚举
-keep class com.litesuits.orm.db.enums.** { *; }
# 数据库忽略
-keep class com.litesuits.orm.** { *; }
-keep class flash.mobile.news.valueb.view.** { *; }
# 适配悬浮窗的 忽略
-keep class  com.booster.memory.green.master.listviewscroll.permission.floatwindow.** { *;}







#小面是套用公用的不用混淆的东西

#--------告诉ProGuard不要警告找不到包名.**这个包里面的类的相关引用
-dontwarn  com.hbyc.wxn.**
#--------对这个包里面的所有类和所有方法不进行混淆
-keep class com.hbyc.wxn.** { *;}

#-keep libraryjars ../zipeiyi_tools

# 指定代码的压缩级别
-optimizationpasses 5
-dontusemixedcaseclassnames

# 是否混淆第三方jar
-dontskipnonpubliclibraryclasses
-dontpreverify
-keepattributes SourceFile,LineNumberTable
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#-flattenpackagehierarchy a.b.c
#-repackageclasses com.a.b.c

# v4和v7包
-dontwarn android.support.**
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**



#-libraryjars ../commonTools/libs/commons-httpclient-3.1.jar
-dontwarn org.apache.commons.httpclient.**
-keep class org.apache.commons.httpclient.** { *; }
-keep public class * extends org.apache.commons.httpclient.**

#-keep public class * extends android.app.Fragment

#-libraryjars libs/gif-drawable.jar
-dontwarn pl.droidsonroids.gif.**
-keep class pl.droidsonroids.gif.** { *; }
-keep public class * extends pl.droidsonroids.gif.**


#-libraryjars libs/httpmime-4.1.3.jar
-dontwarn org.apache.http.entity.mime.**
-keep class org.apache.http.entity.mime.** { *; }
-keep public class * extends org.apache.http.entity.mime.**

#-libraryjars libs/google-gson-1.7.1.jar
-dontwarn com.google.gson.**
-keep class com.google.gson.** { *; }
-keep public class * extends com.google.gson.**

#-libraryjars libs/iProtectZPY.jar
-dontwarn org.apache.http.entity.mime.**
-keep class org.apache.http.entity.mime.** { *; }
-keep public class * extends org.apache.http.entity.mime.**

#-libraryjars libs/iProtectZPY.jar
-dontwarn cn.cloudcore.iprotect.plugin.**
-keep class cn.cloudcore.iprotect.plugin.** { *; }
-keep public class * extends cn.cloudcore.iprotect.plugin.**

#-libraryjars libs/jpush-sdk-release2.1.7.jar
-dontwarn cn.jpush.android.**
-keep class cn.jpush.android.** { *; }
-keep public class * extends cn.jpush.android.**

#-libraryjars libs/ksoap2-android-assembly-2.5.2-jar-with-dependencies.jar
-dontwarn org.kobjects.**
-keep class org.kobjects.** { *; }
-keep public class * extends org.kobjects.**
-dontwarn org.ksoap2.**
-keep class org.ksoap2.** { *; }
-keep public class * extends org.ksoap2.**

-dontwarn org.kxml2.**
-keep class org.kxml2.** { *; }
-keep public class * extends org.kxml2.**

-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.** { *; }
-keep public class * extends org.xmlpull.v1.**
#-keep class org.apache.httpcomponents:httpclient:4.5

#-libapshare.jar
-dontwarn com.alipay.share.sdk.**
-keep class com.alipay.share.sdk.** { *; }
-keep public class * extends com.alipay.share.sdk.**

######################### umeng分享sdk ##########################################
#mta-sdk-1.6.2.jar
-dontwarn com.tencent.stat.**
-keep class com.tencent.stat.** { *; }
-keep public class * extends com.tencent.stat.**

#open_sdk_r5756.jar
-dontwarn com.tencent.**
-keep class com.tencent.** { *; }
-keep public class * extends com.tencent.**

-dontwarn com.tencent.connect.**
-keep class com.tencent.connect.** { *; }
-keep public class * extends com.tencent.connect.**

-dontwarn com.tencent.map.**
-keep class com.tencent.map.** { *; }
-keep public class * extends com.tencent.map.**

-dontwarn com.tencent.open.**
-keep class com.tencent.open.** { *; }
-keep public class * extends com.tencent.open.**

-dontwarn com.tencent.qqconnect.dataprovider.datatype.**
-keep class com.tencent.qqconnect.dataprovider.datatype.** { *; }
-keep public class * extends com.tencent.qqconnect.dataprovider.datatype.**

-dontwarn com.tencent.tauth.**
-keep class com.tencent.tauth.** { *; }
-keep public class * extends com.tencent.tauth.**

#-libraryjars libs/SocialSDK_QQZone_3.jar
-dontwarn com.umeng.socialize.**
-keep class com.umeng.socialize.** { *; }
-keep public class * extends com.umeng.socialize.**

#weiboSDKCore_3.1.4.jar
-dontwarn com.sina.sso.**
-keep class  com.sina.sso.**{*;}
-keep public class * extends com.sina.sso.**

-dontwarn com.sina.weibo.sdk.**
-keep class com.sina.weibo.sdk.**{*;}
-keep public class * extends com.sina.weibo.sdk.**

#-libraryjars libs/SocialSDK_scrshot.jar
-dontwarn com.umeng.scrshot.**
-keep class com.umeng.scrshot.** { *; }
-keep public class * extends com.umeng.scrshot.**



#-libraryjars libs/SocialSDK_shake.jar
-dontwarn com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.sensor.** { *; }
-keep public class * extends com.umeng.socialize.sensor.**

#-libraryjars libs/SocialSDK_Sina.jar
-dontwarn com.sina.sso.**
-keep class com.sina.sso.** { *; }
-keep public class * extends com.sina.sso.**

-dontwarn com.umeng.socialize.sso.**
-keep class com.umeng.socialize.sso.** { *; }
-keep public class * extends com.umeng.socialize.sso.**

#-libraryjars libs/SocialSDK_sms.jar

#umeng_social_sdk.jar
-dontwarn com.umeng.socialize.**
-keep class  com.umeng.socialize.a.** { *; }

#-libraryjars libs/SocialSDK_tencentWB_1.jar
-dontwarn com.tencent.weibo.sdk.android.**
-keep class com.tencent.weibo.sdk.android.** { *; }
-keep public class * extends com.tencent.weibo.sdk.android.**


#-libraryjars libs/SocialSDK_WeiXin_1.jar
-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.** { *; }
-keep public class * extends com.tencent.mm.**

#-libraryjars libs/SocialSDK_WeiXin_2.jar
-dontwarn com.umeng.socialize.**
-keep class com.umeng.socialize.** { *; }
-keep public class * extends com.umeng.socialize.**

-dontwarn com.umeng.socialize.tumblr.**
-keep class  com.umeng.socialize.tumblr.** { *; }
-keep public class * extends com.umeng.socialize.tumblr.**

#SocialSDK_tencentWB_1.jar
-dontwarn com.umeng.weibo.sdk.android.**
-keep class  com.umeng.weibo.sdk.android.** { *; }
-keep public class * extends com.umeng.weibo.sdk.android.**

#SocialSDK_weixin_2.jar
-dontwarn com.umeng.socialize.weixin.**
-keep class  com.umeng.socialize.weixin.** { *; }
-keep public class * extends com.umeng.socialize.weixin.**



#-libraryjars libs/SocialSDK_whatsapp.jar
-dontwarn com.umeng.socialize.whatsapp.**
-keep class com.umeng.socialize.whatsapp.** { *; }
-keep public class * extends com.umeng.socialize.whatsapp.**



#-libraryjars libs/umeng_social_sdk.jar

#-libraryjars libs/umeng-analytics-v5.5.3.jar
-dontwarn com.umeng.analytics.**
-keep class com.umeng.analytics.** { *; }
-keep public class * extends com.umeng.analytics.**

#-libraryjars libs/xUtils-2.6.13.jar
-dontwarn com.lidroid.xutils.**
-keep class com.lidroid.xutils.** { *; }
-keep public class * extends com.lidroid.xutils.**


#-libraryjars libs/universal-image-lader-1.9.4-with-sources.jar
-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader.** { *; }
-keep public class * extends com.nostra13.universalimageloader.**

#ACRA specifics
# we need line numbers in our stack traces otherwise they are pretty useless
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# ACRA needs "annotations" so add this...
-keepattributes *Annotation*

# 保持自定义控件类不被混淆
#-keep class com.zpy.app.view.** {*;}
#-keep class com.zpy.app.gesturelock.** {*;}
#-keep class com.hbyc.wxn.view.** {*;}
#微信支付的2个类不混淆
-keep class com.zpy.app.wxapi.** {*;}

-keepclassmembers class * extends com.zpy.app.view.CircleView {
   public void *(android.view.View);
}
#-keep class **.R$* {*;}

# http client
-keep class org.apache.http.** {*; }

# 微信
#-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
#-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
# keep 所有的 javabean
-keep class com.zpy.app.bean.**{*;}
# keep 泛型
-keepattributes Signature

#-keep class org.apache.commons.lang.**{*;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**

# 友盟
#-keep class com.sina.**{*;}
-keep class com.umeng.socialize.sso.**{*;}
-keep class org.apache.commons.httpclient.**{*;}
-keep class com.google.gson.**{*;}

-keepattributes EnclosingMethod

-keepclassmembers class * extends com.zpy.app.request.GetZpyRequest{
            <fields>;
 }
##            <methods>;

-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}
 -dontskipnonpubliclibraryclassmembers
 -ignorewarnings
