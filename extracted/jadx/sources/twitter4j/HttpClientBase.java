package twitter4j;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import twitter4j.auth.Authorization;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class HttpClientBase implements HttpClient, Serializable {
    private static final Logger logger = Logger.getLogger(HttpClientBase.class);
    private static final long serialVersionUID = -8016974810651763053L;
    protected final HttpClientConfiguration CONF;
    private final Map<String, String> requestHeaders = new HashMap();

    public HttpClientBase(HttpClientConfiguration httpClientConfiguration) {
        this.CONF = httpClientConfiguration;
        this.requestHeaders.put("X-Twitter-Client-Version", Version.getVersion());
        this.requestHeaders.put("X-Twitter-Client-URL", "http://twitter4j.org/en/twitter4j-" + Version.getVersion() + ".xml");
        this.requestHeaders.put("X-Twitter-Client", "Twitter4J");
        this.requestHeaders.put("User-Agent", "twitter4j http://twitter4j.org/ /" + Version.getVersion());
        if (httpClientConfiguration.isGZIPEnabled()) {
            this.requestHeaders.put(io.fabric.sdk.android.services.network.HttpRequest.HEADER_ACCEPT_ENCODING, io.fabric.sdk.android.services.network.HttpRequest.ENCODING_GZIP);
        }
    }

    @Override // twitter4j.HttpClient
    public void addDefaultRequestHeader(String str, String str2) {
        this.requestHeaders.put(str, str2);
    }

    @Override // twitter4j.HttpClient
    public HttpResponse delete(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.DELETE, str, null, null, this.requestHeaders));
    }

    @Override // twitter4j.HttpClient
    public HttpResponse delete(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.DELETE, str, httpParameterArr, authorization, this.requestHeaders), httpResponseListener);
    }

    @Override // twitter4j.HttpClient
    public HttpResponse get(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, str, null, null, this.requestHeaders));
    }

    @Override // twitter4j.HttpClient
    public HttpResponse get(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, str, httpParameterArr, authorization, this.requestHeaders), httpResponseListener);
    }

    @Override // twitter4j.HttpClient
    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    abstract HttpResponse handleRequest(HttpRequest httpRequest) throws TwitterException;

    @Override // twitter4j.HttpClient
    public HttpResponse head(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.HEAD, str, null, null, this.requestHeaders));
    }

    protected boolean isProxyConfigured() {
        return (this.CONF.getHttpProxyHost() == null || this.CONF.getHttpProxyHost().equals("")) ? false : true;
    }

    @Override // twitter4j.HttpClient
    public HttpResponse post(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, str, null, null, this.requestHeaders));
    }

    @Override // twitter4j.HttpClient
    public HttpResponse post(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, authorization, this.requestHeaders), httpResponseListener);
    }

    @Override // twitter4j.HttpClient
    public HttpResponse put(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.PUT, str, null, null, this.requestHeaders));
    }

    @Override // twitter4j.HttpClient
    public HttpResponse put(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.PUT, str, httpParameterArr, authorization, this.requestHeaders), httpResponseListener);
    }

    @Override // twitter4j.HttpClient
    public final HttpResponse request(HttpRequest httpRequest) throws TwitterException {
        return handleRequest(httpRequest);
    }

    @Override // twitter4j.HttpClient
    public final HttpResponse request(HttpRequest httpRequest, HttpResponseListener httpResponseListener) throws TwitterException {
        try {
            HttpResponse httpResponseHandleRequest = handleRequest(httpRequest);
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(httpRequest, httpResponseHandleRequest, null));
            }
            return httpResponseHandleRequest;
        } catch (TwitterException e) {
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(httpRequest, null, e));
            }
            throw e;
        }
    }

    public void write(DataOutputStream dataOutputStream, String str) throws IOException {
        dataOutputStream.writeBytes(str);
        logger.debug(str);
    }
}
