package com.naver.glink.android.sdk.ui.widget.photoview.a;

import android.content.Context;
import android.os.Build;

/* JADX INFO: compiled from: VersionedGestureDetector.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class f {
    public static d a(Context context, e eVar) {
        d bVar;
        int i = Build.VERSION.SDK_INT;
        if (i < 5) {
            bVar = new a(context);
        } else {
            bVar = i < 8 ? new b(context) : new c(context);
        }
        bVar.a(eVar);
        return bVar;
    }
}
