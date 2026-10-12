package com.facebook;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface FacebookCallback<RESULT> {
    void onCancel();

    void onError(FacebookException facebookException);

    void onSuccess(RESULT result);
}
