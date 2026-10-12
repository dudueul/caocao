package com.nexon.core.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXByteUtil {
    public static byte[] HexStringToBytes(String str) {
        int i = 0;
        byte[] bArr = new byte[str.length() / 2];
        int i2 = 0;
        while (i2 < str.length()) {
            int i3 = i2 + 1;
            bArr[i] = (byte) (Character.digit(str.charAt(i2), 16) << 4);
            i2 = i3 + 1;
            bArr[i] = (byte) (bArr[i] + ((byte) Character.digit(str.charAt(i3), 16)));
            i++;
        }
        return bArr;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            sb.append(String.format(Locale.ENGLISH, "%02X", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static byte[] charArrayToBytes(char[] cArr) {
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr);
        ByteBuffer byteBufferEncode = Charset.forName("UTF-8").encode(charBufferWrap);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(byteBufferEncode.array(), byteBufferEncode.position(), byteBufferEncode.limit());
        Arrays.fill(charBufferWrap.array(), (char) 0);
        Arrays.fill(byteBufferEncode.array(), (byte) 0);
        return bArrCopyOfRange;
    }

    public static boolean isEmptyBytes(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static byte[] replace(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (isEmptyBytes(bArr) || isEmptyBytes(bArr2) || isEmptyBytes(bArr3)) {
            return new byte[0];
        }
        byte[] bArr4 = new byte[(bArr.length + bArr3.length) - bArr2.length];
        for (int i = 0; i < bArr.length; i++) {
            int length = i + bArr2.length;
            if (bArr[i] == bArr2[0] && length < bArr.length) {
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, bArr2.length + i);
                if (Arrays.toString(bArr2).equals(Arrays.toString(bArrCopyOfRange))) {
                    System.arraycopy(bArr, 0, bArr4, 0, i);
                    System.arraycopy(bArr3, 0, bArr4, i, bArr3.length);
                    System.arraycopy(bArr, length, bArr4, bArr3.length + i, bArr.length - length);
                    Arrays.fill(bArrCopyOfRange, (byte) 0);
                    return bArr4;
                }
            }
        }
        return bArr4;
    }
}
