package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class gc {
    private static final gc a;
    private static gc b;

    static {
        gd gdVar = new gd();
        a = gdVar;
        b = gdVar;
    }

    gc() {
    }

    static gc a() {
        return b;
    }

    public abstract Object a(Context context, String str, TJPlacementListener tJPlacementListener);
}
