package com.tapjoy.internal;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dz implements dy.a {
    private final ArrayDeque c = new ArrayDeque();
    private dy d = null;
    private final BlockingQueue a = new LinkedBlockingQueue();
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.a);

    private void b() {
        this.d = (dy) this.c.poll();
        if (this.d != null) {
            this.d.executeOnExecutor(this.b, new Object[0]);
        }
    }

    @Override // com.tapjoy.internal.dy.a
    public final void a() {
        this.d = null;
        b();
    }

    public final void a(dy dyVar) {
        dyVar.d = this;
        this.c.add(dyVar);
        if (this.d == null) {
            b();
        }
    }
}
