package com.naver.glink.android.sdk.ui.streaming.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming;

/* JADX INFO: compiled from: ChattingPresenter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class g implements b.a {
    private final Context a;
    private final b.InterfaceC0055b b;
    private final String c;
    private WatchingStreaming d;
    private a e;

    public g(Context context, b.InterfaceC0055b interfaceC0055b, WatchingStreaming watchingStreaming) {
        this(context, interfaceC0055b, watchingStreaming.b, watchingStreaming);
    }

    public g(Context context, b.InterfaceC0055b interfaceC0055b, String str) {
        this(context, interfaceC0055b, str, null);
    }

    private g(Context context, b.InterfaceC0055b interfaceC0055b, String str, WatchingStreaming watchingStreaming) {
        this.a = context;
        this.b = interfaceC0055b;
        this.c = str;
        this.d = watchingStreaming;
        interfaceC0055b.setPresenter(this);
    }

    private void a(final com.naver.glink.android.sdk.api.streaming.c.a aVar) {
        if (this.d != null) {
            aVar.a(this.d);
        } else {
            com.naver.glink.android.sdk.api.streaming.c.a(this.a, WatchingStreaming.Type.STREAMING, this.c, new com.naver.glink.android.sdk.api.streaming.c.a() { // from class: com.naver.glink.android.sdk.ui.streaming.a.g.4
                @Override // com.naver.glink.android.sdk.api.streaming.c.a
                public void a(@NonNull WatchingStreaming watchingStreaming) {
                    g.this.d = watchingStreaming;
                    aVar.a(g.this.d);
                }
            });
        }
    }

    @Override // com.naver.glink.android.sdk.a.d.a
    public void a() {
        if (this.e == null) {
            this.e = new a(this.a, this.c, false);
            this.e.a();
        }
        d();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void a(final ChattingMessage chattingMessage) {
        a(new com.naver.glink.android.sdk.api.streaming.c.a() { // from class: com.naver.glink.android.sdk.ui.streaming.a.g.1
            @Override // com.naver.glink.android.sdk.api.streaming.c.a
            public void a(@NonNull WatchingStreaming watchingStreaming) {
                if (chattingMessage.deleted || chattingMessage.getType() != ChattingMessage.MessageType.TEXT) {
                    return;
                }
                g.this.b.a(watchingStreaming, chattingMessage);
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void a(String str) {
        this.e.a(str);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void b() {
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void c() {
        a(new com.naver.glink.android.sdk.api.streaming.c.a() { // from class: com.naver.glink.android.sdk.ui.streaming.a.g.2
            @Override // com.naver.glink.android.sdk.api.streaming.c.a
            public void a(@NonNull WatchingStreaming watchingStreaming) {
                StreamingResponse.Authority authority = watchingStreaming.j.chat;
                if (authority.able) {
                    g.this.b.a();
                } else {
                    Toast.makeText(g.this.a, authority.errorMessage, 1).show();
                }
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void d() {
        this.e.a(this.a);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.a
    public void e() {
        a(new com.naver.glink.android.sdk.api.streaming.c.a() { // from class: com.naver.glink.android.sdk.ui.streaming.a.g.3
            @Override // com.naver.glink.android.sdk.api.streaming.c.a
            public void a(@NonNull WatchingStreaming watchingStreaming) {
                StreamingResponse.Authority authority = watchingStreaming.j.like;
                if (authority.able) {
                    g.this.e.c();
                } else {
                    Toast.makeText(g.this.a, authority.errorMessage, 1).show();
                }
            }
        });
    }
}
