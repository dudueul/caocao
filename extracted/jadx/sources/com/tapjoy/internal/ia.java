package com.tapjoy.internal;

import android.graphics.PointF;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ia {
    public static final bi d = new bi() { // from class: com.tapjoy.internal.ia.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            return new ia(bnVar);
        }
    };
    public aa a;
    public PointF b;
    public ArrayList c = new ArrayList();

    public ia(bn bnVar) {
        this.a = aa.UNSPECIFIED;
        bnVar.h();
        while (bnVar.j()) {
            String strL = bnVar.l();
            if ("buttons".equals(strL)) {
                if (bnVar.k() == bs.BEGIN_ARRAY) {
                    bnVar.a(this.c, hz.n);
                } else {
                    bnVar.s();
                }
            } else if ("window_aspect_ratio".equals(strL)) {
                if (bnVar.a()) {
                    PointF pointF = new PointF();
                    bnVar.h();
                    while (bnVar.j()) {
                        String strL2 = bnVar.l();
                        if ("width".equals(strL2)) {
                            pointF.x = (float) bnVar.p();
                        } else if ("height".equals(strL2)) {
                            pointF.y = (float) bnVar.p();
                        } else {
                            bnVar.s();
                        }
                    }
                    bnVar.i();
                    if (pointF.x != 0.0f && pointF.y != 0.0f) {
                        this.b = pointF;
                    }
                } else {
                    bnVar.s();
                }
            } else if (TJAdUnitConstants.String.ORIENTATION.equals(strL)) {
                String strM = bnVar.m();
                if (TJAdUnitConstants.String.LANDSCAPE.equals(strM)) {
                    this.a = aa.LANDSCAPE;
                } else if (TJAdUnitConstants.String.PORTRAIT.equals(strM)) {
                    this.a = aa.PORTRAIT;
                }
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
    }
}
