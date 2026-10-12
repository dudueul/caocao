package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import com.tapjoy.TJAdUnitConstants;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class gz {
    private static final gz q;
    private static gz r;
    private static Handler w;
    private static File x;
    public hi b;
    public Context e;
    public hc f;
    public gy g;
    public hm h;
    public gx i;
    public String j;
    public boolean k;
    public String l;
    public String m;
    public String o;
    private String u;
    private String v;
    public boolean c = false;
    private boolean s = false;
    public String d = null;
    private boolean t = false;
    public boolean n = false;
    public ha p = ha.a((go) null);
    public final hh a = new hh(this);

    static {
        gz gzVar = new gz();
        q = gzVar;
        r = gzVar;
    }

    private gz() {
    }

    public static gz a() {
        return r;
    }

    public static gz a(Context context) {
        gz gzVar = r;
        gzVar.b(context);
        return gzVar;
    }

    public static String a(Context context, Intent intent) {
        String strA = d.a(intent);
        if (strA != null) {
            gz gzVar = r;
            gzVar.b(context);
            if (jq.c(gzVar.f.c()) || intent.getBooleanExtra("fiverocks:force", false)) {
                hc hcVar = gzVar.f;
                synchronized (hcVar) {
                    hcVar.c.d.a(strA);
                    hcVar.b.d = strA;
                }
                if (strA.length() > 0) {
                    gy gyVar = gzVar.g;
                    gyVar.a(gyVar.a(ey.APP, "referrer"));
                }
            }
        }
        return strA;
    }

    public static void a(GLSurfaceView gLSurfaceView) {
        if (gw.a(gLSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            gr.a(gLSurfaceView);
        }
    }

    public static synchronized void a(Runnable runnable) {
        if (w == null) {
            w = new Handler(Looper.getMainLooper());
        }
        w.post(runnable);
    }

    public static synchronized File c(Context context) {
        if (x == null) {
            x = context.getDir("fiverocks", 0);
        }
        return x;
    }

    static File d(Context context) {
        return new File(c(context), "install");
    }

    public final fb a(boolean z) {
        if (z) {
            this.f.a();
        }
        return this.f.b();
    }

    public final synchronized void a(Context context, String str, String str2, String str3, String str4, String str5) {
        boolean z;
        boolean z2;
        synchronized (this) {
            if (!this.k) {
                b(context);
                if (gw.a(this.e != null, "The given context was null")) {
                    if (str4 != null && str4.length() == 24 && str4.matches("[0-9a-f]{24}")) {
                        z = true;
                    } else {
                        gw.b("Invalid App ID: {}", str4);
                        z = false;
                    }
                    if (z) {
                        if (str5 != null && str5.length() == 20 && str5.matches("[0-9A-Za-z\\-_]{20}")) {
                            z2 = true;
                        } else {
                            gw.b("Invalid App Key: {}", str5);
                            z2 = false;
                        }
                        if (z2) {
                            this.l = str;
                            this.m = str2;
                            this.u = str4;
                            this.v = str5;
                            try {
                                ce ceVar = new ce("TapjoySDK " + str2 + " (" + Build.MODEL + "; Android " + Build.VERSION.RELEASE + "; " + Locale.getDefault() + ")", new URL(str3));
                                ca.b = ceVar;
                                ca.a = Executors.newCachedThreadPool();
                                gx gxVar = this.i;
                                gxVar.b = ceVar;
                                gxVar.a();
                                new Object[1][0] = str3;
                                this.k = true;
                                hd hdVar = new hd(d(this.e));
                                if (!(hdVar.b() != null) && hdVar.a()) {
                                    gy gyVar = this.g;
                                    gyVar.a(gyVar.a(ey.APP, "install"));
                                }
                                hc hcVar = this.f;
                                if (!jq.c(str4) && !str4.equals(hcVar.c.D.a())) {
                                    hcVar.c.D.a(str4);
                                    hcVar.c.a(false);
                                }
                                b();
                            } catch (MalformedURLException e) {
                                throw new IllegalArgumentException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    public final synchronized void a(@Nullable final String str) {
        if (this.k) {
            if (str == null && this.o != null) {
                str = this.o;
            }
            this.o = null;
            if (str != null) {
                fb fbVarB = this.f.b();
                gw.a("GCM registration id of device {} updated for sender {}: {}", fbVarB.d.h, this.d, str);
                new il(fbVarB, str).a(new cf() { // from class: com.tapjoy.internal.gz.1
                    @Override // com.tapjoy.internal.cf
                    public final void a(ca caVar) {
                    }

                    @Override // com.tapjoy.internal.cf
                    public final /* synthetic */ void a(ca caVar, Object obj) {
                        hb hbVarB = hb.b(gz.this.e);
                        String str2 = str;
                        long jCurrentTimeMillis = 0 != 0 ? System.currentTimeMillis() + 0 : 0L;
                        if (!str2.equals(hbVarB.b.b(hbVarB.a))) {
                            new Object[1][0] = str2;
                            return;
                        }
                        hbVarB.b.b(hbVarB.a, true);
                        if (jCurrentTimeMillis != 0) {
                            new Object[1][0] = new Timestamp(jCurrentTimeMillis);
                        }
                        hbVarB.b.a(hbVarB.a, jCurrentTimeMillis);
                    }
                }, ca.a);
            }
        } else if (str != null) {
            this.o = str;
        }
    }

    final void a(Map map) {
        gy gyVar = this.g;
        ev.a aVarA = gyVar.a(ey.CAMPAIGN, "impression");
        if (map != null) {
            aVarA.r = bh.a((Object) map);
        }
        gyVar.a(aVarA);
    }

    final void a(Map map, long j) {
        gy gyVar = this.g;
        ev.a aVarA = gyVar.a(ey.CAMPAIGN, "view");
        aVarA.i = Long.valueOf(j);
        if (map != null) {
            aVarA.r = bh.a((Object) map);
        }
        gyVar.a(aVarA);
    }

    final void a(Map map, String str) {
        gy gyVar = this.g;
        ev.a aVarA = gyVar.a(ey.CAMPAIGN, TJAdUnitConstants.String.CLICK);
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put("region", str);
        aVarA.r = bh.a((Object) linkedHashMap);
        gyVar.a(aVarA);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x007e A[Catch: all -> 0x007b, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:23:0x0049, B:25:0x004f, B:26:0x0079, B:31:0x007e), top: B:33:0x0049 }] */
    public final void a(Set set) {
        if (d("setUserTags")) {
            if (set != null && !set.isEmpty()) {
                HashSet hashSet = new HashSet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (str != null) {
                        String strTrim = str.trim();
                        if (!strTrim.isEmpty() && strTrim.length() <= 200) {
                            hashSet.add(strTrim);
                            if (hashSet.size() >= 200) {
                                break;
                            }
                        }
                    }
                }
                set = hashSet;
            }
            hc hcVar = this.f;
            synchronized (hcVar) {
                if (set == null) {
                    hcVar.c.z.c();
                    hcVar.b.A.clear();
                } else if (set.isEmpty()) {
                    hcVar.c.z.c();
                    hcVar.b.A.clear();
                } else {
                    hcVar.c.z.a(Base64.encodeToString(fg.c.b(new fg(new ArrayList(set))), 2));
                    hcVar.b.A.clear();
                    hcVar.b.A.addAll(set);
                }
                throw th;
            }
        }
    }

    public final boolean a(Context context, String str, boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        b(context);
        if (!this.f.a(str, jCurrentTimeMillis, z)) {
            return false;
        }
        gy gyVar = this.g;
        ev.a aVarA = gyVar.a(ey.APP, "push_show");
        aVarA.s = new fc(null, null, str);
        gyVar.a(aVarA);
        return true;
    }

    public final synchronized void b() {
        if (this.k) {
            hb.b(this.e).e(this.d);
            a((String) null);
        }
    }

    public final synchronized void b(Context context) {
        if (this.e == null) {
            Context applicationContext = context.getApplicationContext();
            this.e = applicationContext;
            ga.a().a(applicationContext);
            this.f = hc.a(applicationContext);
            File file = new File(c(applicationContext), "events2");
            if (this.i == null) {
                this.i = new gx(file);
            }
            this.g = new gy(this.f, this.i);
            this.h = new hm(this.g);
            this.b = new hi(applicationContext);
            gf.a(new gh(new File(c(applicationContext), "usages"), this.g));
            ht htVar = ht.a;
            htVar.b = applicationContext.getApplicationContext();
            htVar.c = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2E", 0);
            htVar.d = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2U", 0);
            htVar.a();
        }
    }

    public final void b(String str) {
        gy gyVar = this.g;
        ev.a aVarA = gyVar.a(ey.APP, "push_ignore");
        aVarA.s = new fc(null, null, str);
        gyVar.a(aVarA);
    }

    public final Set c() {
        return !d("getUserTags") ? new HashSet() : this.f.e();
    }

    public final boolean c(String str) {
        if ((this.k || this.j != null) && this.e != null) {
            return true;
        }
        if (gw.a) {
            gw.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean d() {
        return this.h != null && this.h.b.get();
    }

    public final boolean d(String str) {
        if (this.e != null) {
            return true;
        }
        if (gw.a) {
            gw.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean e() {
        boolean z;
        hm hmVar = this.h;
        if (hmVar.c != null) {
            hmVar.c.cancel(false);
            hmVar.c = null;
        }
        if (hmVar.b.compareAndSet(false, true)) {
            gw.a("New session started");
            gy gyVar = hmVar.a;
            fc fcVarD = gyVar.a.d();
            hc hcVar = gyVar.a;
            synchronized (hcVar) {
                int iB = hcVar.c.h.b() + 1;
                hcVar.c.h.a(iB);
                hcVar.b.h = Integer.valueOf(iB);
            }
            ev.a aVarA = gyVar.a(ey.APP, "bootup");
            gyVar.c = SystemClock.elapsedRealtime();
            if (fcVarD != null) {
                aVarA.s = fcVarD;
            }
            gyVar.a(aVarA);
            fs.c.notifyObservers();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        hh hhVar = this.a;
        synchronized (hhVar) {
            hhVar.b = null;
        }
        ht.a.a();
        return true;
    }
}
