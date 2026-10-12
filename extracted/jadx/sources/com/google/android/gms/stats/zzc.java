package com.google.android.gms.stats;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzc implements Runnable {
    private final /* synthetic */ WeakReference zzaej;

    zzc(WeakReference weakReference) {
        this.zzaej = weakReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WakeLock.HeldLock heldLock = (WakeLock.HeldLock) this.zzaej.get();
        if (heldLock != null) {
            heldLock.release(0);
        }
    }
}
