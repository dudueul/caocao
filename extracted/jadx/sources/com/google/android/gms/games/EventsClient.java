package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class EventsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Events.LoadEventsResult, EventBuffer> zzj = new zzg();

    EventsClient(@NonNull Activity activity, @NonNull Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    EventsClient(@NonNull Context context, @NonNull Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public void increment(@NonNull String str, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i) {
        doWrite(new zzf(this, str, i));
    }

    public Task<AnnotatedData<EventBuffer>> load(boolean z) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.load(asGoogleApiClient(), z), zzj);
    }

    public Task<AnnotatedData<EventBuffer>> loadByIds(boolean z, @NonNull String... strArr) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.loadByIds(asGoogleApiClient(), z, strArr), zzj);
    }
}
