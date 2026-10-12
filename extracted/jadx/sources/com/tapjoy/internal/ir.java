package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ir implements is, it, Cloneable {
    private static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    iy a;
    long b;

    private void a(byte[] bArr) throws EOFException {
        int iMin;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return;
            }
            int length = bArr.length - i2;
            je.a(bArr.length, i2, length);
            iy iyVar = this.a;
            if (iyVar == null) {
                iMin = -1;
            } else {
                iMin = Math.min(length, iyVar.c - iyVar.b);
                System.arraycopy(iyVar.a, iyVar.b, bArr, i2, iMin);
                iyVar.b += iMin;
                this.b -= (long) iMin;
                if (iyVar.b == iyVar.c) {
                    this.a = iyVar.a();
                    iz.a(iyVar);
                }
            }
            if (iMin == -1) {
                throw new EOFException();
            }
            i = iMin + i2;
        }
    }

    private byte[] g(long j) throws EOFException {
        je.a(this.b, 0L, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[(int) j];
        a(bArr);
        return bArr;
    }

    @Override // com.tapjoy.internal.is
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ir e(int i) {
        iy iyVarC = c(1);
        byte[] bArr = iyVarC.a;
        int i2 = iyVarC.c;
        iyVarC.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    @Override // com.tapjoy.internal.is
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ir b(iu iuVar) {
        if (iuVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        iuVar.a(this);
        return this;
    }

    @Override // com.tapjoy.internal.is
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ir b(String str) {
        int length = str.length();
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (length < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + length + " < 0");
        }
        if (length > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + length + " > " + str.length());
        }
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                iy iyVarC = c(1);
                byte[] bArr = iyVarC.a;
                int i2 = iyVarC.c - i;
                int iMin = Math.min(length, 8192 - i2);
                int i3 = i + 1;
                bArr[i + i2] = (byte) cCharAt;
                while (i3 < iMin) {
                    char cCharAt2 = str.charAt(i3);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i3 + i2] = (byte) cCharAt2;
                    i3++;
                }
                int i4 = (i3 + i2) - iyVarC.c;
                iyVarC.c += i4;
                this.b += (long) i4;
                i = i3;
            } else if (cCharAt < 2048) {
                e((cCharAt >> 6) | 192);
                e((cCharAt & '?') | 128);
                i++;
            } else if (cCharAt < 55296 || cCharAt > 57343) {
                e((cCharAt >> '\f') | 224);
                e(((cCharAt >> 6) & 63) | 128);
                e((cCharAt & '?') | 128);
                i++;
            } else {
                char cCharAt3 = i + 1 < length ? str.charAt(i + 1) : (char) 0;
                if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                    e(63);
                    i++;
                } else {
                    int i5 = ((cCharAt3 & 9215) | ((cCharAt & 10239) << 10)) + 65536;
                    e((i5 >> 18) | 240);
                    e(((i5 >> 12) & 63) | 128);
                    e(((i5 >> 6) & 63) | 128);
                    e((i5 & 63) | 128);
                    i += 2;
                }
            }
        }
        return this;
    }

    public final ir a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        je.a(bArr.length, 0L, i2);
        int i3 = i2 + 0;
        while (i < i3) {
            iy iyVarC = c(1);
            int iMin = Math.min(i3 - i, 8192 - iyVarC.c);
            System.arraycopy(bArr, i, iyVarC.a, iyVarC.c, iMin);
            i += iMin;
            iyVarC.c = iMin + iyVarC.c;
        }
        this.b += (long) i2;
        return this;
    }

    @Override // com.tapjoy.internal.is
    public final is a() {
        return this;
    }

    @Override // com.tapjoy.internal.it
    public final void a(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    @Override // com.tapjoy.internal.jb
    public final void a(ir irVar, long j) {
        iy iyVarA;
        if (irVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (irVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        je.a(irVar.b, 0L, j);
        while (j > 0) {
            if (j < irVar.a.c - irVar.a.b) {
                iy iyVar = this.a != null ? this.a.g : null;
                if (iyVar != null && iyVar.e) {
                    if ((((long) iyVar.c) + j) - ((long) (iyVar.d ? 0 : iyVar.b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        irVar.a.a(iyVar, (int) j);
                        irVar.b -= j;
                        this.b += j;
                        return;
                    }
                }
                iy iyVar2 = irVar.a;
                int i = (int) j;
                if (i <= 0 || i > iyVar2.c - iyVar2.b) {
                    throw new IllegalArgumentException();
                }
                if (i >= 1024) {
                    iyVarA = new iy(iyVar2);
                } else {
                    iyVarA = iz.a();
                    System.arraycopy(iyVar2.a, iyVar2.b, iyVarA.a, 0, i);
                }
                iyVarA.c = iyVarA.b + i;
                iyVar2.b = i + iyVar2.b;
                iyVar2.g.a(iyVarA);
                irVar.a = iyVarA;
            }
            iy iyVar3 = irVar.a;
            long j2 = iyVar3.c - iyVar3.b;
            irVar.a = iyVar3.a();
            if (this.a == null) {
                this.a = iyVar3;
                iy iyVar4 = this.a;
                iy iyVar5 = this.a;
                iy iyVar6 = this.a;
                iyVar5.g = iyVar6;
                iyVar4.f = iyVar6;
            } else {
                iy iyVarA2 = this.a.g.a(iyVar3);
                if (iyVarA2.g == iyVarA2) {
                    throw new IllegalStateException();
                }
                if (iyVarA2.g.e) {
                    int i2 = iyVarA2.c - iyVarA2.b;
                    if (i2 <= (iyVarA2.g.d ? 0 : iyVarA2.g.b) + (8192 - iyVarA2.g.c)) {
                        iyVarA2.a(iyVarA2.g, i2);
                        iyVarA2.a();
                        iz.a(iyVarA2);
                    }
                }
            }
            irVar.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    @Override // com.tapjoy.internal.jc
    public final long b(ir irVar, long j) {
        if (irVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.b == 0) {
            return -1L;
        }
        if (j > this.b) {
            j = this.b;
        }
        irVar.a(this, j);
        return j;
    }

    @Override // com.tapjoy.internal.is
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final ir d(int i) {
        int iA = je.a(i);
        iy iyVarC = c(4);
        byte[] bArr = iyVarC.a;
        int i2 = iyVarC.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((iA >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((iA >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((iA >>> 8) & 255);
        bArr[i5] = (byte) (iA & 255);
        iyVarC.c = i5 + 1;
        this.b += 4;
        return this;
    }

    @Override // com.tapjoy.internal.it
    public final iu b(long j) {
        return new iu(g(j));
    }

    @Override // com.tapjoy.internal.it
    public final boolean b() {
        return this.b == 0;
    }

    @Override // com.tapjoy.internal.it
    public final byte c() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        iy iyVar = this.a;
        int i = iyVar.b;
        int i2 = iyVar.c;
        int i3 = i + 1;
        byte b = iyVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = iyVar.a();
            iz.a(iyVar);
        } else {
            iyVar.b = i3;
        }
        return b;
    }

    final iy c(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.a != null) {
            iy iyVar = this.a.g;
            return (iyVar.c + i > 8192 || !iyVar.e) ? iyVar.a(iz.a()) : iyVar;
        }
        this.a = iz.a();
        iy iyVar2 = this.a;
        iy iyVar3 = this.a;
        iy iyVar4 = this.a;
        iyVar3.g = iyVar4;
        iyVar2.f = iyVar4;
        return iyVar4;
    }

    @Override // com.tapjoy.internal.it
    public final String c(long j) {
        Charset charset = je.a;
        je.a(this.b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        iy iyVar = this.a;
        if (((long) iyVar.b) + j > iyVar.c) {
            return new String(g(j), charset);
        }
        String str = new String(iyVar.a, iyVar.b, (int) j, charset);
        iyVar.b = (int) (((long) iyVar.b) + j);
        this.b -= j;
        if (iyVar.b != iyVar.c) {
            return str;
        }
        this.a = iyVar.a();
        iz.a(iyVar);
        return str;
    }

    @Override // com.tapjoy.internal.jb, java.io.Closeable, java.lang.AutoCloseable, com.tapjoy.internal.jc
    public final void close() {
    }

    public final int d() {
        if (this.b < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        iy iyVar = this.a;
        int i = iyVar.b;
        int i2 = iyVar.c;
        if (i2 - i < 4) {
            return ((c() & 255) << 24) | ((c() & 255) << 16) | ((c() & 255) << 8) | (c() & 255);
        }
        byte[] bArr = iyVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.b -= 4;
        if (i8 != i2) {
            iyVar.b = i8;
            return i9;
        }
        this.a = iyVar.a();
        iz.a(iyVar);
        return i9;
    }

    @Override // com.tapjoy.internal.it
    public final void d(long j) throws EOFException {
        while (j > 0) {
            if (this.a == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, this.a.c - this.a.b);
            this.b -= (long) iMin;
            j -= (long) iMin;
            iy iyVar = this.a;
            iyVar.b = iMin + iyVar.b;
            if (this.a.b == this.a.c) {
                iy iyVar2 = this.a;
                this.a = iyVar2.a();
                iz.a(iyVar2);
            }
        }
    }

    @Override // com.tapjoy.internal.it
    public final int e() {
        return je.a(d());
    }

    @Override // com.tapjoy.internal.is
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final ir f(long j) {
        long jA = je.a(j);
        iy iyVarC = c(8);
        byte[] bArr = iyVarC.a;
        int i = iyVarC.c;
        int i2 = i + 1;
        bArr[i] = (byte) ((jA >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((jA >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((jA >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((jA >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((jA >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((jA >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((jA >>> 8) & 255);
        bArr[i8] = (byte) (jA & 255);
        iyVarC.c = i8 + 1;
        this.b += 8;
        return this;
    }

    public final boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ir)) {
            return false;
        }
        ir irVar = (ir) obj;
        if (this.b != irVar.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        iy iyVar = this.a;
        iy iyVar2 = irVar.a;
        int i = iyVar.b;
        int i2 = iyVar2.b;
        while (j < this.b) {
            long jMin = Math.min(iyVar.c - i, iyVar2.c - i2);
            int i3 = 0;
            while (i3 < jMin) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (iyVar.a[i] != iyVar2.a[i2]) {
                    return false;
                }
                i3++;
                i2 = i5;
                i = i4;
            }
            if (i == iyVar.c) {
                iyVar = iyVar.f;
                i = iyVar.b;
            }
            if (i2 == iyVar2.c) {
                iyVar2 = iyVar2.f;
                i2 = iyVar2.b;
            }
            j += jMin;
        }
        return true;
    }

    @Override // com.tapjoy.internal.it
    public final long f() {
        long jD;
        if (this.b < 8) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        iy iyVar = this.a;
        int i = iyVar.b;
        int i2 = iyVar.c;
        if (i2 - i < 8) {
            jD = ((((long) d()) & 4294967295L) << 32) | (((long) d()) & 4294967295L);
        } else {
            byte[] bArr = iyVar.a;
            int i3 = i + 1;
            long j = (((long) bArr[i]) & 255) << 56;
            int i4 = i3 + 1;
            long j2 = ((((long) bArr[i3]) & 255) << 48) | j;
            int i5 = i4 + 1;
            long j3 = j2 | ((((long) bArr[i4]) & 255) << 40);
            int i6 = i5 + 1;
            long j4 = j3 | ((((long) bArr[i5]) & 255) << 32);
            int i7 = i6 + 1;
            long j5 = j4 | ((((long) bArr[i6]) & 255) << 24);
            int i8 = i7 + 1;
            long j6 = j5 | ((((long) bArr[i7]) & 255) << 16);
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            jD = (((long) bArr[i9]) & 255) | j6 | ((((long) bArr[i8]) & 255) << 8);
            this.b -= 8;
            if (i10 == i2) {
                this.a = iyVar.a();
                iz.a(iyVar);
            } else {
                iyVar.b = i10;
            }
        }
        return je.a(jD);
    }

    @Override // com.tapjoy.internal.jb, java.io.Flushable
    public final void flush() {
    }

    public final byte[] g() {
        try {
            return g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final ir clone() {
        ir irVar = new ir();
        if (this.b == 0) {
            return irVar;
        }
        irVar.a = new iy(this.a);
        iy iyVar = irVar.a;
        iy iyVar2 = irVar.a;
        iy iyVar3 = irVar.a;
        iyVar2.g = iyVar3;
        iyVar.f = iyVar3;
        for (iy iyVar4 = this.a.f; iyVar4 != this.a; iyVar4 = iyVar4.f) {
            irVar.a.g.a(new iy(iyVar4));
        }
        irVar.b = this.b;
        return irVar;
    }

    public final int hashCode() {
        iy iyVar = this.a;
        if (iyVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = iyVar.c;
            for (int i3 = iyVar.b; i3 < i2; i3++) {
                i = (i * 31) + iyVar.a[i3];
            }
            iyVar = iyVar.f;
        } while (iyVar != this.a);
        return i;
    }

    public final String toString() {
        if (this.b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
        }
        int i = (int) this.b;
        return (i == 0 ? iu.b : new ja(this, i)).toString();
    }
}
