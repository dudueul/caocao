package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@KeepForSdk
public interface ComponentRegistrar {
    @KeepForSdk
    List<Component<?>> getComponents();
}
