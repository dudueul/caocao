package com.google.firebase.iid;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface InstanceIdResult {
    @NonNull
    String getId();

    @NonNull
    String getToken();
}
