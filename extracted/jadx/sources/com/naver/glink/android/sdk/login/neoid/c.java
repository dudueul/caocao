package com.naver.glink.android.sdk.login.neoid;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: NeoIdPreferenceManager.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    public static final String a = "ACCESS_TOKEN";
    public static final String b = "SNS_CODE";
    public static final String c = "JOINED";
    private static final String d = "NeoIdPreferenceData";

    public static String a(Context context) {
        SharedPreferences sharedPreferencesD = d(context);
        return sharedPreferencesD == null ? "" : sharedPreferencesD.getString(a, "");
    }

    public static void a(Context context, String str) {
        SharedPreferences sharedPreferencesD = d(context);
        if (sharedPreferencesD == null) {
            return;
        }
        sharedPreferencesD.edit().putString(a, str).apply();
    }

    public static void a(Context context, boolean z) {
        SharedPreferences sharedPreferencesD = d(context);
        if (sharedPreferencesD == null) {
            return;
        }
        sharedPreferencesD.edit().putBoolean(c, z).apply();
    }

    public static String b(Context context) {
        SharedPreferences sharedPreferencesD = d(context);
        return sharedPreferencesD == null ? "" : sharedPreferencesD.getString(b, "");
    }

    public static void b(Context context, String str) {
        SharedPreferences sharedPreferencesD = d(context);
        if (sharedPreferencesD == null) {
            return;
        }
        sharedPreferencesD.edit().putString(b, str).apply();
    }

    public static boolean c(Context context) {
        SharedPreferences sharedPreferencesD = d(context);
        if (sharedPreferencesD == null) {
            return false;
        }
        return sharedPreferencesD.getBoolean(c, false);
    }

    private static SharedPreferences d(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(d, 0);
    }
}
