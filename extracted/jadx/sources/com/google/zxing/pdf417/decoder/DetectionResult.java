package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    DetectionResult(BarcodeMetadata barcodeMetadata, BoundingBox boundingBox) {
        this.barcodeMetadata = barcodeMetadata;
        this.barcodeColumnCount = barcodeMetadata.getColumnCount();
        this.boundingBox = boundingBox;
        this.detectionResultColumns = new DetectionResultColumn[this.barcodeColumnCount + 2];
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.hasValidRowNumber() || codeword2.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(codeword2.getRowNumber());
        return true;
    }

    private static int adjustRowNumberIfValid(int i, int i2, Codeword codeword) {
        if (codeword == null) {
            return i2;
        }
        if (!codeword.hasValidRowNumber()) {
            if (codeword.isValidRowNumber(i)) {
                codeword.setRowNumber(i);
                i2 = 0;
            } else {
                i2++;
            }
        }
        return i2;
    }

    private int adjustRowNumbers() {
        int iAdjustRowNumbersByRow = adjustRowNumbersByRow();
        if (iAdjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int i = 1; i < this.barcodeColumnCount + 1; i++) {
            Codeword[] codewords = this.detectionResultColumns[i].getCodewords();
            for (int i2 = 0; i2 < codewords.length; i2++) {
                if (codewords[i2] != null && !codewords[i2].hasValidRowNumber()) {
                    adjustRowNumbers(i, i2, codewords);
                }
            }
        }
        return iAdjustRowNumbersByRow;
    }

    private void adjustRowNumbers(int i, int i2, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i2];
        Codeword[] codewords = this.detectionResultColumns[i - 1].getCodewords();
        Codeword[] codewords2 = codewords;
        if (this.detectionResultColumns[i + 1] != null) {
            codewords2 = this.detectionResultColumns[i + 1].getCodewords();
        }
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[i2];
        codewordArr2[3] = codewords2[i2];
        if (i2 > 0) {
            codewordArr2[0] = codewordArr[i2 - 1];
            codewordArr2[4] = codewords[i2 - 1];
            codewordArr2[5] = codewords2[i2 - 1];
        }
        if (i2 > 1) {
            codewordArr2[8] = codewordArr[i2 - 2];
            codewordArr2[10] = codewords[i2 - 2];
            codewordArr2[11] = codewords2[i2 - 2];
        }
        if (i2 < codewordArr.length - 1) {
            codewordArr2[1] = codewordArr[i2 + 1];
            codewordArr2[6] = codewords[i2 + 1];
            codewordArr2[7] = codewords2[i2 + 1];
        }
        if (i2 < codewordArr.length - 2) {
            codewordArr2[9] = codewordArr[i2 + 2];
            codewordArr2[12] = codewords[i2 + 2];
            codewordArr2[13] = codewords2[i2 + 2];
        }
        for (int i3 = 0; i3 < 14 && !adjustRowNumber(codeword, codewordArr2[i3]); i3++) {
        }
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        if (this.detectionResultColumns[0] == null || this.detectionResultColumns[this.barcodeColumnCount + 1] == null) {
            return;
        }
        Codeword[] codewords = this.detectionResultColumns[0].getCodewords();
        Codeword[] codewords2 = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
        for (int i = 0; i < codewords.length; i++) {
            if (codewords[i] != null && codewords2[i] != null && codewords[i].getRowNumber() == codewords2[i].getRowNumber()) {
                for (int i2 = 1; i2 <= this.barcodeColumnCount; i2++) {
                    Codeword codeword = this.detectionResultColumns[i2].getCodewords()[i];
                    if (codeword != null) {
                        codeword.setRowNumber(codewords[i].getRowNumber());
                        if (!codeword.hasValidRowNumber()) {
                            this.detectionResultColumns[i2].getCodewords()[i] = null;
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromLRI() {
        if (this.detectionResultColumns[0] == null) {
            return 0;
        }
        int i = 0;
        Codeword[] codewords = this.detectionResultColumns[0].getCodewords();
        for (int i2 = 0; i2 < codewords.length; i2++) {
            if (codewords[i2] != null) {
                int rowNumber = codewords[i2].getRowNumber();
                int iAdjustRowNumberIfValid = 0;
                for (int i3 = 1; i3 < this.barcodeColumnCount + 1 && iAdjustRowNumberIfValid < 2; i3++) {
                    Codeword codeword = this.detectionResultColumns[i3].getCodewords()[i2];
                    if (codeword != null) {
                        iAdjustRowNumberIfValid = adjustRowNumberIfValid(rowNumber, iAdjustRowNumberIfValid, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    private int adjustRowNumbersFromRRI() {
        if (this.detectionResultColumns[this.barcodeColumnCount + 1] == null) {
            return 0;
        }
        int i = 0;
        Codeword[] codewords = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
        for (int i2 = 0; i2 < codewords.length; i2++) {
            if (codewords[i2] != null) {
                int rowNumber = codewords[i2].getRowNumber();
                int iAdjustRowNumberIfValid = 0;
                for (int i3 = this.barcodeColumnCount + 1; i3 > 0 && iAdjustRowNumberIfValid < 2; i3--) {
                    Codeword codeword = this.detectionResultColumns[i3].getCodewords()[i2];
                    if (codeword != null) {
                        iAdjustRowNumberIfValid = adjustRowNumberIfValid(rowNumber, iAdjustRowNumberIfValid, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    DetectionResultColumn getDetectionResultColumn(int i) {
        return this.detectionResultColumns[i];
    }

    DetectionResultColumn[] getDetectionResultColumns() {
        int i;
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int iAdjustRowNumbers = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        do {
            i = iAdjustRowNumbers;
            iAdjustRowNumbers = adjustRowNumbers();
            if (iAdjustRowNumbers <= 0) {
                break;
            }
        } while (iAdjustRowNumbers < i);
        return this.detectionResultColumns;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    void setDetectionResultColumn(int i, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[i] = detectionResultColumn;
    }

    public String toString() {
        DetectionResultColumn detectionResultColumn = this.detectionResultColumns[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = this.detectionResultColumns[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < detectionResultColumn.getCodewords().length; i++) {
            formatter.format("CW %3d:", Integer.valueOf(i));
            for (int i2 = 0; i2 < this.barcodeColumnCount + 2; i2++) {
                if (this.detectionResultColumns[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    Codeword codeword = this.detectionResultColumns[i2].getCodewords()[i];
                    if (codeword == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}
