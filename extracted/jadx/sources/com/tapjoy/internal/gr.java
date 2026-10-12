package com.tapjoy.internal;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@ThreadSafe
public final class gr {
    private static Activity b;
    private static final by c = new by();
    private static final by d = new by();
    public static final ba a = new ba() { // from class: com.tapjoy.internal.gr.1
        @Override // com.tapjoy.internal.ba
        public final boolean a(Runnable runnable) {
            GLSurfaceView gLSurfaceView = (GLSurfaceView) gr.c.a();
            if (gLSurfaceView == null) {
                return false;
            }
            gLSurfaceView.queueEvent(runnable);
            return true;
        }
    };

    public static Activity a() {
        Activity activity = b;
        return activity == null ? b.a() : activity;
    }

    static void a(GLSurfaceView gLSurfaceView) {
        new Object[1][0] = gLSurfaceView;
        c.a(gLSurfaceView);
        gLSurfaceView.queueEvent(new Runnable() { // from class: com.tapjoy.internal.gr.2
            @Override // java.lang.Runnable
            public final void run() {
                Thread threadCurrentThread = Thread.currentThread();
                new Object[1][0] = threadCurrentThread;
                gr.d.a(threadCurrentThread);
            }
        });
    }

    public static Thread b() {
        return (Thread) d.a();
    }
}
