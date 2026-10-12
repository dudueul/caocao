package com.tapjoy.internal;

import com.tapjoy.TJAwardCurrencyListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJAwardCurrencyListenerNative implements TJAwardCurrencyListener {
    private final long a;

    private TJAwardCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    @ft
    static Object create(long j) {
        return new TJAwardCurrencyListenerNative(j);
    }

    private static native void onAwardCurrencyResponseFailureNative(long j, String str);

    private static native void onAwardCurrencyResponseNative(long j, String str, int i);

    @Override // com.tapjoy.TJAwardCurrencyListener
    public void onAwardCurrencyResponse(String str, int i) {
        onAwardCurrencyResponseNative(this.a, str, i);
    }

    @Override // com.tapjoy.TJAwardCurrencyListener
    public void onAwardCurrencyResponseFailure(String str) {
        onAwardCurrencyResponseFailureNative(this.a, str);
    }
}
