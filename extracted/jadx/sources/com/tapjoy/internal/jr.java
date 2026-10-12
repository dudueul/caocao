package com.tapjoy.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jr {
    public static RuntimeException a(Throwable th) throws Throwable {
        Throwable th2 = (Throwable) jp.a(th);
        a(th2, Error.class);
        a(th2, RuntimeException.class);
        throw new RuntimeException(th);
    }

    private static void a(@Nullable Throwable th, Class cls) throws Throwable {
        if (th != null && cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }
}
