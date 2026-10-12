package com.nexon.core.requestpostman.request;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPPageServerURL extends NXPServerURL {
    public static final String ALPHA_PAGE_URL = "http://alpha-page.mp.nexon.com";
    public static final String LIVE_PAGE_URL = "https://m-page.nexon.com";
    public static final String PRE_PAGE_URL = "https://pre-page.mp.nexon.com";
    public static final String QA_PAGE_URL = "https://mt-page.nexon.com";
    private static NXPServerURL.Environment pageServerEnv = null;

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public NXPServerURL.Environment getEnvironment() {
        NXPServerURL.Environment environment = pageServerEnv;
        return environment == null ? getDefaultEnvironment() : environment;
    }

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public String getUrl() {
        switch (getEnvironment()) {
            case Live:
                return LIVE_PAGE_URL;
            case QA:
                return QA_PAGE_URL;
            case Pre:
                return PRE_PAGE_URL;
            case Alpha:
                return ALPHA_PAGE_URL;
            default:
                return LIVE_PAGE_URL;
        }
    }
}
