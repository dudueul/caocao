package com.navercorp.volleyextensions.volleyer;

import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.request.creator.RequestCreator;
import com.navercorp.volleyextensions.volleyer.request.executor.RequestExecutor;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VolleyerConfiguration {
    private Response.ErrorListener errorListener;
    private NetworkResponseParser networkResponseParser;
    private RequestCreator requestCreator;
    private RequestExecutor requestExecutor;

    public VolleyerConfiguration(RequestCreator requestCreator, RequestExecutor requestExecutor, NetworkResponseParser networkResponseParser, Response.ErrorListener errorListener) {
        this.requestCreator = requestCreator;
        this.requestExecutor = requestExecutor;
        this.networkResponseParser = networkResponseParser;
        this.errorListener = errorListener;
    }

    public Response.ErrorListener getFallbackErrorListener() {
        return this.errorListener;
    }

    public NetworkResponseParser getFallbackNetworkResponseParser() {
        return this.networkResponseParser;
    }

    public RequestCreator getRequestCreator() {
        return this.requestCreator;
    }

    public RequestExecutor getRequestExecutor() {
        return this.requestExecutor;
    }
}
