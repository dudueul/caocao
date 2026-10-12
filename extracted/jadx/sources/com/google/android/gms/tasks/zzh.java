package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzh implements Runnable {
    private final /* synthetic */ zzg zzafr;

    zzh(zzg zzgVar) {
        this.zzafr = zzgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzafr.mLock) {
            if (this.zzafr.zzafq != null) {
                this.zzafr.zzafq.onCanceled();
            }
        }
    }
}
