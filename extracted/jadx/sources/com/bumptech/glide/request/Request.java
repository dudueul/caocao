package com.bumptech.glide.request;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Request {
    void begin();

    void clear();

    boolean isCancelled();

    boolean isComplete();

    boolean isFailed();

    boolean isPaused();

    boolean isResourceSet();

    boolean isRunning();

    void pause();

    void recycle();
}
