package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import com.nexon.nexonanalyticssdk.NxLogInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class GoogleARProxy extends c {
    private boolean f;

    GoogleARProxy(f fVar) {
        super("Google AR", fVar);
        this.f = false;
    }

    public static boolean a() {
        boolean z;
        try {
            Class<?> clsLoadClass = UnityPlayer.class.getClassLoader().loadClass("com.unity3d.unitygar.GoogleAR");
            o oVar = new o(clsLoadClass, clsLoadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            oVar.a("getClassVersion", new Class[0]);
            if (((Number) oVar.a("getClassVersion", new Object[0])).intValue() > 0) {
                Log.d("Unity", "Loading ARCore V1+ path.");
                z = false;
            } else {
                Log.d("Unity", "Loading ARCore Preview path (Version <= 1).");
                z = true;
            }
            return z;
        } catch (Exception e) {
            Log.d("Unity", "Loading ARCore Preview path.");
            return true;
        }
    }

    private boolean a(ClassLoader classLoader) {
        if (this.f) {
            return true;
        }
        try {
            Class<?> clsLoadClass = classLoader.loadClass("com.unity3d.unitygar.GoogleAR");
            o oVar = new o(clsLoadClass, clsLoadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            oVar.a(NxLogInfo.KEY_INITIALIZE_TYPE, new Class[]{Activity.class});
            oVar.a("create", new Class[0]);
            oVar.a("pause", new Class[0]);
            oVar.a("resume", new Class[0]);
            this.a = oVar;
            this.f = true;
            return true;
        } catch (Exception e) {
            this.b.reportError("Google AR Error", e.toString() + e.getLocalizedMessage());
            return false;
        }
    }

    private final native void tangoOnCreate(Activity activity);

    private final native void tangoOnServiceConnected(IBinder iBinder);

    private final native void tangoOnStop();

    final void a(final Activity activity, Context context) {
        if (a(UnityPlayer.class.getClassLoader())) {
            this.c = context;
            runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleARProxy.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (GoogleARProxy.this.a != null) {
                            GoogleARProxy.this.a.a(NxLogInfo.KEY_INITIALIZE_TYPE, activity);
                        }
                    } catch (Exception e) {
                        GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.e + " VR on UI Thread. " + e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    final void b() {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleARProxy.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("create", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    final void c() {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleARProxy.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("pause", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception pausing " + GoogleARProxy.this.e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    final void d() {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleARProxy.4
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("resume", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception resuming " + GoogleARProxy.this.e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    public final boolean e() {
        return this.f;
    }
}
