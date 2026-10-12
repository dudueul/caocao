package com.nexon.core.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.requestpostman.request.NXPBoltServerURL;
import com.nexon.core.requestpostman.request.NXPEmptyServerURL;
import com.nexon.core.requestpostman.request.NXPPageServerURL;
import com.nexon.core.requestpostman.request.NXPPushServerURL;
import com.nexon.core.requestpostman.request.NXPServerURL;
import com.nexon.core.requestpostman.request.NXPStoreServerURL;
import com.nexon.core.requestpostman.request.NXPWebShopServerURL;
import com.nexon.core.util.NXToyApplicationInfoUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPDefaultApplicationConfig extends NXPApplicationConfig {
    public NXPDefaultApplicationConfig(@NonNull Context context) {
        super(context);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @Nullable
    public NXPServerURL.Environment getDefaultServerEnvironment() {
        return null;
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public int getPushVersion() {
        return NXToyApplicationInfoUtil.getPushVersion(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public NXPServerURL getServerURL(@NonNull NXPApplicationConfigInterface.ServerType serverType) {
        switch (serverType) {
            case Bolt:
                return new NXPBoltServerURL();
            case Push:
                return new NXPPushServerURL();
            case Store:
                return new NXPStoreServerURL();
            case Page:
                return new NXPPageServerURL();
            case WebShop:
                return new NXPWebShopServerURL();
            case StoreVerify:
                return new NXPEmptyServerURL();
            default:
                return new NXPEmptyServerURL();
        }
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getServiceId() {
        return NXToyApplicationInfoUtil.getServiceID(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getStoreClientId() {
        return NXToyApplicationInfoUtil.getCliendID(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @Nullable
    public Object getTag() {
        return null;
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isEnabledGuestLoginAlert() {
        return NXToyApplicationInfoUtil.useGuestLoginAlert(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isEnabledStoreDebug() {
        return false;
    }
}
