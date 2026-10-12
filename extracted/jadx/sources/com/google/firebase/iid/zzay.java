package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzay {

    @GuardedBy("itself")
    private final zzau zzag;

    @GuardedBy("this")
    private int zzdc = 0;

    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdd = new ArrayMap();

    zzay(zzau zzauVar) {
        this.zzag = zzauVar;
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] strArrSplit = str.split("!");
        if (strArrSplit.length != 2) {
            return true;
        }
        String str2 = strArrSplit[0];
        String str3 = strArrSplit[1];
        byte b = -1;
        try {
            switch (str2.hashCode()) {
                case 83:
                    if (str2.equals("S")) {
                        b = 0;
                    }
                    break;
                case 85:
                    if (str2.equals("U")) {
                        b = 1;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    firebaseInstanceId.zzb(str3);
                    if (!FirebaseInstanceId.zzk()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    firebaseInstanceId.zzc(str3);
                    if (!FirebaseInstanceId.zzk()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Topic sync failed: ".concat(strValueOf) : new String("Topic sync failed: "));
            return false;
        }
    }

    @GuardedBy("this")
    @Nullable
    private final String zzaq() {
        String strZzaj;
        synchronized (this.zzag) {
            strZzaj = this.zzag.zzaj();
        }
        if (!TextUtils.isEmpty(strZzaj)) {
            String[] strArrSplit = strZzaj.split(",");
            if (strArrSplit.length > 1 && !TextUtils.isEmpty(strArrSplit[1])) {
                return strArrSplit[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        boolean z;
        synchronized (this.zzag) {
            String strZzaj = this.zzag.zzaj();
            String strValueOf = String.valueOf(",");
            String strValueOf2 = String.valueOf(str);
            if (strZzaj.startsWith(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf))) {
                String strValueOf3 = String.valueOf(",");
                String strValueOf4 = String.valueOf(str);
                this.zzag.zzf(strZzaj.substring((strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3)).length()));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    final synchronized Task<Void> zza(String str) {
        String strZzaj;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzag) {
            strZzaj = this.zzag.zzaj();
            this.zzag.zzf(new StringBuilder(String.valueOf(strZzaj).length() + 1 + String.valueOf(str).length()).append(strZzaj).append(",").append(str).toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzdd.put(Integer.valueOf((TextUtils.isEmpty(strZzaj) ? 0 : strZzaj.split(",").length - 1) + this.zzdc), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    @WorkerThread
    final boolean zza(FirebaseInstanceId firebaseInstanceId) {
        TaskCompletionSource<Void> taskCompletionSourceRemove;
        while (true) {
            synchronized (this) {
                String strZzaq = zzaq();
                if (strZzaq == null) {
                    Log.d("FirebaseInstanceId", "topic sync succeeded");
                    return true;
                }
                if (!zza(firebaseInstanceId, strZzaq)) {
                    return false;
                }
                synchronized (this) {
                    taskCompletionSourceRemove = this.zzdd.remove(Integer.valueOf(this.zzdc));
                    zzk(strZzaq);
                    this.zzdc++;
                }
                if (taskCompletionSourceRemove != null) {
                    taskCompletionSourceRemove.setResult(null);
                }
            }
        }
    }

    final synchronized boolean zzap() {
        return zzaq() != null;
    }
}
