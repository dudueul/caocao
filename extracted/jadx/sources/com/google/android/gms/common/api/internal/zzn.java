package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzn extends GooglePlayServicesUpdatedReceiver.Callback {
    private final /* synthetic */ Dialog zzex;
    private final /* synthetic */ zzm zzey;

    zzn(zzm zzmVar, Dialog dialog) {
        this.zzey = zzmVar;
        this.zzex = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.Callback
    public final void zzv() {
        this.zzey.zzew.zzt();
        if (this.zzex.isShowing()) {
            this.zzex.dismiss();
        }
    }
}
