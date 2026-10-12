package com.naver.glink.android.sdk.a;

import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: compiled from: ForwardTextWatcher.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class g implements TextWatcher {
    private final TextWatcher a;

    public g() {
        this.a = null;
    }

    public g(TextWatcher textWatcher) {
        this.a = textWatcher;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.a != null) {
            this.a.afterTextChanged(editable);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a != null) {
            this.a.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a != null) {
            this.a.onTextChanged(charSequence, i, i2, i3);
        }
    }
}
