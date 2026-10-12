package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.a.h;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.r;
import com.naver.glink.android.sdk.alarm.LiveAlarmMessage;
import com.naver.glink.android.sdk.api.streaming.StreamingCreateResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingKeyResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingPollingResponse;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.streaming.publish.publisher.PublisherConfigure;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: StreamerFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
public class c extends com.naver.glink.android.sdk.ui.parent.a.b {
    private static final l a = l.a(c.class.getSimpleName());
    private static final String b = "publish-fragment";
    private StreamerSettings c;
    private com.naver.glink.android.sdk.ui.streaming.a.a d;
    private com.naver.glink.android.sdk.ui.streaming.publish.a e;
    private r f;

    /* JADX INFO: compiled from: StreamerFragment.java */
    static class a {
        final String a;

        a(String str) {
            this.a = str;
        }
    }

    /* JADX INFO: compiled from: StreamerFragment.java */
    private class b implements com.naver.glink.android.sdk.ui.streaming.publish.a.InterfaceC0057a {
        private final StreamerSettings b;
        private final com.naver.glink.android.sdk.ui.widget.progress.c c = new com.naver.glink.android.sdk.ui.widget.progress.c();

        b(StreamerSettings streamerSettings) {
            this.b = streamerSettings;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(PlugError plugError) {
            com.naver.glink.android.sdk.ui.streaming.b.g(c.this.getFragmentManager());
            Glink.startWidget(c.this.getActivity());
            if (plugError != null) {
                Toast.makeText(c.this.getActivity(), plugError.errorMessage, 1).show();
            }
            this.c.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            c.this.a(this.b);
            com.naver.glink.android.sdk.ui.streaming.b.a(c.this.getFragmentManager());
            if (this.b.d) {
                com.naver.glink.android.sdk.ui.streaming.b.b(c.this.getFragmentManager());
            }
            com.naver.glink.android.sdk.ui.streaming.b.a(c.this.getFragmentManager(), this.b.g);
            com.naver.glink.android.sdk.ui.streaming.b.b(c.this.getFragmentManager(), this.b.g);
            this.c.a();
        }

        @Override // com.naver.glink.android.sdk.ui.streaming.publish.a.InterfaceC0057a
        public void a() {
            com.naver.glink.android.sdk.api.streaming.c.a(this.b).execute(c.this.getActivity(), new RequestListener<StreamingCreateResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.b.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull StreamingCreateResponse streamingCreateResponse) {
                    b.this.b.j = streamingCreateResponse.liveId;
                    b.this.b.k = streamingCreateResponse.linkUrl;
                    b.this.d();
                    com.naver.glink.android.sdk.alarm.b.a(new LiveAlarmMessage(streamingCreateResponse.nickName, b.this.b.a, true, String.valueOf(System.currentTimeMillis())));
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    b.this.a(plugError);
                }
            });
        }

        @Override // com.naver.glink.android.sdk.ui.streaming.publish.a.InterfaceC0057a
        public void b() {
            a((PlugError) null);
        }

        void c() {
            com.naver.glink.android.sdk.ui.main.b.a(false);
            if (!h.a()) {
                this.c.a(c.this.getActivity());
            }
            com.naver.glink.android.sdk.api.streaming.c.c().execute(c.this.getActivity(), new RequestListener<StreamingKeyResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.b.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull StreamingKeyResponse streamingKeyResponse) {
                    b.this.b.a(streamingKeyResponse);
                    c.this.a(new PublisherConfigure(b.this.b), b.this);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    b.this.a(plugError);
                }
            });
        }
    }

    public static c a() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(StreamingPollingResponse streamingPollingResponse) {
        StreamingPollingResponse.StreamingStat streamingStat = streamingPollingResponse.streamingStatus;
        if (streamingStat != null) {
            switch (streamingStat.status()) {
                case STOP:
                case PUBLISH_DROPPED:
                    Toast.makeText(getActivity(), streamingStat.message, 1).show();
                    b();
                    com.naver.glink.android.sdk.ui.streaming.b.g(getFragmentManager());
                    if (this.c.b()) {
                        com.naver.glink.android.sdk.ui.streaming.b.a(getActivity(), this.c);
                    }
                    break;
                case VOD_GENERATE_FAILED:
                case BLOCK_STREAMING:
                case BLOCK_VIDEO:
                case INVALID_KEY_FRAME_INTERVAL:
                    AlertDialogFragmentView.b(getActivity(), streamingStat.message).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.3
                        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                        public void b(DialogInterface dialogInterface) {
                            com.naver.glink.android.sdk.ui.streaming.b.g(c.this.getFragmentManager());
                        }
                    }).a();
                    b();
                    break;
            }
        }
        StreamingPollingResponse.Count count = streamingPollingResponse.count;
        if (count == null || this.d == null) {
            return;
        }
        this.d.a(count.cv);
    }

    private boolean e() {
        return this.c != null && this.c.a();
    }

    private void f() {
        g();
        this.f.a(TimeUnit.SECONDS.toMillis(1L), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.1
            long a = System.currentTimeMillis();

            private long a() {
                long jCurrentTimeMillis = System.currentTimeMillis() - this.a;
                this.a = System.currentTimeMillis();
                return jCurrentTimeMillis;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.c.a(a());
                com.naver.glink.android.sdk.a.a.b.c(new a(c.this.c.c()));
            }
        });
        this.f.a(TimeUnit.SECONDS.toMillis(10L), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.2
            @Override // java.lang.Runnable
            public void run() {
                com.naver.glink.android.sdk.api.streaming.c.c(c.this.c.g, com.naver.glink.android.sdk.api.streaming.b.c().a(c.this.c.j).b()).execute(c.this.getActivity(), new RequestListener<StreamingPollingResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.c.2.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull StreamingPollingResponse streamingPollingResponse) {
                        c.this.a(streamingPollingResponse);
                    }
                });
            }
        });
    }

    private void g() {
        this.f.a();
    }

    public void a(PublisherConfigure publisherConfigure, com.naver.glink.android.sdk.ui.streaming.publish.a.InterfaceC0057a interfaceC0057a) {
        this.e.a(publisherConfigure, interfaceC0057a);
    }

    public void a(StreamerSettings streamerSettings) {
        a.a("startedStreaming", new Object[0]);
        this.c = streamerSettings;
        this.c.a(true);
        this.d = new com.naver.glink.android.sdk.ui.streaming.a.a(getActivity(), this.c.g, true);
        this.d.a();
        f();
        if (com.naver.glink.android.sdk.ui.main.b.i() == null || com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog() == null) {
            return;
        }
        com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog().setCanceledOnTouchOutside(true);
    }

    void b() {
        a.a("stopStreaming", new Object[0]);
        this.e.d();
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
        g();
        if (com.naver.glink.android.sdk.ui.main.b.i() == null || com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog() == null) {
            return;
        }
        com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog().setCanceledOnTouchOutside(false);
    }

    void b(StreamerSettings streamerSettings) {
        new b(streamerSettings).c();
    }

    public StreamerSettings c() {
        return this.c;
    }

    public com.naver.glink.android.sdk.ui.streaming.a.a d() {
        return this.d;
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            com.naver.glink.android.sdk.ui.streaming.b.g(getFragmentManager());
            return;
        }
        this.f = new r();
        this.e = com.naver.glink.android.sdk.ui.streaming.publish.a.a();
        getChildFragmentManager().beginTransaction().add(this.e, b).commit();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.b, android.app.Fragment
    public void onPause() {
        if (e()) {
            g();
            this.e.c();
        }
        super.onPause();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.b, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (e()) {
            this.e.b();
            f();
        }
    }
}
