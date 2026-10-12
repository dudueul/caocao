package com.google.firebase.iid;

import android.content.Intent;
import android.support.annotation.WorkerThread;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Deprecated
public class FirebaseInstanceIdService extends zzb {
    @WorkerThread
    @Deprecated
    public void onTokenRefresh() {
    }

    @Override // com.google.firebase.iid.zzb
    protected final Intent zzb(Intent intent) {
        return zzat.zzah().zzcr.poll();
    }

    @Override // com.google.firebase.iid.zzb
    public final void zzd(Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            onTokenRefresh();
            return;
        }
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(intent.getExtras());
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(strValueOf).length()).append("Received command: ").append(stringExtra).append(" - ").append(strValueOf).toString());
            }
            if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zzl();
            } else if ("SYNC".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zzp();
            }
        }
    }
}
