package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class hy extends ho implements gq {
    public static final bi a = new bi() { // from class: com.tapjoy.internal.hy.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            int iR = 1;
            bnVar.h();
            String strM = null;
            String strM2 = null;
            String strM3 = null;
            while (bnVar.j()) {
                String strL = bnVar.l();
                if ("id".equals(strL)) {
                    strM3 = bnVar.m();
                } else if ("name".equals(strL)) {
                    strM2 = bnVar.m();
                } else if ("quantity".equals(strL)) {
                    iR = bnVar.r();
                } else if ("token".equals(strL)) {
                    strM = bnVar.m();
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return new hy(strM3, strM2, iR, strM);
        }
    };
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    hy(String str, String str2, int i, String str3) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    @Override // com.tapjoy.internal.gq
    public final String a() {
        return this.b;
    }

    @Override // com.tapjoy.internal.gq
    public final String b() {
        return this.c;
    }

    @Override // com.tapjoy.internal.gq
    public final int c() {
        return this.d;
    }

    @Override // com.tapjoy.internal.gq
    public final String d() {
        return this.e;
    }
}
