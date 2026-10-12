package com.naver.glink.android.sdk.api.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.CacheRequest;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: CacheRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static final C0024a cafeRequest = new C0024a();
    public static final CacheRequest<Responses.i> forChannelsRequest = new CacheRequest<Responses.i>() { // from class: com.naver.glink.android.sdk.api.requests.a.1
        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<Responses.i> createRequest() {
            return c.a((String) null, -1);
        }
    };
    public static final CacheRequest<com.naver.glink.android.sdk.api.a.c> connectionsRequest = new CacheRequest<com.naver.glink.android.sdk.api.a.c>() { // from class: com.naver.glink.android.sdk.api.requests.a.2
        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<com.naver.glink.android.sdk.api.a.c> createRequest() {
            return c.d();
        }
    };
    public static final CacheRequest<Responses.r> menusRequest = new CacheRequest<Responses.r>() { // from class: com.naver.glink.android.sdk.api.requests.a.3
        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<Responses.r> createRequest() {
            return f.a(false);
        }

        @Override // com.naver.plug.android.core.api.request.CacheRequest
        protected String getCacheKey() {
            return String.valueOf(a.b());
        }
    };
    public static final CacheRequest<f.a> userRequest = new CacheRequest<f.a>() { // from class: com.naver.glink.android.sdk.api.requests.a.4
        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<f.a> createRequest() {
            return f.f();
        }
    };
    public static final CacheRequest<Responses.i> forStreamingCafeRequest = new CacheRequest<Responses.i>() { // from class: com.naver.glink.android.sdk.api.requests.a.5
        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<Responses.i> createRequest() {
            return com.naver.glink.android.sdk.d.k() ? f.a() : c.a((String) null, com.naver.glink.android.sdk.d.b().a());
        }
    };

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.requests.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheRequests.java */
    private static class C0024a extends CacheRequest<Responses.i> {
        private String channelCode;
        private int channelId;

        private C0024a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final Context context, String str, final int i, final RequestListener<Responses.i> requestListener) {
            this.channelCode = str;
            this.channelId = i;
            execute(context, true, new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.api.requests.a.a.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    if (iVar.noticeMenuId == -1 && !com.naver.glink.android.sdk.d.h()) {
                        Toast.makeText(context, com.naver.glink.android.sdk.d.a(R.string.not_configured_notice_menu_id), 1).show();
                    }
                    if (iVar.eventMenuId == -1 && !com.naver.glink.android.sdk.d.h()) {
                        Toast.makeText(context, com.naver.glink.android.sdk.d.a(R.string.not_configured_event_menu_id), 1).show();
                    }
                    if (com.naver.glink.android.sdk.d.b().a(C0024a.this.channelCode, i)) {
                        com.naver.glink.android.sdk.d.b().a(C0024a.this.channelCode);
                        com.naver.glink.android.sdk.d.b().a(-1);
                    } else {
                        if (iVar.b() != null) {
                            com.naver.glink.android.sdk.d.b().a(iVar.langCode);
                            com.naver.glink.android.sdk.d.b().a(iVar.channelId);
                            if (i != iVar.channelId) {
                                C0024a.this.getCachedResponse().setTag(iVar.channelId + "");
                            }
                        }
                        a.forChannelsRequest.setCachedResponse(iVar);
                    }
                    if (com.naver.glink.android.sdk.d.b().f()) {
                        com.naver.glink.android.sdk.ui.main.b.f();
                    }
                    if (requestListener != null) {
                        requestListener.onSuccess(iVar);
                    }
                    com.naver.glink.android.sdk.ui.tabs.b.a(iVar);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(Responses.i iVar, PlugError plugError) {
                    if (requestListener != null) {
                        requestListener.onFinally(iVar, plugError);
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    if (requestListener != null) {
                        requestListener.onFailure(plugError);
                    }
                }
            });
        }

        @Override // com.naver.plug.android.core.api.request.CacheRequest
        public Request<Responses.i> createRequest() {
            return com.naver.glink.android.sdk.d.b().a(this.channelCode, this.channelId) ? f.a() : c.a(this.channelCode, this.channelId);
        }

        @Override // com.naver.plug.android.core.api.request.CacheRequest
        protected String getCacheKey() {
            return this.channelId + "";
        }
    }

    public static Responses.i a() {
        return cafeRequest.getCachedResponse();
    }

    public static void a(Context context, RequestListener<Responses.i> requestListener) {
        a(context, com.naver.glink.android.sdk.d.b().b(), com.naver.glink.android.sdk.d.b().a(), requestListener);
    }

    public static void a(Context context, String str, int i, RequestListener<Responses.i> requestListener) {
        cafeRequest.a(context, str, i, requestListener);
    }

    public static int b() {
        if (a() != null) {
            return a().channelId;
        }
        return -1;
    }

    public static String c() {
        if (a() == null || a().b() == null) {
            return null;
        }
        return a().b().a();
    }

    public static void d() {
        forStreamingCafeRequest.setCachedResponse(null);
        userRequest.setCachedResponse(null);
    }

    public static void e() {
        cafeRequest.setCachedResponse(null);
        forChannelsRequest.setCachedResponse(null);
        forStreamingCafeRequest.setCachedResponse(null);
        connectionsRequest.setCachedResponse(null);
        menusRequest.setCachedResponse(null);
    }
}
