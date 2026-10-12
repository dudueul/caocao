package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class df {

    @SuppressLint({"StaticFieldLeak"})
    private static df b = new df();
    public Context a;

    private df() {
    }

    public static df a() {
        return b;
    }
}
