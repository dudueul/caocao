package com.google.gson.internal;

/* JADX INFO: renamed from: com.google.gson.internal.$Gson$Preconditions, reason: invalid class name */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
}
