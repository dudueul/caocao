package com.tapjoy.internal;

import java.util.Collections;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class eb extends dx {
    public eb(dy.b bVar, HashSet hashSet, JSONObject jSONObject, long j) {
        super(bVar, hashSet, jSONObject, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.dy, android.os.AsyncTask
    /* JADX INFO: renamed from: a */
    public final void onPostExecute(String str) {
        dd ddVarA = dd.a();
        if (ddVarA != null) {
            for (cz czVar : Collections.unmodifiableCollection(ddVarA.a)) {
                if (this.a.contains(czVar.f)) {
                    ds dsVar = czVar.c;
                    if (this.c >= dsVar.e && dsVar.d != ds.a.c) {
                        dsVar.d = ds.a.c;
                        dg.a().b(dsVar.c(), str);
                    }
                }
            }
        }
        super.onPostExecute(str);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.b.toString();
    }
}
