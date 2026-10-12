package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    FinderPattern(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    private FinderPattern(float f, float f2, float f3, int i) {
        super(f, f2);
        this.estimatedModuleSize = f3;
        this.count = i;
    }

    boolean aboutEquals(float f, float f2, float f3) {
        if (Math.abs(f2 - getY()) > f || Math.abs(f3 - getX()) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs <= this.estimatedModuleSize;
    }

    FinderPattern combineEstimate(float f, float f2, float f3) {
        int i = this.count + 1;
        return new FinderPattern(((this.count * getX()) + f2) / i, ((this.count * getY()) + f) / i, ((this.count * this.estimatedModuleSize) + f3) / i, i);
    }

    int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }
}
