package com.nexon.nexonanalyticssdk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxRequest implements Callable<NxResponse> {
    private int connectionTimeout;
    private String headerKey;
    private String headerValue;
    private String method;
    private String postData;
    private int readTimeout;
    private URL url;

    public static class Builder {
        private String headerKey;
        private String headerValue;
        private String postData;
        private URL url;
        private String method = "GET";
        private int readTimeout = 30000;
        private int connectionTimeout = 30000;

        public NxRequest build() {
            return new NxRequest(this);
        }

        public Builder connectionTimeout(int i) {
            this.connectionTimeout = i;
            return this;
        }

        public Builder extraHeader(String str, String str2) {
            this.headerKey = str;
            this.headerValue = str2;
            return this;
        }

        public Builder post(String str) {
            this.postData = str;
            this.method = "POST";
            return this;
        }

        public Builder readTimeout(int i) {
            this.readTimeout = i;
            return this;
        }

        public Builder url(String str) throws MalformedURLException {
            this.url = new URL(str);
            return this;
        }
    }

    public interface Type {
        public static final String GET = "GET";
        public static final String POST = "POST";
    }

    private NxRequest(Builder builder) {
        this.url = builder.url;
        this.postData = builder.postData;
        this.method = builder.method;
        this.readTimeout = builder.readTimeout;
        this.connectionTimeout = builder.connectionTimeout;
        this.headerKey = builder.headerKey;
        this.headerValue = builder.headerValue;
    }

    private NxResponse requestGet(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        NxLogcat.i("Status code : " + responseCode);
        InputStream inputStream = responseCode == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        NxUtils.close(inputStream);
        NxUtils.close(bufferedReader);
        return new NxResponse(responseCode, line);
    }

    private NxResponse requestPost(HttpURLConnection httpURLConnection) throws IOException {
        String line = null;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        bufferedWriter.write(this.postData);
        bufferedWriter.flush();
        int responseCode = httpURLConnection.getResponseCode();
        NxLogcat.i("Status code : " + responseCode);
        InputStream errorStream = null;
        BufferedReader bufferedReader = null;
        if (responseCode != 200) {
            errorStream = httpURLConnection.getErrorStream();
            bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
            line = bufferedReader.readLine();
        }
        NxUtils.close(errorStream);
        NxUtils.close(bufferedReader);
        NxUtils.close(bufferedWriter);
        return new NxResponse(responseCode, line);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public NxResponse call() {
        NxResponse nxResponseRequestGet = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.url.openConnection();
            httpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            httpURLConnection.setRequestMethod(this.method);
            httpURLConnection.setReadTimeout(this.readTimeout);
            httpURLConnection.setConnectTimeout(this.connectionTimeout);
            if (this.method == "POST") {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);
            }
            if (this.headerKey != null) {
                httpURLConnection.setRequestProperty(this.headerKey, this.headerValue);
            }
            httpURLConnection.connect();
            if (this.method == "POST") {
                nxResponseRequestGet = requestPost(httpURLConnection);
            } else if (this.method == "GET") {
                nxResponseRequestGet = requestGet(httpURLConnection);
            }
            httpURLConnection.disconnect();
        } catch (ProtocolException e) {
            NxLogcat.e("Protocol error : " + e.getMessage());
        } catch (IOException e2) {
            NxLogcat.e("IOException : " + e2.getMessage());
        }
        return nxResponseRequestGet;
    }
}
