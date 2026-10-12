package com.nhn.android.naverlogin.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.data.OAuthIntent;
import com.nhn.android.naverlogin.data.OAuthLoginData;
import com.nhn.android.naverlogin.data.OAuthLoginPreferenceManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthWebviewUrlUtil {
    public static final String FINAL_URL = "http://nid.naver.com/com.nhn.login_global/inweb/finish";
    public static final String FINAL_URL_HTTPS = "https://nid.naver.com/com.nhn.login_global/inweb/finish";
    private static final String TAG = "NaverLoginOAuth|OAuthWebvewUrlUtil";

    private static String getDecodedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strDecode = "";
        try {
            strDecode = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
        }
        return (TextUtils.isEmpty(strDecode) || strDecode.equalsIgnoreCase(str)) ? str : strDecode;
    }

    private static Map<String, String> getQueryMap(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("&");
        HashMap map = new HashMap();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split("=");
            if (strArrSplit2.length == 2) {
                map.put(strArrSplit2[0], strArrSplit2[1]);
            } else if (strArrSplit2.length == 1) {
                map.put(strArrSplit2[0], "");
            }
        }
        return map;
    }

    private static Map<String, String> getQueryMapFromUrl(String str) {
        if (str.contains("?")) {
            str = str.split("\\?")[1];
        }
        return getQueryMap(str);
    }

    public static boolean isErrorResultNaverTokenInvalid(Context context, String str, String str2, OAuthLoginData oAuthLoginData) {
        Map<String, String> queryMapFromUrl;
        try {
            if (TextUtils.isEmpty(str) || str.startsWith("https://nid.naver.com/")) {
                try {
                    queryMapFromUrl = getQueryMap(new URL(str2).getQuery());
                } catch (Exception e) {
                    queryMapFromUrl = getQueryMapFromUrl(str2);
                }
                if (queryMapFromUrl != null && queryMapFromUrl.containsKey("error") && queryMapFromUrl.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION) && queryMapFromUrl.get("error").equalsIgnoreCase("invalid_request") && queryMapFromUrl.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION).contains("token") && queryMapFromUrl.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION).contains("invalid")) {
                    return true;
                }
            } else if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "isErrorResultNaverTokenInvalid - pre url is not naver.com");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static boolean isFinalUrl(boolean z, String str, String str2) {
        if (str2 == null) {
            return false;
        }
        if (str2.equalsIgnoreCase(FINAL_URL) || str2.equalsIgnoreCase(FINAL_URL_HTTPS) || str2.equalsIgnoreCase("http://m.naver.com/") || str2.equalsIgnoreCase("http://m.naver.com")) {
            return true;
        }
        if (z && str2.startsWith("https://nid.naver.com/nidlogin.login?svctype=262144")) {
            return true;
        }
        if (z) {
            return false;
        }
        return (str.startsWith("https://nid.naver.com/mobile/user/help/sleepId.nhn?m=viewSleepId&token_help=") && str2.startsWith("https://nid.naver.com/nidlogin.login?svctype=262144")) || (str.startsWith("https://nid.naver.com/mobile/user/global/idSafetyRelease.nhn?") && str2.startsWith("https://nid.naver.com/nidlogin.login?svctype=262144")) || (str.startsWith("https://nid.naver.com/mobile/user/help/idSafetyRelease.nhn?") && str2.startsWith("https://nid.naver.com/nidlogin.login?svctype=262144"));
    }

    public static void processCallbackUrl(Context context, String str, OAuthLoginData oAuthLoginData) throws MalformedURLException {
        Map<String, String> queryMapFromUrl;
        try {
            queryMapFromUrl = getQueryMap(new URL(str).getQuery());
        } catch (Exception e) {
            queryMapFromUrl = getQueryMapFromUrl(str);
        }
        oAuthLoginData.setMiddleResult(queryMapFromUrl.get("code"), queryMapFromUrl.get("state"), queryMapFromUrl.get("error"), getDecodedString(queryMapFromUrl.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION)));
    }

    public static boolean returnWhenAuthorizationDone(Context context, String str, String str2, OAuthLoginData oAuthLoginData) {
        boolean z;
        Map<String, String> queryMapFromUrl;
        if (oAuthLoginData == null) {
            oAuthLoginData = new OAuthLoginData("", "", "");
        }
        try {
            if (TextUtils.isEmpty(str) || str.startsWith("https://nid.naver.com/") || str.startsWith("https://nid.naver.com/")) {
                if (str2.startsWith("https://nid.naver.com/login/noauth/logout.nhn") || str2.startsWith("http://nid.naver.com/nidlogin.logout")) {
                    Intent intent = new Intent();
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE, OAuthErrorCode.CLIENT_USER_CANCEL.getCode());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION, OAuthErrorCode.CLIENT_USER_CANCEL.getDesc());
                    ((Activity) context).setResult(-1, intent);
                    ((Activity) context).finish();
                    return true;
                }
                try {
                    queryMapFromUrl = getQueryMap(new URL(str2).getQuery());
                } catch (Exception e) {
                    queryMapFromUrl = getQueryMapFromUrl(str2);
                }
                if (queryMapFromUrl != null && queryMapFromUrl.containsKey("code") && queryMapFromUrl.containsKey("state")) {
                    if (OAuthLoginDefine.DEVELOPER_VERSION) {
                        Log.d(TAG, "query map contain code and state");
                    }
                    z = true;
                } else if (queryMapFromUrl != null && queryMapFromUrl.containsKey("error") && queryMapFromUrl.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION)) {
                    if (OAuthLoginDefine.DEVELOPER_VERSION) {
                        Log.d(TAG, "query map contain error, url : " + str2);
                    }
                    OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
                    oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.fromString(queryMapFromUrl.get("error")));
                    oAuthLoginPreferenceManager.setLastErrorDesc(getDecodedString(queryMapFromUrl.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION)));
                    z = true;
                } else if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.d(TAG, "query map does not contain code and state, url:" + str2);
                    z = false;
                }
                z = false;
            } else if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "returnWhenAuthorizationDone - pre url is not naver.com");
                z = false;
            } else {
                z = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z) {
            try {
                processCallbackUrl(context, str2, oAuthLoginData);
                Intent intent2 = new Intent();
                intent2.putExtra(OAuthIntent.EXTRA_OAUTH_CODE, oAuthLoginData.getCode());
                intent2.putExtra(OAuthIntent.EXTRA_OAUTH_STATE, oAuthLoginData.getInitState());
                intent2.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE, oAuthLoginData.getErrorCode().getCode());
                intent2.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION, oAuthLoginData.getErrorDesc());
                ((Activity) context).setResult(-1, intent2);
            } catch (MalformedURLException e3) {
                e3.printStackTrace();
                Intent intent3 = new Intent();
                intent3.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE, OAuthErrorCode.CLIENT_ERROR_PARSING_FAIL.getCode());
                intent3.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION, OAuthErrorCode.CLIENT_ERROR_PARSING_FAIL.getDesc());
                ((Activity) context).setResult(0, intent3);
            }
            ((Activity) context).finish();
        }
        return z;
    }
}
