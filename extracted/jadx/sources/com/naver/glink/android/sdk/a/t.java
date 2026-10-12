package com.naver.glink.android.sdk.a;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/* JADX INFO: compiled from: SingleOnClickListener.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class t implements View.OnClickListener {
    private static final String a = t.class.getSimpleName();
    private long b;
    private long c;

    public t() {
        this.c = 1000L;
    }

    public t(int i) {
        this.c = 1000L;
        this.c = i;
    }

    public abstract void a(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - this.b < this.c) {
            return;
        }
        this.b = SystemClock.elapsedRealtime();
        try {
            a(view);
        } catch (Exception e) {
            Log.d(a, "", e);
        }
    }
}
