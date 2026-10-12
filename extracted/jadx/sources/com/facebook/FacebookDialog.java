package com.facebook;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface FacebookDialog<CONTENT, RESULT> {
    boolean canShow(CONTENT content);

    void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback);

    void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback, int i);

    void show(CONTENT content);
}
