package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzci implements Runnable {
    private final /* synthetic */ Result zzmk;
    private final /* synthetic */ zzch zzml;

    zzci(zzch zzchVar, Result result) {
        this.zzml = zzchVar;
        this.zzmk = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        try {
            BasePendingResult.zzez.set(true);
            this.zzml.zzmi.sendMessage(this.zzml.zzmi.obtainMessage(0, this.zzml.zzmd.onSuccess(this.zzmk)));
        } catch (RuntimeException e) {
            this.zzml.zzmi.sendMessage(this.zzml.zzmi.obtainMessage(1, e));
        } finally {
            BasePendingResult.zzez.set(false);
            zzch zzchVar = this.zzml;
            zzch.zzb(this.zzmk);
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzml.zzfc.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzml);
            }
        }
    }
}
