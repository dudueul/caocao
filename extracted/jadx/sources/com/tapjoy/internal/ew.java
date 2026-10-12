package com.tapjoy.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ew extends eh {
    public static final ej c = new b();
    public final List d;

    public static final class a extends eh.a {
        public List c = eo.a();

        public final ew b() {
            return new ew(this.c, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, ew.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            ew ewVar = (ew) obj;
            return ev.c.a().a(1, ewVar.d) + ewVar.a().c();
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
                        aVar.c.add(ev.c.a(ekVar));
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
            ew ewVar = (ew) obj;
            ev.c.a().a(elVar, 1, ewVar.d);
            elVar.a(ewVar.a());
        }
    }

    public ew(List list, iu iuVar) {
        super(c, iuVar);
        this.d = eo.a("events", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ew)) {
            return false;
        }
        ew ewVar = (ew) obj;
        return a().equals(ewVar.a()) && this.d.equals(ewVar.d);
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
            sb.append(", events=").append(this.d);
        }
        return sb.replace(0, 2, "EventBatch{").append('}').toString();
    }
}
