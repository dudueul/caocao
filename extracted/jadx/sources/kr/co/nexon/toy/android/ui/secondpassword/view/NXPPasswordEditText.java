package kr.co.nexon.toy.android.ui.secondpassword.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPasswordEditText extends EditText {
    private OnBackPressListener backPressListener;
    private OnEnterKeyEventListener enterKeyEventListener;

    public interface OnBackPressListener {
        void onBackPress();
    }

    public interface OnEnterKeyEventListener {
        void onEnterKeyEvent();
    }

    public NXPPasswordEditText(Context context) {
        super(context);
        init();
    }

    public NXPPasswordEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NXPPasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        super.setImeOptions(-1879048186);
        super.setInputType(18);
        super.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPPasswordEditText.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 || NXPPasswordEditText.this.enterKeyEventListener == null) {
                    return false;
                }
                ToyLog.d("Enter key event : user action");
                NXPPasswordEditText.this.enterKeyEventListener.onEnterKeyEvent();
                return true;
            }
        });
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 4 || this.backPressListener == null) {
            return super.onKeyPreIme(i, keyEvent);
        }
        this.backPressListener.onBackPress();
        return true;
    }

    public void setOnBackPressListener(OnBackPressListener onBackPressListener) {
        this.backPressListener = onBackPressListener;
    }

    public void setOnEnterKeyEventListener(OnEnterKeyEventListener onEnterKeyEventListener) {
        this.enterKeyEventListener = onEnterKeyEventListener;
    }
}
