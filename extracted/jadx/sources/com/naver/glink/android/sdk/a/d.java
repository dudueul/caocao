package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CustomTabsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d {
    static final String a = "com.android.chrome";
    static final String b = "com.chrome.beta";
    static final String c = "com.chrome.dev";
    static final String d = "com.google.android.apps.chrome";
    private static final String e = "CustomTabsHelper";
    private static final String f = "android.support.customtabs.action.CustomTabsService";
    private static String g;

    private d() {
    }

    public static String a(Context context) {
        if (g != null) {
            return g;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        String str = resolveInfoResolveActivity != null ? resolveInfoResolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction("android.support.customtabs.action.CustomTabsService");
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            g = null;
        } else if (arrayList.size() == 1) {
            g = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(str) && !a(context, intent) && arrayList.contains(str)) {
            g = str;
        } else if (arrayList.contains(a)) {
            g = a;
        } else if (arrayList.contains(b)) {
            g = b;
        } else if (arrayList.contains(c)) {
            g = c;
        } else if (arrayList.contains(d)) {
            g = d;
        }
        return g;
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() == 0) {
                return false;
            }
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                IntentFilter intentFilter = resolveInfo.filter;
                if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                    return true;
                }
            }
        } catch (RuntimeException e2) {
            Log.e(e, "Runtime exception while getting specialized handlers");
        }
        return false;
    }

    public static String[] a() {
        return new String[]{"", a, b, c, d};
    }
}
