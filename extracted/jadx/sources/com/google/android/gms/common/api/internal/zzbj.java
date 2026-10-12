package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbj implements Runnable {
    private final /* synthetic */ GoogleApiManager.zza zzkk;

    zzbj(GoogleApiManager.zza zzaVar) {
        this.zzkk = zzaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzkk.zzbk();
    }
}
