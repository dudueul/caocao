package com.facebook.appevents.internal;

import android.os.Looper;
import com.facebook.internal.Utility;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AppEventUtility {
    private static final String regex = "[-+]*\\d+([\\,\\.]\\d+)*([\\.\\,]\\d+)?";

    public static void assertIsMainThread() {
    }

    public static void assertIsNotMainThread() {
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02x", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }

    private static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static double normalizePrice(String str) {
        try {
            Matcher matcher = Pattern.compile(regex, 8).matcher(str);
            if (!matcher.find()) {
                return 0.0d;
            }
            return NumberFormat.getNumberInstance(Utility.getCurrentLocale()).parse(matcher.group(0)).doubleValue();
        } catch (ParseException e) {
            return 0.0d;
        }
    }
}
