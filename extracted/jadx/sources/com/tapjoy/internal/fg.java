package com.tapjoy.internal;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fg extends eh {
    public static final ej c = new b();
    public final List d;

    public static final class a extends eh.a {
        public List c = eo.a();

        public final fg b() {
            return new fg(this.c, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fg.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fg fgVar = (fg) obj;
            return ej.p.a().a(1, fgVar.d) + fgVar.a().c();
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
                        aVar.c.add(ej.p.a(ekVar));
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
            fg fgVar = (fg) obj;
            ej.p.a().a(elVar, 1, fgVar.d);
            elVar.a(fgVar.a());
        }
    }

    public fg(List list) {
        this(list, iu.b);
    }

    public fg(List list, iu iuVar) {
        super(c, iuVar);
        this.d = eo.a(MessengerShareContentUtility.ELEMENTS, list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fg)) {
            return false;
        }
        fg fgVar = (fg) obj;
        return a().equals(fgVar.a()) && this.d.equals(fgVar.d);
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
            sb.append(", elements=").append(this.d);
        }
        return sb.replace(0, 2, "StringList{").append('}').toString();
    }
}
