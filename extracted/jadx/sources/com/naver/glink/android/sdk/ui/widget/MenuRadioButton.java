package com.naver.glink.android.sdk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MenuRadioButton extends RadioButton {
    private Drawable a;
    private boolean b;
    private int c;

    public MenuRadioButton(Context context) {
        super(context);
        this.a = getContext().getResources().getDrawable(R.drawable.bg_articles_menu_selector_new);
        this.b = false;
        a();
    }

    public MenuRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = getContext().getResources().getDrawable(R.drawable.bg_articles_menu_selector_new);
        this.b = false;
        a();
    }

    public MenuRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = getContext().getResources().getDrawable(R.drawable.bg_articles_menu_selector_new);
        this.b = false;
        a();
    }

    @TargetApi(21)
    public MenuRadioButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = getContext().getResources().getDrawable(R.drawable.bg_articles_menu_selector_new);
        this.b = false;
        a();
    }

    private void a() {
        if (Build.VERSION.SDK_INT <= 16) {
            this.c = getResources().getDimensionPixelSize(R.dimen.menu_selector_padding);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b) {
            int width = getWidth() - this.a.getIntrinsicWidth();
            int top = getTop() + x.a(10.0f);
            this.a.setBounds(width, top, getWidth(), this.a.getIntrinsicHeight() + top);
            this.a.draw(canvas);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (Build.VERSION.SDK_INT <= 16) {
            setPadding(this.c, 0, this.c, 0);
        }
        super.onMeasure(i, i2);
    }

    public void setNewVisible(boolean z) {
        this.b = z;
        invalidate();
    }
}
