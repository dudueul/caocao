package com.google.zxing.datamatrix.decoder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version) {
        Version.ECBlocks eCBlocks = version.getECBlocks();
        int count = 0;
        Version.ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        for (Version.ECB ecb : eCBlocks2) {
            count += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[count];
        int i = 0;
        for (Version.ECB ecb2 : eCBlocks2) {
            int i2 = 0;
            while (i2 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[i] = new DataBlock(dataCodewords, new byte[eCBlocks.getECCodewords() + dataCodewords]);
                i2++;
                i++;
            }
        }
        int length = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        int i3 = length - 1;
        int i4 = 0;
        int i5 = 0;
        while (i5 < i3) {
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
        boolean z = version.getVersionNumber() == 24;
        int i8 = z ? 8 : i;
        int i9 = 0;
        int i10 = i4;
        while (i9 < i8) {
            dataBlockArr[i9].codewords[length - 1] = bArr[i10];
            i9++;
            i10++;
        }
        int length2 = dataBlockArr[0].codewords.length;
        int i11 = length;
        int i12 = i10;
        while (i11 < length2) {
            int i13 = 0;
            int i14 = i12;
            while (i13 < i) {
                int i15 = z ? (i13 + 8) % i : i13;
                dataBlockArr[i15].codewords[(!z || i15 <= 7) ? i11 : i11 - 1] = bArr[i14];
                i13++;
                i14++;
            }
            i11++;
            i12 = i14;
        }
        if (i12 != bArr.length) {
            throw new IllegalArgumentException();
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
