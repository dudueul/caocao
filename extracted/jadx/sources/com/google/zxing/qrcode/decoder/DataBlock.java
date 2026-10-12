package com.google.zxing.qrcode.decoder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
        int count = 0;
        Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
        for (Version.ECB ecb : eCBlocks) {
            count += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[count];
        int i = 0;
        for (Version.ECB ecb2 : eCBlocks) {
            int i2 = 0;
            while (i2 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[i] = new DataBlock(dataCodewords, new byte[eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords]);
                i2++;
                i++;
            }
        }
        int length = dataBlockArr[0].codewords.length;
        int length2 = dataBlockArr.length - 1;
        while (length2 >= 0 && dataBlockArr[length2].codewords.length != length) {
            length2--;
        }
        int i3 = length2 + 1;
        int eCCodewordsPerBlock = length - eCBlocksForLevel.getECCodewordsPerBlock();
        int i4 = 0;
        int i5 = 0;
        while (i5 < eCCodewordsPerBlock) {
            int i6 = 0;
            int i7 = i4;
            while (i6 < i) {
                dataBlockArr[i6].codewords[i5] = bArr[i7];
                i6++;
                i7++;
            }
            i5++;
            i4 = i7;
        }
        int i8 = i3;
        int i9 = i4;
        while (i8 < i) {
            dataBlockArr[i8].codewords[eCCodewordsPerBlock] = bArr[i9];
            i8++;
            i9++;
        }
        int length3 = dataBlockArr[0].codewords.length;
        int i10 = eCCodewordsPerBlock;
        int i11 = i9;
        while (i10 < length3) {
            int i12 = 0;
            int i13 = i11;
            while (i12 < i) {
                dataBlockArr[i12].codewords[i12 < i3 ? i10 : i10 + 1] = bArr[i13];
                i12++;
                i13++;
            }
            i10++;
            i11 = i13;
        }
        return dataBlockArr;
    }

    byte[] getCodewords() {
        return this.codewords;
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
