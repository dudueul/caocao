package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import com.naver.glink.android.sdk.R;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class v {
    public static String a(Context context, int i) {
        return a(context, i, 9999);
    }

    public static String a(Context context, int i, int i2) {
        String string = context.getString(R.string.count_format, Integer.valueOf(Math.min(i, i2)));
        return i > i2 ? string + "+" : string;
    }

    public static final boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.replaceAll("\\s+", ""));
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? str : Html.fromHtml(str).toString();
    }
}
