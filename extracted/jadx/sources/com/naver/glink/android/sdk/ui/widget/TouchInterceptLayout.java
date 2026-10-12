package com.naver.glink.android.sdk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TouchInterceptLayout extends RelativeLayout {
    private ArrayList<View> a;
    private boolean b;

    public TouchInterceptLayout(Context context) {
        super(context);
        a();
    }

    public TouchInterceptLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TouchInterceptLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public TouchInterceptLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        this.a = new ArrayList<>();
    }

    private boolean a(View view, int i, int i2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return i >= iArr[0] && i <= iArr[0] + view.getWidth() && i2 >= iArr[1] && i2 <= iArr[1] + view.getHeight();
    }

    public void a(View view) {
        if (this.a.contains(view)) {
            return;
        }
        this.a.add(view);
    }

    public void b(View view) {
        this.a.remove(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        for (int i = 0; i < this.a.size(); i++) {
            this.a.remove(i);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.b) {
            return false;
        }
        for (int i = 0; i < this.a.size(); i++) {
            if (a(this.a.get(i), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return true;
    }

    public void setInterceptable(boolean z) {
        this.b = z;
    }
}
