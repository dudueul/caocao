package com.tapjoy.internal;

import com.tapjoy.TapjoyLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class fq {
    private static final String c = fq.class.getSimpleName();
    public final Map a = new HashMap();
    public final Map b = new HashMap();

    protected fq(String str, String str2, String str3) {
        this.a.put("placement", str);
        this.a.put("placement_type", str2);
        this.a.put("content_type", str3);
    }

    public final gf.a a() {
        return a("Content.rendered", null, null);
    }

    protected final gf.a a(String str, Map map, Map map2) {
        gf.a aVarB = gf.e(str).a().a(this.a).a(map).b(map2);
        this.b.put(str, aVarB);
        return aVarB;
    }

    public final void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    public final gf.a b() {
        return b("Content.rendered", null, null);
    }

    protected final gf.a b(String str, Map map, Map map2) {
        gf.a aVar = !al.a(str) ? (gf.a) this.b.remove(str) : null;
        if (aVar == null) {
            TapjoyLog.e(c, "Error when calling endTrackingEvent -- " + str + " tracking has not been started.");
        } else {
            aVar.a(this.a).a(map).b(map2).b().c();
        }
        return aVar;
    }
}
