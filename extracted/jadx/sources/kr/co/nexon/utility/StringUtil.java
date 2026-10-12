package kr.co.nexon.utility;

import android.util.Base64;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class StringUtil {
    public static String fileToString(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            inputStream.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String stringToBase64String(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }
}
