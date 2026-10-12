package com.naver.glink.android.sdk.api.requests;

import android.util.Log;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilderFactory;
import net.daum.mf.oauth.impl.Constant;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* JADX INFO: compiled from: XmlNetworkResponseParser.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class i implements NetworkResponseParser {
    private static final String TAG = i.class.getSimpleName();

    /* JADX WARN: Type inference failed for: r0v3, types: [T, com.naver.glink.android.sdk.api.a$b] */
    public static <T> T a(String str, Class<T> cls) {
        if (!cls.equals(com.naver.glink.android.sdk.api.a.b.class)) {
            return null;
        }
        ?? r0 = (T) new com.naver.glink.android.sdk.api.a.b();
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            Element documentElement = documentBuilderFactoryNewInstance.newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes(Constant.DEFAULT_ENCODING))).getDocumentElement();
            r0.index = b(documentElement, "index");
            r0.url = a(documentElement, "url");
            r0.path = a(documentElement, "path");
            r0.fileName = a(documentElement, "fileName");
            r0.width = b(documentElement, "width");
            r0.height = b(documentElement, "height");
            r0.colorSpace = a(documentElement, "colorSpace");
            r0.fileSize = b(documentElement, "fileSize");
            r0.thumbnail = a(documentElement, "thumbnail");
            r0.animatedCnt = b(documentElement, "animatedCnt");
            return r0;
        } catch (Exception e) {
            Log.d(TAG, "", e);
            return r0;
        }
    }

    static String a(Element element, String str) {
        NodeList elementsByTagName = element.getElementsByTagName(str);
        if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
            return null;
        }
        return elementsByTagName.item(0).getFirstChild().getNodeValue();
    }

    static int b(Element element, String str) {
        String strA = a(element, str);
        if (strA == null) {
            return 0;
        }
        return Integer.valueOf(strA).intValue();
    }

    protected final String a(NetworkResponse networkResponse) {
        return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
    }

    @Override // com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser
    public <T> Response<T> parseNetworkResponse(NetworkResponse networkResponse, Class<T> cls) {
        Assert.notNull(networkResponse, "The response");
        Assert.notNull(cls, "The class token");
        try {
            return Response.success(a(a(networkResponse), cls), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}
