package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ex extends eh {
    public static final ej c = new b();
    public static final ey d = ey.APP;
    public final ey e;
    public final String f;
    public final String g;

    public static final class a extends eh.a {
        public ey c;
        public String d;
        public String e;

        public final ex b() {
            if (this.c == null || this.d == null) {
                throw eo.a(this.c, "type", this.d, "name");
            }
            return new ex(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, ex.class);
        }

        private static ex b(ek ekVar) throws IOException {
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
        public final /* synthetic */ int a(Object obj) {
            ex exVar = (ex) obj;
            return (exVar.g != null ? ej.p.a(3, exVar.g) : 0) + ej.p.a(2, exVar.f) + ey.ADAPTER.a(1, exVar.e) + exVar.a().c();
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) {
            return b(ekVar);
        }

        @Override // com.tapjoy.internal.ej
        public final /* bridge */ /* synthetic */ void a(el elVar, Object obj) {
            ex exVar = (ex) obj;
            ey.ADAPTER.a(elVar, 1, exVar.e);
            ej.p.a(elVar, 2, exVar.f);
            if (exVar.g != null) {
                ej.p.a(elVar, 3, exVar.g);
            }
            elVar.a(exVar.a());
        }
    }

    public ex(ey eyVar, String str, String str2, iu iuVar) {
        super(c, iuVar);
        this.e = eyVar;
        this.f = str;
        this.g = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ex)) {
            return false;
        }
        ex exVar = (ex) obj;
        return a().equals(exVar.a()) && this.e.equals(exVar.e) && this.f.equals(exVar.f) && eo.a(this.g, exVar.g);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (this.g != null ? this.g.hashCode() : 0) + (((((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode()) * 37);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", type=").append(this.e);
        sb.append(", name=").append(this.f);
        if (this.g != null) {
            sb.append(", category=").append(this.g);
        }
        return sb.replace(0, 2, "EventGroup{").append('}').toString();
    }
}
