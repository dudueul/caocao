package kr.co.nexon.toy.android.ui.common;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPEditText extends EditText implements TextWatcher {
    private NXPTextWatcher textWatcher;

    public interface NXPTextWatcher {
        void afterTextChanged(View view, Editable editable);

        void beforeTextChanged(View view, CharSequence charSequence, int i, int i2, int i3);

        void onTextChanged(View view, CharSequence charSequence, int i, int i2, int i3);
    }

    public NXPEditText(Context context) {
        super(context);
    }

    public NXPEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public NXPEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.textWatcher != null) {
            this.textWatcher.afterTextChanged(this, editable);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.textWatcher != null) {
            this.textWatcher.beforeTextChanged(this, charSequence, i, i2, i3);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        addTextChangedListener(this);
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.textWatcher != null) {
            this.textWatcher.onTextChanged(this, charSequence, i, i2, i3);
        }
    }

    public void setTextWatcher(NXPTextWatcher nXPTextWatcher) {
        this.textWatcher = nXPTextWatcher;
    }
}
