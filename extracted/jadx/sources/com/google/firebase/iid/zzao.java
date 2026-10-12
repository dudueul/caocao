package com.google.firebase.iid;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzao {

    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<String>> zzcf = new ArrayMap();

    zzao() {
    }

    final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzcf.remove(pair);
        }
        return task;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final synchronized Task<String> zza(String str, String str2, zzaq zzaqVar) {
        Task task;
        final Pair<String, String> pair = new Pair<>(str, str2);
        Task task2 = this.zzcf.get(pair);
        if (task2 == null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(pair);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 24).append("Making new request for: ").append(strValueOf).toString());
            }
            Task taskContinueWithTask = zzaqVar.zzt().continueWithTask(zzi.zzd(), new Continuation(this, pair) { // from class: com.google.firebase.iid.zzap
                private final zzao zzcg;
                private final Pair zzch;

                {
                    this.zzcg = this;
                    this.zzch = pair;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task3) {
                    return this.zzcg.zza(this.zzch, task3);
                }
            });
            this.zzcf.put(pair, (Task<String>) taskContinueWithTask);
            task = taskContinueWithTask;
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            task = task2;
            String strValueOf2 = String.valueOf(pair);
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf2).length() + 29).append("Joining ongoing request for: ").append(strValueOf2).toString());
            task = task2;
        }
        return task;
    }
}
