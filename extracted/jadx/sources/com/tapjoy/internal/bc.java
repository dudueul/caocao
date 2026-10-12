package com.tapjoy.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bc extends FilterInputStream {
    public bc(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            int i2 = super.read(bArr, i, bArr.length - i);
            if (i2 == -1) {
                if (i != 0) {
                    return i;
                }
                return -1;
            }
            i += i2;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = super.read(bArr, i + i3, i2 - i3);
            if (i4 == -1) {
                if (i3 != 0) {
                    return i3;
                }
                return -1;
            }
            i3 += i4;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j) {
            long jSkip = super.skip(j - j2);
            if (jSkip == 0) {
                if (super.read() < 0) {
                    break;
                }
                jSkip++;
            }
            j2 = jSkip + j2;
        }
        return j2;
    }
}
