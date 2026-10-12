package com.tapjoy.internal;

import android.os.SystemClock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class im implements Runnable {
    private final long a = 300;

    public abstract boolean a();

    @Override // java.lang.Runnable
    public void run() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() + this.a;
        while (!a() && jElapsedRealtime - SystemClock.elapsedRealtime() > 0) {
            try {
                Thread.sleep(0L);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
