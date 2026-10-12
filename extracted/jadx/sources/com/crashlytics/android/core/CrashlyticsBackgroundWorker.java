package com.crashlytics.android.core;

import android.os.Looper;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CrashlyticsBackgroundWorker {
    private final ExecutorService executorService;

    public CrashlyticsBackgroundWorker(ExecutorService executorService) {
        this.executorService = executorService;
    }

    Future<?> submit(final Runnable runnable) {
        try {
            return this.executorService.submit(new Runnable() { // from class: com.crashlytics.android.core.CrashlyticsBackgroundWorker.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        Fabric.getLogger().e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    <T> Future<T> submit(final Callable<T> callable) {
        try {
            return this.executorService.submit(new Callable<T>() { // from class: com.crashlytics.android.core.CrashlyticsBackgroundWorker.2
                @Override // java.util.concurrent.Callable
                public T call() throws Exception {
                    try {
                        return (T) callable.call();
                    } catch (Exception e) {
                        Fabric.getLogger().e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                        return null;
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    <T> T submitAndWait(Callable<T> callable) {
        T t = null;
        try {
            t = Looper.getMainLooper() == Looper.myLooper() ? this.executorService.submit(callable).get(4L, TimeUnit.SECONDS) : this.executorService.submit(callable).get();
        } catch (RejectedExecutionException e) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
        } catch (Exception e2) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Failed to execute task.", e2);
        }
        return t;
    }
}
