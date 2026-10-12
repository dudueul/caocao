package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzam implements PendingResultUtil.ResultConverter<Leaderboards.LeaderboardMetadataResult, Leaderboard> {
    zzam() {
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0023 A[DONT_GENERATE, DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:13:0x0025 A[DONT_GENERATE] */
    private static Leaderboard zza(Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        Leaderboard leaderboardFreeze = null;
        if (leaderboardMetadataResult != null) {
            LeaderboardBuffer leaderboards = leaderboardMetadataResult.getLeaderboards();
            if (leaderboards != null) {
                try {
                    if (leaderboards.getCount() > 0) {
                        leaderboardFreeze = leaderboards.get(0).freeze();
                    }
                } finally {
                    if (leaderboards != null) {
                        leaderboards.release();
                    }
                }
            }
        }
        return leaderboardFreeze;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Leaderboard convert(Result result) {
        return zza((Leaderboards.LeaderboardMetadataResult) result);
    }
}
