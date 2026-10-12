package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ev extends eh {
    public static final ej c = new b();
    public static final ey d = ey.APP;
    public static final Long e = 0L;
    public static final Long f = 0L;
    public static final Long g = 0L;
    public static final Long h = 0L;
    public static final Integer i = 0;
    public static final Integer j = 0;
    public static final Integer k = 0;
    public static final Long l = 0L;
    public static final Long m = 0L;
    public final fd A;
    public final String B;
    public final String C;
    public final fc D;
    public final String E;
    public final String F;
    public final String G;
    public final List H;
    public final String I;
    public final Integer J;
    public final Long K;
    public final Long L;
    public final ey n;
    public final String o;
    public final Long p;
    public final Long q;
    public final String r;
    public final Long s;
    public final Long t;
    public final fa u;
    public final eu v;
    public final fh w;
    public final Integer x;
    public final Integer y;
    public final ex z;

    public static final class a extends eh.a {
        public Long A;
        public ey c;
        public String d;
        public Long e;
        public Long f;
        public String g;
        public Long h;
        public Long i;
        public fa j;
        public eu k;
        public fh l;
        public Integer m;
        public Integer n;
        public ex o;
        public fd p;
        public String q;
        public String r;
        public fc s;
        public String t;
        public String u;
        public String v;
        public List w = eo.a();
        public String x;
        public Integer y;
        public Long z;

        public final ev b() {
            if (this.c == null || this.d == null || this.e == null) {
                throw eo.a(this.c, "type", this.d, "name", this.e, "time");
            }
            return new ev(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, ev.class);
        }

        private static ev b(ek ekVar) throws IOException {
            a aVar = new a();
            long jA = ekVar.a();
            while (true) {
                int iB = ekVar.b();
                if (iB == -1) {
                    ekVar.a(jA);
                    return aVar.b();
                }
                switch (iB) {
                    case 1:
                        try {
                            aVar.c = (ey) ey.ADAPTER.a(ekVar);
                        } catch (ej.a e) {
                            aVar.a(iB, eg.VARINT, Long.valueOf(e.a));
                        }
                        break;
                    case 2:
                        aVar.d = (String) ej.p.a(ekVar);
                        break;
                    case 3:
                        aVar.e = (Long) ej.i.a(ekVar);
                        break;
                    case 4:
                        aVar.i = (Long) ej.i.a(ekVar);
                        break;
                    case 5:
                        aVar.j = (fa) fa.c.a(ekVar);
                        break;
                    case 6:
                        aVar.k = (eu) eu.c.a(ekVar);
                        break;
                    case 7:
                        aVar.l = (fh) fh.c.a(ekVar);
                        break;
                    case 8:
                        aVar.m = (Integer) ej.d.a(ekVar);
                        break;
                    case 9:
                        aVar.n = (Integer) ej.d.a(ekVar);
                        break;
                    case 10:
                        aVar.o = (ex) ex.c.a(ekVar);
                        break;
                    case 11:
                        aVar.p = (fd) fd.c.a(ekVar);
                        break;
                    case 12:
                        aVar.q = (String) ej.p.a(ekVar);
                        break;
                    case 13:
                        aVar.r = (String) ej.p.a(ekVar);
                        break;
                    case 14:
                        aVar.t = (String) ej.p.a(ekVar);
                        break;
                    case 15:
                        aVar.u = (String) ej.p.a(ekVar);
                        break;
                    case 16:
                        aVar.v = (String) ej.p.a(ekVar);
                        break;
                    case 17:
                        aVar.w.add(ez.c.a(ekVar));
                        break;
                    case 18:
                        aVar.s = (fc) fc.c.a(ekVar);
                        break;
                    case 19:
                        aVar.f = (Long) ej.i.a(ekVar);
                        break;
                    case 20:
                        aVar.g = (String) ej.p.a(ekVar);
                        break;
                    case 21:
                        aVar.h = (Long) ej.i.a(ekVar);
                        break;
                    case 22:
                        aVar.x = (String) ej.p.a(ekVar);
                        break;
                    case 23:
                        aVar.y = (Integer) ej.d.a(ekVar);
                        break;
                    case 24:
                        aVar.z = (Long) ej.i.a(ekVar);
                        break;
                    case 25:
                        aVar.A = (Long) ej.i.a(ekVar);
                        break;
                    default:
                        eg egVarC = ekVar.c();
                        aVar.a(iB, egVarC, egVarC.a().a(ekVar));
                        break;
                }
            }
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            ev evVar = (ev) obj;
            return (evVar.K != null ? ej.i.a(24, evVar.K) : 0) + ez.c.a().a(17, evVar.H) + (evVar.G != null ? ej.p.a(16, evVar.G) : 0) + ej.i.a(3, evVar.p) + ey.ADAPTER.a(1, evVar.n) + ej.p.a(2, evVar.o) + (evVar.q != null ? ej.i.a(19, evVar.q) : 0) + (evVar.r != null ? ej.p.a(20, evVar.r) : 0) + (evVar.s != null ? ej.i.a(21, evVar.s) : 0) + (evVar.t != null ? ej.i.a(4, evVar.t) : 0) + (evVar.u != null ? fa.c.a(5, evVar.u) : 0) + (evVar.v != null ? eu.c.a(6, evVar.v) : 0) + (evVar.w != null ? fh.c.a(7, evVar.w) : 0) + (evVar.x != null ? ej.d.a(8, evVar.x) : 0) + (evVar.y != null ? ej.d.a(9, evVar.y) : 0) + (evVar.z != null ? ex.c.a(10, evVar.z) : 0) + (evVar.A != null ? fd.c.a(11, evVar.A) : 0) + (evVar.B != null ? ej.p.a(12, evVar.B) : 0) + (evVar.C != null ? ej.p.a(13, evVar.C) : 0) + (evVar.D != null ? fc.c.a(18, evVar.D) : 0) + (evVar.E != null ? ej.p.a(14, evVar.E) : 0) + (evVar.F != null ? ej.p.a(15, evVar.F) : 0) + (evVar.I != null ? ej.p.a(22, evVar.I) : 0) + (evVar.J != null ? ej.d.a(23, evVar.J) : 0) + (evVar.L != null ? ej.i.a(25, evVar.L) : 0) + evVar.a().c();
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) {
            return b(ekVar);
        }

        @Override // com.tapjoy.internal.ej
        public final /* bridge */ /* synthetic */ void a(el elVar, Object obj) {
            ev evVar = (ev) obj;
            ey.ADAPTER.a(elVar, 1, evVar.n);
            ej.p.a(elVar, 2, evVar.o);
            ej.i.a(elVar, 3, evVar.p);
            if (evVar.q != null) {
                ej.i.a(elVar, 19, evVar.q);
            }
            if (evVar.r != null) {
                ej.p.a(elVar, 20, evVar.r);
            }
            if (evVar.s != null) {
                ej.i.a(elVar, 21, evVar.s);
            }
            if (evVar.t != null) {
                ej.i.a(elVar, 4, evVar.t);
            }
            if (evVar.u != null) {
                fa.c.a(elVar, 5, evVar.u);
            }
            if (evVar.v != null) {
                eu.c.a(elVar, 6, evVar.v);
            }
            if (evVar.w != null) {
                fh.c.a(elVar, 7, evVar.w);
            }
            if (evVar.x != null) {
                ej.d.a(elVar, 8, evVar.x);
            }
            if (evVar.y != null) {
                ej.d.a(elVar, 9, evVar.y);
            }
            if (evVar.z != null) {
                ex.c.a(elVar, 10, evVar.z);
            }
            if (evVar.A != null) {
                fd.c.a(elVar, 11, evVar.A);
            }
            if (evVar.B != null) {
                ej.p.a(elVar, 12, evVar.B);
            }
            if (evVar.C != null) {
                ej.p.a(elVar, 13, evVar.C);
            }
            if (evVar.D != null) {
                fc.c.a(elVar, 18, evVar.D);
            }
            if (evVar.E != null) {
                ej.p.a(elVar, 14, evVar.E);
            }
            if (evVar.F != null) {
                ej.p.a(elVar, 15, evVar.F);
            }
            if (evVar.G != null) {
                ej.p.a(elVar, 16, evVar.G);
            }
            ez.c.a().a(elVar, 17, evVar.H);
            if (evVar.I != null) {
                ej.p.a(elVar, 22, evVar.I);
            }
            if (evVar.J != null) {
                ej.d.a(elVar, 23, evVar.J);
            }
            if (evVar.K != null) {
                ej.i.a(elVar, 24, evVar.K);
            }
            if (evVar.L != null) {
                ej.i.a(elVar, 25, evVar.L);
            }
            elVar.a(evVar.a());
        }
    }

    public ev(ey eyVar, String str, Long l2, Long l3, String str2, Long l4, Long l5, fa faVar, eu euVar, fh fhVar, Integer num, Integer num2, ex exVar, fd fdVar, String str3, String str4, fc fcVar, String str5, String str6, String str7, List list, String str8, Integer num3, Long l6, Long l7, iu iuVar) {
        super(c, iuVar);
        this.n = eyVar;
        this.o = str;
        this.p = l2;
        this.q = l3;
        this.r = str2;
        this.s = l4;
        this.t = l5;
        this.u = faVar;
        this.v = euVar;
        this.w = fhVar;
        this.x = num;
        this.y = num2;
        this.z = exVar;
        this.A = fdVar;
        this.B = str3;
        this.C = str4;
        this.D = fcVar;
        this.E = str5;
        this.F = str6;
        this.G = str7;
        this.H = eo.a(TJAdUnitConstants.String.USAGE_TRACKER_VALUES, list);
        this.I = str8;
        this.J = num3;
        this.K = l6;
        this.L = l7;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ev)) {
            return false;
        }
        ev evVar = (ev) obj;
        return a().equals(evVar.a()) && this.n.equals(evVar.n) && this.o.equals(evVar.o) && this.p.equals(evVar.p) && eo.a(this.q, evVar.q) && eo.a(this.r, evVar.r) && eo.a(this.s, evVar.s) && eo.a(this.t, evVar.t) && eo.a(this.u, evVar.u) && eo.a(this.v, evVar.v) && eo.a(this.w, evVar.w) && eo.a(this.x, evVar.x) && eo.a(this.y, evVar.y) && eo.a(this.z, evVar.z) && eo.a(this.A, evVar.A) && eo.a(this.B, evVar.B) && eo.a(this.C, evVar.C) && eo.a(this.D, evVar.D) && eo.a(this.E, evVar.E) && eo.a(this.F, evVar.F) && eo.a(this.G, evVar.G) && this.H.equals(evVar.H) && eo.a(this.I, evVar.I) && eo.a(this.J, evVar.J) && eo.a(this.K, evVar.K) && eo.a(this.L, evVar.L);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = (((this.K != null ? this.K.hashCode() : 0) + (((this.J != null ? this.J.hashCode() : 0) + (((this.I != null ? this.I.hashCode() : 0) + (((((this.G != null ? this.G.hashCode() : 0) + (((this.F != null ? this.F.hashCode() : 0) + (((this.E != null ? this.E.hashCode() : 0) + (((this.D != null ? this.D.hashCode() : 0) + (((this.C != null ? this.C.hashCode() : 0) + (((this.B != null ? this.B.hashCode() : 0) + (((this.A != null ? this.A.hashCode() : 0) + (((this.z != null ? this.z.hashCode() : 0) + (((this.y != null ? this.y.hashCode() : 0) + (((this.x != null ? this.x.hashCode() : 0) + (((this.w != null ? this.w.hashCode() : 0) + (((this.v != null ? this.v.hashCode() : 0) + (((this.u != null ? this.u.hashCode() : 0) + (((this.t != null ? this.t.hashCode() : 0) + (((this.s != null ? this.s.hashCode() : 0) + (((this.r != null ? this.r.hashCode() : 0) + (((this.q != null ? this.q.hashCode() : 0) + (((((((a().hashCode() * 37) + this.n.hashCode()) * 37) + this.o.hashCode()) * 37) + this.p.hashCode()) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37) + this.H.hashCode()) * 37)) * 37)) * 37)) * 37) + (this.L != null ? this.L.hashCode() : 0);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", type=").append(this.n);
        sb.append(", name=").append(this.o);
        sb.append(", time=").append(this.p);
        if (this.q != null) {
            sb.append(", systemTime=").append(this.q);
        }
        if (this.r != null) {
            sb.append(", instanceId=").append(this.r);
        }
        if (this.s != null) {
            sb.append(", elapsedRealtime=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", duration=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", info=").append(this.u);
        }
        if (this.v != null) {
            sb.append(", app=").append(this.v);
        }
        if (this.w != null) {
            sb.append(", user=").append(this.w);
        }
        if (this.x != null) {
            sb.append(", xxx_session_seq=").append(this.x);
        }
        if (this.y != null) {
            sb.append(", eventSeq=").append(this.y);
        }
        if (this.z != null) {
            sb.append(", eventPrev=").append(this.z);
        }
        if (this.A != null) {
            sb.append(", purchase=").append(this.A);
        }
        if (this.B != null) {
            sb.append(", exception=").append(this.B);
        }
        if (this.C != null) {
            sb.append(", metaBase=").append(this.C);
        }
        if (this.D != null) {
            sb.append(", meta=").append(this.D);
        }
        if (this.E != null) {
            sb.append(", category=").append(this.E);
        }
        if (this.F != null) {
            sb.append(", p1=").append(this.F);
        }
        if (this.G != null) {
            sb.append(", p2=").append(this.G);
        }
        if (!this.H.isEmpty()) {
            sb.append(", values=").append(this.H);
        }
        if (this.I != null) {
            sb.append(", dimensions=").append(this.I);
        }
        if (this.J != null) {
            sb.append(", count=").append(this.J);
        }
        if (this.K != null) {
            sb.append(", firstTime=").append(this.K);
        }
        if (this.L != null) {
            sb.append(", lastTime=").append(this.L);
        }
        return sb.replace(0, 2, "Event{").append('}').toString();
    }
}
