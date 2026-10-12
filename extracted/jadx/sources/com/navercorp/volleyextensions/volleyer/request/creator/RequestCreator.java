package com.navercorp.volleyextensions.volleyer.request.creator;

import com.android.volley.Request;
import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface RequestCreator {
    <T> Request<T> createRequest(HttpContent httpContent, Class<T> cls, NetworkResponseParser networkResponseParser, Response.Listener<T> listener, Response.ErrorListener errorListener);
}
