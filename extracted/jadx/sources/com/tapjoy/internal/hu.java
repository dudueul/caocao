package com.tapjoy.internal;

import android.graphics.Point;
import android.os.SystemClock;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Nullable;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hu extends hq {
    public static final bi n = new bi() { // from class: com.tapjoy.internal.hu.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            return new hu(bnVar);
        }
    };

    @Nullable
    public hx a;

    @Nullable
    public hx b;
    public hx c;

    @Nullable
    public Point d;

    @Nullable
    public hx e;

    @Nullable
    public hx f;
    public String g;

    @Nullable
    public gl h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public Map k;
    public long l;

    @Nullable
    public hv m;

    public hu() {
    }

    hu(bn bnVar) {
        bnVar.h();
        String strB = null;
        String strB2 = null;
        while (bnVar.j()) {
            String strL = bnVar.l();
            if ("frame".equals(strL)) {
                bnVar.h();
                while (bnVar.j()) {
                    String strL2 = bnVar.l();
                    if (TJAdUnitConstants.String.PORTRAIT.equals(strL2)) {
                        this.a = (hx) hx.e.a(bnVar);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(strL2)) {
                        this.b = (hx) hx.e.a(bnVar);
                    } else if ("close_button".equals(strL2)) {
                        this.c = (hx) hx.e.a(bnVar);
                    } else if ("close_button_offset".equals(strL2)) {
                        this.d = (Point) bj.a.a(bnVar);
                    } else {
                        bnVar.s();
                    }
                }
                bnVar.i();
            } else if ("creative".equals(strL)) {
                bnVar.h();
                while (bnVar.j()) {
                    String strL3 = bnVar.l();
                    if (TJAdUnitConstants.String.PORTRAIT.equals(strL3)) {
                        this.e = (hx) hx.e.a(bnVar);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(strL3)) {
                        this.f = (hx) hx.e.a(bnVar);
                    } else {
                        bnVar.s();
                    }
                }
                bnVar.i();
            } else if ("url".equals(strL)) {
                this.g = bnVar.b();
            } else if (ho.a(strL)) {
                this.h = ho.a(strL, bnVar);
            } else if ("mappings".equals(strL)) {
                bnVar.h();
                while (bnVar.j()) {
                    String strL4 = bnVar.l();
                    if (TJAdUnitConstants.String.PORTRAIT.equals(strL4)) {
                        bnVar.a(this.i, hs.h);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(strL4)) {
                        bnVar.a(this.j, hs.h);
                    } else {
                        bnVar.s();
                    }
                }
                bnVar.i();
            } else if (NXPBaseFcmMessagingService.KEY_META.equals(strL)) {
                this.k = bnVar.d();
            } else if ("ttl".equals(strL)) {
                this.l = ((long) (bnVar.p() * 1000.0d)) + SystemClock.elapsedRealtime();
            } else if ("no_more_today".equals(strL)) {
                this.m = (hv) hv.d.a(bnVar);
            } else if ("ad_content".equals(strL)) {
                strB = bnVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(strL)) {
                strB2 = bnVar.b();
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
        if (this.g == null) {
            this.g = "";
        }
        if (this.i != null) {
            for (hs hsVar : this.i) {
                if (hsVar.f == null) {
                    hsVar.f = strB;
                }
                if (hsVar.e == null) {
                    hsVar.e = strB2;
                }
            }
        }
        if (this.j != null) {
            for (hs hsVar2 : this.j) {
                if (hsVar2.f == null) {
                    hsVar2.f = strB;
                }
                if (hsVar2.e == null) {
                    hsVar2.e = strB2;
                }
            }
        }
    }

    public final boolean a() {
        return (this.c == null || this.a == null || this.e == null) ? false : true;
    }

    public final boolean b() {
        return (this.c == null || this.b == null || this.f == null) ? false : true;
    }
}
