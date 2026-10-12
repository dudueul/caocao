package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GamesMetadataClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<GamesMetadata.LoadGamesResult, Game> zzbf = new zzv();
    private static final com.google.android.gms.games.internal.zzq<GamesMetadata.LoadGamesResult> zzbg = new zzw();

    GamesMetadataClient(@NonNull Activity activity, @NonNull Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    GamesMetadataClient(@NonNull Context context, @NonNull Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Game> getCurrentGame() {
        return doRead(new zzu(this));
    }

    public Task<AnnotatedData<Game>> loadGame() {
        return com.google.android.gms.games.internal.zzi.zza(Games.GamesMetadata.loadGame(asGoogleApiClient()), zzbf, zzbg);
    }
}
