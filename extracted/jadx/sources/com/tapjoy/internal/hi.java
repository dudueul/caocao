package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hi {
    final o a;
    public final o b;
    private final SharedPreferences c;

    public hi(Context context) {
        this.c = context.getApplicationContext().getSharedPreferences("fiverocks", 0);
        this.a = new o(this.c, "noMoreToday.date");
        this.b = new o(this.c, "noMoreToday.actionIds");
        b();
    }

    static String a() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public final void b() {
        String strA = this.a.a();
        if (strA == null || a().equals(strA)) {
            return;
        }
        this.a.a(null);
        this.b.a(null);
    }
}
