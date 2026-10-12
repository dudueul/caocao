package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ga {
    private static final ga b;
    private static ga c;
    public final fy a = new fy();
    private Context d;

    static {
        ga gaVar = new ga();
        b = gaVar;
        c = gaVar;
    }

    ga() {
    }

    public static ga a() {
        return c;
    }

    public static fy b() {
        return c.a;
    }

    public final synchronized void a(Context context) {
        if (context != null) {
            if (this.d == null) {
                this.d = context;
                SharedPreferences sharedPreferencesC = c();
                String string = c().getString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, null);
                if (string != null) {
                    try {
                        bn bnVarB = bn.b(string);
                        try {
                            Map mapD = bnVarB.d();
                            bnVarB.close();
                            this.a.a(mapD);
                        } catch (Throwable th) {
                            bnVarB.close();
                            throw th;
                        }
                    } catch (Exception e) {
                        sharedPreferencesC.edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).apply();
                    }
                }
                Observer observer = new Observer() { // from class: com.tapjoy.internal.ga.1
                    @Override // java.util.Observer
                    public final void update(Observable observable, Object obj) {
                        Object objCast;
                        gf.a(ga.this.a.a("usage_tracking_enabled", false));
                        Iterator it = ga.this.a.b.iterator();
                        while (it.hasNext()) {
                            Object objA = ((gj.a) it.next()).a("usage_tracking_exclude");
                            if (objA != null && List.class.isInstance(objA)) {
                                objCast = List.class.cast(objA);
                                gf.a((Collection) objCast);
                            }
                        }
                        objCast = null;
                        gf.a((Collection) objCast);
                    }
                };
                this.a.addObserver(observer);
                observer.update(this.a, null);
            }
        }
    }

    public final SharedPreferences c() {
        return this.d.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
    }
}
