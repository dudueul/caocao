package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzk implements PendingResultUtil.StatusConverter {
    zzk() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.StatusConverter
    public final ApiException convert(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
