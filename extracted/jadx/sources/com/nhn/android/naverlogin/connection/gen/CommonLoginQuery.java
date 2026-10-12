package com.nhn.android.naverlogin.connection.gen;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CommonLoginQuery {
    protected final String kUrlRequestLoginKey = "https://nid.naver.com/nidlogin.login?";
    protected final String kUrlRequestLogout = "https://nid.naver.com/nidlogin.logout?";

    private static String percentEncode(String str) throws UnsupportedEncodingException {
        return str == null ? "" : URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    protected String getQueryParameter(Map<String, String> map) {
        Set<String> setKeySet = map.keySet();
        StringBuilder sb = new StringBuilder("");
        for (String str : setKeySet) {
            String str2 = map.get(str);
            if (str != null && str2 != null) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(str);
                sb.append("=");
                try {
                    sb.append(percentEncode(str2));
                } catch (UnsupportedEncodingException e) {
                    sb.append(str2);
                }
            }
        }
        return sb.toString();
    }
}
