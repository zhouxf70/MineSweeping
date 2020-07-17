package com.example.zhouxinfan.minesweeping.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.util.Collection;

public class Util {

    //implementation 'com.squareup.okhttp3:okhttp:3.10.0'
    //implementation 'com.github.zhaokaiqiang.klog:library:1.6.0'
    //implementation 'com.google.code.gson:gson:2.8.5'
    //implementation 'com.android.support:recyclerview-v7:28.0.0'

    //Butter Knife
    //implementation 'com.jakewharton:butterknife:8.8.0'
    //annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.0'

    public static boolean isDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean notEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean notEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

}
