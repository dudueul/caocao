package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzv implements PendingResultUtil.ResultConverter<GamesMetadata.LoadGamesResult, Game> {
    zzv() {
    }

    private static Game zza(@Nullable GamesMetadata.LoadGamesResult loadGamesResult) {
        GameBuffer games;
        Game gameFreeze = null;
        if (loadGamesResult != null && (games = loadGamesResult.getGames()) != null) {
            try {
                if (games.getCount() > 0) {
                    gameFreeze = ((Game) games.get(0)).freeze();
                }
            } finally {
                games.release();
            }
        }
        return gameFreeze;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Game convert(@Nullable Result result) {
        return zza((GamesMetadata.LoadGamesResult) result);
    }
}
