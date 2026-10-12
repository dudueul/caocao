package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StringPart extends AbstractPart {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final ContentType PLAIN_TEXT = ContentType.CONTENT_TYPE_TEXT_PLAIN;
    private final String charset;
    private final String content;
    private ContentType contentType;

    public StringPart(String str, String str2) {
        this(str, str2, "utf-8");
    }

    public StringPart(String str, String str2, String str3) {
        this(str, str2, "utf-8", PLAIN_TEXT);
    }

    public StringPart(String str, String str2, String str3, ContentType contentType) {
        super(str);
        Assert.notNull(str2, "Content");
        str3 = str3 == null ? "utf-8" : str3;
        this.content = str2;
        this.charset = str3;
        this.contentType = contentType;
    }

    private byte[] getBytesFromContent() throws IOException {
        try {
            return this.content.getBytes(this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new IOException("The character set, " + this.charset + " is not supported.");
        }
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public InputStream getContent() throws IOException {
        byte[] bytesFromContent = getBytesFromContent();
        if (bytesFromContent == null) {
            return null;
        }
        return new ByteArrayInputStream(bytesFromContent);
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.AbstractPart
    protected byte[] getExtraHeader() {
        return null;
    }
}
