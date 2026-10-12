package com.naver.plug.android.core.api.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.http.ContentTypes;
import com.navercorp.volleyextensions.volleyer.response.parser.TypedNetworkResponseParser;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GsonNetworkResponseParser implements TypedNetworkResponseParser {
    public static <T> T parseBodyString(String str, Class<T> cls) {
        return (T) new Gson().fromJson(str, (Class) cls);
    }

    protected final String getBodyString(NetworkResponse networkResponse) {
        return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.TypedNetworkResponseParser
    public ContentTypes getContentTypes() {
        return new ContentTypes(ContentType.CONTENT_TYPE_APPLICATION_JSON, ContentType.CONTENT_TYPE_TEXT_JSON);
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser
    public <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls) {
        Assert.notNull(networkResponse, "Response");
        Assert.notNull(cls, "Class token");
        try {
            return Response.success(parseBodyString(getBodyString(networkResponse), cls), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e2) {
            return Response.error(new VolleyError(e2));
        }
    }
}
