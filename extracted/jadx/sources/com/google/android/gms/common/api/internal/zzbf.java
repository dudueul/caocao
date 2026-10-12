package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbf extends Handler {
    private final /* synthetic */ zzbd zzjh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbf(zzbd zzbdVar, Looper looper) {
        super(looper);
        this.zzjh = zzbdVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ((zzbe) message.obj).zzc(this.zzjh);
                return;
            case 2:
                throw ((RuntimeException) message.obj);
            default:
                Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                return;
        }
    }
}
