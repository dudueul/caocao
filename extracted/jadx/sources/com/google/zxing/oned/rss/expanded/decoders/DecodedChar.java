package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DecodedChar extends DecodedObject {
    static final char FNC1 = '$';
    private final char value;

    DecodedChar(int i, char c) {
        super(i);
        this.value = c;
    }

    char getValue() {
        return this.value;
    }

    boolean isFNC1() {
        return this.value == '$';
    }
}
