package com.naver.glink.android.sdk.ui.streaming;

/* JADX INFO: compiled from: CheckResult.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    public final boolean a;
    public final String b;

    private a(boolean z, String str) {
        this.a = z;
        this.b = str;
    }

    public static a a() {
        return new a(true, null);
    }

    public static a a(String str) {
        return new a(false, str);
    }
}
