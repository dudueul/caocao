package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class zzat implements Runnable {
    private final /* synthetic */ zzaj zzhv;

    private zzat(zzaj zzajVar) {
        this.zzhv = zzajVar;
    }

    /* synthetic */ zzat(zzaj zzajVar, zzak zzakVar) {
        this(zzajVar);
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public void run() {
        this.zzhv.zzga.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zzaq();
            return;
        } catch (RuntimeException e) {
            this.zzhv.zzhf.zzb(e);
            return;
        } finally {
            this.zzhv.zzga.unlock();
        }
        this.zzhv.zzga.unlock();
    }

    @WorkerThread
    protected abstract void zzaq();
}
