package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Map;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hr extends hq {
    public static final bi d = new bi() { // from class: com.tapjoy.internal.hr.1
        @Override // com.tapjoy.internal.bi
        public final /* synthetic */ Object a(bn bnVar) {
            return new hr(bnVar);
        }
    };
    public ArrayList a = new ArrayList();
    public Map b;
    public float c;

    public hr(bn bnVar) {
        bnVar.h();
        String strB = null;
        String strB2 = null;
        while (bnVar.j()) {
            String strL = bnVar.l();
            if ("layouts".equals(strL)) {
                bnVar.a(this.a, ia.d);
            } else if (NXPBaseFcmMessagingService.KEY_META.equals(strL)) {
                this.b = bnVar.d();
            } else if ("max_show_time".equals(strL)) {
                this.c = (float) bnVar.p();
            } else if ("ad_content".equals(strL)) {
                strB2 = bnVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(strL)) {
                strB = bnVar.b();
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
        if (this.a != null) {
            for (ia iaVar : this.a) {
                if (iaVar.c != null) {
                    for (hz hzVar : iaVar.c) {
                        if (hzVar.i == null) {
                            hzVar.i = strB2;
                        }
                        if (hzVar.h == null) {
                            hzVar.h = strB;
                        }
                    }
                }
            }
        }
    }
}
