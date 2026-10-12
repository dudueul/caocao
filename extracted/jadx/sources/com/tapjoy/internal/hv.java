package com.tapjoy.internal;

import android.graphics.Point;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TJAdUnitConstants;
import java.net.URL;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hv {
    public static final bi d = new bi() { // from class: com.tapjoy.internal.hv.1
        private static Point b(bn bnVar) {
            Point point = null;
            bnVar.h();
            while (bnVar.j()) {
                if ("offset".equals(bnVar.l())) {
                    bnVar.h();
                    int iR = 0;
                    int iR2 = 0;
                    while (bnVar.j()) {
                        String strL = bnVar.l();
                        if ("x".equals(strL)) {
                            iR2 = bnVar.r();
                        } else if ("y".equals(strL)) {
                            iR = bnVar.r();
                        } else {
                            bnVar.s();
                        }
                    }
                    bnVar.i();
                    point = new Point(iR2, iR);
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return point;
        }

        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            bnVar.h();
            hx hxVar = null;
            Point pointB = null;
            Point pointB2 = null;
            while (bnVar.j()) {
                String strL = bnVar.l();
                if (MessengerShareContentUtility.MEDIA_IMAGE.equals(strL)) {
                    String strM = bnVar.m();
                    if (!jq.c(strM)) {
                        hxVar = new hx(new URL(strM));
                    }
                } else if (TJAdUnitConstants.String.LANDSCAPE.equals(strL)) {
                    pointB2 = b(bnVar);
                } else if (TJAdUnitConstants.String.PORTRAIT.equals(strL)) {
                    pointB = b(bnVar);
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return new hv(hxVar, pointB2, pointB);
        }
    };
    public final hx a;
    public final Point b;
    public final Point c;

    public hv(hx hxVar, Point point, Point point2) {
        this.a = hxVar;
        this.b = point;
        this.c = point2;
    }
}
