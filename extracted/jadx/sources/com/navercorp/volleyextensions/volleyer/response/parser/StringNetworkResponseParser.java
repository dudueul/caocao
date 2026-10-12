package com.navercorp.volleyextensions.volleyer.response.parser;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StringNetworkResponseParser implements NetworkResponseParser {
    protected final String getBodyString(NetworkResponse networkResponse) throws UnsupportedEncodingException {
        return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser
    public <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls) {
        Assert.notNull(networkResponse, "The response");
        Assert.notNull(cls, "The class token");
        try {
            return Response.success(getBodyString(networkResponse), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}
