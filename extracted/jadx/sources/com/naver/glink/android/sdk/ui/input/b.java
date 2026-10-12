package com.naver.glink.android.sdk.ui.input;

import android.content.Context;
import android.text.InputFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: TextInputContract.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface b {

    /* JADX INFO: compiled from: TextInputContract.java */
    public interface a extends com.naver.glink.android.sdk.a.d.a {
        void a(Context context, String str);

        boolean b();

        InputFilter[] c();
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.input.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextInputContract.java */
    public interface InterfaceC0044b extends com.naver.glink.android.sdk.a.d.b<a> {
        void c_();

        void setInputText(String str);
    }
}
