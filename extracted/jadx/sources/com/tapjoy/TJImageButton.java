package com.tapjoy;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJImageButton extends ImageButton {
    private Bitmap a;
    private Bitmap b;

    public TJImageButton(Context context) {
        super(context);
    }

    public TJImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TJImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TJImageButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setDisableImageBitmap(Bitmap bitmap) {
        this.b = bitmap;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setImageBitmap(z ? this.a : this.b);
    }

    public void setEnabledImageBitmap(Bitmap bitmap) {
        this.a = bitmap;
    }
}
