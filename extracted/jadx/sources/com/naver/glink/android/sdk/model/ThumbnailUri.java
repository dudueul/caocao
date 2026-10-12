package com.naver.glink.android.sdk.model;

import android.net.Uri;
import android.text.TextUtils;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.d;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ThumbnailUri {
    private static Uri get(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return y.a(d.b().c.f + str2 + (TextUtils.isEmpty(str) ? "" : "?type=" + str));
    }

    public static Uri getArticle(String str) {
        return get("w968", str);
    }

    public static Uri getViewer(String str) {
        return get("wa1280", str);
    }
}
