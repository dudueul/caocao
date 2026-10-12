package com.adjust.sdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ISdkClickHandler {
    void init(IActivityHandler iActivityHandler, boolean z);

    void pauseSending();

    void resumeSending();

    void sendReftagReferrers();

    void sendSdkClick(ActivityPackage activityPackage);

    void teardown();
}
