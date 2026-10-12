package com.tapjoy.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jo {
    public static boolean a(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Object b(@Nullable Object obj, @Nullable Object obj2) {
        return obj != null ? obj : jp.a(obj2);
    }
}
