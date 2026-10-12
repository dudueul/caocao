package com.tapjoy.internal;

import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class cs {
    public static cs a(ct ctVar, cu cuVar) {
        if (!ck.b()) {
            throw new IllegalStateException("Method called before OM SDK activation");
        }
        Cdo.a(ctVar, "AdSessionConfiguration is null");
        Cdo.a(cuVar, "AdSessionContext is null");
        return new cz(ctVar, cuVar);
    }

    public abstract void a();

    public abstract void a(View view);

    public abstract void b();
}
