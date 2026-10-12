package com.navercorp.volleyextensions.volleyer.response.parser;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface NetworkResponseParser {
    <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls);
}
