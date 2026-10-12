package com.naver.glink.android.sdk.login.neoid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.naver.glink.android.sdk.ChannelCodes;
import java.util.Iterator;

/* JADX INFO: compiled from: DeviceAppInfo.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static final String a = a.class.getSimpleName();

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().startsWith(ChannelCodes.KOREAN);
    }

    public static boolean a(Context context, String str) {
        try {
            return (TextUtils.isEmpty(str) || context.getPackageManager().getLaunchIntentForPackage(str) == null) ? false : true;
        } catch (Exception e) {
            Log.d(a, "", e);
            return false;
        }
    }

    public static String b(Context context) {
        return ("Android" + Build.VERSION.RELEASE).replaceAll("\\s", "");
    }

    public static boolean b(Context context, String str) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(new Intent(str), 128).iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next();
        return true;
    }

    public static String c(Context context) {
        String str = ("Android/" + Build.VERSION.RELEASE).replaceAll("\\s", "") + " " + ("Model/" + Build.MODEL).replaceAll("\\s", "");
        String packageName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 448);
            return str + " " + String.format("%s/%s(%d,uid:%d%s)", packageName, packageInfo.versionName, Integer.valueOf(packageInfo.versionCode), Integer.valueOf(packageInfo.applicationInfo.uid), packageInfo.applicationInfo.loadDescription(packageManager) != null ? ",appId:" + ((Object) packageInfo.applicationInfo.loadDescription(packageManager)) : "").replaceAll("\\s", "") + " " + ("NeoIdSignInMod/" + NeoIdSdkManager.a()).replaceAll("\\s", "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(a, "not installed app : " + packageName, e);
            return str;
        } catch (Exception e2) {
            Log.e(a, "", e2);
            return str;
        }
    }

    public static String d(Context context) {
        String string = context.getResources().getConfiguration().locale.toString();
        return TextUtils.isEmpty(string) ? "ko_KR" : string;
    }

    public static String e(Context context) {
        String strG = g(context);
        if (strG == null) {
            return "";
        }
        try {
            return String.valueOf(Integer.parseInt(strG.substring(0, 3)));
        } catch (Exception e) {
            Log.e(a, "getMccString exception: " + e.getMessage());
            return "";
        }
    }

    public static String f(Context context) {
        String strG = g(context);
        if (strG == null) {
            return "";
        }
        try {
            return String.valueOf(Integer.parseInt(strG.substring(3)));
        } catch (Exception e) {
            Log.e(a, "getMccString exception: " + e.getMessage());
            return "";
        }
    }

    private static String g(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
    }
}
