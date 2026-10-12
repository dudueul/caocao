package com.tapjoy.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dk implements di {
    private final di a;

    public dk(di diVar) {
        this.a = diVar;
    }

    @Override // com.tapjoy.internal.di
    public final JSONObject a(View view) {
        return dm.a(0, 0, 0, 0);
    }

    @Override // com.tapjoy.internal.di
    public final void a(View view, JSONObject jSONObject, di.a aVar, boolean z) {
        View rootView;
        ArrayList arrayList = new ArrayList();
        dd ddVarA = dd.a();
        if (ddVarA != null) {
            Collection collectionUnmodifiableCollection = Collections.unmodifiableCollection(ddVarA.b);
            IdentityHashMap identityHashMap = new IdentityHashMap((collectionUnmodifiableCollection.size() * 2) + 3);
            Iterator it = collectionUnmodifiableCollection.iterator();
            while (it.hasNext()) {
                View viewC = ((cz) it.next()).c();
                if (viewC != null && dp.b(viewC) && (rootView = viewC.getRootView()) != null && !identityHashMap.containsKey(rootView)) {
                    identityHashMap.put(rootView, rootView);
                    float fA = dp.a(rootView);
                    int size = arrayList.size();
                    while (size > 0 && dp.a((View) arrayList.get(size - 1)) > fA) {
                        size--;
                    }
                    arrayList.add(size, rootView);
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            aVar.a((View) it2.next(), this.a, jSONObject);
        }
    }
}
