package com.unity3d.player;

import android.os.Build;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class m implements Thread.UncaughtExceptionHandler {
    private volatile Thread.UncaughtExceptionHandler a;

    m() {
    }

    final synchronized boolean a() {
        boolean z;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == this) {
            z = false;
        } else {
            this.a = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
            z = true;
        }
        return z;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        try {
            Error error = new Error(String.format("FATAL EXCEPTION [%s]\n", thread.getName()) + String.format("Unity version     : %s\n", "2018.2.16f1") + String.format("Device model      : %s %s\n", Build.MANUFACTURER, Build.MODEL) + String.format("Device fingerprint: %s\n", Build.FINGERPRINT));
            error.setStackTrace(new StackTraceElement[0]);
            error.initCause(th);
            this.a.uncaughtException(thread, error);
        } catch (Throwable th2) {
            this.a.uncaughtException(thread, th);
        }
    }
}
