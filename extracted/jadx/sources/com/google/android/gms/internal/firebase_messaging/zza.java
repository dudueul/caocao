package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zza {
    private static final zzb zza;
    private static final int zzb;

    /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_messaging.zza$zza, reason: collision with other inner class name */
    static final class C0014zza extends zzb {
        C0014zza() {
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzb
        public final void zza(Throwable th, Throwable th2) {
        }
    }

    static {
        zzb c0014zza;
        Integer numZza = null;
        try {
            numZza = zza();
            if (numZza == null || numZza.intValue() < 19) {
                c0014zza = !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new zze() : new C0014zza();
            } else {
                c0014zza = new zzf();
            }
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = C0014zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            c0014zza = new C0014zza();
        }
        zza = c0014zza;
        zzb = numZza == null ? 1 : numZza.intValue();
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }
}
