package com.tapjoy.internal;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dl implements di {
    private final int[] a = new int[2];

    @Override // com.tapjoy.internal.di
    public final JSONObject a(View view) {
        if (view == null) {
            return dm.a(0, 0, 0, 0);
        }
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.a);
        return dm.a(this.a[0], this.a[1], width, height);
    }

    @Override // com.tapjoy.internal.di
    public final void a(View view, JSONObject jSONObject, di.a aVar, boolean z) {
        int i = 0;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || Build.VERSION.SDK_INT < 21) {
                while (i < viewGroup.getChildCount()) {
                    aVar.a(viewGroup.getChildAt(i), this, jSONObject);
                    i++;
                }
                return;
            }
            HashMap map = new HashMap();
            while (true) {
                int i2 = i;
                if (i2 >= viewGroup.getChildCount()) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i2);
                ArrayList arrayList = (ArrayList) map.get(Float.valueOf(childAt.getZ()));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(Float.valueOf(childAt.getZ()), arrayList);
                }
                arrayList.add(childAt);
                i = i2 + 1;
            }
            ArrayList arrayList2 = new ArrayList(map.keySet());
            Collections.sort(arrayList2);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((ArrayList) map.get((Float) it.next())).iterator();
                while (it2.hasNext()) {
                    aVar.a((View) it2.next(), this, jSONObject);
                }
            }
        }
    }
}
