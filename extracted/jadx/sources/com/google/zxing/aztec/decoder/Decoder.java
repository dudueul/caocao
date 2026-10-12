package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.naver.glink.android.sdk.api.Responses;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Arrays;
import kr.co.nexon.npaccount.board.NXBoardManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", Responses.i.CAFE_TYPE_R, "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", NXBoardManager.BOARD_HELPCENTER, NXBoardManager.BOARD_FORUM, "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        byte[] bArr = new byte[(zArr.length + 7) / 8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = readByte(zArr, i << 3);
        }
        return bArr;
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        int i;
        GenericGF genericGF;
        if (this.ddata.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            i = 8;
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % i;
        int[] iArr = new int[length];
        int i2 = 0;
        while (i2 < length) {
            iArr[i2] = readCode(zArr, length2, i);
            i2++;
            length2 += i;
        }
        try {
            new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
            int i3 = (1 << i) - 1;
            int i4 = 0;
            for (int i5 = 0; i5 < nbDatablocks; i5++) {
                int i6 = iArr[i5];
                if (i6 == 0 || i6 == i3) {
                    throw FormatException.getFormatInstance();
                }
                if (i6 == 1 || i6 == i3 - 1) {
                    i4++;
                }
            }
            boolean[] zArr2 = new boolean[(nbDatablocks * i) - i4];
            int i7 = 0;
            for (int i8 = 0; i8 < nbDatablocks; i8++) {
                int i9 = iArr[i8];
                if (i9 == 1 || i9 == i3 - 1) {
                    Arrays.fill(zArr2, i7, (i7 + i) - 1, i9 > 1);
                    i7 += i - 1;
                } else {
                    int i10 = i - 1;
                    int i11 = i7;
                    while (i10 >= 0) {
                        int i12 = i11 + 1;
                        zArr2[i11] = ((1 << i10) & i9) != 0;
                        i10--;
                        i11 = i12;
                    }
                    i7 = i11;
                }
            }
            return zArr2;
        } catch (ReedSolomonException e) {
            throw FormatException.getFormatInstance(e);
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean zIsCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i = (zIsCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, zIsCompact)];
        if (zIsCompact) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr[i2] = i2;
            }
        } else {
            int i3 = i / 2;
            int i4 = ((i + 1) + ((((i / 2) - 1) / 15) * 2)) / 2;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = i5 + (i5 / 15);
                iArr[(i3 - i5) - 1] = (i4 - i6) - 1;
                iArr[i3 + i5] = i4 + i6 + 1;
            }
        }
        int i7 = 0;
        for (int i8 = 0; i8 < nbLayers; i8++) {
            int i9 = ((nbLayers - i8) << 2) + (zIsCompact ? 9 : 12);
            int i10 = i8 << 1;
            int i11 = (i - 1) - i10;
            for (int i12 = 0; i12 < i9; i12++) {
                int i13 = i12 << 1;
                for (int i14 = 0; i14 < 2; i14++) {
                    zArr[i7 + i13 + i14] = bitMatrix.get(iArr[i10 + i14], iArr[i10 + i12]);
                    zArr[(i9 * 2) + i7 + i13 + i14] = bitMatrix.get(iArr[i10 + i12], iArr[i11 - i14]);
                    zArr[(i9 * 4) + i7 + i13 + i14] = bitMatrix.get(iArr[i11 - i14], iArr[i11 - i12]);
                    zArr[(i9 * 6) + i7 + i13 + i14] = bitMatrix.get(iArr[i11 - i12], iArr[i10 + i14]);
                }
            }
            i7 += i9 << 3;
        }
        return zArr;
    }

    private static String getCharacter(Table table, int i) {
        switch (table) {
            case UPPER:
                return UPPER_TABLE[i];
            case LOWER:
                return LOWER_TABLE[i];
            case MIXED:
                return MIXED_TABLE[i];
            case PUNCT:
                return PUNCT_TABLE[i];
            case DIGIT:
                return DIGIT_TABLE[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        int i = 0;
        while (i < length) {
            if (table2 != Table.BINARY) {
                int i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int code = readCode(zArr, i, i2);
                i += i2;
                String character = getCharacter(table2, code);
                if (character.startsWith("CTRL_")) {
                    table = table2;
                    table2 = getTable(character.charAt(5));
                    if (character.charAt(6) == 'L') {
                        table = table2;
                    }
                } else {
                    sb.append(character);
                    table2 = table;
                }
            } else {
                if (length - i < 5) {
                    break;
                }
                int code2 = readCode(zArr, i, 5);
                i += 5;
                if (code2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    code2 = readCode(zArr, i, 11) + 31;
                    i += 11;
                }
                for (int i3 = 0; i3 < code2; i3++) {
                    if (length - i < 8) {
                        i = length;
                        break;
                    }
                    sb.append((char) readCode(zArr, i, 8));
                    i += 8;
                }
                table2 = table;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c) {
        switch (c) {
            case 'B':
                return Table.BINARY;
            case 'D':
                return Table.DIGIT;
            case 'L':
                return Table.LOWER;
            case 'M':
                return Table.MIXED;
            case 'P':
                return Table.PUNCT;
            default:
                return Table.UPPER;
        }
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static byte readByte(boolean[] zArr, int i) {
        int length = zArr.length - i;
        return length >= 8 ? (byte) readCode(zArr, i, 8) : (byte) (readCode(zArr, i, length) << (8 - length));
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] zArrCorrectBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(zArrCorrectBits), getEncodedData(zArrCorrectBits), null, null);
        decoderResult.setNumBits(zArrCorrectBits.length);
        return decoderResult;
    }
}
