package twitter4j;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import twitter4j.conf.ConfigurationContext;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class HttpClientImpl extends HttpClientBase implements HttpResponseCode, Serializable {
    private static final Map<HttpClientConfiguration, HttpClient> instanceMap;
    private static final Logger logger = Logger.getLogger(HttpClientImpl.class);
    private static final long serialVersionUID = -403500272719330534L;

    static {
        try {
            if (Integer.parseInt((String) Class.forName("android.os.Build$VERSION").getField("SDK").get(null)) < 8) {
                System.setProperty("http.keepAlive", "false");
            }
        } catch (Exception e) {
        }
        instanceMap = new HashMap(1);
    }

    public HttpClientImpl() {
        super(ConfigurationContext.getInstance().getHttpClientConfiguration());
    }

    public HttpClientImpl(HttpClientConfiguration httpClientConfiguration) {
        super(httpClientConfiguration);
    }

    public static HttpClient getInstance(HttpClientConfiguration httpClientConfiguration) {
        HttpClient httpClient = instanceMap.get(httpClientConfiguration);
        if (httpClient != null) {
            return httpClient;
        }
        HttpClientImpl httpClientImpl = new HttpClientImpl(httpClientConfiguration);
        instanceMap.put(httpClientConfiguration, httpClientImpl);
        return httpClientImpl;
    }

    private void setHeaders(HttpRequest httpRequest, HttpURLConnection httpURLConnection) {
        String authorizationHeader;
        if (logger.isDebugEnabled()) {
            logger.debug("Request: ");
            logger.debug(String.valueOf(httpRequest.getMethod().name()) + " ", httpRequest.getURL());
        }
        if (httpRequest.getAuthorization() != null && (authorizationHeader = httpRequest.getAuthorization().getAuthorizationHeader(httpRequest)) != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authorization: ", authorizationHeader.replaceAll(".", "*"));
            }
            httpURLConnection.addRequestProperty(io.fabric.sdk.android.services.network.HttpRequest.HEADER_AUTHORIZATION, authorizationHeader);
        }
        if (httpRequest.getRequestHeaders() != null) {
            for (String str : httpRequest.getRequestHeaders().keySet()) {
                httpURLConnection.addRequestProperty(str, httpRequest.getRequestHeaders().get(str));
                logger.debug(String.valueOf(str) + ": " + httpRequest.getRequestHeaders().get(str));
            }
        }
    }

    @Override // twitter4j.HttpClientBase, twitter4j.HttpClient
    public HttpResponse get(String str) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, str, null, null, null));
    }

    HttpURLConnection getConnection(String str) throws IOException {
        HttpURLConnection httpURLConnection;
        if (isProxyConfigured()) {
            if (this.CONF.getHttpProxyUser() != null && !this.CONF.getHttpProxyUser().equals("")) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Proxy AuthUser: " + this.CONF.getHttpProxyUser());
                    logger.debug("Proxy AuthPassword: " + this.CONF.getHttpProxyPassword().replaceAll(".", "*"));
                }
                Authenticator.setDefault(new Authenticator() { // from class: twitter4j.HttpClientImpl.1
                    @Override // java.net.Authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        if (getRequestorType().equals(Authenticator.RequestorType.PROXY)) {
                            return new PasswordAuthentication(HttpClientImpl.this.CONF.getHttpProxyUser(), HttpClientImpl.this.CONF.getHttpProxyPassword().toCharArray());
                        }
                        return null;
                    }
                });
            }
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(this.CONF.getHttpProxyHost(), this.CONF.getHttpProxyPort()));
            if (logger.isDebugEnabled()) {
                logger.debug("Opening proxied connection(" + this.CONF.getHttpProxyHost() + ":" + this.CONF.getHttpProxyPort() + ")");
            }
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection(proxy);
        } else {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        }
        if (this.CONF.getHttpConnectionTimeout() > 0) {
            httpURLConnection.setConnectTimeout(this.CONF.getHttpConnectionTimeout());
        }
        if (this.CONF.getHttpReadTimeout() > 0) {
            httpURLConnection.setReadTimeout(this.CONF.getHttpReadTimeout());
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }

    @Override // twitter4j.HttpClientBase
    public HttpResponse handleRequest(HttpRequest httpRequest) throws Throwable {
        int httpRetryCount = this.CONF.getHttpRetryCount() + 1;
        HttpResponseImpl httpResponseImpl = null;
        int i = 0;
        while (true) {
            HttpResponseImpl httpResponseImpl2 = httpResponseImpl;
            if (i >= httpRetryCount) {
                return httpResponseImpl2;
            }
            OutputStream outputStream = null;
            try {
                HttpURLConnection connection = getConnection(httpRequest.getURL());
                connection.setDoInput(true);
                setHeaders(httpRequest, connection);
                connection.setRequestMethod(httpRequest.getMethod().name());
                if (httpRequest.getMethod() == RequestMethod.POST) {
                    if (HttpParameter.containsFile(httpRequest.getParameters())) {
                        String str = "----Twitter4J-upload" + System.currentTimeMillis();
                        connection.setRequestProperty(io.fabric.sdk.android.services.network.HttpRequest.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=" + str);
                        String str2 = "--" + str;
                        connection.setDoOutput(true);
                        outputStream = connection.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        for (HttpParameter httpParameter : httpRequest.getParameters()) {
                            if (httpParameter.isFile()) {
                                write(dataOutputStream, String.valueOf(str2) + "\r\n");
                                write(dataOutputStream, "Content-Disposition: form-data; name=\"" + httpParameter.getName() + "\"; filename=\"" + httpParameter.getFile().getName() + "\"\r\n");
                                write(dataOutputStream, "Content-Type: " + httpParameter.getContentType() + "\r\n\r\n");
                                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpParameter.hasFileBody() ? httpParameter.getFileBody() : new FileInputStream(httpParameter.getFile()));
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i2 = bufferedInputStream.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    dataOutputStream.write(bArr, 0, i2);
                                }
                                write(dataOutputStream, "\r\n");
                                bufferedInputStream.close();
                            } else {
                                write(dataOutputStream, String.valueOf(str2) + "\r\n");
                                write(dataOutputStream, "Content-Disposition: form-data; name=\"" + httpParameter.getName() + "\"\r\n");
                                write(dataOutputStream, "Content-Type: text/plain; charset=UTF-8\r\n\r\n");
                                logger.debug(httpParameter.getValue());
                                dataOutputStream.write(httpParameter.getValue().getBytes("UTF-8"));
                                write(dataOutputStream, "\r\n");
                            }
                        }
                        write(dataOutputStream, String.valueOf(str2) + "--\r\n");
                        write(dataOutputStream, "\r\n");
                    } else {
                        connection.setRequestProperty(io.fabric.sdk.android.services.network.HttpRequest.HEADER_CONTENT_TYPE, io.fabric.sdk.android.services.network.HttpRequest.CONTENT_TYPE_FORM);
                        String strEncodeParameters = HttpParameter.encodeParameters(httpRequest.getParameters());
                        logger.debug("Post Params: ", strEncodeParameters);
                        byte[] bytes = strEncodeParameters.getBytes("UTF-8");
                        connection.setRequestProperty(io.fabric.sdk.android.services.network.HttpRequest.HEADER_CONTENT_LENGTH, Integer.toString(bytes.length));
                        connection.setDoOutput(true);
                        outputStream = connection.getOutputStream();
                        outputStream.write(bytes);
                    }
                    outputStream.flush();
                    outputStream.close();
                }
                httpResponseImpl = new HttpResponseImpl(connection, this.CONF);
                try {
                    int responseCode = connection.getResponseCode();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Response: ");
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        for (String str3 : headerFields.keySet()) {
                            for (String str4 : headerFields.get(str3)) {
                                if (str3 != null) {
                                    logger.debug(String.valueOf(str3) + ": " + str4);
                                } else {
                                    logger.debug(str4);
                                }
                            }
                        }
                    }
                    if (responseCode >= 200 && (responseCode == 302 || 300 > responseCode)) {
                        if (outputStream == null) {
                            return httpResponseImpl;
                        }
                        try {
                            outputStream.close();
                            return httpResponseImpl;
                        } catch (Exception e) {
                            return httpResponseImpl;
                        }
                    }
                    if (responseCode == 420 || responseCode == 400 || responseCode < 500 || i == this.CONF.getHttpRetryCount()) {
                        throw new TwitterException(httpResponseImpl.asString(), httpResponseImpl);
                    }
                    if (outputStream != null) {
                        try {
                            try {
                                outputStream.close();
                            } catch (IOException e2) {
                                if (i == this.CONF.getHttpRetryCount()) {
                                    throw new TwitterException(e2.getMessage(), e2, responseCode);
                                }
                            }
                        } catch (Exception e3) {
                        }
                    }
                    try {
                        if (logger.isDebugEnabled() && httpResponseImpl != null) {
                            httpResponseImpl.asString();
                        }
                        logger.debug("Sleeping " + this.CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
                        Thread.sleep(this.CONF.getHttpRetryIntervalSeconds() * 1000);
                    } catch (InterruptedException e4) {
                    }
                    i++;
                } catch (Throwable th) {
                    th = th;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, null, null));
    }
}
