package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            resultPointArr[iArr[i]] = resultPointArr2[i];
        }
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        List<ResultPoint[]> listDetect = detect(z, blackMatrix);
        if (listDetect.isEmpty()) {
            blackMatrix = blackMatrix.m8clone();
            blackMatrix.rotate180();
            listDetect = detect(z, blackMatrix);
        }
        return new PDF417DetectorResult(blackMatrix, listDetect);
    }

    private static List<ResultPoint[]> detect(boolean z, BitMatrix bitMatrix) {
        ArrayList<ResultPoint[]> arrayList = new ArrayList();
        int y = 0;
        int x = 0;
        boolean z2 = false;
        while (y < bitMatrix.getHeight()) {
            ResultPoint[] resultPointArrFindVertices = findVertices(bitMatrix, y, x);
            if (resultPointArrFindVertices[0] == null && resultPointArrFindVertices[3] == null) {
                if (!z2) {
                    break;
                }
                z2 = false;
                x = 0;
                for (ResultPoint[] resultPointArr : arrayList) {
                    if (resultPointArr[1] != null) {
                        y = (int) Math.max(y, resultPointArr[1].getY());
                    }
                    if (resultPointArr[3] != null) {
                        y = Math.max(y, (int) resultPointArr[3].getY());
                    }
                }
                y += 5;
            } else {
                z2 = true;
                arrayList.add(resultPointArrFindVertices);
                if (!z) {
                    break;
                }
                if (resultPointArrFindVertices[2] != null) {
                    x = (int) resultPointArrFindVertices[2].getX();
                    y = (int) resultPointArrFindVertices[2].getY();
                } else {
                    x = (int) resultPointArrFindVertices[4].getX();
                    y = (int) resultPointArrFindVertices[4].getY();
                }
            }
        }
        return arrayList;
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = i;
        int i5 = 0;
        while (bitMatrix.get(i4, i2) && i4 > 0) {
            int i6 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i4--;
            i5 = i6;
        }
        int i7 = i4;
        int i8 = 0;
        int length = iArr.length;
        boolean z2 = z;
        while (i7 < i3) {
            if (bitMatrix.get(i7, i2) ^ z2) {
                iArr2[i8] = iArr2[i8] + 1;
            } else {
                if (i8 != length - 1) {
                    i8++;
                } else {
                    if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                        return new int[]{i4, i7};
                    }
                    i4 += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, 2, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i8--;
                }
                iArr2[i8] = 1;
                z2 = !z2;
            }
            i7++;
        }
        if (i8 != length - 1 || patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
            return null;
        }
        return new int[]{i4, i7 - 1};
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int i, int i2, int i3, int i4, int[] iArr) {
        ResultPoint[] resultPointArr = new ResultPoint[4];
        boolean z = false;
        int[] iArr2 = new int[iArr.length];
        while (i3 < i) {
            int[] iArrFindGuardPattern = findGuardPattern(bitMatrix, i4, i3, i2, false, iArr, iArr2);
            if (iArrFindGuardPattern != null) {
                while (i3 > 0) {
                    i3--;
                    int[] iArrFindGuardPattern2 = findGuardPattern(bitMatrix, i4, i3, i2, false, iArr, iArr2);
                    if (iArrFindGuardPattern2 == null) {
                        i3++;
                        break;
                    }
                    iArrFindGuardPattern = iArrFindGuardPattern2;
                }
                resultPointArr[0] = new ResultPoint(iArrFindGuardPattern[0], i3);
                resultPointArr[1] = new ResultPoint(iArrFindGuardPattern[1], i3);
                z = true;
                break;
            }
            i3 += 5;
        }
        int i5 = i3 + 1;
        if (z) {
            int i6 = 0;
            int[] iArr3 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            while (i5 < i) {
                int[] iArrFindGuardPattern3 = findGuardPattern(bitMatrix, iArr3[0], i5, i2, false, iArr, iArr2);
                if (iArrFindGuardPattern3 != null && Math.abs(iArr3[0] - iArrFindGuardPattern3[0]) < 5 && Math.abs(iArr3[1] - iArrFindGuardPattern3[1]) < 5) {
                    iArr3 = iArrFindGuardPattern3;
                    i6 = 0;
                } else {
                    if (i6 > 25) {
                        break;
                    }
                    i6++;
                }
                i5++;
            }
            i5 -= i6 + 1;
            resultPointArr[2] = new ResultPoint(iArr3[0], i5);
            resultPointArr[3] = new ResultPoint(iArr3[1], i5);
        }
        if (i5 - i3 < 10) {
            for (int i7 = 0; i7 < 4; i7++) {
                resultPointArr[i7] = null;
            }
        }
        return resultPointArr;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i, int i2) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, START_PATTERN), INDEXES_START_PATTERN);
        if (resultPointArr[4] != null) {
            i2 = (int) resultPointArr[4].getX();
            i = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i / i2;
        float f3 = f * f2;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            float f5 = iArr2[i4] * f2;
            float f6 = ((float) i5) > f5 ? i5 - f5 : f5 - i5;
            if (f6 > f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f6;
        }
        return f4 / i;
    }
}
