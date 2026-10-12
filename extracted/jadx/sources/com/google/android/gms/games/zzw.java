package com.google.android.gms.games;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzw implements com.google.android.gms.games.internal.zzq<GamesMetadata.LoadGamesResult> {
    zzw() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(@NonNull GamesMetadata.LoadGamesResult loadGamesResult) {
        GamesMetadata.LoadGamesResult loadGamesResult2 = loadGamesResult;
        if (loadGamesResult2.getGames() != null) {
            loadGamesResult2.getGames().release();
        }
    }
}
