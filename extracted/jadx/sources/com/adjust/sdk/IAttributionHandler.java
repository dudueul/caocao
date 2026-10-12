package com.adjust.sdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface IAttributionHandler {
    void checkSdkClickResponse(SdkClickResponseData sdkClickResponseData);

    void checkSessionResponse(SessionResponseData sessionResponseData);

    void getAttribution();

    void init(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z);

    void pauseSending();

    void resumeSending();

    void teardown();
}
