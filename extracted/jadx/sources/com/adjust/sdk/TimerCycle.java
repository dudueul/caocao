package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TimerCycle {
    private Runnable command;
    private long cycleDelay;
    private CustomScheduledExecutor executor;
    private long initialDelay;
    private boolean isPaused = true;
    private ILogger logger = AdjustFactory.getLogger();
    private String name;
    private ScheduledFuture waitingTask;

    public TimerCycle(Runnable runnable, long j, long j2, String str) {
        this.executor = new CustomScheduledExecutor(str, true);
        this.name = str;
        this.command = runnable;
        this.initialDelay = j;
        this.cycleDelay = j2;
        this.logger.verbose("%s configured to fire after %s seconds of starting and cycles every %s seconds", str, Util.SecondsDisplayFormat.format(j / 1000.0d), Util.SecondsDisplayFormat.format(j2 / 1000.0d));
    }

    private void cancel(boolean z) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(z);
        }
        this.waitingTask = null;
    }

    public void start() {
        if (!this.isPaused) {
            this.logger.verbose("%s is already started", this.name);
            return;
        }
        this.logger.verbose("%s starting", this.name);
        this.waitingTask = this.executor.scheduleWithFixedDelay(new Runnable() { // from class: com.adjust.sdk.TimerCycle.1
            @Override // java.lang.Runnable
            public void run() {
                TimerCycle.this.logger.verbose("%s fired", TimerCycle.this.name);
                TimerCycle.this.command.run();
            }
        }, this.initialDelay, this.cycleDelay, TimeUnit.MILLISECONDS);
        this.isPaused = false;
    }

    public void suspend() {
        if (this.isPaused) {
            this.logger.verbose("%s is already suspended", this.name);
            return;
        }
        this.initialDelay = this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
        this.waitingTask.cancel(false);
        this.logger.verbose("%s suspended with %s seconds left", this.name, Util.SecondsDisplayFormat.format(this.initialDelay / 1000.0d));
        this.isPaused = true;
    }

    public void teardown() {
        cancel(true);
        if (this.executor != null) {
            try {
                this.executor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        this.executor = null;
    }
}
