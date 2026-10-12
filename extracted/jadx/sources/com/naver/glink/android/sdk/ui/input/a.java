package com.naver.glink.android.sdk.ui.input;

import android.content.Context;
import android.text.InputFilter;

/* JADX INFO: compiled from: ChatInputPresenter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a implements b.a {
    private static final int a = 65;
    private final b.InterfaceC0044b b;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.input.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChatInputPresenter.java */
    public static class C0043a {
        public final String a;

        private C0043a(String str) {
            this.a = str;
        }
    }

    public a(b.InterfaceC0044b interfaceC0044b) {
        this.b = interfaceC0044b;
        interfaceC0044b.setPresenter(this);
    }

    @Override // com.naver.glink.android.sdk.a.d.a
    public void a() {
        this.b.setInputText("");
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public void a(Context context, String str) {
        com.naver.glink.android.sdk.a.a.b.c(new C0043a(str));
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public boolean b() {
        return true;
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public InputFilter[] c() {
        return new InputFilter[]{new InputFilter.LengthFilter(65)};
    }
}
