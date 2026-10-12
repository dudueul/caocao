package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class InstantApps {
    private static Context zzaay;
    private static Boolean zzaaz;

    public static synchronized boolean isInstantApp(Context context) {
        boolean zBooleanValue;
        Context applicationContext = context.getApplicationContext();
        if (zzaay == null || zzaaz == null || zzaay != applicationContext) {
            zzaaz = null;
            if (PlatformVersion.isAtLeastO()) {
                zzaaz = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    zzaaz = true;
                } catch (ClassNotFoundException e) {
                    zzaaz = false;
                }
            }
            zzaay = applicationContext;
            zBooleanValue = zzaaz.booleanValue();
        } else {
            zBooleanValue = zzaaz.booleanValue();
        }
        return zBooleanValue;
    }
}
