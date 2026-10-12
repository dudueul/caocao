package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class hw extends ho implements gp {
    public static final bi a = new bi() { // from class: com.tapjoy.internal.hw.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            String strC = "";
            String strC2 = "";
            bnVar.h();
            while (bnVar.j()) {
                String strL = bnVar.l();
                if ("campaign_id".equals(strL)) {
                    strC = bnVar.c("");
                } else if ("product_id".equals(strL)) {
                    strC2 = bnVar.c("");
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return new hw(strC, strC2);
        }
    };
    private final String b;
    private final String c;

    hw(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    @Override // com.tapjoy.internal.gp
    public final String a() {
        return this.b;
    }

    @Override // com.tapjoy.internal.gp
    public final String b() {
        return this.c;
    }
}
