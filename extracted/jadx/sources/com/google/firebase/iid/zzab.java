package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzab implements ServiceConnection {

    @GuardedBy("this")
    int state;
    final Messenger zzbo;
    zzag zzbp;

    @GuardedBy("this")
    final Queue<zzai<?>> zzbq;

    @GuardedBy("this")
    final SparseArray<zzai<?>> zzbr;
    final /* synthetic */ zzz zzbs;

    private zzab(zzz zzzVar) {
        this.zzbs = zzzVar;
        this.state = 0;
        this.zzbo = new Messenger(new Handler(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzac
            private final zzab zzbt;

            {
                this.zzbt = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzbt.zza(message);
            }
        }));
        this.zzbq = new ArrayDeque();
        this.zzbr = new SparseArray<>();
    }

    private final void zzx() {
        this.zzbs.zzbl.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzae
            private final zzab zzbt;

            {
                this.zzbt = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzai<?> zzaiVarPoll;
                final zzab zzabVar = this.zzbt;
                while (true) {
                    synchronized (zzabVar) {
                        if (zzabVar.state != 2) {
                            return;
                        }
                        if (zzabVar.zzbq.isEmpty()) {
                            zzabVar.zzy();
                            return;
                        } else {
                            zzaiVarPoll = zzabVar.zzbq.poll();
                            zzabVar.zzbr.put(zzaiVarPoll.zzbw, zzaiVarPoll);
                            zzabVar.zzbs.zzbl.schedule(new Runnable(zzabVar, zzaiVarPoll) { // from class: com.google.firebase.iid.zzaf
                                private final zzab zzbt;
                                private final zzai zzbu;

                                {
                                    this.zzbt = zzabVar;
                                    this.zzbu = zzaiVarPoll;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zzbt.zza(this.zzbu.zzbw);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String strValueOf = String.valueOf(zzaiVarPoll);
                        Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(strValueOf).length() + 8).append("Sending ").append(strValueOf).toString());
                    }
                    Context context = zzabVar.zzbs.zzv;
                    Messenger messenger = zzabVar.zzbo;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = zzaiVarPoll.what;
                    messageObtain.arg1 = zzaiVarPoll.zzbw;
                    messageObtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzaiVarPoll.zzaa());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", zzaiVarPoll.zzby);
                    messageObtain.setData(bundle);
                    try {
                        zzabVar.zzbp.send(messageObtain);
                    } catch (RemoteException e) {
                        zzabVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
        } else {
            try {
                this.zzbp = new zzag(iBinder);
                this.state = 2;
                zzx();
            } catch (RemoteException e) {
                zza(0, e.getMessage());
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    final synchronized void zza(int i) {
        zzai<?> zzaiVar = this.zzbr.get(i);
        if (zzaiVar != null) {
            Log.w("MessengerIpcClient", new StringBuilder(31).append("Timing out request: ").append(i).toString());
            this.zzbr.remove(i);
            zzaiVar.zza(new zzaj(3, "Timed out waiting for response"));
            zzy();
        }
    }

    final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(this.zzbs.zzv, this);
                zzaj zzajVar = new zzaj(i, str);
                Iterator<zzai<?>> it = this.zzbq.iterator();
                while (it.hasNext()) {
                    it.next().zza(zzajVar);
                }
                this.zzbq.clear();
                for (int i2 = 0; i2 < this.zzbr.size(); i2++) {
                    this.zzbr.valueAt(i2).zza(zzajVar);
                }
                this.zzbr.clear();
                break;
            case 3:
                this.state = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
        }
    }

    final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", new StringBuilder(41).append("Received response to request: ").append(i).toString());
        }
        synchronized (this) {
            zzai<?> zzaiVar = this.zzbr.get(i);
            if (zzaiVar == null) {
                Log.w("MessengerIpcClient", new StringBuilder(50).append("Received response for unknown request: ").append(i).toString());
            } else {
                this.zzbr.remove(i);
                zzy();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzaiVar.zza(new zzaj(4, "Not supported by GmsCore"));
                } else {
                    zzaiVar.zzb(data);
                }
            }
        }
        return true;
    }

    final synchronized boolean zzb(zzai zzaiVar) {
        boolean z = true;
        synchronized (this) {
            switch (this.state) {
                case 0:
                    this.zzbq.add(zzaiVar);
                    Preconditions.checkState(this.state == 0);
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                    }
                    this.state = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    if (!ConnectionTracker.getInstance().bindService(this.zzbs.zzv, intent, this, 1)) {
                        zza(0, "Unable to bind to service");
                    } else {
                        this.zzbs.zzbl.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzad
                            private final zzab zzbt;

                            {
                                this.zzbt = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzbt.zzz();
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    break;
                case 1:
                    this.zzbq.add(zzaiVar);
                    break;
                case 2:
                    this.zzbq.add(zzaiVar);
                    zzx();
                    break;
                case 3:
                case 4:
                    z = false;
                    break;
                default:
                    throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
            }
        }
        return z;
    }

    final synchronized void zzy() {
        if (this.state == 2 && this.zzbq.isEmpty() && this.zzbr.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzbs.zzv, this);
        }
    }

    final synchronized void zzz() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }
}
