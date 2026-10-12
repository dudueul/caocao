package net.daum.mf.oauth.impl;

import android.text.TextUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class OAuthUtils {
    public static String getAuthrizationCallbackUrl(String str, String str2) {
        return TextUtils.isEmpty(str2) ? String.format(Constant.URL_AUTHORIZE_CALLBACK, str) : String.format(Constant.URL_AUTHORIZE_CALLBACK_WITH_SUFFIX, str, str2);
    }

    public static boolean isAuthrizationCallbackUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.isEmpty(str3) ? str.equals(Constant.SCHEME_OAUTH_PREFIX + str2) : str.equals(Constant.SCHEME_OAUTH_PREFIX + str2 + "-" + str3);
    }
}
