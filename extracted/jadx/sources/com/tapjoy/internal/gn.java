package com.tapjoy.internal;

import android.app.Activity;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class gn {
    public static void a() {
        gz gzVarA = gz.a();
        if (gzVarA.c("startSession") && gzVarA.e()) {
            gt.b(null);
        }
    }

    public static void a(Activity activity) {
        gz gzVarA = gz.a();
        if (gw.a(activity, "onActivityStart: The given activity was null")) {
            gw.c("onActivityStart");
            b.a(activity.getApplication());
            b.b(activity);
            if (gzVarA.c("onActivityStart") && gzVarA.e()) {
                gt.b(activity);
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        gz gzVarA = gz.a();
        if (gzVarA.c("trackPurchase")) {
            try {
                e eVar = new e(str);
                String strB = gu.b(eVar.a);
                String strB2 = gu.b(eVar.f);
                if (strB == null || strB2 == null) {
                    gw.a("trackPurchase", "skuDetails", "insufficient fields");
                    return;
                }
                if (strB2.length() != 3) {
                    gw.a("trackPurchase", "skuDetails", "invalid currency code");
                    return;
                }
                String strB3 = gu.b(str2);
                String strB4 = gu.b(str3);
                if (strB3 != null) {
                    if (strB4 != null) {
                        try {
                            f fVar = new f(strB3);
                            if (jq.c(fVar.a) || jq.c(fVar.b) || jq.c(fVar.c) || fVar.d == 0) {
                                gw.a("trackPurchase", "purchaseData", "insufficient fields");
                            }
                        } catch (IOException e) {
                            gw.a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                        }
                    } else {
                        gw.a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
                    }
                } else if (strB4 != null) {
                    gw.a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                }
                gzVarA.g.a(strB, strB2.toUpperCase(Locale.US), eVar.g / 1000000.0d, strB3, strB4, gu.b(str4));
                if (strB3 == null || strB4 == null) {
                    gw.a("trackPurchase without purchaseData called");
                } else {
                    gw.a("trackPurchase with purchaseData called");
                }
            } catch (IOException e2) {
                gw.a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j) {
        gz gzVarA = gz.a();
        if (gzVarA.d("trackEvent") && gw.a(str2, "trackEvent: name was null")) {
            LinkedHashMap linkedHashMapB = null;
            if (j != 0) {
                linkedHashMapB = ju.b();
                linkedHashMapB.put("value", Long.valueOf(j));
            }
            gzVarA.g.a(str, str2, str3, str4, linkedHashMapB);
            gw.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, linkedHashMapB);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        gz gzVarA = gz.a();
        if (gzVarA.d("trackEvent") && gw.a(str2, "trackEvent: name was null")) {
            LinkedHashMap linkedHashMapB = ju.b();
            if (str5 != null && j != 0) {
                linkedHashMapB.put(str5, Long.valueOf(j));
            }
            if (str6 != null && j2 != 0) {
                linkedHashMapB.put(str6, Long.valueOf(j2));
            }
            if (str7 != null && j3 != 0) {
                linkedHashMapB.put(str7, Long.valueOf(j3));
            }
            if (linkedHashMapB.isEmpty()) {
                linkedHashMapB = null;
            }
            gzVarA.g.a(str, str2, str3, str4, linkedHashMapB);
            gw.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, linkedHashMapB);
        }
    }

    public static void b() {
        gz gzVarA = gz.a();
        if (gzVarA.c("endSession")) {
            gzVarA.h.a();
        }
    }

    public static void b(Activity activity) {
        gz gzVarA = gz.a();
        if (gw.a(activity, "onActivityStop: The given activity was null")) {
            gw.c("onActivityStop");
            b.c(activity);
            if (!gzVarA.c("onActivityStop") || b.b()) {
                return;
            }
            gzVarA.h.a();
        }
    }
}
