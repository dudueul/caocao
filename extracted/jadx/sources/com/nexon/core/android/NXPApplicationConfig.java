package com.nexon.core.android;

import android.content.Context;
import android.support.annotation.NonNull;
import com.nexon.core.util.NXToyApplicationInfoUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXPApplicationConfig implements NXPApplicationConfigInterface {
    protected transient Context applicationContext;

    public NXPApplicationConfig(@NonNull Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getAnalyticsRegionHost() {
        return NXToyApplicationInfoUtil.getAnalyticsRegionHost(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getAnalyticsServiceID() {
        return NXToyApplicationInfoUtil.getAnalyticsServiceID(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGameId() {
        return NXToyApplicationInfoUtil.getGameID(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGoogleGameAppId() {
        return NXToyApplicationInfoUtil.getGoogleGameAppId(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    @NonNull
    public String getGoogleWebClientId() {
        return NXToyApplicationInfoUtil.getGoogleWebClientId(this.applicationContext);
    }

    @Override // com.nexon.core.android.NXPApplicationConfigInterface
    public boolean isSupportSnsConnectWithGuest() {
        return NXToyApplicationInfoUtil.canSnsConnectWithGuest(this.applicationContext);
    }
}
