package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        for (int i3 = 0; i3 < CODE_PATTERNS.length; i3++) {
            float fPatternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i3], MAX_INDIVIDUAL_VARIANCE);
            if (fPatternMatchVariance < f) {
                f = fPatternMatchVariance;
                i2 = i3;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int i = 0;
        int[] iArr = new int[6];
        int i2 = nextSet;
        boolean z = false;
        for (int i3 = nextSet; i3 < size; i3++) {
            if (bitArray.get(i3) ^ z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i == 5) {
                    float f = MAX_AVG_VARIANCE;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        float fPatternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        if (fPatternMatchVariance < f) {
                            f = fPatternMatchVariance;
                            i4 = i5;
                        }
                    }
                    if (i4 >= 0 && bitArray.isRange(Math.max(0, i2 - ((i3 - i2) / 2)), i2, false)) {
                        return new int[]{i2, i3, i4};
                    }
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, 4);
                    iArr[4] = 0;
                    iArr[5] = 0;
                    i--;
                } else {
                    i++;
                }
                iArr[i] = 1;
                z = !z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:102:0x0201  */
    /* JADX WARN: Code duplicated, block: B:104:0x0211  */
    /* JADX WARN: Code duplicated, block: B:106:0x0217  */
    /* JADX WARN: Code duplicated, block: B:109:0x021d  */
    /* JADX WARN: Code duplicated, block: B:110:0x0221  */
    /* JADX WARN: Code duplicated, block: B:111:0x0223  */
    /* JADX WARN: Code duplicated, block: B:113:0x0229  */
    /* JADX WARN: Code duplicated, block: B:114:0x0234  */
    /* JADX WARN: Code duplicated, block: B:115:0x023f  */
    /* JADX WARN: Code duplicated, block: B:116:0x0243  */
    /* JADX WARN: Code duplicated, block: B:143:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:145:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:146:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:148:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:14:0x0066  */
    /* JADX WARN: Code duplicated, block: B:16:0x0086  */
    /* JADX WARN: Code duplicated, block: B:19:0x008d  */
    /* JADX WARN: Code duplicated, block: B:23:0x009f A[LOOP:1: B:21:0x0097->B:23:0x009f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:27:0x00af  */
    /* JADX WARN: Code duplicated, block: B:35:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:41:0x00df  */
    /* JADX WARN: Code duplicated, block: B:42:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:46:0x0100  */
    /* JADX WARN: Code duplicated, block: B:48:0x0111  */
    /* JADX WARN: Code duplicated, block: B:49:0x0120  */
    /* JADX WARN: Code duplicated, block: B:51:0x0126  */
    /* JADX WARN: Code duplicated, block: B:54:0x012b  */
    /* JADX WARN: Code duplicated, block: B:55:0x012f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0131  */
    /* JADX WARN: Code duplicated, block: B:58:0x0137  */
    /* JADX WARN: Code duplicated, block: B:59:0x0142  */
    /* JADX WARN: Code duplicated, block: B:60:0x014d  */
    /* JADX WARN: Code duplicated, block: B:61:0x014f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:63:0x0157 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0159 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:66:0x0161  */
    /* JADX WARN: Code duplicated, block: B:67:0x0165  */
    /* JADX WARN: Code duplicated, block: B:68:0x0169  */
    /* JADX WARN: Code duplicated, block: B:69:0x016d  */
    /* JADX WARN: Code duplicated, block: B:70:0x0170  */
    /* JADX WARN: Code duplicated, block: B:72:0x0176  */
    /* JADX WARN: Code duplicated, block: B:74:0x017c  */
    /* JADX WARN: Code duplicated, block: B:76:0x018e  */
    /* JADX WARN: Code duplicated, block: B:77:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:79:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:82:0x01af  */
    /* JADX WARN: Code duplicated, block: B:83:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:84:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:86:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:87:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:88:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:89:0x01d4 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:91:0x01dc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x01de A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:94:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:95:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:96:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:97:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:98:0x01f5  */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:94:0x01e6
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.google.zxing.oned.OneDReader
    public com.google.zxing.Result decodeRow(int r39, com.google.zxing.common.BitArray r40, java.util.Map<com.google.zxing.DecodeHintType, ?> r41) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            Method dump skipped, instruction units count: 928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
