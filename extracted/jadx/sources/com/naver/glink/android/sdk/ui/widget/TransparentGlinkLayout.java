package com.naver.glink.android.sdk.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TransparentGlinkLayout extends RelativeLayout {
    public static final int a = 28;
    public static final int b = 4;
    public static final int c = 1;
    private int d;
    private Paint e;

    public TransparentGlinkLayout(Context context) {
        super(context);
        a();
    }

    public TransparentGlinkLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TransparentGlinkLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.d = 255;
        this.e = new Paint();
    }

    @Override // android.view.ViewGroup, android.view.View
    @SuppressLint({"WrongConstant"})
    protected void dispatchDraw(Canvas canvas) {
        if (this.d != 255) {
            if (Build.VERSION.SDK_INT >= 28) {
                canvas.saveLayerAlpha(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.d);
            } else {
                canvas.saveLayerAlpha(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.d, 4);
            }
        } else if (Build.VERSION.SDK_INT < 26) {
            if (Build.VERSION.SDK_INT >= 28) {
                canvas.saveLayer(0.0f, 0.0f, 0.0f, 0.0f, this.e);
            } else {
                canvas.saveLayer(0.0f, 0.0f, 0.0f, 0.0f, this.e, 1);
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d != 255;
    }

    public void setWindowAlpha(int i) {
        this.d = i;
        if (i == 255) {
            setEnabled(true);
            setClickable(true);
            setLongClickable(true);
        } else {
            setEnabled(false);
            setClickable(false);
            setLongClickable(false);
        }
        invalidate();
    }
}
