package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum cv {
    HTML(TJAdUnitConstants.String.HTML),
    NATIVE("native");

    private final String c;

    cv(String str) {
        this.c = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.c;
    }
}
