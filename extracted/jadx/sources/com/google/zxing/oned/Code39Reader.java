package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private static final String CHECK_DIGIT_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static {
        int[] iArr = {52, 289, 97, 352, 49, HttpResponseCode.NOT_MODIFIED, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, HttpResponseCode.BAD_REQUEST, 208, 133, 388, 196, 148, 168, 162, 138, 42};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[39];
    }

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '+' || cCharAt == '$' || cCharAt == '%' || cCharAt == '/') {
                char cCharAt2 = charSequence.charAt(i + 1);
                char c = 0;
                switch (cCharAt) {
                    case '$':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        c = (char) (cCharAt2 - '@');
                        sb.append(c);
                        i++;
                        break;
                    case '%':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            c = (char) (cCharAt2 - '&');
                        } else {
                            if (cCharAt2 < 'F' || cCharAt2 > 'W') {
                                throw FormatException.getFormatInstance();
                            }
                            c = (char) (cCharAt2 - 11);
                        }
                        sb.append(c);
                        i++;
                        break;
                    case '+':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        c = (char) (cCharAt2 + ' ');
                        sb.append(c);
                        i++;
                        break;
                    case '/':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                            c = (char) (cCharAt2 - ' ');
                        } else {
                            if (cCharAt2 != 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c = ':';
                        }
                        sb.append(c);
                        i++;
                        break;
                    default:
                        sb.append(c);
                        i++;
                        break;
                }
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int i = 0;
        int i2 = nextSet;
        boolean z = false;
        int length = iArr.length;
        for (int i3 = nextSet; i3 < size; i3++) {
            if (bitArray.get(i3) ^ z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i != length - 1) {
                    i++;
                } else {
                    if (toNarrowWidePattern(iArr) == ASTERISK_ENCODING && bitArray.isRange(Math.max(0, i2 - ((i3 - i2) / 2)), i2, false)) {
                        return new int[]{i2, i3};
                    }
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i--;
                }
                iArr[i] = 1;
                z = !z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i) throws NotFoundException {
        for (int i2 = 0; i2 < CHARACTER_ENCODINGS.length; i2++) {
            if (CHARACTER_ENCODINGS[i2] == i) {
                return ALPHABET_STRING.charAt(i2);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        do {
            int i3 = Integer.MAX_VALUE;
            for (int i4 : iArr) {
                if (i4 < i3 && i4 > i2) {
                    i3 = i4;
                }
            }
            i2 = i3;
            i = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i6 |= 1 << ((length - 1) - i7);
                    i++;
                    i5 += i8;
                }
            }
            if (i == 3) {
                for (int i9 = 0; i9 < length && i > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i--;
                        if ((i10 << 1) >= i5) {
                            return -1;
                        }
                    }
                }
                return i6;
            }
        } while (i > 3);
        return -1;
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        char cPatternToChar;
        int i2;
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        int[] iArrFindAsteriskPattern = findAsteriskPattern(bitArray, iArr);
        int nextSet = bitArray.getNextSet(iArrFindAsteriskPattern[1]);
        int size = bitArray.getSize();
        do {
            recordPattern(bitArray, nextSet, iArr);
            int narrowWidePattern = toNarrowWidePattern(iArr);
            if (narrowWidePattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            cPatternToChar = patternToChar(narrowWidePattern);
            sb.append(cPatternToChar);
            i2 = nextSet;
            for (int i3 : iArr) {
                nextSet += i3;
            }
            nextSet = bitArray.getNextSet(nextSet);
        } while (cPatternToChar != '*');
        sb.setLength(sb.length() - 1);
        int i4 = 0;
        for (int i5 : iArr) {
            i4 += i5;
        }
        int i6 = (nextSet - i2) - i4;
        if (nextSet != size && (i6 << 1) < i4) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (this.usingCheckDigit) {
            int length = sb.length() - 1;
            int iIndexOf = 0;
            for (int i7 = 0; i7 < length; i7++) {
                iIndexOf += CHECK_DIGIT_STRING.indexOf(this.decodeRowResult.charAt(i7));
            }
            if (sb.charAt(length) != CHECK_DIGIT_STRING.charAt(iIndexOf % 43)) {
                throw ChecksumException.getChecksumInstance();
            }
            sb.setLength(length);
        }
        if (sb.length() == 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new Result(this.extendedMode ? decodeExtended(sb) : sb.toString(), null, new ResultPoint[]{new ResultPoint((iArrFindAsteriskPattern[1] + iArrFindAsteriskPattern[0]) / 2.0f, i), new ResultPoint(i2 + (i4 / 2.0f), i)}, BarcodeFormat.CODE_39);
    }
}
