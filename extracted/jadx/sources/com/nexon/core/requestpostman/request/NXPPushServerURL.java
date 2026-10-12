package com.nexon.core.requestpostman.request;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPPushServerURL extends NXPServerURL {
    public static final String ALPHA_URL_WITHOUT_PATH = "http://alpha-sdk-push.mp.nexon.com";
    public static final String LIVE_URL_WITHOUT_PATH = "https://sdk-push.mp.nexon.com";
    public static final String PRE_URL_WITHOUT_PATH = "https://pre-sdk-push.mp.nexon.com";
    public static final String QA_URL_WITHOUT_PATH = "http://qa-sdk-push.mp.nexon.com";
    private static NXPServerURL.Environment pushServerEnv = null;

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public NXPServerURL.Environment getEnvironment() {
        NXPServerURL.Environment environment = pushServerEnv;
        return environment == null ? getDefaultEnvironment() : environment;
    }

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public String getUrl() {
        switch (getEnvironment()) {
            case Live:
                return LIVE_URL_WITHOUT_PATH;
            case QA:
                return QA_URL_WITHOUT_PATH;
            case Pre:
                return PRE_URL_WITHOUT_PATH;
            case Alpha:
                return ALPHA_URL_WITHOUT_PATH;
            default:
                return LIVE_URL_WITHOUT_PATH;
        }
    }
}
