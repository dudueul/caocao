package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zze implements Runnable {
    private final /* synthetic */ Intent zzl;
    private final /* synthetic */ zzd zzr;

    zze(zzd zzdVar, Intent intent) {
        this.zzr = zzdVar;
        this.zzl = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String action = this.zzl.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        this.zzr.finish();
    }
}
