package com.tapjoy.internal;

import android.net.Uri;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fk {
    public static String a(Map map) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            Object key = entry.getKey();
            if (key != null) {
                sb.append(Uri.encode(key.toString()));
            }
            sb.append("=");
            Object value = entry.getValue();
            if (value != null) {
                sb.append(Uri.encode(value.toString()));
            }
        }
        return sb.toString();
    }

    public static void a(HttpURLConnection httpURLConnection, String str, String str2, Charset charset) throws IOException {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, str);
        byte[] bytes = str2.getBytes(charset);
        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
        } finally {
            outputStream.close();
        }
    }
}
