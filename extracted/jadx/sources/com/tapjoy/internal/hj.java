package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hj {
    public final o A;
    public final h B;
    final h C;
    final o D;
    final Context a;
    final SharedPreferences b;
    final o c;
    final o d;
    final k e;
    final k f;
    final o g;
    final k h;
    final l i;
    final l j;
    final l k;
    final o l;
    final k m;
    final i n;
    final l o;
    final i p;
    final o q;
    final o r;
    final k s;
    final k t;
    final o u;
    final o v;
    final o w;
    final o x;
    final o y;
    final o z;

    private hj(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = applicationContext.getSharedPreferences("fiverocks", 0);
        this.c = new o(this.b, "sdk");
        this.d = new o(this.b, "ir");
        this.e = new k(this.b, "fql", 0);
        this.f = new k(this.b, "fq", 0);
        this.g = new o(this.b, "push");
        this.h = new k(this.b, "ss", 0);
        this.i = new l(this.b, "std");
        this.j = new l(this.b, "slt");
        this.k = new l(this.b, "sld");
        this.l = new o(this.b, "ptc");
        this.m = new k(this.b, "pc", 0);
        this.n = new i(this.b, "ptp");
        this.o = new l(this.b, "lpt");
        this.p = new i(this.b, "plp");
        this.q = new o(this.b, "adv");
        this.r = new o(this.b, "ui");
        this.s = new k(this.b, "ul", -1);
        this.t = new k(this.b, "uf", -1);
        this.u = new o(this.b, TapjoyConstants.TJC_USER_VARIABLE_1);
        this.v = new o(this.b, TapjoyConstants.TJC_USER_VARIABLE_2);
        this.w = new o(this.b, TapjoyConstants.TJC_USER_VARIABLE_3);
        this.x = new o(this.b, TapjoyConstants.TJC_USER_VARIABLE_4);
        this.y = new o(this.b, TapjoyConstants.TJC_USER_VARIABLE_5);
        this.z = new o(this.b, "utags");
        this.A = new o(this.b, "idfa");
        this.B = new h(this.b, "idfa.optout");
        this.C = new h(this.b, "push.optout");
        this.D = new o(this.b, "appId");
    }

    public static hj a(Context context) {
        return new hj(context);
    }

    final SharedPreferences.Editor a() {
        return this.b.edit();
    }

    public final void a(boolean z) {
        n.a(this.b, "gcm.onServer", z);
    }

    public final String b() {
        String string = this.b.getString("ir", null);
        if (string != null) {
            if (string.length() > 0) {
                return string;
            }
            return null;
        }
        File file = new File(gz.c(this.a), "referrer");
        if (file.exists()) {
            try {
                string = bg.a(file, ak.c);
            } catch (IOException e) {
            }
        }
        this.b.edit().putString("ir", string != null ? string : "").apply();
        if (string == null || string.length() <= 0) {
            return null;
        }
        return string;
    }
}
