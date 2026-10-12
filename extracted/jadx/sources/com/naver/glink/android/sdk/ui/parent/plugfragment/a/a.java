package com.naver.glink.android.sdk.ui.parent.plugfragment.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: FragmentLifecycle.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a implements b {
    private WeakReference<Activity> a;

    public a(Activity activity) {
        this.a = new WeakReference<>(activity);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void a() {
        if (com.naver.glink.android.sdk.d.r() == null) {
            com.naver.glink.android.sdk.d.e(this.a.get());
        } else {
            com.naver.glink.android.sdk.alarm.b.a(this.a.get());
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void a(int i) {
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void a(int i, int i2, Intent intent) {
        com.naver.glink.android.sdk.a.a.b.c(new com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.C0048a(i, i2, intent, null));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void a(int i, String[] strArr, int[] iArr) {
        com.naver.glink.android.sdk.a.a.b.c(new com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.b(i, strArr, iArr));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void b() {
        if (com.naver.glink.android.sdk.d.r() == null) {
            return;
        }
        com.naver.glink.android.sdk.alarm.b.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void c() {
        if (com.naver.glink.android.sdk.d.r() == null) {
            return;
        }
        com.naver.glink.android.sdk.alarm.b.a(false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void d() {
        if (this.a == null || this.a.get() == null || com.naver.glink.android.sdk.d.r() == null) {
            return;
        }
        com.naver.glink.android.sdk.alarm.b.a(true);
        com.naver.glink.android.sdk.alarm.b.b();
        if (com.naver.glink.android.sdk.d.f()) {
            com.naver.glink.android.sdk.d.a((Context) this.a.get(), false);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.b
    public void e() {
    }
}
