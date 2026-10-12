package com.google.android.gms.common.stats;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface StatisticsTracker {
    void registerEvent(ConnectionEvent connectionEvent);

    void registerEvent(WakeLockEvent wakeLockEvent);
}
