package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbh implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zzjy;

    zzbh(GoogleApiManager googleApiManager) {
        this.zzjy = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        this.zzjy.handler.sendMessage(this.zzjy.handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
