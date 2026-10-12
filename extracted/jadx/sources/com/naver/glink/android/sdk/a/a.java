package com.naver.glink.android.sdk.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: AnalyticsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    public static void a(com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a interfaceC0036a) {
        a(interfaceC0036a, (Map<String, Object>) null);
    }

    public static void a(com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a interfaceC0036a, int i) {
        HashMap map = new HashMap();
        map.put("articleId", Integer.valueOf(i));
        a(interfaceC0036a, map);
    }

    public static void a(com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a interfaceC0036a, String str) {
        HashMap map = new HashMap();
        map.put("streamingId", str);
        a(interfaceC0036a, map);
    }

    public static void a(com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a interfaceC0036a, Map<String, Object> map) {
        if (a() && com.naver.glink.android.sdk.d.k()) {
            com.naver.glink.android.sdk.statistics.jackpot.a aVar = new com.naver.glink.android.sdk.statistics.jackpot.a();
            aVar.a(interfaceC0036a.getAction());
            aVar.a(interfaceC0036a.getSceneId());
            aVar.b(interfaceC0036a.getClassifier());
            if (map != null) {
                aVar.a(map);
            }
            Context contextR = com.naver.glink.android.sdk.d.r();
            if (contextR != null) {
                aVar.a(contextR);
            }
        }
    }

    public static boolean a() {
        if (!com.naver.glink.android.sdk.d.o() && !com.naver.glink.android.sdk.d.n() && com.naver.glink.android.sdk.d.m()) {
        }
        return true;
    }
}
