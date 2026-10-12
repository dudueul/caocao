package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class RSS14Reader extends AbstractRSSReader {
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair == null) {
            return;
        }
        boolean z = false;
        for (Pair pair2 : collection) {
            if (pair2.getValue() == pair.getValue()) {
                pair2.incrementCount();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(pair);
    }

    private void adjustOddEvenCounts(boolean z, int i) throws NotFoundException {
        int iSum = MathUtils.sum(getOddCounts());
        int iSum2 = MathUtils.sum(getEvenCounts());
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        if (z) {
            if (iSum > 12) {
                z3 = true;
            } else if (iSum < 4) {
                z2 = true;
            }
            if (iSum2 > 12) {
                z5 = true;
            } else if (iSum2 < 4) {
                z4 = true;
            }
        } else {
            if (iSum > 11) {
                z3 = true;
            } else if (iSum < 5) {
                z2 = true;
            }
            if (iSum2 > 10) {
                z5 = true;
            } else if (iSum2 < 4) {
                z4 = true;
            }
        }
        int i2 = (iSum + iSum2) - i;
        boolean z6 = (iSum & 1) == (z ? 1 : 0);
        boolean z7 = (iSum2 & 1) == 1;
        if (i2 == 1) {
            if (z6) {
                if (z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z3 = true;
            } else {
                if (!z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z5 = true;
            }
        } else if (i2 == -1) {
            if (z6) {
                if (z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z2 = true;
            } else {
                if (!z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = true;
            }
        } else {
            if (i2 != 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (z6) {
                if (!z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (iSum < iSum2) {
                    z2 = true;
                    z5 = true;
                } else {
                    z3 = true;
                    z4 = true;
                }
            } else if (z7) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z2) {
            if (z3) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getOddCounts(), getOddRoundingErrors());
        }
        if (z3) {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (z4) {
            if (z5) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (z5) {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return checksumPortion == value;
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String strValueOf = String.valueOf((4537077 * ((long) pair.getValue())) + ((long) pair2.getValue()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - strValueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(strValueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int iCharAt = sb.charAt(i2) - '0';
            if ((i2 & 1) == 0) {
                iCharAt *= 3;
            }
            i += iCharAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        sb.append(i3);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(sb.toString()), null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z) {
            recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            int i = 0;
            for (int length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        int i3 = z ? 16 : 15;
        float fSum = MathUtils.sum(dataCharacterCounters) / i3;
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i4 = 0; i4 < dataCharacterCounters.length; i4++) {
            float f = dataCharacterCounters[i4] / fSum;
            int i5 = (int) (0.5f + f);
            if (i5 <= 0) {
                i5 = 1;
            } else if (i5 > 8) {
                i5 = 8;
            }
            int i6 = i4 / 2;
            if ((i4 & 1) == 0) {
                oddCounts[i6] = i5;
                oddRoundingErrors[i6] = f - i5;
            } else {
                evenCounts[i6] = i5;
                evenRoundingErrors[i6] = f - i5;
            }
        }
        adjustOddEvenCounts(z, i3);
        int i7 = 0;
        int i8 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            i8 = (i8 * 9) + oddCounts[length2];
            i7 += oddCounts[length2];
        }
        int i9 = 0;
        int i10 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            i9 = (i9 * 9) + evenCounts[length3];
            i10 += evenCounts[length3];
        }
        int i11 = i8 + (i9 * 3);
        if (!z) {
            if ((i10 & 1) != 0 || i10 > 10 || i10 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i12 = (10 - i10) / 2;
            int i13 = INSIDE_ODD_WIDEST[i12];
            return new DataCharacter((RSSUtils.getRSSvalue(evenCounts, 9 - i13, false) * INSIDE_ODD_TOTAL_SUBSET[i12]) + RSSUtils.getRSSvalue(oddCounts, i13, true) + INSIDE_GSUM[i12], i11);
        }
        if ((i7 & 1) != 0 || i7 > 12 || i7 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i14 = (12 - i7) / 2;
        int i15 = OUTSIDE_ODD_WIDEST[i14];
        return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i15, false) * OUTSIDE_EVEN_TOTAL_SUBSET[i14]) + RSSUtils.getRSSvalue(evenCounts, 9 - i15, true) + OUTSIDE_GSUM[i14], i11);
    }

    private Pair decodePair(BitArray bitArray, boolean z, int i, Map<DecodeHintType, ?> map) {
        try {
            int[] iArrFindFinderPattern = findFinderPattern(bitArray, 0, z);
            FinderPattern foundFinderPattern = parseFoundFinderPattern(bitArray, i, z, iArrFindFinderPattern);
            ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                float size = (iArrFindFinderPattern[0] + iArrFindFinderPattern[1]) / 2.0f;
                if (z) {
                    size = (bitArray.getSize() - 1) - size;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(size, i));
            }
            DataCharacter dataCharacterDecodeDataCharacter = decodeDataCharacter(bitArray, foundFinderPattern, true);
            DataCharacter dataCharacterDecodeDataCharacter2 = decodeDataCharacter(bitArray, foundFinderPattern, false);
            return new Pair((dataCharacterDecodeDataCharacter.getValue() * 1597) + dataCharacterDecodeDataCharacter2.getValue(), dataCharacterDecodeDataCharacter.getChecksumPortion() + (dataCharacterDecodeDataCharacter2.getChecksumPortion() * 4), foundFinderPattern);
        } catch (NotFoundException e) {
            return null;
        }
    }

    private int[] findFinderPattern(BitArray bitArray, int i, boolean z) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (z == z2) {
                break;
            }
            i++;
        }
        int i2 = 0;
        int i3 = i;
        for (int i4 = i; i4 < size; i4++) {
            if (bitArray.get(i4) ^ z2) {
                decodeFinderCounters[i2] = decodeFinderCounters[i2] + 1;
            } else {
                if (i2 != 3) {
                    i2++;
                } else {
                    if (isFinderPattern(decodeFinderCounters)) {
                        return new int[]{i3, i4};
                    }
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i2--;
                }
                decodeFinderCounters[i2] = 1;
                z2 = !z2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        boolean z2 = bitArray.get(iArr[0]);
        int i2 = iArr[0] - 1;
        while (i2 >= 0 && (bitArray.get(i2) ^ z2)) {
            i2--;
        }
        int i3 = i2 + 1;
        int i4 = iArr[0] - i3;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i4;
        int finderValue = parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        int size = i3;
        int size2 = iArr[1];
        if (z) {
            size = (bitArray.getSize() - 1) - size;
            size2 = (bitArray.getSize() - 1) - size2;
        }
        return new FinderPattern(finderValue, new int[]{i3, iArr[1]}, size, size2, i);
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i, map));
        bitArray.reverse();
        for (Pair pair : this.possibleLeftPairs) {
            if (pair.getCount() > 1) {
                for (Pair pair2 : this.possibleRightPairs) {
                    if (pair2.getCount() > 1 && checkChecksum(pair, pair2)) {
                        return constructResult(pair, pair2);
                    }
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }
}
