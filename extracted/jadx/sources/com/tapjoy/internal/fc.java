package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fc extends eh {
    public static final ej c = new b();
    public final String d;
    public final String e;
    public final String f;

    public static final class a extends eh.a {
        public String c;
        public String d;
        public String e;

        public final fc b() {
            return new fc(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fc.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fc fcVar = (fc) obj;
            return (fcVar.d != null ? ej.p.a(1, fcVar.d) : 0) + (fcVar.e != null ? ej.p.a(2, fcVar.e) : 0) + (fcVar.f != null ? ej.p.a(3, fcVar.f) : 0) + fcVar.a().c();
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
                        aVar.d = (String) ej.p.a(ekVar);
                        break;
                    case 3:
                        aVar.e = (String) ej.p.a(ekVar);
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
            fc fcVar = (fc) obj;
            if (fcVar.d != null) {
                ej.p.a(elVar, 1, fcVar.d);
            }
            if (fcVar.e != null) {
                ej.p.a(elVar, 2, fcVar.e);
            }
            if (fcVar.f != null) {
                ej.p.a(elVar, 3, fcVar.f);
            }
            elVar.a(fcVar.a());
        }
    }

    public fc(String str, String str2, String str3) {
        this(str, str2, str3, iu.b);
    }

    public fc(String str, String str2, String str3, iu iuVar) {
        super(c, iuVar);
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fc)) {
            return false;
        }
        fc fcVar = (fc) obj;
        return a().equals(fcVar.a()) && eo.a(this.d, fcVar.d) && eo.a(this.e, fcVar.e) && eo.a(this.f, fcVar.f);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (a().hashCode() * 37)) * 37)) * 37) + (this.f != null ? this.f.hashCode() : 0);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.d != null) {
            sb.append(", fq7Change=").append(this.d);
        }
        if (this.e != null) {
            sb.append(", fq30Change=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", pushId=").append(this.f);
        }
        return sb.replace(0, 2, "Meta{").append('}').toString();
    }
}
