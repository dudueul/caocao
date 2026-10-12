package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzl implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zzk zzafv;

    zzl(zzk zzkVar, Task task) {
        this.zzafv = zzkVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzafv.mLock) {
            if (this.zzafv.zzafu != null) {
                this.zzafv.zzafu.onFailure(this.zzafn.getException());
            }
        }
    }
}
