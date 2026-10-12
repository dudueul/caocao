package com.tapjoy.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jq {
    public static String a(@Nullable String str) {
        return str == null ? "" : str;
    }

    @Nullable
    public static String b(@Nullable String str) {
        if (c(str)) {
            return null;
        }
        return str;
    }

    public static boolean c(@Nullable String str) {
        return str == null || str.length() == 0;
    }
}
