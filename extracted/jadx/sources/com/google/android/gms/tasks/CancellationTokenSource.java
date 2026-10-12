package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CancellationTokenSource {
    private final zza zzafj = new zza();

    public void cancel() {
        this.zzafj.cancel();
    }

    public CancellationToken getToken() {
        return this.zzafj;
    }
}
