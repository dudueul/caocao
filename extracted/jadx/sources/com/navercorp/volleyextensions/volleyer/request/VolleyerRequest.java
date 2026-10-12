package com.navercorp.volleyextensions.volleyer.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.multipart.Multipart;
import com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VolleyerRequest<T> extends Request<T> implements MultipartContainer {
    private Class<T> clazz;
    private HttpContent httpContent;
    private Response.Listener<T> listener;
    private NetworkResponseParser responseParser;

    public VolleyerRequest(HttpContent httpContent, Class<T> cls, NetworkResponseParser networkResponseParser, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(httpContent.getMethod().getMethodCode(), httpContent.getUrl(), errorListener);
        Assert.notNull(cls, "Target class token");
        Assert.notNull(networkResponseParser, "NetworkResponseParser");
        Assert.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.httpContent = httpContent;
        this.responseParser = networkResponseParser;
        this.listener = listener;
        this.clazz = cls;
    }

    @Override // com.android.volley.Request
    protected void deliverResponse(T t) {
        this.listener.onResponse(t);
    }

    @Override // com.android.volley.Request
    public byte[] getBody() {
        return this.httpContent.getBody();
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.httpContent.getHeaders();
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer
    public Multipart getMultipart() {
        return this.httpContent.getMultipart();
    }

    protected Class<T> getTargetClass() {
        return this.clazz;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer
    public boolean hasMultipart() {
        return this.httpContent.hasMultipart();
    }

    @Override // com.android.volley.Request
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        return this.responseParser.parseNetworkResponse(networkResponse, this.clazz);
    }
}
