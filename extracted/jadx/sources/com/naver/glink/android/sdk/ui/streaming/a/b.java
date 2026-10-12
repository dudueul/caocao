package com.naver.glink.android.sdk.ui.streaming.a;

import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: ChattingContract.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {

    /* JADX INFO: compiled from: ChattingContract.java */
    interface a extends com.naver.glink.android.sdk.a.d.a {
        void a(ChattingMessage chattingMessage);

        void a(String str);

        void b();

        void c();

        void d();

        void e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChattingContract.java */
    public interface InterfaceC0055b extends com.naver.glink.android.sdk.a.d.b<a> {
        void a();

        void a(WatchingStreaming watchingStreaming, ChattingMessage chattingMessage);
    }

    b() {
    }
}
