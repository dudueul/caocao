package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class gv extends hg {
    private static final String h = gv.class.getSimpleName();
    private static gv i;
    final String a;
    final hr b;
    private final gz j;
    private boolean k;
    private boolean l;
    private long m;
    private Context n;
    private io o;
    private Activity p;
    private ha q;
    private Handler r;
    private Runnable s;

    public gv(gz gzVar, String str, hr hrVar, Context context) {
        this.j = gzVar;
        this.a = str;
        this.b = hrVar;
        this.n = context;
    }

    public static void a() {
        gv gvVar = i;
        if (gvVar != null) {
            Runnable runnable = new Runnable() { // from class: com.tapjoy.internal.gv.1
                @Override // java.lang.Runnable
                public final void run() {
                    gv.a(gv.this);
                }
            };
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null && mainLooper.getThread() == Thread.currentThread()) {
                runnable.run();
            } else {
                u.a().post(runnable);
            }
        }
    }

    private void a(final Activity activity, final ha haVar, fw fwVar) {
        if (this.k) {
            TapjoyLog.e(h, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.k = true;
        this.l = true;
        i = this;
        this.g = fwVar.a;
        this.o = new io(activity, this.b, new io.a() { // from class: com.tapjoy.internal.gv.2
            @Override // com.tapjoy.internal.io.a
            public final void a() {
                gv.a(gv.this);
            }

            @Override // com.tapjoy.internal.io.a
            public final void a(hz hzVar) {
                fv fvVar;
                if ((gv.this.g instanceof fv) && (fvVar = (fv) gv.this.g) != null && fvVar.c != null) {
                    fvVar.c.a();
                }
                gv.this.j.a(gv.this.b.b, hzVar.k);
                if (!jq.c(hzVar.h)) {
                    gv.this.e.a(activity, hzVar.h, jq.b(hzVar.i));
                    gv.this.d = true;
                } else if (!jq.c(hzVar.g)) {
                    hg.a(activity, hzVar.g);
                }
                haVar.a(gv.this.a, null);
                if (hzVar.j) {
                    gv.a(gv.this);
                }
            }
        });
        Window window = activity.getWindow();
        io ioVar = this.o;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        Window.Callback callback = window.getCallback();
        window.setCallback(null);
        window.addContentView(ioVar, layoutParams);
        window.setCallback(callback);
        this.m = SystemClock.elapsedRealtime();
        this.j.a(this.b.b);
        fwVar.a();
        fq fqVar = this.g;
        if (fqVar != null) {
            fqVar.b();
        }
        haVar.c(this.a);
        if (this.b.c > 0.0f) {
            this.r = new Handler(Looper.getMainLooper());
            this.s = new Runnable() { // from class: com.tapjoy.internal.gv.3
                @Override // java.lang.Runnable
                public final void run() {
                    gv.a(gv.this);
                }
            };
            this.r.postDelayed(this.s, (long) (this.b.c * 1000.0f));
        }
    }

    static /* synthetic */ void a(gv gvVar) {
        if (gvVar.l) {
            gvVar.l = false;
            if (gvVar.r != null) {
                gvVar.r.removeCallbacks(gvVar.s);
                gvVar.s = null;
                gvVar.r = null;
            }
            if (i == gvVar) {
                i = null;
            }
            gvVar.j.a(gvVar.b.b, SystemClock.elapsedRealtime() - gvVar.m);
            if (!gvVar.d && gvVar.q != null) {
                gvVar.q.a(gvVar.a, gvVar.f, null);
                gvVar.q = null;
            }
            ViewGroup viewGroup = (ViewGroup) gvVar.o.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(gvVar.o);
            }
            gvVar.o = null;
            if (gvVar.p instanceof TJContentActivity) {
                gvVar.p.finish();
            }
            gvVar.p = null;
        }
    }

    @Override // com.tapjoy.internal.hg
    public final void a(ha haVar, fw fwVar) {
        this.q = haVar;
        this.p = gr.a();
        if (this.p != null && !this.p.isFinishing()) {
            try {
                a(this.p, haVar, fwVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException e) {
            }
        }
        this.p = a.a(this.n);
        if (this.p != null && !this.p.isFinishing()) {
            try {
                a(this.p, haVar, fwVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException e2) {
            }
        }
        gw.b("Failed to show the content for \"{}\". No usable activity found.", this.a);
        haVar.a(this.a, this.f, null);
    }

    @Override // com.tapjoy.internal.hg
    public final void b() throws Throwable {
        Iterator it = this.b.a.iterator();
        while (it.hasNext()) {
            for (hz hzVar : ((ia) it.next()).c) {
                if (hzVar.l != null) {
                    hzVar.l.b();
                }
                if (hzVar.m != null) {
                    hzVar.m.b();
                }
            }
        }
    }

    @Override // com.tapjoy.internal.hg
    public final boolean c() {
        Iterator it = this.b.a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Iterator it2 = ((ia) it.next()).c.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                hz hzVar = (hz) it2.next();
                if ((hzVar.l != null && !hzVar.l.a()) || (hzVar.m != null && !hzVar.m.a())) {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return z;
    }
}
