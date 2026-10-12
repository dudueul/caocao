package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.support.annotation.DrawableRes;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment;

/* JADX INFO: compiled from: StreamingViewerContract.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class b {

    /* JADX INFO: compiled from: StreamingViewerContract.java */
    interface a extends com.naver.glink.android.sdk.a.d.a {
        void a(StreamingResponse.Quality quality, boolean z);

        void a(WatchingStreaming watchingStreaming);

        WatchingStreaming.Type b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();

        void j();

        void k();
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.viewer.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: StreamingViewerContract.java */
    interface InterfaceC0062b extends com.naver.glink.android.sdk.a.d.b<a> {
        void a();

        void a(int i, boolean z);

        void a(StreamingResponse.Quality quality, boolean z);

        void a(StreamingReportFragment.Report report);

        void a(WatchingStreaming watchingStreaming);

        void a(String str);

        void a(String str, @DrawableRes int i);

        void b();

        void b(WatchingStreaming watchingStreaming);

        void c(WatchingStreaming watchingStreaming);
    }

    b() {
    }
}
