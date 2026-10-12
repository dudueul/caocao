package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class UnityPlayer extends FrameLayout implements com.unity3d.player.f {
    public static Activity currentActivity = null;
    private static boolean t;
    e a;
    k b;
    private int c;
    private boolean d;
    private boolean e;
    private n f;
    private final ConcurrentLinkedQueue g;
    private BroadcastReceiver h;
    private boolean i;
    private c j;
    private TelephonyManager k;
    private ClipboardManager l;
    private l m;
    private GoogleARProxy n;
    private GoogleARCoreApi o;
    private a p;
    private Camera2Wrapper q;
    private Context r;
    private SurfaceView s;
    private boolean u;
    private boolean v;
    private boolean w;
    private q x;

    /* JADX INFO: renamed from: com.unity3d.player.UnityPlayer$3, reason: invalid class name */
    class AnonymousClass3 extends BroadcastReceiver {
        final /* synthetic */ UnityPlayer a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            this.a.c();
        }
    }

    class a implements SensorEventListener {
        a() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class b {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        private static final /* synthetic */ int[] d = {a, b, c};
    }

    private class c extends PhoneStateListener {
        private c() {
        }

        /* synthetic */ c(UnityPlayer unityPlayer, byte b) {
            this();
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCallStateChanged(int i, String str) {
            UnityPlayer.this.nativeMuteMasterAudio(i == 1);
        }
    }

    enum d {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME
    }

    private class e extends Thread {
        Handler a;
        boolean b;
        boolean c;
        int d;
        int e;

        private e() {
            this.b = false;
            this.c = false;
            this.d = b.b;
            this.e = 5;
        }

        /* synthetic */ e(UnityPlayer unityPlayer, byte b) {
            this();
        }

        private void a(d dVar) {
            Message.obtain(this.a, 2269, dVar).sendToTarget();
        }

        public final void a() {
            a(d.QUIT);
        }

        public final void a(Runnable runnable) {
            a(d.PAUSE);
            Message.obtain(this.a, runnable).sendToTarget();
        }

        public final void b() {
            a(d.RESUME);
        }

        public final void b(Runnable runnable) {
            a(d.SURFACE_LOST);
            Message.obtain(this.a, runnable).sendToTarget();
        }

        public final void c() {
            a(d.FOCUS_GAINED);
        }

        public final void c(Runnable runnable) {
            Message.obtain(this.a, runnable).sendToTarget();
            a(d.SURFACE_ACQUIRED);
        }

        public final void d() {
            a(d.FOCUS_LOST);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.a = new Handler(new Handler.Callback() { // from class: com.unity3d.player.UnityPlayer.e.1
                private void a() {
                    if (e.this.d == b.c && e.this.c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        e.this.d = b.a;
                    }
                }

                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    if (message.what != 2269) {
                        return false;
                    }
                    d dVar = (d) message.obj;
                    if (dVar == d.NEXT_FRAME) {
                        return true;
                    }
                    if (dVar == d.QUIT) {
                        Looper.myLooper().quit();
                    } else if (dVar == d.RESUME) {
                        e.this.b = true;
                    } else if (dVar == d.PAUSE) {
                        e.this.b = false;
                    } else if (dVar == d.SURFACE_LOST) {
                        e.this.c = false;
                    } else if (dVar == d.SURFACE_ACQUIRED) {
                        e.this.c = true;
                        a();
                    } else if (dVar == d.FOCUS_LOST) {
                        if (e.this.d == b.a) {
                            UnityPlayer.this.nativeFocusChanged(false);
                        }
                        e.this.d = b.b;
                    } else if (dVar == d.FOCUS_GAINED) {
                        e.this.d = b.c;
                        a();
                    }
                    return true;
                }
            });
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.unity3d.player.UnityPlayer.e.2
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    UnityPlayer.this.executeGLThreadJobs();
                    if (e.this.b && e.this.c) {
                        if (e.this.e >= 0) {
                            if (e.this.e == 0 && UnityPlayer.this.i()) {
                                UnityPlayer.this.a();
                            }
                            e.this.e--;
                        }
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.c();
                        }
                        Message.obtain(e.this.a, 2269, d.NEXT_FRAME).sendToTarget();
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }

    private abstract class f implements Runnable {
        private f() {
        }

        /* synthetic */ f(UnityPlayer unityPlayer, byte b) {
            this();
        }

        public abstract void a();

        @Override // java.lang.Runnable
        public final void run() {
            if (UnityPlayer.this.isFinishing()) {
                return;
            }
            a();
        }
    }

    static {
        new m().a();
        t = false;
        t = loadLibraryStatic("main");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnityPlayer(Context context) {
        super(context);
        byte b2 = 0;
        this.c = -1;
        this.d = false;
        this.e = true;
        this.f = new n();
        this.g = new ConcurrentLinkedQueue();
        this.h = null;
        this.a = new e(this, b2);
        this.i = false;
        this.j = new c(this, b2);
        this.n = null;
        this.o = null;
        this.p = new a();
        this.q = null;
        this.w = false;
        this.b = null;
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.c = currentActivity.getRequestedOrientation();
        }
        a(currentActivity);
        this.r = context;
        if (currentActivity != null && i()) {
            this.m = new l(this.r, l.a.a()[getSplashMode()]);
            addView(this.m);
        }
        if (j.c) {
            if (currentActivity != null) {
                j.d.a(currentActivity, new Runnable() { // from class: com.unity3d.player.UnityPlayer.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnityPlayer.this.a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                UnityPlayer.this.f.d();
                                UnityPlayer.this.f();
                            }
                        });
                    }
                });
            } else {
                this.f.d();
            }
        }
        a(this.r.getApplicationInfo());
        if (!n.c()) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.r).setTitle("Failure to initialize!").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.unity3d.player.UnityPlayer.13
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.this.c();
                }
            }).setMessage("Your hardware does not support this application, sorry!").create();
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.show();
            return;
        }
        initJni(context);
        this.s = b();
        addView(this.s);
        bringChildToFront(this.m);
        this.u = false;
        nativeInitWebRequest(UnityWebRequest.class);
        k();
        this.k = (TelephonyManager) this.r.getSystemService("phone");
        this.l = (ClipboardManager) this.r.getSystemService("clipboard");
        this.q = new Camera2Wrapper(this.r);
        this.a.start();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (n.c()) {
            nativeUnitySendMessage(str, str2, str3);
        } else {
            g.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.16
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.removeView(UnityPlayer.this.m);
                UnityPlayer.h(UnityPlayer.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Surface surface) {
        if (this.d) {
            return;
        }
        b(0, surface);
    }

    private static void a(Activity activity) {
        View decorView;
        if (activity == null || !activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) || activity.getWindow() == null || (decorView = activity.getWindow().getDecorView()) == null) {
            return;
        }
        decorView.setSystemUiVisibility(7);
    }

    private static void a(ApplicationInfo applicationInfo) {
        if (t && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            n.a();
        }
    }

    private void a(View view, View view2) {
        boolean z;
        if (this.f.e()) {
            z = false;
        } else {
            pause();
            z = true;
        }
        if (view != null) {
            ViewParent parent = view.getParent();
            if (!(parent instanceof UnityPlayer) || ((UnityPlayer) parent) != this) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
                bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            removeView(view2);
        }
        if (z) {
            resume();
        }
    }

    private void a(f fVar) {
        if (isFinishing()) {
            return;
        }
        b(fVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SurfaceView b() {
        SurfaceView surfaceView = new SurfaceView(this.r);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.unity3d.player.UnityPlayer.17
            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                UnityPlayer.this.a(0, surfaceHolder.getSurface());
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.a(0, surfaceHolder.getSurface());
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.a(0, (Surface) null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    private void b(Runnable runnable) {
        if (n.c()) {
            if (Thread.currentThread() == this.a) {
                runnable.run();
            } else {
                this.g.add(runnable);
            }
        }
    }

    private boolean b(final int i, final Surface surface) {
        if (!n.c()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.18
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.nativeRecreateGfxState(i, surface);
                semaphore.release();
            }
        };
        if (i != 0) {
            runnable.run();
        } else if (surface == null) {
            this.a.b(runnable);
        } else {
            this.a.c(runnable);
        }
        if (surface == null && i == 0) {
            try {
                if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                    g.Log(5, "Timeout while trying detaching primary window.");
                }
            } catch (InterruptedException e2) {
                g.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!(this.r instanceof Activity) || ((Activity) this.r).isFinishing()) {
            return;
        }
        ((Activity) this.r).finish();
    }

    private void d() {
        reportSoftInputStr(null, 1, true);
        if (this.f.g()) {
            if (n.c()) {
                final Semaphore semaphore = new Semaphore(0);
                this.a.a(isFinishing() ? new Runnable() { // from class: com.unity3d.player.UnityPlayer.20
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnityPlayer.this.e();
                        semaphore.release();
                    }
                } : new Runnable() { // from class: com.unity3d.player.UnityPlayer.21
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (!UnityPlayer.this.nativePause()) {
                            semaphore.release();
                            return;
                        }
                        UnityPlayer.m(UnityPlayer.this);
                        UnityPlayer.this.e();
                        semaphore.release(2);
                    }
                });
                try {
                    if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                        g.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e2) {
                    g.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.f.c(false);
            this.f.b(true);
            if (this.i) {
                this.k.listen(this.j, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.v = nativeShouldQuit();
        this.w = true;
        nativeDone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.f.f()) {
            this.f.c(true);
            b(new Runnable() { // from class: com.unity3d.player.UnityPlayer.4
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeResume();
                }
            });
            this.a.b();
        }
    }

    private static void g() {
        if (n.c()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            n.b();
        }
    }

    private ApplicationInfo h() {
        return this.r.getPackageManager().getApplicationInfo(this.r.getPackageName(), 128);
    }

    static /* synthetic */ l h(UnityPlayer unityPlayer) {
        unityPlayer.m = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        try {
            return h().metaData.getBoolean("unity.splash-enable");
        } catch (Exception e2) {
            return false;
        }
    }

    private final native void initJni(Context context);

    private boolean j() {
        try {
            return h().metaData.getBoolean("unity.tango-enable");
        } catch (Exception e2) {
            return false;
        }
    }

    private void k() {
        if (this.r instanceof Activity) {
            ((Activity) this.r).getWindow().setFlags(1024, 1024);
        }
    }

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception e2) {
            g.Log(6, "Unknown error " + e2);
            return false;
        } catch (UnsatisfiedLinkError e3) {
            g.Log(6, "Unable to find " + str);
            return false;
        }
    }

    static /* synthetic */ boolean m(UnityPlayer unityPlayer) {
        unityPlayer.u = true;
        return true;
    }

    private final native void nativeDone();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeLowMemory();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativePause();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeRender();

    private final native void nativeRestartActivityIndicator();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeResume();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    private final native boolean nativeShouldQuit();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    private final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    static /* synthetic */ q t(UnityPlayer unityPlayer) {
        unityPlayer.x = null;
        return null;
    }

    final void a(Runnable runnable) {
        if (this.r instanceof Activity) {
            ((Activity) this.r).runOnUiThread(runnable);
        } else {
            g.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void addPhoneCallListener() {
        this.i = true;
        this.k.listen(this.j, 32);
    }

    @Override // com.unity3d.player.f
    public boolean addViewToPlayer(View view, boolean z) {
        boolean z2 = true;
        a(view, z ? this.s : null);
        boolean z3 = view.getParent() == this;
        boolean z4 = z && this.s.getParent() == null;
        boolean z5 = this.s.getParent() == this;
        if (!z3 || (!z4 && !z5)) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        if (this.s instanceof SurfaceView) {
            this.s.getHolder().setSizeFromLayout();
        }
        if (this.x != null) {
            this.x.c();
        }
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.c();
        }
    }

    protected void disableLogger() {
        g.a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.d = surface != null;
            a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.19
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.d) {
                        UnityPlayer.this.removeView(UnityPlayer.this.s);
                    } else {
                        UnityPlayer.this.addView(UnityPlayer.this.s);
                    }
                }
            });
        }
        return b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.g.poll();
            if (runnable == null) {
                return;
            } else {
                runnable.run();
            }
        }
    }

    protected String getClipboardText() {
        ClipData primaryClip = this.l.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.r).toString() : "";
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected int getSplashMode() {
        try {
            return h().metaData.getInt("unity.splash-mode");
        } catch (Exception e2) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        final Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.6
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.dismiss();
                    UnityPlayer.this.b = null;
                }
            }
        };
        if (j.b) {
            a(new f() { // from class: com.unity3d.player.UnityPlayer.7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(UnityPlayer.this, (byte) 0);
                }

                @Override // com.unity3d.player.UnityPlayer.f
                public final void a() {
                    UnityPlayer.this.a(runnable);
                }
            });
        } else {
            a(runnable);
        }
    }

    public void init(int i, boolean z) {
    }

    protected boolean initializeGoogleAr() {
        if (this.n == null && currentActivity != null && j()) {
            if (GoogleARProxy.a()) {
                this.n = new GoogleARProxy(this);
                this.n.a(currentActivity, this.r);
                this.n.b();
                if (!this.f.e()) {
                    this.n.d();
                }
                return this.n.e();
            }
            this.o = new GoogleARCoreApi();
            this.o.initializeARCore(currentActivity);
            if (!this.f.e()) {
                this.o.resumeARCore();
            }
        }
        return false;
    }

    protected boolean initializeGoogleVr() {
        final GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB == null) {
            GoogleVrApi.a(this);
            googleVrProxyB = GoogleVrApi.b();
            if (googleVrProxyB == null) {
                g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.11
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.injectEvent(new KeyEvent(0, 4));
                UnityPlayer.this.injectEvent(new KeyEvent(1, 4));
            }
        };
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.12
            @Override // java.lang.Runnable
            public final void run() {
                if (!googleVrProxyB.a(UnityPlayer.currentActivity, UnityPlayer.this.r, UnityPlayer.this.b(), runnable)) {
                    g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    googleVrProxyB.a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return googleVrProxyB.a();
            }
            g.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e2) {
            g.Log(5, "UI thread was interrupted while initializing Google VR. " + e2.getLocalizedMessage());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (n.c()) {
            return nativeInjectEvent(inputEvent);
        }
        return false;
    }

    protected boolean isFinishing() {
        if (!this.u) {
            boolean z = (this.r instanceof Activity) && ((Activity) this.r).isFinishing();
            this.u = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        if (n.c()) {
            b(new Runnable() { // from class: com.unity3d.player.UnityPlayer.2
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeLowMemory();
                }
            });
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.n != null) {
            this.n.c();
        } else if (this.o != null) {
            this.o.pauseARCore();
        }
        if (this.x != null) {
            this.x.a();
        }
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.pauseGvrLayout();
        }
        d();
    }

    public void quit() {
        if (GoogleVrApi.b() != null) {
            GoogleVrApi.a();
        }
        if (this.q != null) {
            this.q.a();
            this.q = null;
        }
        this.u = true;
        if (!this.f.e()) {
            pause();
        }
        this.a.a();
        try {
            this.a.join(4000L);
        } catch (InterruptedException e2) {
            this.a.interrupt();
        }
        if (this.h != null) {
            this.r.unregisterReceiver(this.h);
        }
        this.h = null;
        if (n.c()) {
            removeAllViews();
        }
        if (this.v || !this.w) {
            kill();
        }
        g();
    }

    @Override // com.unity3d.player.f
    public void removeViewFromPlayer(View view) {
        a(this.s, view);
        boolean z = view.getParent() == null;
        boolean z2 = this.s.getParent() == this;
        if (z && z2) {
            return;
        }
        if (!z) {
            g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
        }
        if (z2) {
            return;
        }
        g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
    }

    @Override // com.unity3d.player.f
    public void reportError(String str, String str2) {
        g.Log(6, str + ": " + str2);
    }

    protected void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        a(new f() { // from class: com.unity3d.player.UnityPlayer.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.f
            public final void a() {
                if (z) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                } else if (str != null) {
                    UnityPlayer.this.nativeSetInputString(str);
                }
                if (i == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    public void resume() {
        if (this.n != null) {
            this.n.d();
        } else if (this.o != null) {
            this.o.resumeARCore();
        }
        this.f.b(false);
        if (this.x != null) {
            this.x.b();
        }
        f();
        nativeRestartActivityIndicator();
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.b();
        }
    }

    protected void setCharacterLimit(final int i) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.9
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.a(i);
                }
            }
        });
    }

    protected void setClipboardText(String str) {
        this.l.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    protected void setSoftInputStr(final String str) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.8
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b == null || str == null) {
                    return;
                }
                UnityPlayer.this.b.a(str);
            }
        });
    }

    protected void showSoftInput(final String str, final int i, final boolean z, final boolean z2, final boolean z3, final boolean z4, final String str2, final int i2) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.5
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.b = new k(UnityPlayer.this.r, this, str, i, z, z2, z3, str2, i2);
                UnityPlayer.this.b.show();
            }
        });
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.x == null) {
            this.x = new q(this);
        }
        boolean zA = this.x.a(this.r, str, i, i2, i3, z, i4, i5, new q.a() { // from class: com.unity3d.player.UnityPlayer.14
            @Override // com.unity3d.player.q.a
            public final void a() {
                UnityPlayer.t(UnityPlayer.this);
            }
        });
        if (zA) {
            a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.15
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && (UnityPlayer.this.r instanceof Activity)) {
                        ((Activity) UnityPlayer.this.r).setRequestedOrientation(UnityPlayer.this.c);
                    }
                }
            });
        }
        return zA;
    }

    public void start() {
    }

    public void stop() {
    }

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.r.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.p, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.p);
        }
    }

    public void windowFocusChanged(boolean z) {
        this.f.a(z);
        if (z && this.b != null) {
            nativeSoftInputLostFocus();
            reportSoftInputStr(null, 1, false);
        }
        if (z) {
            this.a.c();
        } else {
            this.a.d();
        }
        f();
    }
}
