package com.google.android.gms.dynamic;

import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzc implements DeferredLifecycleHelper.zza {
    private final /* synthetic */ DeferredLifecycleHelper zzabg;
    private final /* synthetic */ Bundle zzabi;

    zzc(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.zzabg = deferredLifecycleHelper;
        this.zzabi = bundle;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final int getState() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final void zza(LifecycleDelegate lifecycleDelegate) {
        this.zzabg.zzabc.onCreate(this.zzabi);
    }
}
