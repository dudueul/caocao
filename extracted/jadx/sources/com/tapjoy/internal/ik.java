package com.tapjoy.internal;

import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ik extends ii {
    private final ew.a c = new ew.a();
    private ey d = null;

    public final boolean a(ev evVar) {
        if (this.d == null) {
            this.d = evVar.n;
        } else if (evVar.n != this.d) {
            return false;
        }
        this.c.c.add(evVar);
        return true;
    }

    @Override // com.tapjoy.internal.ca
    public final String c() {
        return this.d == ey.USAGES ? "api/v1/usages" : "api/v1/cevs";
    }

    @Override // com.tapjoy.internal.ii, com.tapjoy.internal.ca
    public final Map e() {
        Map mapE = super.e();
        mapE.put("events", new bm(hp.a(this.c.b())));
        return mapE;
    }

    public final int g() {
        return this.c.c.size();
    }
}
