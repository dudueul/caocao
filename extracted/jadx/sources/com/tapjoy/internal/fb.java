package com.tapjoy.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fb extends eh {
    public static final ej c = new b();
    public final fa d;
    public final eu e;
    public final fh f;

    public static final class a extends eh.a {
        public fa c;
        public eu d;
        public fh e;

        public final fb b() {
            return new fb(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends ej {
        b() {
            super(eg.LENGTH_DELIMITED, fb.class);
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            fb fbVar = (fb) obj;
            return (fbVar.d != null ? fa.c.a(1, fbVar.d) : 0) + (fbVar.e != null ? eu.c.a(2, fbVar.e) : 0) + (fbVar.f != null ? fh.c.a(3, fbVar.f) : 0) + fbVar.a().c();
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
                        aVar.c = (fa) fa.c.a(ekVar);
                        break;
                    case 2:
                        aVar.d = (eu) eu.c.a(ekVar);
                        break;
                    case 3:
                        aVar.e = (fh) fh.c.a(ekVar);
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
            fb fbVar = (fb) obj;
            if (fbVar.d != null) {
                fa.c.a(elVar, 1, fbVar.d);
            }
            if (fbVar.e != null) {
                eu.c.a(elVar, 2, fbVar.e);
            }
            if (fbVar.f != null) {
                fh.c.a(elVar, 3, fbVar.f);
            }
            elVar.a(fbVar.a());
        }
    }

    public fb(fa faVar, eu euVar, fh fhVar) {
        this(faVar, euVar, fhVar, iu.b);
    }

    public fb(fa faVar, eu euVar, fh fhVar, iu iuVar) {
        super(c, iuVar);
        this.d = faVar;
        this.e = euVar;
        this.f = fhVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fb)) {
            return false;
        }
        fb fbVar = (fb) obj;
        return a().equals(fbVar.a()) && eo.a(this.d, fbVar.d) && eo.a(this.e, fbVar.e) && eo.a(this.f, fbVar.f);
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
            sb.append(", info=").append(this.d);
        }
        if (this.e != null) {
            sb.append(", app=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", user=").append(this.f);
        }
        return sb.replace(0, 2, "InfoSet{").append('}').toString();
    }
}
