package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class CodaBarReader extends OneDReader {
    private static final float MAX_ACCEPTABLE = 2.0f;
    private static final int MIN_CHARACTER_LENGTH = 3;
    private static final float PADDING = 1.5f;
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
    static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    static final int[] CHARACTER_ENCODINGS = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] STARTEND_ENCODING = {'A', 'B', 'C', 'D'};
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private int[] counters = new int[80];
    private int counterLength = 0;

    static boolean arrayContains(char[] cArr, char c) {
        if (cArr == null) {
            return false;
        }
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private void counterAppend(int i) {
        this.counters[this.counterLength] = i;
        this.counterLength++;
        if (this.counterLength >= this.counters.length) {
            int[] iArr = new int[this.counterLength << 1];
            System.arraycopy(this.counters, 0, iArr, 0, this.counterLength);
            this.counters = iArr;
        }
    }

    private int findStartPattern() throws NotFoundException {
        for (int i = 1; i < this.counterLength; i += 2) {
            int narrowWidePattern = toNarrowWidePattern(i);
            if (narrowWidePattern != -1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.counters[i3];
                }
                if (i == 1 || this.counters[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void setCounters(BitArray bitArray) throws NotFoundException {
        this.counterLength = 0;
        int nextUnset = bitArray.getNextUnset(0);
        int size = bitArray.getSize();
        if (nextUnset >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = true;
        int i = 0;
        while (nextUnset < size) {
            if (bitArray.get(nextUnset) ^ z) {
                i++;
            } else {
                counterAppend(i);
                i = 1;
                z = !z;
            }
            nextUnset++;
        }
        counterAppend(i);
    }

    private int toNarrowWidePattern(int i) {
        int i2 = i + 7;
        if (i2 >= this.counterLength) {
            return -1;
        }
        int[] iArr = this.counters;
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        for (int i5 = i; i5 < i2; i5 += 2) {
            int i6 = iArr[i5];
            if (i6 < i4) {
                i4 = i6;
            }
            if (i6 > i3) {
                i3 = i6;
            }
        }
        int i7 = (i4 + i3) / 2;
        int i8 = 0;
        int i9 = Integer.MAX_VALUE;
        for (int i10 = i + 1; i10 < i2; i10 += 2) {
            int i11 = iArr[i10];
            if (i11 < i9) {
                i9 = i11;
            }
            if (i11 > i8) {
                i8 = i11;
            }
        }
        int i12 = (i9 + i8) / 2;
        int i13 = 128;
        int i14 = 0;
        for (int i15 = 0; i15 < 7; i15++) {
            i13 >>= 1;
            if (iArr[i + i15] > ((i15 & 1) == 0 ? i7 : i12)) {
                i14 |= i13;
            }
        }
        for (int i16 = 0; i16 < CHARACTER_ENCODINGS.length; i16++) {
            if (CHARACTER_ENCODINGS[i16] == i14) {
                return i16;
            }
        }
        return -1;
    }

    private void validatePattern(int i) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.decodeRowResult.length() - 1;
        int i2 = i;
        int i3 = 0;
        while (true) {
            int i4 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i3)];
            for (int i5 = 6; i5 >= 0; i5--) {
                int i6 = (i5 & 1) + ((i4 & 1) << 1);
                iArr[i6] = iArr[i6] + this.counters[i2 + i5];
                iArr2[i6] = iArr2[i6] + 1;
                i4 >>= 1;
            }
            if (i3 >= length) {
                break;
            }
            i2 += 8;
            i3++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i7 = 0; i7 < 2; i7++) {
            fArr2[i7] = 0.0f;
            fArr2[i7 + 2] = ((iArr[i7] / iArr2[i7]) + (iArr[i7 + 2] / iArr2[i7 + 2])) / 2.0f;
            fArr[i7] = fArr2[i7 + 2];
            fArr[i7 + 2] = ((iArr[i7 + 2] * 2.0f) + PADDING) / iArr2[i7 + 2];
        }
        int i8 = i;
        int i9 = 0;
        loop3: while (true) {
            int i10 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i9)];
            for (int i11 = 6; i11 >= 0; i11--) {
                int i12 = (i11 & 1) + ((i10 & 1) << 1);
                int i13 = this.counters[i8 + i11];
                if (i13 < fArr2[i12] || i13 > fArr[i12]) {
                    break loop3;
                }
                i10 >>= 1;
            }
            if (i9 >= length) {
                return;
            }
            i8 += 8;
            i9++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.counters, 0);
        setCounters(bitArray);
        int iFindStartPattern = findStartPattern();
        int i2 = iFindStartPattern;
        this.decodeRowResult.setLength(0);
        do {
            int narrowWidePattern = toNarrowWidePattern(i2);
            if (narrowWidePattern == -1) {
                throw NotFoundException.getNotFoundInstance();
            }
            this.decodeRowResult.append((char) narrowWidePattern);
            i2 += 8;
            if (this.decodeRowResult.length() > 1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                break;
            }
        } while (i2 < this.counterLength);
        int i3 = this.counters[i2 - 1];
        int i4 = 0;
        for (int i5 = -8; i5 < -1; i5++) {
            i4 += this.counters[i2 + i5];
        }
        if (i2 < this.counterLength && i3 < i4 / 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        validatePattern(iFindStartPattern);
        for (int i6 = 0; i6 < this.decodeRowResult.length(); i6++) {
            this.decodeRowResult.setCharAt(i6, ALPHABET[this.decodeRowResult.charAt(i6)]);
        }
        if (!arrayContains(STARTEND_ENCODING, this.decodeRowResult.charAt(0))) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (!arrayContains(STARTEND_ENCODING, this.decodeRowResult.charAt(this.decodeRowResult.length() - 1))) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (this.decodeRowResult.length() <= 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
            this.decodeRowResult.deleteCharAt(this.decodeRowResult.length() - 1);
            this.decodeRowResult.deleteCharAt(0);
        }
        int i7 = 0;
        for (int i8 = 0; i8 < iFindStartPattern; i8++) {
            i7 += this.counters[i8];
        }
        float f = i7;
        for (int i9 = iFindStartPattern; i9 < i2 - 1; i9++) {
            i7 += this.counters[i9];
        }
        return new Result(this.decodeRowResult.toString(), null, new ResultPoint[]{new ResultPoint(f, i), new ResultPoint(i7, i)}, BarcodeFormat.CODABAR);
    }
}
