package com.navercorp.volleyextensions.volleyer.response.parser;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.http.ContentTypes;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Jackson2NetworkResponseParser implements TypedNetworkResponseParser {
    private final ObjectMapper objectMapper;

    private static class ObjectMapperHolder {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        static {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        }

        private ObjectMapperHolder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ObjectMapper getObjectMapper() {
            return objectMapper;
        }
    }

    public Jackson2NetworkResponseParser() {
        this(ObjectMapperHolder.getObjectMapper());
    }

    public Jackson2NetworkResponseParser(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper");
        this.objectMapper = objectMapper;
    }

    protected final String getBodyString(NetworkResponse networkResponse) throws UnsupportedEncodingException {
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
            return Response.success(this.objectMapper.readValue(getBodyString(networkResponse), cls), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (JsonParseException e) {
            return Response.error(new ParseError((Throwable) e));
        } catch (JsonMappingException e2) {
            return Response.error(new ParseError((Throwable) e2));
        } catch (UnsupportedEncodingException e3) {
            return Response.error(new ParseError(e3));
        } catch (IOException e4) {
            return Response.error(new VolleyError(e4));
        } catch (Exception e5) {
            return Response.error(new VolleyError(e5));
        }
    }
}
