package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ek {
    final it a;
    private int d;
    private eg h;
    private long b = 0;
    private long c = Long.MAX_VALUE;
    private int e = 2;
    private int f = -1;
    private long g = -1;

    public ek(it itVar) {
        this.a = itVar;
    }

    private void a(int i) throws IOException {
        while (this.b < this.c && !this.a.b()) {
            int i2 = i();
            if (i2 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i3 = i2 >> 3;
            int i4 = i2 & 7;
            switch (i4) {
                case 0:
                    this.e = 0;
                    e();
                    break;
                case 1:
                    this.e = 1;
                    g();
                    break;
                case 2:
                    int i5 = i();
                    this.b += (long) i5;
                    this.a.d(i5);
                    break;
                case 3:
                    a(i3);
                    break;
                case 4:
                    if (i3 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case 5:
                    this.e = 5;
                    f();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i4);
            }
        }
        throw new EOFException();
    }

    private void b(int i) throws IOException {
        if (this.e == i) {
            this.e = 6;
            return;
        }
        if (this.b > this.c) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
        if (this.b != this.c) {
            this.e = 7;
            return;
        }
        this.c = this.g;
        this.g = -1L;
        this.e = 6;
    }

    private int i() throws ProtocolException {
        this.b++;
        byte bC = this.a.c();
        if (bC >= 0) {
            return bC;
        }
        int i = bC & 127;
        this.b++;
        byte bC2 = this.a.c();
        if (bC2 >= 0) {
            return i | (bC2 << 7);
        }
        int i2 = i | ((bC2 & 127) << 7);
        this.b++;
        byte bC3 = this.a.c();
        if (bC3 >= 0) {
            return i2 | (bC3 << 14);
        }
        int i3 = i2 | ((bC3 & 127) << 14);
        this.b++;
        byte bC4 = this.a.c();
        if (bC4 >= 0) {
            return i3 | (bC4 << 21);
        }
        int i4 = i3 | ((bC4 & 127) << 21);
        this.b++;
        byte bC5 = this.a.c();
        int i5 = i4 | (bC5 << 28);
        if (bC5 >= 0) {
            return i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            this.b++;
            if (this.a.c() >= 0) {
                return i5;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    public final long a() throws IOException {
        if (this.e != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.d + 1;
        this.d = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.g;
        this.g = -1L;
        this.e = 6;
        return j;
    }

    public final void a(long j) throws IOException {
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.d - 1;
        this.d = i;
        if (i < 0 || this.g != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        }
        if (this.b != this.c && this.d != 0) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
        this.c = j;
    }

    public final int b() throws IOException {
        if (this.e == 7) {
            this.e = 2;
            return this.f;
        }
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        }
        while (this.b < this.c && !this.a.b()) {
            int i = i();
            if (i == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            this.f = i >> 3;
            int i2 = i & 7;
            switch (i2) {
                case 0:
                    this.h = eg.VARINT;
                    this.e = 0;
                    return this.f;
                case 1:
                    this.h = eg.FIXED64;
                    this.e = 1;
                    return this.f;
                case 2:
                    this.h = eg.LENGTH_DELIMITED;
                    this.e = 2;
                    int i3 = i();
                    if (i3 < 0) {
                        throw new ProtocolException("Negative length: " + i3);
                    }
                    if (this.g != -1) {
                        throw new IllegalStateException();
                    }
                    this.g = this.c;
                    this.c = ((long) i3) + this.b;
                    if (this.c > this.g) {
                        throw new EOFException();
                    }
                    return this.f;
                case 3:
                    a(this.f);
                    break;
                case 4:
                    throw new ProtocolException("Unexpected end group");
                case 5:
                    this.h = eg.FIXED32;
                    this.e = 5;
                    return this.f;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i2);
            }
        }
        return -1;
    }

    public final eg c() {
        return this.h;
    }

    public final int d() {
        if (this.e != 0 && this.e != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
        }
        int i = i();
        b(0);
        return i;
    }

    public final long e() {
        if (this.e != 0 && this.e != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
        }
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            this.b++;
            byte bC = this.a.c();
            j |= ((long) (bC & 127)) << i;
            if ((bC & 128) == 0) {
                b(0);
                return j;
            }
        }
        throw new ProtocolException("WireInput encountered a malformed varint");
    }

    public final int f() throws IOException {
        if (this.e != 5 && this.e != 2) {
            throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.e);
        }
        this.a.a(4L);
        this.b += 4;
        int iE = this.a.e();
        b(5);
        return iE;
    }

    public final long g() throws IOException {
        if (this.e != 1 && this.e != 2) {
            throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.e);
        }
        this.a.a(8L);
        this.b += 8;
        long jF = this.a.f();
        b(1);
        return jF;
    }

    final long h() {
        if (this.e != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.e);
        }
        long j = this.c - this.b;
        this.a.a(j);
        this.e = 6;
        this.b = this.c;
        this.c = this.g;
        this.g = -1L;
        return j;
    }
}
