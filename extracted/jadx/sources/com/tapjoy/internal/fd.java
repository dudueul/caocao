package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fd extends eh {
    public static final ej c = new b();
    public static final Integer d = 1;
    public static final Double e = Double.valueOf(0.0d);
    public static final Integer f = 0;
    public static final Long g = 0L;
    public final String h;
    public final Integer i;
    public final Double j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final Integer p;
    public final Long q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;

    public static final class a extends eh.a {
        public String c;
        public Integer d;
        public Double e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public Integer k;
        public Long l;
        public String m;
        public String n;
        public String o;
        public String p;

        public final fd b() {
            if (this.c == null) {
                throw eo.a(this.c, "productId");
            }
            return new fd(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fd.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fd fdVar = (fd) obj;
            return (fdVar.t != null ? ej.p.a(13, fdVar.t) : 0) + ej.p.a(1, fdVar.h) + (fdVar.i != null ? ej.d.a(2, fdVar.i) : 0) + (fdVar.j != null ? ej.o.a(3, fdVar.j) : 0) + (fdVar.k != null ? ej.p.a(4, fdVar.k) : 0) + (fdVar.l != null ? ej.p.a(5, fdVar.l) : 0) + (fdVar.m != null ? ej.p.a(6, fdVar.m) : 0) + (fdVar.n != null ? ej.p.a(7, fdVar.n) : 0) + (fdVar.o != null ? ej.p.a(8, fdVar.o) : 0) + (fdVar.p != null ? ej.d.a(9, fdVar.p) : 0) + (fdVar.q != null ? ej.i.a(10, fdVar.q) : 0) + (fdVar.r != null ? ej.p.a(11, fdVar.r) : 0) + (fdVar.s != null ? ej.p.a(12, fdVar.s) : 0) + (fdVar.u != null ? ej.p.a(14, fdVar.u) : 0) + fdVar.a().c();
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
                        aVar.c = (String) ej.p.a(ekVar);
                        break;
                    case 2:
                        aVar.d = (Integer) ej.d.a(ekVar);
                        break;
                    case 3:
                        aVar.e = (Double) ej.o.a(ekVar);
                        break;
                    case 4:
                        aVar.f = (String) ej.p.a(ekVar);
                        break;
                    case 5:
                        aVar.g = (String) ej.p.a(ekVar);
                        break;
                    case 6:
                        aVar.h = (String) ej.p.a(ekVar);
                        break;
                    case 7:
                        aVar.i = (String) ej.p.a(ekVar);
                        break;
                    case 8:
                        aVar.j = (String) ej.p.a(ekVar);
                        break;
                    case 9:
                        aVar.k = (Integer) ej.d.a(ekVar);
                        break;
                    case 10:
                        aVar.l = (Long) ej.i.a(ekVar);
                        break;
                    case 11:
                        aVar.m = (String) ej.p.a(ekVar);
                        break;
                    case 12:
                        aVar.n = (String) ej.p.a(ekVar);
                        break;
                    case 13:
                        aVar.o = (String) ej.p.a(ekVar);
                        break;
                    case 14:
                        aVar.p = (String) ej.p.a(ekVar);
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
            fd fdVar = (fd) obj;
            ej.p.a(elVar, 1, fdVar.h);
            if (fdVar.i != null) {
                ej.d.a(elVar, 2, fdVar.i);
            }
            if (fdVar.j != null) {
                ej.o.a(elVar, 3, fdVar.j);
            }
            if (fdVar.k != null) {
                ej.p.a(elVar, 4, fdVar.k);
            }
            if (fdVar.l != null) {
                ej.p.a(elVar, 5, fdVar.l);
            }
            if (fdVar.m != null) {
                ej.p.a(elVar, 6, fdVar.m);
            }
            if (fdVar.n != null) {
                ej.p.a(elVar, 7, fdVar.n);
            }
            if (fdVar.o != null) {
                ej.p.a(elVar, 8, fdVar.o);
            }
            if (fdVar.p != null) {
                ej.d.a(elVar, 9, fdVar.p);
            }
            if (fdVar.q != null) {
                ej.i.a(elVar, 10, fdVar.q);
            }
            if (fdVar.r != null) {
                ej.p.a(elVar, 11, fdVar.r);
            }
            if (fdVar.s != null) {
                ej.p.a(elVar, 12, fdVar.s);
            }
            if (fdVar.t != null) {
                ej.p.a(elVar, 13, fdVar.t);
            }
            if (fdVar.u != null) {
                ej.p.a(elVar, 14, fdVar.u);
            }
            elVar.a(fdVar.a());
        }
    }

    public fd(String str, Integer num, Double d2, String str2, String str3, String str4, String str5, String str6, Integer num2, Long l, String str7, String str8, String str9, String str10, iu iuVar) {
        super(c, iuVar);
        this.h = str;
        this.i = num;
        this.j = d2;
        this.k = str2;
        this.l = str3;
        this.m = str4;
        this.n = str5;
        this.o = str6;
        this.p = num2;
        this.q = l;
        this.r = str7;
        this.s = str8;
        this.t = str9;
        this.u = str10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fd)) {
            return false;
        }
        fd fdVar = (fd) obj;
        return a().equals(fdVar.a()) && this.h.equals(fdVar.h) && eo.a(this.i, fdVar.i) && eo.a(this.j, fdVar.j) && eo.a(this.k, fdVar.k) && eo.a(this.l, fdVar.l) && eo.a(this.m, fdVar.m) && eo.a(this.n, fdVar.n) && eo.a(this.o, fdVar.o) && eo.a(this.p, fdVar.p) && eo.a(this.q, fdVar.q) && eo.a(this.r, fdVar.r) && eo.a(this.s, fdVar.s) && eo.a(this.t, fdVar.t) && eo.a(this.u, fdVar.u);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((this.t != null ? this.t.hashCode() : 0) + (((this.s != null ? this.s.hashCode() : 0) + (((this.r != null ? this.r.hashCode() : 0) + (((this.q != null ? this.q.hashCode() : 0) + (((this.p != null ? this.p.hashCode() : 0) + (((this.o != null ? this.o.hashCode() : 0) + (((this.n != null ? this.n.hashCode() : 0) + (((this.m != null ? this.m.hashCode() : 0) + (((this.l != null ? this.l.hashCode() : 0) + (((this.k != null ? this.k.hashCode() : 0) + (((this.j != null ? this.j.hashCode() : 0) + (((this.i != null ? this.i.hashCode() : 0) + (((a().hashCode() * 37) + this.h.hashCode()) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37)) * 37) + (this.u != null ? this.u.hashCode() : 0);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", productId=").append(this.h);
        if (this.i != null) {
            sb.append(", productQuantity=").append(this.i);
        }
        if (this.j != null) {
            sb.append(", productPrice=").append(this.j);
        }
        if (this.k != null) {
            sb.append(", productPriceCurrency=").append(this.k);
        }
        if (this.l != null) {
            sb.append(", productType=").append(this.l);
        }
        if (this.m != null) {
            sb.append(", productTitle=").append(this.m);
        }
        if (this.n != null) {
            sb.append(", productDescription=").append(this.n);
        }
        if (this.o != null) {
            sb.append(", transactionId=").append(this.o);
        }
        if (this.p != null) {
            sb.append(", transactionState=").append(this.p);
        }
        if (this.q != null) {
            sb.append(", transactionDate=").append(this.q);
        }
        if (this.r != null) {
            sb.append(", campaignId=").append(this.r);
        }
        if (this.s != null) {
            sb.append(", currencyPrice=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", receipt=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", signature=").append(this.u);
        }
        return sb.replace(0, 2, "Purchase{").append('}').toString();
    }
}
