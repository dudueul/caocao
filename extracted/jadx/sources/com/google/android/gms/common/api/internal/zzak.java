package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzak implements Runnable {
    private final /* synthetic */ zzaj zzhv;

    zzak(zzaj zzajVar) {
        this.zzhv = zzajVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzhv.zzgk.cancelAvailabilityErrorNotifications(this.zzhv.mContext);
    }
}
