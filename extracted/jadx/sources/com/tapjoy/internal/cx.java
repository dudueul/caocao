package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class cx {
    public final String a;
    public final String b;

    private cx(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static cx a(String str, String str2) {
        Cdo.a(str, "Name is null or empty");
        Cdo.a(str2, "Version is null or empty");
        return new cx(str, str2);
    }
}
