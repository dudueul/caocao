package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class b {
    private static Application a;
    private static int b;
    private static final by c = new by();
    private static final Set d = Collections.synchronizedSet(new ay());
    private static final by e = new by();

    @Nullable
    public static Activity a() {
        Activity activity = (Activity) e.a();
        return activity == null ? c() : activity;
    }

    public static void a(Activity activity) {
        c.a(activity);
    }

    public static synchronized void a(Application application) {
        if (a != application) {
            a = application;
        }
    }

    public static void b(Activity activity) {
        b++;
        c.a(activity);
        d.add(activity);
    }

    public static boolean b() {
        return b > 0;
    }

    @Nullable
    public static Activity c() {
        Activity activity = (Activity) c.a();
        if (activity == null) {
            synchronized (d) {
                activity = (Activity) jt.a(d.iterator());
            }
        }
        return activity;
    }

    public static void c(Activity activity) {
        b--;
        c.a = null;
        d.remove(activity);
        if (b < 0) {
            b = 0;
        }
    }
}
