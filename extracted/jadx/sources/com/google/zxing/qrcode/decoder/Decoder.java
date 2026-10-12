package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

    private void correctErrors(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.rsDecoder.decode(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException e) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    private DecoderResult decode(BitMatrixParser bitMatrixParser, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        Version version = bitMatrixParser.readVersion();
        ErrorCorrectionLevel errorCorrectionLevel = bitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), version, errorCorrectionLevel);
        int numDataCodewords = 0;
        for (DataBlock dataBlock : dataBlocks) {
            numDataCodewords += dataBlock.getNumDataCodewords();
        }
        byte[] bArr = new byte[numDataCodewords];
        int i = 0;
        int length = dataBlocks.length;
        int i2 = 0;
        while (i2 < length) {
            DataBlock dataBlock2 = dataBlocks[i2];
            byte[] codewords = dataBlock2.getCodewords();
            int numDataCodewords2 = dataBlock2.getNumDataCodewords();
            correctErrors(codewords, numDataCodewords2);
            int i3 = 0;
            int i4 = i;
            while (i3 < numDataCodewords2) {
                bArr[i4] = codewords[i3];
                i3++;
                i4++;
            }
            i2++;
            i = i4;
        }
        return DecodedBitStreamParser.decode(bArr, version, errorCorrectionLevel, map);
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002f  */
    /* JADX WARN: Code duplicated, block: B:14:0x0032 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0034  */
    /* JADX WARN: Code duplicated, block: B:16:0x0035  */
    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws Throwable {
        Throwable th;
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        FormatException e = null;
        ChecksumException e2 = null;
        try {
            return decode(bitMatrixParser, map);
        } catch (ChecksumException e3) {
            e2 = e3;
            try {
                bitMatrixParser.remask();
                bitMatrixParser.setMirror(true);
                bitMatrixParser.readVersion();
                bitMatrixParser.readFormatInformation();
                bitMatrixParser.mirror();
                DecoderResult decoderResultDecode = decode(bitMatrixParser, map);
                decoderResultDecode.setOther(new QRCodeDecoderMetaData(true));
                return decoderResultDecode;
            } catch (ChecksumException e4) {
                th = e4;
                if (e != null) {
                    throw e;
                }
                if (e2 != null) {
                    throw e2;
                }
                throw th;
            } catch (FormatException e5) {
                th = e5;
                if (e != null) {
                    throw e;
                }
                if (e2 != null) {
                    throw e2;
                }
                throw th;
            }
        } catch (FormatException e6) {
            e = e6;
            bitMatrixParser.remask();
            bitMatrixParser.setMirror(true);
            bitMatrixParser.readVersion();
            bitMatrixParser.readFormatInformation();
            bitMatrixParser.mirror();
            DecoderResult decoderResultDecode2 = decode(bitMatrixParser, map);
            decoderResultDecode2.setOther(new QRCodeDecoderMetaData(true));
            return decoderResultDecode2;
        }
    }

    public DecoderResult decode(boolean[][] zArr) throws ChecksumException, FormatException {
        return decode(zArr, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(boolean[][] zArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix, map);
    }
}
