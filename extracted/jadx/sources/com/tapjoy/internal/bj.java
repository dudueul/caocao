package com.tapjoy.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.tapjoy.TJAdUnitConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bj {
    public static final bi a = new bi() { // from class: com.tapjoy.internal.bj.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            Point point = new Point();
            bnVar.h();
            while (bnVar.j()) {
                String strL = bnVar.l();
                if ("x".equals(strL)) {
                    point.x = bnVar.r();
                } else if ("y".equals(strL)) {
                    point.y = bnVar.r();
                } else {
                    bnVar.s();
                }
            }
            bnVar.i();
            return point;
        }
    };
    public static final bi b = new bi() { // from class: com.tapjoy.internal.bj.2
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            Rect rect = new Rect();
            switch (AnonymousClass3.a[bnVar.k().ordinal()]) {
                case 1:
                    bnVar.f();
                    rect.left = bnVar.r();
                    rect.top = bnVar.r();
                    rect.right = bnVar.r();
                    rect.bottom = bnVar.r();
                    while (bnVar.j()) {
                        bnVar.s();
                    }
                    bnVar.g();
                    return rect;
                case 2:
                    bnVar.h();
                    while (bnVar.j()) {
                        String strL = bnVar.l();
                        if (TJAdUnitConstants.String.LEFT.equals(strL)) {
                            rect.left = bnVar.r();
                        } else if (TJAdUnitConstants.String.TOP.equals(strL)) {
                            rect.top = bnVar.r();
                        } else if (TJAdUnitConstants.String.RIGHT.equals(strL)) {
                            rect.right = bnVar.r();
                        } else if (TJAdUnitConstants.String.BOTTOM.equals(strL)) {
                            rect.bottom = bnVar.r();
                        } else {
                            bnVar.s();
                        }
                    }
                    bnVar.i();
                    return rect;
                default:
                    throw new IllegalStateException("Unexpected token: " + bnVar.k());
            }
        }
    };

    /* JADX INFO: renamed from: com.tapjoy.internal.bj$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[bs.values().length];

        static {
            try {
                a[bs.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bs.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
