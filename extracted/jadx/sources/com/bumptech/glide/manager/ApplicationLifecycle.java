package com.bumptech.glide.manager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class ApplicationLifecycle implements Lifecycle {
    ApplicationLifecycle() {
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
