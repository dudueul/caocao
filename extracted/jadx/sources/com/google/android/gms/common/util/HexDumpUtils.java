package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class HexDumpUtils {
    public static byte[] bytesFromString(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\n");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (stringTokenizer.hasMoreTokens()) {
            try {
                byteArrayOutputStream.write(Integer.parseInt(stringTokenizer.nextToken(), 16) & 255);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String dump(byte[] bArr, int i, int i2, boolean z) {
        if (bArr == null || bArr.length == 0 || i < 0 || i2 <= 0 || i + i2 > bArr.length) {
            return null;
        }
        StringBuilder sb = new StringBuilder((z ? 75 : 57) * (((i2 + 16) - 1) / 16));
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        while (i4 > 0) {
            if (i6 == 0) {
                if (i2 < 65536) {
                    sb.append(String.format("%04X:", Integer.valueOf(i3)));
                    i5 = i3;
                } else {
                    sb.append(String.format("%08X:", Integer.valueOf(i3)));
                    i5 = i3;
                }
            } else if (i6 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", Integer.valueOf(bArr[i3] & 255)));
            int i7 = i4 - 1;
            int i8 = i6 + 1;
            if (z && (i8 == 16 || i7 == 0)) {
                int i9 = 16 - i8;
                if (i9 > 0) {
                    for (int i10 = 0; i10 < i9; i10++) {
                        sb.append("   ");
                    }
                }
                if (i9 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int i11 = 0; i11 < i8; i11++) {
                    char c = (char) bArr[i5 + i11];
                    if (c < ' ' || c > '~') {
                        c = '.';
                    }
                    sb.append(c);
                }
            }
            if (i8 == 16 || i7 == 0) {
                sb.append('\n');
                i6 = 0;
            } else {
                i6 = i8;
            }
            i3++;
            i4 = i7;
        }
        return sb.toString();
    }
}
