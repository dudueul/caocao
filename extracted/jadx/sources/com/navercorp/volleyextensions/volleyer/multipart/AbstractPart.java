package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import com.navercorp.volleyextensions.volleyer.util.IoUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class AbstractPart implements Part {
    private static final byte[] CONTENT_DISPOSITION = "Content-Disposition: form-data;".getBytes();
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private String name;

    public AbstractPart(String str) {
        Assert.notNull(str, "Part name");
        this.name = str;
    }

    private void writeCRLF(OutputStream outputStream) throws IOException {
        outputStream.write("\r\n".getBytes());
    }

    private void writeContentDisposition(OutputStream outputStream) throws IOException {
        outputStream.write(CONTENT_DISPOSITION);
    }

    private void writeContentTypeIfNotNull(OutputStream outputStream) throws IOException {
        ContentType contentType = getContentType();
        if (contentType == null) {
            return;
        }
        outputStream.write(("Content-Type: " + contentType).getBytes());
        writeCRLF(outputStream);
    }

    private void writeLine(OutputStream outputStream, String str) throws IOException {
        outputStream.write((str + "\n").getBytes());
    }

    private void writeName(OutputStream outputStream) throws IOException {
        outputStream.write((" name=\"" + getName() + "\"").getBytes());
    }

    protected abstract byte[] getExtraHeader();

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Part
    public String getName() {
        return this.name;
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Writable
    public void write(OutputStream outputStream) throws IOException {
        writeContentDisposition(outputStream);
        writeName(outputStream);
        writeExtraHeaderIfNotNull(outputStream);
        writeCRLF(outputStream);
        writeContentTypeIfNotNull(outputStream);
        writeCRLF(outputStream);
        writeContent(outputStream);
        writeCRLF(outputStream);
    }

    protected final void writeContent(OutputStream outputStream) throws IOException {
        try {
            InputStream content = getContent();
            if (content == null) {
                IoUtils.closeQuietly(content);
                return;
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int i = content.read(bArr);
                if (i == -1) {
                    IoUtils.closeQuietly(content);
                    return;
                }
                outputStream.write(bArr, 0, i < 1024 ? i : 1024);
            }
        } catch (Throwable th) {
            IoUtils.closeQuietly(null);
            throw th;
        }
    }

    protected final void writeExtraHeaderIfNotNull(OutputStream outputStream) throws IOException {
        byte[] extraHeader = getExtraHeader();
        if (extraHeader == null) {
            return;
        }
        outputStream.write(59);
        outputStream.write(extraHeader);
    }
}
