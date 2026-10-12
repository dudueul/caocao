package com.nexon.core.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.requestpostman.request.NXPServerURL;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPApplicationConfigManager implements NXPApplicationConfigInterface {
    private NXPApplicationConfigInterface applicationConfigInterface;

    private static class Singleton {
        private static final NXPApplicationConfigManager instance = new NXPApplicationConfigManager();

        private Singleton() {
        }
    }

    private NXPApplicationConfigManager() {
    }

    public static NXPApplicationConfigManager getInstance() {
        return Singleton.instance;
    }

    public static NXPApplicationConfigManager getInstance(boolean z) throws IllegalArgumentException {
        NXPApplicationConfigManager nXPApplicationConfigManager = getInstance();
        if (z && nXPApplicationConfigManager.applicationConfigInterface == null) {
            throw new IllegalArgumentException("Check the Application's name in AndroidManifest.xml. Set the android:name=\"com.nexon.core.android.NXPToyApplication\" or android:name=\"com.nexon.core.android.NXPToyMultidexApplication\" under the application tag.");
        }
        return nXPApplicationConfigManager;
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getAnalyticsRegionHost() {
        return this.applicationConfigInterface.getAnalyticsRegionHost();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getAnalyticsServiceID() {
        return this.applicationConfigInterface.getAnalyticsServiceID();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @Nullable
    public NXPServerURL.Environment getDefaultServerEnvironment() {
        return this.applicationConfigInterface.getDefaultServerEnvironment();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGameId() {
        return this.applicationConfigInterface.getGameId();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGoogleGameAppId() {
        return this.applicationConfigInterface.getGoogleGameAppId();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGoogleWebClientId() {
        return this.applicationConfigInterface.getGoogleWebClientId();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public int getPushVersion() {
        return this.applicationConfigInterface.getPushVersion();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public NXPServerURL getServerURL(@NonNull NXPApplicationConfigInterface.ServerType serverType) {
        return this.applicationConfigInterface.getServerURL(serverType);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getServiceId() {
        return this.applicationConfigInterface.getServiceId();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getStoreClientId() {
        return this.applicationConfigInterface.getStoreClientId();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @Nullable
    public Object getTag() {
        return this.applicationConfigInterface.getTag();
    }

    public void initialize(@NonNull Context context) {
        if (this.applicationConfigInterface == null) {
            this.applicationConfigInterface = new NXPDefaultApplicationConfig(context);
        }
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isEnabledGuestLoginAlert() {
        return this.applicationConfigInterface.isEnabledGuestLoginAlert();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isEnabledStoreDebug() {
        return this.applicationConfigInterface.isEnabledStoreDebug();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isSupportSnsConnectWithGuest() {
        return this.applicationConfigInterface.isSupportSnsConnectWithGuest();
    }
}
