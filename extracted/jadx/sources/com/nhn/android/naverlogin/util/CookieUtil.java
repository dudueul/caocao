package com.nhn.android.naverlogin.util;

import android.util.Log;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CookieUtil {
    public static final String COOKIE_DOMAIN_NID = "https://nid.naver.com";
    private static final String TAG = "NaverLoginOAuth|CookieUtil";

    public static String getCookie(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        sb.setLength(0);
        if (headers != null) {
            for (int i = 0; i < headers.length; i++) {
                sb.append(headers[i].getValue());
                if (!sb.toString().trim().endsWith(";")) {
                    sb.append(";");
                }
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.d(TAG, "cookie:" + headers[i].getValue());
                }
            }
        }
        return sb.toString();
    }
}
