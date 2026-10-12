package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox) {
        this.boundingBox = new BoundingBox(boundingBox);
        this.codewords = new Codeword[(boundingBox.getMaxY() - boundingBox.getMinY()) + 1];
    }

    final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    final Codeword getCodeword(int i) {
        return this.codewords[imageRowToCodewordIndex(i)];
    }

    final Codeword getCodewordNearby(int i) {
        Codeword codeword = getCodeword(i);
        if (codeword != null) {
            return codeword;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int iImageRowToCodewordIndex = imageRowToCodewordIndex(i) - i2;
            if (iImageRowToCodewordIndex >= 0 && (codeword = this.codewords[iImageRowToCodewordIndex]) != null) {
                return codeword;
            }
            int iImageRowToCodewordIndex2 = imageRowToCodewordIndex(i) + i2;
            if (iImageRowToCodewordIndex2 < this.codewords.length && (codeword = this.codewords[iImageRowToCodewordIndex2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    final Codeword[] getCodewords() {
        return this.codewords;
    }

    final int imageRowToCodewordIndex(int i) {
        return i - this.boundingBox.getMinY();
    }

    final void setCodeword(int i, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(i)] = codeword;
    }

    public String toString() {
        int i;
        Formatter formatter = new Formatter();
        Codeword[] codewordArr = this.codewords;
        int length = codewordArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Codeword codeword = codewordArr[i2];
            if (codeword == null) {
                i = i3 + 1;
                formatter.format("%3d:    |   %n", Integer.valueOf(i3));
            } else {
                i = i3 + 1;
                formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i3), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
            }
            i2++;
            i3 = i;
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}
