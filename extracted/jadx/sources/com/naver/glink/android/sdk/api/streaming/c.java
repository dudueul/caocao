package com.naver.glink.android.sdk.api.streaming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.RequestBuilders;
import com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment;
import com.naver.glink.android.sdk.ui.streaming.streamer.StreamerSettings;
import com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.NullRequest;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import java.util.HashMap;

/* JADX INFO: compiled from: StreamingRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {

    /* JADX INFO: compiled from: StreamingRequests.java */
    public static class a {
        public void a(@NonNull WatchingStreaming watchingStreaming) {
        }

        public void a(@NonNull PlugError plugError) {
        }
    }

    /* JADX INFO: compiled from: StreamingRequests.java */
    public static class b extends Response {
        public int likeCount;
        public int viewCount;
    }

    public static Request<AuthorizeResponse> a() {
        return RequestBuilders.COMMON.post("/streaming/authorize", AuthorizeResponse.class);
    }

    public static Request<StreamingReportTypesResponse> a(StreamingReportFragment.Report report) {
        switch (report.a) {
            case STREAMING:
                return RequestBuilders.COMMON.get("/streaming/reportTypes", StreamingReportTypesResponse.class);
            case CHATTING:
                HashMap map = new HashMap();
                map.put("streamingMessageId", report.c.streamingMessageId);
                return RequestBuilders.COMMON.get("/streaming/message/reportTypes", map, StreamingReportTypesResponse.class);
            default:
                return NullRequest.nullRequest();
        }
    }

    public static Request<Responses.x> a(StreamingReportFragment.Report report, String str, String str2) {
        HashMap map = new HashMap();
        map.put("reportType", str);
        if (!TextUtils.isEmpty(str2)) {
            map.put("reportReason", str2);
        }
        switch (report.a) {
            case STREAMING:
                return RequestBuilders.COMMON.post("/streamings/" + report.b + "/report", map, Responses.x.class);
            case CHATTING:
                map.put("streamingMessageId", report.c.streamingMessageId);
                return RequestBuilders.COMMON.post("/streamings/" + report.b + "/message/report", map, Responses.x.class);
            default:
                return NullRequest.nullRequest();
        }
    }

    public static Request<StreamingCreateResponse> a(StreamerSettings streamerSettings) {
        HashMap map = new HashMap();
        map.put("title", streamerSettings.a);
        map.put("streamingId", streamerSettings.g);
        map.put("streamKey", streamerSettings.h);
        return RequestBuilders.COMMON.post("/streamings", map, StreamingCreateResponse.class);
    }

    public static Request<StreamingVideoResponse> a(String str) {
        return RequestBuilders.COMMON.get("/streamingVideos/" + str, StreamingVideoResponse.class);
    }

    public static Request<StreamingHomeResponse> a(String str, int i) {
        String str2;
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(str)) {
            str2 = "/streaming/home";
            map.put("sortBy", String.valueOf(i));
        } else {
            str2 = "/members/" + str + "/streaming/home";
        }
        return RequestBuilders.COMMON.get(str2, map, StreamingHomeResponse.class);
    }

    public static Request<Responses.x> a(String str, String str2) {
        HashMap map = new HashMap();
        map.put("title", str2);
        return RequestBuilders.COMMON.put("/streamings/" + str, map, Responses.x.class);
    }

    public static Request<StreamingVideosResponse> a(String str, String str2, int i) {
        String str3;
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(str)) {
            str3 = "/streamingVideos";
            map.put("sortBy", String.valueOf(i));
        } else {
            str3 = "/members/" + str + "/streamingVideos";
        }
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(20));
        if (str2 != null) {
            map.put("lastVideoId", str2);
        }
        return RequestBuilders.COMMON.get(str3, map, StreamingVideosResponse.class);
    }

    public static Request<b> a(String str, boolean z, String str2) {
        HashMap map = new HashMap();
        map.put("isPublish", String.valueOf(z));
        map.put("title", str2);
        return RequestBuilders.COMMON.put("/streamings/" + str + "/upload", map, b.class);
    }

    public static void a(final Context context, WatchingStreaming.Type type, final String str, final a aVar) {
        switch (type) {
            case STREAMING:
                d(str, "KR").execute(context, new RequestListener<StreamingResponse>() { // from class: com.naver.glink.android.sdk.api.streaming.c.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull StreamingResponse streamingResponse) {
                        aVar.a(new WatchingStreaming(str, streamingResponse));
                    }

                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    public void onFailure(@NonNull PlugError plugError) {
                        if (plugError.errorCode == 11017) {
                            c.a(context, WatchingStreaming.Type.VIDEO, str, aVar);
                        } else {
                            aVar.a(plugError);
                        }
                    }
                });
                break;
            case VIDEO:
                a(str).execute(context, new RequestListener<StreamingVideoResponse>() { // from class: com.naver.glink.android.sdk.api.streaming.c.2
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull StreamingVideoResponse streamingVideoResponse) {
                        aVar.a(new WatchingStreaming(streamingVideoResponse));
                    }

                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    public void onFailure(@NonNull PlugError plugError) {
                        aVar.a(plugError);
                    }
                });
                break;
        }
    }

    public static Request<Responses.x> b() {
        return RequestBuilders.COMMON.post("/streaming/agree", Responses.x.class);
    }

    public static Request<Responses.x> b(String str) {
        return RequestBuilders.COMMON.delete("/streamingVideos/" + str, Responses.x.class);
    }

    public static Request<StreamingsResponse> b(String str, String str2) {
        String str3 = TextUtils.isEmpty(str) ? "/streamings" : "/members/" + str + "/streamings";
        HashMap map = new HashMap();
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(20));
        if (str2 != null) {
            map.put("lastStreamingId", str2);
        }
        return RequestBuilders.COMMON.get(str3, map, StreamingsResponse.class);
    }

    public static Request<StreamingKeyResponse> c() {
        return RequestBuilders.COMMON.post("/streaming/key", StreamingKeyResponse.class);
    }

    public static Request<Responses.x> c(String str) {
        return RequestBuilders.COMMON.put("/streamings/" + str + "/end", Responses.x.class);
    }

    public static Request<StreamingPollingResponse> c(String str, String str2) {
        HashMap map = new HashMap();
        map.put("data", str2);
        return RequestBuilders.COMMON.post("/streamings/" + str + "/polling", map, StreamingPollingResponse.class);
    }

    public static Request<OlivePlayResponse> d(String str) {
        return RequestBuilders.PLUG_API_GW.get("/olive-play/1.0/lives/" + str + "/check-country", OlivePlayResponse.class);
    }

    private static Request<StreamingResponse> d(String str, String str2) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put("countryCode", str2);
        }
        return RequestBuilders.COMMON.get("/streamings/" + str, map, StreamingResponse.class);
    }
}
