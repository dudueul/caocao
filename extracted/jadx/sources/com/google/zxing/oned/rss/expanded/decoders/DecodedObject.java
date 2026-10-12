package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class DecodedObject {
    private final int newPosition;

    DecodedObject(int i) {
        this.newPosition = i;
    }

    final int getNewPosition() {
        return this.newPosition;
    }
}
