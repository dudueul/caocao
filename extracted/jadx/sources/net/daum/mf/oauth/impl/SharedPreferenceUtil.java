package net.daum.mf.oauth.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class SharedPreferenceUtil {
    private static final boolean HAS_HONEYCOMB;

    static {
        HAS_HONEYCOMB = Build.VERSION.SDK_INT >= 11;
    }

    private SharedPreferenceUtil() {
    }

    public static SharedPreferences getSharedPref(Context context, String str) {
        return HAS_HONEYCOMB ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
    }
}
