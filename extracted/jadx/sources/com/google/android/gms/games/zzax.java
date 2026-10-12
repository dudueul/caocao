package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzax implements PendingResultUtil.ResultConverter<Players.LoadPlayersResult, PlayerBuffer> {
    zzax() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ PlayerBuffer convert(@Nullable Result result) {
        Players.LoadPlayersResult loadPlayersResult = (Players.LoadPlayersResult) result;
        if (loadPlayersResult == null) {
            return null;
        }
        return loadPlayersResult.getPlayers();
    }
}
