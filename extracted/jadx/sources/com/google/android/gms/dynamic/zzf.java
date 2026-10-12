package com.google.android.gms.dynamic;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzf implements DeferredLifecycleHelper.zza {
    private final /* synthetic */ DeferredLifecycleHelper zzabg;

    zzf(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zzabg = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final int getState() {
        return 4;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final void zza(LifecycleDelegate lifecycleDelegate) {
        this.zzabg.zzabc.onStart();
    }
}
