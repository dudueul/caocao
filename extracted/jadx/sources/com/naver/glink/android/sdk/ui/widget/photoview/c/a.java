package com.naver.glink.android.sdk.ui.widget.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

/* JADX INFO: compiled from: GingerScroller.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(9)
public class a extends d {
    protected final OverScroller a;
    private boolean b = false;

    public a(Context context) {
        this.a = new OverScroller(context);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public void a(boolean z) {
        this.a.forceFinished(z);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public boolean a() {
        if (this.b) {
            this.a.computeScrollOffset();
            this.b = false;
        }
        return this.a.computeScrollOffset();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public boolean b() {
        return this.a.isFinished();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public int c() {
        return this.a.getCurrX();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c.d
    public int d() {
        return this.a.getCurrY();
    }
}
