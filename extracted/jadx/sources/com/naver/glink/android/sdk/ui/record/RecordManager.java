package com.naver.glink.android.sdk.ui.record;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
public class RecordManager {
    public static final int a = 88641;
    private static final String b = "naverRecord";
    private static RecordManager c;
    private MediaProjectionManager d;
    private MediaProjection e;
    private VirtualDisplay f;
    private VirtualDisplay g;
    private com.naver.glink.android.sdk.ui.record.c i;
    private c j;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private RECORD_TYPE q;
    private b r;
    private MediaRecorder h = new MediaRecorder();
    private a s = new a();
    private int k = 0;

    public enum RECORD_TYPE {
        WIDGET,
        CUSTOM
    }

    private class a extends MediaProjection.Callback {
        private a() {
        }

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            Log.i(RecordManager.b, "record MediaProjectionCallback onStop");
        }
    }

    public interface b {
        void a();

        void a(long j);

        void a(String str);

        void b();
    }

    private class c extends Timer {
        Handler a;
        TimerTask b;
        int c = 0;

        public c() {
            this.a = new d();
            this.b = new TimerTask() { // from class: com.naver.glink.android.sdk.ui.record.RecordManager.c.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (RecordManager.c == null) {
                        cancel();
                    }
                    if (!RecordManager.c.l) {
                        cancel();
                    }
                    Message message = new Message();
                    c cVar = c.this;
                    int i = cVar.c;
                    cVar.c = i + 1;
                    message.arg1 = i;
                    c.this.a.sendMessage(message);
                }
            };
        }

        public void a() {
            scheduleAtFixedRate(this.b, 1L, 1000L);
        }
    }

    private class d extends Handler {
        private d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (RecordManager.this.r != null) {
                RecordManager.this.r.a(message.arg1);
            }
        }
    }

    private RecordManager() {
        a();
    }

    private VirtualDisplay a(boolean z) {
        Point pointE = f().e();
        if (z) {
            pointE.x = 1;
            pointE.y = 1;
        }
        if (pointE == null) {
            return null;
        }
        return this.e.createVirtualDisplay(b + z, pointE.x, pointE.y, this.k, 2, z ? null : this.h.getSurface(), z ? null : new VirtualDisplay.Callback() { // from class: com.naver.glink.android.sdk.ui.record.RecordManager.4
            @Override // android.hardware.display.VirtualDisplay.Callback
            public void onPaused() {
                super.onPaused();
                Log.i(RecordManager.b, "record callback onPaused");
            }

            @Override // android.hardware.display.VirtualDisplay.Callback
            public void onResumed() {
                super.onResumed();
                Log.i(RecordManager.b, "record callback onResumed");
                RecordManager.this.o = true;
                if (RecordManager.this.r != null) {
                    RecordManager.this.r.a();
                }
            }

            @Override // android.hardware.display.VirtualDisplay.Callback
            public void onStopped() {
                super.onStopped();
                Log.i(RecordManager.b, "record callback onStopped");
                RecordManager.this.o = false;
                try {
                    if (RecordManager.this.e != null) {
                        RecordManager.this.e.unregisterCallback(RecordManager.this.s);
                        RecordManager.this.e = null;
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (RuntimeException e2) {
                    e2.printStackTrace();
                    Log.e(RecordManager.b, "record stop error!");
                }
                if (RecordManager.this.r != null) {
                    RecordManager.this.r.a(RecordManager.this.f().g());
                }
                RecordManager.c.l = false;
                RecordManager.c.n = false;
                RecordManager.a();
            }
        }, null);
    }

    public static void a() {
        if (c != null) {
            c.l = false;
            c.m = false;
            c.n = false;
            c.o = false;
            c.q = null;
        }
    }

    public static synchronized void a(Fragment fragment, com.naver.glink.android.sdk.ui.record.c cVar, RECORD_TYPE record_type) {
        DisplayManager displayManager;
        b();
        if (c != null && fragment != null && !c.l && !c.m && !c.n && !c.o && (displayManager = (DisplayManager) fragment.getActivity().getSystemService("display")) != null) {
            c.p = displayManager.getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION).length;
            a(cVar);
            a(record_type);
            if (c.k == 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                fragment.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                c.k = displayMetrics.densityDpi;
            }
            if (c.d == null) {
                c.d = (MediaProjectionManager) fragment.getActivity().getSystemService("media_projection");
            }
            if (c.e == null) {
                fragment.startActivityForResult(c.d.createScreenCaptureIntent(), a);
                c.m = true;
            } else {
                c.m = false;
                c.e.registerCallback(c.s, null);
                c.e();
            }
        }
    }

    public static void a(RECORD_TYPE record_type) {
        c.q = record_type;
    }

    public static void a(com.naver.glink.android.sdk.ui.record.c cVar) {
        if (c == null) {
            return;
        }
        if (cVar != null) {
            c.i = cVar;
        } else {
            c.i = com.naver.glink.android.sdk.ui.record.c.a();
        }
    }

    public static boolean a(int i, int i2, Intent intent) {
        if (c == null) {
            return false;
        }
        if (i != 88641) {
            Log.e(b, "Unknown request code: " + i);
            return false;
        }
        c.m = false;
        if (i2 != -1) {
            Log.e(b, "Screen Cast Permission Denied " + i);
            return false;
        }
        if (c.l) {
            return false;
        }
        c.e = c.d.getMediaProjection(i2, intent);
        c.e.registerCallback(c.s, null);
        c.e();
        return true;
    }

    public static RecordManager b() {
        if (c == null) {
            synchronized (RecordManager.class) {
                if (c == null) {
                    c = new RecordManager();
                }
            }
        }
        return c;
    }

    public static void b(RECORD_TYPE record_type) {
        if (c == null || c.q != record_type) {
            return;
        }
        c();
    }

    public static synchronized void c() {
        try {
            if (c != null && c.l && !c.n) {
                try {
                    c.n = true;
                    if (c.h != null) {
                        c.h.stop();
                        c.h.reset();
                        c.h.release();
                    }
                    c.i();
                    c.j();
                    c.j.cancel();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (RuntimeException e2) {
                    e2.printStackTrace();
                    Log.e(b, "record stop error!");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void e() {
        if (c.g()) {
            c.l = true;
            if (this.g != null) {
                new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.record.RecordManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RecordManager.this.g.release();
                        RecordManager.this.g = null;
                    }
                }, 100L);
            }
            try {
                Class.forName("com.unity3d.player.UnityPlayer");
                Log.d(b, "This is unity.");
                if (this.p == 0) {
                    c.g = c.a(true);
                }
            } catch (ClassNotFoundException e) {
                Log.d(b, "This is not unity. normal action.");
            }
            c.f = c.a(false);
            if (c.f != null) {
                c.h.start();
                if (c.j != null) {
                    c.j.cancel();
                }
                RecordManager recordManager = c;
                RecordManager recordManager2 = c;
                recordManager2.getClass();
                recordManager.j = recordManager2.new c();
                c.j.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.naver.glink.android.sdk.ui.record.c f() {
        if (this.i == null) {
            this.i = com.naver.glink.android.sdk.ui.record.c.a();
        }
        return this.i;
    }

    private synchronized boolean g() {
        boolean z = true;
        synchronized (this) {
            try {
                this.h = new MediaRecorder();
                this.h.setVideoSource(2);
                if (com.naver.glink.android.sdk.d.a(NXRuntimePermissionManager.RECORD_AUDIO) && f().f()) {
                    this.h.setAudioSource(1);
                }
                this.h.setOutputFormat(2);
                this.h.setVideoEncoder(2);
                this.h.setVideoFrameRate(f().c());
                this.h.setVideoEncodingBitRate(f().d());
                if (com.naver.glink.android.sdk.d.a(NXRuntimePermissionManager.RECORD_AUDIO) && f().f()) {
                    this.h.setAudioEncoder(1);
                    this.h.setAudioEncodingBitRate(128000);
                }
                Point pointE = f().e();
                if (pointE == null) {
                    z = false;
                } else {
                    this.h.setVideoSize(pointE.x, pointE.y);
                    this.h.setOutputFile(f().g());
                    this.h.setOnInfoListener(new MediaRecorder.OnInfoListener() { // from class: com.naver.glink.android.sdk.ui.record.RecordManager.2
                        @Override // android.media.MediaRecorder.OnInfoListener
                        public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
                            Log.i(RecordManager.b, "record onInfo" + i + " " + i2);
                            RecordManager.this.o = false;
                        }
                    });
                    this.h.setOnErrorListener(new MediaRecorder.OnErrorListener() { // from class: com.naver.glink.android.sdk.ui.record.RecordManager.3
                        @Override // android.media.MediaRecorder.OnErrorListener
                        public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                            Log.i(RecordManager.b, "record onError" + i + " " + i2);
                            RecordManager.a();
                            if (RecordManager.this.r != null) {
                                RecordManager.this.r.b();
                            }
                        }
                    });
                    h();
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.h.release();
                a();
                if (this.r != null) {
                    this.r.b();
                }
            }
        }
        return z;
    }

    private void h() throws IOException {
        if (this.h != null) {
            this.h.prepare();
        }
    }

    private void i() {
        if (this.f == null) {
            return;
        }
        this.f.release();
    }

    private void j() {
        if (this.e != null) {
            this.e.stop();
        }
    }

    public void a(b bVar) {
        this.r = bVar;
    }

    public boolean c(RECORD_TYPE record_type) {
        if (c == null || c.q != record_type) {
            return false;
        }
        return this.o;
    }
}
