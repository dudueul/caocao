package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class zzc<R extends Result> extends BaseImplementation.ApiMethodImpl<R, CommonClient> {
    public zzc(GoogleApiClient googleApiClient) {
        super(Common.API, googleApiClient);
    }
}
