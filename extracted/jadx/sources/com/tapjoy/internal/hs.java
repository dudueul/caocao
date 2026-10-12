package com.tapjoy.internal;

import android.graphics.Rect;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hs {
    public static final bi h = new bi() { // from class: com.tapjoy.internal.hs.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            boolean zN = false;
            String strM = "";
            bnVar.h();
            ho hoVarA = null;
            String strB = null;
            String strB2 = null;
            String strM2 = null;
            Rect rect = null;
            while (bnVar.j()) {
                String strL = bnVar.l();
                if ("region".equals(strL)) {
                    rect = (Rect) bj.b.a(bnVar);
                } else if ("value".equals(strL)) {
                    strM2 = bnVar.m();
                } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(strL)) {
                    zN = bnVar.n();
                } else if ("url".equals(strL)) {
                    strM = bnVar.m();
                } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(strL)) {
                    strB2 = bnVar.b();
                } else if ("ad_content".equals(strL)) {
                    strB = bnVar.b();
                } else if (ho.a(strL)) {
                    hoVarA = ho.a(strL, bnVar);
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return new hs(rect, strM2, zN, strM, strB2, strB, hoVarA);
        }
    };
    public final Rect a;
    public final String b;
    public final boolean c;
    public final String d;
    public String e;
    public String f;
    public final gl g;

    hs(Rect rect, String str, boolean z, String str2, String str3, String str4, gl glVar) {
        this.a = rect;
        this.b = str;
        this.c = z;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = glVar;
    }
}
