package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {
    private static final String TAG = "BufferedIs";
    private volatile byte[] buf;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public static class InvalidMarkException extends RuntimeException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String str) {
            super(str);
        }
    }

    public RecyclableBufferedInputStream(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        this.markpos = -1;
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("buffer is null or empty");
        }
        this.buf = bArr;
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        if (this.markpos == -1 || this.pos - this.markpos >= this.marklimit) {
            int i = inputStream.read(bArr);
            if (i > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = i;
            }
            return i;
        }
        if (this.markpos == 0 && this.marklimit > bArr.length && this.count == bArr.length) {
            int length = bArr.length * 2;
            if (length > this.marklimit) {
                length = this.marklimit;
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "allocate buffer of length: " + length);
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.buf = bArr2;
            bArr = bArr2;
        } else if (this.markpos > 0) {
            System.arraycopy(bArr, this.markpos, bArr, 0, bArr.length - this.markpos);
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int i2 = inputStream.read(bArr, this.pos, bArr.length - this.pos);
        this.count = i2 <= 0 ? this.pos : this.pos + i2;
        return i2;
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.buf == null || inputStream == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + inputStream.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buf = null;
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.marklimit = Math.max(this.marklimit, i);
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        int i = -1;
        synchronized (this) {
            byte[] bArr = this.buf;
            InputStream inputStream = this.in;
            if (bArr == null || inputStream == null) {
                throw streamClosed();
            }
            if (this.pos < this.count || fillbuf(inputStream, bArr) != -1) {
                if (bArr != this.buf && (bArr = this.buf) == null) {
                    throw streamClosed();
                }
                if (this.count - this.pos > 0) {
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    i = bArr[i2] & 255;
                }
            }
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0092 A[Catch: all -> 0x000b, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:7:0x000a, B:15:0x0013, B:17:0x0017, B:18:0x001b, B:19:0x001c, B:21:0x0022, B:24:0x002a, B:26:0x0036, B:30:0x0045, B:31:0x0048, B:33:0x004c, B:35:0x004f, B:38:0x0057, B:54:0x0086, B:58:0x0092, B:40:0x005c, B:43:0x0064, B:44:0x0067, B:46:0x006b, B:48:0x006f, B:49:0x0073, B:50:0x0074, B:53:0x007c, B:57:0x008b, B:29:0x003e), top: B:62:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x009c A[LOOP:0: B:31:0x0048->B:61:0x009c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x0098 A[SYNTHETIC] */
    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5 = -1;
        synchronized (this) {
            byte[] bArr2 = this.buf;
            if (bArr2 == null) {
                throw streamClosed();
            }
            if (i2 == 0) {
                i5 = 0;
            } else {
                InputStream inputStream = this.in;
                if (inputStream == null) {
                    throw streamClosed();
                }
                if (this.pos < this.count) {
                    int i6 = this.count - this.pos >= i2 ? i2 : this.count - this.pos;
                    System.arraycopy(bArr2, this.pos, bArr, i, i6);
                    this.pos += i6;
                    if (i6 == i2 || inputStream.available() == 0) {
                        i5 = i6;
                    } else {
                        i += i6;
                        i3 = i2 - i6;
                    }
                } else {
                    i3 = i2;
                }
                while (true) {
                    if (this.markpos == -1 && i3 >= bArr2.length) {
                        i4 = inputStream.read(bArr, i, i3);
                        if (i4 == -1) {
                            if (i3 == i2) {
                                break;
                            }
                            i5 = i2 - i3;
                            break;
                        }
                        i3 -= i4;
                        if (i3 == 0) {
                            i5 = i2;
                            break;
                        }
                        if (inputStream.available() == 0) {
                            i5 = i2 - i3;
                            break;
                        }
                        i += i4;
                    } else {
                        if (fillbuf(inputStream, bArr2) == -1) {
                            if (i3 == i2) {
                                break;
                            }
                            i5 = i2 - i3;
                            break;
                        }
                        if (bArr2 != this.buf && (bArr2 = this.buf) == null) {
                            throw streamClosed();
                        }
                        i4 = this.count - this.pos >= i3 ? i3 : this.count - this.pos;
                        System.arraycopy(bArr2, this.pos, bArr, i, i4);
                        this.pos += i4;
                        i3 -= i4;
                        if (i3 == 0) {
                            i5 = i2;
                            break;
                        }
                        if (inputStream.available() == 0) {
                            i5 = i2 - i3;
                            break;
                        }
                        i += i4;
                    }
                }
            }
        }
        return i5;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        }
        if (-1 == this.markpos) {
            throw new InvalidMarkException("Mark has been invalidated");
        }
        this.pos = this.markpos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        byte[] bArr = this.buf;
        InputStream inputStream = this.in;
        if (bArr == null) {
            throw streamClosed();
        }
        if (j < 1) {
            j = 0;
        } else {
            if (inputStream == null) {
                throw streamClosed();
            }
            if (this.count - this.pos >= j) {
                this.pos = (int) (((long) this.pos) + j);
            } else {
                long j2 = this.count - this.pos;
                this.pos = this.count;
                if (this.markpos == -1 || j > this.marklimit) {
                    j = j2 + inputStream.skip(j - j2);
                } else if (fillbuf(inputStream, bArr) == -1) {
                    j = j2;
                } else if (this.count - this.pos >= j - j2) {
                    this.pos = (int) (((long) this.pos) + (j - j2));
                } else {
                    long j3 = (((long) this.count) + j2) - ((long) this.pos);
                    this.pos = this.count;
                    j = j3;
                }
            }
        }
        return j;
    }
}
