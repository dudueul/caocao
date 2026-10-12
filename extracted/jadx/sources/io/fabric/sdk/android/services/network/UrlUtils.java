package io.fabric.sdk.android.services.network;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.TreeMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class UrlUtils {
    public static final String UTF8 = "UTF8";

    private UrlUtils() {
    }

    public static TreeMap<String, String> getQueryParams(String str, boolean z) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length == 2) {
                    if (z) {
                        treeMap.put(urlDecode(strArrSplit[0]), urlDecode(strArrSplit[1]));
                    } else {
                        treeMap.put(strArrSplit[0], strArrSplit[1]);
                    }
                } else if (!TextUtils.isEmpty(strArrSplit[0])) {
                    if (z) {
                        treeMap.put(urlDecode(strArrSplit[0]), "");
                    } else {
                        treeMap.put(strArrSplit[0], "");
                    }
                }
            }
        }
        return treeMap;
    }

    public static TreeMap<String, String> getQueryParams(URI uri, boolean z) {
        return getQueryParams(uri.getRawQuery(), z);
    }

    public static String percentEncode(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String strUrlEncode = urlEncode(str);
        int length = strUrlEncode.length();
        int i = 0;
        while (i < length) {
            char cCharAt = strUrlEncode.charAt(i);
            if (cCharAt == '*') {
                sb.append("%2A");
            } else if (cCharAt == '+') {
                sb.append("%20");
            } else if (cCharAt == '%' && i + 2 < length && strUrlEncode.charAt(i + 1) == '7' && strUrlEncode.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    public static String urlDecode(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String urlEncode(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
