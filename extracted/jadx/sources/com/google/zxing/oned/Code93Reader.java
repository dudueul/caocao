package com.google.zxing.oned;

import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.naver.glink.android.sdk.ui.record.c;
import java.util.Arrays;
import java.util.Map;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Code93Reader extends OneDReader {
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private final int[] counters = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, HttpResponseCode.ENHANCE_YOUR_CLAIM, 418, HttpResponseCode.NOT_FOUND, 402, 394, c.a, 356, 354, 308, 282, 344, 332, 326, HttpResponseCode.MULTIPLE_CHOICES, 278, 436, 434, 428, HttpResponseCode.UNPROCESSABLE_ENTITY, HttpResponseCode.NOT_ACCEPTABLE, 410, 364, 358, 310, 314, HttpResponseCode.FOUND, 468, 466, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 1;
        int iIndexOf = 0;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iIndexOf += ALPHABET_STRING.indexOf(charSequence.charAt(i4)) * i3;
            i3++;
            if (i3 > i2) {
                i3 = 1;
            }
        }
        if (charSequence.charAt(i) != ALPHABET[iIndexOf % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < 'a' || cCharAt > 'd') {
                sb.append(cCharAt);
            } else {
                if (i >= length - 1) {
                    throw FormatException.getFormatInstance();
                }
                char cCharAt2 = charSequence.charAt(i + 1);
                char c = 0;
                switch (cCharAt) {
                    case 'a':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        c = (char) (cCharAt2 - '@');
                        sb.append(c);
                        i++;
                        break;
                    case 'b':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            c = (char) (cCharAt2 - '&');
                        } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                            c = (char) (cCharAt2 - 11);
                        } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                            c = (char) (cCharAt2 + 16);
                        } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                            c = (char) (cCharAt2 + '+');
                        } else {
                            if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c = 127;
                        }
                        sb.append(c);
                        i++;
                        break;
                    case 'c':
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
                    case 'd':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        c = (char) (cCharAt2 + ' ');
                        sb.append(c);
                        i++;
                        break;
                    default:
                        sb.append(c);
                        i++;
                        break;
                }
            }
            i++;
        }
        return sb.toString();
    }

    private int[] findAsteriskPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.counters, 0);
        int[] iArr = this.counters;
        int i = nextSet;
        boolean z = false;
        int length = iArr.length;
        int i2 = 0;
        for (int i3 = nextSet; i3 < size; i3++) {
            if (bitArray.get(i3) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else {
                    if (toPattern(iArr) == ASTERISK_ENCODING) {
                        return new int[]{i, i3};
                    }
                    i += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                z = !z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i) throws NotFoundException {
        for (int i2 = 0; i2 < CHARACTER_ENCODINGS.length; i2++) {
            if (CHARACTER_ENCODINGS[i2] == i) {
                return ALPHABET[i2];
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toPattern(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int i3 = 0;
        int length = iArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            int iRound = Math.round((iArr[i4] * 9.0f) / i);
            if (iRound <= 0 || iRound > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                for (int i5 = 0; i5 < iRound; i5++) {
                    i3 = (i3 << 1) | 1;
                }
            } else {
                i3 <<= iRound;
            }
        }
        return i3;
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        char cPatternToChar;
        int i2;
        int[] iArrFindAsteriskPattern = findAsteriskPattern(bitArray);
        int nextSet = bitArray.getNextSet(iArrFindAsteriskPattern[1]);
        int size = bitArray.getSize();
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        do {
            recordPattern(bitArray, nextSet, iArr);
            int pattern = toPattern(iArr);
            if (pattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            cPatternToChar = patternToChar(pattern);
            sb.append(cPatternToChar);
            i2 = nextSet;
            for (int i3 : iArr) {
                nextSet += i3;
            }
            nextSet = bitArray.getNextSet(nextSet);
        } while (cPatternToChar != '*');
        sb.deleteCharAt(sb.length() - 1);
        int i4 = 0;
        for (int i5 : iArr) {
            i4 += i5;
        }
        if (nextSet == size || !bitArray.get(nextSet)) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (sb.length() < 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        checkChecksums(sb);
        sb.setLength(sb.length() - 2);
        return new Result(decodeExtended(sb), null, new ResultPoint[]{new ResultPoint((iArrFindAsteriskPattern[1] + iArrFindAsteriskPattern[0]) / 2.0f, i), new ResultPoint(i2 + (i4 / 2.0f), i)}, BarcodeFormat.CODE_93);
    }
}
