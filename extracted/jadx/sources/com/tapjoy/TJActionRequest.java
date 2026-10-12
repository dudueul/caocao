package com.tapjoy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface TJActionRequest {
    void cancelled();

    void completed();

    String getRequestId();

    String getToken();
}
