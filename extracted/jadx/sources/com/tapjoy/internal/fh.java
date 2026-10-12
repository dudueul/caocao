package com.tapjoy.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fh extends eh {
    public static final ej c = new b();
    public static final Long d = 0L;
    public static final Integer e = 0;
    public static final Integer f = 0;
    public static final Integer g = 0;
    public static final Long h = 0L;
    public static final Long i = 0L;
    public static final Long j = 0L;
    public static final Integer k = 0;
    public static final Double l = Double.valueOf(0.0d);
    public static final Long m = 0L;
    public static final Double n = Double.valueOf(0.0d);
    public static final Boolean o = false;
    public static final Integer p = 0;
    public static final Integer q = 0;
    public static final Boolean r = false;
    public final Long A;
    public final String B;
    public final Integer C;
    public final Double D;
    public final Long E;
    public final Double F;
    public final String G;
    public final Boolean H;
    public final String I;
    public final Integer J;
    public final Integer K;
    public final String L;
    public final String M;
    public final String N;
    public final String O;
    public final String P;
    public final List Q;
    public final Boolean R;
    public final Long s;
    public final String t;
    public final Integer u;
    public final Integer v;
    public final List w;
    public final Integer x;
    public final Long y;
    public final Long z;

    public static final class a extends eh.a {
        public Boolean B;
        public Long c;
        public String d;
        public Integer e;
        public Integer f;
        public Integer h;
        public Long i;
        public Long j;
        public Long k;
        public String l;
        public Integer m;
        public Double n;
        public Long o;
        public Double p;
        public String q;
        public Boolean r;
        public String s;
        public Integer t;
        public Integer u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;
        public List g = eo.a();
        public List A = eo.a();

        public final fh b() {
            return new fh(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fh.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fh fhVar = (fh) obj;
            return (fhVar.P != null ? ej.p.a(12, fhVar.P) : 0) + fe.c.a().a(15, fhVar.w) + (fhVar.v != null ? ej.d.a(14, fhVar.v) : 0) + (fhVar.t != null ? ej.p.a(2, fhVar.t) : 0) + (fhVar.s != null ? ej.i.a(1, fhVar.s) : 0) + (fhVar.u != null ? ej.d.a(13, fhVar.u) : 0) + (fhVar.x != null ? ej.d.a(16, fhVar.x) : 0) + (fhVar.y != null ? ej.i.a(17, fhVar.y) : 0) + (fhVar.z != null ? ej.i.a(18, fhVar.z) : 0) + (fhVar.A != null ? ej.i.a(19, fhVar.A) : 0) + (fhVar.B != null ? ej.p.a(20, fhVar.B) : 0) + (fhVar.C != null ? ej.d.a(3, fhVar.C) : 0) + (fhVar.D != null ? ej.o.a(21, fhVar.D) : 0) + (fhVar.E != null ? ej.i.a(4, fhVar.E) : 0) + (fhVar.F != null ? ej.o.a(22, fhVar.F) : 0) + (fhVar.G != null ? ej.p.a(23, fhVar.G) : 0) + (fhVar.H != null ? ej.c.a(24, fhVar.H) : 0) + (fhVar.I != null ? ej.p.a(5, fhVar.I) : 0) + (fhVar.J != null ? ej.d.a(6, fhVar.J) : 0) + (fhVar.K != null ? ej.d.a(7, fhVar.K) : 0) + (fhVar.L != null ? ej.p.a(8, fhVar.L) : 0) + (fhVar.M != null ? ej.p.a(9, fhVar.M) : 0) + (fhVar.N != null ? ej.p.a(10, fhVar.N) : 0) + (fhVar.O != null ? ej.p.a(11, fhVar.O) : 0) + ej.p.a().a(26, fhVar.Q) + (fhVar.R != null ? ej.c.a(25, fhVar.R) : 0) + fhVar.a().c();
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) throws IOException {
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
                        aVar.c = (Long) ej.i.a(ekVar);
                        break;
                    case 2:
                        aVar.d = (String) ej.p.a(ekVar);
                        break;
                    case 3:
                        aVar.m = (Integer) ej.d.a(ekVar);
                        break;
                    case 4:
                        aVar.o = (Long) ej.i.a(ekVar);
                        break;
                    case 5:
                        aVar.s = (String) ej.p.a(ekVar);
                        break;
                    case 6:
                        aVar.t = (Integer) ej.d.a(ekVar);
                        break;
                    case 7:
                        aVar.u = (Integer) ej.d.a(ekVar);
                        break;
                    case 8:
                        aVar.v = (String) ej.p.a(ekVar);
                        break;
                    case 9:
                        aVar.w = (String) ej.p.a(ekVar);
                        break;
                    case 10:
                        aVar.x = (String) ej.p.a(ekVar);
                        break;
                    case 11:
                        aVar.y = (String) ej.p.a(ekVar);
                        break;
                    case 12:
                        aVar.z = (String) ej.p.a(ekVar);
                        break;
                    case 13:
                        aVar.e = (Integer) ej.d.a(ekVar);
                        break;
                    case 14:
                        aVar.f = (Integer) ej.d.a(ekVar);
                        break;
                    case 15:
                        aVar.g.add(fe.c.a(ekVar));
                        break;
                    case 16:
                        aVar.h = (Integer) ej.d.a(ekVar);
                        break;
                    case 17:
                        aVar.i = (Long) ej.i.a(ekVar);
                        break;
                    case 18:
                        aVar.j = (Long) ej.i.a(ekVar);
                        break;
                    case 19:
                        aVar.k = (Long) ej.i.a(ekVar);
                        break;
                    case 20:
                        aVar.l = (String) ej.p.a(ekVar);
                        break;
                    case 21:
                        aVar.n = (Double) ej.o.a(ekVar);
                        break;
                    case 22:
                        aVar.p = (Double) ej.o.a(ekVar);
                        break;
                    case 23:
                        aVar.q = (String) ej.p.a(ekVar);
                        break;
                    case 24:
                        aVar.r = (Boolean) ej.c.a(ekVar);
                        break;
                    case 25:
                        aVar.B = (Boolean) ej.c.a(ekVar);
                        break;
                    case 26:
                        aVar.A.add(ej.p.a(ekVar));
                        break;
                    default:
                        eg egVarC = ekVar.c();
                        aVar.a(iB, egVarC, egVarC.a().a(ekVar));
                        break;
                }
            }
        }

        @Override // com.tapjoy.internal.ej
        public final /* bridge */ /* synthetic */ void a(el elVar, Object obj) {
            fh fhVar = (fh) obj;
            if (fhVar.s != null) {
                ej.i.a(elVar, 1, fhVar.s);
            }
            if (fhVar.t != null) {
                ej.p.a(elVar, 2, fhVar.t);
            }
            if (fhVar.u != null) {
                ej.d.a(elVar, 13, fhVar.u);
            }
            if (fhVar.v != null) {
                ej.d.a(elVar, 14, fhVar.v);
            }
            fe.c.a().a(elVar, 15, fhVar.w);
            if (fhVar.x != null) {
                ej.d.a(elVar, 16, fhVar.x);
            }
            if (fhVar.y != null) {
                ej.i.a(elVar, 17, fhVar.y);
            }
            if (fhVar.z != null) {
                ej.i.a(elVar, 18, fhVar.z);
            }
            if (fhVar.A != null) {
                ej.i.a(elVar, 19, fhVar.A);
            }
            if (fhVar.B != null) {
                ej.p.a(elVar, 20, fhVar.B);
            }
            if (fhVar.C != null) {
                ej.d.a(elVar, 3, fhVar.C);
            }
            if (fhVar.D != null) {
                ej.o.a(elVar, 21, fhVar.D);
            }
            if (fhVar.E != null) {
                ej.i.a(elVar, 4, fhVar.E);
            }
            if (fhVar.F != null) {
                ej.o.a(elVar, 22, fhVar.F);
            }
            if (fhVar.G != null) {
                ej.p.a(elVar, 23, fhVar.G);
            }
            if (fhVar.H != null) {
                ej.c.a(elVar, 24, fhVar.H);
            }
            if (fhVar.I != null) {
                ej.p.a(elVar, 5, fhVar.I);
            }
            if (fhVar.J != null) {
                ej.d.a(elVar, 6, fhVar.J);
            }
            if (fhVar.K != null) {
                ej.d.a(elVar, 7, fhVar.K);
            }
            if (fhVar.L != null) {
                ej.p.a(elVar, 8, fhVar.L);
            }
            if (fhVar.M != null) {
                ej.p.a(elVar, 9, fhVar.M);
            }
            if (fhVar.N != null) {
                ej.p.a(elVar, 10, fhVar.N);
            }
            if (fhVar.O != null) {
                ej.p.a(elVar, 11, fhVar.O);
            }
            if (fhVar.P != null) {
                ej.p.a(elVar, 12, fhVar.P);
            }
            ej.p.a().a(elVar, 26, fhVar.Q);
            if (fhVar.R != null) {
                ej.c.a(elVar, 25, fhVar.R);
            }
            elVar.a(fhVar.a());
        }
    }

    public fh(Long l2, String str, Integer num, Integer num2, List list, Integer num3, Long l3, Long l4, Long l5, String str2, Integer num4, Double d2, Long l6, Double d3, String str3, Boolean bool, String str4, Integer num5, Integer num6, String str5, String str6, String str7, String str8, String str9, List list2, Boolean bool2, iu iuVar) {
        super(c, iuVar);
        this.s = l2;
        this.t = str;
        this.u = num;
        this.v = num2;
        this.w = eo.a("pushes", list);
        this.x = num3;
        this.y = l3;
        this.z = l4;
        this.A = l5;
        this.B = str2;
        this.C = num4;
        this.D = d2;
        this.E = l6;
        this.F = d3;
        this.G = str3;
        this.H = bool;
        this.I = str4;
        this.J = num5;
        this.K = num6;
        this.L = str5;
        this.M = str6;
        this.N = str7;
        this.O = str8;
        this.P = str9;
        this.Q = eo.a("tags", list2);
        this.R = bool2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fh)) {
            return false;
        }
        fh fhVar = (fh) obj;
        return a().equals(fhVar.a()) && eo.a(this.s, fhVar.s) && eo.a(this.t, fhVar.t) && eo.a(this.u, fhVar.u) && eo.a(this.v, fhVar.v) && this.w.equals(fhVar.w) && eo.a(this.x, fhVar.x) && eo.a(this.y, fhVar.y) && eo.a(this.z, fhVar.z) && eo.a(this.A, fhVar.A) && eo.a(this.B, fhVar.B) && eo.a(this.C, fhVar.C) && eo.a(this.D, fhVar.D) && eo.a(this.E, fhVar.E) && eo.a(this.F, fhVar.F) && eo.a(this.G, fhVar.G) && eo.a(this.H, fhVar.H) && eo.a(this.I, fhVar.I) && eo.a(this.J, fhVar.J) && eo.a(this.K, fhVar.K) && eo.a(this.L, fhVar.L) && eo.a(this.M, fhVar.M) && eo.a(this.N, fhVar.N) && eo.a(this.O, fhVar.O) && eo.a(this.P, fhVar.P) && this.Q.equals(fhVar.Q) && eo.a(this.R, fhVar.R);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = (((((this.P != null ? this.P.hashCode() : 0) + (((this.O != null ? this.O.hashCode() : 0) + (((this.N != null ? this.N.hashCode() : 0) + (((this.M != null ? this.M.hashCode() : 0) + (((this.L != null ? this.L.hashCode() : 0) + (((this.K != null ? this.K.hashCode() : 0) + (((this.J != null ? this.J.hashCode() : 0) + (((this.I != null ? this.I.hashCode() : 0) + (((this.H != null ? this.H.hashCode() : 0) + (((this.G != null ? this.G.hashCode() : 0) + (((this.F != null ? this.F.hashCode() : 0) + (((this.E != null ? this.E.hashCode() : 0) + (((this.D != null ? this.D.hashCode() : 0) + (((this.C != null ? this.C.hashCode() : 0) + (((this.B != null ? this.B.hashCode() : 0) + (((this.A != null ? this.A.hashCode() : 0) + (((this.z != null ? this.z.hashCode() : 0) + (((this.y != null ? this.y.hashCode() : 0) + (((this.x != null ? this.x.hashCode() : 0) + (((((this.v != null ? this.v.hashCode() : 0) + (((this.u != null ? this.u.hashCode() : 0) + (((this.t != null ? this.t.hashCode() : 0) + (((this.s != null ? this.s.hashCode() : 0) + (a().hashCode() * 37)) * 37)) * 37)) * 37)) * 37) + this.w.hashCode()) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37) + this.Q.hashCode()) * 37) + (this.R != null ? this.R.hashCode() : 0);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.s != null) {
            sb.append(", installed=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", referrer=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", fq7=").append(this.u);
        }
        if (this.v != null) {
            sb.append(", fq30=").append(this.v);
        }
        if (!this.w.isEmpty()) {
            sb.append(", pushes=").append(this.w);
        }
        if (this.x != null) {
            sb.append(", sessionTotalCount=").append(this.x);
        }
        if (this.y != null) {
            sb.append(", sessionTotalDuration=").append(this.y);
        }
        if (this.z != null) {
            sb.append(", sessionLastTime=").append(this.z);
        }
        if (this.A != null) {
            sb.append(", sessionLastDuration=").append(this.A);
        }
        if (this.B != null) {
            sb.append(", purchaseCurrency=").append(this.B);
        }
        if (this.C != null) {
            sb.append(", purchaseTotalCount=").append(this.C);
        }
        if (this.D != null) {
            sb.append(", purchaseTotalPrice=").append(this.D);
        }
        if (this.E != null) {
            sb.append(", purchaseLastTime=").append(this.E);
        }
        if (this.F != null) {
            sb.append(", purchaseLastPrice=").append(this.F);
        }
        if (this.G != null) {
            sb.append(", idfa=").append(this.G);
        }
        if (this.H != null) {
            sb.append(", idfaOptout=").append(this.H);
        }
        if (this.I != null) {
            sb.append(", userId=").append(this.I);
        }
        if (this.J != null) {
            sb.append(", userLevel=").append(this.J);
        }
        if (this.K != null) {
            sb.append(", friendCount=").append(this.K);
        }
        if (this.L != null) {
            sb.append(", uv1=").append(this.L);
        }
        if (this.M != null) {
            sb.append(", uv2=").append(this.M);
        }
        if (this.N != null) {
            sb.append(", uv3=").append(this.N);
        }
        if (this.O != null) {
            sb.append(", uv4=").append(this.O);
        }
        if (this.P != null) {
            sb.append(", uv5=").append(this.P);
        }
        if (!this.Q.isEmpty()) {
            sb.append(", tags=").append(this.Q);
        }
        if (this.R != null) {
            sb.append(", pushOptout=").append(this.R);
        }
        return sb.replace(0, 2, "User{").append('}').toString();
    }
}
