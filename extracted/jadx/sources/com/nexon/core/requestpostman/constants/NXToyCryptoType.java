package com.nexon.core.requestpostman.constants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NXToyCryptoType {
    NONE(0),
    COMMON(1),
    NPSN(2);

    private int value;

    NXToyCryptoType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
