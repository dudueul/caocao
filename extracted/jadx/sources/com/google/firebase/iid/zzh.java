package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzh implements ServiceConnection {
    private boolean zzaa;
    private final Context zzv;
    private final Intent zzw;
    private final ScheduledExecutorService zzx;
    private final Queue<zzd> zzy;
    private zzf zzz;

    public zzh(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0));
    }

    @VisibleForTesting
    private zzh(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzy = new ArrayDeque();
        this.zzaa = false;
        this.zzv = context.getApplicationContext();
        this.zzw = new Intent(str).setPackage(this.zzv.getPackageName());
        this.zzx = scheduledExecutorService;
    }

    private final synchronized void zzc() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "flush queue called");
        }
        while (!this.zzy.isEmpty()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "found intent to be delivered");
            }
            if (this.zzz == null || !this.zzz.isBinderAlive()) {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    Log.d("EnhancedIntentService", new StringBuilder(39).append("binder is dead. start connection? ").append(!this.zzaa).toString());
                }
                if (this.zzaa) {
                    break;
                }
                this.zzaa = true;
                try {
                    if (!ConnectionTracker.getInstance().bindService(this.zzv, this.zzw, this, 65)) {
                        Log.e("EnhancedIntentService", "binding to the service failed");
                        while (!this.zzy.isEmpty()) {
                            this.zzy.poll().finish();
                        }
                        break;
                    }
                    break;
                } catch (SecurityException e) {
                    Log.e("EnhancedIntentService", "Exception while binding the service", e);
                }
            } else {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                }
                this.zzz.zza(this.zzy.poll());
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.zzaa = false;
            this.zzz = (zzf) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String strValueOf = String.valueOf(componentName);
                Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(strValueOf).length() + 20).append("onServiceConnected: ").append(strValueOf).toString());
            }
            zzc();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String strValueOf = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(strValueOf).length() + 23).append("onServiceDisconnected: ").append(strValueOf).toString());
        }
        zzc();
    }

    public final synchronized void zza(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.zzy.add(new zzd(intent, pendingResult, this.zzx));
        zzc();
    }
}
