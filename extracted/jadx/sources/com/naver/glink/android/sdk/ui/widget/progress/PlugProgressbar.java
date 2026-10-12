package com.naver.glink.android.sdk.ui.widget.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PlugProgressbar extends View {
    private Drawable a;
    private boolean b;

    public PlugProgressbar(Context context) {
        super(context);
        a(context);
    }

    public PlugProgressbar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PlugProgressbar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = context.getResources().getDrawable(R.drawable.loading_progress);
        setBackground(this.a);
        this.b = true;
    }

    private void c() {
        int[] drawableState = getDrawableState();
        if (this.a != null && this.a.isStateful()) {
            this.a.setState(drawableState);
        }
        if (this.a == null || !this.a.isStateful()) {
            return;
        }
        this.a.setState(drawableState);
    }

    void a() {
        if (getVisibility() != 0) {
            return;
        }
        if (this.a instanceof Animatable) {
            this.b = true;
        }
        postInvalidate();
    }

    void b() {
        if (this.a instanceof Animatable) {
            ((Animatable) this.a).stop();
            this.b = false;
        }
        postInvalidate();
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b && (this.a instanceof Animatable)) {
            ((Animatable) this.a).start();
            this.b = false;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.a.setBounds(0, 0, i, i2);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (i == 8 || i == 4) {
                b();
            } else {
                a();
            }
        }
    }
}
