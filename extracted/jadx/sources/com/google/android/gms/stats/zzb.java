package com.google.android.gms.stats;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb implements Runnable {
    private final /* synthetic */ WakeLock zzaei;

    zzb(WakeLock wakeLock) {
        this.zzaei = wakeLock;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaei.zzn(0);
    }
}
