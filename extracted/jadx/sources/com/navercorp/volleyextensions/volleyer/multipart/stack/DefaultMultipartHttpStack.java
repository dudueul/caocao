package com.navercorp.volleyextensions.volleyer.multipart.stack;

import android.net.http.AndroidHttpClient;
import android.os.Build;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultMultipartHttpStack implements MultipartHttpStack {
    private static final String DEFAULT_USER_AGENT = "volleyer-multipart";
    private MultipartHttpStack stack;

    public DefaultMultipartHttpStack() {
        determineMultipartStack(DEFAULT_USER_AGENT);
    }

    public DefaultMultipartHttpStack(String str) {
        determineMultipartStack(str);
    }

    public DefaultMultipartHttpStack(HttpClient httpClient) {
        determineMultipartStack(httpClient);
    }

    private HttpClient createHttpClientIfNull(HttpClient httpClient) {
        return httpClient != null ? httpClient : AndroidHttpClient.newInstance(DEFAULT_USER_AGENT);
    }

    private void determineMultipartStack(String str) {
        determineMultipartStack(AndroidHttpClient.newInstance(str));
    }

    private void determineMultipartStack(HttpClient httpClient) {
        if (Build.VERSION.SDK_INT >= 9) {
            this.stack = new MultipartHurlStack();
        } else {
            this.stack = new MultipartHttpClientStack(createHttpClientIfNull(httpClient));
        }
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        return this.stack.performRequest(request, map);
    }
}
