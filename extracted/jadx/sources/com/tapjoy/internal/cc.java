package com.tapjoy.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class cc implements Runnable {
    private final ca a;

    @Nullable
    private final cf b;

    protected cc(ca caVar, @Nullable cf cfVar) {
        this.a = caVar;
        this.b = cfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Object objF = this.a.f();
            if (this.b == null || (this.b instanceof cg)) {
                return;
            }
            this.b.a(this.a, objF);
        } catch (Throwable th) {
            if (this.b == null || (this.b instanceof cg)) {
                return;
            }
            this.b.a(this.a);
        }
    }
}
