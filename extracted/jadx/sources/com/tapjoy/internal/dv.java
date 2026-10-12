package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dv implements di.a {
    private int e;
    private long i;
    private static dv c = new dv();
    public static Handler a = new Handler(Looper.getMainLooper());
    private static Handler d = null;
    private static final Runnable j = new Runnable() { // from class: com.tapjoy.internal.dv.2
        @Override // java.lang.Runnable
        public final void run() {
            dv.b(dv.a());
        }
    };
    private static final Runnable k = new Runnable() { // from class: com.tapjoy.internal.dv.3
        @Override // java.lang.Runnable
        public final void run() {
            if (dv.d != null) {
                dv.d.post(dv.j);
                dv.d.postDelayed(dv.k, 200L);
            }
        }
    };
    public List b = new ArrayList();
    private dw g = new dw();
    private dj f = new dj();
    private ed h = new ed(new dz());

    dv() {
    }

    public static dv a() {
        return c;
    }

    private void a(long j2) {
        if (this.b.size() > 0) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                it.next();
                TimeUnit.NANOSECONDS.toMillis(j2);
            }
        }
    }

    private void a(View view, di diVar, JSONObject jSONObject, int i) {
        diVar.a(view, jSONObject, this, i == ee.a);
    }

    public static void b() {
        if (d == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            d = handler;
            handler.post(j);
            d.postDelayed(k, 200L);
        }
    }

    static /* synthetic */ void b(dv dvVar) {
        String strC;
        dvVar.e = 0;
        dvVar.i = System.nanoTime();
        dw dwVar = dvVar.g;
        dd ddVarA = dd.a();
        if (ddVarA != null) {
            for (cz czVar : Collections.unmodifiableCollection(ddVarA.b)) {
                View viewC = czVar.c();
                if (czVar.d()) {
                    String str = czVar.f;
                    if (viewC != null) {
                        if (viewC.hasWindowFocus()) {
                            HashSet hashSet = new HashSet();
                            View view = viewC;
                            while (true) {
                                if (view == null) {
                                    dwVar.d.addAll(hashSet);
                                    strC = null;
                                    break;
                                }
                                strC = dp.c(view);
                                if (strC != null) {
                                    break;
                                }
                                hashSet.add(view);
                                Object parent = view.getParent();
                                view = parent instanceof View ? (View) parent : null;
                            }
                        } else {
                            strC = "noWindowFocus";
                        }
                        if (strC == null) {
                            dwVar.e.add(str);
                            dwVar.a.put(viewC, str);
                            dwVar.a(czVar);
                        } else {
                            dwVar.f.add(str);
                            dwVar.b.put(str, viewC);
                            dwVar.g.put(str, strC);
                        }
                    } else {
                        dwVar.f.add(str);
                        dwVar.g.put(str, "noAdView");
                    }
                }
            }
        }
        long jNanoTime = System.nanoTime();
        dk dkVar = dvVar.f.b;
        if (dvVar.g.f.size() > 0) {
            for (String str2 : dvVar.g.f) {
                JSONObject jSONObjectA = dkVar.a(null);
                View view2 = (View) dvVar.g.b.get(str2);
                dl dlVar = dvVar.f.a;
                String str3 = (String) dvVar.g.g.get(str2);
                if (str3 != null) {
                    JSONObject jSONObjectA2 = dlVar.a(view2);
                    dm.a(jSONObjectA2, str2);
                    dm.b(jSONObjectA2, str3);
                    dm.a(jSONObjectA, jSONObjectA2);
                }
                dm.a(jSONObjectA);
                HashSet hashSet2 = new HashSet();
                hashSet2.add(str2);
                ed edVar = dvVar.h;
                edVar.a.a(new eb(edVar, hashSet2, jSONObjectA, jNanoTime));
            }
        }
        if (dvVar.g.e.size() > 0) {
            JSONObject jSONObjectA3 = dkVar.a(null);
            dvVar.a(null, dkVar, jSONObjectA3, ee.a);
            dm.a(jSONObjectA3);
            ed edVar2 = dvVar.h;
            edVar2.a.a(new ec(edVar2, dvVar.g.e, jSONObjectA3, jNanoTime));
        } else {
            dvVar.h.b();
        }
        dw dwVar2 = dvVar.g;
        dwVar2.a.clear();
        dwVar2.b.clear();
        dwVar2.c.clear();
        dwVar2.d.clear();
        dwVar2.e.clear();
        dwVar2.f.clear();
        dwVar2.g.clear();
        dwVar2.h = false;
        dvVar.a(System.nanoTime() - dvVar.i);
    }

    public static void c() {
        if (d != null) {
            d.removeCallbacks(k);
            d = null;
        }
    }

    @Override // com.tapjoy.internal.di.a
    public final void a(View view, di diVar, JSONObject jSONObject) {
        int i;
        String str;
        boolean z;
        ArrayList arrayList;
        if (dp.c(view) == null) {
            dw dwVar = this.g;
            if (dwVar.d.contains(view)) {
                i = ee.a;
            } else {
                i = dwVar.h ? ee.b : ee.c;
            }
            if (i != ee.c) {
                JSONObject jSONObjectA = diVar.a(view);
                dm.a(jSONObject, jSONObjectA);
                dw dwVar2 = this.g;
                if (dwVar2.a.size() == 0) {
                    str = null;
                } else {
                    str = (String) dwVar2.a.get(view);
                    if (str != null) {
                        dwVar2.a.remove(view);
                    }
                }
                if (str != null) {
                    dm.a(jSONObjectA, str);
                    this.g.h = true;
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    dw dwVar3 = this.g;
                    if (dwVar3.c.size() == 0) {
                        arrayList = null;
                    } else {
                        arrayList = (ArrayList) dwVar3.c.get(view);
                        if (arrayList != null) {
                            dwVar3.c.remove(view);
                            Collections.sort(arrayList);
                        }
                    }
                    if (arrayList != null) {
                        dm.a(jSONObjectA, arrayList);
                    }
                    a(view, diVar, jSONObjectA, i);
                }
                this.e++;
            }
        }
    }
}
