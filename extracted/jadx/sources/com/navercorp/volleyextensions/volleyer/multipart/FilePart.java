package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.http.ContentType;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FilePart extends AbstractPart {
    private ContentType contentType;
    private final File file;
    private String filename;

    public FilePart(File file) {
        this(file.getName(), file);
    }

    public FilePart(String str, File file) {
        this(str, file, null);
    }

    public FilePart(String str, File file, ContentType contentType) {
        super(str);
        this.file = file;
        this.contentType = createDefaultContentTypeIfNull(contentType);
        this.filename = file.getName();
    }

    private ContentType createDefaultContentTypeIfNull(ContentType contentType) {
        return contentType == null ? ContentType.createContentType("application/octet-stream") : contentType;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public InputStream getContent() throws IOException {
        if (this.file.exists()) {
            return new BufferedInputStream(new FileInputStream(this.file));
        }
        throw new IOException("The file of parts doesn't exist. [PATH : ] " + this.file.getAbsolutePath());
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
