package com.nexon.core.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.requestpostman.request.NXPServerURL;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface NXPApplicationConfigInterface {

    public enum ServerType {
        Bolt,
        Push,
        Store,
        Page,
        WebShop,
        StoreVerify
    }

    @NonNull
    String getAnalyticsRegionHost();

    @NonNull
    String getAnalyticsServiceID();

    @Nullable
    NXPServerURL.Environment getDefaultServerEnvironment();

    @NonNull
    String getGameId();

    @NonNull
    String getGoogleGameAppId();

    @NonNull
    String getGoogleWebClientId();

    int getPushVersion();

    @NonNull
    NXPServerURL getServerURL(@NonNull ServerType serverType);

    @NonNull
    String getServiceId();

    @NonNull
    String getStoreClientId();

    @Nullable
    Object getTag();

    boolean isEnabledGuestLoginAlert();

    boolean isEnabledStoreDebug();

    boolean isSupportSnsConnectWithGuest();
}
