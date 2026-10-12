package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzas extends Handler {
    private final /* synthetic */ zzar zzcn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzas(zzar zzarVar, Looper looper) {
        super(looper);
        this.zzcn = zzarVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzcn.zzb(message);
    }
}
