package com.google.android.gms.common.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Hex {
    private static final char[] zzzz = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzaaa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String bytesToColonDelimitedStringLowercase(byte[] bArr) {
        int i = 0;
        if (bArr.length == 0) {
            return new String();
        }
        char[] cArr = new char[(bArr.length * 3) - 1];
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 >= bArr.length - 1) {
                int i4 = bArr[bArr.length - 1] & 255;
                cArr[i3] = zzaaa[i4 >>> 4];
                cArr[i3 + 1] = zzaaa[i4 & 15];
                return new String(cArr);
            }
            int i5 = bArr[i2] & 255;
            int i6 = i3 + 1;
            cArr[i3] = zzaaa[i5 >>> 4];
            int i7 = i6 + 1;
            cArr[i6] = zzaaa[i5 & 15];
            i = i7 + 1;
            cArr[i7] = ':';
            i2++;
        }
    }

    public static String bytesToColonDelimitedStringUppercase(byte[] bArr) {
        int i = 0;
        if (bArr.length == 0) {
            return new String();
        }
        char[] cArr = new char[(bArr.length * 3) - 1];
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 >= bArr.length - 1) {
                int i4 = bArr[bArr.length - 1] & 255;
                cArr[i3] = zzzz[i4 >>> 4];
                cArr[i3 + 1] = zzzz[i4 & 15];
                return new String(cArr);
            }
            int i5 = bArr[i2] & 255;
            int i6 = i3 + 1;
            cArr[i3] = zzzz[i5 >>> 4];
            int i7 = i6 + 1;
            cArr[i6] = zzzz[i5 & 15];
            i = i7 + 1;
            cArr[i7] = ':';
            i2++;
        }
    }

    public static String bytesToStringLowercase(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[bArr.length << 1];
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 >= bArr.length) {
                return new String(cArr);
            }
            int i4 = bArr[i2] & 255;
            int i5 = i3 + 1;
            cArr[i3] = zzaaa[i4 >>> 4];
            i = i5 + 1;
            cArr[i5] = zzaaa[i4 & 15];
            i2++;
        }
    }

    public static String bytesToStringUppercase(byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    public static String bytesToStringUppercase(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length << 1);
        for (int i = 0; i < length && (!z || i != length - 1 || (bArr[i] & 255) != 0); i++) {
            sb.append(zzzz[(bArr[i] & 240) >>> 4]);
            sb.append(zzzz[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public static byte[] colonDelimitedStringToBytes(String str) {
        return stringToBytes(str.replace(":", ""));
    }

    public static byte[] stringToBytes(String str) throws IllegalArgumentException {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
        }
        return bArr;
    }
}
