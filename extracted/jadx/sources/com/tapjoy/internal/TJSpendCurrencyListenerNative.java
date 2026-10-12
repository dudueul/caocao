package com.tapjoy.internal;

import com.tapjoy.TJSpendCurrencyListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJSpendCurrencyListenerNative implements TJSpendCurrencyListener {
    private final long a;

    private TJSpendCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    @ft
    static Object create(long j) {
        return new TJSpendCurrencyListenerNative(j);
    }

    private static native void onSpendCurrencyResponseFailureNative(long j, String str);

    private static native void onSpendCurrencyResponseNative(long j, String str, int i);

    @Override // com.tapjoy.TJSpendCurrencyListener
    public void onSpendCurrencyResponse(String str, int i) {
        onSpendCurrencyResponseNative(this.a, str, i);
    }

    @Override // com.tapjoy.TJSpendCurrencyListener
    public void onSpendCurrencyResponseFailure(String str) {
        onSpendCurrencyResponseFailureNative(this.a, str);
    }
}
