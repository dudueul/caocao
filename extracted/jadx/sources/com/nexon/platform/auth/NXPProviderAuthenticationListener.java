package com.nexon.platform.auth;

import android.support.annotation.NonNull;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface NXPProviderAuthenticationListener {
    void onFailure(int i, String str);

    void onSuccess(@NonNull NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo);
}
