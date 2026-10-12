package com.tapjoy.internal;

import com.tapjoy.TJVideoListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJVideoListenerNative implements TJVideoListener {
    private final long a;

    private TJVideoListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    @ft
    static Object create(long j) {
        return new TJVideoListenerNative(j);
    }

    private static native void onVideoCompleteNative(long j);

    private static native void onVideoErrorNative(long j, int i);

    private static native void onVideoStartNative(long j);

    @Override // com.tapjoy.TJVideoListener
    public void onVideoComplete() {
        onVideoCompleteNative(this.a);
    }

    @Override // com.tapjoy.TJVideoListener
    public void onVideoError(int i) {
        onVideoErrorNative(this.a, i);
    }

    @Override // com.tapjoy.TJVideoListener
    public void onVideoStart() {
        onVideoStartNative(this.a);
    }
}
