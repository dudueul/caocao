package com.naver.glink.android.sdk.api.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: LikeRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e {
    public static final String LIKE_REACTION_TYPE = "like";

    /* JADX INFO: compiled from: LikeRequests.java */
    public static class a {
        private final int articleId;

        public a(int i) {
            this.articleId = i;
        }

        private Request<com.naver.glink.android.sdk.api.a.d> a() {
            return RequestBuilders.LIKE.get(b(), c(null), com.naver.glink.android.sdk.api.a.d.class);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Request<com.naver.glink.android.sdk.api.a.e> a(com.naver.glink.android.sdk.api.a.d dVar) {
            Map<String, String> mapC = c(dVar);
            mapC.put("suppress_response_codes", "true");
            mapC.put("_method", "POST");
            return RequestBuilders.LIKE.get(b(), mapC, com.naver.glink.android.sdk.api.a.e.class);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Request<com.naver.glink.android.sdk.api.a.e> b(com.naver.glink.android.sdk.api.a.d dVar) {
            Map<String, String> mapC = c(dVar);
            mapC.put("suppress_response_codes", "true");
            mapC.put("_method", HttpRequest.METHOD_DELETE);
            return RequestBuilders.LIKE.get(b(), mapC, com.naver.glink.android.sdk.api.a.e.class);
        }

        private String b() {
            return "/like_neoid/v1/services/glink/contents/" + (com.naver.glink.android.sdk.d.b().e.a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + com.naver.glink.android.sdk.api.requests.a.b() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.articleId);
        }

        private Map<String, String> c(com.naver.glink.android.sdk.api.a.d dVar) {
            HashMap map = new HashMap();
            map.put("consumerKey", com.naver.glink.android.sdk.d.b().e.b);
            String strD = NeoIdSdkManager.d();
            if (!TextUtils.isEmpty(strD)) {
                map.put("token", strD);
                map.put("snsCode", com.naver.glink.android.sdk.login.neoid.c.b(com.naver.glink.android.sdk.d.r()));
            }
            map.put("reactionType", e.LIKE_REACTION_TYPE);
            map.put("categoryId", String.valueOf(com.naver.glink.android.sdk.api.requests.a.b()));
            if (dVar != null) {
                map.put("isPostTimeline", "false");
                map.put("guestToken", dVar.guestToken);
                map.put(TapjoyConstants.TJC_TIMESTAMP, dVar.timestamp);
            }
            return map;
        }

        public void a(Context context) {
            a().execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.d>() { // from class: com.naver.glink.android.sdk.api.requests.e.a.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.d dVar) {
                    new b(dVar).a();
                }
            });
        }

        public void b(final Context context) {
            a().execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.d>() { // from class: com.naver.glink.android.sdk.api.requests.e.a.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.d dVar) {
                    if (dVar.a()) {
                        a.this.b(dVar).execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.e>() { // from class: com.naver.glink.android.sdk.api.requests.e.a.2.1
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.e eVar) {
                                new b(eVar).a();
                            }
                        });
                    } else {
                        a.this.a(dVar).execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.e>() { // from class: com.naver.glink.android.sdk.api.requests.e.a.2.2
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.e eVar) {
                                new b(eVar).a();
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: compiled from: LikeRequests.java */
    public static class b extends com.naver.glink.android.sdk.a.a.a {
        public final int articleId;
        public final int count;
        public final boolean isReacted;

        b(com.naver.glink.android.sdk.api.a.d dVar) {
            this.articleId = a(dVar.contentsId);
            this.count = dVar.b();
            this.isReacted = dVar.a();
        }

        b(com.naver.glink.android.sdk.api.a.e eVar) {
            this.articleId = a(eVar.contentsId);
            this.count = eVar.count;
            this.isReacted = eVar.isReacted;
        }

        int a(String str) {
            String[] strArrSplit = TextUtils.split(str, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            if (strArrSplit.length < 3) {
                return -1;
            }
            return Integer.valueOf(strArrSplit[2]).intValue();
        }
    }

    public static Request<com.naver.glink.android.sdk.api.a.d> a(String str) {
        int i = com.naver.glink.android.sdk.d.b().d.a;
        String str2 = "/like_v2_oauth2_to_cookie/v1/services/CAFE_LIVE/contents/" + (i + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str);
        HashMap map = new HashMap();
        map.put("pool", "common");
        map.put("reactionType", LIKE_REACTION_TYPE);
        map.put("categoryId", String.valueOf(i));
        return RequestBuilders.LOCAL_LIKE.get(str2, map, com.naver.glink.android.sdk.api.a.d.class);
    }

    public static void a(final Context context, final String str, final RequestListener<com.naver.glink.android.sdk.api.a.e> requestListener) {
        a(str).execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.d>() { // from class: com.naver.glink.android.sdk.api.requests.e.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.d dVar) {
                e.b(str, dVar).execute(context, requestListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Request<com.naver.glink.android.sdk.api.a.e> b(String str, com.naver.glink.android.sdk.api.a.d dVar) {
        int i = com.naver.glink.android.sdk.d.b().d.a;
        String str2 = "/like_v2_oauth2_to_cookie/v1/services/CAFE_LIVE/contents/" + (i + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str);
        HashMap map = new HashMap();
        map.put("pool", "common");
        map.put("reactionType", LIKE_REACTION_TYPE);
        map.put("categoryId", String.valueOf(i));
        if (dVar != null) {
            map.put("isPostTimeline", "false");
            map.put("guestToken", dVar.guestToken);
            map.put(TapjoyConstants.TJC_TIMESTAMP, dVar.timestamp);
            map.put("suppress_response_codes", "true");
            map.put("_method", dVar.a() ? HttpRequest.METHOD_DELETE : "POST");
        }
        return RequestBuilders.LOCAL_LIKE.get(str2, map, com.naver.glink.android.sdk.api.a.e.class);
    }
}
