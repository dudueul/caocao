package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbb extends GooglePlayServicesUpdatedReceiver.Callback {
    private WeakReference<zzav> zziy;

    zzbb(zzav zzavVar) {
        this.zziy = new WeakReference<>(zzavVar);
    }

    @Override // com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.Callback
    public final void zzv() {
        zzav zzavVar = this.zziy.get();
        if (zzavVar == null) {
            return;
        }
        zzavVar.resume();
    }
}
