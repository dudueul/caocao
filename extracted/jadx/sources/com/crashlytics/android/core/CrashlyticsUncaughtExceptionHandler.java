package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);

    interface CrashListener {
        void onUncaughtException(Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.crashListener = crashListener;
        this.defaultHandler = uncaughtExceptionHandler;
    }

    boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.isHandlingException.set(true);
        try {
            this.crashListener.onUncaughtException(thread, th);
        } catch (Exception e) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
        } finally {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
    }
}
