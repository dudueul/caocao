package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzcl implements com.google.android.gms.games.internal.zzr {
    zzcl() {
    }

    @Override // com.google.android.gms.games.internal.zzr
    public final boolean zza(@NonNull Status status) {
        return status.isSuccess() || status.getStatusCode() == 5;
    }
}
