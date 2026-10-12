package com.google.android.gms.common.util;

import android.os.Looper;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ThreadUtils {
    private ThreadUtils() {
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
