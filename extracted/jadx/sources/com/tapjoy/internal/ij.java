package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ij extends ii {
    public final String c;
    public boolean d = false;
    private final gz e;
    private final fa f;
    private final eu g;
    private final fh h;
    private Context i;

    public static class a {
        public hg a;
        public final List b;

        public a(hg hgVar, List list) {
            this.a = hgVar;
            this.b = list;
        }
    }

    public ij(gz gzVar, fa faVar, eu euVar, fh fhVar, String str, Context context) {
        this.e = gzVar;
        this.f = faVar;
        this.g = euVar;
        this.h = fhVar;
        this.c = str;
        this.i = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.ii, com.tapjoy.internal.bz
    public final /* synthetic */ Object a(bn bnVar) {
        bnVar.h();
        List listC = null;
        hr hrVar = null;
        hu huVar = null;
        while (bnVar.j()) {
            String strL = bnVar.l();
            if ("interstitial".equals(strL)) {
                huVar = (hu) bnVar.a(hu.n);
            } else if ("contextual_button".equals(strL)) {
                hrVar = (hr) bnVar.a(hr.d);
            } else if ("enabled_placements".equals(strL)) {
                listC = bnVar.c();
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
        if (huVar == null || !(huVar.a() || huVar.b())) {
            return hrVar != null ? new a(new gv(this.e, this.c, hrVar, this.i), listC) : new a(new hf(), listC);
        }
        return new a(new he(this.e, this.c, huVar, this.i), listC);
    }

    @Override // com.tapjoy.internal.ca
    public final String c() {
        return "placement";
    }

    @Override // com.tapjoy.internal.ii, com.tapjoy.internal.ca
    public final Map e() {
        Map mapE = super.e();
        mapE.put(TJAdUnitConstants.String.VIDEO_INFO, new bm(hp.a(this.f)));
        mapE.put("app", new bm(hp.a(this.g)));
        mapE.put("user", new bm(hp.a(this.h)));
        mapE.put("placement", this.c);
        return mapE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.ii, com.tapjoy.internal.ca
    public final /* synthetic */ Object f() {
        a aVar = (a) super.f();
        if (!(aVar.a instanceof hf)) {
            aVar.a.b();
            if (!aVar.a.c()) {
                new Object[1][0] = this.c;
                aVar.a = new hf();
            }
        }
        return aVar;
    }
}
