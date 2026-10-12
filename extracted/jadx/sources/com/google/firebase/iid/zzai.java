package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class zzai<T> {
    final int what;
    final int zzbw;
    final TaskCompletionSource<T> zzbx = new TaskCompletionSource<>();
    final Bundle zzby;

    zzai(int i, int i2, Bundle bundle) {
        this.zzbw = i;
        this.what = i2;
        this.zzby = bundle;
    }

    final void finish(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(t);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(strValueOf).length() + 16 + String.valueOf(strValueOf2).length()).append("Finishing ").append(strValueOf).append(" with ").append(strValueOf2).toString());
        }
        this.zzbx.setResult(t);
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzbw;
        return new StringBuilder(55).append("Request { what=").append(i).append(" id=").append(i2).append(" oneWay=").append(zzaa()).append("}").toString();
    }

    final void zza(zzaj zzajVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(zzajVar);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(strValueOf).length() + 14 + String.valueOf(strValueOf2).length()).append("Failing ").append(strValueOf).append(" with ").append(strValueOf2).toString());
        }
        this.zzbx.setException(zzajVar);
    }

    abstract boolean zzaa();

    abstract void zzb(Bundle bundle);
}
