package com.nexon.core.requestpostman.request;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPEmptyServerURL extends NXPServerURL {
    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public NXPServerURL.Environment getEnvironment() {
        return NXPServerURL.Environment.QA;
    }

    @Override // com.nexon.core.requestpostman.request.NXPServerURL
    @NonNull
    public String getUrl() {
        return "";
    }
}
