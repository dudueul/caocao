package com.navercorp.volleyextensions.volleyer.request.creator;

import com.android.volley.Request;
import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.request.VolleyerRequest;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultRequestCreator implements RequestCreator {
    @Override // com.navercorp.volleyextensions.volleyer.request.creator.RequestCreator
    public <T> Request<T> createRequest(HttpContent httpContent, Class<T> cls, NetworkResponseParser networkResponseParser, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        return new VolleyerRequest(httpContent, cls, networkResponseParser, listener, errorListener);
    }
}
