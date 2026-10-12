package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@KeepForSdk
public interface ComponentFactory<T> {
    @KeepForSdk
    T create(ComponentContainer componentContainer);
}
