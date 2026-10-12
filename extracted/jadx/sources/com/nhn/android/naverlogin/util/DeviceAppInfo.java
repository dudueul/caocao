package com.nhn.android.naverlogin.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.naver.glink.android.sdk.ChannelCodes;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DeviceAppInfo {
    private static final String TAG = "NaverLoginOAuth|DeviceAppInfo";
    private static final DeviceAppInfo mInstance = new DeviceAppInfo();

    private DeviceAppInfo() {
    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "NAVER";
        }
    }

    public static DeviceAppInfo getBaseInstance() {
        return mInstance;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getPrimaryNaverOAuth2ndAppPackageName(Context context) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(OAuthLoginDefine.ACTION_OAUTH_LOGIN_2NDAPP), 128);
            for (String str : new String[]{"com.nhn.android.appstore"}) {
                for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                    if (OAuthLoginDefine.DEVELOPER_VERSION) {
                        Log.d(TAG, "package name:" + resolveInfo.activityInfo.packageName + ", " + resolveInfo.activityInfo.name);
                    }
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        return resolveInfo.activityInfo.packageName;
                    }
                }
            }
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "no app assinged in order-list. package name:" + listQueryIntentActivities.get(0).activityInfo.packageName + ", " + listQueryIntentActivities.get(0).activityInfo.name);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean isAppExist(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (context.getPackageManager().getLaunchIntentForPackage(str) != null) {
                return true;
            }
            Log.i(TAG, String.valueOf(str) + " is not installed.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isIntentFilterExist(Context context, String str) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(new Intent(str), 128).iterator();
        if (!it.hasNext()) {
            return false;
        }
        ResolveInfo next = it.next();
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "intent filter name:" + str);
            Log.d(TAG, "package name:" + next.activityInfo.packageName + ", " + next.activityInfo.name);
        }
        return true;
    }

    public String getLocaleString(Context context) {
        String string = "ko_KR";
        try {
            string = context.getResources().getConfiguration().locale.toString();
            if (TextUtils.isEmpty(string)) {
                return "ko_KR";
            }
        } catch (Exception e) {
        }
        return string;
    }

    public String getUserAgent(Context context) {
        String str = String.valueOf(("Android/" + Build.VERSION.RELEASE).replaceAll("\\s", "")) + " " + ("Model/" + Build.MODEL).replaceAll("\\s", "");
        String packageName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 448);
            return String.valueOf(str) + " " + String.format("%s/%s(%d,uid:%d%s)", packageName, packageInfo.versionName, Integer.valueOf(packageInfo.versionCode), Integer.valueOf(packageInfo.applicationInfo.uid), packageInfo.applicationInfo.loadDescription(packageManager) != null ? ",appId:" + ((Object) packageInfo.applicationInfo.loadDescription(packageManager)) : "").replaceAll("\\s", "") + " " + ("OAuthLoginMod/" + OAuthLogin.getVersion()).replaceAll("\\s", "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "not installed app : " + packageName);
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public boolean isKorean(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().startsWith(ChannelCodes.KOREAN);
    }
}
