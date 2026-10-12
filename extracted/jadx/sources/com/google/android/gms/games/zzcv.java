package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzcv implements com.google.android.gms.games.internal.zzp<TurnBasedMatch> {
    zzcv() {
    }

    @Override // com.google.android.gms.games.internal.zzp
    public final /* synthetic */ ApiException zza(@NonNull Status status, @NonNull TurnBasedMatch turnBasedMatch) {
        return status.getStatusCode() == 26593 ? new TurnBasedMultiplayerClient.MatchOutOfDateApiException(status, turnBasedMatch) : ApiExceptionUtil.fromStatus(status);
    }
}
