package com.navercorp.volleyextensions.volleyer.response.parser;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.navercorp.volleyextensions.volleyer.exception.UnsupportedContentTypeException;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.http.ContentTypes;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class IntegratedNetworkResponseParser implements NetworkResponseParser {
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final NetworkResponseParser STRING_NETWORK_RESPONSE_PARSER = new StringNetworkResponseParser();
    private final Map<ContentType, NetworkResponseParser> parsers = new HashMap();

    public static class Builder {
        private final Map<ContentType, NetworkResponseParser> parsers = new HashMap();

        private void addParserForContentTypes(TypedNetworkResponseParser typedNetworkResponseParser, ContentTypes contentTypes) {
            Iterator<ContentType> it = contentTypes.getListOfContentTypes().iterator();
            while (it.hasNext()) {
                addParser(it.next(), typedNetworkResponseParser);
            }
        }

        public Builder addParser(ContentType contentType, NetworkResponseParser networkResponseParser) {
            Assert.notNull(contentType, "ContentType");
            Assert.notNull(networkResponseParser, "NetworkResponseParser");
            this.parsers.put(contentType, networkResponseParser);
            return this;
        }

        public Builder addParser(TypedNetworkResponseParser typedNetworkResponseParser) {
            Assert.notNull(typedNetworkResponseParser, "TypedNetworkResponseParser");
            addParserForContentTypes(typedNetworkResponseParser, typedNetworkResponseParser.getContentTypes());
            return this;
        }

        public IntegratedNetworkResponseParser build() {
            return new IntegratedNetworkResponseParser(this.parsers);
        }
    }

    IntegratedNetworkResponseParser(Map<ContentType, NetworkResponseParser> map) {
        this.parsers.putAll(map);
    }

    protected String getResponseHeader(NetworkResponse networkResponse, String str) {
        return networkResponse.headers.get(str);
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser
    public <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls) {
        Assert.notNull(networkResponse, "NetworkResponse");
        Assert.notNull(cls, "Target class token");
        if (cls == Void.class) {
            return Response.success(null, null);
        }
        if (cls == String.class) {
            return STRING_NETWORK_RESPONSE_PARSER.parseNetworkResponse(networkResponse, cls);
        }
        if (cls == NetworkResponse.class) {
            return Response.success(networkResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        String responseHeader = getResponseHeader(networkResponse, "Content-Type");
        if (responseHeader == null) {
            return Response.error(new ParseError(new UnsupportedContentTypeException("It cannot find any response parser, because the response content type is null.")));
        }
        NetworkResponseParser networkResponseParser = this.parsers.get(ContentType.createContentType(responseHeader));
        return networkResponseParser == null ? Response.error(new ParseError(new UnsupportedContentTypeException("It cannot find any response parser for the response content type."))) : networkResponseParser.parseNetworkResponse(networkResponse, cls);
    }
}
