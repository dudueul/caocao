package com.tapjoy.internal;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hz {
    public static final bi n = new bi() { // from class: com.tapjoy.internal.hz.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            return new hz(bnVar);
        }
    };
    public ib a;
    public ib b;
    public ib c;
    public ib d;
    public int e;
    public int f;
    public String g;
    public String h;
    public String i;
    public boolean j;
    public String k;
    public hx l;
    public hx m;

    public hz(bn bnVar) {
        this.e = 9;
        this.f = 10;
        this.j = false;
        bnVar.h();
        while (bnVar.j()) {
            String strL = bnVar.l();
            if ("x".equals(strL)) {
                this.a = ib.a(bnVar.m());
            } else if ("y".equals(strL)) {
                this.b = ib.a(bnVar.m());
            } else if ("width".equals(strL)) {
                this.c = ib.a(bnVar.m());
            } else if ("height".equals(strL)) {
                this.d = ib.a(bnVar.m());
            } else if ("url".equals(strL)) {
                this.g = bnVar.m();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(strL)) {
                this.h = bnVar.m();
            } else if ("ad_content".equals(strL)) {
                this.i = bnVar.m();
            } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(strL)) {
                this.j = bnVar.n();
            } else if ("value".equals(strL)) {
                this.k = bnVar.m();
            } else if (MessengerShareContentUtility.MEDIA_IMAGE.equals(strL)) {
                this.l = (hx) hx.e.a(bnVar);
            } else if ("image_clicked".equals(strL)) {
                this.m = (hx) hx.e.a(bnVar);
            } else if ("align".equals(strL)) {
                String strM = bnVar.m();
                if (TJAdUnitConstants.String.LEFT.equals(strM)) {
                    this.e = 9;
                } else if (TJAdUnitConstants.String.RIGHT.equals(strM)) {
                    this.e = 11;
                } else if ("center".equals(strM)) {
                    this.e = 14;
                } else {
                    bnVar.s();
                }
            } else if ("valign".equals(strL)) {
                String strM2 = bnVar.m();
                if (TJAdUnitConstants.String.TOP.equals(strM2)) {
                    this.f = 10;
                } else if ("middle".equals(strM2)) {
                    this.f = 15;
                } else if (TJAdUnitConstants.String.BOTTOM.equals(strM2)) {
                    this.f = 12;
                } else {
                    bnVar.s();
                }
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
    }
}
