package com.google.android.gms.common;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class GooglePlayServicesNotAvailableException extends Exception {
    public final int errorCode;

    public GooglePlayServicesNotAvailableException(int i) {
        this.errorCode = i;
    }
}
