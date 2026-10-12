package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fw {
    public fq a;
    public volatile gf.a b;
    public int c;
    public volatile gf.a d;
    public volatile gf.a e;

    public final void a() {
        a(16);
        gf.a aVar = this.d;
        if (aVar != null) {
            this.d = null;
            aVar.b().c();
        }
    }

    public final synchronized void a(int i) {
        gf.a aVar = this.b;
        if (aVar != null && this.c < i) {
            this.c |= i;
            aVar.a("state", Integer.valueOf(this.c)).b().c();
        }
    }
}
