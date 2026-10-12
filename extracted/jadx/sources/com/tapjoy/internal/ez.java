package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ez extends eh {
    public static final ej c = new b();
    public static final Long d = 0L;
    public final String e;
    public final Long f;

    public static final class a extends eh.a {
        public String c;
        public Long d;

        public final ez b() {
            if (this.c == null || this.d == null) {
                throw eo.a(this.c, "name", this.d, "value");
            }
            return new ez(this.c, this.d, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, ez.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            ez ezVar = (ez) obj;
            return ej.p.a(1, ezVar.e) + ej.i.a(2, ezVar.f) + ezVar.a().c();
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
                    default:
                        eg egVarC = ekVar.c();
                        aVar.a(iB, egVarC, egVarC.a().a(ekVar));
                        break;
                }
            }
        }

        @Override // com.tapjoy.internal.ej
        public final /* bridge */ /* synthetic */ void a(el elVar, Object obj) {
            ez ezVar = (ez) obj;
            ej.p.a(elVar, 1, ezVar.e);
            ej.i.a(elVar, 2, ezVar.f);
            elVar.a(ezVar.a());
        }
    }

    public ez(String str, Long l) {
        this(str, l, iu.b);
    }

    public ez(String str, Long l, iu iuVar) {
        super(c, iuVar);
        this.e = str;
        this.f = l;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ez)) {
            return false;
        }
        ez ezVar = (ez) obj;
        return a().equals(ezVar.a()) && this.e.equals(ezVar.e) && this.f.equals(ezVar.f);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode();
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", name=").append(this.e);
        sb.append(", value=").append(this.f);
        return sb.replace(0, 2, "EventValue{").append('}').toString();
    }
}
