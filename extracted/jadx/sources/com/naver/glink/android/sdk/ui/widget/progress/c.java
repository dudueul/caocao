package com.naver.glink.android.sdk.ui.widget.progress;

import android.content.Context;

/* JADX INFO: compiled from: ProgressWrapper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private a a;

    public void a() {
        if (this.a != null) {
            this.a.dismiss();
            this.a = null;
        }
    }

    public void a(Context context) {
        if (this.a == null) {
            this.a = new a(context);
            this.a.show();
        }
    }
}
