package com.naver.glink.android.sdk.ui.widget.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HackyViewPager extends ViewPager {
    private boolean a;

    public HackyViewPager(Context context) {
        super(context);
        this.a = false;
    }

    public HackyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
    }

    public void a() {
        this.a = !this.a;
    }

    public boolean b() {
        return this.a;
    }

    @Override // android.support.v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.support.v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.a && super.onTouchEvent(motionEvent);
    }

    public void setLocked(boolean z) {
        this.a = z;
    }
}
