package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            for (int i4 = i - i3; i4 <= i + i3; i4++) {
                bitMatrix.set(i4, i - i3);
                bitMatrix.set(i4, i + i3);
                bitMatrix.set(i - i3, i4);
                bitMatrix.set(i + i3, i4);
            }
        }
        bitMatrix.set(i - i2, i - i2);
        bitMatrix.set((i - i2) + 1, i - i2);
        bitMatrix.set(i - i2, (i - i2) + 1);
        bitMatrix.set(i + i2, i - i2);
        bitMatrix.set(i + i2, (i - i2) + 1);
        bitMatrix.set(i + i2, (i + i2) - 1);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        if (z) {
            for (int i3 = 0; i3 < 7; i3++) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
            }
            return;
        }
        for (int i5 = 0; i5 < 10; i5++) {
            int i6 = (i2 - 5) + i5 + (i5 / 5);
            if (bitArray.get(i5)) {
                bitMatrix.set(i6, i2 - 7);
            }
            if (bitArray.get(i5 + 10)) {
                bitMatrix.set(i2 + 7, i6);
            }
            if (bitArray.get(29 - i5)) {
                bitMatrix.set(i6, i2 + 7);
            }
            if (bitArray.get(39 - i5)) {
                bitMatrix.set(i2 - 7, i6);
            }
        }
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    public static AztecCode encode(byte[] bArr, int i, int i2) {
        int i3;
        BitArray bitArrayStuffBits;
        boolean z;
        int iAbs;
        int i4;
        int i5;
        BitArray bitArrayEncode = new HighLevelEncoder(bArr).encode();
        int size = ((bitArrayEncode.getSize() * i) / 100) + 11;
        int size2 = bitArrayEncode.getSize() + size;
        if (i2 != 0) {
            z = i2 < 0;
            iAbs = Math.abs(i2);
            if (iAbs > (z ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i4 = totalBitsInLayer(iAbs, z);
            i3 = WORD_SIZE[iAbs];
            int i6 = i4 - (i4 % i3);
            bitArrayStuffBits = stuffBits(bitArrayEncode, i3);
            if (bitArrayStuffBits.getSize() + size > i6) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z && bitArrayStuffBits.getSize() > (i3 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            i3 = 0;
            bitArrayStuffBits = null;
            int i7 = 0;
            while (true) {
                if (i7 > 32) {
                    throw new IllegalArgumentException("Data too large for an Aztec code");
                }
                z = i7 <= 3;
                iAbs = z ? i7 + 1 : i7;
                i4 = totalBitsInLayer(iAbs, z);
                if (size2 <= i4) {
                    if (i3 != WORD_SIZE[iAbs]) {
                        i3 = WORD_SIZE[iAbs];
                        bitArrayStuffBits = stuffBits(bitArrayEncode, i3);
                    }
                    int i8 = i4 - (i4 % i3);
                    if ((!z || bitArrayStuffBits.getSize() <= (i3 << 6)) && bitArrayStuffBits.getSize() + size <= i8) {
                        break;
                    }
                }
                i7++;
            }
        }
        BitArray bitArrayGenerateCheckWords = generateCheckWords(bitArrayStuffBits, i4, i3);
        int size3 = bitArrayStuffBits.getSize() / i3;
        BitArray bitArrayGenerateModeMessage = generateModeMessage(z, iAbs, size3);
        int i9 = (z ? 11 : 14) + (iAbs << 2);
        int[] iArr = new int[i9];
        if (z) {
            i5 = i9;
            for (int i10 = 0; i10 < iArr.length; i10++) {
                iArr[i10] = i10;
            }
        } else {
            i5 = i9 + 1 + ((((i9 / 2) - 1) / 15) * 2);
            int i11 = i9 / 2;
            int i12 = i5 / 2;
            for (int i13 = 0; i13 < i11; i13++) {
                int i14 = i13 + (i13 / 15);
                iArr[(i11 - i13) - 1] = (i12 - i14) - 1;
                iArr[i11 + i13] = i12 + i14 + 1;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i5);
        int i15 = 0;
        for (int i16 = 0; i16 < iAbs; i16++) {
            int i17 = ((iAbs - i16) << 2) + (z ? 9 : 12);
            for (int i18 = 0; i18 < i17; i18++) {
                int i19 = i18 << 1;
                for (int i20 = 0; i20 < 2; i20++) {
                    if (bitArrayGenerateCheckWords.get(i15 + i19 + i20)) {
                        bitMatrix.set(iArr[(i16 << 1) + i20], iArr[(i16 << 1) + i18]);
                    }
                    if (bitArrayGenerateCheckWords.get((i17 << 1) + i15 + i19 + i20)) {
                        bitMatrix.set(iArr[(i16 << 1) + i18], iArr[((i9 - 1) - (i16 << 1)) - i20]);
                    }
                    if (bitArrayGenerateCheckWords.get((i17 << 2) + i15 + i19 + i20)) {
                        bitMatrix.set(iArr[((i9 - 1) - (i16 << 1)) - i20], iArr[((i9 - 1) - (i16 << 1)) - i18]);
                    }
                    if (bitArrayGenerateCheckWords.get((i17 * 6) + i15 + i19 + i20)) {
                        bitMatrix.set(iArr[((i9 - 1) - (i16 << 1)) - i18], iArr[(i16 << 1) + i20]);
                    }
                }
            }
            i15 += i17 << 3;
        }
        drawModeMessage(bitMatrix, z, i5, bitArrayGenerateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i5 / 2, 5);
        } else {
            drawBullsEye(bitMatrix, i5 / 2, 7);
            int i21 = 0;
            int i22 = 0;
            while (i21 < (i9 / 2) - 1) {
                for (int i23 = (i5 / 2) & 1; i23 < i5; i23 += 2) {
                    bitMatrix.set((i5 / 2) - i22, i23);
                    bitMatrix.set((i5 / 2) + i22, i23);
                    bitMatrix.set(i23, (i5 / 2) - i22);
                    bitMatrix.set(i23, (i5 / 2) + i22);
                }
                i21 += 15;
                i22 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i5);
        aztecCode.setLayers(iAbs);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] iArrBitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(iArrBitsToWords, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : iArrBitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static GenericGF getGF(int i) {
        switch (i) {
            case 4:
                return GenericGF.AZTEC_PARAM;
            case 5:
            case 7:
            case 9:
            case 11:
            default:
                throw new IllegalArgumentException("Unsupported word size " + i);
            case 6:
                return GenericGF.AZTEC_DATA_6;
            case 8:
                return GenericGF.AZTEC_DATA_8;
            case 10:
                return GenericGF.AZTEC_DATA_10;
            case 12:
                return GenericGF.AZTEC_DATA_12;
        }
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                if (i3 + i5 >= size || bitArray.get(i3 + i5)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            if ((i4 & i2) == i2) {
                bitArray2.appendBits(i4 & i2, i);
                i3--;
            } else if ((i4 & i2) == 0) {
                bitArray2.appendBits(i4 | 1, i);
                i3--;
            } else {
                bitArray2.appendBits(i4, i);
            }
            i3 += i;
        }
        return bitArray2;
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }
}
