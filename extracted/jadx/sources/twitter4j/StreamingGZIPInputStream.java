package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class StreamingGZIPInputStream extends GZIPInputStream {
    private final InputStream wrapped;

    public StreamingGZIPInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.wrapped = inputStream;
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.wrapped.available();
    }
}
