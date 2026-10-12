package io.fabric.sdk.android.services.common;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        Element(int i, int i2) {
            this.position = i;
            this.length = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }

    private final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int i = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return i;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            QueueFile.nonNull(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (this.remaining <= 0) {
                return -1;
            }
            if (i2 > this.remaining) {
                i2 = this.remaining;
            }
            QueueFile.this.ringRead(this.position, bArr, i, i2);
            this.position = QueueFile.this.wrapPosition(this.position + i2);
            this.remaining -= i2;
            return i2;
        }
    }

    public interface ElementReader {
        void read(InputStream inputStream, int i) throws IOException;
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    QueueFile(RandomAccessFile randomAccessFile) throws IOException {
        this.buffer = new byte[16];
        this.raf = randomAccessFile;
        readHeader();
    }

    private void expandIfNecessary(int i) throws IOException {
        int i2;
        int i3 = i + 4;
        int iRemainingBytes = remainingBytes();
        if (iRemainingBytes >= i3) {
            return;
        }
        int i4 = this.fileLength;
        do {
            iRemainingBytes += i4;
            i2 = i4 << 1;
            i4 = i2;
        } while (iRemainingBytes < i3);
        setLength(i2);
        int iWrapPosition = wrapPosition(this.last.position + 4 + this.last.length);
        if (iWrapPosition < this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            int i5 = iWrapPosition - 4;
            if (channel.transferTo(16L, i5, channel) != i5) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        if (this.last.position < this.first.position) {
            int i6 = (this.fileLength + this.last.position) - 16;
            writeHeader(i2, this.elementCount, this.first.position, i6);
            this.last = new Element(i6, this.last.length);
        } else {
            writeHeader(i2, this.elementCount, this.first.position, this.last.position);
        }
        this.fileLength = i2;
    }

    private static void initialize(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile randomAccessFileOpen = open(file2);
        try {
            randomAccessFileOpen.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            randomAccessFileOpen.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            randomAccessFileOpen.write(bArr);
            randomAccessFileOpen.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            randomAccessFileOpen.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t, String str) {
        if (t == null) {
            throw new NullPointerException(str);
        }
        return t;
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private Element readElement(int i) throws IOException {
        if (i == 0) {
            return Element.NULL;
        }
        this.raf.seek(i);
        return new Element(i, this.raf.readInt());
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        this.fileLength = readInt(this.buffer, 0);
        if (this.fileLength > this.raf.length()) {
            throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
        }
        this.elementCount = readInt(this.buffer, 4);
        int i = readInt(this.buffer, 8);
        int i2 = readInt(this.buffer, 12);
        this.first = readElement(i);
        this.last = readElement(i2);
    }

    private static int readInt(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int i, byte[] bArr, int i2, int i3) throws IOException {
        int iWrapPosition = wrapPosition(i);
        if (iWrapPosition + i3 <= this.fileLength) {
            this.raf.seek(iWrapPosition);
            this.raf.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.fileLength - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.readFully(bArr, i2, i4);
        this.raf.seek(16L);
        this.raf.readFully(bArr, i2 + i4, i3 - i4);
    }

    private void ringWrite(int i, byte[] bArr, int i2, int i3) throws IOException {
        int iWrapPosition = wrapPosition(i);
        if (iWrapPosition + i3 <= this.fileLength) {
            this.raf.seek(iWrapPosition);
            this.raf.write(bArr, i2, i3);
            return;
        }
        int i4 = this.fileLength - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.write(bArr, i2, i4);
        this.raf.seek(16L);
        this.raf.write(bArr, i2 + i4, i3 - i4);
    }

    private void setLength(int i) throws IOException {
        this.raf.setLength(i);
        this.raf.getChannel().force(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int i) {
        return i < this.fileLength ? i : (i + 16) - this.fileLength;
    }

    private void writeHeader(int i, int i2, int i3, int i4) throws IOException {
        writeInts(this.buffer, i, i2, i3, i4);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private static void writeInt(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i = 0;
        for (int i2 : iArr) {
            writeInt(bArr, i, i2);
            i += 4;
        }
    }

    public void add(byte[] bArr) throws IOException {
        add(bArr, 0, bArr.length);
    }

    public synchronized void add(byte[] bArr, int i, int i2) throws IOException {
        nonNull(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary(i2);
        boolean zIsEmpty = isEmpty();
        Element element = new Element(zIsEmpty ? 16 : wrapPosition(this.last.position + 4 + this.last.length), i2);
        writeInt(this.buffer, 0, i2);
        ringWrite(element.position, this.buffer, 0, 4);
        ringWrite(element.position + 4, bArr, i, i2);
        writeHeader(this.fileLength, this.elementCount + 1, zIsEmpty ? element.position : this.first.position, element.position);
        this.last = element;
        this.elementCount++;
        if (zIsEmpty) {
            this.first = this.last;
        }
    }

    public synchronized void clear() throws IOException {
        writeHeader(4096, 0, 0, 0);
        this.elementCount = 0;
        this.first = Element.NULL;
        this.last = Element.NULL;
        if (this.fileLength > 4096) {
            setLength(4096);
        }
        this.fileLength = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.raf.close();
    }

    public synchronized void forEach(ElementReader elementReader) throws IOException {
        int iWrapPosition = this.first.position;
        for (int i = 0; i < this.elementCount; i++) {
            Element element = readElement(iWrapPosition);
            elementReader.read(new ElementInputStream(element), element.length);
            iWrapPosition = wrapPosition(element.position + 4 + element.length);
        }
    }

    public boolean hasSpaceFor(int i, int i2) {
        return (usedBytes() + 4) + i <= i2;
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    public synchronized void peek(ElementReader elementReader) throws IOException {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.length);
        }
    }

    public synchronized byte[] peek() throws IOException {
        byte[] bArr;
        if (isEmpty()) {
            bArr = null;
        } else {
            int i = this.first.length;
            bArr = new byte[i];
            ringRead(this.first.position + 4, bArr, 0, i);
        }
        return bArr;
    }

    public synchronized void remove() throws IOException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.elementCount == 1) {
            clear();
        } else {
            int iWrapPosition = wrapPosition(this.first.position + 4 + this.first.length);
            ringRead(iWrapPosition, this.buffer, 0, 4);
            int i = readInt(this.buffer, 0);
            writeHeader(this.fileLength, this.elementCount - 1, iWrapPosition, this.last.position);
            this.elementCount--;
            this.first = new Element(iWrapPosition, i);
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('[');
        sb.append("fileLength=").append(this.fileLength);
        sb.append(", size=").append(this.elementCount);
        sb.append(", first=").append(this.first);
        sb.append(", last=").append(this.last);
        sb.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: io.fabric.sdk.android.services.common.QueueFile.1
                boolean first = true;

                @Override // io.fabric.sdk.android.services.common.QueueFile.ElementReader
                public void read(InputStream inputStream, int i) throws IOException {
                    if (this.first) {
                        this.first = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i);
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e);
        }
        sb.append("]]");
        return sb.toString();
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        return this.last.position >= this.first.position ? (this.last.position - this.first.position) + 4 + this.last.length + 16 : (((this.last.position + 4) + this.last.length) + this.fileLength) - this.first.position;
    }
}
