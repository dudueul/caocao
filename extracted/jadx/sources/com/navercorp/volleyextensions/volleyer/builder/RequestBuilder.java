package com.navercorp.volleyextensions.volleyer.builder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.VolleyerConfiguration;
import com.navercorp.volleyextensions.volleyer.builder.RequestBuilder;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.http.HttpMethod;
import com.navercorp.volleyextensions.volleyer.util.Assert;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class RequestBuilder<B extends RequestBuilder<B>> {
    private final VolleyerConfiguration configuration;
    protected final HttpContent httpContent;
    protected boolean isDoneToBuild = false;
    private RequestQueue requestQueue;

    public RequestBuilder(RequestQueue requestQueue, VolleyerConfiguration volleyerConfiguration, String str, HttpMethod httpMethod) {
        Assert.notNull(requestQueue, "RequestQueue");
        Assert.notNull(volleyerConfiguration, "VolleyerConfiguration");
        this.requestQueue = requestQueue;
        this.configuration = volleyerConfiguration;
        this.httpContent = new HttpContent(str, httpMethod);
    }

    public B addHeader(String str, String str2) {
        assertFinishState();
        this.httpContent.addHeader(str, str2);
        return this;
    }

    protected final void assertFinishState() {
        if (this.isDoneToBuild) {
            throw new IllegalStateException("RequestBuilder should not be used any more. Because afterRequest() has been called.");
        }
    }

    public Request<Void> execute() {
        assertFinishState();
        return withTargetClass(Void.class).execute();
    }

    protected final void markFinishState() {
        this.isDoneToBuild = true;
        this.requestQueue = null;
    }

    public ResponseBuilder<String> withErrorListener(Response.ErrorListener errorListener) {
        assertFinishState();
        ResponseBuilder responseBuilderWithTargetClass = withTargetClass(String.class);
        responseBuilderWithTargetClass.withErrorListener(errorListener);
        return responseBuilderWithTargetClass;
    }

    public ResponseBuilder<String> withListener(Response.Listener<String> listener) {
        assertFinishState();
        ResponseBuilder responseBuilderWithTargetClass = withTargetClass(String.class);
        responseBuilderWithTargetClass.withListener(listener);
        return responseBuilderWithTargetClass;
    }

    public <T> ResponseBuilder<T> withTargetClass(Class<T> cls) {
        Assert.notNull(cls, "Target Class token");
        assertFinishState();
        ResponseBuilder<T> responseBuilder = new ResponseBuilder<>(this.requestQueue, this.configuration, this.httpContent, cls);
        markFinishState();
        return responseBuilder;
    }
}
