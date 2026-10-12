package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum cw {
    NATIVE("native"),
    JAVASCRIPT("javascript"),
    NONE("none");

    private final String d;

    cw(String str) {
        this.d = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.d;
    }
}
