package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzaz implements PendingResultUtil.ResultConverter<Players.LoadPlayersResult, Player> {
    zzaz() {
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0023 A[DONT_GENERATE, DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:13:0x0025 A[DONT_GENERATE] */
    private static Player zza(@Nullable Players.LoadPlayersResult loadPlayersResult) {
        Player playerFreeze = null;
        if (loadPlayersResult != null) {
            PlayerBuffer players = loadPlayersResult.getPlayers();
            if (players != null) {
                try {
                    if (players.getCount() > 0) {
                        playerFreeze = ((Player) players.get(0)).freeze();
                    }
                } finally {
                    if (players != null) {
                        players.release();
                    }
                }
            }
        }
        return playerFreeze;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Player convert(@Nullable Result result) {
        return zza((Players.LoadPlayersResult) result);
    }
}
