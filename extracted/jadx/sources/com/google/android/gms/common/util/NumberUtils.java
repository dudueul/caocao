package com.google.android.gms.common.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@VisibleForTesting
public class NumberUtils {
    private NumberUtils() {
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static long parseHexLong(String str) {
        if (str.length() > 16) {
            throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 37).append("Invalid input: ").append(str).append(" exceeds 16 characters").toString());
        }
        return str.length() == 16 ? Long.parseLong(str.substring(8), 16) | (Long.parseLong(str.substring(0, 8), 16) << 32) : Long.parseLong(str, 16);
    }
}
