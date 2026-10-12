package com.facebook.login;

import com.facebook.internal.NativeProtocol;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum DefaultAudience {
    NONE(null),
    ONLY_ME(NativeProtocol.AUDIENCE_ME),
    FRIENDS("friends"),
    EVERYONE(NativeProtocol.AUDIENCE_EVERYONE);

    private final String nativeProtocolAudience;

    DefaultAudience(String str) {
        this.nativeProtocolAudience = str;
    }

    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
