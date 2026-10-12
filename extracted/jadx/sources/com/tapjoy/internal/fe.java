package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fe extends eh {
    public static final ej c = new b();
    public static final Long d = 0L;
    public static final Long e = 0L;
    public final String f;
    public final Long g;
    public final Long h;

    public static final class a extends eh.a {
        public String c;
        public Long d;
        public Long e;

        public final fe b() {
            if (this.c == null || this.d == null) {
                throw eo.a(this.c, "id", this.d, "received");
            }
            return new fe(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fe.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fe feVar = (fe) obj;
            return (feVar.h != null ? ej.i.a(3, feVar.h) : 0) + ej.i.a(2, feVar.g) + ej.p.a(1, feVar.f) + feVar.a().c();
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
                        aVar.d = (Long) ej.i.a(ekVar);
                        break;
                    case 3:
                        aVar.e = (Long) ej.i.a(ekVar);
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
            fe feVar = (fe) obj;
            ej.p.a(elVar, 1, feVar.f);
            ej.i.a(elVar, 2, feVar.g);
            if (feVar.h != null) {
                ej.i.a(elVar, 3, feVar.h);
            }
            elVar.a(feVar.a());
        }
    }

    public fe(String str, Long l) {
        this(str, l, null, iu.b);
    }

    public fe(String str, Long l, Long l2, iu iuVar) {
        super(c, iuVar);
        this.f = str;
        this.g = l;
        this.h = l2;
    }

    public final a b() {
        a aVar = new a();
        aVar.c = this.f;
        aVar.d = this.g;
        aVar.e = this.h;
        aVar.a(a());
        return aVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fe)) {
            return false;
        }
        fe feVar = (fe) obj;
        return a().equals(feVar.a()) && this.f.equals(feVar.f) && this.g.equals(feVar.g) && eo.a(this.h, feVar.h);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (this.h != null ? this.h.hashCode() : 0) + (((((a().hashCode() * 37) + this.f.hashCode()) * 37) + this.g.hashCode()) * 37);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", id=").append(this.f);
        sb.append(", received=").append(this.g);
        if (this.h != null) {
            sb.append(", clicked=").append(this.h);
        }
        return sb.replace(0, 2, "Push{").append('}').toString();
    }
}
