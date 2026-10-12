package com.nexon.core.log.constant;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NXToyLogLevel {
    DEVELOP(1),
    VERBOSE(2),
    DEBUG(3),
    INFO(4),
    ERROR(6);

    private int value;

    NXToyLogLevel(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
