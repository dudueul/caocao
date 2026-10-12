package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX INFO: compiled from: DeviceUtils.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e {
    private static final l a = l.a("DeviceUtils");
    private static final String b = "H_";
    private static final String c = "M_";

    public static int a() {
        return 1;
    }

    public static String a(Context context) {
        return d(context).getLanguage();
    }

    private static String a(String str) {
        return !com.naver.glink.android.sdk.d.o() ? com.naver.glink.android.sdk.d.b().b.name().toLowerCase() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str : str;
    }

    public static String b() {
        return "1.0";
    }

    public static String b(Context context) {
        return d(context).getCountry().toUpperCase();
    }

    private static String b(String str) throws NoSuchAlgorithmException {
        a.a("MD5 Source=%s", str);
        if (v.a((CharSequence) str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes(), 0, str.length());
        byte[] bArrDigest = messageDigest.digest();
        for (byte b2 : bArrDigest) {
            int i = b2 & 255;
            if (i <= 15) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        String upperCase = stringBuffer.toString().toUpperCase();
        a.a("MD5 Result=%s", upperCase);
        return upperCase;
    }

    public static String c() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static String c(Context context) {
        String str;
        String str2 = e() + f() + e(context);
        a.a("DeviceId Source=%s", str2);
        try {
            str = c + b(str2);
        } catch (Exception e) {
            a.a("CREATE DEVICE ID ERROR:", e);
            str = null;
        }
        if (v.a((CharSequence) str)) {
            str = b + e();
        }
        String strA = a(str);
        a.a("DeviceId =%s", strA);
        return strA;
    }

    public static String d() {
        return Build.VERSION.RELEASE;
    }

    private static Locale d(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    private static String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("35");
        stringBuffer.append(Build.BOARD.length() % 10);
        stringBuffer.append(Build.BRAND.length() % 10);
        stringBuffer.append(Build.CPU_ABI.length() % 10);
        stringBuffer.append(Build.DEVICE.length() % 10);
        stringBuffer.append(Build.DISPLAY.length() % 10);
        stringBuffer.append(Build.HOST.length() % 10);
        stringBuffer.append(Build.ID.length() % 10);
        stringBuffer.append(Build.MANUFACTURER.length() % 10);
        stringBuffer.append(Build.MODEL.length() % 10);
        stringBuffer.append(Build.PRODUCT.length() % 10);
        stringBuffer.append(Build.TAGS.length() % 10);
        stringBuffer.append(Build.TYPE.length() % 10);
        stringBuffer.append(Build.USER.length() % 10);
        return stringBuffer.toString();
    }

    private static String e(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
    }

    private static String f() {
        return Build.SERIAL;
    }
}
