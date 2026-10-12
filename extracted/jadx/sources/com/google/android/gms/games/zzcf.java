package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzcf implements com.google.android.gms.games.internal.zzq<TurnBasedMultiplayer.LoadMatchesResult> {
    zzcf() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(@NonNull TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult) {
        TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult2 = loadMatchesResult;
        if (loadMatchesResult2.getMatches() != null) {
            loadMatchesResult2.getMatches().release();
        }
    }
}
