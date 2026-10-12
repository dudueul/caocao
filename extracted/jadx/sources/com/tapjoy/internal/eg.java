package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum eg {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);

    final int e;

    eg(int i) {
        this.e = i;
    }

    public final ej a() {
        switch (this) {
            case VARINT:
                return ej.j;
            case FIXED32:
                return ej.g;
            case FIXED64:
                return ej.l;
            case LENGTH_DELIMITED:
                return ej.q;
            default:
                throw new AssertionError();
        }
    }
}
