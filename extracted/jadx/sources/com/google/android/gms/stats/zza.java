package com.google.android.gms.stats;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zza implements WakeLock.Configuration {
    zza() {
    }

    @Override // com.google.android.gms.stats.WakeLock.Configuration
    public final long getMaximumTimeout(String str, String str2) {
        return Long.MAX_VALUE;
    }

    @Override // com.google.android.gms.stats.WakeLock.Configuration
    public final boolean isWorkChainsEnabled() {
        return false;
    }
}
