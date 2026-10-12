package com.tapjoy.internal;

import android.support.annotation.VisibleForTesting;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ed implements dy.b {
    final dz a;
    private JSONObject b;

    public ed(dz dzVar) {
        this.a = dzVar;
    }

    @Override // com.tapjoy.internal.dy.b
    @VisibleForTesting
    public final JSONObject a() {
        return this.b;
    }

    @Override // com.tapjoy.internal.dy.b
    @VisibleForTesting
    public final void a(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public final void b() {
        this.a.a(new ea(this));
    }
}
