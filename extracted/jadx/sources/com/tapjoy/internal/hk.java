package com.tapjoy.internal;

import android.os.SystemClock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class hk {
    protected static a a;
    private static hk b;

    public static class a {
        public final String a;
        public final String b;
        public final long c = SystemClock.elapsedRealtime();
        public final fi d = new fi(60000);

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    protected static void a(hk hkVar) {
        synchronized (hk.class) {
            b = hkVar;
            a aVar = a;
            if (aVar != null) {
                a = null;
                hkVar.a(aVar);
            }
        }
    }

    public static void a(String str, String str2) {
        synchronized (hk.class) {
            a aVar = new a(str, str2);
            if (b != null) {
                a = null;
                b.a(aVar);
            } else {
                a = aVar;
            }
        }
    }

    public static boolean c() {
        if (b != null && b.b()) {
            return true;
        }
        a aVar = a;
        return (aVar == null || aVar.d.a()) ? false : true;
    }

    public abstract void a(a aVar);

    public abstract boolean b();
}
