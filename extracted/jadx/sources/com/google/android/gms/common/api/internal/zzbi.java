package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbi implements Runnable {
    private final /* synthetic */ GoogleApiManager.zza zzkk;

    zzbi(GoogleApiManager.zza zzaVar) {
        this.zzkk = zzaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzkk.zzbj();
    }
}
