package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzz implements OnCompleteListener<Map<zzh<?>, String>> {
    private final /* synthetic */ zzw zzgu;
    private SignInConnectionListener zzgv;

    zzz(zzw zzwVar, SignInConnectionListener signInConnectionListener) {
        this.zzgu = zzwVar;
        this.zzgv = signInConnectionListener;
    }

    final void cancel() {
        this.zzgv.onComplete();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Map<zzh<?>, String>> task) {
        this.zzgu.zzga.lock();
        try {
            if (!this.zzgu.zzgp) {
                this.zzgv.onComplete();
                this.zzgu.zzga.unlock();
                return;
            }
            if (task.isSuccessful()) {
                this.zzgu.zzgr = new ArrayMap(this.zzgu.zzgh.size());
                Iterator it = this.zzgu.zzgh.values().iterator();
                while (it.hasNext()) {
                    this.zzgu.zzgr.put(((zzv) it.next()).zzm(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zzgu.zzgn) {
                    this.zzgu.zzgr = new ArrayMap(this.zzgu.zzgh.size());
                    for (zzv zzvVar : this.zzgu.zzgh.values()) {
                        Object objZzm = zzvVar.zzm();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zzvVar);
                        if (this.zzgu.zza((zzv<?>) zzvVar, connectionResult)) {
                            this.zzgu.zzgr.put(objZzm, new ConnectionResult(16));
                        } else {
                            this.zzgu.zzgr.put(objZzm, connectionResult);
                        }
                    }
                } else {
                    this.zzgu.zzgr = availabilityException.zzl();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zzgu.zzgr = Collections.emptyMap();
            }
            if (this.zzgu.isConnected()) {
                this.zzgu.zzgq.putAll(this.zzgu.zzgr);
                if (this.zzgu.zzai() == null) {
                    this.zzgu.zzag();
                    this.zzgu.zzah();
                    this.zzgu.zzgl.signalAll();
                }
            }
            this.zzgv.onComplete();
            this.zzgu.zzga.unlock();
        } catch (Throwable th) {
            this.zzgu.zzga.unlock();
            throw th;
        }
    }
}
