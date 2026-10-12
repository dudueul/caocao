package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TimerOnce {
    private Runnable command;
    private CustomScheduledExecutor executor;
    private ILogger logger = AdjustFactory.getLogger();
    private String name;
    private ScheduledFuture waitingTask;

    public TimerOnce(Runnable runnable, String str) {
        this.name = str;
        this.executor = new CustomScheduledExecutor(str, true);
        this.command = runnable;
    }

    private void cancel(boolean z) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(z);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }

    public void cancel() {
        cancel(false);
    }

    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0L;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }

    public void startIn(long j) {
        cancel(false);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, Util.SecondsDisplayFormat.format(j / 1000.0d));
        this.waitingTask = this.executor.schedule(new Runnable() { // from class: com.adjust.sdk.TimerOnce.1
            @Override // java.lang.Runnable
            public void run() {
                TimerOnce.this.logger.verbose("%s fired", TimerOnce.this.name);
                TimerOnce.this.command.run();
                TimerOnce.this.waitingTask = null;
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    public void teardown() {
        cancel(true);
        this.executor = null;
    }
}
