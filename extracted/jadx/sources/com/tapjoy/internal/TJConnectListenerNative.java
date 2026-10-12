package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJConnectListenerNative implements TJConnectListener {
    private final long a;

    private TJConnectListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    @ft
    static Object create(long j) {
        return new TJConnectListenerNative(j);
    }

    private static native void onConnectFailureNative(long j);

    private static native void onConnectSuccessNative(long j);

    @Override // com.tapjoy.TJConnectListener
    public void onConnectFailure() {
        onConnectFailureNative(this.a);
    }

    @Override // com.tapjoy.TJConnectListener
    public void onConnectSuccess() {
        onConnectSuccessNative(this.a);
    }
}
