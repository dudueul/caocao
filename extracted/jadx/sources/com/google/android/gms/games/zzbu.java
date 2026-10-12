package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzbu implements PendingResultUtil.ResultConverter<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> {
    zzbu() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ SnapshotMetadataBuffer convert(@Nullable Result result) {
        Snapshots.LoadSnapshotsResult loadSnapshotsResult = (Snapshots.LoadSnapshotsResult) result;
        if (loadSnapshotsResult == null) {
            return null;
        }
        return loadSnapshotsResult.getSnapshots();
    }
}
