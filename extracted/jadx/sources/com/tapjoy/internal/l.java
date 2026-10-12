package com.tapjoy.internal;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class l extends m {
    private final long c;

    public l(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
        this.c = 0L;
    }

    public final long a() {
        return this.a.getLong(this.b, this.c);
    }

    public final SharedPreferences.Editor a(SharedPreferences.Editor editor) {
        return editor.remove(this.b);
    }

    public final SharedPreferences.Editor a(SharedPreferences.Editor editor, long j) {
        return editor.putLong(this.b, j);
    }

    public final void a(long j) {
        this.a.edit().putLong(this.b, j).apply();
    }
}
