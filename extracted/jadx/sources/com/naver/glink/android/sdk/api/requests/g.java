package com.naver.glink.android.sdk.api.requests;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.ChannelCodes;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestBuilder;
import java.util.HashMap;
import kr.co.nexon.toy.android.ui.baseplate.NPPlateCodes;

/* JADX INFO: compiled from: VideoPlayLogRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class g {

    /* JADX INFO: compiled from: VideoPlayLogRequests.java */
    public static class a extends Response {
    }

    private static int a() {
        return com.naver.glink.android.sdk.d.m() ? 22027 : 2027;
    }

    public static Request<a> a(String str) {
        if (TextUtils.isEmpty(com.naver.glink.android.sdk.d.b().c.g)) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("sid", String.valueOf(a()));
        map.put(ChannelCodes.PORTUGUESE, "plug_a");
        map.put("inout", ChannelCodes.INDONESIAN);
        map.put("os", "android");
        map.put("stp", "0");
        map.put("vt", "");
        map.put(NPPlateCodes.CODE_CUSTOMER_CENTER, "");
        map.put("pv", "2.0.0");
        map.put("d", Build.MODEL);
        map.put("osv", String.valueOf(Build.VERSION.RELEASE));
        map.put("vid", str);
        String json = new GsonBuilder().create().toJson(map);
        HashMap map2 = new HashMap();
        map2.put("p", json);
        return new RequestBuilder().method(1).host(com.naver.glink.android.sdk.d.b().c.g).path("/rmcnmv/rmcnmv/PlayCount.json").parameters(map2).responseClass(a.class).timeoutMs(10000).toRequest();
    }
}
