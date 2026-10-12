package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class eu extends eh {
    public static final ej c = new b();
    public static final Integer d = 0;
    public final String e;
    public final Integer f;
    public final String g;
    public final String h;
    public final String i;

    public static final class a extends eh.a {
        public String c;
        public Integer d;
        public String e;
        public String f;
        public String g;

        public final eu b() {
            return new eu(this.c, this.d, this.e, this.f, this.g, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, eu.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            eu euVar = (eu) obj;
            return (euVar.h != null ? ej.p.a(4, euVar.h) : 0) + (euVar.f != null ? ej.d.a(2, euVar.f) : 0) + (euVar.e != null ? ej.p.a(1, euVar.e) : 0) + (euVar.g != null ? ej.p.a(3, euVar.g) : 0) + (euVar.i != null ? ej.p.a(5, euVar.i) : 0) + euVar.a().c();
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
                        aVar.e = (String) ej.p.a(ekVar);
                        break;
                    case 4:
                        aVar.f = (String) ej.p.a(ekVar);
                        break;
                    case 5:
                        aVar.g = (String) ej.p.a(ekVar);
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
            eu euVar = (eu) obj;
            if (euVar.e != null) {
                ej.p.a(elVar, 1, euVar.e);
            }
            if (euVar.f != null) {
                ej.d.a(elVar, 2, euVar.f);
            }
            if (euVar.g != null) {
                ej.p.a(elVar, 3, euVar.g);
            }
            if (euVar.h != null) {
                ej.p.a(elVar, 4, euVar.h);
            }
            if (euVar.i != null) {
                ej.p.a(elVar, 5, euVar.i);
            }
            elVar.a(euVar.a());
        }
    }

    public eu(String str, Integer num, String str2, String str3, String str4, iu iuVar) {
        super(c, iuVar);
        this.e = str;
        this.f = num;
        this.g = str2;
        this.h = str3;
        this.i = str4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eu)) {
            return false;
        }
        eu euVar = (eu) obj;
        return a().equals(euVar.a()) && eo.a(this.e, euVar.e) && eo.a(this.f, euVar.f) && eo.a(this.g, euVar.g) && eo.a(this.h, euVar.h) && eo.a(this.i, euVar.i);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((this.h != null ? this.h.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + (((this.f != null ? this.f.hashCode() : 0) + (((this.e != null ? this.e.hashCode() : 0) + (a().hashCode() * 37)) * 37)) * 37)) * 37)) * 37) + (this.i != null ? this.i.hashCode() : 0);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.e != null) {
            sb.append(", pkgVer=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", pkgRev=").append(this.f);
        }
        if (this.g != null) {
            sb.append(", dataVer=").append(this.g);
        }
        if (this.h != null) {
            sb.append(", installer=").append(this.h);
        }
        if (this.i != null) {
            sb.append(", store=").append(this.i);
        }
        return sb.replace(0, 2, "App{").append('}').toString();
    }
}
