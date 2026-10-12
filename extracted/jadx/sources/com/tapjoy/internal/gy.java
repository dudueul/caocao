package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.os.SystemClock;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class gy {
    final hc a;
    final gx b;
    long c;
    private int d = 1;
    private final ex.a e = new ex.a();

    gy(hc hcVar, gx gxVar) {
        this.a = hcVar;
        this.b = gxVar;
    }

    public final ev.a a(ey eyVar, String str) {
        fb fbVarB = this.a.b();
        ev.a aVar = new ev.a();
        aVar.g = hc.a;
        aVar.c = eyVar;
        aVar.d = str;
        if (v.c()) {
            aVar.e = Long.valueOf(v.b());
            aVar.f = Long.valueOf(System.currentTimeMillis());
        } else {
            aVar.e = Long.valueOf(System.currentTimeMillis());
            aVar.h = Long.valueOf(SystemClock.elapsedRealtime());
        }
        aVar.j = fbVarB.d;
        aVar.k = fbVarB.e;
        aVar.l = fbVarB.f;
        return aVar;
    }

    public final synchronized void a(ev.a aVar) {
        if (aVar.c != ey.USAGES) {
            int i = this.d;
            this.d = i + 1;
            aVar.n = Integer.valueOf(i);
            if (this.e.c != null) {
                aVar.o = this.e.b();
            }
            this.e.c = aVar.c;
            this.e.d = aVar.d;
            this.e.e = aVar.t;
        }
        gx gxVar = this.b;
        ev evVarB = aVar.b();
        try {
            hl hlVar = gxVar.a;
            synchronized (hlVar.a) {
                try {
                    hlVar.b.add(evVarB);
                } catch (Exception e) {
                    hlVar.a();
                    try {
                        hlVar.b.add(evVarB);
                    } catch (Exception e2) {
                    }
                }
            }
            if (gxVar.b == null) {
                gxVar.a.flush();
            } else if (gw.a || evVarB.n != ey.CUSTOM) {
                gxVar.a(true);
            } else {
                gxVar.a(false);
            }
        } catch (Exception e3) {
        }
    }

    public final void a(String str, String str2, double d, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        double dA;
        int iB = 1;
        hc hcVar = this.a;
        synchronized (hcVar) {
            SharedPreferences.Editor editorA = hcVar.c.a();
            if (str2.equals(hcVar.c.l.a())) {
                iB = hcVar.c.m.b() + 1;
                hcVar.c.m.a(editorA, iB);
                dA = hcVar.c.n.a() + d;
                hcVar.c.n.a(editorA, dA);
                editorA.apply();
            } else {
                hcVar.c.l.a(editorA, str2);
                hcVar.c.m.a(editorA, 1);
                hcVar.c.n.a(editorA, d);
                hcVar.c.o.a(editorA);
                hcVar.c.p.a(editorA);
                editorA.apply();
                hcVar.b.l = str2;
                hcVar.b.o = null;
                hcVar.b.p = null;
                dA = d;
            }
            hcVar.b.m = Integer.valueOf(iB);
            hcVar.b.n = Double.valueOf(dA);
        }
        ev.a aVarA = a(ey.APP, "purchase");
        fd.a aVar = new fd.a();
        aVar.c = str;
        if (str2 != null) {
            aVar.f = str2;
        }
        aVar.e = Double.valueOf(d);
        if (str5 != null) {
            aVar.m = str5;
        }
        if (str3 != null) {
            aVar.o = str3;
        }
        if (str4 != null) {
            aVar.p = str4;
        }
        aVarA.p = aVar.b();
        a(aVarA);
        hc hcVar2 = this.a;
        long jLongValue = aVarA.e.longValue();
        synchronized (hcVar2) {
            SharedPreferences.Editor editorA2 = hcVar2.c.a();
            hcVar2.c.o.a(editorA2, jLongValue);
            hcVar2.c.p.a(editorA2, d);
            editorA2.apply();
            hcVar2.b.o = Long.valueOf(jLongValue);
            hcVar2.b.p = Double.valueOf(d);
        }
    }

    public final void a(String str, String str2, int i, long j, long j2, Map map) {
        ev.a aVarA = a(ey.USAGES, str);
        aVarA.x = str2;
        aVarA.y = Integer.valueOf(i);
        aVarA.z = Long.valueOf(j);
        aVarA.A = Long.valueOf(j2);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                aVarA.w.add(new ez((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(aVarA);
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        ev.a aVarA = a(ey.CUSTOM, str2);
        aVarA.t = str;
        aVarA.u = str3;
        aVarA.v = str4;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                aVarA.w.add(new ez((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(aVarA);
    }
}
