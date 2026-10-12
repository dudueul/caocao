package io.fabric.sdk.android.services.common;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SystemCurrentTimeProvider implements CurrentTimeProvider {
    @Override // io.fabric.sdk.android.services.common.CurrentTimeProvider
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
