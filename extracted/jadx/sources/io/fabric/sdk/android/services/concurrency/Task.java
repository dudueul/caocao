package io.fabric.sdk.android.services.concurrency;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Task {
    Throwable getError();

    boolean isFinished();

    void setError(Throwable th);

    void setFinished(boolean z);
}
