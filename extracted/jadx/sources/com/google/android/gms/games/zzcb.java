package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzcb implements PendingResultUtil.ResultConverter<Snapshots.OpenSnapshotResult, Snapshots.OpenSnapshotResult> {
    zzcb() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Snapshots.OpenSnapshotResult convert(@Nullable Result result) {
        return (Snapshots.OpenSnapshotResult) result;
    }
}
