package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HttpEntityWrapper implements HttpEntity {
    private static final int UNKNOWN_LENGTH = -1;
    private Writable writable;

    public HttpEntityWrapper(Writable writable) {
        Assert.notNull(writable, "Writable");
        this.writable = writable;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IllegalStateException, IOException {
        return null;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return null;
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return -1L;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return null;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return true;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return true;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return true;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        this.writable.write(outputStream);
    }
}
