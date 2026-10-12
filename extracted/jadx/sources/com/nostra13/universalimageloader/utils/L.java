package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class L {
    private static volatile boolean DISABLED = false;
    private static final String LOG_FORMAT = "%1$s\n%2$s";

    private L() {
    }

    public static void d(String str, Object... objArr) {
        log(3, null, str, objArr);
    }

    public static void disableLogging() {
        DISABLED = true;
    }

    public static void e(String str, Object... objArr) {
        log(6, null, str, objArr);
    }

    public static void e(Throwable th) {
        log(6, th, null, new Object[0]);
    }

    public static void e(Throwable th, String str, Object... objArr) {
        log(6, th, str, objArr);
    }

    public static void enableLogging() {
        DISABLED = false;
    }

    public static void i(String str, Object... objArr) {
        log(4, null, str, objArr);
    }

    private static void log(int i, Throwable th, String str, Object... objArr) {
        String str2;
        if (DISABLED) {
            return;
        }
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        if (th == null) {
            str2 = str;
        } else {
            str2 = String.format(LOG_FORMAT, str == null ? th.getMessage() : str, Log.getStackTraceString(th));
        }
        Log.println(i, ImageLoader.TAG, str2);
    }

    public static void w(String str, Object... objArr) {
        log(5, null, str, objArr);
    }
}
