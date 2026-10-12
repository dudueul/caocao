package com.tapjoy.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class he extends hg {
    private static final String h = he.class.getSimpleName();
    private static he i;
    final String a;
    final hu b;
    private final gz j;
    private c k;
    private boolean l;
    private long m;
    private Context n;
    private boolean o = false;

    public he(gz gzVar, String str, hu huVar, Context context) {
        this.j = gzVar;
        this.a = str;
        this.b = huVar;
        this.n = context;
    }

    private static Boolean a(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                Object obj = bundle.get("tapjoy:hardwareAccelerated");
                if (obj instanceof Boolean) {
                    return (Boolean) obj;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    public static void a() {
        he heVar = i;
        if (heVar != null) {
            heVar.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Activity activity, final ha haVar, fw fwVar) {
        boolean z;
        if (this.l) {
            TapjoyLog.e(h, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.l = true;
        i = this;
        this.g = fwVar.a;
        this.k = new c(activity);
        this.k.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tapjoy.internal.he.2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                haVar.d(he.this.a);
            }
        });
        this.k.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tapjoy.internal.he.3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                String string;
                he.d();
                hg.a(activity, he.this.b.g);
                he.this.j.a(he.this.b.k, SystemClock.elapsedRealtime() - he.this.m);
                if (!he.this.d) {
                    haVar.a(he.this.a, he.this.f, he.this.b.h);
                }
                if (he.this.o && he.this.b.k != null && he.this.b.k.containsKey("action_id") && (string = he.this.b.k.get("action_id").toString()) != null && string.length() > 0) {
                    gz gzVar = he.this.j;
                    if (gzVar.b != null) {
                        hi hiVar = gzVar.b;
                        String strA = hi.a();
                        String strA2 = hiVar.b.a();
                        String strA3 = hiVar.a.a();
                        if (strA3 == null || !strA.equals(strA3)) {
                            hiVar.a.a(strA);
                            strA2 = "";
                        }
                        if (strA2.length() == 0) {
                            strA2 = string;
                        } else if (!strA2.contains(string)) {
                            strA2 = strA2.concat("," + string);
                        }
                        hiVar.b.a(strA2);
                    }
                }
                if (activity instanceof TJContentActivity) {
                    activity.finish();
                }
            }
        });
        this.k.setCanceledOnTouchOutside(false);
        ip ipVar = new ip(activity, this.b, new iq(activity, this.b, new iq.a() { // from class: com.tapjoy.internal.he.4
            @Override // com.tapjoy.internal.iq.a
            public final void a() {
                he.this.k.cancel();
            }

            @Override // com.tapjoy.internal.iq.a
            public final void a(hs hsVar) {
                fu fuVar;
                if ((he.this.g instanceof fu) && (fuVar = (fu) he.this.g) != null && fuVar.c != null) {
                    fuVar.c.a();
                }
                he.this.j.a(he.this.b.k, hsVar.b);
                hg.a(activity, hsVar.d);
                if (!jq.c(hsVar.e)) {
                    he.this.e.a(activity, hsVar.e, jq.b(hsVar.f));
                    he.this.d = true;
                }
                haVar.a(he.this.a, hsVar.g);
                if (hsVar.c) {
                    he.this.k.dismiss();
                }
            }

            @Override // com.tapjoy.internal.iq.a
            public final void b() {
                he.this.o = !he.this.o;
            }
        }));
        FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.addView(ipVar, new FrameLayout.LayoutParams(-2, -2, 17));
        this.k.setContentView(frameLayout);
        if (Boolean.FALSE.booleanValue()) {
            Window window = this.k.getWindow();
            if (Build.VERSION.SDK_INT != 16 || !"4.1.2".equals(Build.VERSION.RELEASE)) {
                z = true;
            } else if (Boolean.FALSE.equals(a(window.getContext()))) {
                z = false;
            } else {
                window.setFlags(16777216, 16777216);
                z = true;
            }
            if (z) {
                int i2 = ad.a.b;
                ae aeVar = new ae();
                switch (ad.AnonymousClass1.a[i2 - 1]) {
                    case 1:
                        ag agVar = new ag();
                        agVar.a = false;
                        agVar.b = 60.0f;
                        aeVar.a(agVar.a()).a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new ah().a(1.0f).b(0.3f).a());
                        break;
                    case 2:
                        ag agVar2 = new ag();
                        agVar2.a = false;
                        agVar2.b = -60.0f;
                        aeVar.a(agVar2.a()).a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new ah().a(-0.4f).b(0.3f).a());
                        break;
                    case 3:
                        ag agVar3 = new ag();
                        agVar3.a = true;
                        agVar3.b = -60.0f;
                        aeVar.a(agVar3.a()).a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new ah().a(0.3f).b(1.0f).a());
                        break;
                    case 4:
                        ag agVar4 = new ag();
                        agVar4.a = true;
                        agVar4.b = 60.0f;
                        aeVar.a(agVar4.a()).a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new ah().a(0.3f).b(-0.4f).a());
                        break;
                }
                ipVar.startAnimation(aeVar.b().a());
            }
        }
        try {
            this.k.show();
            this.k.getWindow().setLayout(-1, -1);
            if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
                this.k.getWindow().setFlags(1024, 1024);
            }
            this.m = SystemClock.elapsedRealtime();
            this.j.a(this.b.k);
            fwVar.a();
            fq fqVar = this.g;
            if (fqVar != null) {
                fqVar.b();
            }
            haVar.c(this.a);
        } catch (WindowManager.BadTokenException e) {
            throw e;
        }
    }

    static /* synthetic */ he d() {
        i = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.k != null) {
            this.k.dismiss();
        }
    }

    @Override // com.tapjoy.internal.hg
    public final void a(final ha haVar, final fw fwVar) {
        Activity activityA = a.a(this.n);
        if (activityA != null && !activityA.isFinishing()) {
            try {
                a(activityA, haVar, fwVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException e) {
            }
        }
        Activity activityA2 = gr.a();
        boolean z = (activityA2 == null || (activityA2.getWindow().getAttributes().flags & 1024) == 0) ? false : true;
        try {
            TJContentActivity.start(gz.a().e, new TJContentActivity.AbstractContentProducer() { // from class: com.tapjoy.internal.he.1
                @Override // com.tapjoy.TJContentActivity.AbstractContentProducer, com.tapjoy.TJContentActivity.ContentProducer
                public final void dismiss(Activity activity) {
                    he.this.e();
                }

                @Override // com.tapjoy.TJContentActivity.ContentProducer
                public final void show(Activity activity) {
                    try {
                        he.this.a(activity, haVar, fwVar);
                    } catch (WindowManager.BadTokenException e2) {
                        gw.b("Failed to show the content for \"{}\" caused by invalid activity", he.this.a);
                        haVar.a(he.this.a, he.this.f, null);
                    }
                }
            }, z);
            new Object[1][0] = this.a;
        } catch (ActivityNotFoundException e2) {
            if (activityA2 != null && !activityA2.isFinishing()) {
                try {
                    a(activityA2, haVar, fwVar);
                    new Object[1][0] = this.a;
                    return;
                } catch (WindowManager.BadTokenException e3) {
                    gw.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.a);
                    haVar.a(this.a, this.f, null);
                }
            }
            gw.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.a);
            haVar.a(this.a, this.f, null);
        }
    }

    @Override // com.tapjoy.internal.hg
    public final void b() throws Throwable {
        hu huVar = this.b;
        if (huVar.a != null) {
            huVar.a.b();
        }
        if (huVar.b != null) {
            huVar.b.b();
        }
        huVar.c.b();
        if (huVar.e != null) {
            huVar.e.b();
        }
        if (huVar.f != null) {
            huVar.f.b();
        }
        if (huVar.m == null || huVar.m.a == null) {
            return;
        }
        huVar.m.a.b();
    }

    @Override // com.tapjoy.internal.hg
    public final boolean c() {
        hu huVar = this.b;
        return (huVar.c == null || huVar.c.b == null || (huVar.m != null && huVar.m.a != null && huVar.m.a.b == null) || ((huVar.b == null || huVar.f == null || huVar.b.b == null || huVar.f.b == null) && (huVar.a == null || huVar.e == null || huVar.a.b == null || huVar.e.b == null))) ? false : true;
    }
}
