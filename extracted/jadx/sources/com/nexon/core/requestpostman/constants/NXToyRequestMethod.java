package com.nexon.core.requestpostman.constants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NXToyRequestMethod {
    GET(0),
    POST(1),
    PUT(2),
    DELETE(3);

    private int value;

    NXToyRequestMethod(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
