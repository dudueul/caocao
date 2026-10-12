package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.DefaultLogger;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private static final String HTTPS = "https";
    private boolean attemptedSslInit;
    private final Logger logger;
    private PinningInfoProvider pinningInfo;
    private SSLSocketFactory sslSocketFactory;

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger logger) {
        this.logger = logger;
    }

    private synchronized SSLSocketFactory getSSLSocketFactory() {
        if (this.sslSocketFactory == null && !this.attemptedSslInit) {
            this.sslSocketFactory = initSSLSocketFactory();
        }
        return this.sslSocketFactory;
    }

    private synchronized SSLSocketFactory initSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        this.attemptedSslInit = true;
        try {
            sSLSocketFactory = NetworkUtils.getSSLSocketFactory(this.pinningInfo);
            this.logger.d(Fabric.TAG, "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.logger.e(Fabric.TAG, "Exception while validating pinned certs", e);
            sSLSocketFactory = null;
        }
        return sSLSocketFactory;
    }

    private boolean isHttps(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    private synchronized void resetSSLSocketFactory() {
        this.attemptedSslInit = false;
        this.sslSocketFactory = null;
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str) {
        return buildHttpRequest(httpMethod, str, Collections.emptyMap());
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest httpRequestDelete;
        SSLSocketFactory sSLSocketFactory;
        switch (httpMethod) {
            case GET:
                httpRequestDelete = HttpRequest.get((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case POST:
                httpRequestDelete = HttpRequest.post((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case PUT:
                httpRequestDelete = HttpRequest.put(str);
                break;
            case DELETE:
                httpRequestDelete = HttpRequest.delete(str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (isHttps(str) && this.pinningInfo != null && (sSLSocketFactory = getSSLSocketFactory()) != null) {
            ((HttpsURLConnection) httpRequestDelete.getConnection()).setSSLSocketFactory(sSLSocketFactory);
        }
        return httpRequestDelete;
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public PinningInfoProvider getPinningInfoProvider() {
        return this.pinningInfo;
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public void setPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        if (this.pinningInfo != pinningInfoProvider) {
            this.pinningInfo = pinningInfoProvider;
            resetSSLSocketFactory();
        }
    }
}
