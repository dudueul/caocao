package com.navercorp.volleyextensions.volleyer.builder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.VolleyerConfiguration;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;
import com.navercorp.volleyextensions.volleyer.util.Assert;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ResponseBuilder<T> {
    private Class<T> clazz;
    private VolleyerConfiguration configuration;
    private Response.ErrorListener errorListener;
    private HttpContent httpContent;
    private boolean isDoneToBuild = false;
    private Response.Listener<T> listener;
    private RequestQueue requestQueue;
    private NetworkResponseParser responseParser;

    ResponseBuilder(RequestQueue requestQueue, VolleyerConfiguration volleyerConfiguration, HttpContent httpContent, Class<T> cls) {
        Assert.notNull(requestQueue, "RequestQueue");
        Assert.notNull(volleyerConfiguration, "VolleyerConfiguration");
        Assert.notNull(httpContent, "HttpContent");
        Assert.notNull(cls, "Target class token");
        this.requestQueue = requestQueue;
        this.configuration = volleyerConfiguration;
        this.httpContent = httpContent;
        this.clazz = cls;
    }

    private void assertFinishState() {
        if (this.isDoneToBuild) {
            throw new IllegalStateException("ResponseBuilder should not be used any more. Because execute() has been called.");
        }
    }

    private Request<T> buildRequest() {
        return this.configuration.getRequestCreator().createRequest(this.httpContent, this.clazz, this.responseParser, this.listener, this.errorListener);
    }

    private Response.Listener<T> createEmptyListener() {
        return new Response.Listener<T>() { // from class: com.navercorp.volleyextensions.volleyer.builder.ResponseBuilder.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(Object obj) {
            }
        };
    }

    private void executeRequest(Request<T> request) {
        this.configuration.getRequestExecutor().executeRequest(this.requestQueue, request);
    }

    private void setFallbackErrorListenerIfNull() {
        if (this.errorListener != null) {
            return;
        }
        this.errorListener = this.configuration.getFallbackErrorListener();
    }

    private void setFallbackListenerIfNull() {
        if (this.listener != null) {
            return;
        }
        this.listener = createEmptyListener();
    }

    private void setFallbackResponseParserIfNull() {
        if (this.responseParser != null) {
            return;
        }
        this.responseParser = this.configuration.getFallbackNetworkResponseParser();
    }

    public Request<T> execute() {
        setFallbackListenerIfNull();
        setFallbackErrorListenerIfNull();
        setFallbackResponseParserIfNull();
        Request<T> requestBuildRequest = buildRequest();
        if (requestBuildRequest != null) {
            executeRequest(requestBuildRequest);
            markFinishState();
        }
        return requestBuildRequest;
    }

    protected final void markFinishState() {
        this.isDoneToBuild = true;
        this.requestQueue = null;
    }

    public ResponseBuilder<T> withErrorListener(Response.ErrorListener errorListener) {
        assertFinishState();
        this.errorListener = errorListener;
        return this;
    }

    public ResponseBuilder<T> withListener(Response.Listener<T> listener) {
        assertFinishState();
        this.listener = listener;
        return this;
    }

    public ResponseBuilder<T> withResponseParser(NetworkResponseParser networkResponseParser) {
        Assert.notNull(networkResponseParser, "Response Parser");
        assertFinishState();
        this.responseParser = networkResponseParser;
        return this;
    }
}
