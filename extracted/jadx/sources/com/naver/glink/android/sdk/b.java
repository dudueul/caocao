package com.naver.glink.android.sdk;

/* JADX INFO: compiled from: Callbacks.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static Glink.OnClickAppSchemeBannerListener a;
    private static Glink.OnSdkStartedListener b;
    private static Glink.OnSdkStoppedListener c;
    private static Glink.OnJoinedListener d;
    private static Glink.OnPostedArticleListener e;
    private static Glink.OnPostedCommentListener f;
    private static Glink.OnWidgetScreenshotClickListener g;
    private static Glink.OnRecordFinishListener h;
    private static Glink.OnVotedListener i;
    private static PlugRecordManager.OnRecordManagerListener j;
    private static Glink.OnEndStreamingLiveListener k;
    private static Glink.OnEndWatchingLiveListener l;

    public static void a() {
        if (b != null) {
            b.onSdkStarted();
        }
    }

    public static void a(int i2) {
        if (f != null) {
            f.onPostedComment(i2);
        }
    }

    public static void a(int i2, int i3) {
        if (k != null) {
            k.onEndStreamingLive(i2, i3);
        }
    }

    public static void a(int i2, int i3, int i4) {
        if (e != null) {
            e.onPostedArticle(i2, i3, i4);
        }
    }

    public static void a(Glink.OnClickAppSchemeBannerListener onClickAppSchemeBannerListener) {
        a = onClickAppSchemeBannerListener;
    }

    public static void a(Glink.OnEndStreamingLiveListener onEndStreamingLiveListener) {
        k = onEndStreamingLiveListener;
    }

    public static void a(Glink.OnEndWatchingLiveListener onEndWatchingLiveListener) {
        l = onEndWatchingLiveListener;
    }

    public static void a(Glink.OnJoinedListener onJoinedListener) {
        d = onJoinedListener;
    }

    public static void a(Glink.OnPostedArticleListener onPostedArticleListener) {
        e = onPostedArticleListener;
    }

    public static void a(Glink.OnPostedCommentListener onPostedCommentListener) {
        f = onPostedCommentListener;
    }

    public static void a(Glink.OnRecordFinishListener onRecordFinishListener) {
        h = onRecordFinishListener;
    }

    public static void a(Glink.OnSdkStartedListener onSdkStartedListener) {
        b = onSdkStartedListener;
    }

    public static void a(Glink.OnSdkStoppedListener onSdkStoppedListener) {
        c = onSdkStoppedListener;
    }

    public static void a(Glink.OnVotedListener onVotedListener) {
        i = onVotedListener;
    }

    public static void a(Glink.OnWidgetScreenshotClickListener onWidgetScreenshotClickListener) {
        g = onWidgetScreenshotClickListener;
    }

    public static void a(PlugRecordManager.OnRecordManagerListener onRecordManagerListener) {
        j = onRecordManagerListener;
    }

    public static void a(String str) {
        if (a != null) {
            try {
                a.onClickAppSchemeBanner(str);
            } catch (Exception e2) {
            }
        }
    }

    public static void b() {
        if (c != null) {
            c.onSdkStopped();
        }
    }

    public static void b(int i2) {
        if (i != null) {
            i.onVoted(i2);
        }
    }

    public static void b(String str) {
        if (h != null) {
            h.onRecordFinished(str);
        }
    }

    public static void c() {
        if (d != null) {
            d.onJoined();
        }
    }

    public static void c(int i2) {
        if (l != null) {
            l.onEndWatchingLive(i2);
        }
    }

    public static void c(String str) {
        if (j != null) {
            j.onFinishRecord(str);
        }
    }

    public static void d() {
        if (g != null) {
            g.onScreenshotClick();
        }
    }

    public static void e() {
        if (j != null) {
            j.onStartRecord();
        }
    }

    public static void f() {
        if (j != null) {
            j.onErrorRecord();
        }
    }
}
