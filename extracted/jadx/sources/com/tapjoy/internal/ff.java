package com.tapjoy.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ff extends eh {
    public static final ej c = new b();
    public final List d;

    public static final class a extends eh.a {
        public List c = eo.a();

        public final ff b() {
            return new ff(this.c, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, ff.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            ff ffVar = (ff) obj;
            return fe.c.a().a(1, ffVar.d) + ffVar.a().c();
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
                        aVar.c.add(fe.c.a(ekVar));
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
            ff ffVar = (ff) obj;
            fe.c.a().a(elVar, 1, ffVar.d);
            elVar.a(ffVar.a());
        }
    }

    public ff(List list) {
        this(list, iu.b);
    }

    public ff(List list, iu iuVar) {
        super(c, iuVar);
        this.d = eo.a("pushes", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ff)) {
            return false;
        }
        ff ffVar = (ff) obj;
        return a().equals(ffVar.a()) && this.d.equals(ffVar.d);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = (a().hashCode() * 37) + this.d.hashCode();
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.tapjoy.internal.eh
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.d.isEmpty()) {
            sb.append(", pushes=").append(this.d);
        }
        return sb.replace(0, 2, "PushList{").append('}').toString();
    }
}
