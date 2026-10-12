package com.tapjoy.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class aq implements an {
    @Nullable
    protected abstract ao a(Object obj, boolean z);

    @Override // com.tapjoy.internal.an
    public final Object a(Object obj) {
        ao aoVarA = a(obj, false);
        if (aoVarA != null) {
            return aoVarA.a();
        }
        return null;
    }

    @Override // com.tapjoy.internal.an
    public void a(Object obj, Object obj2) {
        a(obj, true).a(obj2);
    }
}
