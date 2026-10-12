package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        int[] rowHeights;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int i = 0;
        for (int i2 : rowHeights) {
            i += max - i2;
            if (i2 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        for (int i3 = 0; i > 0 && codewords[i3] == null; i3++) {
            i--;
        }
        int i4 = 0;
        for (int length = rowHeights.length - 1; length >= 0; length--) {
            i4 += max - rowHeights[length];
            if (rowHeights[length] > 0) {
                break;
            }
        }
        for (int length2 = codewords.length - 1; i4 > 0 && codewords[length2] == null; length2--) {
            i4--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i, i4, detectionResultRowIndicatorColumn.isLeft());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        int[] value = barcodeValueArr[0][1].getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length != 0) {
            if (value[0] != barcodeColumnCount) {
                barcodeValueArr[0][1].setValue(barcodeColumnCount);
            }
        } else {
            if (barcodeColumnCount <= 0 || barcodeColumnCount > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x000f  */
    /* JADX WARN: Code duplicated, block: B:12:0x0015  */
    /* JADX WARN: Code duplicated, block: B:21:0x002a A[LOOP:1: B:8:0x000b->B:21:0x002a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x001d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0022 A[EDGE_INSN: B:30:0x0022->B:17:0x0022 BREAK  A[LOOP:1: B:8:0x000b->B:21:0x002a], SYNTHETIC] */
    private static int adjustCodewordStartColumn(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        int i5 = z ? -1 : 1;
        int i6 = 0;
        int i7 = i3;
        while (i6 < 2) {
            int i8 = i7;
            while (true) {
                if (!z) {
                    if (i8 < i2) {
                        if (z == bitMatrix.get(i8, i4)) {
                            break;
                            break;
                        }
                        if (Math.abs(i3 - i8) > 2) {
                            return i3;
                        }
                        i8 += i5;
                    } else {
                        break;
                    }
                } else {
                    if (i8 < i) {
                        break;
                    }
                    if (z == bitMatrix.get(i8, i4)) {
                        break;
                    }
                    if (Math.abs(i3 - i8) > 2) {
                        return i3;
                    }
                    i8 += i5;
                }
            }
            i5 = -i5;
            z = !z;
            i6++;
            i7 = i8;
        }
        return i7;
    }

    private static boolean checkCodewordSkew(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return errorCorrection.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        int rowNumber;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance((Class<?>) BarcodeValue.class, detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2);
        for (int i = 0; i < barcodeValueArr.length; i++) {
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                barcodeValueArr[i][i2] = new BarcodeValue();
            }
        }
        int i3 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0 && rowNumber < barcodeValueArr.length) {
                        barcodeValueArr[rowNumber][i3].setValue(codeword.getValue());
                    }
                }
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws NotFoundException, ChecksumException, FormatException {
        BarcodeValue[][] barcodeValueArrCreateBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, barcodeValueArrCreateBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < detectionResult.getBarcodeRowCount(); i++) {
            for (int i2 = 0; i2 < detectionResult.getBarcodeColumnCount(); i2++) {
                int[] value = barcodeValueArrCreateBarcodeMatrix[i][i2 + 1].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i) + i2;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            iArr2[i3] = (int[]) arrayList2.get(i3);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws ChecksumException, FormatException {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2;
            i2 = i3 - 1;
            if (i3 <= 0) {
                throw ChecksumException.getChecksumInstance();
            }
            for (int i4 = 0; i4 < iArr5.length; i4++) {
                iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
            }
            try {
                return decodeCodewords(iArr, i, iArr2);
            } catch (ChecksumException e) {
                if (iArr5.length == 0) {
                    throw ChecksumException.getChecksumInstance();
                }
                for (int i5 = 0; i5 < iArr5.length; i5++) {
                    if (iArr5[i5] < iArr4[i5].length - 1) {
                        iArr5[i5] = iArr5[i5] + 1;
                        break;
                    }
                    iArr5[i5] = 0;
                    if (i5 == iArr5.length - 1) {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00f9 A[SYNTHETIC] */
    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException, ChecksumException, FormatException {
        Codeword codewordDetectCodeword;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        DetectionResultRowIndicatorColumn rowIndicatorColumn = null;
        DetectionResultRowIndicatorColumn rowIndicatorColumn2 = null;
        DetectionResult detectionResultMerge = null;
        for (int i3 = 0; i3 < 2; i3++) {
            if (resultPoint != null) {
                rowIndicatorColumn = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i, i2);
            }
            if (resultPoint3 != null) {
                rowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResultMerge = merge(rowIndicatorColumn, rowIndicatorColumn2);
            if (detectionResultMerge == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i3 != 0 || detectionResultMerge.getBoundingBox() == null || (detectionResultMerge.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResultMerge.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                detectionResultMerge.setBoundingBox(boundingBox);
                break;
            }
            boundingBox = detectionResultMerge.getBoundingBox();
        }
        int barcodeColumnCount = detectionResultMerge.getBarcodeColumnCount() + 1;
        detectionResultMerge.setDetectionResultColumn(0, rowIndicatorColumn);
        detectionResultMerge.setDetectionResultColumn(barcodeColumnCount, rowIndicatorColumn2);
        boolean z = rowIndicatorColumn != null;
        for (int i4 = 1; i4 <= barcodeColumnCount; i4++) {
            int i5 = z ? i4 : barcodeColumnCount - i4;
            if (detectionResultMerge.getDetectionResultColumn(i5) == null) {
                DetectionResultColumn detectionResultRowIndicatorColumn = (i5 == 0 || i5 == barcodeColumnCount) ? new DetectionResultRowIndicatorColumn(boundingBox, i5 == 0) : new DetectionResultColumn(boundingBox);
                detectionResultMerge.setDetectionResultColumn(i5, detectionResultRowIndicatorColumn);
                int i6 = -1;
                for (int minY = boundingBox.getMinY(); minY <= boundingBox.getMaxY(); minY++) {
                    int startColumn = getStartColumn(detectionResultMerge, i5, minY, z);
                    if (startColumn >= 0 && startColumn <= boundingBox.getMaxX()) {
                        codewordDetectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, startColumn, minY, i, i2);
                        if (codewordDetectCodeword != null) {
                            detectionResultRowIndicatorColumn.setCodeword(minY, codewordDetectCodeword);
                            i6 = startColumn;
                            i = Math.min(i, codewordDetectCodeword.getWidth());
                            i2 = Math.max(i2, codewordDetectCodeword.getWidth());
                        }
                    } else if (i6 != -1) {
                        startColumn = i6;
                        codewordDetectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, startColumn, minY, i, i2);
                        if (codewordDetectCodeword != null) {
                            detectionResultRowIndicatorColumn.setCodeword(minY, codewordDetectCodeword);
                            i6 = startColumn;
                            i = Math.min(i, codewordDetectCodeword.getWidth());
                            i2 = Math.max(i2, codewordDetectCodeword.getWidth());
                        }
                    }
                }
            }
        }
        return createDecoderResult(detectionResultMerge);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i, int[] iArr2) throws ChecksumException, FormatException {
        if (iArr.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int i2 = 1 << (i + 1);
        int iCorrectErrors = correctErrors(iArr, iArr2, i2);
        verifyCodewordCount(iArr, i2);
        DecoderResult decoderResultDecode = DecodedBitStreamParser.decode(iArr, String.valueOf(i));
        decoderResultDecode.setErrorsCorrected(Integer.valueOf(iCorrectErrors));
        decoderResultDecode.setErasures(Integer.valueOf(iArr2.length));
        return decoderResultDecode;
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int decodedValue;
        int codeword;
        int iAdjustCodewordStartColumn = adjustCodewordStartColumn(bitMatrix, i, i2, z, i3, i4);
        int[] moduleBitCount = getModuleBitCount(bitMatrix, i, i2, z, iAdjustCodewordStartColumn, i4);
        if (moduleBitCount == null) {
            return null;
        }
        int iSum = MathUtils.sum(moduleBitCount);
        if (z) {
            i7 = iAdjustCodewordStartColumn + iSum;
        } else {
            for (int i8 = 0; i8 < moduleBitCount.length / 2; i8++) {
                int i9 = moduleBitCount[i8];
                moduleBitCount[i8] = moduleBitCount[(moduleBitCount.length - 1) - i8];
                moduleBitCount[(moduleBitCount.length - 1) - i8] = i9;
            }
            i7 = iAdjustCodewordStartColumn;
            iAdjustCodewordStartColumn -= iSum;
        }
        if (checkCodewordSkew(iSum, i5, i6) && (codeword = PDF417Common.getCodeword((decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount)))) != -1) {
            return new Codeword(iAdjustCodewordStartColumn, i7, getCodewordBucketNumber(decodedValue), codeword);
        }
        return null;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        BarcodeMetadata barcodeMetadata2;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        }
        if (detectionResultRowIndicatorColumn2 == null || (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) == null || barcodeMetadata.getColumnCount() == barcodeMetadata2.getColumnCount() || barcodeMetadata.getErrorCorrectionLevel() == barcodeMetadata2.getErrorCorrectionLevel() || barcodeMetadata.getRowCount() == barcodeMetadata2.getRowCount()) {
            return barcodeMetadata;
        }
        return null;
    }

    private static int[] getBitCountForCodeword(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            if ((i & 1) != i2) {
                i2 = i & 1;
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i) {
        return getCodewordBucketNumber(getBitCountForCodeword(i));
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static int getMax(int[] iArr) {
        int iMax = -1;
        for (int i : iArr) {
            iMax = Math.max(iMax, i);
        }
        return iMax;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0023 A[EDGE_INSN: B:28:0x0023->B:14:0x0023 BREAK  A[LOOP:0: B:6:0x000b->B:34:0x000b], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x002d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0031 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x000f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:9:0x0011  */
    private static int[] getModuleBitCount(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        int i5 = i3;
        int[] iArr = new int[8];
        int i6 = 0;
        int i7 = z ? 1 : -1;
        boolean z2 = z;
        while (true) {
            if (!z) {
                if (i5 >= i) {
                    if (i6 < 8) {
                        break;
                        break;
                    }
                    if (bitMatrix.get(i5, i4) == z2) {
                        iArr[i6] = iArr[i6] + 1;
                        i5 += i7;
                    } else {
                        i6++;
                        if (!z2) {
                        }
                    }
                } else {
                    break;
                }
            } else {
                if (i5 >= i2) {
                    break;
                }
                if (i6 < 8) {
                    break;
                }
                if (bitMatrix.get(i5, i4) == z2) {
                    iArr[i6] = iArr[i6] + 1;
                    i5 += i7;
                } else {
                    i6++;
                    z2 = !z2;
                }
            }
        }
        if (i6 == 8) {
            return iArr;
        }
        if (!z) {
            i2 = i;
        }
        if (i5 == i2 && i6 == 7) {
            return iArr;
        }
        return null;
    }

    private static int getNumberOfECCodeWords(int i) {
        return 2 << i;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            for (int y = (int) resultPoint.getY(); y <= boundingBox.getMaxY() && y >= boundingBox.getMinY(); y += i4) {
                Codeword codewordDetectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i, i2);
                if (codewordDetectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y, codewordDetectCodeword);
                    x = z ? codewordDetectCodeword.getStartX() : codewordDetectCodeword.getEndX();
                }
            }
            i3++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static int getStartColumn(DetectionResult detectionResult, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        Codeword codeword = isValidBarcodeColumn(detectionResult, i - i3) ? detectionResult.getDetectionResultColumn(i - i3).getCodeword(i2) : null;
        if (codeword != null) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i).getCodewordNearby(i2);
        if (codewordNearby != null) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, i - i3)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i - i3).getCodewordNearby(i2);
        }
        if (codewordNearby != null) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i4 = 0;
        while (isValidBarcodeColumn(detectionResult, i - i3)) {
            i -= i3;
            for (Codeword codeword2 : detectionResult.getDetectionResultColumn(i).getCodewords()) {
                if (codeword2 != null) {
                    return (z ? codeword2.getEndX() : codeword2.getStartX()) + (i3 * i4 * (codeword2.getEndX() - codeword2.getStartX()));
                }
            }
            i4++;
        }
        return z ? detectionResult.getBoundingBox().getMinX() : detectionResult.getBoundingBox().getMaxX();
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < barcodeValueArr.length; i++) {
            formatter.format("Row %2d: ", Integer.valueOf(i));
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                BarcodeValue barcodeValue = barcodeValueArr[i][i2];
                if (barcodeValue.getValue().length == 0) {
                    formatter.format("        ", null);
                } else {
                    formatter.format("%4d(%2d)", Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0]));
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    private static void verifyCodewordCount(int[] iArr, int i) throws FormatException {
        if (iArr.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw FormatException.getFormatInstance();
        }
        if (i2 == 0) {
            if (i >= iArr.length) {
                throw FormatException.getFormatInstance();
            }
            iArr[0] = iArr.length - i;
        }
    }
}
