package com.naver.glink.android.sdk.a;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ViewHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class aa {
    public static void a(int i, List<View> list) {
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(i);
        }
    }

    @TargetApi(16)
    public static void a(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
