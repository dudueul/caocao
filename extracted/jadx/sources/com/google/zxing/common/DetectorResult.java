package com.google.zxing.common;

import com.google.zxing.ResultPoint;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DetectorResult {
    private final BitMatrix bits;
    private final ResultPoint[] points;

    public DetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr) {
        this.bits = bitMatrix;
        this.points = resultPointArr;
    }

    public final BitMatrix getBits() {
        return this.bits;
    }

    public final ResultPoint[] getPoints() {
        return this.points;
    }
}
