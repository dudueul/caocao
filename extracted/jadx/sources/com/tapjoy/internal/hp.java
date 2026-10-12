package com.tapjoy.internal;

import com.facebook.AccessToken;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hp {
    public static String a(eu euVar) {
        bh bhVarC = new bh().c();
        if (euVar.e != null) {
            bhVarC.a(TapjoyConstants.TJC_PACKAGE_VERSION).b(euVar.e);
        }
        if (euVar.f != null) {
            bhVarC.a(TapjoyConstants.TJC_PACKAGE_REVISION).a((Number) euVar.f);
        }
        if (euVar.g != null) {
            bhVarC.a("data_ver").b(euVar.g);
        }
        if (euVar.h != null) {
            bhVarC.a(TapjoyConstants.TJC_INSTALLER).b(euVar.h);
        }
        if (euVar.i != null) {
            bhVarC.a(TapjoyConstants.TJC_STORE).b(euVar.i);
        }
        return bhVarC.d().toString();
    }

    private static String a(ev evVar, boolean z, boolean z2, boolean z3) {
        bh bhVarB = new bh().c().a("type").b(a(evVar.n)).a("name").b(evVar.o);
        bhVarB.a("time");
        if (evVar.q != null) {
            bhVarB.a((Number) evVar.p);
            bhVarB.a("systime").a((Number) evVar.q);
        } else if (!v.c() || evVar.r == null || evVar.s == null || !hc.a.equals(evVar.r)) {
            bhVarB.a((Number) evVar.p);
        } else {
            bhVarB.a(v.a(evVar.s.longValue()));
            bhVarB.a("systime").a((Number) evVar.p);
        }
        if (evVar.t != null) {
            bhVarB.a("duration").a((Number) evVar.t);
        }
        if (!z && evVar.u != null) {
            bhVarB.a(TJAdUnitConstants.String.VIDEO_INFO).a((bl) new bm(a(evVar.u)));
        }
        if (!z2 && evVar.v != null) {
            bhVarB.a("app").a((bl) new bm(a(evVar.v)));
        }
        if (!z3 && evVar.w != null) {
            bhVarB.a("user").a((bl) new bm(a(evVar.w, evVar)));
        }
        if (evVar.y != null) {
            bhVarB.a("event_seq").a((Number) evVar.y);
        }
        if (evVar.z != null) {
            bh bhVarA = bhVarB.a("event_prev");
            ex exVar = evVar.z;
            bh bhVarB2 = new bh().c().a("type").b(a(exVar.e)).a("name").b(exVar.f);
            if (exVar.g != null) {
                bhVarB2.a("category").b(exVar.g);
            }
            bhVarA.a((bl) new bm(bhVarB2.d().toString()));
        }
        if (evVar.A != null) {
            bh bhVarA2 = bhVarB.a("purchase");
            fd fdVar = evVar.A;
            bh bhVarB3 = new bh().c().a("product_id").b(fdVar.h);
            if (fdVar.i != null) {
                bhVarB3.a("product_quantity").a((Number) fdVar.i);
            }
            if (fdVar.j != null) {
                bhVarB3.a("product_price").a((Number) fdVar.j);
            }
            if (fdVar.k != null) {
                bhVarB3.a("product_price_currency").b(fdVar.k);
            }
            if (fdVar.s != null) {
                bhVarB3.a("currency_price").b(fdVar.s);
            }
            if (fdVar.l != null) {
                bhVarB3.a("product_type").b(fdVar.l);
            }
            if (fdVar.m != null) {
                bhVarB3.a("product_title").b(fdVar.m);
            }
            if (fdVar.n != null) {
                bhVarB3.a("product_description").b(fdVar.n);
            }
            if (fdVar.o != null) {
                bhVarB3.a("transaction_id").b(fdVar.o);
            }
            if (fdVar.p != null) {
                bhVarB3.a("transaction_state").a((Number) fdVar.p);
            }
            if (fdVar.q != null) {
                bhVarB3.a("transaction_date").a((Number) fdVar.q);
            }
            if (fdVar.r != null) {
                bhVarB3.a("campaign_id").b(fdVar.r);
            }
            if (fdVar.t != null) {
                bhVarB3.a("receipt").b(fdVar.t);
            }
            if (fdVar.u != null) {
                bhVarB3.a("signature").b(fdVar.u);
            }
            bhVarA2.a((bl) new bm(bhVarB3.d().toString()));
        }
        if (evVar.B != null) {
            bhVarB.a("exception").b(evVar.B);
        }
        try {
            if (evVar.D != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (evVar.C != null) {
                    bn.b(evVar.C).a(linkedHashMap);
                }
                fc fcVar = evVar.D;
                if (fcVar.d != null) {
                    linkedHashMap.put("fq7_change", fcVar.d);
                }
                if (fcVar.e != null) {
                    linkedHashMap.put("fq30_change", fcVar.e);
                }
                if (fcVar.f != null) {
                    linkedHashMap.put(TJAdUnitConstants.PARAM_PUSH_ID, fcVar.f);
                }
                bhVarB.a(NXPBaseFcmMessagingService.KEY_META).a((Map) linkedHashMap);
            } else if (evVar.C != null) {
                bhVarB.a(NXPBaseFcmMessagingService.KEY_META).a((bl) new bm(evVar.C));
            }
        } catch (IOException e) {
        }
        if (evVar.I != null) {
            bhVarB.a(TJAdUnitConstants.String.USAGE_TRACKER_DIMENSIONS).a((bl) new bm(evVar.I));
        }
        if (evVar.J != null) {
            bhVarB.a(NxLogSummaryInfos.KEY_COUNT).a((Number) evVar.J);
        }
        if (evVar.K != null) {
            bhVarB.a("first_time").a((Number) evVar.K);
        }
        if (evVar.L != null) {
            bhVarB.a("last_time").a((Number) evVar.L);
        }
        if (evVar.E != null) {
            bhVarB.a("category").b(evVar.E);
        }
        if (evVar.F != null) {
            bhVarB.a("p1").b(evVar.F);
        }
        if (evVar.G != null) {
            bhVarB.a("p2").b(evVar.G);
        }
        if (evVar.H.size() > 0) {
            bhVarB.a(TJAdUnitConstants.String.USAGE_TRACKER_VALUES).c();
            for (ez ezVar : evVar.H) {
                bhVarB.a(ezVar.e).a((Number) ezVar.f);
            }
            bhVarB.d();
        }
        return bhVarB.d().toString();
    }

    public static String a(ew ewVar) {
        boolean z;
        eu euVar;
        boolean z2;
        boolean z3;
        bh bhVarA = new bh().a();
        fh fhVar = null;
        eu euVar2 = null;
        fa faVar = null;
        for (ev evVar : ewVar.d) {
            if (faVar == null || !faVar.equals(evVar.u)) {
                faVar = evVar.u;
                z = false;
            } else {
                z = true;
            }
            if (euVar2 == null || !euVar2.equals(evVar.v)) {
                euVar = evVar.v;
                z2 = false;
            } else {
                z2 = true;
                euVar = euVar2;
            }
            if (fhVar == null || !fhVar.equals(evVar.w)) {
                fhVar = evVar.w;
                z3 = false;
            } else {
                z3 = true;
            }
            bhVarA.a((bl) new bm(a(evVar, z, z2, z3)));
            euVar2 = euVar;
        }
        return bhVarA.b().toString();
    }

    private static String a(ey eyVar) {
        switch (eyVar) {
            case APP:
                return "app";
            case CAMPAIGN:
                return "campaign";
            case CUSTOM:
                return "custom";
            case USAGES:
                return "usages";
            default:
                throw new RuntimeException();
        }
    }

    public static String a(fa faVar) {
        bh bhVarB = new bh().c().a("sdk").b(faVar.t).a(NxSystemInfo.KEY_OS_NAME).b(faVar.k).a("os_ver").b(faVar.l).a("device_id").b(faVar.h).a("device_maker").b(faVar.i).a("device_model").b(faVar.j).a(TapjoyConstants.TJC_PACKAGE_ID).b(faVar.r).a(TapjoyConstants.TJC_PACKAGE_SIGN).b(faVar.s).a("locale").b(faVar.p).a(TapjoyConstants.TJC_DEVICE_TIMEZONE).b(faVar.q);
        if (faVar.m != null) {
            bhVarB.a(TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY).a((Number) faVar.m);
        }
        if (faVar.n != null) {
            bhVarB.a(TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH).a((Number) faVar.n);
        }
        if (faVar.o != null) {
            bhVarB.a(TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT).a((Number) faVar.o);
        }
        if (faVar.g != null) {
            bhVarB.a("mac").b(faVar.g);
        }
        if (faVar.u != null) {
            bhVarB.a(TapjoyConstants.TJC_DEVICE_COUNTRY_SIM).b(faVar.u);
        }
        if (faVar.v != null) {
            bhVarB.a("country_net").b(faVar.v);
        }
        if (faVar.w != null) {
            bhVarB.a("imei").b(faVar.w);
        }
        if (faVar.x != null) {
            bhVarB.a(TapjoyConstants.TJC_ANDROID_ID).b(faVar.x);
        }
        return bhVarB.d().toString();
    }

    public static String a(fh fhVar) {
        return a(fhVar, null);
    }

    private static String a(fh fhVar, ev evVar) {
        String strB;
        bh bhVarC = new bh().c();
        if (fhVar.s != null) {
            bhVarC.a(TapjoyConstants.TJC_INSTALLED).a((Number) fhVar.s);
        }
        if (fhVar.t != null) {
            bhVarC.a("referrer").b(fhVar.t);
        }
        if (fhVar.G != null) {
            bhVarC.a("idfa").b(fhVar.G);
            if (fhVar.H != null && fhVar.H.booleanValue()) {
                bhVarC.a("idfa_optout").a(1L);
            }
        } else if (evVar != null && evVar.r != null && hc.a.equals(evVar.r) && (strB = hn.b()) != null) {
            bhVarC.a("idfa").b(strB);
            if (hn.c()) {
                bhVarC.a("idfa_optout").a(1L);
            }
        }
        if (fhVar.u != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY).a(Math.max(fhVar.u.intValue(), 1));
        }
        if (fhVar.v != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY).a(Math.max(fhVar.v.intValue(), 1));
        }
        if (fhVar.w.size() > 0) {
            ArrayList arrayList = new ArrayList(fhVar.w.size());
            for (fe feVar : fhVar.w) {
                if (feVar.h != null) {
                    arrayList.add(feVar.f);
                }
            }
            if (!arrayList.isEmpty()) {
                bhVarC.a("push").a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    bhVarC.b((String) it.next());
                }
                bhVarC.b();
            }
        }
        bhVarC.a("session").c();
        if (fhVar.x != null) {
            bhVarC.a("total_count").a((Number) fhVar.x);
        }
        if (fhVar.y != null) {
            bhVarC.a("total_length").a((Number) fhVar.y);
        }
        if (fhVar.z != null) {
            bhVarC.a("last_at").a((Number) fhVar.z);
        }
        if (fhVar.A != null) {
            bhVarC.a("last_length").a((Number) fhVar.A);
        }
        bhVarC.d();
        bhVarC.a("purchase").c();
        if (fhVar.B != null) {
            bhVarC.a("currency").b(fhVar.B);
        }
        if (fhVar.C != null) {
            bhVarC.a("total_count").a((Number) fhVar.C);
        }
        if (fhVar.D != null) {
            bhVarC.a("total_price").a((Number) fhVar.D);
        }
        if (fhVar.E != null) {
            bhVarC.a("last_at").a((Number) fhVar.E);
        }
        if (fhVar.F != null) {
            bhVarC.a("last_price").a((Number) fhVar.F);
        }
        bhVarC.d();
        if (fhVar.I != null) {
            bhVarC.a(AccessToken.USER_ID_KEY).b(fhVar.I);
        }
        if (fhVar.J != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_LEVEL).a((Number) fhVar.J);
        }
        if (fhVar.K != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_FRIEND_COUNT).a((Number) fhVar.K);
        }
        if (fhVar.L != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_VARIABLE_1).b(fhVar.L);
        }
        if (fhVar.M != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_VARIABLE_2).b(fhVar.M);
        }
        if (fhVar.N != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_VARIABLE_3).b(fhVar.N);
        }
        if (fhVar.O != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_VARIABLE_4).b(fhVar.O);
        }
        if (fhVar.P != null) {
            bhVarC.a(TapjoyConstants.TJC_USER_VARIABLE_5).b(fhVar.P);
        }
        if (fhVar.Q.size() > 0) {
            bhVarC.a("tags").a((Collection) fhVar.Q);
        }
        if (Boolean.TRUE.equals(fhVar.R)) {
            bhVarC.a("push_optout").a(1L);
        }
        return bhVarC.d().toString();
    }
}
