package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.naver.glink.android.sdk.ChannelCodes;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hc {
    public static final String a = UUID.randomUUID().toString();
    private static hc d;
    public final fh.a b;
    public final hj c;
    private final fa.a e;
    private final eu.a f;
    private final Context g;

    private hc(Context context, hj hjVar) {
        hn.a();
        this.e = new fa.a();
        this.f = new eu.a();
        this.b = new fh.a();
        this.e.p = "12.4.2/Android";
        this.e.g = "Android";
        this.e.h = Build.VERSION.RELEASE;
        this.e.e = Build.MANUFACTURER;
        this.e.f = Build.MODEL;
        this.e.l = Locale.getDefault().toString();
        this.e.m = TimeZone.getDefault().getID();
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        fa.a aVar = this.e;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
        File file = new File(gz.c(applicationContext), "deviceid");
        String string = sharedPreferences.getString(TapjoyConstants.PREF_ANALYTICS_ID, null);
        if (jq.c(string)) {
            string = file.exists() ? jq.b(bg.a(file)) : null;
            string = string == null ? UUID.randomUUID().toString() : string;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(TapjoyConstants.PREF_ANALYTICS_ID, string);
            editorEdit.apply();
        }
        aVar.d = string;
        if (!ga.b().a(TapjoyConnectFlag.DISABLE_ANDROID_ID_AS_ANALYTICS_ID, true)) {
            fa.a aVar2 = this.e;
            String string2 = Settings.Secure.getString(applicationContext.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            aVar2.t = !"9774d56d682e549c".equals(string2) ? jq.b(string2) : null;
        }
        PackageManager packageManager = applicationContext.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (!jq.c(simCountryIso)) {
                this.e.q = simCountryIso.toUpperCase(Locale.US);
            }
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (!jq.c(networkCountryIso)) {
                this.e.r = networkCountryIso.toUpperCase(Locale.US);
            }
        }
        String packageName = applicationContext.getPackageName();
        this.e.n = packageName;
        fa.a aVar3 = this.e;
        Signature[] signatureArrE = z.e(packageManager, packageName);
        aVar3.o = jq.a((signatureArrE == null || signatureArrE.length <= 0) ? null : Base64.encodeToString(ch.a(signatureArrE[0].toByteArray()), 2));
        this.f.c = z.a(packageManager, packageName);
        this.f.d = Integer.valueOf(z.b(packageManager, packageName));
        String installerPackageName = packageManager.getInstallerPackageName(packageName);
        if (!jq.c(installerPackageName)) {
            this.f.f = installerPackageName;
        }
        String strA = a(packageManager, packageName);
        if (!jq.c(strA)) {
            this.f.g = strA;
        }
        a();
        this.c = hjVar;
        String strA2 = this.c.c.a();
        if (strA2 != null && strA2.length() > 0) {
            this.e.p = strA2 + " 12.4.2/Android";
        }
        String strB = this.c.b();
        if (strB != null) {
            this.b.d = strB;
        }
        fh.a aVar4 = this.b;
        hj hjVar2 = this.c;
        long jC = hjVar2.b.getLong(ChannelCodes.ITALIAN, 0L);
        if (jC == 0) {
            Context context2 = hjVar2.a;
            jC = z.c(context2.getPackageManager(), context2.getPackageName());
            if (jC == 0) {
                jC = gz.d(hjVar2.a).lastModified();
                if (jC == 0) {
                    Context context3 = hjVar2.a;
                    jC = new File(z.d(context3.getPackageManager(), context3.getPackageName())).lastModified();
                    if (jC == 0) {
                        jC = System.currentTimeMillis();
                    }
                }
            }
            hjVar2.b.edit().putLong(ChannelCodes.ITALIAN, jC).apply();
        }
        aVar4.c = Long.valueOf(jC);
        int iB = this.c.f.b();
        this.b.e = Integer.valueOf(a(7, iB));
        this.b.f = Integer.valueOf(a(30, iB));
        int iB2 = this.c.h.b();
        if (iB2 > 0) {
            this.b.h = Integer.valueOf(iB2);
        }
        long jA = this.c.i.a();
        if (jA > 0) {
            this.b.i = Long.valueOf(jA);
        }
        long jA2 = this.c.j.a();
        if (jA2 > 0) {
            this.b.j = Long.valueOf(jA2);
        }
        long jA3 = this.c.k.a();
        if (jA3 > 0) {
            this.b.k = Long.valueOf(jA3);
        }
        String strA3 = this.c.l.a();
        if (strA3 != null) {
            this.b.l = strA3;
        }
        int iB3 = this.c.m.b();
        if (iB3 > 0) {
            this.b.m = Integer.valueOf(iB3);
        }
        double dA = this.c.n.a();
        if (dA != 0.0d) {
            this.b.n = Double.valueOf(dA);
        }
        long jA4 = this.c.o.a();
        if (jA4 > 0) {
            this.b.o = Long.valueOf(jA4);
        }
        double dA2 = this.c.p.a();
        if (dA2 != 0.0d) {
            this.b.p = Double.valueOf(dA2);
        }
        String strA4 = this.c.g.a();
        if (strA4 != null) {
            try {
                ff ffVar = (ff) ff.c.a(Base64.decode(strA4, 2));
                this.b.g.clear();
                this.b.g.addAll(ffVar.d);
            } catch (IOException e) {
                this.c.g.c();
            } catch (IllegalArgumentException e2) {
                this.c.g.c();
            }
        }
        this.f.e = this.c.q.a();
        this.b.s = this.c.r.a();
        int iIntValue = this.c.s.a().intValue();
        this.b.t = iIntValue != -1 ? Integer.valueOf(iIntValue) : null;
        int iIntValue2 = this.c.t.a().intValue();
        this.b.u = iIntValue2 != -1 ? Integer.valueOf(iIntValue2) : null;
        this.b.v = this.c.u.a();
        this.b.w = this.c.v.a();
        this.b.x = this.c.w.a();
        this.b.y = this.c.x.a();
        this.b.z = this.c.y.a();
        String strA5 = this.c.z.a();
        if (strA5 != null) {
            try {
                fg fgVar = (fg) fg.c.a(Base64.decode(strA5, 2));
                this.b.A.clear();
                this.b.A.addAll(fgVar.d);
            } catch (IOException e3) {
                this.c.z.c();
            } catch (IllegalArgumentException e4) {
                this.c.z.c();
            }
        }
        String strA6 = this.c.A.a();
        boolean zBooleanValue = this.c.B.a().booleanValue();
        if (strA6 != null) {
            this.b.q = strA6;
            this.b.r = Boolean.valueOf(zBooleanValue);
        } else {
            this.b.q = null;
            this.b.r = null;
        }
        this.b.B = this.c.C.a();
    }

    private static int a(int i, int i2) {
        return Integer.bitCount(((1 << i) - 1) & i2);
    }

    public static synchronized hc a(Context context) {
        if (d == null) {
            d = new hc(context, hj.a(context));
        }
        return d;
    }

    private static String a(PackageManager packageManager, String str) {
        Object obj;
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            if (bundle != null && (obj = bundle.get("com.tapjoy.appstore")) != null) {
                return obj.toString().trim();
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    private void g() {
        this.c.g.a(Base64.encodeToString(ff.c.b(new ff(this.b.g)), 2));
    }

    final void a() {
        Window window;
        synchronized (this) {
            try {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) this.g.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                Activity activityA = gr.a();
                if (activityA != null && (window = activityA.getWindow()) != null) {
                    int i = displayMetrics.heightPixels;
                    Rect rect = new Rect();
                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                    displayMetrics.heightPixels = i - rect.top;
                }
                this.e.i = Integer.valueOf(displayMetrics.densityDpi);
                this.e.j = Integer.valueOf(displayMetrics.widthPixels);
                this.e.k = Integer.valueOf(displayMetrics.heightPixels);
            } catch (Exception e) {
            }
        }
    }

    public final boolean a(int i, String str) {
        boolean z = false;
        synchronized (this) {
            try {
                switch (i) {
                    case 1:
                        this.c.u.a(str);
                        z = jo.a(this.b.v, str) ? false : true;
                        if (z) {
                            this.b.v = str;
                        }
                        break;
                    case 2:
                        this.c.v.a(str);
                        z = jo.a(this.b.w, str) ? false : true;
                        if (z) {
                            this.b.w = str;
                        }
                        break;
                    case 3:
                        this.c.w.a(str);
                        z = jo.a(this.b.x, str) ? false : true;
                        if (z) {
                            this.b.x = str;
                        }
                        break;
                    case 4:
                        this.c.x.a(str);
                        z = jo.a(this.b.y, str) ? false : true;
                        if (z) {
                            this.b.y = str;
                        }
                        break;
                    case 5:
                        this.c.y.a(str);
                        z = jo.a(this.b.z, str) ? false : true;
                        if (z) {
                            this.b.z = str;
                        }
                        break;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public final boolean a(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.s.a(num);
            z = !jo.a(this.b.t, num);
            if (z) {
                this.b.t = num;
            }
        }
        return z;
    }

    public final boolean a(String str) {
        boolean z;
        synchronized (this) {
            this.c.q.a(str);
            if (str != null) {
                z = jo.a(this.f.e, str) ? false : true;
                this.f.e = str;
            } else {
                z = this.f.e != null;
                this.f.e = null;
            }
        }
        return z;
    }

    final boolean a(String str, long j, boolean z) {
        synchronized (this) {
            int size = this.b.g.size();
            for (int i = 0; i < size; i++) {
                fe feVar = (fe) this.b.g.get(i);
                if (feVar.f.equals(str)) {
                    if (!z) {
                        return false;
                    }
                    fe.a aVarB = feVar.b();
                    aVarB.d = Long.valueOf(j);
                    this.b.g.set(i, aVarB.b());
                    return true;
                }
            }
            this.b.g.add(new fe(str, Long.valueOf(j)));
            g();
            return true;
        }
    }

    public final boolean a(boolean z) {
        boolean z2;
        synchronized (this) {
            this.c.C.a(z);
            z2 = z != ((Boolean) jo.b(this.b.B, fh.r)).booleanValue();
            this.b.B = Boolean.valueOf(z);
        }
        return z2;
    }

    public final fb b() {
        fb fbVar;
        boolean z;
        synchronized (this) {
            this.e.l = Locale.getDefault().toString();
            this.e.m = TimeZone.getDefault().getID();
            boolean z2 = false;
            long jCurrentTimeMillis = System.currentTimeMillis() - 259200000;
            Iterator it = this.b.g.iterator();
            while (it.hasNext()) {
                if (((fe) it.next()).g.longValue() <= jCurrentTimeMillis) {
                    it.remove();
                    z = true;
                } else {
                    z = z2;
                }
                z2 = z;
            }
            if (z2) {
                g();
            }
            fbVar = new fb(this.e.b(), this.f.b(), this.b.b());
        }
        return fbVar;
    }

    public final boolean b(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.t.a(num);
            z = !jo.a(this.b.u, num);
            if (z) {
                this.b.u = num;
            }
        }
        return z;
    }

    public final boolean b(String str) {
        boolean z;
        synchronized (this) {
            this.c.r.a(str);
            z = !jo.a(this.b.s, str);
            if (z) {
                this.b.s = str;
            }
        }
        return z;
    }

    final String c() {
        String strA;
        synchronized (this) {
            strA = this.c.d.a();
        }
        return strA;
    }

    public final boolean c(String str) {
        synchronized (this) {
            for (int size = this.b.g.size() - 1; size >= 0; size--) {
                fe feVar = (fe) this.b.g.get(size);
                if (feVar.f.equals(str)) {
                    fe.a aVarB = feVar.b();
                    aVarB.e = Long.valueOf(System.currentTimeMillis());
                    this.b.g.set(size, aVarB.b());
                    g();
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0127 A[Catch: all -> 0x00ff, LOOP:0: B:23:0x011f->B:25:0x0127, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0005, B:7:0x0030, B:8:0x004c, B:9:0x005a, B:11:0x005c, B:12:0x0093, B:13:0x0096, B:14:0x00a1, B:17:0x00a8, B:31:0x0156, B:32:0x015a, B:21:0x0102, B:23:0x011f, B:25:0x0127, B:26:0x012f, B:33:0x015f), top: B:35:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x014e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0151  */
    @Nullable
    public final fc d() {
        Calendar calendar;
        long timeInMillis;
        int i;
        int i2;
        fc fcVar = null;
        int i3 = 1;
        synchronized (this) {
            Calendar calendar2 = Calendar.getInstance();
            int i4 = calendar2.get(5) + (calendar2.get(1) * 10000) + (calendar2.get(2) * 100) + 100;
            int iIntValue = this.c.e.a().intValue();
            if (iIntValue != i4) {
                if (iIntValue == 0) {
                    this.b.e = 1;
                    this.b.f = 1;
                    fcVar = new fc("fq7_0_1", "fq30_0_1", null);
                } else {
                    int iIntValue2 = this.c.f.a().intValue();
                    int iA = a(7, iIntValue2);
                    int iA2 = a(30, iIntValue2);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.set(iIntValue / 10000, ((iIntValue / 100) % 100) - 1, iIntValue % 100);
                    int iSignum = Integer.signum(calendar2.get(1) - calendar3.get(1));
                    switch (iSignum) {
                        case -1:
                            calendar = (Calendar) calendar3.clone();
                            calendar.set(calendar2.get(1), calendar2.get(2), calendar2.get(5));
                            timeInMillis = calendar3.getTimeInMillis();
                            i = 0;
                            while (calendar.getTimeInMillis() < timeInMillis) {
                                calendar.add(5, 1);
                                i++;
                            }
                            if (iSignum > 0) {
                                i2 = -i;
                            } else {
                                i2 = i;
                            }
                            break;
                        case 0:
                        default:
                            i2 = calendar2.get(6) - calendar3.get(6);
                            break;
                        case 1:
                            calendar = (Calendar) calendar2.clone();
                            calendar.set(calendar3.get(1), calendar3.get(2), calendar3.get(5));
                            timeInMillis = calendar2.getTimeInMillis();
                            i = 0;
                            while (calendar.getTimeInMillis() < timeInMillis) {
                                calendar.add(5, 1);
                                i++;
                            }
                            if (iSignum > 0) {
                                i2 = -i;
                            } else {
                                i2 = i;
                            }
                            break;
                    }
                    i3 = (Math.abs(i2) >= 30 ? 0 : i2 >= 0 ? iIntValue2 << i2 : iIntValue2 >> (-i2)) | 1;
                    int iA3 = a(7, i3);
                    int iA4 = a(30, i3);
                    this.b.e = Integer.valueOf(iA3);
                    this.b.f = Integer.valueOf(iA4);
                    fcVar = new fc("fq7_" + iA + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + iA3, "fq30_" + iA2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + iA4, null);
                }
                this.c.e.a(i4);
                this.c.f.a(i3);
            }
        }
        return fcVar;
    }

    public final Set e() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.b.A);
        }
        return hashSet;
    }

    public final boolean f() {
        return ((Boolean) jo.b(this.b.B, fh.r)).booleanValue();
    }
}
