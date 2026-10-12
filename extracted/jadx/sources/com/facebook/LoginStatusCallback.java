package com.facebook;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface LoginStatusCallback {
    void onCompleted(AccessToken accessToken);

    void onError(Exception exc);

    void onFailure();
}
