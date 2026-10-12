package com.tapjoy.internal;

import android.app.Activity;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class gt {
    protected static gt a;

    public static void b(Activity activity) {
        if (a != null) {
            a.a(activity);
        }
    }

    public abstract void a(Activity activity);
}
