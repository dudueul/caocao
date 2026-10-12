package com.navercorp.volleyextensions.volleyer.multipart.stack;

import com.adjust.sdk.Constants;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.navercorp.volleyextensions.volleyer.multipart.Multipart;
import com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MultipartHurlStack implements MultipartHttpStack {
    private static final int DEFAULT_CHUNK_LENGTH = 1024;
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;

    public interface UrlRewriter {
        String rewriteUrl(String str);
    }

    public MultipartHurlStack() {
        this(null);
    }

    public MultipartHurlStack(UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }

    public MultipartHurlStack(UrlRewriter urlRewriter, SSLSocketFactory sSLSocketFactory) {
        this.mUrlRewriter = urlRewriter;
        this.mSslSocketFactory = sSLSocketFactory;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void addMultipartIfExists(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        OutputStream outputStream = null;
        try {
            if (!(request instanceof MultipartContainer)) {
                if (outputStream != null) {
                    return;
                } else {
                    return;
                }
            }
            MultipartContainer multipartContainer = (MultipartContainer) request;
            if (!multipartContainer.hasMultipart()) {
                if (outputStream != null) {
                    return;
                } else {
                    return;
                }
            }
            Multipart multipart = multipartContainer.getMultipart();
            if (multipart == null) {
                if (outputStream != null) {
                    return;
                } else {
                    return;
                }
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", multipart.getContentType());
            httpURLConnection.setChunkedStreamingMode(1024);
            outputStream = httpURLConnection.getOutputStream();
            multipart.write(outputStream);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private static HttpEntity entityFromConnection(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength(httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    private HttpURLConnection openConnection(URL url, Request<?> request) throws IOException {
        HttpURLConnection httpURLConnectionCreateConnection = createConnection(url);
        int timeoutMs = request.getTimeoutMs();
        httpURLConnectionCreateConnection.setConnectTimeout(timeoutMs);
        httpURLConnectionCreateConnection.setReadTimeout(timeoutMs);
        httpURLConnectionCreateConnection.setUseCaches(false);
        httpURLConnectionCreateConnection.setDoInput(true);
        if (Constants.SCHEME.equals(url.getProtocol()) && this.mSslSocketFactory != null) {
            ((HttpsURLConnection) httpURLConnectionCreateConnection).setSSLSocketFactory(this.mSslSocketFactory);
        }
        return httpURLConnectionCreateConnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                addMultipartIfExists(httpURLConnection, request);
                return;
            case 2:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_PUT);
                addMultipartIfExists(httpURLConnection, request);
                return;
            case 3:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_DELETE);
                return;
            case 4:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_HEAD);
                return;
            case 5:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_OPTIONS);
                return;
            case 6:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_TRACE);
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                addMultipartIfExists(httpURLConnection, request);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    protected HttpURLConnection createConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        String url = request.getUrl();
        HashMap map2 = new HashMap();
        map2.putAll(request.getHeaders());
        map2.putAll(map);
        if (this.mUrlRewriter != null) {
            String strRewriteUrl = this.mUrlRewriter.rewriteUrl(url);
            if (strRewriteUrl == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
            url = strRewriteUrl;
        }
        HttpURLConnection httpURLConnectionOpenConnection = openConnection(new URL(url), request);
        for (String str : map2.keySet()) {
            httpURLConnectionOpenConnection.addRequestProperty(str, (String) map2.get(str));
        }
        setConnectionParametersForRequest(httpURLConnectionOpenConnection, request);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (httpURLConnectionOpenConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, httpURLConnectionOpenConnection.getResponseCode(), httpURLConnectionOpenConnection.getResponseMessage()));
        basicHttpResponse.setEntity(entityFromConnection(httpURLConnectionOpenConnection));
        for (Map.Entry<String, List<String>> entry : httpURLConnectionOpenConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader(entry.getKey(), entry.getValue().get(0)));
            }
        }
        return basicHttpResponse;
    }
}
