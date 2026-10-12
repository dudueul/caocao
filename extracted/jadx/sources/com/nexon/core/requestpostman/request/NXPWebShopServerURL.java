package com.nexon.core.requestpostman.request;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPWebShopServerURL extends NXPServerURL {
    public static final String ALPHA_WEBSHOP_URL = "http://alpha-shop.mp.nexon.com";
    public static final String LIVE_WEBSHOP_URL = "https://shop.mp.nexon.com";
    public static final String PRE_WEBSHOP_URL = "https://pre-shop.mp.nexon.com";
    public static final String QA_WEBSHOP_URL = "https://qa-shop.mp.nexon.com";
    private static NXPServerURL.Environment webshopServerEnv = null;

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public NXPServerURL.Environment getEnvironment() {
        NXPServerURL.Environment environment = webshopServerEnv;
        return environment == null ? getDefaultEnvironment() : environment;
    }

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public String getUrl() {
        switch (getEnvironment()) {
            case Live:
                return LIVE_WEBSHOP_URL;
            case QA:
                return QA_WEBSHOP_URL;
            case Pre:
                return PRE_WEBSHOP_URL;
            case Alpha:
                return ALPHA_WEBSHOP_URL;
            default:
                return LIVE_WEBSHOP_URL;
        }
    }
}
