package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class f {
    public String a;
    public String b;
    public String c;
    public long d;
    public int e;
    public String f;
    public String g;

    public f(String str) {
        bn bnVarB = bn.b(str);
        bnVarB.h();
        while (bnVarB.j()) {
            String strL = bnVarB.l();
            if ("orderId".equals(strL)) {
                this.a = bnVarB.m();
            } else if ("packageName".equals(strL)) {
                this.b = bnVarB.m();
            } else if ("productId".equals(strL)) {
                this.c = bnVarB.m();
            } else if ("purchaseTime".equals(strL)) {
                this.d = bnVarB.q();
            } else if ("purchaseState".equals(strL)) {
                this.e = bnVarB.r();
            } else if ("developerPayload".equals(strL)) {
                this.f = bnVarB.m();
            } else if ("purchaseToken".equals(strL)) {
                this.g = bnVarB.m();
            } else {
                bnVarB.s();
            }
        }
        bnVarB.i();
    }
}
