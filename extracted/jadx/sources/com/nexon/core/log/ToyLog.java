package com.nexon.core.log;

import android.content.pm.PackageManager;
import com.nexon.core.log.constant.NXToyLogLevel;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ToyLog {
    public static void d(String str) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.DEBUG, str, null);
    }

    public static void d(String str, Object... objArr) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.DEBUG, str, objArr);
    }

    public static void dd(String str) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.DEVELOP, str, null);
    }

    public static void dd(String str, Object... objArr) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.DEVELOP, str, objArr);
    }

    public static void e(String str) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.ERROR, str, null);
    }

    public static void e(String str, Object... objArr) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.ERROR, str, objArr);
    }

    public static void i(String str) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.INFO, str, null);
    }

    public static void i(String str, Object... objArr) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.INFO, str, objArr);
    }

    public static void setEnableSendServer(boolean z) {
        NXToyLogManager.getInstance().setEnableSendServer(z);
    }

    public static void setupLogcatLogger(PackageManager packageManager) {
        NXToyLogManager.getInstance().setupLogcatLogger(packageManager);
    }

    public static void v(String str) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.VERBOSE, str, null);
    }

    public static void v(String str, Object... objArr) {
        NXToyLogManager.getInstance().log(NXToyLogLevel.VERBOSE, str, objArr);
    }
}
