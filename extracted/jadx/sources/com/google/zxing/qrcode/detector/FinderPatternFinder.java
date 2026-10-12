package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    private static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() != finderPattern.getCount()) {
                return finderPattern2.getCount() - finderPattern.getCount();
            }
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return 1;
            }
            return fAbs == fAbs2 ? 0 : -1;
        }
    }

    private static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return -1;
            }
            return fAbs == fAbs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private boolean crossCheckDiagonal(int i, int i2, int i3, int i4) {
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = 0;
        while (i >= i5 && i2 >= i5 && this.image.get(i2 - i5, i - i5)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5++;
        }
        if (i < i5 || i2 < i5) {
            return false;
        }
        while (i >= i5 && i2 >= i5 && !this.image.get(i2 - i5, i - i5) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5++;
        }
        if (i < i5 || i2 < i5 || crossCheckStateCount[1] > i3) {
            return false;
        }
        while (i >= i5 && i2 >= i5 && this.image.get(i2 - i5, i - i5) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i5++;
        }
        if (crossCheckStateCount[0] > i3) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i6 = 1;
        while (i + i6 < height && i2 + i6 < width && this.image.get(i2 + i6, i + i6)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6++;
        }
        if (i + i6 >= height || i2 + i6 >= width) {
            return false;
        }
        while (i + i6 < height && i2 + i6 < width && !this.image.get(i2 + i6, i + i6) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i6++;
        }
        if (i + i6 >= height || i2 + i6 >= width || crossCheckStateCount[3] >= i3) {
            return false;
        }
        while (i + i6 < height && i2 + i6 < width && this.image.get(i2 + i6, i + i6) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i6++;
        }
        return crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) < i4 * 2 && foundPatternCross(crossCheckStateCount);
    }

    private float crossCheckHorizontal(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i5, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i5, i2) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.get(i5, i2) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i5--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < width && bitMatrix.get(i6, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6++;
        }
        if (i6 == width) {
            return Float.NaN;
        }
        while (i6 < width && !bitMatrix.get(i6, i2) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i6++;
        }
        if (i6 == width || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < width && bitMatrix.get(i6, i2) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i6++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i6);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i2, i5)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i2, i5) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.get(i2, i5) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i5--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < height && bitMatrix.get(i2, i6)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6++;
        }
        if (i6 == height) {
            return Float.NaN;
        }
        while (i6 < height && !bitMatrix.get(i2, i6) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i6++;
        }
        if (i6 == height || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < height && bitMatrix.get(i2, i6) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i6++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 * 2 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i6);
        }
        return Float.NaN;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    protected static boolean foundPatternCross(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((3.0f * f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private int[] getCrossCheckStateCount() {
        this.crossCheckStateCount[0] = 0;
        this.crossCheckStateCount[1] = 0;
        this.crossCheckStateCount[2] = 0;
        this.crossCheckStateCount[3] = 0;
        this.crossCheckStateCount[4] = 0;
        return this.crossCheckStateCount;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int i = 0;
        float estimatedModuleSize = 0.0f;
        int size = this.possibleCenters.size();
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i++;
                estimatedModuleSize += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i < 3) {
            return false;
        }
        float f = estimatedModuleSize / size;
        float fAbs = 0.0f;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().getEstimatedModuleSize() - f);
        }
        return fAbs <= 0.05f * estimatedModuleSize;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (size > 3) {
            float f = 0.0f;
            float f2 = 0.0f;
            Iterator<FinderPattern> it = this.possibleCenters.iterator();
            while (it.hasNext()) {
                float estimatedModuleSize = it.next().getEstimatedModuleSize();
                f += estimatedModuleSize;
                f2 += estimatedModuleSize * estimatedModuleSize;
            }
            float f3 = f / size;
            float fSqrt = (float) Math.sqrt((f2 / size) - (f3 * f3));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f3));
            float fMax = Math.max(0.2f * f3, fSqrt);
            int i = 0;
            while (i < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i).getEstimatedModuleSize() - f3) > fMax) {
                    this.possibleCenters.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            float estimatedModuleSize2 = 0.0f;
            Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
            while (it2.hasNext()) {
                estimatedModuleSize2 += it2.next().getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(estimatedModuleSize2 / this.possibleCenters.size()));
            this.possibleCenters.subList(3, this.possibleCenters.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = (height * 3) / 228;
        if (i < 3 || z) {
            i = 3;
        }
        boolean zHaveMultiplyConfirmedCenters = false;
        int[] iArr = new int[5];
        int i2 = i - 1;
        while (i2 < height && !zHaveMultiplyConfirmedCenters) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            int i4 = 0;
            while (i4 < width) {
                if (this.image.get(i4, i2)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (!foundPatternCross(iArr)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                } else if (handlePossibleCenter(iArr, i2, i4, z2)) {
                    i = 2;
                    if (this.hasSkipped) {
                        zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                    } else {
                        int iFindRowSkip = findRowSkip();
                        if (iFindRowSkip > iArr[2]) {
                            i2 += (iFindRowSkip - iArr[2]) - 2;
                            i4 = width - 1;
                        }
                    }
                    i3 = 0;
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                }
                i4++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, width, z2)) {
                i = iArr[0];
                if (this.hasSkipped) {
                    zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                }
            }
            i2 += i;
        }
        FinderPattern[] finderPatternArrSelectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(finderPatternArrSelectBestPatterns);
        return new FinderPatternInfo(finderPatternArrSelectBestPatterns);
    }

    protected final BitMatrix getImage() {
        return this.image;
    }

    protected final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    protected final boolean handlePossibleCenter(int[] iArr, int i, int i2, boolean z) {
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        float fCenterFromEnd = centerFromEnd(iArr, i2);
        float fCrossCheckVertical = crossCheckVertical(i, (int) fCenterFromEnd, iArr[2], i3);
        if (!Float.isNaN(fCrossCheckVertical)) {
            float fCrossCheckHorizontal = crossCheckHorizontal((int) fCenterFromEnd, (int) fCrossCheckVertical, iArr[2], i3);
            if (!Float.isNaN(fCrossCheckHorizontal) && (!z || crossCheckDiagonal((int) fCrossCheckVertical, (int) fCrossCheckHorizontal, iArr[2], i3))) {
                float f = i3 / 7.0f;
                boolean z2 = false;
                for (int i4 = 0; i4 < this.possibleCenters.size(); i4++) {
                    FinderPattern finderPattern = this.possibleCenters.get(i4);
                    if (finderPattern.aboutEquals(f, fCrossCheckVertical, fCrossCheckHorizontal)) {
                        this.possibleCenters.set(i4, finderPattern.combineEstimate(fCrossCheckVertical, fCrossCheckHorizontal, f));
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    FinderPattern finderPattern2 = new FinderPattern(fCrossCheckHorizontal, fCrossCheckVertical, f);
                    this.possibleCenters.add(finderPattern2);
                    if (this.resultPointCallback != null) {
                        this.resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
