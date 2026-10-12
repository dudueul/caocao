package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        EXP900[1] = bigIntegerValueOf;
        for (int i = 2; i < EXP900.length; i++) {
            EXP900[i] = EXP900[i - 1].multiply(bigIntegerValueOf);
        }
    }

    private DecodedBitStreamParser() {
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i == BYTE_COMPACTION_MODE_LATCH) {
            int i3 = 0;
            long j = 0;
            int[] iArr2 = new int[6];
            boolean z = false;
            int i4 = iArr[i2];
            i2++;
            while (i2 < iArr[0] && !z) {
                int i5 = i3 + 1;
                iArr2[i3] = i4;
                j = (900 * j) + ((long) i4);
                int i6 = i2 + 1;
                i4 = iArr[i2];
                if (i4 == TEXT_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH || i4 == NUMERIC_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                    i2 = i6 - 1;
                    z = true;
                    i3 = i5;
                } else if (i5 % 5 != 0 || i5 <= 0) {
                    i3 = i5;
                    i2 = i6;
                } else {
                    for (int i7 = 0; i7 < 6; i7++) {
                        byteArrayOutputStream.write((byte) (j >> ((5 - i7) * 8)));
                    }
                    j = 0;
                    i3 = 0;
                    i2 = i6;
                }
            }
            if (i2 == iArr[0] && i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i3] = i4;
                i3++;
            }
            for (int i8 = 0; i8 < i3; i8++) {
                byteArrayOutputStream.write((byte) iArr2[i8]);
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            int i9 = 0;
            long j2 = 0;
            boolean z2 = false;
            while (i2 < iArr[0] && !z2) {
                int i10 = i2 + 1;
                int i11 = iArr[i2];
                if (i11 < TEXT_COMPACTION_MODE_LATCH) {
                    i9++;
                    j2 = (900 * j2) + ((long) i11);
                    i2 = i10;
                } else if (i11 == TEXT_COMPACTION_MODE_LATCH || i11 == BYTE_COMPACTION_MODE_LATCH || i11 == NUMERIC_COMPACTION_MODE_LATCH || i11 == BYTE_COMPACTION_MODE_LATCH_6 || i11 == 928 || i11 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i11 == MACRO_PDF417_TERMINATOR) {
                    i2 = i10 - 1;
                    z2 = true;
                } else {
                    i2 = i10;
                }
                if (i9 % 5 == 0 && i9 > 0) {
                    for (int i12 = 0; i12 < 6; i12++) {
                        byteArrayOutputStream.write((byte) (j2 >> ((5 - i12) * 8)));
                    }
                    j2 = 0;
                    i9 = 0;
                }
            }
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i2;
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int iDecodeMacroBlock;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charsetForName = DEFAULT_ENCODING;
        int i = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        for (int i2 = 1 + 1; i2 < iArr[0]; i2 = iDecodeMacroBlock + 1) {
            switch (i) {
                case TEXT_COMPACTION_MODE_LATCH /* 900 */:
                    iDecodeMacroBlock = textCompaction(iArr, i2, sb);
                    break;
                case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    iDecodeMacroBlock = byteCompaction(i, iArr, charsetForName, i2, sb);
                    break;
                case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                    iDecodeMacroBlock = numericCompaction(iArr, i2, sb);
                    break;
                case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                    sb.append((char) iArr[i2]);
                    iDecodeMacroBlock = i2 + 1;
                    break;
                case MACRO_PDF417_TERMINATOR /* 922 */:
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    throw FormatException.getFormatInstance();
                case ECI_USER_DEFINED /* 925 */:
                    iDecodeMacroBlock = i2 + 1;
                    break;
                case ECI_GENERAL_PURPOSE /* 926 */:
                    iDecodeMacroBlock = i2 + 2;
                    break;
                case ECI_CHARSET /* 927 */:
                    charsetForName = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i2]).name());
                    iDecodeMacroBlock = i2 + 1;
                    break;
                case 928:
                    iDecodeMacroBlock = decodeMacroBlock(iArr, i2, pDF417ResultMetadata);
                    break;
                default:
                    iDecodeMacroBlock = textCompaction(iArr, i2 - 1, sb);
                    break;
            }
            if (iDecodeMacroBlock >= iArr.length) {
                throw FormatException.getFormatInstance();
            }
            i = iArr[iDecodeMacroBlock];
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return string.substring(1);
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int iTextCompaction = textCompaction(iArr, i, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        if (iArr[iTextCompaction] != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            if (iArr[iTextCompaction] != MACRO_PDF417_TERMINATOR) {
                return iTextCompaction;
            }
            pDF417ResultMetadata.setLastSegment(true);
            return iTextCompaction + 1;
        }
        int i3 = iTextCompaction + 1;
        int[] iArr3 = new int[iArr[0] - i3];
        int i4 = 0;
        boolean z = false;
        while (i3 < iArr[0] && !z) {
            int i5 = i3 + 1;
            int i6 = iArr[i3];
            if (i6 < TEXT_COMPACTION_MODE_LATCH) {
                iArr3[i4] = i6;
                i4++;
                i3 = i5;
            } else {
                switch (i6) {
                    case MACRO_PDF417_TERMINATOR /* 922 */:
                        pDF417ResultMetadata.setLastSegment(true);
                        i3 = i5 + 1;
                        z = true;
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            }
        }
        pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i4));
        return i3;
    }

    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        Mode mode = Mode.ALPHA;
        Mode mode2 = Mode.ALPHA;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            char c = 0;
            switch (mode) {
                case ALPHA:
                    if (i3 < 26) {
                        c = (char) (i3 + 65);
                    } else if (i3 == 26) {
                        c = ' ';
                    } else if (i3 == 27) {
                        mode = Mode.LOWER;
                    } else if (i3 == 28) {
                        mode = Mode.MIXED;
                    } else if (i3 == 29) {
                        mode2 = mode;
                        mode = Mode.PUNCT_SHIFT;
                    } else if (i3 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        sb.append((char) iArr2[i2]);
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
                case LOWER:
                    if (i3 < 26) {
                        c = (char) (i3 + 97);
                    } else if (i3 == 26) {
                        c = ' ';
                    } else if (i3 == 27) {
                        mode2 = mode;
                        mode = Mode.ALPHA_SHIFT;
                    } else if (i3 == 28) {
                        mode = Mode.MIXED;
                    } else if (i3 == 29) {
                        mode2 = mode;
                        mode = Mode.PUNCT_SHIFT;
                    } else if (i3 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        sb.append((char) iArr2[i2]);
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
                case MIXED:
                    if (i3 < 25) {
                        c = MIXED_CHARS[i3];
                    } else if (i3 == 25) {
                        mode = Mode.PUNCT;
                    } else if (i3 == 26) {
                        c = ' ';
                    } else if (i3 == 27) {
                        mode = Mode.LOWER;
                    } else if (i3 == 28) {
                        mode = Mode.ALPHA;
                    } else if (i3 == 29) {
                        mode2 = mode;
                        mode = Mode.PUNCT_SHIFT;
                    } else if (i3 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        sb.append((char) iArr2[i2]);
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
                case PUNCT:
                    if (i3 < 29) {
                        c = PUNCT_CHARS[i3];
                    } else if (i3 == 29) {
                        mode = Mode.ALPHA;
                    } else if (i3 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        sb.append((char) iArr2[i2]);
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
                case ALPHA_SHIFT:
                    mode = mode2;
                    if (i3 < 26) {
                        c = (char) (i3 + 65);
                    } else if (i3 == 26) {
                        c = ' ';
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
                case PUNCT_SHIFT:
                    mode = mode2;
                    if (i3 < 29) {
                        c = PUNCT_CHARS[i3];
                    } else if (i3 == 29) {
                        mode = Mode.ALPHA;
                    } else if (i3 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        sb.append((char) iArr2[i2]);
                    } else if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                        mode = Mode.ALPHA;
                    }
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int i2 = 0;
        boolean z = false;
        int[] iArr2 = new int[15];
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4;
                i2++;
                i = i3;
            } else if (i4 == TEXT_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i = i3 - 1;
                z = true;
            } else {
                i = i3;
            }
            if (i2 % 15 == 0 || i4 == NUMERIC_COMPACTION_MODE_LATCH || z) {
                if (i2 > 0) {
                    sb.append(decodeBase900toBase10(iArr2, i2));
                    i2 = 0;
                }
            }
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        int i2 = 0;
        boolean z = false;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 >= TEXT_COMPACTION_MODE_LATCH) {
                switch (i4) {
                    case TEXT_COMPACTION_MODE_LATCH /* 900 */:
                        iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                        i2++;
                        i = i3;
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                    case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                    case MACRO_PDF417_TERMINATOR /* 922 */:
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    case 928:
                        i = i3 - 1;
                        z = true;
                        break;
                    case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                        iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                        i = i3 + 1;
                        iArr3[i2] = iArr[i3];
                        i2++;
                        break;
                    default:
                        i = i3;
                        break;
                }
            } else {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
                i = i3;
            }
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }
}
