package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.mGeneratedAdapter = generatedAdapter;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.mGeneratedAdapter.callMethods(lifecycleOwner, event, false, null);
        this.mGeneratedAdapter.callMethods(lifecycleOwner, event, true, null);
    }
}
