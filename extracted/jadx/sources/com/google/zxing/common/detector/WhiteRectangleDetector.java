package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i, int i2, int i3) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        this.width = bitMatrix.getWidth();
        int i4 = i / 2;
        this.leftInit = i2 - i4;
        this.rightInit = i2 + i4;
        this.upInit = i3 - i4;
        this.downInit = i3 + i4;
        if (this.upInit < 0 || this.leftInit < 0 || this.downInit >= this.height || this.rightInit >= this.width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = resultPoint2.getX();
        float y2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        float y3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        float y4 = resultPoint4.getY();
        return x < ((float) this.width) / 2.0f ? new ResultPoint[]{new ResultPoint(x4 - 1.0f, 1.0f + y4), new ResultPoint(1.0f + x2, 1.0f + y2), new ResultPoint(x3 - 1.0f, y3 - 1.0f), new ResultPoint(1.0f + x, y - 1.0f)} : new ResultPoint[]{new ResultPoint(1.0f + x4, 1.0f + y4), new ResultPoint(1.0f + x2, y2 - 1.0f), new ResultPoint(x3 - 1.0f, 1.0f + y3), new ResultPoint(x - 1.0f, y - 1.0f)};
    }

    private boolean containsBlackPoint(int i, int i2, int i3, boolean z) {
        if (z) {
            for (int i4 = i; i4 <= i2; i4++) {
                if (this.image.get(i4, i3)) {
                    return true;
                }
            }
        } else {
            for (int i5 = i; i5 <= i2; i5++) {
                if (this.image.get(i3, i5)) {
                    return true;
                }
            }
        }
        return false;
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int iRound = MathUtils.round(MathUtils.distance(f, f2, f3, f4));
        float f5 = (f3 - f) / iRound;
        float f6 = (f4 - f2) / iRound;
        for (int i = 0; i < iRound; i++) {
            int iRound2 = MathUtils.round((i * f5) + f);
            int iRound3 = MathUtils.round((i * f6) + f2);
            if (this.image.get(iRound2, iRound3)) {
                return new ResultPoint(iRound2, iRound3);
            }
        }
        return null;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i = this.leftInit;
        int i2 = this.rightInit;
        int i3 = this.upInit;
        int i4 = this.downInit;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (z2) {
            z2 = false;
            boolean zContainsBlackPoint = true;
            while (true) {
                if ((!zContainsBlackPoint && z4) || i2 >= this.width) {
                    break;
                }
                zContainsBlackPoint = containsBlackPoint(i3, i4, i2, false);
                if (zContainsBlackPoint) {
                    i2++;
                    z2 = true;
                    z4 = true;
                } else if (!z4) {
                    i2++;
                }
            }
            if (i2 >= this.width) {
                z = true;
                break;
            }
            boolean zContainsBlackPoint2 = true;
            while (true) {
                if ((!zContainsBlackPoint2 && z5) || i4 >= this.height) {
                    break;
                }
                zContainsBlackPoint2 = containsBlackPoint(i, i2, i4, true);
                if (zContainsBlackPoint2) {
                    i4++;
                    z2 = true;
                    z5 = true;
                } else if (!z5) {
                    i4++;
                }
            }
            if (i4 >= this.height) {
                z = true;
                break;
            }
            boolean zContainsBlackPoint3 = true;
            while (true) {
                if ((!zContainsBlackPoint3 && z6) || i < 0) {
                    break;
                }
                zContainsBlackPoint3 = containsBlackPoint(i3, i4, i, false);
                if (zContainsBlackPoint3) {
                    i--;
                    z2 = true;
                    z6 = true;
                } else if (!z6) {
                    i--;
                }
            }
            if (i < 0) {
                z = true;
                break;
            }
            boolean zContainsBlackPoint4 = true;
            while (true) {
                if ((!zContainsBlackPoint4 && z7) || i3 < 0) {
                    break;
                }
                zContainsBlackPoint4 = containsBlackPoint(i, i2, i3, true);
                if (zContainsBlackPoint4) {
                    i3--;
                    z2 = true;
                    z7 = true;
                } else if (!z7) {
                    i3--;
                }
            }
            if (i3 < 0) {
                z = true;
                break;
            }
            if (z2) {
                z3 = true;
            }
        }
        if (z || !z3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        ResultPoint blackPointOnSegment = null;
        for (int i6 = 1; blackPointOnSegment == null && i6 < i5; i6++) {
            blackPointOnSegment = getBlackPointOnSegment(i, i4 - i6, i + i6, i4);
        }
        if (blackPointOnSegment == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment2 = null;
        for (int i7 = 1; blackPointOnSegment2 == null && i7 < i5; i7++) {
            blackPointOnSegment2 = getBlackPointOnSegment(i, i3 + i7, i + i7, i3);
        }
        if (blackPointOnSegment2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment3 = null;
        for (int i8 = 1; blackPointOnSegment3 == null && i8 < i5; i8++) {
            blackPointOnSegment3 = getBlackPointOnSegment(i2, i3 + i8, i2 - i8, i3);
        }
        if (blackPointOnSegment3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment4 = null;
        for (int i9 = 1; blackPointOnSegment4 == null && i9 < i5; i9++) {
            blackPointOnSegment4 = getBlackPointOnSegment(i2, i4 - i9, i2 - i9, i4);
        }
        if (blackPointOnSegment4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        return centerEdges(blackPointOnSegment4, blackPointOnSegment, blackPointOnSegment3, blackPointOnSegment2);
    }
}
