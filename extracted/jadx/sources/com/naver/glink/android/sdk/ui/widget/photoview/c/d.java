package com.naver.glink.android.sdk.ui.widget.photoview.c;

import android.content.Context;
import android.os.Build;

/* JADX INFO: compiled from: ScrollerProxy.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class d {
    public static d a(Context context) {
        if (Build.VERSION.SDK_INT < 9) {
            return new c(context);
        }
        return Build.VERSION.SDK_INT < 14 ? new a(context) : new b(context);
    }

    public abstract void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract boolean b();

    public abstract int c();

    public abstract int d();
}
