package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzba extends Handler {
    private final /* synthetic */ zzav zzit;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzba(zzav zzavVar, Looper looper) {
        super(looper);
        this.zzit = zzavVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zzit.zzay();
                break;
            case 2:
                this.zzit.resume();
                break;
            default:
                Log.w("GoogleApiClientImpl", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                break;
        }
    }
}
