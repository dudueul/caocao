package com.navercorp.volleyextensions.volleyer.http;

import com.navercorp.volleyextensions.volleyer.multipart.Multipart;
import com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer;
import com.navercorp.volleyextensions.volleyer.multipart.Part;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HttpContent implements MultipartContainer {
    private byte[] body;
    private Map<String, String> headers;
    private HttpMethod method;
    private Multipart multipart;
    private String url;

    public HttpContent(String str, HttpMethod httpMethod) {
        Assert.notNull(str, "url");
        Assert.notNull(httpMethod, "HttpMethod");
        this.url = str;
        this.method = httpMethod;
        this.headers = new HashMap();
        this.multipart = new Multipart();
    }

    public void addHeader(String str, String str2) {
        Assert.notNull(str, "Header key");
        Assert.notNull(str2, "Header value");
        this.headers.put(str, str2);
    }

    public void addPart(Part part) {
        this.multipart.add(part);
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getHeader(String str) {
        return this.headers.get(str);
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer
    public Multipart getMultipart() {
        return this.multipart;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer
    public boolean hasMultipart() {
        return !this.multipart.isEmpty();
    }

    public void setBody(byte[] bArr) {
        this.body = bArr;
    }
}
