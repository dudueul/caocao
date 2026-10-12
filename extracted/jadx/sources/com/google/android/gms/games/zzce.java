package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzce implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> {
    zzce() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ LoadMatchesResponse convert(@Nullable Result result) {
        TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult = (TurnBasedMultiplayer.LoadMatchesResult) result;
        if (loadMatchesResult == null) {
            return null;
        }
        return loadMatchesResult.getMatches();
    }
}
