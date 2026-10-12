package com.google.firebase.iid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzs implements Continuation<Bundle, String> {
    private final /* synthetic */ zzp zzbe;

    zzs(zzp zzpVar) {
        this.zzbe = zzpVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ String then(@NonNull Task<Bundle> task) throws Exception {
        Bundle result = task.getResult(IOException.class);
        zzp zzpVar = this.zzbe;
        return zzp.zza(result);
    }
}
