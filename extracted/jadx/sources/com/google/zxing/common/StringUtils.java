package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING = Charset.defaultCharset().name();
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static {
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING) || EUC_JP.equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING);
    }

    private StringUtils() {
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00df A[PHI: r17
      0x00df: PHI (r17v6 int) = (r17v1 int), (r17v5 int), (r17v1 int) binds: [B:47:0x00c2, B:55:0x00da, B:25:0x007e] A[DONT_GENERATE, DONT_INLINE]] */
    public static String guessEncoding(byte[] bArr, Map<DecodeHintType, ?> map) {
        if (map != null && map.containsKey(DecodeHintType.CHARACTER_SET)) {
            return map.get(DecodeHintType.CHARACTER_SET).toString();
        }
        int length = bArr.length;
        boolean z = true;
        boolean z2 = true;
        boolean z3 = true;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        boolean z4 = bArr.length > 3 && bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65;
        for (int i12 = 0; i12 < length && (z || z2 || z3); i12++) {
            int i13 = bArr[i12] & 255;
            if (z3) {
                if (i > 0) {
                    if ((i13 & 128) != 0) {
                        i--;
                    } else {
                        z3 = false;
                    }
                } else if ((i13 & 128) != 0) {
                    if ((i13 & 64) != 0) {
                        i++;
                        if ((i13 & 32) == 0) {
                            i2++;
                        } else {
                            i++;
                            if ((i13 & 16) == 0) {
                                i3++;
                            } else {
                                i++;
                                if ((i13 & 8) == 0) {
                                    i4++;
                                } else {
                                    z3 = false;
                                }
                            }
                        }
                    } else {
                        z3 = false;
                    }
                }
            }
            if (z) {
                if (i13 > 127 && i13 < 160) {
                    z = false;
                } else if (i13 > 159 && (i13 < 192 || i13 == 215 || i13 == 247)) {
                    i11++;
                }
            }
            if (z2) {
                if (i5 > 0) {
                    if (i13 < 64 || i13 == 127 || i13 > 252) {
                        z2 = false;
                    } else {
                        i5--;
                    }
                } else if (i13 == 128 || i13 == 160 || i13 > 239) {
                    z2 = false;
                } else if (i13 > 160 && i13 < 224) {
                    i6++;
                    i8 = 0;
                    i7++;
                    if (i7 > i9) {
                        i9 = i7;
                    }
                } else if (i13 > 127) {
                    i5++;
                    i7 = 0;
                    i8++;
                    if (i8 > i10) {
                        i10 = i8;
                    }
                } else {
                    i7 = 0;
                    i8 = 0;
                }
            }
        }
        if (z3 && i > 0) {
            z3 = false;
        }
        if (z2 && i5 > 0) {
            z2 = false;
        }
        if (z3 && (z4 || i2 + i3 + i4 > 0)) {
            return "UTF8";
        }
        if (z2 && (ASSUME_SHIFT_JIS || i9 >= 3 || i10 >= 3)) {
            return SHIFT_JIS;
        }
        if (z && z2) {
            return (!(i9 == 2 && i6 == 2) && i11 * 10 < length) ? ISO88591 : SHIFT_JIS;
        }
        if (z) {
            return ISO88591;
        }
        if (z2) {
            return SHIFT_JIS;
        }
        return z3 ? "UTF8" : PLATFORM_DEFAULT_ENCODING;
    }
}
