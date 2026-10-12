package com.naver.glink.android.sdk.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ScrollListenerLayout extends FrameLayout {
    private ViewGroup a;
    private float b;
    private float c;
    private float d;
    private Animation e;
    private Animation f;
    private boolean g;
    private boolean h;
    private boolean i;
    private DataSetObserver j;
    private boolean k;

    public ScrollListenerLayout(Context context) {
        super(context);
        d();
    }

    public ScrollListenerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public ScrollListenerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    @TargetApi(21)
    public ScrollListenerLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        d();
    }

    private AbsListView a(ViewGroup viewGroup) {
        AbsListView absListViewA;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= viewGroup.getChildCount()) {
                return null;
            }
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                if ((childAt instanceof ViewGroup) && (absListViewA = a((ViewGroup) childAt)) != null) {
                    return absListViewA;
                }
                if (childAt instanceof AbsListView) {
                    return (AbsListView) childAt;
                }
            }
            i = i2 + 1;
        }
    }

    private void a(MotionEvent motionEvent, float f, float f2, float f3) {
        if (this.g || !this.h) {
            return;
        }
        if (this.d < ((double) f2) - (((double) this.a.getHeight()) / 10.0d) && this.a.getVisibility() != 0) {
            this.a.startAnimation(this.f);
            return;
        }
        if (f3 >= (-this.a.getHeight()) || this.a.getVisibility() != 0) {
            return;
        }
        if ((this.i && e()) || !this.i) {
            this.a.startAnimation(this.e);
        }
        this.b = motionEvent.getRawY();
    }

    private void d() {
        this.f = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_from_top);
        this.f.setDuration(50L);
        this.f.setAnimationListener(new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.widget.ScrollListenerLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ScrollListenerLayout.this.a.setVisibility(0);
                ScrollListenerLayout.this.g = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                ScrollListenerLayout.this.g = true;
            }
        });
        this.e = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_to_top);
        this.e.setDuration(50L);
        this.e.setAnimationListener(new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.widget.ScrollListenerLayout.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ScrollListenerLayout.this.a.setVisibility(4);
                ScrollListenerLayout.this.g = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                ScrollListenerLayout.this.g = true;
            }
        });
        this.i = false;
    }

    private boolean e() {
        AbsListView absListViewA = a((ViewGroup) this);
        return absListViewA != null && absListViewA.getFirstVisiblePosition() > 0;
    }

    public void a() {
        this.h = false;
        if (this.a.getVisibility() != 0) {
            this.a.startAnimation(this.f);
        }
    }

    public void a(View view) {
        this.a.addView(view);
    }

    public void b(View view) {
        this.a.removeView(view);
    }

    public boolean b() {
        return this.a.getVisibility() == 0;
    }

    public void c() {
        this.a.setVisibility(8);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.a.isEnabled()) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.b = motionEvent.getRawY();
                    this.c = motionEvent.getRawX();
                    this.d = motionEvent.getRawY();
                    break;
                case 2:
                    float rawY = motionEvent.getRawY();
                    float rawX = motionEvent.getRawX();
                    float f = rawY - this.b;
                    if (Math.abs(f) >= Math.abs(rawX - this.c)) {
                        a(motionEvent, rawX, rawY, f);
                        this.d = rawY;
                    }
                    break;
            }
        }
        return zDispatchTouchEvent;
    }

    public boolean getEnable() {
        return this.h;
    }

    public ViewGroup getTabView() {
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (ViewGroup) findViewById(R.id.menu_tab_layout);
    }

    public void setEnable(boolean z) {
        this.h = z;
    }

    public void setNestedScrollEnable(boolean z) {
        this.i = z;
    }
}
