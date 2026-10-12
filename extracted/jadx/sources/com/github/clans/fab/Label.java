package com.github.clans.fab;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Label extends TextView {
    private static final Xfermode b = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    GestureDetector a;
    private int c;
    private int d;
    private int e;
    private int f;
    private Drawable g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private FloatingActionButton o;
    private Animation p;
    private Animation q;
    private boolean r;
    private boolean s;

    private class a extends Drawable {
        private Paint b;
        private Paint c;

        private a() {
            this.b = new Paint(1);
            this.c = new Paint(1);
            a();
        }

        private void a() {
            Label.this.setLayerType(1, null);
            this.b.setStyle(Paint.Style.FILL);
            this.b.setColor(Label.this.k);
            this.c.setXfermode(Label.b);
            if (Label.this.isInEditMode()) {
                return;
            }
            this.b.setShadowLayer(Label.this.c, Label.this.d, Label.this.e, Label.this.f);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            RectF rectF = new RectF(Label.this.c + Math.abs(Label.this.d), Label.this.c + Math.abs(Label.this.e), Label.this.i, Label.this.j);
            canvas.drawRoundRect(rectF, Label.this.n, Label.this.n, this.b);
            canvas.drawRoundRect(rectF, Label.this.n, Label.this.n, this.c);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    public Label(Context context) {
        super(context);
        this.h = true;
        this.a = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.Label.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                Label.this.d();
                if (Label.this.o != null) {
                    Label.this.o.f();
                }
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Label.this.e();
                if (Label.this.o != null) {
                    Label.this.o.g();
                }
                return super.onSingleTapUp(motionEvent);
            }
        });
        this.s = true;
    }

    public Label(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = true;
        this.a = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.Label.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                Label.this.d();
                if (Label.this.o != null) {
                    Label.this.o.f();
                }
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Label.this.e();
                if (Label.this.o != null) {
                    Label.this.o.g();
                }
                return super.onSingleTapUp(motionEvent);
            }
        });
        this.s = true;
    }

    public Label(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = true;
        this.a = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.Label.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                Label.this.d();
                if (Label.this.o != null) {
                    Label.this.o.f();
                }
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Label.this.e();
                if (Label.this.o != null) {
                    Label.this.o.g();
                }
                return super.onSingleTapUp(motionEvent);
            }
        });
        this.s = true;
    }

    private Drawable a(int i) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{this.n, this.n, this.n, this.n, this.n, this.n, this.n, this.n}, null, null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    private int h() {
        if (this.i == 0) {
            this.i = getMeasuredWidth();
        }
        return getMeasuredWidth() + a();
    }

    private int i() {
        if (this.j == 0) {
            this.j = getMeasuredHeight();
        }
        return getMeasuredHeight() + b();
    }

    @TargetApi(21)
    private Drawable j() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, a(this.l));
        stateListDrawable.addState(new int[0], a(this.k));
        if (!com.github.clans.fab.a.b()) {
            this.g = stateListDrawable;
            return stateListDrawable;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{this.m}), stateListDrawable, null);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.github.clans.fab.Label.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        setClipToOutline(true);
        this.g = rippleDrawable;
        return rippleDrawable;
    }

    private void k() {
        if (this.p != null) {
            this.q.cancel();
            startAnimation(this.p);
        }
    }

    private void l() {
        if (this.q != null) {
            this.p.cancel();
            startAnimation(this.q);
        }
    }

    @TargetApi(21)
    private void setBackgroundCompat(Drawable drawable) {
        if (com.github.clans.fab.a.a()) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    private void setShadow(FloatingActionButton floatingActionButton) {
        this.f = floatingActionButton.getShadowColor();
        this.c = floatingActionButton.getShadowRadius();
        this.d = floatingActionButton.getShadowXOffset();
        this.e = floatingActionButton.getShadowYOffset();
        this.h = floatingActionButton.h();
    }

    int a() {
        if (this.h) {
            return this.c + Math.abs(this.d);
        }
        return 0;
    }

    void a(int i, int i2, int i3) {
        this.k = i;
        this.l = i2;
        this.m = i3;
    }

    void a(boolean z) {
        if (z) {
            k();
        }
        setVisibility(0);
    }

    int b() {
        if (this.h) {
            return this.c + Math.abs(this.e);
        }
        return 0;
    }

    void b(boolean z) {
        if (z) {
            l();
        }
        setVisibility(4);
    }

    void c() {
        if (this.h) {
            new LayerDrawable(new Drawable[]{new a(), j()}).setLayerInset(1, this.c + Math.abs(this.d), this.c + Math.abs(this.e), this.c + Math.abs(this.d), this.c + Math.abs(this.e));
        } else {
            new LayerDrawable(new Drawable[]{j()});
        }
    }

    @TargetApi(21)
    void d() {
        if (this.r) {
            this.g = getBackground();
        }
        if (this.g instanceof StateListDrawable) {
            ((StateListDrawable) this.g).setState(new int[]{R.attr.state_pressed});
        } else if (com.github.clans.fab.a.b() && (this.g instanceof RippleDrawable)) {
            RippleDrawable rippleDrawable = (RippleDrawable) this.g;
            rippleDrawable.setState(new int[]{R.attr.state_enabled, R.attr.state_pressed});
            rippleDrawable.setHotspot(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
            rippleDrawable.setVisible(true, true);
        }
        setPressed(true);
    }

    @TargetApi(21)
    void e() {
        if (this.r) {
            this.g = getBackground();
        }
        if (this.g instanceof StateListDrawable) {
            ((StateListDrawable) this.g).setState(new int[0]);
        } else if (com.github.clans.fab.a.b() && (this.g instanceof RippleDrawable)) {
            RippleDrawable rippleDrawable = (RippleDrawable) this.g;
            rippleDrawable.setState(new int[0]);
            rippleDrawable.setHotspot(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
            rippleDrawable.setVisible(true, true);
        }
        setPressed(false);
    }

    boolean f() {
        return this.s;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(h(), i());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.o == null || this.o.getOnClickListener() == null || !this.o.isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 1:
                e();
                this.o.g();
                break;
            case 3:
                e();
                this.o.g();
                break;
        }
        this.a.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    void setCornerRadius(int i) {
        this.n = i;
    }

    void setFab(FloatingActionButton floatingActionButton) {
        this.o = floatingActionButton;
        setShadow(floatingActionButton);
    }

    void setHandleVisibilityChanges(boolean z) {
        this.s = z;
    }

    void setHideAnimation(Animation animation) {
        this.q = animation;
    }

    void setShowAnimation(Animation animation) {
        this.p = animation;
    }

    void setShowShadow(boolean z) {
        this.h = z;
    }

    void setUsingStyle(boolean z) {
        this.r = z;
    }
}
