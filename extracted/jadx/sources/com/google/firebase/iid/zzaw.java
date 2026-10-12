package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzaw implements Runnable {
    private final zzal zzak;
    private final zzay zzan;
    private final long zzcy;
    private final PowerManager.WakeLock zzcz = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zzda;

    @VisibleForTesting
    zzaw(FirebaseInstanceId firebaseInstanceId, zzal zzalVar, zzay zzayVar, long j) {
        this.zzda = firebaseInstanceId;
        this.zzak = zzalVar;
        this.zzan = zzayVar;
        this.zzcy = j;
        this.zzcz.setReferenceCounted(false);
    }

    @VisibleForTesting
    private final boolean zzal() {
        try {
            if (!this.zzda.zzn()) {
                this.zzda.zzo();
            }
            return true;
        } catch (IOException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Build channel failed: ".concat(strValueOf) : new String("Build channel failed: "));
            return false;
        }
    }

    @VisibleForTesting
    private final boolean zzam() {
        zzav zzavVarZzi = this.zzda.zzi();
        if (zzavVarZzi != null && !zzavVarZzi.zzj(this.zzak.zzac())) {
            return true;
        }
        try {
            String strZzj = this.zzda.zzj();
            if (strZzj == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzavVarZzi != null && (zzavVarZzi == null || strZzj.equals(zzavVarZzi.zzbh))) {
                return true;
            }
            Context context = getContext();
            Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
            intent.putExtra("token", strZzj);
            zzat.zzc(context, intent);
            zzat.zzb(context, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
            return true;
        } catch (IOException | SecurityException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Token retrieval failed: ".concat(strValueOf) : new String("Token retrieval failed: "));
            return false;
        }
    }

    final Context getContext() {
        return this.zzda.zzg().getApplicationContext();
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzcz.acquire();
        try {
            this.zzda.zza(true);
            if (!this.zzda.zzm()) {
                this.zzda.zza(false);
                return;
            }
            if (!zzan()) {
                new zzax(this).zzao();
                return;
            }
            if (zzal() && zzam() && this.zzan.zza(this.zzda)) {
                this.zzda.zza(false);
            } else {
                this.zzda.zza(this.zzcy);
            }
        } finally {
            this.zzcz.release();
        }
    }

    final boolean zzan() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
