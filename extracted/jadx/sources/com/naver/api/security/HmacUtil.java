package com.naver.api.security;

import com.naver.api.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HmacUtil {
    private static final String ALGORITHM = "HmacSHA1";
    private static final String AMPERCENT = "&";
    private static final int MAX_MESSAGESIZE = 255;
    private static final String MD = "&md=";
    private static final String MSGPAD = "msgpad=";
    private static final String QUESTION = "?";
    private static final String UTF8 = "utf-8";

    public static Mac getMac(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), ALGORITHM);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(secretKeySpec);
        return mac;
    }

    public static String getMessage(String str, String str2) {
        String strSubstring = str.substring(0, Math.min(255, str.length()));
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(strSubstring).append(str2);
        return sb.toString();
    }

    public static String getMessageDigest(String str, String str2) throws Exception {
        return getMessageDigest(getMac(str), str2);
    }

    public static String getMessageDigest(Mac mac, String str) {
        byte[] bArrDoFinal;
        synchronized (mac) {
            bArrDoFinal = mac.doFinal(str.getBytes());
        }
        return Base64.encodeBase64(bArrDoFinal);
    }

    public static String makeEncryptUrl(Mac mac, String str) throws Exception {
        return makeEncryptUrlCore(mac, str, String.valueOf(Calendar.getInstance().getTimeInMillis()));
    }

    public static String makeEncryptUrl(Mac mac, String str, long j) throws Exception {
        return makeEncryptUrlCore(mac, str, String.valueOf(Calendar.getInstance().getTimeInMillis() + j));
    }

    private static String makeEncryptUrlCore(Mac mac, String str, String str2) throws UnsupportedEncodingException {
        String strEncode = URLEncoder.encode(getMessageDigest(mac, getMessage(str, str2)), "utf-8");
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(str);
        if (str.contains(QUESTION)) {
            sb.append(AMPERCENT);
        } else {
            sb.append(QUESTION);
        }
        sb.append(MSGPAD).append(str2).append(MD).append(strEncode);
        return sb.toString();
    }

    public static String makeEncryptUrlWithMsgpad(Mac mac, String str, long j) throws Exception {
        return makeEncryptUrlCore(mac, str, String.valueOf(j));
    }
}
