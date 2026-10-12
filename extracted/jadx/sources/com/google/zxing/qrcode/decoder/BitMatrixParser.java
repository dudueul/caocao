package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix;
    }

    private int copyBit(int i, int i2, int i3) {
        return this.mirror ? this.bitMatrix.get(i2, i) : this.bitMatrix.get(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    void mirror() {
        for (int i = 0; i < this.bitMatrix.getWidth(); i++) {
            for (int i2 = i + 1; i2 < this.bitMatrix.getHeight(); i2++) {
                if (this.bitMatrix.get(i, i2) != this.bitMatrix.get(i2, i)) {
                    this.bitMatrix.flip(i2, i);
                    this.bitMatrix.flip(i, i2);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00a2 A[PHI: r2 r5
      0x00a2: PHI (r2v4 int) = (r2v3 int), (r2v6 int) binds: [B:15:0x005c, B:20:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x00a2: PHI (r5v4 int) = (r5v3 int), (r5v7 int) binds: [B:15:0x005c, B:20:0x007a] A[DONT_GENERATE, DONT_INLINE]] */
    byte[] readCodewords() throws FormatException {
        int i;
        FormatInformation formatInformation = readFormatInformation();
        Version version = readVersion();
        DataMask dataMask = DataMask.values()[formatInformation.getDataMask()];
        int height = this.bitMatrix.getHeight();
        dataMask.unmaskBitMatrix(this.bitMatrix, height);
        BitMatrix bitMatrixBuildFunctionPattern = version.buildFunctionPattern();
        boolean z = true;
        byte[] bArr = new byte[version.getTotalCodewords()];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = height - 1;
        while (i5 > 0) {
            if (i5 == 6) {
                i5--;
            }
            int i6 = 0;
            while (i6 < height) {
                int i7 = z ? (height - 1) - i6 : i6;
                int i8 = 0;
                while (true) {
                    i = i2;
                    if (i8 < 2) {
                        if (bitMatrixBuildFunctionPattern.get(i5 - i8, i7)) {
                            i2 = i;
                        } else {
                            i4++;
                            i3 <<= 1;
                            if (this.bitMatrix.get(i5 - i8, i7)) {
                                i3 |= 1;
                            }
                            if (i4 == 8) {
                                i2 = i + 1;
                                bArr[i] = (byte) i3;
                                i4 = 0;
                                i3 = 0;
                            } else {
                                i2 = i;
                            }
                        }
                        i8++;
                    }
                }
                i6++;
                i2 = i;
            }
            z = !z;
            i5 -= 2;
        }
        if (i2 != version.getTotalCodewords()) {
            throw FormatException.getFormatInstance();
        }
        return bArr;
    }

    FormatInformation readFormatInformation() throws FormatException {
        if (this.parsedFormatInfo != null) {
            return this.parsedFormatInfo;
        }
        int iCopyBit = 0;
        for (int i = 0; i < 6; i++) {
            iCopyBit = copyBit(i, 8, iCopyBit);
        }
        int iCopyBit2 = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, iCopyBit)));
        for (int i2 = 5; i2 >= 0; i2--) {
            iCopyBit2 = copyBit(8, i2, iCopyBit2);
        }
        int height = this.bitMatrix.getHeight();
        int iCopyBit3 = 0;
        int i3 = height - 7;
        for (int i4 = height - 1; i4 >= i3; i4--) {
            iCopyBit3 = copyBit(8, i4, iCopyBit3);
        }
        for (int i5 = height - 8; i5 < height; i5++) {
            iCopyBit3 = copyBit(i5, 8, iCopyBit3);
        }
        this.parsedFormatInfo = FormatInformation.decodeFormatInformation(iCopyBit2, iCopyBit3);
        if (this.parsedFormatInfo != null) {
            return this.parsedFormatInfo;
        }
        throw FormatException.getFormatInstance();
    }

    Version readVersion() throws FormatException {
        if (this.parsedVersion != null) {
            return this.parsedVersion;
        }
        int height = this.bitMatrix.getHeight();
        int i = (height - 17) / 4;
        if (i <= 6) {
            return Version.getVersionForNumber(i);
        }
        int iCopyBit = 0;
        int i2 = height - 11;
        for (int i3 = 5; i3 >= 0; i3--) {
            for (int i4 = height - 9; i4 >= i2; i4--) {
                iCopyBit = copyBit(i4, i3, iCopyBit);
            }
        }
        Version versionDecodeVersionInformation = Version.decodeVersionInformation(iCopyBit);
        if (versionDecodeVersionInformation != null && versionDecodeVersionInformation.getDimensionForVersion() == height) {
            this.parsedVersion = versionDecodeVersionInformation;
            return versionDecodeVersionInformation;
        }
        int iCopyBit2 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = height - 9; i6 >= i2; i6--) {
                iCopyBit2 = copyBit(i5, i6, iCopyBit2);
            }
        }
        Version versionDecodeVersionInformation2 = Version.decodeVersionInformation(iCopyBit2);
        if (versionDecodeVersionInformation2 == null || versionDecodeVersionInformation2.getDimensionForVersion() != height) {
            throw FormatException.getFormatInstance();
        }
        this.parsedVersion = versionDecodeVersionInformation2;
        return versionDecodeVersionInformation2;
    }

    void remask() {
        if (this.parsedFormatInfo == null) {
            return;
        }
        DataMask.values()[this.parsedFormatInfo.getDataMask()].unmaskBitMatrix(this.bitMatrix, this.bitMatrix.getHeight());
    }

    void setMirror(boolean z) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = z;
    }
}
