package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzs implements Runnable {
    private final /* synthetic */ zzr zzgc;

    zzs(zzr zzrVar) {
        this.zzgc = zzrVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }
}
