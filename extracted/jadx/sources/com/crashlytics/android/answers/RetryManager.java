package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.RetryState;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class RetryManager {
    private static final long NANOSECONDS_IN_MS = 1000000;
    long lastRetry;
    private RetryState retryState;

    public RetryManager(RetryState retryState) {
        if (retryState == null) {
            throw new NullPointerException("retryState must not be null");
        }
        this.retryState = retryState;
    }

    public boolean canRetry(long j) {
        return j - this.lastRetry >= NANOSECONDS_IN_MS * this.retryState.getRetryDelay();
    }

    public void recordRetry(long j) {
        this.lastRetry = j;
        this.retryState = this.retryState.nextRetryState();
    }

    public void reset() {
        this.lastRetry = 0L;
        this.retryState = this.retryState.initialRetryState();
    }
}
