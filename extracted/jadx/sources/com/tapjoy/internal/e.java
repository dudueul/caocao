package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class e {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;

    public e(String str) {
        bn bnVarB = bn.b(str);
        bnVarB.h();
        while (bnVarB.j()) {
            String strL = bnVarB.l();
            if ("productId".equals(strL)) {
                this.a = bnVarB.m();
            } else if ("type".equals(strL)) {
                this.b = bnVarB.m();
            } else if ("price".equals(strL)) {
                this.c = bnVarB.m();
            } else if ("title".equals(strL)) {
                this.d = bnVarB.m();
            } else if ("description".equals(strL)) {
                this.e = bnVarB.m();
            } else if ("price_currency_code".equals(strL)) {
                this.f = bnVarB.m();
            } else if ("price_amount_micros".equals(strL)) {
                this.g = bnVarB.q();
            } else {
                bnVarB.s();
            }
        }
        bnVarB.i();
    }
}
