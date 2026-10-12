package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class zzbe {
    private final zzbc zzjg;

    protected zzbe(zzbc zzbcVar) {
        this.zzjg = zzbcVar;
    }

    protected abstract void zzaq();

    public final void zzc(zzbd zzbdVar) {
        zzbdVar.zzga.lock();
        try {
            if (zzbdVar.zzjc != this.zzjg) {
                return;
            }
            zzaq();
        } finally {
            zzbdVar.zzga.unlock();
        }
    }
}
