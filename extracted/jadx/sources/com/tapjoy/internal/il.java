package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class il extends ii {
    private final fa c;
    private final eu d;
    private final fh e;
    private final String f;

    private il(fa faVar, eu euVar, fh fhVar, String str) {
        this.c = faVar;
        this.d = euVar;
        this.e = fhVar;
        this.f = str;
    }

    public il(fb fbVar, String str) {
        this(fbVar.d, fbVar.e, fbVar.f, str);
    }

    @Override // com.tapjoy.internal.ca
    public final String c() {
        return "api/v1/tokens";
    }

    @Override // com.tapjoy.internal.ii, com.tapjoy.internal.ca
    public final Map e() {
        Map mapE = super.e();
        mapE.put(TJAdUnitConstants.String.VIDEO_INFO, new bm(hp.a(this.c)));
        mapE.put("app", new bm(hp.a(this.d)));
        mapE.put("user", new bm(hp.a(this.e)));
        if (!al.a(this.f)) {
            mapE.put("push_token", this.f);
        }
        return mapE;
    }
}
