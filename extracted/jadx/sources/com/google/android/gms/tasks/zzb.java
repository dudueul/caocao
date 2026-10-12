package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzafi;

    zzb(zza zzaVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.zzafi = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Void r2) {
        this.zzafi.onCanceled();
    }
}
