package com.naver.glink.android.sdk.ui.widget.photoview;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: Compat.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static final int a = 16;

    public static int a(int i) {
        return Build.VERSION.SDK_INT >= 11 ? c(i) : b(i);
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            b(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }
    }

    @TargetApi(5)
    private static int b(int i) {
        return (65280 & i) >> 8;
    }

    @TargetApi(16)
    private static void b(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    @TargetApi(11)
    private static int c(int i) {
        return (65280 & i) >> 8;
    }
}
