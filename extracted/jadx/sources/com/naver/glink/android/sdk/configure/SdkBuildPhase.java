package com.naver.glink.android.sdk.configure;

import android.content.Context;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum SdkBuildPhase {
    DEV("e690624732fa43af9f82a6c95a6df852", "230d0f2e495042dba9f6786eca86104c", "ec2e25de9cb74a1e939e08aa967bf4f9", "5336b63e5c4a4a969090e84a5f8ec04a", "e690624732fa43af9f82a6c95a6df852", "230d0f2e495042dba9f6786eca86104c"),
    STAGE("a753903ee18a4ae590337e987e5f501b", "f5690df635e24427b75dfc29b733ccdc", "ee11606afe6f44af9ae5685c130cb811", "512dab813fb6459bb1af81a9df06310b", "3365f9aeb3da433f9ffe1a96a867ccc6", "f58f7516315847b18b516dcbc4262c79"),
    REAL(STAGE);

    public final String kaleidoGlobalWorkspaceKey;
    public final String kaleidoLocalWorkspaceKey;
    public final String kaleidoNeoIdServiceId;
    public final String kaleidoNeoIdServiceKey;
    public final String plugNeoIdServiceId;
    public final String plugNeoIdServiceKey;

    SdkBuildPhase(SdkBuildPhase sdkBuildPhase) {
        this(sdkBuildPhase.plugNeoIdServiceId, sdkBuildPhase.plugNeoIdServiceKey, sdkBuildPhase.kaleidoLocalWorkspaceKey, sdkBuildPhase.kaleidoGlobalWorkspaceKey, sdkBuildPhase.kaleidoNeoIdServiceId, sdkBuildPhase.kaleidoNeoIdServiceKey);
    }

    SdkBuildPhase(String str, String str2, String str3, String str4, String str5, String str6) {
        this.plugNeoIdServiceId = str;
        this.plugNeoIdServiceKey = str2;
        this.kaleidoLocalWorkspaceKey = str3;
        this.kaleidoGlobalWorkspaceKey = str4;
        this.kaleidoNeoIdServiceId = str5;
        this.kaleidoNeoIdServiceKey = str6;
    }

    public static SdkBuildPhase from(Context context) {
        return from(context.getString(R.string.flavor_name));
    }

    private static SdkBuildPhase from(String str) {
        for (SdkBuildPhase sdkBuildPhase : values()) {
            if (sdkBuildPhase.name().equalsIgnoreCase(str)) {
                return sdkBuildPhase;
            }
        }
        throw new IllegalArgumentException(str + "에 해당하는 BuildPhase를 찾지 못했습니다.");
    }
}
