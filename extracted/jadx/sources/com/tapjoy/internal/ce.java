package com.tapjoy.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@ThreadSafe
public final class ce implements cd {
    private final String a;
    private final URL b;

    public ce(String str, URL url) {
        this.a = str;
        this.b = url;
    }

    @Override // com.tapjoy.internal.cd
    public final Object a(ca caVar) throws IOException {
        URI uri;
        URL url = new URL(this.b, caVar.c());
        String strB = caVar.b();
        if ("GET".equals(strB) || HttpRequest.METHOD_DELETE.equals(strB)) {
            Map mapE = caVar.e();
            if (!mapE.isEmpty()) {
                url = new URL(url, url.getPath() + "?" + fk.a(mapE));
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) fj.a(url);
        httpURLConnection.setRequestMethod(strB);
        httpURLConnection.setRequestProperty("User-Agent", this.a);
        for (Map.Entry entry : caVar.a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
        }
        if (!"GET".equals(strB) && !HttpRequest.METHOD_DELETE.equals(strB)) {
            if (!"POST".equals(strB) && !HttpRequest.METHOD_PUT.equals(strB)) {
                throw new IllegalArgumentException("Unknown method: " + strB);
            }
            String strD = caVar.d();
            if (strD == null) {
                fk.a(httpURLConnection, HttpRequest.CONTENT_TYPE_FORM, fk.a(caVar.e()), jm.c);
            } else {
                if (!"application/json".equals(strD)) {
                    throw new IllegalArgumentException("Unknown content type: " + strD);
                }
                fk.a(httpURLConnection, "application/json; charset=utf-8", bh.a((Object) caVar.e()), jm.c);
            }
        }
        httpURLConnection.connect();
        switch (httpURLConnection.getResponseCode()) {
            case 200:
            case 201:
            case 409:
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    try {
                        uri = httpURLConnection.getURL().toURI();
                        break;
                    } catch (URISyntaxException e) {
                        uri = null;
                    }
                    return caVar.a(uri, inputStream);
                } finally {
                    inputStream.close();
                }
            default:
                throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
        }
    }
}
