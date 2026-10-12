package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class bz extends ca {
    public abstract Object a(bn bnVar);

    @Override // com.tapjoy.internal.ca
    public final Object a(URI uri, InputStream inputStream) {
        bn bnVarA = bn.a(inputStream);
        bnVarA.a("BASE_URI", uri);
        int iR = 0;
        try {
            bnVarA.h();
            Object objA = null;
            String strM = null;
            while (bnVarA.j()) {
                String strL = bnVarA.l();
                if ("status".equals(strL)) {
                    iR = bnVarA.r();
                } else if ("message".equals(strL)) {
                    strM = bnVarA.m();
                } else if ("data".equals(strL)) {
                    objA = a(bnVarA);
                } else {
                    bnVarA.s();
                }
            }
            bnVarA.i();
            if (iR != 200) {
                throw new cb(iR, strM);
            }
            bnVarA.close();
            return objA;
        } catch (Throwable th) {
            bnVarA.close();
            throw th;
        }
    }

    @Override // com.tapjoy.internal.ca
    public final Map a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Accept", "application/json");
        return linkedHashMap;
    }
}
