package com.google.android.gms.auth;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class CookieUtil {
    private CookieUtil() {
    }

    public static String getCookieUrl(String str, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        String str2 = zzd(bool) ? Constants.SCHEME : "http";
        return new StringBuilder(String.valueOf(str2).length() + 3 + String.valueOf(str).length()).append(str2).append("://").append(str).toString();
    }

    public static String getCookieValue(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Long l) {
        StringBuilder sbAppend = new StringBuilder(str).append('=');
        if (!TextUtils.isEmpty(str2)) {
            sbAppend.append(str2);
        }
        if (zzd(bool)) {
            sbAppend.append(";HttpOnly");
        }
        if (zzd(bool2)) {
            sbAppend.append(";Secure");
        }
        if (!TextUtils.isEmpty(str3)) {
            sbAppend.append(";Domain=").append(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            sbAppend.append(";Path=").append(str4);
        }
        if (l != null && l.longValue() > 0) {
            sbAppend.append(";Max-Age=").append(l);
        }
        return sbAppend.toString();
    }

    private static boolean zzd(Boolean bool) {
        return bool != null && bool.booleanValue();
    }
}
