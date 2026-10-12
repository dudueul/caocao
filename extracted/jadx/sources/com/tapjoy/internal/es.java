package com.tapjoy.internal;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.FiveRocksIntegration;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJCurrency;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJEventOptimizer;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TapjoyAppSettings;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyException;
import com.tapjoy.TapjoyIntegrationException;
import com.tapjoy.TapjoyLog;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class es extends er {
    private boolean c = false;
    private String d = "";
    private TJCurrency e = null;
    private TapjoyCache f = null;

    es() {
    }

    private boolean k(String str) {
        if (this.a) {
            return true;
        }
        TapjoyLog.w("TapjoyAPI", "Can not call " + str + " because Tapjoy SDK has not successfully connected.");
        return false;
    }

    private boolean l(String str) {
        if (this.c) {
            return true;
        }
        this.d = "Can not call " + str + " because Tapjoy SDK is not initialized.";
        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, this.d));
        return false;
    }

    @Override // com.tapjoy.internal.er
    public final TJPlacement a(String str, TJPlacementListener tJPlacementListener) {
        return TJPlacementManager.a(str, "", "", tJPlacementListener);
    }

    @Override // com.tapjoy.internal.er
    public final void a(float f) {
        if (l("setCurrencyMultiplier")) {
            TapjoyConnectCore.getInstance().setCurrencyMultiplier(f);
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(int i) {
        gz gzVarA = gz.a();
        if (gzVarA.d("setUserLevel")) {
            gw.a("setUserLevel({}) called", Integer.valueOf(i));
            gzVarA.f.a(i >= 0 ? Integer.valueOf(i) : null);
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (this.e == null || !k("awardCurrency")) {
            return;
        }
        this.e.awardCurrency(i, tJAwardCurrencyListener);
    }

    @Override // com.tapjoy.internal.er
    public final void a(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (this.e == null || !k("spendCurrency")) {
            return;
        }
        this.e.spendCurrency(i, tJSpendCurrencyListener);
    }

    @Override // com.tapjoy.internal.er
    public final void a(int i, String str) {
        gz gzVarA = gz.a();
        if (gzVarA.d("setUserCohortVariable")) {
            boolean z = i > 0 && i <= 5;
            if (gw.a && !z) {
                gw.b("setCohortVariable: variableIndex is out of range");
            }
            if (z) {
                gw.a("setUserCohortVariable({}, {}) called", Integer.valueOf(i), str);
                gzVarA.f.a(i, gu.a(str));
            }
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(Activity activity) {
        if (activity != null) {
            b.a(activity);
        } else {
            TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Cannot set activity to NULL"));
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(Context context, Map map) {
        gz gzVarA = gz.a();
        if (gzVarA.e == null) {
            gzVarA.b(context);
        }
        hb.b(gzVarA.e);
        Context context2 = gzVarA.e;
        new Object[1][0] = map;
        String str = (String) map.get("fiverocks");
        if (str != null) {
            if (hc.a(context2).f()) {
                gz.a(context2).b(str);
                return;
            }
            String str2 = (String) map.get("title");
            String str3 = (String) map.get("message");
            if (str3 != null) {
                String str4 = (String) map.get("rich");
                String str5 = (String) map.get("sound");
                String str6 = (String) map.get(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                String str7 = (String) map.get("always");
                boolean z = "true".equals(str7) || Boolean.TRUE.equals(str7);
                String str8 = (String) map.get("repeatable");
                boolean z2 = "true".equals(str8) || Boolean.TRUE.equals(str8);
                String str9 = (String) map.get("placement");
                int iB = hb.b(map.get("nid"));
                String str10 = (String) map.get("channel_id");
                if (z || !gz.a(context2).d()) {
                    Notification notificationA = hb.a(context2, str, jq.a(str2), str3, hb.a((Object) str4), hb.a((Object) str5), str6, str9, iB, str10);
                    if (gz.a(context2).a(context2, str, z2)) {
                        hb.a(context2, iB, notificationA);
                    }
                }
            }
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(GLSurfaceView gLSurfaceView) {
        gz.a();
        gz.a(gLSurfaceView);
    }

    @Override // com.tapjoy.internal.er
    public final void a(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        if (this.e == null || !k("setEarnedCurrencyListener")) {
            return;
        }
        this.e.setEarnedCurrencyListener(tJEarnedCurrencyListener);
    }

    @Override // com.tapjoy.internal.er
    public final void a(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        if (this.e == null || !k("getCurrencyBalance")) {
            return;
        }
        this.e.getCurrencyBalance(tJGetCurrencyBalanceListener);
    }

    @Override // com.tapjoy.internal.er
    public final void a(TJVideoListener tJVideoListener) {
        if (l("setVideoListener")) {
            TJAdUnit.a = tJVideoListener;
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str) {
        gn.a(null, str, null, null, 0L);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, long j) {
        gn.a(null, str, null, null, j);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, TJSetUserIDListener tJSetUserIDListener) {
        if (!l("setUserID")) {
            if (tJSetUserIDListener != null) {
                tJSetUserIDListener.onSetUserIDFailure(this.d);
            }
        } else {
            TapjoyConnectCore.setUserID(str, tJSetUserIDListener);
            gz gzVarA = gz.a();
            if (gzVarA.d("setUserId")) {
                gzVarA.f.b(gu.a(str));
            }
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2) {
        gn.a(str, null, null, str2);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, double d, String str3) {
        String strA;
        String strA2;
        gz gzVarA = gz.a();
        if (!gzVarA.c("trackPurchase") || (strA = gu.a(str, "trackPurchase", "productId")) == null || (strA2 = gu.a(str2, "trackPurchase", "currencyCode")) == null) {
            return;
        }
        if (strA2.length() != 3) {
            gw.a("trackPurchase", "currencyCode", "invalid currency code");
        } else {
            gzVarA.g.a(strA, strA2.toUpperCase(Locale.US), d, (String) null, (String) null, gu.b(str3));
            gw.a("trackPurchase called");
        }
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, long j) {
        gn.a(str, str2, null, null, j);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4) {
        gn.a(str, str2, str3, str4);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4, long j) {
        gn.a(str, str2, str3, str4, j);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4, String str5, long j) {
        gn.a(str, str2, str3, str4, str5, j, null, 0L, null, 0L);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        gn.a(str, str2, str3, str4, str5, j, str6, j2, null, 0L);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        gn.a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    @Override // com.tapjoy.internal.er
    public final void a(String str, String str2, String str3, String str4, Map map) {
        gz gzVarA = gz.a();
        if (!gzVarA.c("trackEvent") || jq.c(str2)) {
            return;
        }
        LinkedHashMap linkedHashMapB = ju.b();
        if (map != null && map.size() > 0) {
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                if (key == null) {
                    if (gw.a) {
                        x.a("Tapjoy", "{}: {} must not be null", "trackEvent", "key in values map");
                        return;
                    }
                    return;
                } else if (key instanceof String) {
                    String strA = gu.a((String) key, "trackEvent", "key in values map");
                    if (strA == null) {
                        return;
                    }
                    Object value = entry.getValue();
                    if (!(value instanceof Number)) {
                        gw.a("trackEvent", "value in values map", "must be a long");
                        return;
                    }
                    linkedHashMapB.put(strA, Long.valueOf(((Number) value).longValue()));
                }
            }
        }
        gzVarA.g.a(str, str2, str3, str4, linkedHashMapB);
        gw.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, linkedHashMapB);
    }

    @Override // com.tapjoy.internal.er
    public final void a(Set set) {
        gz.a().a(set);
    }

    @Override // com.tapjoy.internal.er
    public final void a(boolean z) {
        TapjoyLog.setDebugEnabled(z);
    }

    @Override // com.tapjoy.internal.er
    public final boolean a(Context context, String str) {
        return a(context, str, (Hashtable) null, (TJConnectListener) null);
    }

    @Override // com.tapjoy.internal.er
    public final synchronized boolean a(final Context context, String str, final TJConnectListener tJConnectListener) {
        boolean z = false;
        synchronized (this) {
            TapjoyConnectCore.setSDKType("event");
            if (context == null) {
                TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "The application context is NULL"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else if (jq.c(str)) {
                TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "The limited SDK key is NULL. A valid limited SDK key is required to connect successfully to Tapjoy"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else {
                try {
                    try {
                        TapjoyConnectCore.requestLimitedTapjoyConnect(context, str, new TJConnectListener() { // from class: com.tapjoy.internal.es.2
                            @Override // com.tapjoy.TJConnectListener
                            public final void onConnectFailure() {
                                if (tJConnectListener != null) {
                                    tJConnectListener.onConnectFailure();
                                }
                            }

                            @Override // com.tapjoy.TJConnectListener
                            public final void onConnectSuccess() {
                                es.this.f = new TapjoyCache(context);
                                es.this.b = true;
                                if (tJConnectListener != null) {
                                    tJConnectListener.onConnectSuccess();
                                }
                            }
                        });
                        z = true;
                    } catch (TapjoyIntegrationException e) {
                        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e.getMessage()));
                        if (tJConnectListener != null) {
                            tJConnectListener.onConnectFailure();
                        }
                    }
                } catch (TapjoyException e2) {
                    TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e2.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        }
        return z;
    }

    @Override // com.tapjoy.internal.er
    public synchronized boolean a(final Context context, String str, Hashtable hashtable, final TJConnectListener tJConnectListener) {
        String strValueOf;
        boolean z = false;
        synchronized (this) {
            if (hashtable != null) {
                try {
                    Object obj = hashtable.get(TapjoyConnectFlag.ENABLE_LOGGING);
                    if (obj != null) {
                        TapjoyLog.setDebugEnabled("true".equals(obj.toString()));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            TapjoyConnectCore.setSDKType("event");
            if (context == null) {
                TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "The application context is NULL"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else if (jq.c(str)) {
                TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "The SDK key is NULL. A valid SDK key is required to connect successfully to Tapjoy"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else {
                FiveRocksIntegration.a();
                try {
                    TapjoyAppSettings.init(context);
                    TapjoyConnectCore.requestTapjoyConnect(context, str, hashtable, new TJConnectListener() { // from class: com.tapjoy.internal.es.1
                        @Override // com.tapjoy.TJConnectListener
                        public final void onConnectFailure() {
                            if (tJConnectListener != null) {
                                tJConnectListener.onConnectFailure();
                            }
                        }

                        @Override // com.tapjoy.TJConnectListener
                        public final void onConnectSuccess() {
                            es.this.e = new TJCurrency(context);
                            es.this.f = new TapjoyCache(context);
                            try {
                                TJEventOptimizer.init(context);
                                es.this.a = true;
                                if (tJConnectListener != null) {
                                    tJConnectListener.onConnectSuccess();
                                }
                            } catch (InterruptedException e) {
                                onConnectFailure();
                            } catch (RuntimeException e2) {
                                TapjoyLog.w("TapjoyAPI", e2.getMessage());
                                onConnectFailure();
                            }
                        }
                    });
                    this.c = true;
                    if (Build.VERSION.SDK_INT < 14) {
                        TapjoyLog.i("TapjoyAPI", "Automatic session tracking is not available on this device.");
                    } else {
                        if (hashtable != null && (strValueOf = String.valueOf(hashtable.get(TapjoyConnectFlag.DISABLE_AUTOMATIC_SESSION_TRACKING))) != null && strValueOf.equalsIgnoreCase("true")) {
                            z = true;
                        }
                        if (z) {
                            TapjoyLog.i("TapjoyAPI", "Automatic session tracking is disabled.");
                        } else {
                            fl.a(context);
                        }
                    }
                    z = true;
                } catch (TapjoyIntegrationException e) {
                    TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                } catch (TapjoyException e2) {
                    TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e2.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        }
        return z;
    }

    @Override // com.tapjoy.internal.er
    public final TJPlacement b(String str, TJPlacementListener tJPlacementListener) {
        return TJPlacementManager.b(str, "", "", tJPlacementListener);
    }

    @Override // com.tapjoy.internal.er
    public final String b() {
        return "12.4.2";
    }

    @Override // com.tapjoy.internal.er
    public final void b(int i) {
        gz gzVarA = gz.a();
        if (gzVarA.d("setUserFriendCount")) {
            gw.a("setUserFriendCount({}) called", Integer.valueOf(i));
            gzVarA.f.b(i >= 0 ? Integer.valueOf(i) : null);
        }
    }

    @Override // com.tapjoy.internal.er
    public final void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 14) {
            fl.a();
        }
        gz.a().n = true;
        gn.a(activity);
    }

    @Override // com.tapjoy.internal.er
    public final void b(String str) {
        gz gzVarA = gz.a();
        if (gzVarA.d("setAppDataVersion")) {
            gzVarA.f.a(gu.a(str));
        }
    }

    @Override // com.tapjoy.internal.er
    public final void b(String str, String str2, String str3, String str4) {
        gn.a(str, str2, str3, str4, 0L);
    }

    @Override // com.tapjoy.internal.er
    public final void b(boolean z) {
        String str;
        Object objValueOf;
        Object[] objArr;
        Object[] objArr2;
        String strB;
        char c = 0;
        gz gzVarA = gz.a();
        if (gzVarA.d("setPushNotificationDisabled")) {
            boolean zA = gzVarA.f.a(z);
            if (zA) {
                Object[] objArr3 = new Object[1];
                objValueOf = Boolean.valueOf(z);
                objArr = objArr3;
                objArr2 = objArr3;
                str = "setPushNotificationDisabled({}) called";
            } else {
                str = "setPushNotificationDisabled({}) called, but it is already {}";
                Object[] objArr4 = new Object[2];
                objArr4[0] = Boolean.valueOf(z);
                if (z) {
                    objValueOf = "disabled";
                    c = 1;
                    objArr = objArr4;
                    objArr2 = objArr4;
                } else {
                    objValueOf = TJAdUnitConstants.String.ENABLED;
                    c = 1;
                    objArr = objArr4;
                    objArr2 = objArr4;
                }
            }
            objArr[c] = objValueOf;
            gw.a(str, objArr2);
            if (zA && gzVarA.k && !jq.c(gzVarA.d)) {
                if (gzVarA.o != null) {
                    strB = null;
                } else {
                    hb hbVarB = hb.b(gzVarA.e);
                    strB = jq.b(hbVarB.b.b(hbVarB.a));
                }
                gzVarA.a(strB);
            }
        }
    }

    @Override // com.tapjoy.internal.er
    public final float c() {
        if (l("getCurrencyMultiplier")) {
            return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
        }
        return 1.0f;
    }

    @Override // com.tapjoy.internal.er
    public final void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 14) {
            fl.a();
        }
        gn.b(activity);
    }

    @Override // com.tapjoy.internal.er
    public final void c(String str) {
        if (jq.c(str)) {
            return;
        }
        gz gzVarA = gz.a();
        Set setC = gzVarA.c();
        if (setC.add(str)) {
            gzVarA.a(setC);
        }
    }

    @Override // com.tapjoy.internal.er
    public final void c(boolean z) {
        ge geVarA = ge.a();
        geVarA.a = Boolean.valueOf(z);
        if (geVarA.b()) {
            return;
        }
        geVarA.d = true;
    }

    @Override // com.tapjoy.internal.er
    public final void d() {
        if (l("startSession")) {
            if (Build.VERSION.SDK_INT >= 14) {
                fl.a();
            }
            TapjoyConnectCore.getInstance().appResume();
            gn.a();
        }
    }

    @Override // com.tapjoy.internal.er
    public final void d(String str) {
        if (jq.c(str)) {
            return;
        }
        gz gzVarA = gz.a();
        Set setC = gzVarA.c();
        if (setC.remove(str)) {
            gzVarA.a(setC);
        }
    }

    @Override // com.tapjoy.internal.er
    public final void d(boolean z) {
        ge geVarA = ge.a();
        geVarA.c = Boolean.valueOf(z);
        if (geVarA.d()) {
            return;
        }
        geVarA.d = true;
    }

    @Override // com.tapjoy.internal.er
    public final void e() {
        if (l("endSession")) {
            if (Build.VERSION.SDK_INT >= 14) {
                fl.a();
            }
            gz.a().n = false;
            TapjoyConnectCore.getInstance().appPause();
            gn.b();
        }
    }

    @Override // com.tapjoy.internal.er
    public final void e(String str) {
        if (k("actionComplete")) {
            TapjoyConnectCore.getInstance().actionComplete(str);
        }
    }

    @Override // com.tapjoy.internal.er
    public final Set f() {
        return gz.a().c();
    }

    @Override // com.tapjoy.internal.er
    public final void f(String str) {
        gz gzVarA = gz.a();
        gw.a("setGcmSender({}) called", str);
        gzVarA.d = jq.a(str);
        gzVarA.b();
    }

    @Override // com.tapjoy.internal.er
    public final String g(String str) {
        if (k("getSupportURL")) {
            return TapjoyConnectCore.getSupportURL(str);
        }
        return null;
    }

    @Override // com.tapjoy.internal.er
    public final void g() {
        gz.a().a((Set) null);
    }

    @Override // com.tapjoy.internal.er
    public final void h(String str) {
        ge geVarA = ge.a();
        if (al.a(str)) {
            return;
        }
        geVarA.b = str;
        if (geVarA.c()) {
            return;
        }
        geVarA.d = true;
    }

    @Override // com.tapjoy.internal.er
    public final boolean h() {
        gz gzVarA = gz.a();
        if (!gzVarA.d("isPushNotificationDisabled")) {
            return false;
        }
        boolean zF = gzVarA.f.f();
        gw.a("isPushNotificationDisabled = {}", Boolean.valueOf(zF));
        return zF;
    }

    @Override // com.tapjoy.internal.er
    public final void i(String str) {
        gz.a().a(str);
    }

    @Override // com.tapjoy.internal.er
    public final boolean i() {
        return this.a;
    }

    @Override // com.tapjoy.internal.er
    public final void j(String str) {
        TapjoyConnectCore.setCustomParameter(str);
    }

    @Override // com.tapjoy.internal.er
    public final boolean j() {
        return this.b;
    }

    @Override // com.tapjoy.internal.er
    public final String k() {
        return TapjoyConnectCore.getUserToken();
    }

    @Override // com.tapjoy.internal.er
    public final String l() {
        return TapjoyConnectCore.getCustomParameter();
    }
}
