package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Deprecated
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0021 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x005a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x002b A[EDGE_INSN: B:59:0x002b->B:16:0x002b BREAK  A[LOOP:1: B:11:0x001d->B:64:0x001d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x002b A[EDGE_INSN: B:61:0x002b->B:16:0x002b BREAK  A[LOOP:1: B:11:0x001d->B:64:0x001d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x002b A[EDGE_INSN: B:62:0x002b->B:16:0x002b BREAK  A[LOOP:1: B:11:0x001d->B:64:0x001d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x001d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x001d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x0064 A[EDGE_INSN: B:77:0x0064->B:37:0x0064 BREAK  A[LOOP:3: B:32:0x0056->B:82:0x0056], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x0079 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0064 A[EDGE_INSN: B:79:0x0064->B:37:0x0064 BREAK  A[LOOP:3: B:32:0x0056->B:82:0x0056], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x005c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x0064 A[EDGE_INSN: B:81:0x0064->B:37:0x0064 BREAK  A[LOOP:3: B:32:0x0056->B:82:0x0056], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0056 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0056 A[SYNTHETIC] */
    private int[] blackWhiteRange(int i, int i2, int i3, int i4, boolean z) {
        int i5 = (i3 + i4) / 2;
        int i6 = i5;
        while (i6 >= i3) {
            if (z) {
                if (this.image.get(i6, i)) {
                    i6--;
                } else {
                    int i7 = i6;
                    while (true) {
                        i6--;
                        if (i6 < i3) {
                            break;
                        }
                        if (z) {
                            if (this.image.get(i6, i)) {
                                break;
                            }
                        } else if (this.image.get(i, i6)) {
                            break;
                        }
                    }
                    int i8 = i7 - i6;
                    if (i6 >= i3 || i8 > i2) {
                        i6 = i7;
                        break;
                    }
                }
            } else {
                if (!this.image.get(i, i6)) {
                    int i9 = i6;
                    while (true) {
                        i6--;
                        if (i6 < i3) {
                            break;
                            break;
                        }
                        if (z) {
                            if (this.image.get(i6, i)) {
                                break;
                                break;
                            }
                        } else if (this.image.get(i, i6)) {
                            break;
                            break;
                        }
                    }
                    int i10 = i9 - i6;
                    if (i6 >= i3) {
                    }
                    i6 = i9;
                    break;
                }
                i6--;
            }
        }
        int i11 = i6 + 1;
        int i12 = i5;
        while (i12 < i4) {
            if (z) {
                if (this.image.get(i12, i)) {
                    i12++;
                } else {
                    int i13 = i12;
                    while (true) {
                        i12++;
                        if (i12 >= i4) {
                            break;
                        }
                        if (z) {
                            if (this.image.get(i12, i)) {
                                break;
                            }
                        } else if (this.image.get(i, i12)) {
                            break;
                        }
                    }
                    int i14 = i12 - i13;
                    if (i12 < i4 || i14 > i2) {
                        i12 = i13;
                        break;
                    }
                }
            } else {
                if (!this.image.get(i, i12)) {
                    int i15 = i12;
                    while (true) {
                        i12++;
                        if (i12 >= i4) {
                            break;
                            break;
                        }
                        if (z) {
                            if (this.image.get(i12, i)) {
                                break;
                                break;
                            }
                        } else if (this.image.get(i, i12)) {
                            break;
                            break;
                        }
                    }
                    int i16 = i12 - i15;
                    if (i12 < i4) {
                    }
                    i12 = i15;
                    break;
                }
                i12++;
            }
        }
        int i17 = i12 - 1;
        if (i17 > i11) {
            return new int[]{i11, i17};
        }
        return null;
    }

    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int[] iArr = null;
        int i10 = i5;
        int i11 = i;
        while (i10 < i8 && i10 >= i7 && i11 < i4 && i11 >= i3) {
            int[] iArrBlackWhiteRange = i2 == 0 ? blackWhiteRange(i10, i9, i3, i4, true) : blackWhiteRange(i11, i9, i7, i8, false);
            if (iArrBlackWhiteRange == null) {
                if (iArr == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (i2 == 0) {
                    int i12 = i10 - i6;
                    if (iArr[0] >= i) {
                        return new ResultPoint(iArr[1], i12);
                    }
                    if (iArr[1] > i) {
                        return new ResultPoint(iArr[i6 > 0 ? (char) 0 : (char) 1], i12);
                    }
                    return new ResultPoint(iArr[0], i12);
                }
                int i13 = i11 - i2;
                if (iArr[0] >= i5) {
                    return new ResultPoint(i13, iArr[1]);
                }
                if (iArr[1] > i5) {
                    return new ResultPoint(i13, iArr[i2 < 0 ? (char) 0 : (char) 1]);
                }
                return new ResultPoint(i13, iArr[0]);
            }
            iArr = iArrBlackWhiteRange;
            i10 += i6;
            i11 += i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int iMax = Math.max(1, height / 256);
        int iMax2 = Math.max(1, width / 256);
        int y = ((int) findCornerFromCenter(i2, 0, 0, width, i, -iMax, 0, height, i2 / 2).getY()) - 1;
        ResultPoint resultPointFindCornerFromCenter = findCornerFromCenter(i2, -iMax2, 0, width, i, 0, y, height, i / 2);
        int x = ((int) resultPointFindCornerFromCenter.getX()) - 1;
        ResultPoint resultPointFindCornerFromCenter2 = findCornerFromCenter(i2, iMax2, x, width, i, 0, y, height, i / 2);
        int x2 = ((int) resultPointFindCornerFromCenter2.getX()) + 1;
        ResultPoint resultPointFindCornerFromCenter3 = findCornerFromCenter(i2, 0, x, x2, i, iMax, y, height, i2 / 2);
        return new ResultPoint[]{findCornerFromCenter(i2, 0, x, x2, i, -iMax, y, ((int) resultPointFindCornerFromCenter3.getY()) + 1, i2 / 4), resultPointFindCornerFromCenter, resultPointFindCornerFromCenter2, resultPointFindCornerFromCenter3};
    }
}
