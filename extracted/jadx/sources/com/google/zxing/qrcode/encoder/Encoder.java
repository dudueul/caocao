package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    private Encoder() {
    }

    static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                bitArray.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            if (i + 1 < length) {
                int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(i + 1));
                if (alphanumericCode2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                i += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                i++;
            }
        }
    }

    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                appendNumericBytes(str, bitArray);
                return;
            case ALPHANUMERIC:
                appendAlphanumericBytes(str, bitArray);
                return;
            case BYTE:
                append8BitBytes(str, bitArray, str2);
                return;
            case KANJI:
                appendKanjiBytes(str, bitArray);
                return;
            default:
                throw new WriterException("Invalid mode: " + mode);
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }

    static void appendKanjiBytes(String str, BitArray bitArray) throws WriterException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                int i3 = (i2 << 8) | (bytes[i + 1] & 255);
                int i4 = -1;
                if (i3 >= 33088 && i3 <= 40956) {
                    i4 = i3 - 33088;
                } else if (i3 >= 57408 && i3 <= 60351) {
                    i4 = i3 - 49472;
                }
                if (i4 == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bitArray.appendBits(((i4 >> 8) * 192) + (i4 & 255), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    static void appendLengthInfo(int i, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        if (i >= (1 << characterCountBits)) {
            throw new WriterException(i + " is bigger than " + ((1 << characterCountBits) - 1));
        }
        bitArray.appendBits(i, characterCountBits);
    }

    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iCharAt = charSequence.charAt(i) - '0';
            if (i + 2 < length) {
                bitArray.appendBits((iCharAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i + 2) - '0'), 10);
                i += 3;
            } else if (i + 1 < length) {
                bitArray.appendBits((iCharAt * 10) + (charSequence.charAt(i + 1) - '0'), 7);
                i += 2;
            } else {
                bitArray.appendBits(iCharAt, 4);
                i++;
            }
        }
    }

    private static int calculateBitsNeeded(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray.getSize() + mode.getCharacterCountBits(version) + bitArray2.getSize();
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i3, byteMatrix);
            int iCalculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (iCalculateMaskPenalty < i) {
                i = iCalculateMaskPenalty;
                i2 = i3;
            }
        }
        return i2;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z = true;
            } else {
                if (getAlphanumericCode(cCharAt) == -1) {
                    return Mode.BYTE;
                }
                z2 = true;
            }
        }
        if (z2) {
            return Mode.ALPHANUMERIC;
        }
        return z ? Mode.NUMERIC : Mode.BYTE;
    }

    private static Version chooseVersion(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            Version versionForNumber = Version.getVersionForNumber(i2);
            if (willFit(i, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, null);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        Version versionRecommendVersion;
        CharacterSetECI characterSetECIByName;
        String string = DEFAULT_BYTE_MODE_ENCODING;
        if (map != null && map.containsKey(EncodeHintType.CHARACTER_SET)) {
            string = map.get(EncodeHintType.CHARACTER_SET).toString();
        }
        Mode modeChooseMode = chooseMode(str, string);
        BitArray bitArray = new BitArray();
        if (modeChooseMode == Mode.BYTE && !DEFAULT_BYTE_MODE_ENCODING.equals(string) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(string)) != null) {
            appendECI(characterSetECIByName, bitArray);
        }
        appendModeInfo(modeChooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, modeChooseMode, bitArray2, string);
        if (map == null || !map.containsKey(EncodeHintType.QR_VERSION)) {
            versionRecommendVersion = recommendVersion(errorCorrectionLevel, modeChooseMode, bitArray, bitArray2);
        } else {
            versionRecommendVersion = Version.getVersionForNumber(Integer.parseInt(map.get(EncodeHintType.QR_VERSION).toString()));
            if (!willFit(calculateBitsNeeded(modeChooseMode, bitArray, bitArray2, versionRecommendVersion), versionRecommendVersion, errorCorrectionLevel)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(modeChooseMode == Mode.BYTE ? bitArray2.getSizeInBytes() : str.length(), versionRecommendVersion, modeChooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        Version.ECBlocks eCBlocksForLevel = versionRecommendVersion.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = versionRecommendVersion.getTotalCodewords() - eCBlocksForLevel.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        BitArray bitArrayInterleaveWithECBytes = interleaveWithECBytes(bitArray3, versionRecommendVersion.getTotalCodewords(), totalCodewords, eCBlocksForLevel.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(modeChooseMode);
        qRCode.setVersion(versionRecommendVersion);
        int dimensionForVersion = versionRecommendVersion.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        int iChooseMaskPattern = chooseMaskPattern(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionRecommendVersion, byteMatrix);
        qRCode.setMaskPattern(iChooseMaskPattern);
        MatrixUtil.buildMatrix(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionRecommendVersion, iChooseMaskPattern, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }

    static byte[] generateECBytes(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static int getAlphanumericCode(int i) {
        if (i < ALPHANUMERIC_TABLE.length) {
            return ALPHANUMERIC_TABLE[i];
        }
        return -1;
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i2 / i3;
        int i9 = i8 + 1;
        int i10 = i7 - i8;
        int i11 = (i7 + 1) - i9;
        if (i10 != i11) {
            throw new WriterException("EC bytes mismatch");
        }
        if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        }
        if (i != ((i8 + i10) * i6) + ((i9 + i11) * i5)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (i4 < i6) {
            iArr[0] = i8;
            iArr2[0] = i10;
        } else {
            iArr[0] = i9;
            iArr2[0] = i11;
        }
    }

    static BitArray interleaveWithECBytes(BitArray bitArray, int i, int i2, int i3) throws WriterException {
        if (bitArray.getSizeInBytes() != i2) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        int i4 = 0;
        int iMax = 0;
        int iMax2 = 0;
        ArrayList arrayList = new ArrayList(i3);
        for (int i5 = 0; i5 < i3; i5++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(i, i2, i3, i5, iArr, iArr2);
            int i6 = iArr[0];
            byte[] bArr = new byte[i6];
            bitArray.toBytes(i4 << 3, bArr, 0, i6);
            byte[] bArrGenerateECBytes = generateECBytes(bArr, iArr2[0]);
            arrayList.add(new BlockPair(bArr, bArrGenerateECBytes));
            iMax = Math.max(iMax, i6);
            iMax2 = Math.max(iMax2, bArrGenerateECBytes.length);
            i4 += iArr[0];
        }
        if (i2 != i4) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = new BitArray();
        for (int i7 = 0; i7 < iMax; i7++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] dataBytes = ((BlockPair) it.next()).getDataBytes();
                if (i7 < dataBytes.length) {
                    bitArray2.appendBits(dataBytes[i7], 8);
                }
            }
        }
        for (int i8 = 0; i8 < iMax2; i8++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] errorCorrectionBytes = ((BlockPair) it2.next()).getErrorCorrectionBytes();
                if (i8 < errorCorrectionBytes.length) {
                    bitArray2.appendBits(errorCorrectionBytes[i8], 8);
                }
            }
        }
        if (i != bitArray2.getSizeInBytes()) {
            throw new WriterException("Interleaving error: " + i + " and " + bitArray2.getSizeInBytes() + " differ.");
        }
        return bitArray2;
    }

    private static boolean isOnlyDoubleByteKanji(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static Version recommendVersion(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    static void terminateBits(int i, BitArray bitArray) throws WriterException {
        int i2 = i << 3;
        if (bitArray.getSize() > i2) {
            throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + i2);
        }
        for (int i3 = 0; i3 < 4 && bitArray.getSize() < i2; i3++) {
            bitArray.appendBit(false);
        }
        int size = bitArray.getSize() & 7;
        if (size > 0) {
            for (int i4 = size; i4 < 8; i4++) {
                bitArray.appendBit(false);
            }
        }
        int sizeInBytes = i - bitArray.getSizeInBytes();
        for (int i5 = 0; i5 < sizeInBytes; i5++) {
            bitArray.appendBits((i5 & 1) == 0 ? 236 : 17, 8);
        }
        if (bitArray.getSize() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    private static boolean willFit(int i, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i + 7) / 8;
    }
}
