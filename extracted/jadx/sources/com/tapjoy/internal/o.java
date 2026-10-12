package com.tapjoy.internal;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class o extends m {
    private final String c;

    public o(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
        this.c = null;
    }

    public final SharedPreferences.Editor a(SharedPreferences.Editor editor, String str) {
        return str != null ? editor.putString(this.b, str) : editor.remove(this.b);
    }

    public final String a() {
        return this.a.getString(this.b, this.c);
    }

    public final void a(String str) {
        this.a.edit().putString(this.b, str).apply();
    }
}
