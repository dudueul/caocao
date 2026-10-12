package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzz {

    @GuardedBy("MessengerIpcClient.class")
    private static zzz zzbk;
    private final ScheduledExecutorService zzbl;

    @GuardedBy("this")
    private zzab zzbm = new zzab(this);

    @GuardedBy("this")
    private int zzbn = 1;
    private final Context zzv;

    @VisibleForTesting
    private zzz(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbl = scheduledExecutorService;
        this.zzv = context.getApplicationContext();
    }

    private final synchronized <T> Task<T> zza(zzai<T> zzaiVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(zzaiVar);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(strValueOf).length() + 9).append("Queueing ").append(strValueOf).toString());
        }
        if (!this.zzbm.zzb(zzaiVar)) {
            this.zzbm = new zzab(this);
            this.zzbm.zzb(zzaiVar);
        }
        return zzaiVar.zzbx.getTask();
    }

    public static synchronized zzz zzc(Context context) {
        if (zzbk == null) {
            zzbk = new zzz(context, Executors.newSingleThreadScheduledExecutor());
        }
        return zzbk;
    }

    private final synchronized int zzw() {
        int i;
        i = this.zzbn;
        this.zzbn = i + 1;
        return i;
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzah(zzw(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzak(zzw(), 1, bundle));
    }
}
