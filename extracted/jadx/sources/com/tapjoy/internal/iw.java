package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class iw implements is {
    public final ir a = new ir();
    public final jb b;
    boolean c;

    iw(jb jbVar) {
        if (jbVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = jbVar;
    }

    private is b() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        ir irVar = this.a;
        long j = irVar.b;
        if (j == 0) {
            j = 0;
        } else {
            iy iyVar = irVar.a.g;
            if (iyVar.c < 8192 && iyVar.e) {
                j -= (long) (iyVar.c - iyVar.b);
            }
        }
        if (j > 0) {
            this.b.a(this.a, j);
        }
        return this;
    }

    @Override // com.tapjoy.internal.is
    public final is a() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long j = this.a.b;
        if (j > 0) {
            this.b.a(this.a, j);
        }
        return this;
    }

    @Override // com.tapjoy.internal.jb
    public final void a(ir irVar, long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(irVar, j);
        b();
    }

    @Override // com.tapjoy.internal.is
    public final is b(iu iuVar) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(iuVar);
        return b();
    }

    @Override // com.tapjoy.internal.is
    public final is b(String str) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(str);
        return b();
    }

    @Override // com.tapjoy.internal.jb, java.io.Closeable, java.lang.AutoCloseable, com.tapjoy.internal.jc
    public final void close() throws Throwable {
        if (this.c) {
            return;
        }
        Throwable th = null;
        try {
            if (this.a.b > 0) {
                this.b.a(this.a, this.a.b);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.b.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.c = true;
        if (th != null) {
            je.a(th);
        }
    }

    @Override // com.tapjoy.internal.is
    public final is d(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.d(i);
        return b();
    }

    @Override // com.tapjoy.internal.is
    public final is e(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.e(i);
        return b();
    }

    @Override // com.tapjoy.internal.is
    public final is f(long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.f(j);
        return b();
    }

    @Override // com.tapjoy.internal.jb, java.io.Flushable
    public final void flush() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.a(this.a, this.a.b);
        }
        this.b.flush();
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
