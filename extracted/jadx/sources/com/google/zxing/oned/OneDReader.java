package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.naver.glink.android.sdk.ui.record.c;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class OneDReader implements Reader {
    private Result doDecode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        BitArray bitArray = new BitArray(width);
        int i = height >> 1;
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int iMax = Math.max(1, height >> (z ? 8 : 5));
        int i2 = z ? height : 15;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (i3 + 1) / 2;
            if (!((i3 & 1) == 0)) {
                i4 = -i4;
            }
            int i5 = i + (iMax * i4);
            if (i5 < 0 || i5 >= height) {
                break;
            }
            try {
                bitArray = binaryBitmap.getBlackRow(i5, bitArray);
                for (int i6 = 0; i6 < 2; i6++) {
                    if (i6 == 1) {
                        bitArray.reverse();
                        if (map != null) {
                            if (map.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                                EnumMap enumMap = new EnumMap(DecodeHintType.class);
                                enumMap.putAll(map);
                                enumMap.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                                map = enumMap;
                            }
                        }
                    }
                    try {
                        Result resultDecodeRow = decodeRow(i5, bitArray, map);
                        if (i6 == 1) {
                            resultDecodeRow.putMetadata(ResultMetadataType.ORIENTATION, 180);
                            ResultPoint[] resultPoints = resultDecodeRow.getResultPoints();
                            if (resultPoints != null) {
                                resultPoints[0] = new ResultPoint((width - resultPoints[0].getX()) - 1.0f, resultPoints[0].getY());
                                resultPoints[1] = new ResultPoint((width - resultPoints[1].getX()) - 1.0f, resultPoints[1].getY());
                            }
                        }
                        return resultDecodeRow;
                    } catch (ReaderException e) {
                    }
                }
            } catch (NotFoundException e2) {
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
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

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = !bitArray.get(i);
        int i2 = 0;
        int i3 = i;
        while (i3 < size) {
            if (!(bitArray.get(i3) ^ z)) {
                i2++;
                if (i2 == length) {
                    break;
                }
                iArr[i2] = 1;
                z = !z;
            } else {
                iArr[i2] = iArr[i2] + 1;
            }
            i3++;
        }
        if (i2 != length) {
            if (i2 != length - 1 || i3 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, i + 1, iArr);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            BinaryBitmap binaryBitmapRotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            Result resultDoDecode = doDecode(binaryBitmapRotateCounterClockwise, map);
            Map<ResultMetadataType, Object> resultMetadata = resultDoDecode.getResultMetadata();
            int iIntValue = 270;
            if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                iIntValue = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % c.a;
            }
            resultDoDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(iIntValue));
            ResultPoint[] resultPoints = resultDoDecode.getResultPoints();
            if (resultPoints == null) {
                return resultDoDecode;
            }
            int height = binaryBitmapRotateCounterClockwise.getHeight();
            for (int i = 0; i < resultPoints.length; i++) {
                resultPoints[i] = new ResultPoint((height - resultPoints[i].getY()) - 1.0f, resultPoints[i].getX());
            }
            return resultDoDecode;
        }
    }

    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
