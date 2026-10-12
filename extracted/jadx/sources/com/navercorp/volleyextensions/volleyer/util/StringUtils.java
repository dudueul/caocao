package com.navercorp.volleyextensions.volleyer.util;

import java.security.SecureRandom;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StringUtils {
    private static char[] chars;

    static {
        initDefaultChars();
    }

    public static String generateRandom(int i) {
        if (i < 0) {
            return null;
        }
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int length = chars.length;
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = chars[secureRandom.nextInt(length)];
        }
        return String.valueOf(cArr);
    }

    private static void initDefaultChars() {
        int length = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length();
        chars = new char[length];
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".getChars(0, length, chars, 0);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
