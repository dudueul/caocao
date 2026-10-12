package com.bumptech.glide.manager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ConnectivityMonitor extends LifecycleListener {

    public interface ConnectivityListener {
        void onConnectivityChanged(boolean z);
    }
}
