package com.naver.glink.android.sdk.a.a;

import android.util.Log;
import com.squareup.otto.Bus;

/* JADX INFO: compiled from: BusProvider.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class b {
    private static final String a = b.class.getSimpleName();
    private static final Bus b = new Bus();

    public static void a(Object obj) {
        try {
            b.register(obj);
        } catch (RuntimeException e) {
            Log.d(a, "", e);
        }
    }

    public static void b(Object obj) {
        try {
            b.unregister(obj);
        } catch (RuntimeException e) {
            Log.d(a, "", e);
        }
    }

    public static void c(Object obj) {
        try {
            b.post(obj);
        } catch (RuntimeException e) {
            Log.d(a, "", e);
        }
    }
}
