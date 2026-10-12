package com.navercorp.volleyextensions.volleyer.response.parser;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.http.ContentTypes;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import com.navercorp.volleyextensions.volleyer.util.IoUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.simpleframework.xml.core.ElementException;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SimpleXmlNetworkResponseParser implements TypedNetworkResponseParser {
    private Persister persister;

    private static class PersisterHolder {
        private static final Persister persister = new Persister();

        private PersisterHolder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Persister getPersister() {
            return persister;
        }
    }

    public SimpleXmlNetworkResponseParser() {
        this(PersisterHolder.getPersister());
    }

    public SimpleXmlNetworkResponseParser(Persister persister) {
        Assert.notNull(persister, "Persister");
        this.persister = persister;
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.TypedNetworkResponseParser
    public ContentTypes getContentTypes() {
        return new ContentTypes(ContentType.CONTENT_TYPE_APPLICATION_XML, ContentType.CONTENT_TYPE_TEXT_XML);
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser
    public <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls) throws Throwable {
        Response<T> responseError;
        InputStreamReader inputStreamReader = null;
        try {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(new ByteArrayInputStream(networkResponse.data), HttpHeaderParser.parseCharset(networkResponse.headers));
                try {
                    responseError = Response.success(this.persister.read(cls, inputStreamReader2), HttpHeaderParser.parseCacheHeaders(networkResponse));
                    IoUtils.closeQuietly(inputStreamReader2);
                    inputStreamReader = inputStreamReader2;
                } catch (UnsupportedEncodingException e) {
                    e = e;
                    inputStreamReader = inputStreamReader2;
                    responseError = Response.error(new ParseError(e));
                    IoUtils.closeQuietly(inputStreamReader);
                } catch (Exception e2) {
                    e = e2;
                    inputStreamReader = inputStreamReader2;
                    responseError = Response.error(new VolleyError(e));
                    IoUtils.closeQuietly(inputStreamReader);
                } catch (ElementException e3) {
                    e = e3;
                    inputStreamReader = inputStreamReader2;
                    responseError = Response.error(new ParseError((Throwable) e));
                    IoUtils.closeQuietly(inputStreamReader);
                } catch (XmlPullParserException e4) {
                    e = e4;
                    inputStreamReader = inputStreamReader2;
                    responseError = Response.error(new ParseError(e));
                    IoUtils.closeQuietly(inputStreamReader);
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader = inputStreamReader2;
                    IoUtils.closeQuietly(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (UnsupportedEncodingException e5) {
            e = e5;
        } catch (XmlPullParserException e6) {
            e = e6;
        } catch (Exception e7) {
            e = e7;
        } catch (ElementException e8) {
            e = e8;
        }
        return responseError;
    }
}
