package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@KeepForSdk
public interface EventHandler<T> {
    @KeepForSdk
    void handle(Event<T> event);
}
