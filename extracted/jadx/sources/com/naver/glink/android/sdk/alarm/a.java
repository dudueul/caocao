package com.naver.glink.android.sdk.alarm;

import com.naver.glink.android.sdk.a.c.c;
import com.naver.glink.android.sdk.a.c.d;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.r;
import com.naver.kaleido.KaleidoClient;
import com.squareup.otto.Subscribe;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: LiveAlarm.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class a {
    private r d;
    private d<LiveAlarmMessage> e;
    private LiveAlarmMessage f;
    private static final l b = l.a(a.class.getSimpleName());
    static int a = 60000;
    private static a c = new a();

    private a() {
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private String a(String str) {
        return (com.naver.glink.android.sdk.d.k() ? "glink:" : "plug:") + str + ":" + (com.naver.glink.android.sdk.d.k() ? com.naver.glink.android.sdk.d.b().d.a : com.naver.glink.android.sdk.d.b().e.a);
    }

    private d<LiveAlarmMessage> c(KaleidoClient kaleidoClient) {
        return new d<LiveAlarmMessage>(kaleidoClient, a("streamingPush"), LiveAlarmMessage.class) { // from class: com.naver.glink.android.sdk.alarm.a.3
            @Override // com.naver.glink.android.sdk.a.c.d
            public void a() {
            }

            @Override // com.naver.glink.android.sdk.a.c.d
            public void a(LiveAlarmMessage liveAlarmMessage, boolean z) {
                if (liveAlarmMessage == null) {
                    return;
                }
                if (liveAlarmMessage != null && z) {
                    a.this.b();
                }
                a.b.a("LiveAlarm onReceiveData message" + liveAlarmMessage.timestamp + " " + liveAlarmMessage.nickName + " " + liveAlarmMessage.isFirst + " " + liveAlarmMessage.title, new Object[0]);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.e == null) {
            return;
        }
        if (this.f == null || !com.naver.glink.android.sdk.ui.streaming.b.a(com.naver.glink.android.sdk.d.r())) {
            this.f = null;
            return;
        }
        if (this.f != null) {
            LiveAlarmMessage liveAlarmMessageM9clone = this.f.m9clone();
            b.a("sendLiveAlarm", new Object[0]);
            if (this.e != null) {
                this.e.a(liveAlarmMessageM9clone);
            }
        }
    }

    @Subscribe
    public void a(c.a aVar) {
        if (aVar == null) {
            return;
        }
        b.a("reconnectKaleido " + aVar.a, new Object[0]);
        if (aVar.a) {
            a().e();
        } else {
            a().b(aVar.b);
        }
    }

    public void a(LiveAlarmMessage liveAlarmMessage) {
        this.f = liveAlarmMessage;
        g();
    }

    void a(KaleidoClient kaleidoClient) {
        if (this.d == null && this.e == null) {
            b(kaleidoClient);
            com.naver.glink.android.sdk.a.a.b.a(this);
            d();
            b.a("startTimer", new Object[0]);
        }
    }

    void a(boolean z) {
        if (this.d != null) {
            b(true);
            if (z) {
                com.naver.glink.android.sdk.a.a.b.b(this);
            }
            b.a("stopTimer", new Object[0]);
        }
    }

    void b() {
        LiveAlarmMessage liveAlarmMessageD;
        if (this.e == null || (liveAlarmMessageD = this.e.d()) == null) {
            return;
        }
        try {
            com.naver.glink.android.sdk.a.a.b.c(liveAlarmMessageD);
            b.a(" updateLiveAlarm " + liveAlarmMessageD.timestamp, new Object[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    void b(KaleidoClient kaleidoClient) {
        if (this.e == null) {
            b.a("attach message", new Object[0]);
            this.e = c(kaleidoClient);
            this.e.b();
        }
    }

    void b(boolean z) {
        if (this.d != null) {
            b.a("pauseTimer", new Object[0]);
            this.d.a();
            this.d = null;
        }
        if (!z || this.e == null) {
            return;
        }
        e();
        com.naver.glink.android.sdk.a.a.b.b(this);
    }

    LiveAlarmMessage c() {
        if (this.e == null) {
            return null;
        }
        return this.e.d();
    }

    void d() {
        if (this.e == null || this.d != null) {
            return;
        }
        b.a("resumeTimer", new Object[0]);
        this.d = new r();
        this.d.a(TimeUnit.SECONDS.toMillis(30L), new Runnable() { // from class: com.naver.glink.android.sdk.alarm.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.g();
            }
        });
        this.d.a(TimeUnit.MINUTES.toMillis(3L), new Runnable() { // from class: com.naver.glink.android.sdk.alarm.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.b();
            }
        });
    }

    void e() {
        if (this.e != null) {
            b.a("detach message", new Object[0]);
            this.e.c();
            this.e = null;
        }
    }
}
