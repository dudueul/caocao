package com.tapjoy.internal;

import com.tapjoy.TJEarnedCurrencyListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJEarnedCurrencyListenerNative implements TJEarnedCurrencyListener {
    private final long a;

    private TJEarnedCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    @ft
    static Object create(long j) {
        return new TJEarnedCurrencyListenerNative(j);
    }

    private static native void onEarnedCurrencyNative(long j, String str, int i);

    @Override // com.tapjoy.TJEarnedCurrencyListener
    public void onEarnedCurrency(String str, int i) {
        onEarnedCurrencyNative(this.a, str, i);
    }
}
