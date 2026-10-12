package com.navercorp.volleyextensions.volleyer.factory;

import android.net.http.AndroidHttpClient;
import android.os.Build;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.navercorp.volleyextensions.volleyer.multipart.stack.DefaultMultipartHttpStack;
import com.navercorp.volleyextensions.volleyer.multipart.stack.MultipartHttpStackWrapper;
import org.apache.http.client.HttpClient;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HttpStackFactory {
    private static final String DEFAULT_USER_AGENT = "volleyer";

    private static HttpClient createAndroidHttpClient(String str) {
        return AndroidHttpClient.newInstance(str);
    }

    public static HttpStack createDefaultHttpStack() {
        HttpStack httpStackCreateHttpClientStack;
        HttpClient httpClientCreateAndroidHttpClient = null;
        if (Build.VERSION.SDK_INT >= 9) {
            httpStackCreateHttpClientStack = createHurlStack();
        } else {
            httpClientCreateAndroidHttpClient = createAndroidHttpClient(DEFAULT_USER_AGENT);
            httpStackCreateHttpClientStack = createHttpClientStack(httpClientCreateAndroidHttpClient);
        }
        return new MultipartHttpStackWrapper(httpStackCreateHttpClientStack, new DefaultMultipartHttpStack(httpClientCreateAndroidHttpClient));
    }

    public static HttpClientStack createHttpClientStack(HttpClient httpClient) {
        return new HttpClientStack(httpClient);
    }

    public static HurlStack createHurlStack() {
        return new HurlStack();
    }
}
