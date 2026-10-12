package com.naver.plug.android.core.api.request;

import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.naver.api.security.HmacUtil;
import com.naver.plug.android.core.api.Response;
import com.navercorp.volleyextensions.volleyer.Volleyer;
import com.navercorp.volleyextensions.volleyer.builder.DeleteBuilder;
import com.navercorp.volleyextensions.volleyer.builder.GetBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PostBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PutBuilder;
import com.navercorp.volleyextensions.volleyer.multipart.AbstractPart;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestBuilder<T extends Response> {
    private static final NetworkResponseParser DEFAULT_RESPONSE_PARSER = new GsonNetworkResponseParser();
    private static final String HMAC_KEY = "KBSieWl2uyyEP4pbttFODqkT9oNmZoBbwIsY2q6Tu9DxTOgJnflwBaojawCKTY5k";
    private boolean checkNetworkConnected;
    private Map<String, String> headers;
    private String host;
    private int method;
    public Map<String, String> parameters;
    private List<AbstractPart> parts;
    private String path;
    private Class<T> responseClass;
    private NetworkResponseParser responseParser;
    private boolean showProgress;
    private int timeoutMs;
    private boolean useHmac;

    private com.android.volley.Request<T> _buildRequest(RequestQueue requestQueue, com.android.volley.Response.Listener<T> listener, com.android.volley.Response.ErrorListener errorListener) {
        switch (this.method) {
            case 0:
                GetBuilder getBuilder = Volleyer.volleyer(requestQueue).get(url());
                for (Map.Entry<String, String> entry : headers().entrySet()) {
                    getBuilder.addHeader(entry.getKey(), entry.getValue());
                }
                return getBuilder.withTargetClass(this.responseClass).withResponseParser(responseParser()).withListener(listener).withErrorListener(errorListener).execute();
            case 1:
                PostBuilder postBuilderPost = Volleyer.volleyer(requestQueue).post(url());
                for (Map.Entry<String, String> entry2 : headers().entrySet()) {
                    postBuilderPost.addHeader(entry2.getKey(), entry2.getValue());
                }
                String strQueryString = queryString();
                if (!TextUtils.isEmpty(strQueryString)) {
                    postBuilderPost.withBody(strQueryString.getBytes());
                }
                if (this.parts != null) {
                    Iterator<AbstractPart> it = this.parts.iterator();
                    while (it.hasNext()) {
                        postBuilderPost.addPart(it.next());
                    }
                }
                return postBuilderPost.withTargetClass(this.responseClass).withResponseParser(responseParser()).withListener(listener).withErrorListener(errorListener).execute();
            case 2:
                PutBuilder putBuilderPut = Volleyer.volleyer(requestQueue).put(url());
                for (Map.Entry<String, String> entry3 : headers().entrySet()) {
                    putBuilderPut.addHeader(entry3.getKey(), entry3.getValue());
                }
                String strQueryString2 = queryString();
                if (!TextUtils.isEmpty(strQueryString2)) {
                    putBuilderPut.withBody(strQueryString2.getBytes());
                }
                return putBuilderPut.withTargetClass(this.responseClass).withResponseParser(responseParser()).withListener(listener).withErrorListener(errorListener).execute();
            case 3:
                DeleteBuilder deleteBuilderDelete = Volleyer.volleyer(requestQueue).delete(url());
                for (Map.Entry<String, String> entry4 : headers().entrySet()) {
                    deleteBuilderDelete.addHeader(entry4.getKey(), entry4.getValue());
                }
                return deleteBuilderDelete.withTargetClass(this.responseClass).withResponseParser(responseParser()).withListener(listener).withErrorListener(errorListener).execute();
            default:
                throw new IllegalArgumentException("지원하지 않는 method 입니다.");
        }
    }

    private String makeUrl() {
        if (this.method == 1 || this.method == 2) {
            return this.host + this.path;
        }
        String strQueryString = queryString();
        return this.host + this.path + (TextUtils.isEmpty(strQueryString) ? "" : "?" + strQueryString);
    }

    private String queryString() {
        return RequestHelper.makeQueryString(this.parameters);
    }

    private NetworkResponseParser responseParser() {
        return this.responseParser == null ? DEFAULT_RESPONSE_PARSER : this.responseParser;
    }

    com.android.volley.Request<T> buildRequest(RequestQueue requestQueue, com.android.volley.Response.Listener<T> listener, com.android.volley.Response.ErrorListener errorListener) {
        com.android.volley.Request<T> request_buildRequest = _buildRequest(requestQueue, listener, errorListener);
        request_buildRequest.setRetryPolicy(new DefaultRetryPolicy(this.timeoutMs, 1, 1.0f));
        return request_buildRequest;
    }

    public RequestBuilder<T> checkNetworkConnected(boolean z) {
        this.checkNetworkConnected = z;
        return this;
    }

    public boolean checkNetworkConnected() {
        return this.checkNetworkConnected;
    }

    public RequestBuilder<T> headers(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    public Map<String, String> headers() {
        return this.headers == null ? Collections.emptyMap() : this.headers;
    }

    public RequestBuilder<T> host(String str) {
        this.host = str;
        return this;
    }

    public RequestBuilder<T> method(int i) {
        this.method = i;
        return this;
    }

    public RequestBuilder<T> parameters(Map<String, String> map) {
        this.parameters = map;
        return this;
    }

    public RequestBuilder<T> parts(List<AbstractPart> list) {
        this.parts = list;
        return this;
    }

    public RequestBuilder<T> path(String str) {
        this.path = str;
        return this;
    }

    public RequestBuilder<T> responseClass(Class<T> cls) {
        this.responseClass = cls;
        return this;
    }

    public RequestBuilder<T> responseParser(NetworkResponseParser networkResponseParser) {
        this.responseParser = networkResponseParser;
        return this;
    }

    public RequestBuilder<T> showProgress(boolean z) {
        this.showProgress = z;
        return this;
    }

    public boolean showProgress() {
        return this.showProgress;
    }

    public RequestBuilder<T> timeoutMs(int i) {
        this.timeoutMs = i;
        return this;
    }

    public Request<T> toRequest() {
        return new Request<>(this);
    }

    public String url() {
        if (!this.useHmac) {
            return makeUrl();
        }
        try {
            return HmacUtil.makeEncryptUrl(HmacUtil.getMac(HMAC_KEY), makeUrl());
        } catch (Exception e) {
            return makeUrl();
        }
    }

    public RequestBuilder<T> useHmac(boolean z) {
        this.useHmac = z;
        return this;
    }
}
