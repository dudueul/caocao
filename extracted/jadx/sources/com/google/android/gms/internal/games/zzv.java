package com.google.android.gms.internal.games;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzv implements Events {
    @Override // com.google.android.gms.games.event.Events
    @SuppressLint({"MissingRemoteException"})
    public final void increment(GoogleApiClient googleApiClient, String str, int i) {
        com.google.android.gms.games.internal.zze zzeVarZzb = Games.zzb(googleApiClient, false);
        if (zzeVarZzb == null) {
            return;
        }
        if (zzeVarZzb.isConnected()) {
            zzeVarZzb.zza(str, i);
        } else {
            googleApiClient.execute(new zzy(this, googleApiClient, str, i));
        }
    }

    @Override // com.google.android.gms.games.event.Events
    public final PendingResult<Events.LoadEventsResult> load(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzx(this, googleApiClient, z));
    }

    @Override // com.google.android.gms.games.event.Events
    public final PendingResult<Events.LoadEventsResult> loadByIds(GoogleApiClient googleApiClient, boolean z, String... strArr) {
        return googleApiClient.enqueue(new zzw(this, googleApiClient, z, strArr));
    }
}
