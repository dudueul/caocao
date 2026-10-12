package com.tapjoy.internal;

import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dh implements co, de.a {
    private static dh c;
    public float a = 0.0f;
    public cp b;
    private final cq d;
    private final cn e;
    private dd f;

    private dh(cq cqVar, cn cnVar) {
        this.d = cqVar;
        this.e = cnVar;
    }

    public static dh a() {
        if (c == null) {
            c = new dh(new cq(), new cn());
        }
        return c;
    }

    @Override // com.tapjoy.internal.co
    public final void a(float f) {
        this.a = f;
        if (this.f == null) {
            this.f = dd.a();
        }
        Iterator it = Collections.unmodifiableCollection(this.f.b).iterator();
        while (it.hasNext()) {
            ((cz) it.next()).c.a(f);
        }
    }

    @Override // com.tapjoy.internal.de.a
    public final void a(boolean z) {
        if (z) {
            dv.a();
            dv.b();
        } else {
            dv.a();
            dv.c();
        }
    }
}
