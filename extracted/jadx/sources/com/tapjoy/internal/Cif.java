package com.tapjoy.internal;

import android.support.v4.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: renamed from: com.tapjoy.internal.if, reason: invalid class name */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Cif {
    private ByteBuffer b;
    private ie c;
    private final byte[] a = new byte[256];
    private int d = 0;

    private int[] a(int i) {
        int[] iArr = null;
        byte[] bArr = new byte[i * 3];
        try {
            this.b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = i4 + 1;
                int i7 = bArr[i4] & 255;
                i2 = i6 + 1;
                int i8 = i3 + 1;
                iArr[i3] = (i5 << 16) | ViewCompat.MEASURED_STATE_MASK | (i7 << 8) | (bArr[i6] & 255);
                i3 = i8;
            }
        } catch (BufferUnderflowException e) {
            new Object[1][0] = e;
            this.c.b = 1;
        }
        return iArr;
    }

    private void b() {
        boolean z = false;
        while (!z && !h() && this.c.c <= Integer.MAX_VALUE) {
            switch (g()) {
                case 33:
                    switch (g()) {
                        case 1:
                            e();
                            break;
                        case 249:
                            this.c.d = new id();
                            g();
                            int iG = g();
                            this.c.d.g = (iG & 28) >> 2;
                            if (this.c.d.g == 0) {
                                this.c.d.g = 1;
                            }
                            this.c.d.f = (iG & 1) != 0;
                            short s = this.b.getShort();
                            if (s < 2) {
                                s = 10;
                            }
                            this.c.d.i = s * 10;
                            this.c.d.h = g();
                            g();
                            break;
                        case 254:
                            e();
                            break;
                        case 255:
                            f();
                            String str = "";
                            for (int i = 0; i < 11; i++) {
                                str = str + ((char) this.a[i]);
                            }
                            if (str.equals("NETSCAPE2.0")) {
                                c();
                            } else {
                                e();
                            }
                            break;
                        default:
                            e();
                            break;
                    }
                    break;
                case 44:
                    if (this.c.d == null) {
                        this.c.d = new id();
                    }
                    this.c.d.a = this.b.getShort();
                    this.c.d.b = this.b.getShort();
                    this.c.d.c = this.b.getShort();
                    this.c.d.d = this.b.getShort();
                    int iG2 = g();
                    boolean z2 = (iG2 & 128) != 0;
                    int iPow = (int) Math.pow(2.0d, (iG2 & 7) + 1);
                    this.c.d.e = (iG2 & 64) != 0;
                    if (z2) {
                        this.c.d.k = a(iPow);
                    } else {
                        this.c.d.k = null;
                    }
                    this.c.d.j = this.b.position();
                    g();
                    e();
                    if (!h()) {
                        this.c.c++;
                        this.c.e.add(this.c.d);
                    }
                    break;
                case 59:
                    z = true;
                    break;
                default:
                    this.c.b = 1;
                    break;
            }
        }
    }

    private void c() {
        do {
            f();
            if (this.a[0] == 1) {
                this.c.m = (this.a[1] & 255) | ((this.a[2] & 255) << 8);
                if (this.c.m == 0) {
                    this.c.m = -1;
                }
            }
            if (this.d <= 0) {
                return;
            }
        } while (!h());
    }

    private void d() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) g());
        }
        if (!str.startsWith("GIF")) {
            this.c.b = 1;
            return;
        }
        this.c.f = this.b.getShort();
        this.c.g = this.b.getShort();
        int iG = g();
        this.c.h = (iG & 128) != 0;
        this.c.i = 2 << (iG & 7);
        this.c.j = g();
        this.c.k = g();
        if (!this.c.h || h()) {
            return;
        }
        this.c.a = a(this.c.i);
        this.c.l = this.c.a[this.c.j];
    }

    private void e() {
        int iG;
        do {
            try {
                iG = g();
                this.b.position(this.b.position() + iG);
            } catch (IllegalArgumentException e) {
                return;
            }
        } while (iG > 0);
    }

    private int f() {
        this.d = g();
        if (this.d <= 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < this.d) {
            try {
                i = this.d - i2;
                this.b.get(this.a, i2, i);
                i2 += i;
            } catch (Exception e) {
                Object[] objArr = {Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(this.d), e};
                this.c.b = 1;
                return i2;
            }
        }
        return i2;
    }

    private int g() {
        try {
            return this.b.get() & 255;
        } catch (Exception e) {
            this.c.b = 1;
            return 0;
        }
    }

    private boolean h() {
        return this.c.b != 0;
    }

    public final ie a() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (h()) {
            return this.c;
        }
        d();
        if (!h()) {
            b();
            if (this.c.c < 0) {
                this.c.b = 1;
            }
        }
        return this.c;
    }

    public final Cif a(byte[] bArr) {
        if (bArr != null) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.b = null;
            Arrays.fill(this.a, (byte) 0);
            this.c = new ie();
            this.d = 0;
            this.b = byteBufferWrap.asReadOnlyBuffer();
            this.b.position(0);
            this.b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }
}
