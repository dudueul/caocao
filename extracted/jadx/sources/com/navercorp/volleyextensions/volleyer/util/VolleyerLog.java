package com.navercorp.volleyextensions.volleyer.util;

import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VolleyerLog {
    public static final int LEVEL_PRIORITY = 3;
    public static final String TAG = "Volleyer";

    private static String buildMessage(String str, Object... objArr) {
        return String.format(str, objArr);
    }

    public static void debug(String str, Object... objArr) {
        if (isLoggable()) {
            Log.d(TAG, buildMessage(str, objArr));
        }
    }

    public static void debug(Throwable th, String str, Object... objArr) {
        if (isLoggable()) {
            Log.d(TAG, buildMessage(str, objArr), th);
        }
    }

    public static void error(String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr));
    }

    public static void error(Throwable th, String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr), th);
    }

    public static void info(String str, Object... objArr) {
        Log.i(TAG, buildMessage(str, objArr));
    }

    public static void info(Throwable th, String str, Object... objArr) {
        Log.i(TAG, buildMessage(str, objArr), th);
    }

    private static boolean isLoggable() {
        return Log.isLoggable(TAG, 3);
    }

    public static void warn(String str, Object... objArr) {
        Log.w(TAG, buildMessage(str, objArr));
    }

    public static void warn(Throwable th, String str, Object... objArr) {
        Log.w(TAG, buildMessage(str, objArr), th);
    }
}
