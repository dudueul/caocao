package com.adjust.sdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface IRequestHandler {
    void init(IPackageHandler iPackageHandler);

    void sendPackage(ActivityPackage activityPackage, int i);

    void teardown();
}
