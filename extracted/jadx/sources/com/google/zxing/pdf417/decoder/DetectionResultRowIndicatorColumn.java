package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.isLeft = z;
    }

    private void adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int iImageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int iImageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        Codeword[] codewords = getCodewords();
        int rowNumber = -1;
        int iMax = 1;
        int i = 0;
        for (int i2 = iImageRowToCodewordIndex; i2 < iImageRowToCodewordIndex2; i2++) {
            if (codewords[i2] != null) {
                Codeword codeword = codewords[i2];
                codeword.setRowNumberAsRowIndicatorColumn();
                int rowNumber2 = codeword.getRowNumber() - rowNumber;
                if (rowNumber2 == 0) {
                    i++;
                } else if (rowNumber2 == 1) {
                    iMax = Math.max(iMax, i);
                    i = 1;
                    rowNumber = codeword.getRowNumber();
                } else if (codeword.getRowNumber() >= barcodeMetadata.getRowCount()) {
                    codewords[i2] = null;
                } else {
                    rowNumber = codeword.getRowNumber();
                    i = 1;
                }
            }
        }
    }

    private void removeIncorrectCodewords(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int i = 0; i < codewordArr.length; i++) {
            Codeword codeword = codewordArr[i];
            if (codewordArr[i] != null) {
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (rowNumber <= barcodeMetadata.getRowCount()) {
                    if (!this.isLeft) {
                        rowNumber += 2;
                    }
                    switch (rowNumber % 3) {
                        case 0:
                            if ((value * 3) + 1 != barcodeMetadata.getRowCountUpperPart()) {
                                codewordArr[i] = null;
                            }
                            break;
                        case 1:
                            if (value / 3 != barcodeMetadata.getErrorCorrectionLevel() || value % 3 != barcodeMetadata.getRowCountLowerPart()) {
                                codewordArr[i] = null;
                            }
                            break;
                        case 2:
                            if (value + 1 != barcodeMetadata.getColumnCount()) {
                                codewordArr[i] = null;
                            }
                            break;
                    }
                } else {
                    codewordArr[i] = null;
                }
            }
        }
    }

    private void setRowNumbers() {
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    void adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        Codeword[] codewords = getCodewords();
        setRowNumbers();
        removeIncorrectCodewords(codewords, barcodeMetadata);
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int iImageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int iImageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        int rowNumber = -1;
        int iMax = 1;
        int i = 0;
        int i2 = iImageRowToCodewordIndex;
        while (i2 < iImageRowToCodewordIndex2) {
            if (codewords[i2] != null) {
                Codeword codeword = codewords[i2];
                int rowNumber2 = codeword.getRowNumber() - rowNumber;
                if (rowNumber2 == 0) {
                    i++;
                } else if (rowNumber2 == 1) {
                    iMax = Math.max(iMax, i);
                    i = 1;
                    rowNumber = codeword.getRowNumber();
                } else if (rowNumber2 < 0 || codeword.getRowNumber() >= barcodeMetadata.getRowCount() || rowNumber2 > i2) {
                    codewords[i2] = null;
                } else {
                    int i3 = iMax > 2 ? (iMax - 2) * rowNumber2 : rowNumber2;
                    boolean z = i3 >= i2;
                    for (int i4 = 1; i4 <= i3 && !z; i4++) {
                        z = codewords[i2 - i4] != null;
                    }
                    if (z) {
                        codewords[i2] = null;
                    } else {
                        rowNumber = codeword.getRowNumber();
                        i = 1;
                    }
                }
            }
            i2++;
        }
    }

    BarcodeMetadata getBarcodeMetadata() {
        Codeword[] codewords = getCodewords();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : codewords) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (!this.isLeft) {
                    rowNumber += 2;
                }
                switch (rowNumber % 3) {
                    case 0:
                        barcodeValue2.setValue((value * 3) + 1);
                        break;
                    case 1:
                        barcodeValue4.setValue(value / 3);
                        barcodeValue3.setValue(value % 3);
                        break;
                    case 2:
                        barcodeValue.setValue(value + 1);
                        break;
                }
            }
        }
        if (barcodeValue.getValue().length == 0 || barcodeValue2.getValue().length == 0 || barcodeValue3.getValue().length == 0 || barcodeValue4.getValue().length == 0 || barcodeValue.getValue()[0] <= 0 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] < 3 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.getValue()[0], barcodeValue2.getValue()[0], barcodeValue3.getValue()[0], barcodeValue4.getValue()[0]);
        removeIncorrectCodewords(codewords, barcodeMetadata);
        return barcodeMetadata;
    }

    int[] getRowHeights() {
        int rowNumber;
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata();
        if (barcodeMetadata == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata);
        int[] iArr = new int[barcodeMetadata.getRowCount()];
        for (Codeword codeword : getCodewords()) {
            if (codeword != null && (rowNumber = codeword.getRowNumber()) < iArr.length) {
                iArr[rowNumber] = iArr[rowNumber] + 1;
            }
        }
        return iArr;
    }

    boolean isLeft() {
        return this.isLeft;
    }

    @Override // com.google.zxing.pdf417.decoder.DetectionResultColumn
    public String toString() {
        return "IsLeft: " + this.isLeft + '\n' + super.toString();
    }
}
