package com.tapjoy.internal;

import java.util.HashMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@ft
public final class PluginSupport {
    private PluginSupport() {
    }

    @ft
    public static void trackUsage(String str, String str2, String str3) {
        TreeMap treeMap;
        HashMap map = null;
        try {
            if (al.a(str2)) {
                treeMap = null;
            } else {
                treeMap = new TreeMap();
                bn bnVarB = bn.b(str2);
                try {
                    bnVarB.a(treeMap);
                    bnVarB.close();
                } catch (Throwable th) {
                    bnVarB.close();
                    throw th;
                }
            }
            if (!al.a(str3)) {
                map = new HashMap();
                bn bnVarB2 = bn.b(str3);
                try {
                    bnVarB2.h();
                    while (bnVarB2.j()) {
                        map.put(bnVarB2.l(), Long.valueOf(bnVarB2.q()));
                    }
                    bnVarB2.i();
                    bnVarB2.close();
                } catch (Throwable th2) {
                    bnVarB2.close();
                    throw th2;
                }
            }
            gf.a(str, treeMap, map);
        } catch (Exception e) {
        }
    }
}
