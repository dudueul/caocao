package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class ix implements it {
    public final ir a = new ir();
    public final jc b;
    boolean c;

    ix(jc jcVar) {
        if (jcVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.b = jcVar;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    /* JADX WARN: Code duplicated, block: B:19:0x004a A[RETURN] */
    @Override // com.tapjoy.internal.it
    public final void a(long j) throws EOFException {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (this.a.b < j) {
            if (this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                z = false;
                if (!z) {
                    throw new EOFException();
                }
            }
        }
        z = true;
        if (!z) {
            throw new EOFException();
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
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b == 0 && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.a.b(irVar, Math.min(j, this.a.b));
    }

    @Override // com.tapjoy.internal.it
    public final iu b(long j) throws EOFException {
        a(j);
        return this.a.b(j);
    }

    @Override // com.tapjoy.internal.it
    public final boolean b() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        return this.a.b() && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // com.tapjoy.internal.it
    public final byte c() throws EOFException {
        a(1L);
        return this.a.c();
    }

    @Override // com.tapjoy.internal.it
    public final String c(long j) throws EOFException {
        a(j);
        return this.a.c(j);
    }

    @Override // com.tapjoy.internal.jc, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.close();
        ir irVar = this.a;
        try {
            irVar.d(irVar.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.tapjoy.internal.it
    public final void d(long j) throws EOFException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.b == 0 && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.a.b);
            this.a.d(jMin);
            j -= jMin;
        }
    }

    @Override // com.tapjoy.internal.it
    public final int e() throws EOFException {
        a(4L);
        return je.a(this.a.d());
    }

    @Override // com.tapjoy.internal.it
    public final long f() throws EOFException {
        a(8L);
        return this.a.f();
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
