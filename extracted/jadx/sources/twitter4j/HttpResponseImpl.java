package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class HttpResponseImpl extends HttpResponse {
    private HttpURLConnection con;

    HttpResponseImpl(String str) {
        this.responseAsString = str;
    }

    HttpResponseImpl(HttpURLConnection httpURLConnection, HttpClientConfiguration httpClientConfiguration) throws IOException {
        super(httpClientConfiguration);
        this.con = httpURLConnection;
        try {
            this.statusCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            if (!"Received authentication challenge is null".equals(e.getMessage())) {
                throw e;
            }
            this.statusCode = httpURLConnection.getResponseCode();
        }
        InputStream errorStream = httpURLConnection.getErrorStream();
        this.is = errorStream;
        if (errorStream == null) {
            this.is = httpURLConnection.getInputStream();
        }
        if (this.is == null || !io.fabric.sdk.android.services.network.HttpRequest.ENCODING_GZIP.equals(httpURLConnection.getContentEncoding())) {
            return;
        }
        this.is = new StreamingGZIPInputStream(this.is);
    }

    @Override // twitter4j.HttpResponse
    public void disconnect() {
        this.con.disconnect();
    }

    @Override // twitter4j.HttpResponse
    public String getResponseHeader(String str) {
        return this.con.getHeaderField(str);
    }

    @Override // twitter4j.HttpResponse
    public Map<String, List<String>> getResponseHeaderFields() {
        return this.con.getHeaderFields();
    }
}
