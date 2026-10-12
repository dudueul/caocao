package com.naver.glink.android.sdk.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PreImeKeyListeningEditText extends EditText {
    private View.OnKeyListener a;

    public PreImeKeyListeningEditText(Context context) {
        super(context, null);
    }

    public PreImeKeyListeningEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PreImeKeyListeningEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (this.a != null) {
            this.a.onKey(this, i, keyEvent);
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public void setOnKeyPreImeListener(View.OnKeyListener onKeyListener) {
        this.a = onKeyListener;
    }
}
