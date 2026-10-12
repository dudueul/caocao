package com.naver.api.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Base64 {
    private static final char[] INT_TO_BASE64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    public static String encodeBase64(byte[] bArr) {
        int length = bArr.length;
        int i = length / 3;
        int i2 = length - (i * 3);
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        char[] cArr = INT_TO_BASE64;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = i4 + 1;
            int i6 = bArr[i4] & 255;
            int i7 = i5 + 1;
            int i8 = bArr[i5] & 255;
            int i9 = bArr[i7] & 255;
            stringBuffer.append(cArr[i6 >> 2]);
            stringBuffer.append(cArr[((i6 << 4) & 63) | (i8 >> 4)]);
            stringBuffer.append(cArr[((i8 << 2) & 63) | (i9 >> 6)]);
            stringBuffer.append(cArr[i9 & 63]);
            i3++;
            i4 = i7 + 1;
        }
        if (i2 != 0) {
            int i10 = i4 + 1;
            int i11 = bArr[i4] & 255;
            stringBuffer.append(cArr[i11 >> 2]);
            if (i2 == 1) {
                stringBuffer.append(cArr[(i11 << 4) & 63]);
                stringBuffer.append("==");
            } else {
                i4 = i10 + 1;
                int i12 = bArr[i10] & 255;
                stringBuffer.append(cArr[((i11 << 4) & 63) | (i12 >> 4)]);
                stringBuffer.append(cArr[(i12 << 2) & 63]);
                stringBuffer.append('=');
            }
        }
        return stringBuffer.toString();
    }
}
