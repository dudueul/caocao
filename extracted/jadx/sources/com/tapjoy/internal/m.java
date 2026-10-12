package com.tapjoy.internal;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class m {
    protected SharedPreferences a;
    protected String b;

    public m(SharedPreferences sharedPreferences, String str) {
        this.a = sharedPreferences;
        this.b = str;
    }

    public final void c() {
        this.a.edit().remove(this.b).apply();
    }
}
