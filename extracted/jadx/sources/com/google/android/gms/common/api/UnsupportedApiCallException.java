package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzdr;

    public UnsupportedApiCallException(Feature feature) {
        this.zzdr = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String strValueOf = String.valueOf(this.zzdr);
        return new StringBuilder(String.valueOf(strValueOf).length() + 8).append("Missing ").append(strValueOf).toString();
    }
}
