package kr.co.nexon.mdev.android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXSoftKeyboardDectectorView extends View {
    private OnShownKeyboardListener mOnShownSoftKeyboard;
    private boolean mShownKeyboard;
    private OnHiddenKeyboardListener onHiddenSoftKeyboard;

    public interface OnHiddenKeyboardListener {
        void onHiddenSoftKeyboard();
    }

    public interface OnShownKeyboardListener {
        void onShowSoftKeyboard();
    }

    public NXSoftKeyboardDectectorView(Context context) {
        this(context, null);
    }

    public NXSoftKeyboardDectectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onHiddenSoftKeyboard() {
        if (this.onHiddenSoftKeyboard != null) {
            this.onHiddenSoftKeyboard.onHiddenSoftKeyboard();
        }
    }

    public void onShownSoftKeyboard() {
        if (this.mOnShownSoftKeyboard != null) {
            this.mOnShownSoftKeyboard.onShowSoftKeyboard();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        Activity activity = (Activity) getContext();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - rect.top) - i2;
        if (height > 100 && !this.mShownKeyboard) {
            this.mShownKeyboard = true;
            onShownSoftKeyboard();
        } else if (height < 100 && this.mShownKeyboard) {
            this.mShownKeyboard = false;
            onHiddenSoftKeyboard();
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setOnHiddenKeyboard(OnHiddenKeyboardListener onHiddenKeyboardListener) {
        this.onHiddenSoftKeyboard = onHiddenKeyboardListener;
    }

    public void setOnShownKeyboard(OnShownKeyboardListener onShownKeyboardListener) {
        this.mOnShownSoftKeyboard = onShownKeyboardListener;
    }
}
