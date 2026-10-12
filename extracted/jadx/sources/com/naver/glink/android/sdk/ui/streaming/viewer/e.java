package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.r;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.streaming.StreamingPollingResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: StreamingViewerPresenter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e implements b.a {
    private static final int a = 30;
    private final Context b;
    private final b.InterfaceC0062b c;
    private WatchingStreaming d;
    private final r e;
    private long f;

    /* JADX INFO: compiled from: StreamingViewerPresenter.java */
    public static class a {
        public final String a;

        a(String str) {
            this.a = str;
        }
    }

    e(Context context, b.InterfaceC0062b interfaceC0062b, WatchingStreaming watchingStreaming) {
        this.b = context;
        this.c = interfaceC0062b;
        this.d = watchingStreaming;
        interfaceC0062b.setPresenter(this);
        this.e = new r();
    }

    private void a(@StringRes int i, @DrawableRes int i2) {
        this.d.a((StreamingResponse.Quality) null);
        this.c.a(com.naver.glink.android.sdk.d.a(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(StreamingPollingResponse streamingPollingResponse) {
        if (streamingPollingResponse.streamingStatus != null) {
            switch (streamingPollingResponse.streamingStatus.status()) {
                case STOP:
                    a(R.string.live_end_message, R.drawable.cf_icon_error_live);
                    break;
                case ENCODING:
                    a(R.string.live_video_encoding_message, R.drawable.cf_icon_error_live);
                    break;
                case VIDEO:
                    if (this.d.a() || (this.d.b() && !this.d.p)) {
                        e();
                        this.c.a();
                    }
                    break;
                case DELETED_VIDEO:
                    a(R.string.video_already_delete_error, R.drawable.cf_icon_error_livedelete);
                    break;
                case BLOCK_STREAMING:
                    a(R.string.streaming_block_error, R.drawable.icon_default_info_l);
                    break;
            }
        }
        if ((TextUtils.isEmpty(streamingPollingResponse.title) || TextUtils.equals(streamingPollingResponse.title, this.d.e)) ? false : true) {
            this.d.e = streamingPollingResponse.title;
            this.c.a(streamingPollingResponse.title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        com.naver.glink.android.sdk.api.streaming.b bVarC = com.naver.glink.android.sdk.api.streaming.b.c();
        bVarC.a();
        if (this.d.a()) {
            bVarC.a(z, this.d.n, "KR", this.d.c().qualityId, this.d.c);
        }
        com.naver.glink.android.sdk.api.streaming.c.c(this.d.b, bVarC.b()).execute(this.b, new RequestListener<StreamingPollingResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.e.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull StreamingPollingResponse streamingPollingResponse) {
                e.this.a(streamingPollingResponse);
            }
        });
    }

    private void l() {
        this.c.a(this.d);
        switch (this.d.a) {
            case STREAMING:
                a(this.d.c(), false);
                break;
            case VIDEO:
                if (this.d.p) {
                    a(this.d.c(), false);
                } else {
                    a(R.string.live_video_encoding_message, R.drawable.cf_icon_error_live);
                }
                com.naver.glink.android.sdk.api.requests.e.a(this.d.b).execute(this.b, new RequestListener<com.naver.glink.android.sdk.api.a.d>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.e.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.d dVar) {
                        e.this.c.a(dVar.b(), dVar.a());
                    }
                });
                break;
        }
    }

    @Override // com.naver.glink.android.sdk.a.d.a
    public void a() {
        this.f = System.currentTimeMillis();
        l();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void a(StreamingResponse.Quality quality, boolean z) {
        if (quality != null) {
            this.d.a(quality);
            this.c.a(quality, z);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void a(WatchingStreaming watchingStreaming) {
        this.d = watchingStreaming;
        l();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public WatchingStreaming.Type b() {
        return this.d.a;
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void c() {
        com.naver.glink.android.sdk.b.c((int) ((System.currentTimeMillis() - this.f) / 1000));
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void d() {
        e();
        this.e.a(TimeUnit.SECONDS.toMillis(30L), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.e.2
            private boolean b = true;

            @Override // java.lang.Runnable
            public void run() {
                e.this.a(this.b);
                this.b = false;
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void e() {
        this.e.a();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void f() {
        this.c.b(this.d);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void g() {
        this.c.c(this.d);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void h() {
        com.naver.glink.android.sdk.api.streaming.c.b(this.d.b).showProgress(true).execute(this.b, new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.e.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.x xVar) {
                com.naver.glink.android.sdk.a.a.b.c(new a(e.this.d.b));
                Toast.makeText(e.this.b, R.string.delete_message, 1).show();
                e.this.c.b();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void i() {
        this.c.a(this.d.d());
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void j() {
        StreamingResponse.Authority authority = this.d.j.like;
        if (authority.able) {
            com.naver.glink.android.sdk.api.requests.e.a(this.b, this.d.b, new RequestListener<com.naver.glink.android.sdk.api.a.e>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.e.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.e eVar) {
                    e.this.c.a(eVar.count, eVar.isReacted);
                }
            });
        } else {
            Toast.makeText(this.b, authority.errorMessage, 1).show();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.a
    public void k() {
        i.a(this.b, com.naver.glink.android.sdk.d.a(R.string.share_url), this.d.i);
    }
}
