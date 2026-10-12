package com.naver.glink.android.sdk.a;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: compiled from: UriHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class y {
    public static Uri a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return Uri.parse(str);
    }
}
