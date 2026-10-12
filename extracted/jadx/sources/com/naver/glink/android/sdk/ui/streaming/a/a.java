package com.naver.glink.android.sdk.ui.streaming.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.kaleido.KaleidoClient;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Chatting.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static Handler b = new Handler(Looper.getMainLooper());
    public final String a;
    private final Context c;
    private final boolean d;
    private com.naver.glink.android.sdk.a.c.d<ChattingMessage> e;
    private com.naver.glink.android.sdk.a.c.a f;
    private com.naver.glink.android.sdk.a.c.a g;
    private Request<com.naver.glink.android.sdk.api.streaming.a.C0031a> h = null;
    private com.naver.glink.android.sdk.api.streaming.a.C0031a i;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Chatting.java */
    public static class C0054a {
        public final String a;
        public final int b;
        public final boolean c;
        public final boolean d;

        C0054a(String str, int i, boolean z, boolean z2) {
            this.a = str;
            this.b = i;
            this.c = z;
            this.d = z2;
        }
    }

    /* JADX INFO: compiled from: Chatting.java */
    public static class b {
        public final String a;
        public final int b;

        b(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    /* JADX INFO: compiled from: Chatting.java */
    public static class c {
        public final boolean a;
        final boolean b;
        public final String c;
        public final List<ChattingMessage> d;

        c(boolean z, boolean z2, String str, List<ChattingMessage> list) {
            this.a = z;
            this.b = z2;
            this.c = str;
            this.d = list;
        }
    }

    public a(Context context, String str, boolean z) {
        this.c = context;
        this.a = str;
        this.d = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.naver.glink.android.sdk.a.c.d<ChattingMessage> a(KaleidoClient kaleidoClient) {
        return new com.naver.glink.android.sdk.a.c.d<ChattingMessage>(kaleidoClient, b("streamingChat"), ChattingMessage.class) { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.2
            @Override // com.naver.glink.android.sdk.a.c.d
            public void a() {
            }

            @Override // com.naver.glink.android.sdk.a.c.d
            public void a(ChattingMessage chattingMessage, boolean z) {
                if (chattingMessage != null) {
                    if (a.this.d || !z) {
                        a.this.a(false, false, Collections.singletonList(chattingMessage));
                    }
                }
            }
        };
    }

    private void a(ChattingMessage.MessageType messageType, String str) {
        com.naver.glink.android.sdk.api.streaming.a.a(this.a, messageType, str).execute(this.c, new RequestListener<ChattingMessage>() { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.7
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull ChattingMessage chattingMessage) {
                a.this.a(chattingMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ChattingMessage chattingMessage) {
        if (this.e != null) {
            ChattingMessage chattingMessageCopy = chattingMessage.copy();
            chattingMessageCopy.own = false;
            this.e.a(chattingMessageCopy);
        }
        a(false, false, Collections.singletonList(chattingMessage));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Object obj) {
        b.post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.6
            @Override // java.lang.Runnable
            public void run() {
                com.naver.glink.android.sdk.a.a.b.c(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2, List<ChattingMessage> list) {
        a(new c(z, z2, this.a, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.naver.glink.android.sdk.a.c.a b(KaleidoClient kaleidoClient) {
        return new com.naver.glink.android.sdk.a.c.a(kaleidoClient, b("streamingReactionLike")) { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.3
            @Override // com.naver.glink.android.sdk.a.c.a
            public void a(int i) {
                a.this.a(new C0054a(a.this.a, i, false, false));
            }

            @Override // com.naver.glink.android.sdk.a.c.a
            public void a(int i, boolean z) {
                a.this.a(new C0054a(a.this.a, i, false, !z));
            }
        };
    }

    private String b(String str) {
        return (com.naver.glink.android.sdk.d.k() ? "glink:" : "plug:") + str + ":" + this.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.naver.glink.android.sdk.a.c.a c(KaleidoClient kaleidoClient) {
        return new com.naver.glink.android.sdk.a.c.a(kaleidoClient, b("streamingViewerCount")) { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.4
            @Override // com.naver.glink.android.sdk.a.c.a
            public void a(int i) {
                if (!a.this.d) {
                    d();
                }
                a.this.a(new b(a.this.a, i));
            }

            @Override // com.naver.glink.android.sdk.a.c.a
            public void a(int i, boolean z) {
                a.this.a(new b(a.this.a, i));
            }
        };
    }

    public void a() {
        com.naver.glink.android.sdk.a.c.c.a((Activity) this.c, new com.naver.glink.android.sdk.a.c.c.b() { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.1
            @Override // com.naver.glink.android.sdk.a.c.c.b
            public void a(KaleidoClient kaleidoClient) {
                a.this.e = a.this.a(kaleidoClient);
                a.this.e.b();
                a.this.f = a.this.b(kaleidoClient);
                a.this.f.a();
                a.this.g = a.this.c(kaleidoClient);
                a.this.g.a();
            }
        });
    }

    public void a(int i) {
        int iC;
        if (!this.d || this.g == null || (iC = i - this.g.c()) == 0) {
            return;
        }
        this.g.b(iC);
        a(new b(this.a, i));
    }

    void a(Context context) {
        if (this.h != null) {
            return;
        }
        com.naver.glink.android.sdk.api.streaming.a.C0031a.C0032a c0032a = this.i != null ? this.i.metaData : null;
        if (c0032a == null || !c0032a.last) {
            this.h = com.naver.glink.android.sdk.api.streaming.a.b(this.a, c0032a != null ? c0032a.lastTimeStamp : null);
            this.h.execute(context, new RequestListener<com.naver.glink.android.sdk.api.streaming.a.C0031a>() { // from class: com.naver.glink.android.sdk.ui.streaming.a.a.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.streaming.a.C0031a c0031a) {
                    if (c0031a.metaData == null) {
                        c0031a.metaData = new com.naver.glink.android.sdk.api.streaming.a.C0031a.C0032a();
                        c0031a.metaData.last = true;
                    }
                    boolean z = a.this.i == null;
                    a.this.i = c0031a;
                    Collections.reverse(c0031a.messages);
                    a.this.a(true, z, c0031a.messages);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(com.naver.glink.android.sdk.api.streaming.a.C0031a c0031a, PlugError plugError) {
                    a.this.h = null;
                }
            });
        }
    }

    void a(String str) {
        a(ChattingMessage.MessageType.TEXT, str);
    }

    public void b() {
        if (this.e != null) {
            this.e.c();
        }
        if (this.f != null) {
            this.f.b();
        }
        if (this.g != null) {
            if (!this.d) {
                this.g.e();
            }
            this.g.b();
        }
        com.naver.glink.android.sdk.a.c.c.a();
    }

    public void c() {
        if (this.f == null) {
            return;
        }
        a(new C0054a(this.a, this.f.d(), true, true));
    }
}
