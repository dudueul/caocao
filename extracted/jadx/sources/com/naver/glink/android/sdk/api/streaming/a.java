package com.naver.glink.android.sdk.api.streaming;

import android.text.TextUtils;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.RequestBuilders;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: ChattingRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.streaming.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChattingRequests.java */
    public static class C0031a extends Response {
        public List<ChattingMessage> messages = Collections.emptyList();
        public C0032a metaData;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.streaming.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ChattingRequests.java */
        public static class C0032a extends com.naver.plug.android.core.a.a {
            public boolean last;
            public String lastTimeStamp;
        }
    }

    public static Request<ChattingMessage> a(String str, ChattingMessage.MessageType messageType, String str2) {
        HashMap map = new HashMap();
        map.put("streamingId", str);
        map.put("type", messageType.code);
        if (str2 == null) {
            str2 = "";
        }
        map.put("contents", str2);
        return RequestBuilders.COMMON.post("/streamings/" + str + "/messages", map, ChattingMessage.class);
    }

    public static Request<Responses.x> a(String str, String str2) {
        return RequestBuilders.COMMON.delete("/streamings/" + str + "/messages/" + str2, Responses.x.class);
    }

    public static Request<C0031a> b(String str, String str2) {
        HashMap map = new HashMap();
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(20));
        if (!TextUtils.isEmpty(str2)) {
            map.put("lastTimeStamp", str2);
        }
        return RequestBuilders.COMMON.get("/streamings/" + str + "/messages", map, C0031a.class);
    }
}
