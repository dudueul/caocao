package com.nexon.nexonanalyticssdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxLogcat {
    static final String TAG = "[NXAnalytics]";

    NxLogcat() {
    }

    public static String buildLogMsg(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        return "[] " + str;
    }

    public static final void d(String str) {
    }

    public static final void e(String str) {
    }

    public static final void i(String str) {
    }

    public static final void v(String str) {
    }

    public static final void w(String str) {
    }
}
