package com.adjust.sdk;

import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface IPackageHandler {
    void addPackage(ActivityPackage activityPackage);

    void closeFirstPackage(ResponseData responseData, ActivityPackage activityPackage);

    String getBasePath();

    void init(IActivityHandler iActivityHandler, Context context, boolean z);

    void pauseSending();

    void resumeSending();

    void sendFirstPackage();

    void sendNextPackage(ResponseData responseData);

    void teardown();

    void updatePackages(SessionParameters sessionParameters);
}
