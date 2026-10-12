package android.arch.lifecycle;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface LifecycleOwner {
    @NonNull
    Lifecycle getLifecycle();
}
