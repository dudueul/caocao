package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class iy {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    iy f;
    iy g;

    iy() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    iy(iy iyVar) {
        this(iyVar.a, iyVar.b, iyVar.c);
        iyVar.d = true;
    }

    iy(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    public final iy a() {
        iy iyVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return iyVar;
    }

    public final iy a(iy iyVar) {
        iyVar.g = this;
        iyVar.f = this.f;
        this.f.g = iyVar;
        this.f = iyVar;
        return iyVar;
    }

    public final void a(iy iyVar, int i) {
        if (!iyVar.e) {
            throw new IllegalArgumentException();
        }
        if (iyVar.c + i > 8192) {
            if (iyVar.d) {
                throw new IllegalArgumentException();
            }
            if ((iyVar.c + i) - iyVar.b > 8192) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(iyVar.a, iyVar.b, iyVar.a, 0, iyVar.c - iyVar.b);
            iyVar.c -= iyVar.b;
            iyVar.b = 0;
        }
        System.arraycopy(this.a, this.b, iyVar.a, iyVar.c, i);
        iyVar.c += i;
        this.b += i;
    }
}
