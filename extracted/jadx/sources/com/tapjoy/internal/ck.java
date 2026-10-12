package com.tapjoy.internal;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ck {
    private static cl a = new cl();

    public static String a() {
        return "1.2.19-Tapjoy";
    }

    public static boolean a(Context context) {
        cl clVar = a;
        Context applicationContext = context.getApplicationContext();
        Cdo.a(applicationContext, "Application Context cannot be null");
        if (!clVar.a) {
            clVar.a = true;
            dh dhVarA = dh.a();
            dhVarA.b = new cp(new Handler(), applicationContext, new cm(), dhVarA);
            de.a().a = applicationContext.getApplicationContext();
            dm.a(applicationContext);
            df.a().a = applicationContext != null ? applicationContext.getApplicationContext() : null;
        }
        return true;
    }

    public static boolean b() {
        return a.a;
    }
}
