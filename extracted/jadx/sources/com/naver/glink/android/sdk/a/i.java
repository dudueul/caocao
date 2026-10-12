package com.naver.glink.android.sdk.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import android.util.Log;
import com.naver.plug.android.core.api.request.RequestHelper;
import java.util.List;

/* JADX INFO: compiled from: IntentHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class i {
    private static final String a = "IntentHelper";

    private static void a(Activity activity, String str) {
        CustomTabsIntent customTabsIntentBuild = new CustomTabsIntent.Builder().build();
        String strA = d.a(activity);
        if (!TextUtils.isEmpty(strA)) {
            customTabsIntentBuild.intent.setPackage(strA);
        }
        a(activity, customTabsIntentBuild, Uri.parse(str));
    }

    private static void a(Context context, CustomTabsIntent customTabsIntent, Uri uri) {
        if (customTabsIntent == null) {
            return;
        }
        customTabsIntent.intent.setData(uri);
        context.startActivity(customTabsIntent.intent);
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("intent://")) {
            try {
                b(context, Intent.parseUri(str, 1));
                return;
            } catch (Exception e) {
                Log.d(a, e.toString());
                return;
            }
        }
        if (b(context, str)) {
            a((Activity) context, str);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(y.a(str));
        b(context, intent);
    }

    public static void a(Context context, String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (i != -1 && com.naver.glink.android.sdk.d.l()) {
            sb.append("/");
            sb.append(i);
            sb.append("?langCode=");
            sb.append(RequestHelper.getSystemLangCode());
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        if (com.naver.glink.android.sdk.d.h(context)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
        if (com.naver.glink.android.sdk.d.h(context)) {
            com.naver.glink.android.sdk.d.a(context, true);
        }
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str2);
        context.startActivity(Intent.createChooser(intent, str));
    }

    public static boolean a(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities;
        return (context == null || intent == null || (listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536)) == null || listQueryIntentActivities.size() <= 0) ? false : true;
    }

    private static void b(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        if (a(context, intent)) {
            context.startActivity(intent);
            return;
        }
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", y.a("market://details?id=" + intent.getPackage())));
        } catch (ActivityNotFoundException e) {
            Log.d(a, e.toString());
        }
    }

    private static boolean b(Context context, String str) {
        return (context instanceof Activity) && (str.startsWith("http://") || str.startsWith("https://"));
    }
}
