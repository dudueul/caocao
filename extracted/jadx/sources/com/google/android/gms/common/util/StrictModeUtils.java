package com.google.android.gms.common.util;

import android.os.StrictMode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StrictModeUtils {
    public static StrictMode.ThreadPolicy setDynamiteThreadPolicy() {
        StrictMode.noteSlowCall("gcore.dynamite");
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        return threadPolicy;
    }
}
