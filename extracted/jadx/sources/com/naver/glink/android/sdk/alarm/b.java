package com.naver.glink.android.sdk.alarm;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.a.c.c;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.kaleido.KaleidoClient;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: LiveAlarmHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    public static int a = (int) TimeUnit.SECONDS.toMillis(32);
    private static boolean b = false;
    private static c.b c = new c.b() { // from class: com.naver.glink.android.sdk.alarm.b.1
        @Override // com.naver.glink.android.sdk.a.c.c.b
        public void a(final KaleidoClient kaleidoClient) {
            com.naver.glink.android.sdk.api.requests.a.a(d.r(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.alarm.b.1.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    if (iVar.live) {
                        a.a().a(kaleidoClient);
                    }
                }
            });
        }
    };

    public static void a() {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            com.naver.glink.android.sdk.api.requests.a.a(d.r(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.alarm.b.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    if (iVar.live) {
                        a.a().d();
                    }
                }
            });
        }
    }

    public static void a(Context context) {
        if (com.naver.glink.android.sdk.ui.streaming.b.b() && context != null && (context instanceof Activity)) {
            c.a((Activity) context, c);
        }
    }

    public static void a(Context context, boolean z) {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            a.a().b(true);
            if (z) {
                a(context);
            }
        }
    }

    public static void a(LiveAlarmMessage liveAlarmMessage) {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            a.a().a(liveAlarmMessage);
        }
    }

    public static void a(boolean z) {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            a.a().b(z);
        }
    }

    public static void b() {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            c.b();
        }
    }

    public static boolean b(LiveAlarmMessage liveAlarmMessage) {
        if (!com.naver.glink.android.sdk.ui.streaming.b.b() || liveAlarmMessage == null || liveAlarmMessage.timestamp == null) {
            return true;
        }
        try {
            Long lValueOf = Long.valueOf(liveAlarmMessage.timestamp);
            if ((System.currentTimeMillis() - lValueOf.longValue() > ((long) a)) != b) {
                b = !b;
                a.a().b();
            }
            return System.currentTimeMillis() - lValueOf.longValue() > ((long) a);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean c() {
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            return b(a.a().c());
        }
        return true;
    }
}
