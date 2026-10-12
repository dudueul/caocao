package com.naver.plug.android.core.api.request;

import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.multipart.AbstractPart;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BytePart extends AbstractPart {
    private ContentType contentType;
    private int count;
    private File file;
    private String filename;
    private int offset;

    public BytePart(String str, ContentType contentType, File file, int i, int i2) {
        super(str);
        ContentType contentTypeCreateDefaultContentTypeIfNull = createDefaultContentTypeIfNull(contentType);
        this.filename = file.getName();
        this.contentType = contentTypeCreateDefaultContentTypeIfNull;
        this.file = file;
        this.offset = i;
        this.count = i2;
    }

    private ContentType createDefaultContentTypeIfNull(ContentType contentType) {
        return contentType == null ? ContentType.createContentType("application/octet-stream") : contentType;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public InputStream getContent() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.file);
        byte[] bArr = new byte[this.count];
        fileInputStream.skip(this.offset);
        fileInputStream.read(bArr, 0, this.count);
        fileInputStream.close();
        return new ByteArrayInputStream(bArr);
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.AbstractPart
    protected byte[] getExtraHeader() {
        return ("filename=\"" + this.filename + "\"").getBytes();
    }

    public String getFilename() {
        return this.filename;
    }
}
