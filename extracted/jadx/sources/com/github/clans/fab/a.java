package com.github.clans.fab;

import android.content.Context;
import android.os.Build;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class a {
    private a() {
    }

    static int a(Context context, float f) {
        return Math.round(context.getResources().getDisplayMetrics().density * f);
    }

    static boolean a() {
        return Build.VERSION.SDK_INT >= 16;
    }

    static boolean b() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
