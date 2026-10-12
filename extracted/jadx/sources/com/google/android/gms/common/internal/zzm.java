package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzm<R, T> implements PendingResultUtil.ResultConverter<R, T> {
    private final /* synthetic */ Response zzus;

    zzm(Response response) {
        this.zzus = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Object convert(Result result) {
        this.zzus.setResult(result);
        return this.zzus;
    }
}
