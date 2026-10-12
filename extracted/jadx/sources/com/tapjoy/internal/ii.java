package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ii extends bz {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.bz
    @Nullable
    public Object a(bn bnVar) {
        bnVar.s();
        return null;
    }

    @Override // com.tapjoy.internal.ca
    public final String b() {
        return "POST";
    }

    @Override // com.tapjoy.internal.ca
    public final String d() {
        return "application/json";
    }

    @Override // com.tapjoy.internal.ca
    public Map e() {
        Map mapE = super.e();
        gz gzVarA = gz.a();
        mapE.put("sdk_ver", gzVarA.m + "/Android");
        mapE.put(TapjoyConstants.TJC_API_KEY, gzVarA.l);
        if (gw.a) {
            mapE.put("debug", true);
        }
        return mapE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.ca
    public Object f() throws Exception {
        try {
            return super.f();
        } catch (Exception e) {
            new Object[1][0] = this;
            throw e;
        }
    }
}
