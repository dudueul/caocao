package io.fabric.sdk.android.services.concurrency.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    private final int maxRetries;

    public DefaultRetryPolicy() {
        this(1);
    }

    public DefaultRetryPolicy(int i) {
        this.maxRetries = i;
    }

    @Override // io.fabric.sdk.android.services.concurrency.internal.RetryPolicy
    public boolean shouldRetry(int i, Throwable th) {
        return i < this.maxRetries;
    }
}
