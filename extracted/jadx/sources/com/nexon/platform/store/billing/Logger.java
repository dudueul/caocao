package com.nexon.platform.store.billing;

import com.nexon.core.log.ToyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Logger {
    private static Logger logger;

    public static void d(String str, String str2) {
        ToyLog.dd(str + ", " + str2);
    }

    public static void e(String str, String str2) {
        ToyLog.e(str + ", " + str2);
    }

    public static synchronized Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public static void v(String str, String str2) {
        ToyLog.dd(str + ", " + str2);
    }

    public static void w(String str, String str2) {
        ToyLog.d(str + ", " + str2);
    }
}
