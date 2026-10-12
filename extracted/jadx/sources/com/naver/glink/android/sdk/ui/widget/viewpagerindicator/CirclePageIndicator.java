package com.naver.glink.android.sdk.ui.widget.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CirclePageIndicator extends View implements a {
    private static final int a = -1;
    private final Paint b;
    private final Paint c;
    private final Paint d;
    private float e;
    private ViewPager f;
    private ViewPager.OnPageChangeListener g;
    private int h;
    private int i;
    private float j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private float p;
    private int q;
    private boolean r;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.naver.glink.android.sdk.ui.widget.viewpagerindicator.CirclePageIndicator.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.d = new Paint(1);
        this.p = -1.0f;
        this.q = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_circle_indicator_page_color);
        int color2 = resources.getColor(R.color.default_circle_indicator_fill_color);
        int integer = resources.getInteger(R.integer.default_circle_indicator_orientation);
        int color3 = resources.getColor(R.color.default_circle_indicator_stroke_color);
        float dimension = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
        float dimension2 = resources.getDimension(R.dimen.default_circle_indicator_radius);
        boolean z = resources.getBoolean(R.bool.default_circle_indicator_centered);
        boolean z2 = resources.getBoolean(R.bool.default_circle_indicator_snap);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CirclePageIndicator, i, 0);
        this.m = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_centered, z);
        this.l = typedArrayObtainStyledAttributes.getInt(R.styleable.CirclePageIndicator_android_orientation, integer);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_pageColor, color));
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_strokeColor, color3));
        this.c.setStrokeWidth(typedArrayObtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_strokeWidth, dimension));
        this.d.setStyle(Paint.Style.FILL);
        this.d.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_fillColor, color2));
        this.e = typedArrayObtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_radius, dimension2);
        this.n = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_snap, z2);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.CirclePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.o = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private int a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.f == null) {
            return size;
        }
        int count = this.f.getAdapter().getCount();
        int paddingLeft = (int) (((count - 1) * this.e) + getPaddingLeft() + getPaddingRight() + (count * 2 * this.e) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((2.0f * this.e) + getPaddingTop() + getPaddingBottom() + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public boolean a() {
        return this.m;
    }

    public boolean b() {
        return this.n;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.viewpagerindicator.a
    public void c() {
        invalidate();
    }

    public int getFillColor() {
        return this.d.getColor();
    }

    public int getOrientation() {
        return this.l;
    }

    public int getPageColor() {
        return this.b.getColor();
    }

    public float getRadius() {
        return this.e;
    }

    public int getStrokeColor() {
        return this.c.getColor();
    }

    public float getStrokeWidth() {
        return this.c.getStrokeWidth();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        int height;
        int paddingTop;
        int paddingBottom;
        int paddingLeft;
        float f;
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.f == null || (count = this.f.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.h >= count) {
            setCurrentItem(count - 1);
            return;
        }
        if (this.l == 0) {
            height = getWidth();
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
            paddingLeft = getPaddingTop();
        } else {
            height = getHeight();
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
            paddingLeft = getPaddingLeft();
        }
        float f5 = this.e * 3.0f;
        float f6 = this.e + paddingLeft;
        float f7 = paddingTop + this.e;
        if (this.m) {
            f7 += (((height - paddingTop) - paddingBottom) / 2.0f) - ((count * f5) / 2.0f);
        }
        float strokeWidth = this.e;
        if (this.c.getStrokeWidth() > 0.0f) {
            strokeWidth -= this.c.getStrokeWidth() / 2.0f;
        }
        for (int i = 0; i < count; i++) {
            float f8 = f7 + (i * f5);
            if (this.l == 0) {
                f3 = f6;
                f4 = f8;
            } else {
                f3 = f8;
                f4 = f6;
            }
            if (this.b.getAlpha() > 0) {
                canvas.drawCircle(f4, f3, strokeWidth, this.b);
            }
            if (strokeWidth != this.e) {
                canvas.drawCircle(f4, f3, this.e, this.c);
            }
        }
        float f9 = (this.n ? this.i : this.h) * f5;
        if (!this.n) {
            f9 += this.j * f5;
        }
        if (this.l == 0) {
            f2 = f9 + f7;
            f = f6;
        } else {
            f = f7 + f9;
            f2 = f6;
        }
        canvas.drawCircle(f2, f, this.e, this.d);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.l == 0) {
            setMeasuredDimension(a(i), b(i2));
        } else {
            setMeasuredDimension(b(i), a(i2));
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.k = i;
        if (this.g != null) {
            this.g.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.h = i;
        this.j = f;
        invalidate();
        if (this.g != null) {
            this.g.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.n || this.k == 0) {
            this.h = i;
            this.i = i;
            invalidate();
        }
        if (this.g != null) {
            this.g.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.h = savedState.a;
        this.i = savedState.a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.h;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.f == null || this.f.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                this.q = MotionEventCompat.getPointerId(motionEvent, 0);
                this.p = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.r) {
                    int count = this.f.getAdapter().getCount();
                    int width = getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    if (this.h > 0 && motionEvent.getX() < f - f2) {
                        if (action == 3) {
                            return true;
                        }
                        this.f.setCurrentItem(this.h - 1);
                        return true;
                    }
                    if (this.h < count - 1 && motionEvent.getX() > f2 + f) {
                        if (action == 3) {
                            return true;
                        }
                        this.f.setCurrentItem(this.h + 1);
                        return true;
                    }
                }
                this.r = false;
                this.q = -1;
                if (!this.f.isFakeDragging()) {
                    return true;
                }
                this.f.endFakeDrag();
                return true;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.q));
                float f3 = x - this.p;
                if (!this.r && Math.abs(f3) > this.o) {
                    this.r = true;
                }
                if (!this.r) {
                    return true;
                }
                this.p = x;
                if (!this.f.isFakeDragging() && !this.f.beginFakeDrag()) {
                    return true;
                }
                this.f.fakeDragBy(f3);
                return true;
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.p = MotionEventCompat.getX(motionEvent, actionIndex);
                this.q = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                return true;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.q) {
                    this.q = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                }
                this.p = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.q));
                return true;
        }
    }

    public void setCentered(boolean z) {
        this.m = z;
        invalidate();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.viewpagerindicator.a
    public void setCurrentItem(int i) {
        if (this.f == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.f.setCurrentItem(i);
        this.h = i;
        invalidate();
    }

    public void setFillColor(int i) {
        this.d.setColor(i);
        invalidate();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.viewpagerindicator.a
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    public void setOrientation(int i) {
        switch (i) {
            case 0:
            case 1:
                this.l = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public void setPageColor(int i) {
        this.b.setColor(i);
        invalidate();
    }

    public void setRadius(float f) {
        this.e = f;
        invalidate();
    }

    public void setSnap(boolean z) {
        this.n = z;
        invalidate();
    }

    public void setStrokeColor(int i) {
        this.c.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f) {
        this.c.setStrokeWidth(f);
        invalidate();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.viewpagerindicator.a
    public void setViewPager(ViewPager viewPager) {
        if (this.f == viewPager) {
            return;
        }
        if (this.f != null) {
            this.f.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.f = viewPager;
        this.f.setOnPageChangeListener(this);
        invalidate();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.viewpagerindicator.a
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
