package com.naver.glink.android.sdk.ui.widget.photoview.b;

import android.util.Log;

/* JADX INFO: compiled from: LoggerDefault.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c implements b {
    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int a(String str, String str2) {
        return Log.v(str, str2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int a(String str, String str2, Throwable th) {
        return Log.v(str, str2, th);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int b(String str, String str2) {
        return Log.d(str, str2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int b(String str, String str2, Throwable th) {
        return Log.d(str, str2, th);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int c(String str, String str2) {
        return Log.i(str, str2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int c(String str, String str2, Throwable th) {
        return Log.i(str, str2, th);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int d(String str, String str2) {
        return Log.w(str, str2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int d(String str, String str2, Throwable th) {
        return Log.w(str, str2, th);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int e(String str, String str2) {
        return Log.e(str, str2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.b.b
    public int e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }
}
