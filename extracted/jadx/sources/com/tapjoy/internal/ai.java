package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ai extends aj {
    private int a;
    private final Matrix b;
    private final float[] c;

    public ai(Context context) {
        super(context);
        this.a = 0;
        this.b = new Matrix();
        this.c = new float[2];
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.a == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        canvas.clipRect(0, 0, width, height);
        try {
            ViewGroup viewGroup = (ViewGroup) getParent();
            try {
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
                if ((viewGroup2 instanceof ScrollView) || (viewGroup2 instanceof HorizontalScrollView)) {
                    viewGroup = viewGroup2;
                }
            } catch (Exception e) {
            }
            int left = getLeft() - viewGroup.getScrollX();
            int top = getTop() - viewGroup.getScrollY();
            canvas.clipRect(0 - left, 0 - top, viewGroup.getWidth() - left, viewGroup.getHeight() - top);
        } catch (Exception e2) {
        }
        canvas.rotate(this.a * 90);
        switch (this.a) {
            case 1:
                canvas.translate(0.0f, -width);
                break;
            case 2:
                canvas.translate(-width, -height);
                break;
            case 3:
                canvas.translate(-height, 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        this.b.setRotate(this.a * (-90));
        switch (this.a) {
            case 1:
                this.b.postTranslate(0.0f, width - 1);
                break;
            case 2:
                this.b.postTranslate(width - 1, height - 1);
                break;
            case 3:
                this.b.postTranslate(height - 1, 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a == 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float[] fArr = this.c;
        fArr[0] = motionEvent.getX();
        fArr[1] = motionEvent.getY();
        this.b.mapPoints(fArr);
        motionEvent.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getRotationCount() {
        return this.a;
    }

    @Override // com.tapjoy.internal.aj, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.a % 2 == 0) {
            super.onMeasure(i, i2);
        } else {
            super.onMeasure(i2, i);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void setRotationCount(int i) {
        this.a = i & 3;
    }
}
