# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/ThomasColligan/Library/Android/sdk/tools/proguard/proguard-android.txt
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
-dontoptimize
-dontshrink
-dontpreverify

-keepattributes InnerClasses,EnclosingMethod

-keep class javax.** { *; }
-dontwarn javax.**
-keep class org.** { *; }
-dontwarn org.**
-keep class com.facebook.** { *; }
-dontwarn com.facebook.**
-keep class com.fasterxml.** { *; }
-dontwarn com.fasterxml.**
-keep class android.** { *; }
-dontwarn android.**
-keep class sun.** { *; }
-dontwarn sun.**
