package com.tapjoy.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class ja extends iu {
    final transient byte[][] f;
    final transient int[] g;

    ja(ir irVar, int i) {
        super(null);
        je.a(irVar.b, 0L, i);
        iy iyVar = irVar.a;
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            if (iyVar.c == iyVar.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += iyVar.c - iyVar.b;
            i2++;
            iyVar = iyVar.f;
        }
        this.f = new byte[i2][];
        this.g = new int[i2 * 2];
        iy iyVar2 = irVar.a;
        int i4 = 0;
        int i5 = 0;
        while (i5 < i) {
            this.f[i4] = iyVar2.a;
            int i6 = (iyVar2.c - iyVar2.b) + i5;
            if (i6 > i) {
                i6 = i;
            }
            this.g[i4] = i6;
            this.g[this.f.length + i4] = iyVar2.b;
            iyVar2.d = true;
            i4++;
            iyVar2 = iyVar2.f;
            i5 = i6;
        }
    }

    private int b(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : iBinarySearch ^ (-1);
    }

    private iu e() {
        return new iu(d());
    }

    @Override // com.tapjoy.internal.iu
    public final byte a(int i) {
        je.a(this.g[this.f.length - 1], i, 1L);
        int iB = b(i);
        return this.f[iB][(i - (iB == 0 ? 0 : this.g[iB - 1])) + this.g[this.f.length + iB]];
    }

    @Override // com.tapjoy.internal.iu
    public final iu a(int i, int i2) {
        return e().a(i, i2);
    }

    @Override // com.tapjoy.internal.iu
    public final String a() {
        return e().a();
    }

    @Override // com.tapjoy.internal.iu
    final void a(ir irVar) {
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            iy iyVar = new iy(this.f[i], i3, (i3 + i4) - i2);
            if (irVar.a == null) {
                iyVar.g = iyVar;
                iyVar.f = iyVar;
                irVar.a = iyVar;
            } else {
                irVar.a.g.a(iyVar);
            }
            i++;
            i2 = i4;
        }
        irVar.b += (long) i2;
    }

    @Override // com.tapjoy.internal.iu
    public final boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            if (!je.a(this.f[iB], (i - i4) + this.g[this.f.length + iB], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }

    @Override // com.tapjoy.internal.iu
    public final String b() {
        return e().b();
    }

    @Override // com.tapjoy.internal.iu
    public final int c() {
        return this.g[this.f.length - 1];
    }

    @Override // com.tapjoy.internal.iu
    public final byte[] d() {
        byte[] bArr = new byte[this.g[this.f.length - 1]];
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            System.arraycopy(this.f[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    @Override // com.tapjoy.internal.iu
    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof iu) || ((iu) obj).c() != c()) {
            return false;
        }
        iu iuVar = (iu) obj;
        int iC = c();
        if (c() - iC < 0) {
            z = false;
        } else {
            int iB = b(0);
            int i = 0;
            int i2 = 0;
            while (iC > 0) {
                int i3 = iB == 0 ? 0 : this.g[iB - 1];
                int iMin = Math.min(iC, ((this.g[iB] - i3) + i3) - i2);
                if (iuVar.a(i, this.f[iB], (i2 - i3) + this.g[this.f.length + iB], iMin)) {
                    i2 += iMin;
                    i += iMin;
                    iC -= iMin;
                    iB++;
                } else {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    @Override // com.tapjoy.internal.iu
    public final int hashCode() {
        int i = this.d;
        if (i == 0) {
            i = 1;
            int length = this.f.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f[i2];
                int i4 = this.g[length + i2];
                int i5 = this.g[i2];
                int i6 = i4 + (i5 - i3);
                int i7 = i;
                while (i4 < i6) {
                    i7 = bArr[i4] + (i7 * 31);
                    i4++;
                }
                i2++;
                i3 = i5;
                i = i7;
            }
            this.d = i;
        }
        return i;
    }

    @Override // com.tapjoy.internal.iu
    public final String toString() {
        return e().toString();
    }
}
