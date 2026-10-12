package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i);
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i6 << 3;
            int i8 = i4 - 8;
            if (i7 > i8) {
                i7 = i8;
            }
            for (int i9 = 0; i9 < i; i9++) {
                int i10 = i9 << 3;
                int i11 = i3 - 8;
                if (i10 > i11) {
                    i10 = i11;
                }
                int i12 = 0;
                int i13 = 255;
                int i14 = 0;
                int i15 = 0;
                int i16 = (i7 * i3) + i10;
                while (i15 < 8) {
                    for (int i17 = 0; i17 < 8; i17++) {
                        int i18 = bArr[i16 + i17] & 255;
                        i12 += i18;
                        if (i18 < i13) {
                            i13 = i18;
                        }
                        if (i18 > i14) {
                            i14 = i18;
                        }
                    }
                    if (i14 - i13 > 24) {
                        while (true) {
                            i15++;
                            i16 += i3;
                            if (i15 < 8) {
                                for (int i19 = 0; i19 < 8; i19++) {
                                    i12 += bArr[i16 + i19] & 255;
                                }
                            }
                        }
                    }
                    i15++;
                    i16 += i3;
                }
                int i20 = i12 >> 6;
                if (i14 - i13 <= 24) {
                    i20 = i13 / 2;
                    if (i6 > 0 && i9 > 0 && i13 < (i5 = ((iArr[i6 - 1][i9] + (iArr[i6][i9 - 1] * 2)) + iArr[i6 - 1][i9 - 1]) / 4)) {
                        i20 = i5;
                    }
                }
                iArr[i6][i9] = i20;
            }
        }
        return iArr;
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 << 3;
            int i7 = i4 - 8;
            if (i6 > i7) {
                i6 = i7;
            }
            for (int i8 = 0; i8 < i; i8++) {
                int i9 = i8 << 3;
                int i10 = i3 - 8;
                if (i9 > i10) {
                    i9 = i10;
                }
                int iCap = cap(i8, 2, i - 3);
                int iCap2 = cap(i5, 2, i2 - 3);
                int i11 = 0;
                for (int i12 = -2; i12 <= 2; i12++) {
                    int[] iArr2 = iArr[iCap2 + i12];
                    i11 += iArr2[iCap - 2] + iArr2[iCap - 1] + iArr2[iCap] + iArr2[iCap + 1] + iArr2[iCap + 2];
                }
                thresholdBlock(bArr, i9, i6, i11 / 25, i3, bitMatrix);
            }
        }
    }

    private static int cap(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }

    private static void thresholdBlock(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = 0;
        int i6 = (i2 * i4) + i;
        while (i5 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i6 + i7] & 255) <= i3) {
                    bitMatrix.set(i + i7, i2 + i5);
                }
            }
            i5++;
            i6 += i4;
        }
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        if (this.matrix != null) {
            return this.matrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] matrix = luminanceSource.getMatrix();
            int i = width >> 3;
            if ((width & 7) != 0) {
                i++;
            }
            int i2 = height >> 3;
            if ((height & 7) != 0) {
                i2++;
            }
            int[][] iArrCalculateBlackPoints = calculateBlackPoints(matrix, i, i2, width, height);
            BitMatrix bitMatrix = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i, i2, width, height, iArrCalculateBlackPoints, bitMatrix);
            this.matrix = bitMatrix;
        }
        return this.matrix;
    }
}
