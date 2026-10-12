package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ic {
    private static final String d = ic.class.getSimpleName();
    int a;
    int b;
    ie c;
    private int[] e;
    private final int[] f;
    private ByteBuffer g;
    private byte[] h;

    @Nullable
    private byte[] i;
    private int j;
    private int k;
    private Cif l;
    private short[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private int[] q;
    private a r;
    private Bitmap s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;

    interface a {
        @Nonnull
        Bitmap a(int i, int i2, Bitmap.Config config);

        byte[] a(int i);

        int[] b(int i);
    }

    ic() {
        this(new ih());
    }

    private ic(a aVar) {
        this.f = new int[256];
        this.j = 0;
        this.k = 0;
        this.r = aVar;
        this.c = new ie();
    }

    ic(a aVar, ie ieVar, ByteBuffer byteBuffer) {
        this(aVar, ieVar, byteBuffer, (byte) 0);
    }

    private ic(a aVar, ie ieVar, ByteBuffer byteBuffer, byte b) {
        this(aVar);
        b(ieVar, byteBuffer);
    }

    private synchronized void a(ie ieVar, ByteBuffer byteBuffer) {
        b(ieVar, byteBuffer);
    }

    private synchronized void a(ie ieVar, byte[] bArr) {
        a(ieVar, ByteBuffer.wrap(bArr));
    }

    private void a(int[] iArr, id idVar, int i) {
        int i2 = idVar.d / this.v;
        int i3 = idVar.b / this.v;
        int i4 = idVar.c / this.v;
        int i5 = (i3 * this.x) + (idVar.a / this.v);
        int i6 = i5 + (i2 * this.x);
        while (i5 < i6) {
            int i7 = i5 + i4;
            for (int i8 = i5; i8 < i7; i8++) {
                iArr[i8] = i;
            }
            i5 += this.x;
        }
    }

    private void b() {
        if (this.j > this.k) {
            return;
        }
        if (this.i == null) {
            this.i = this.r.a(16384);
        }
        this.k = 0;
        this.j = Math.min(this.g.remaining(), 16384);
        this.g.get(this.i, 0, this.j);
    }

    private synchronized void b(ie ieVar, ByteBuffer byteBuffer) {
        int iHighestOneBit = Integer.highestOneBit(1);
        this.u = 0;
        this.c = ieVar;
        this.y = false;
        this.a = -1;
        this.b = 0;
        this.g = byteBuffer.asReadOnlyBuffer();
        this.g.position(0);
        this.g.order(ByteOrder.LITTLE_ENDIAN);
        this.t = false;
        Iterator it = ieVar.e.iterator();
        while (it.hasNext()) {
            if (((id) it.next()).g == 3) {
                this.t = true;
                break;
            }
        }
        this.v = iHighestOneBit;
        this.x = ieVar.f / iHighestOneBit;
        this.w = ieVar.g / iHighestOneBit;
        this.p = this.r.a(ieVar.f * ieVar.g);
        this.q = this.r.b(this.x * this.w);
    }

    private int c() {
        try {
            b();
            byte[] bArr = this.i;
            int i = this.k;
            this.k = i + 1;
            return bArr[i] & 255;
        } catch (Exception e) {
            this.u = 1;
            return 0;
        }
    }

    private int d() {
        int iC = c();
        if (iC > 0) {
            try {
                if (this.h == null) {
                    this.h = this.r.a(255);
                }
                int i = this.j - this.k;
                if (i >= iC) {
                    System.arraycopy(this.i, this.k, this.h, 0, iC);
                    this.k += iC;
                } else if (this.g.remaining() + i >= iC) {
                    System.arraycopy(this.i, this.k, this.h, 0, i);
                    this.k = this.j;
                    b();
                    int i2 = iC - i;
                    System.arraycopy(this.i, 0, this.h, i, i2);
                    this.k += i2;
                } else {
                    this.u = 1;
                }
            } catch (Exception e) {
                new Object[1][0] = e;
                this.u = 1;
            }
        }
        return iC;
    }

    private Bitmap e() {
        Bitmap bitmapA = this.r.a(this.x, this.w, this.y ? Bitmap.Config.ARGB_4444 : Bitmap.Config.RGB_565);
        if (Build.VERSION.SDK_INT >= 12) {
            bitmapA.setHasAlpha(true);
        }
        return bitmapA;
    }

    final synchronized int a(byte[] bArr) {
        if (this.l == null) {
            this.l = new Cif();
        }
        this.c = this.l.a(bArr).a();
        if (bArr != null) {
            a(this.c, bArr);
        }
        return this.u;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v15, types: [short] */
    /* JADX WARN: Type inference failed for: r11v17 */
    final synchronized Bitmap a() {
        Bitmap bitmapE;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iD;
        short s;
        if (this.c.c <= 0 || this.a < 0) {
            Object[] objArr = {Integer.valueOf(this.c.c), Integer.valueOf(this.a)};
            this.u = 1;
        }
        if (this.u == 1 || this.u == 2) {
            new Object[1][0] = Integer.valueOf(this.u);
            bitmapE = null;
        } else {
            this.u = 0;
            id idVar = (id) this.c.e.get(this.a);
            int i7 = this.a - 1;
            id idVar2 = i7 >= 0 ? (id) this.c.e.get(i7) : null;
            this.e = idVar.k != null ? idVar.k : this.c.a;
            if (this.e == null) {
                new Object[1][0] = Integer.valueOf(this.a);
                this.u = 1;
                bitmapE = null;
            } else {
                if (idVar.f) {
                    System.arraycopy(this.e, 0, this.f, 0, this.e.length);
                    this.e = this.f;
                    this.e[idVar.h] = 0;
                }
                int[] iArr = this.q;
                if (idVar2 == null) {
                    Arrays.fill(iArr, 0);
                }
                if (idVar2 != null && idVar2.g > 0) {
                    if (idVar2.g == 2) {
                        int i8 = 0;
                        if (!idVar.f) {
                            i8 = this.c.l;
                            if (idVar.k != null && this.c.j == idVar.h) {
                                i8 = 0;
                            }
                        } else if (this.a == 0) {
                            this.y = true;
                        }
                        a(iArr, idVar2, i8);
                    } else if (idVar2.g == 3) {
                        if (this.s == null) {
                            a(iArr, idVar2, 0);
                        } else {
                            int i9 = idVar2.d / this.v;
                            int i10 = idVar2.b / this.v;
                            int i11 = idVar2.c / this.v;
                            int i12 = idVar2.a / this.v;
                            this.s.getPixels(iArr, (this.x * i10) + i12, this.x, i12, i10, i11, i9);
                        }
                    }
                }
                this.j = 0;
                this.k = 0;
                if (idVar != null) {
                    this.g.position(idVar.j);
                }
                int i13 = idVar == null ? this.c.f * this.c.g : idVar.c * idVar.d;
                if (this.p == null || this.p.length < i13) {
                    this.p = this.r.a(i13);
                }
                if (this.m == null) {
                    this.m = new short[4096];
                }
                if (this.n == null) {
                    this.n = new byte[4096];
                }
                if (this.o == null) {
                    this.o = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
                }
                int iC = c();
                int i14 = 1 << iC;
                int i15 = i14 + 1;
                int i16 = i14 + 2;
                int i17 = -1;
                int i18 = iC + 1;
                int i19 = (1 << i18) - 1;
                for (int i20 = 0; i20 < i14; i20++) {
                    this.m[i20] = 0;
                    this.n[i20] = (byte) i20;
                }
                int i21 = 0;
                int i22 = 0;
                int i23 = 0;
                int i24 = 0;
                int i25 = 0;
                int i26 = 0;
                int i27 = 0;
                int i28 = 0;
                while (i21 < i13) {
                    if (i27 == 0) {
                        iD = d();
                        if (iD <= 0) {
                            this.u = 3;
                            break;
                        }
                        i6 = 0;
                    } else {
                        i6 = i23;
                        iD = i27;
                    }
                    int i29 = ((this.h[i6] & 255) << i28) + i26;
                    i28 += 8;
                    i23 = i6 + 1;
                    i27 = iD - 1;
                    i25 = i25;
                    int i30 = i29;
                    while (true) {
                        if (i28 >= i18) {
                            int i31 = i30 & i19;
                            i30 >>= i18;
                            i28 -= i18;
                            if (i31 == i14) {
                                i18 = iC + 1;
                                i19 = (1 << i18) - 1;
                                i16 = i14 + 2;
                                i17 = -1;
                            } else {
                                if (i31 > i16) {
                                    this.u = 3;
                                    i26 = i30;
                                    break;
                                }
                                if (i31 != i15) {
                                    if (i17 == -1) {
                                        this.o[i24] = this.n[i31];
                                        i24++;
                                        i25 = i31;
                                        i17 = i31;
                                    } else {
                                        if (i31 >= i16) {
                                            this.o[i24] = (byte) i25;
                                            i24++;
                                            s = i17;
                                        } else {
                                            s = i31;
                                        }
                                        while (s >= i14) {
                                            this.o[i24] = this.n[s];
                                            s = this.m[s];
                                            i24++;
                                        }
                                        i25 = this.n[s] & 255;
                                        int i32 = i24 + 1;
                                        this.o[i24] = (byte) i25;
                                        if (i16 < 4096) {
                                            this.m[i16] = (short) i17;
                                            this.n[i16] = (byte) i25;
                                            i16++;
                                            if ((i16 & i19) == 0 && i16 < 4096) {
                                                i18++;
                                                i19 += i16;
                                            }
                                        }
                                        i24 = i32;
                                        while (i24 > 0) {
                                            i24--;
                                            this.p[i22] = this.o[i24];
                                            i21++;
                                            i22++;
                                        }
                                        i17 = i31;
                                    }
                                }
                            }
                        }
                        i26 = i30;
                        break;
                    }
                }
                for (int i33 = i22; i33 < i13; i33++) {
                    this.p[i33] = 0;
                }
                int i34 = idVar.d / this.v;
                int i35 = idVar.b / this.v;
                int i36 = idVar.c / this.v;
                int i37 = idVar.a / this.v;
                int i38 = 1;
                int i39 = 8;
                int i40 = 0;
                boolean z = this.a == 0;
                int i41 = 0;
                while (i41 < i34) {
                    if (idVar.e) {
                        if (i40 >= i34) {
                            i38++;
                            switch (i38) {
                                case 2:
                                    i40 = 4;
                                    break;
                                case 3:
                                    i40 = 2;
                                    i39 = 4;
                                    break;
                                case 4:
                                    i40 = 1;
                                    i39 = 2;
                                    break;
                            }
                        }
                        i = i40;
                        i2 = i40 + i39;
                        i3 = i39;
                        i4 = i38;
                    } else {
                        i = i41;
                        i2 = i40;
                        i3 = i39;
                        i4 = i38;
                    }
                    int i42 = i + i35;
                    if (i42 < this.w) {
                        int i43 = i42 * this.x;
                        int i44 = i43 + i37;
                        int i45 = i44 + i36;
                        int i46 = this.x + i43 < i45 ? this.x + i43 : i45;
                        int i47 = this.v * i41 * idVar.c;
                        int i48 = i47 + ((i46 - i44) * this.v);
                        int i49 = i47;
                        for (int i50 = i44; i50 < i46; i50++) {
                            if (this.v == 1) {
                                i5 = this.e[this.p[i49] & 255];
                            } else {
                                int i51 = idVar.c;
                                int i52 = 0;
                                int i53 = 0;
                                int i54 = 0;
                                int i55 = 0;
                                int i56 = 0;
                                for (int i57 = i49; i57 < this.v + i49 && i57 < this.p.length && i57 < i48; i57++) {
                                    int i58 = this.e[this.p[i57] & 255];
                                    if (i58 != 0) {
                                        i52 += (i58 >> 24) & 255;
                                        i53 += (i58 >> 16) & 255;
                                        i54 += (i58 >> 8) & 255;
                                        i55 += i58 & 255;
                                        i56++;
                                    }
                                }
                                for (int i59 = i49 + i51; i59 < i49 + i51 + this.v && i59 < this.p.length && i59 < i48; i59++) {
                                    int i60 = this.e[this.p[i59] & 255];
                                    if (i60 != 0) {
                                        i52 += (i60 >> 24) & 255;
                                        i53 += (i60 >> 16) & 255;
                                        i54 += (i60 >> 8) & 255;
                                        i55 += i60 & 255;
                                        i56++;
                                    }
                                }
                                i5 = i56 == 0 ? 0 : (i55 / i56) | ((i54 / i56) << 8) | ((i53 / i56) << 16) | ((i52 / i56) << 24);
                            }
                            if (i5 != 0) {
                                iArr[i50] = i5;
                            } else if (!this.y && z) {
                                this.y = true;
                            }
                            i49 = this.v + i49;
                        }
                    }
                    i41++;
                    i40 = i2;
                    i39 = i3;
                    i38 = i4;
                }
                if (this.t && (idVar.g == 0 || idVar.g == 1)) {
                    if (this.s == null) {
                        this.s = e();
                    }
                    this.s.setPixels(iArr, 0, this.x, 0, 0, this.x, this.w);
                }
                bitmapE = e();
                bitmapE.setPixels(iArr, 0, this.x, 0, 0, this.x, this.w);
            }
        }
        return bitmapE;
    }
}
