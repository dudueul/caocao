package com.navercorp.volleyextensions.volleyer.request.executor;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface RequestExecutor {
    <T> void executeRequest(RequestQueue requestQueue, Request<T> request);
}
