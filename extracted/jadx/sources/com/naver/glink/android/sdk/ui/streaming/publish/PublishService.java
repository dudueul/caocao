package com.naver.glink.android.sdk.ui.streaming.publish;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.ui.streaming.publish.publisher.PublisherConfigure;
import com.naver.glink.android.sdk.ui.streaming.publish.publisher.c;
import com.naver.vapp.broadcast.publisher.RTMPListener;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
public class PublishService extends Service {
    private static final l a = l.a(PublishService.class.getSimpleName());
    private static final String b = "com.naver.glink.action.ACTION_START_PUBLISH";
    private static final String c = "com.naver.glink.action.ACTION_STOP_PUBLISH";
    private static final String d = "com.naver.glink.action.ACTION_PAUSE_PUBLISH";
    private static final String e = "com.naver.glink.action.ACTION_RESUME_PUBLISH";
    private static final String f = "com.naver.glink.action.ACTION_AUDIO_MUTE";
    private static final String g = "com.naver.glink.extra.PUBLISH_CONFIGURE";
    private static final String h = "com.naver.glink.extra.SCREEN_DENSITY";
    private static final String i = "com.naver.glink.extra.RESULT_CODE";
    private static final String j = "com.naver.glink.extra.RESULT_DATA";
    private static final String k = "com.naver.glink.extra.AUDIO_MUTE_ON";
    private c l;
    private MediaProjection m;
    private final RTMPListener.RTMPEventListener n = new RTMPListener.RTMPEventListener() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.PublishService.1
        public void onError(int i2) {
            PublishService.a.a("rtmp publisher error:" + ((String) Arrays.asList("FAILED_CONNECT", "FAILED_SEND_PACKET", "DISCONNECTED").get(i2)), new Object[0]);
        }

        public void onInfo(int i2, int i3) {
            PublishService.a.a("rtmp publisher info:" + ((String) Arrays.asList("CONNECTING", "CONNECTED", "RECONNECTING", "BUFFER_DURATION").get(i2)) + " param:" + i3, new Object[0]);
        }

        public void onStart() {
            PublishService.a.a("rtmp publisher start.", new Object[0]);
        }

        public void onStop() {
            PublishService.a.a("rtmp publisher stop.", new Object[0]);
            PublishService.this.stopSelf();
        }
    };

    public static void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) PublishService.class);
        intent.setAction(c);
        context.startService(intent);
    }

    public static void a(Context context, PublisherConfigure publisherConfigure, int i2, int i3, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) PublishService.class);
        intent2.setAction(b);
        intent2.putExtra(g, publisherConfigure);
        intent2.putExtra(h, i2);
        intent2.putExtra(i, i3);
        intent2.putExtra(j, intent);
        context.startService(intent2);
    }

    public static void a(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) PublishService.class);
        intent.setAction(f);
        intent.putExtra(k, z);
        context.startService(intent);
    }

    private void a(Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        switch (intent.getAction()) {
            case "com.naver.glink.action.ACTION_START_PUBLISH":
                a((PublisherConfigure) intent.getParcelableExtra(g), intent.getIntExtra(h, -1), intent.getIntExtra(i, -1), (Intent) intent.getParcelableExtra(j));
                break;
            case "com.naver.glink.action.ACTION_AUDIO_MUTE":
                a(intent.getBooleanExtra(k, false));
                break;
            case "com.naver.glink.action.ACTION_PAUSE_PUBLISH":
                if (this.l != null) {
                    this.l.d();
                    break;
                }
                break;
            case "com.naver.glink.action.ACTION_RESUME_PUBLISH":
                if (this.l != null) {
                    this.l.c();
                    break;
                }
                break;
            case "com.naver.glink.action.ACTION_STOP_PUBLISH":
                b();
                break;
        }
    }

    private void a(PublisherConfigure publisherConfigure, int i2, int i3, Intent intent) {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService("media_projection");
        if (mediaProjectionManager == null) {
            return;
        }
        this.m = mediaProjectionManager.getMediaProjection(i3, intent);
        if (this.m != null) {
            this.l = new c(getApplicationContext(), publisherConfigure, this.m, i2);
            this.l.a();
        }
    }

    private void a(boolean z) {
        if (this.l != null) {
            this.l.a(z);
        }
    }

    private void b() {
        if (this.l != null) {
            this.l.b();
            this.l = null;
        }
        if (this.m != null) {
            this.m.stop();
            this.m = null;
        }
    }

    public static void b(Context context) {
        Intent intent = new Intent(context, (Class<?>) PublishService.class);
        intent.setAction(d);
        context.startService(intent);
    }

    public static void c(Context context) {
        Intent intent = new Intent(context, (Class<?>) PublishService.class);
        intent.setAction(e);
        context.startService(intent);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        RTMPListener.registerListener(this.n);
    }

    @Override // android.app.Service
    public void onDestroy() {
        RTMPListener.registerListener((RTMPListener.RTMPEventListener) null);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        a(intent);
        return 2;
    }
}
