package com.google.firebase.iid;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final /* synthetic */ class zzj implements Executor {
    static final Executor zzac = new zzj();

    private zzj() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
