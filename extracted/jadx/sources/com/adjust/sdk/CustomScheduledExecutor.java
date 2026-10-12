package com.adjust.sdk;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class CustomScheduledExecutor extends ScheduledThreadPoolExecutor {

    private static class RunnableWrapper implements Runnable {
        private Runnable runnable;

        RunnableWrapper(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.runnable.run();
            } catch (Throwable th) {
                AdjustFactory.getLogger().error("Runnable error [%s] of type [%s]", th.getMessage(), th.getClass().getCanonicalName());
            }
        }
    }

    public CustomScheduledExecutor(final String str, boolean z) {
        super(1, new ThreadFactory() { // from class: com.adjust.sdk.CustomScheduledExecutor.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread threadNewThread = Executors.defaultThreadFactory().newThread(runnable);
                threadNewThread.setPriority(1);
                threadNewThread.setName(Constants.THREAD_PREFIX + threadNewThread.getName() + "-" + str);
                threadNewThread.setDaemon(true);
                threadNewThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.adjust.sdk.CustomScheduledExecutor.1.1
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread, Throwable th) {
                        AdjustFactory.getLogger().error("Thread [%s] with error [%s]", thread.getName(), th.getMessage());
                    }
                });
                return threadNewThread;
            }
        }, new RejectedExecutionHandler() { // from class: com.adjust.sdk.CustomScheduledExecutor.2
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                AdjustFactory.getLogger().warn("Runnable [%s] rejected from [%s] ", runnable.toString(), str);
            }
        });
        if (z) {
            return;
        }
        setKeepAliveTime(10L, TimeUnit.MILLISECONDS);
        allowCoreThreadTimeOut(true);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return super.schedule(new RunnableWrapper(runnable), j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return super.scheduleWithFixedDelay(new RunnableWrapper(runnable), j, j2, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return super.submit(new RunnableWrapper(runnable));
    }
}
