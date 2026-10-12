package io.fabric.sdk.android.services.concurrency;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface PriorityProvider<T> extends Comparable<T> {
    Priority getPriority();
}
