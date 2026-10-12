package com.tapjoy.internal;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class h extends m {
    private final boolean c;

    public h(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
        this.c = false;
    }

    public final Boolean a() {
        return Boolean.valueOf(this.a.getBoolean(this.b, this.c));
    }

    public final void a(boolean z) {
        this.a.edit().putBoolean(this.b, z).apply();
    }
}
