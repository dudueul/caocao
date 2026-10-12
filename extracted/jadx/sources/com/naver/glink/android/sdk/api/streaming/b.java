package com.naver.glink.android.sdk.api.streaming;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.d;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: PollingDataBuilder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private Map<String, Object> data = new HashMap();

    private b() {
        this.data.put("streamingStatus", Collections.emptyMap());
    }

    public static b c() {
        return new b();
    }

    public b a() {
        this.data.put("title", Collections.emptyMap());
        return this;
    }

    public b a(String str) {
        if (!TextUtils.isEmpty(str)) {
            HashMap map = new HashMap();
            map.put("liveId", str);
            this.data.put(NxLogSummaryInfos.KEY_COUNT, map);
        }
        return this;
    }

    public b a(boolean z, String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("isFirst", String.valueOf(z));
        map.put("liveId", str);
        map.put("countryCode", str2);
        map.put("qualityId", str3);
        map.put("userId", str4);
        map.put("osType", "ANDROID");
        map.put("osVer", Build.VERSION.RELEASE);
        map.put("playerType", "PLUG_SDK_A");
        map.put("playerVer", d.b().a);
        this.data.put("oliveStat", map);
        return this;
    }

    public String b() {
        return new GsonBuilder().create().toJson(this.data);
    }
}
