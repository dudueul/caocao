package com.naver.glink.android.sdk;

import android.content.Context;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.naver.glink.android.sdk.api.requests.f;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class Statistics {
    public static final String CURRENCY_DOLLAR = "DOLLAR";
    public static final String CURRENCY_NONE = "NONE";
    public static final String CURRENCY_WON = "WON";
    public static final String MARKET_APPLE = "APPLE";
    public static final String MARKET_GOOGLE = "GOOGLE";
    public static final String MARKET_NONE = "NONE";
    public static final String MARKET_ONE = "ONE";

    private static void send(Map<String, String> map) {
        if (!d.k() || d.r() == null) {
            return;
        }
        f.a(map).execute(d.r());
    }

    public static void sendNewUser(String str, String str2) {
        HashMap map = new HashMap();
        map.put("logType", "NU");
        map.put("gameUserId", str);
        if (!TextUtils.isEmpty(str2)) {
            map.put("market", str2);
        }
        send(map);
    }

    public static void sendPageVisit(String str) {
        HashMap map = new HashMap();
        map.put("logType", "PV");
        map.put("gameUserId", str);
        send(map);
    }

    public static void sendPayUser(String str, double d, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("logType", "PU");
        map.put("gameUserId", str);
        map.put("pay", String.valueOf(d));
        if (!TextUtils.isEmpty(str2)) {
            map.put("productCode", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("currency", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            map.put("market", str4);
        }
        send(map);
    }

    public static void sendRequest(String str) {
        Context contextR = d.r();
        if (contextR == null) {
            return;
        }
        f.b(contextR, str);
    }
}
