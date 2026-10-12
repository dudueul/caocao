package com.nhn.android.naverlogin.connection.gen;

import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthQueryGenerator extends CommonLoginQuery {
    private static final String OAUTH_REQUEST_ACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token?";
    private static final String OAUTH_REQUEST_AUTH_URL = "https://nid.naver.com/oauth2.0/authorize?";
    private static final String TAG = "NaverLoginOAuth|OAuthQueryGenerator";

    public String generateRequestAccessTokenUrl(String str, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        map.put("client_id", str);
        map.put("client_secret", str2);
        map.put("grant_type", "authorization_code");
        map.put("state", str3);
        map.put("code", str4);
        map.put("oauth_os", "android");
        map.put("locale", str5);
        return String.format("%s%s", OAUTH_REQUEST_ACCESS_TOKEN_URL, getQueryParameter(map));
    }

    public String generateRequestDeleteAccessTokenUrl(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("client_id", str);
        map.put("client_secret", str2);
        map.put("grant_type", "delete");
        map.put("access_token", str3);
        map.put("service_provider", "NAVER");
        map.put("oauth_os", "android");
        map.put("locale", str4);
        return String.format("%s%s", OAUTH_REQUEST_ACCESS_TOKEN_URL, getQueryParameter(map));
    }

    public String generateRequestInitUrl(String str, String str2, String str3, String str4) {
        return generateRequestInitUrl(str, str2, str3, str4, null);
    }

    public String generateRequestInitUrl(String str, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        map.put("client_id", str);
        map.put(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, "code");
        map.put("inapp_view", "true");
        map.put("oauth_os", "android");
        map.put("locale", str4);
        if (str5 != null) {
            map.put("network", str5);
        }
        map.put("redirect_uri", str3);
        map.put("state", str2);
        return String.format("%s%s", OAUTH_REQUEST_AUTH_URL, getQueryParameter(map));
    }

    public String generateRequestRefreshAccessTokenUrl(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("client_id", str);
        map.put("client_secret", str2);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", str3);
        map.put("oauth_os", "android");
        map.put("locale", str4);
        return String.format("%s%s", OAUTH_REQUEST_ACCESS_TOKEN_URL, getQueryParameter(map));
    }
}
