package kr.co.nexon.mdev.network;

import com.adjust.sdk.Constants;
import com.nexon.core.util.NXKeyValuePair;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXHttpURLRequest {
    private static final int BUFFER_SIZE = 65535;
    private static final String GZIP = "gzip";
    private static final String HTTP_HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String HTTP_HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HTTP_HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    private byte[] bodyBytes;
    private NXHttpURLRequestDelegate delegate;
    private String method;
    protected int requestTag;
    protected String targetURL;
    protected int connectionTimeOut = 30000;
    protected int readTimeOut = 30000;
    protected ArrayList<NXKeyValuePair<String, String>> args = new ArrayList<>();
    private HashMap<String, String> headers = new HashMap<>();

    public NXHttpURLRequest(String str, NXHttpURLRequestDelegate nXHttpURLRequestDelegate, String str2, int i) {
        this.targetURL = str2;
        this.delegate = nXHttpURLRequestDelegate;
        this.method = str;
        this.requestTag = i;
    }

    private boolean checkGzip(URLConnection uRLConnection) {
        String requestProperty = uRLConnection.getRequestProperty("Accept-Encoding");
        return requestProperty != null && requestProperty.toLowerCase().contains("gzip") && uRLConnection.getHeaderField("Content-Encoding") != null && uRLConnection.getHeaderField("Content-Encoding").toLowerCase().contains("gzip");
    }

    private byte[] getResponseBody(URLConnection uRLConnection) throws IOException {
        InputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if ((uRLConnection.getURL().getProtocol().equals(Constants.SCHEME) ? ((HttpsURLConnection) uRLConnection).getResponseCode() : ((HttpURLConnection) uRLConnection).getResponseCode()) == 200) {
            bufferedInputStream = new BufferedInputStream(uRLConnection.getInputStream());
        } else {
            bufferedInputStream = uRLConnection.getURL().getProtocol().equals(Constants.SCHEME) ? new BufferedInputStream(((HttpsURLConnection) uRLConnection).getErrorStream()) : new BufferedInputStream(((HttpURLConnection) uRLConnection).getErrorStream());
        }
        String headerField = uRLConnection.getHeaderField("Content-Length");
        int i = headerField == null ? -1 : Integer.parseInt(headerField);
        byte[] bArr = new byte[65535];
        if (checkGzip(uRLConnection)) {
            bufferedInputStream = new GZIPInputStream(bufferedInputStream);
        }
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                bufferedInputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            this.delegate.onDataReceive(bArr, i, byteArrayOutputStream.size());
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    private URLConnection getURLConnection() throws IOException {
        if (!this.targetURL.startsWith(Constants.SCHEME)) {
            return (HttpURLConnection) new URL(this.targetURL).openConnection();
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(this.targetURL).openConnection();
        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: kr.co.nexon.mdev.network.NXHttpURLRequest.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        return httpsURLConnection;
    }

    private void processHeaderSetup(URLConnection uRLConnection) {
        if (this.headers.size() > 0) {
            for (String str : this.headers.keySet()) {
                uRLConnection.setRequestProperty(str, this.headers.get(str));
            }
        }
    }

    private void processPostMethod(URLConnection uRLConnection) throws IOException {
        byte[] bytes = null;
        if ("POST".equalsIgnoreCase(this.method)) {
            uRLConnection.setDoOutput(true);
            uRLConnection.setDoInput(true);
            if (this.bodyBytes != null && this.bodyBytes.length > 0) {
                bytes = this.bodyBytes;
            } else if (this.args.size() > 0) {
                String str = "";
                for (int i = 0; i < this.args.size(); i++) {
                    NXKeyValuePair<String, String> nXKeyValuePair = this.args.get(i);
                    str = str + "" + nXKeyValuePair.key + "=" + nXKeyValuePair.value;
                    if (i < this.args.size() - 1) {
                        str = str + "&";
                    }
                }
                bytes = str.getBytes();
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(uRLConnection.getOutputStream());
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }
    }

    private NXHttpURLRequestResult request() {
        NXHttpURLRequestResult nXHttpURLRequestResult = new NXHttpURLRequestResult();
        nXHttpURLRequestResult.tag = this.requestTag;
        try {
            try {
                try {
                    try {
                        URLConnection uRLConnection = getURLConnection();
                        if (uRLConnection == null) {
                            nXHttpURLRequestResult = null;
                            if (uRLConnection != null) {
                                ((HttpURLConnection) uRLConnection).disconnect();
                            }
                        } else {
                            uRLConnection.setConnectTimeout(this.connectionTimeOut);
                            uRLConnection.setReadTimeout(this.readTimeOut);
                            processHeaderSetup(uRLConnection);
                            processPostMethod(uRLConnection);
                            if (uRLConnection.getURL().getProtocol().equals(Constants.SCHEME)) {
                                nXHttpURLRequestResult.httpStatus = ((HttpsURLConnection) uRLConnection).getResponseCode();
                            } else {
                                nXHttpURLRequestResult.httpStatus = ((HttpURLConnection) uRLConnection).getResponseCode();
                            }
                            nXHttpURLRequestResult.responseHeaders = uRLConnection.getHeaderFields();
                            nXHttpURLRequestResult.bytes = getResponseBody(uRLConnection);
                            if (uRLConnection != null) {
                                ((HttpURLConnection) uRLConnection).disconnect();
                            }
                        }
                    } catch (IOException e) {
                        nXHttpURLRequestResult.bErr = true;
                        nXHttpURLRequestResult.errmsg = e.toString();
                        if (0 != 0) {
                            ((HttpURLConnection) null).disconnect();
                        }
                    }
                } catch (SocketTimeoutException e2) {
                    nXHttpURLRequestResult.bErr = true;
                    nXHttpURLRequestResult.errmsg = e2.toString();
                    if (0 != 0) {
                        ((HttpURLConnection) null).disconnect();
                    }
                }
            } catch (MalformedURLException e3) {
                nXHttpURLRequestResult.bErr = true;
                nXHttpURLRequestResult.errmsg = e3.toString();
                if (0 != 0) {
                    ((HttpURLConnection) null).disconnect();
                }
            } catch (Exception e4) {
                nXHttpURLRequestResult.bErr = true;
                nXHttpURLRequestResult.errmsg = e4.toString();
                if (0 != 0) {
                    ((HttpURLConnection) null).disconnect();
                }
            }
            return nXHttpURLRequestResult;
        } catch (Throwable th) {
            if (0 != 0) {
                ((HttpURLConnection) null).disconnect();
            }
            throw th;
        }
    }

    public void addParameter(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        this.args.add(new NXKeyValuePair<>(str, str2));
    }

    public void exec() {
        NXHttpURLRequestResult nXHttpURLRequestResultRequest = request();
        if (nXHttpURLRequestResultRequest == null) {
            this.delegate.onError(new Exception("result is null"));
        } else if (nXHttpURLRequestResultRequest.bErr) {
            this.delegate.onError(new Exception(nXHttpURLRequestResultRequest.errmsg));
        } else {
            this.delegate.onComplete(nXHttpURLRequestResultRequest);
        }
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeOut = i;
    }

    public void setHTTPBody(byte[] bArr) {
        this.bodyBytes = bArr;
    }

    public void setHeader(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        this.headers.put(str, str2);
    }

    public void setReadTimeout(int i) {
        this.readTimeOut = i;
    }
}
