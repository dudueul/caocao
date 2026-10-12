package com.tapjoy.internal;

import android.os.SystemClock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fi {
    public static final fi a = new fi(-1);
    public final long b;
    public long c;

    public fi() {
        this.b = 3600000L;
        try {
            this.c = SystemClock.elapsedRealtime() - 3600000;
        } catch (NullPointerException e) {
            this.c = -1L;
        }
    }

    public fi(long j) {
        this.b = j;
        this.c = SystemClock.elapsedRealtime();
    }

    public final boolean a() {
        try {
            return SystemClock.elapsedRealtime() - this.c > this.b;
        } catch (NullPointerException e) {
            return true;
        }
    }

    public final boolean a(long j) {
        try {
            return (SystemClock.elapsedRealtime() - this.c) + j > this.b;
        } catch (NullPointerException e) {
            return true;
        }
    }
}
