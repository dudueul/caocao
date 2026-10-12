package com.naver.glink.android.sdk.ui.widget.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;

/* JADX INFO: compiled from: IcsScroller.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(14)
public class b extends a {
    public b(Context context) {
        super(context);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.a, com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public boolean a() {
        return this.a.computeScrollOffset();
    }
}
