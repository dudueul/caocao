package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastJsonResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface PostProcessor<T extends FastJsonResponse> {
    T postProcess(T t);
}
