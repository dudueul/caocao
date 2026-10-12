package com.naver.glink.android.sdk.a;

import android.view.View;

/* JADX INFO: compiled from: OnToggleListener.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class m implements View.OnClickListener {
    private boolean a;

    public m() {
    }

    public m(boolean z) {
        b(z);
    }

    public abstract void a(boolean z);

    public void b(boolean z) {
        this.a = z;
        a(z);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        b(!this.a);
    }
}
