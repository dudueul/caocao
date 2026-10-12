package com.tapjoy.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dw {
    final HashMap a = new HashMap();
    final HashMap b = new HashMap();
    final HashMap c = new HashMap();
    final HashSet d = new HashSet();
    final HashSet e = new HashSet();
    final HashSet f = new HashSet();
    final HashMap g = new HashMap();
    boolean h;

    final void a(cz czVar) {
        Iterator it = czVar.b.iterator();
        while (it.hasNext()) {
            View view = (View) ((dq) it.next()).get();
            if (view != null) {
                ArrayList arrayList = (ArrayList) this.c.get(view);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.c.put(view, arrayList);
                }
                arrayList.add(czVar.f);
            }
        }
    }
}
