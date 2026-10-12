package com.google.android.gms.drive;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Deprecated
public final class zzn extends ExecutionOptions {
    private boolean zzar;

    private zzn(@Nullable String str, boolean z, int i, boolean z2) {
        super(str, z, i);
        this.zzar = z2;
    }

    public static zzn zza(@Nullable ExecutionOptions executionOptions) {
        zzp zzpVar = new zzp();
        if (executionOptions != null) {
            zzpVar.setConflictStrategy(executionOptions.zzm());
            zzpVar.setNotifyOnCompletion(executionOptions.zzl());
            String strZzk = executionOptions.zzk();
            if (strZzk != null) {
                zzpVar.setTrackingTag(strZzk);
            }
        }
        return (zzn) zzpVar.build();
    }

    public final boolean zzo() {
        return this.zzar;
    }
}
