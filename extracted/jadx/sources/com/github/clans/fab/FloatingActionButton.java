package com.github.clans.fab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FloatingActionButton extends ImageButton {
    public static final int a = 0;
    public static final int b = 1;
    private static final Xfermode k = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private static final long l = 200;
    private static final double m = 500.0d;
    private static final int n = 270;
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private boolean F;
    private float G;
    private float H;
    private boolean I;
    private RectF J;
    private Paint K;
    private Paint L;
    private boolean M;
    private long N;
    private float O;
    private long P;
    private double Q;
    private boolean R;
    private int S;
    private float T;
    private float U;
    private float V;
    private int W;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private int ad;
    private boolean ae;
    private boolean af;
    private Drawable ag;
    int c;
    boolean d;
    int e;
    int f;
    int g;
    int h;
    GestureDetector i;
    boolean j;
    private int o;
    private int p;
    private int q;
    private int r;
    private Drawable s;
    private int t;
    private Animation u;
    private Animation v;
    private String w;
    private View.OnClickListener x;
    private Drawable y;
    private boolean z;

    static class ProgressSavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<ProgressSavedState> CREATOR = new Parcelable.Creator<ProgressSavedState>() { // from class: com.github.clans.fab.FloatingActionButton.ProgressSavedState.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ProgressSavedState createFromParcel(Parcel parcel) {
                return new ProgressSavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ProgressSavedState[] newArray(int i) {
                return new ProgressSavedState[i];
            }
        };
        float a;
        float b;
        float c;
        int d;
        int e;
        int f;
        int g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        boolean n;

        private ProgressSavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readFloat();
            this.b = parcel.readFloat();
            this.h = parcel.readInt() != 0;
            this.c = parcel.readFloat();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.i = parcel.readInt() != 0;
            this.j = parcel.readInt() != 0;
            this.k = parcel.readInt() != 0;
            this.l = parcel.readInt() != 0;
            this.m = parcel.readInt() != 0;
            this.n = parcel.readInt() != 0;
        }

        ProgressSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.a);
            parcel.writeFloat(this.b);
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeFloat(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeInt(this.l ? 1 : 0);
            parcel.writeInt(this.m ? 1 : 0);
            parcel.writeInt(this.n ? 1 : 0);
        }
    }

    private class a extends ShapeDrawable {
        private int b;
        private int c;

        private a() {
        }

        private a(Shape shape) {
            super(shape);
            this.b = FloatingActionButton.this.h() ? FloatingActionButton.this.f + Math.abs(FloatingActionButton.this.g) : 0;
            this.c = FloatingActionButton.this.h() ? Math.abs(FloatingActionButton.this.h) + FloatingActionButton.this.f : 0;
            if (FloatingActionButton.this.B) {
                this.b += FloatingActionButton.this.C;
                this.c += FloatingActionButton.this.C;
            }
        }

        @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            setBounds(this.b, this.c, FloatingActionButton.this.m() - this.b, FloatingActionButton.this.n() - this.c);
            super.draw(canvas);
        }
    }

    private class b extends Drawable {
        private Paint b = new Paint(1);
        private Paint c = new Paint(1);
        private float d;

        private b() {
            a();
        }

        private void a() {
            FloatingActionButton.this.setLayerType(1, null);
            this.b.setStyle(Paint.Style.FILL);
            this.b.setColor(FloatingActionButton.this.o);
            this.c.setXfermode(FloatingActionButton.k);
            if (!FloatingActionButton.this.isInEditMode()) {
                this.b.setShadowLayer(FloatingActionButton.this.f, FloatingActionButton.this.g, FloatingActionButton.this.h, FloatingActionButton.this.e);
            }
            this.d = FloatingActionButton.this.getCircleSize() / 2;
            if (FloatingActionButton.this.B && FloatingActionButton.this.ae) {
                this.d += FloatingActionButton.this.C;
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            canvas.drawCircle(FloatingActionButton.this.o(), FloatingActionButton.this.p(), this.d, this.b);
            canvas.drawCircle(FloatingActionButton.this.o(), FloatingActionButton.this.p(), this.d, this.c);
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

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = com.github.clans.fab.a.a(getContext(), 4.0f);
        this.g = com.github.clans.fab.a.a(getContext(), 1.0f);
        this.h = com.github.clans.fab.a.a(getContext(), 3.0f);
        this.i = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.FloatingActionButton.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                Label label = (Label) FloatingActionButton.this.getTag(R.id.fab_label);
                if (label != null) {
                    label.d();
                }
                FloatingActionButton.this.f();
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Label label = (Label) FloatingActionButton.this.getTag(R.id.fab_label);
                if (label != null) {
                    label.e();
                }
                FloatingActionButton.this.g();
                return super.onSingleTapUp(motionEvent);
            }
        });
        this.t = com.github.clans.fab.a.a(getContext(), 24.0f);
        this.C = com.github.clans.fab.a.a(getContext(), 6.0f);
        this.G = -1.0f;
        this.H = -1.0f;
        this.J = new RectF();
        this.K = new Paint(1);
        this.L = new Paint(1);
        this.O = 195.0f;
        this.P = 0L;
        this.R = true;
        this.S = 16;
        this.ad = 100;
        a(context, attributeSet, i);
    }

    @TargetApi(21)
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f = com.github.clans.fab.a.a(getContext(), 4.0f);
        this.g = com.github.clans.fab.a.a(getContext(), 1.0f);
        this.h = com.github.clans.fab.a.a(getContext(), 3.0f);
        this.i = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.FloatingActionButton.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                Label label = (Label) FloatingActionButton.this.getTag(R.id.fab_label);
                if (label != null) {
                    label.d();
                }
                FloatingActionButton.this.f();
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Label label = (Label) FloatingActionButton.this.getTag(R.id.fab_label);
                if (label != null) {
                    label.e();
                }
                FloatingActionButton.this.g();
                return super.onSingleTapUp(motionEvent);
            }
        });
        this.t = com.github.clans.fab.a.a(getContext(), 24.0f);
        this.C = com.github.clans.fab.a.a(getContext(), 6.0f);
        this.G = -1.0f;
        this.H = -1.0f;
        this.J = new RectF();
        this.K = new Paint(1);
        this.L = new Paint(1);
        this.O = 195.0f;
        this.P = 0L;
        this.R = true;
        this.S = 16;
        this.ad = 100;
        a(context, attributeSet, i);
    }

    private Drawable a(int i) {
        a aVar = new a(new OvalShape());
        aVar.getPaint().setColor(i);
        return aVar;
    }

    private void a(long j) {
        if (this.P < l) {
            this.P += j;
            return;
        }
        this.Q += j;
        if (this.Q > m) {
            this.Q -= m;
            this.P = 0L;
            this.R = !this.R;
        }
        float fCos = (((float) Math.cos(((this.Q / m) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
        float f = 270 - this.S;
        if (this.R) {
            this.T = fCos * f;
            return;
        }
        float f2 = (1.0f - fCos) * f;
        this.U += this.T - f2;
        this.T = f2;
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, 0);
        this.o = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_colorNormal, -2473162);
        this.p = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_colorPressed, -1617853);
        this.q = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_colorDisabled, -5592406);
        this.r = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_colorRipple, -1711276033);
        this.d = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_fab_showShadow, true);
        this.e = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_shadowColor, 1711276032);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowRadius, this.f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowXOffset, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowYOffset, this.h);
        this.c = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fab_size, 0);
        this.w = typedArrayObtainStyledAttributes.getString(R.styleable.FloatingActionButton_fab_label);
        this.ab = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_fab_progress_indeterminate, false);
        this.D = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_progress_color, -16738680);
        this.E = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionButton_fab_progress_backgroundColor, 1291845632);
        this.ad = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fab_progress_max, this.ad);
        this.ae = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_fab_progress_showBackground, true);
        this.j = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_fab_is_trash, false);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.FloatingActionButton_fab_progress)) {
            this.W = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fab_progress, 0);
            this.ac = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.FloatingActionButton_fab_elevationCompat)) {
            float dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingActionButton_fab_elevationCompat, 0);
            if (isInEditMode()) {
                setElevation(dimensionPixelOffset);
            } else {
                setElevationCompat(dimensionPixelOffset);
            }
        }
        a(typedArrayObtainStyledAttributes);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        if (isInEditMode()) {
            if (this.ab) {
                setIndeterminate(true);
            } else if (this.ac) {
                r();
                setProgress(this.W, false);
            }
        }
        d.e().a(getResources(), getId(), this);
        setClickable(true);
    }

    private void a(TypedArray typedArray) {
        this.u = AnimationUtils.loadAnimation(getContext(), typedArray.getResourceId(R.styleable.FloatingActionButton_fab_showAnimation, R.anim.fab_scale_up));
    }

    private void b(TypedArray typedArray) {
        this.v = AnimationUtils.loadAnimation(getContext(), typedArray.getResourceId(R.styleable.FloatingActionButton_fab_hideAnimation, R.anim.fab_scale_down));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCircleSize() {
        return getResources().getDimensionPixelSize(this.c == 0 ? R.dimen.glink_widget_size_normal : R.dimen.fab_size_mini);
    }

    private int getShadowX() {
        return this.f + Math.abs(this.g);
    }

    private int getShadowY() {
        return this.f + Math.abs(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int m() {
        int circleSize = getCircleSize();
        return this.B ? circleSize + (this.C * 2) : circleSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int n() {
        int circleSize = getCircleSize();
        return this.B ? circleSize + (this.C * 2) : circleSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float o() {
        return getMeasuredWidth() / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float p() {
        return getMeasuredHeight() / 2;
    }

    @TargetApi(21)
    private Drawable q() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[0], getIconDrawable());
        if (!com.github.clans.fab.a.b()) {
            this.y = stateListDrawable;
            return stateListDrawable;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{this.r}), stateListDrawable, null);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.github.clans.fab.FloatingActionButton.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        setClipToOutline(true);
        this.y = rippleDrawable;
        return rippleDrawable;
    }

    private void r() {
        if (this.I) {
            return;
        }
        if (this.G == -1.0f) {
            this.G = getX();
        }
        if (this.H == -1.0f) {
            this.H = getY();
        }
        this.I = true;
    }

    private void s() {
        float x;
        float y;
        if (this.B) {
            x = this.G > getX() ? getX() + this.C : getX() - this.C;
            y = this.H > getY() ? getY() + this.C : getY() - this.C;
        } else {
            x = this.G;
            y = this.H;
        }
        setX(x);
        setY(y);
    }

    @TargetApi(16)
    private void setBackgroundCompat(Drawable drawable) {
        if (com.github.clans.fab.a.a()) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    private void t() {
        this.K.setColor(this.E);
        this.K.setStyle(Paint.Style.STROKE);
        this.K.setStrokeWidth(this.C);
        this.L.setColor(this.D);
        this.L.setStyle(Paint.Style.STROKE);
        this.L.setStrokeWidth(this.C);
    }

    private void u() {
        int shadowX = h() ? getShadowX() : 0;
        int shadowY = h() ? getShadowY() : 0;
        this.J = new RectF((this.C / 2) + shadowX, (this.C / 2) + shadowY, (m() - shadowX) - (this.C / 2), (n() - shadowY) - (this.C / 2));
    }

    int a() {
        if (h()) {
            return getShadowX() * 2;
        }
        return 0;
    }

    void a(int i, int i2, int i3) {
        this.o = i;
        this.p = i2;
        this.r = i3;
    }

    public void a(boolean z) {
        if (i()) {
            if (z) {
                d();
            }
            super.setVisibility(0);
        }
    }

    int b() {
        if (h()) {
            return getShadowY() * 2;
        }
        return 0;
    }

    public void b(boolean z) {
        if (i()) {
            return;
        }
        if (z) {
            e();
        }
        super.setVisibility(4);
    }

    void c() {
        setBackgroundCompat(getIconDrawable());
    }

    public void c(boolean z) {
        if (i()) {
            a(z);
        } else {
            b(z);
        }
    }

    void d() {
        this.v.cancel();
        startAnimation(this.u);
    }

    public void d(boolean z) {
        if (i() || getVisibility() == 8) {
            return;
        }
        b(z);
        Label labelView = getLabelView();
        if (labelView != null) {
            labelView.b(z);
        }
        getHideAnimation().setAnimationListener(new Animation.AnimationListener() { // from class: com.github.clans.fab.FloatingActionButton.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                FloatingActionButton.this.setVisibility(8);
                FloatingActionButton.this.getHideAnimation().setAnimationListener(null);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    void e() {
        this.u.cancel();
        startAnimation(this.v);
    }

    public void e(boolean z) {
        if (getVisibility() == 0) {
            return;
        }
        setVisibility(4);
        a(z);
        Label labelView = getLabelView();
        if (labelView != null) {
            labelView.a(z);
        }
    }

    @TargetApi(21)
    void f() {
    }

    @TargetApi(21)
    void g() {
    }

    public int getButtonSize() {
        return this.c;
    }

    public int getColorDisabled() {
        return this.q;
    }

    public int getColorNormal() {
        return this.o;
    }

    public int getColorPressed() {
        return this.p;
    }

    public int getColorRipple() {
        return this.r;
    }

    Animation getHideAnimation() {
        return this.v;
    }

    protected Drawable getIconDrawable() {
        return this.s != null ? this.s : new ColorDrawable(0);
    }

    public String getLabelText() {
        return this.w;
    }

    Label getLabelView() {
        return (Label) getTag(R.id.fab_label);
    }

    public int getLabelVisibility() {
        Label labelView = getLabelView();
        if (labelView != null) {
            return labelView.getVisibility();
        }
        return -1;
    }

    public synchronized int getMax() {
        return this.ad;
    }

    View.OnClickListener getOnClickListener() {
        return this.x;
    }

    public synchronized int getProgress() {
        return this.M ? 0 : this.W;
    }

    public int getShadowColor() {
        return this.e;
    }

    public int getShadowRadius() {
        return this.f;
    }

    public int getShadowXOffset() {
        return this.g;
    }

    public int getShadowYOffset() {
        return this.h;
    }

    Animation getShowAnimation() {
        return this.u;
    }

    public boolean h() {
        return !this.z && this.d;
    }

    public boolean i() {
        return getVisibility() == 4;
    }

    public synchronized void j() {
        this.B = false;
        this.F = true;
        c();
    }

    public synchronized boolean k() {
        return this.ae;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        boolean z = true;
        super.onDraw(canvas);
        if (this.B) {
            if (this.ae) {
                canvas.drawArc(this.J, 360.0f, 360.0f, false, this.K);
            }
            if (this.M) {
                long jUptimeMillis = SystemClock.uptimeMillis() - this.N;
                float f = (jUptimeMillis * this.O) / 1000.0f;
                a(jUptimeMillis);
                this.U += f;
                if (this.U > 360.0f) {
                    this.U -= 360.0f;
                }
                this.N = SystemClock.uptimeMillis();
                float f2 = this.U - 90.0f;
                float f3 = this.S + this.T;
                if (isInEditMode()) {
                    f2 = 0.0f;
                    f3 = 135.0f;
                }
                canvas.drawArc(this.J, f2, f3, false, this.L);
            } else {
                if (this.U != this.V) {
                    float fUptimeMillis = ((SystemClock.uptimeMillis() - this.N) / 1000.0f) * this.O;
                    if (this.U > this.V) {
                        this.U = Math.max(this.U - fUptimeMillis, this.V);
                    } else {
                        this.U = Math.min(fUptimeMillis + this.U, this.V);
                    }
                    this.N = SystemClock.uptimeMillis();
                } else {
                    z = false;
                }
                canvas.drawArc(this.J, -90.0f, this.U, false, this.L);
            }
            if (z) {
                invalidate();
            }
        }
        if (!this.af || this.ag == null) {
            return;
        }
        this.ag.setBounds(getWidth() - this.ag.getIntrinsicWidth(), 0, getWidth(), this.ag.getIntrinsicHeight() + 0);
        this.ag.draw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(m(), n());
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ProgressSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ProgressSavedState progressSavedState = (ProgressSavedState) parcelable;
        super.onRestoreInstanceState(progressSavedState.getSuperState());
        this.U = progressSavedState.a;
        this.V = progressSavedState.b;
        this.O = progressSavedState.c;
        this.C = progressSavedState.e;
        this.D = progressSavedState.f;
        this.E = progressSavedState.g;
        this.ab = progressSavedState.k;
        this.ac = progressSavedState.l;
        this.W = progressSavedState.d;
        this.aa = progressSavedState.m;
        this.ae = progressSavedState.n;
        this.N = SystemClock.uptimeMillis();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        ProgressSavedState progressSavedState = new ProgressSavedState(super.onSaveInstanceState());
        progressSavedState.a = this.U;
        progressSavedState.b = this.V;
        progressSavedState.c = this.O;
        progressSavedState.e = this.C;
        progressSavedState.f = this.D;
        progressSavedState.g = this.E;
        progressSavedState.k = this.M;
        progressSavedState.l = this.B && this.W > 0 && !this.M;
        progressSavedState.d = this.W;
        progressSavedState.m = this.aa;
        progressSavedState.n = this.ae;
        return progressSavedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        r();
        if (this.ab) {
            setIndeterminate(true);
            this.ab = false;
        } else if (this.ac) {
            setProgress(this.W, this.aa);
            this.ac = false;
        } else if (this.F) {
            s();
            this.F = false;
        }
        super.onSizeChanged(i, i2, i3, i4);
        u();
        t();
        c();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.x != null && isEnabled()) {
            Label label = (Label) getTag(R.id.fab_label);
            if (label == null) {
                return super.onTouchEvent(motionEvent);
            }
            switch (motionEvent.getAction()) {
                case 1:
                    if (label != null) {
                        label.e();
                    }
                    g();
                    break;
                case 3:
                    if (label != null) {
                        label.e();
                    }
                    g();
                    break;
            }
            this.i.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setButtonSize(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Use @FabSize constants only!");
        }
        if (this.c != i) {
            this.c = i;
            c();
        }
    }

    public void setColorDisabled(int i) {
        if (i != this.q) {
            this.q = i;
            c();
        }
    }

    public void setColorDisabledResId(int i) {
        setColorDisabled(ContextCompat.getColor(getContext(), i));
    }

    public void setColorNormal(int i) {
        if (this.o != i) {
            this.o = i;
            c();
        }
    }

    public void setColorNormalResId(int i) {
        setColorNormal(ContextCompat.getColor(getContext(), i));
    }

    public void setColorPressed(int i) {
        if (i != this.p) {
            this.p = i;
            c();
        }
    }

    public void setColorPressedResId(int i) {
        setColorPressed(ContextCompat.getColor(getContext(), i));
    }

    public void setColorRipple(int i) {
        if (i != this.r) {
            this.r = i;
            c();
        }
    }

    public void setColorRippleResId(int i) {
        setColorRipple(ContextCompat.getColor(getContext(), i));
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (!com.github.clans.fab.a.b() || f <= 0.0f) {
            return;
        }
        super.setElevation(f);
        if (!isInEditMode()) {
            this.z = true;
            this.d = false;
        }
        c();
    }

    @TargetApi(21)
    public void setElevationCompat(float f) {
        this.e = 637534208;
        this.f = Math.round(f / 2.0f);
        this.g = 0;
        this.h = Math.round(this.c == 0 ? f : f / 2.0f);
        if (!com.github.clans.fab.a.b()) {
            this.d = true;
            c();
            return;
        }
        super.setElevation(f);
        this.A = true;
        this.d = false;
        c();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            setLayoutParams(layoutParams);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        Label label = (Label) getTag(R.id.fab_label);
        if (label != null) {
            label.setEnabled(z);
        }
    }

    public void setHideAnimation(Animation animation) {
        this.v = animation;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.s != drawable) {
            this.s = drawable;
            c();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        if (this.s != drawable) {
            this.s = drawable;
            c();
        }
    }

    public synchronized void setIndeterminate(boolean z) {
        if (!z) {
            this.U = 0.0f;
        }
        this.B = z;
        this.F = true;
        this.M = z;
        this.N = SystemClock.uptimeMillis();
        u();
        r();
        c();
    }

    public void setLabelText(String str) {
        this.w = str;
        Label labelView = getLabelView();
        if (labelView != null) {
            labelView.setText(str);
        }
    }

    public void setLabelVisibility(int i) {
        Label labelView = getLabelView();
        if (labelView != null) {
            labelView.setVisibility(i);
            labelView.setHandleVisibilityChanges(i == 0);
        }
    }

    @Override // android.view.View
    @TargetApi(21)
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && this.A) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin += getShadowX();
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin += getShadowY();
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin += getShadowX();
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += getShadowY();
        }
        super.setLayoutParams(layoutParams);
    }

    public synchronized void setMax(int i) {
        this.ad = i;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.x = onClickListener;
        View view = (View) getTag(R.id.fab_label);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.github.clans.fab.FloatingActionButton.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (FloatingActionButton.this.x != null) {
                        FloatingActionButton.this.x.onClick(FloatingActionButton.this);
                    }
                }
            });
        }
    }

    public synchronized void setProgress(int i, boolean z) {
        if (!this.M) {
            this.W = i;
            this.aa = z;
            if (this.I) {
                this.B = true;
                this.F = true;
                u();
                r();
                c();
                if (i < 0) {
                    i = 0;
                } else if (i > this.ad) {
                    i = this.ad;
                }
                if (i != this.V) {
                    this.V = this.ad > 0 ? (i / this.ad) * 360.0f : 0.0f;
                    this.N = SystemClock.uptimeMillis();
                    if (!z) {
                        this.U = this.V;
                    }
                    invalidate();
                }
            } else {
                this.ac = true;
            }
        }
    }

    public void setShadowColor(int i) {
        if (this.e != i) {
            this.e = i;
            c();
        }
    }

    public void setShadowColorResource(int i) {
        int color = ContextCompat.getColor(getContext(), i);
        if (this.e != color) {
            this.e = color;
            c();
        }
    }

    public void setShadowRadius(float f) {
        this.f = com.github.clans.fab.a.a(getContext(), f);
        requestLayout();
        c();
    }

    public void setShadowRadius(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        if (this.f != dimensionPixelSize) {
            this.f = dimensionPixelSize;
            requestLayout();
            c();
        }
    }

    public void setShadowXOffset(float f) {
        this.g = com.github.clans.fab.a.a(getContext(), f);
        requestLayout();
        c();
    }

    public void setShadowXOffset(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        if (this.g != dimensionPixelSize) {
            this.g = dimensionPixelSize;
            requestLayout();
            c();
        }
    }

    public void setShadowYOffset(float f) {
        this.h = com.github.clans.fab.a.a(getContext(), f);
        requestLayout();
        c();
    }

    public void setShadowYOffset(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        if (this.h != dimensionPixelSize) {
            this.h = dimensionPixelSize;
            requestLayout();
            c();
        }
    }

    public void setShowAnimation(Animation animation) {
        this.u = animation;
    }

    public void setShowNewIcon(boolean z) {
        this.af = z;
    }

    public void setShowNewIconDrawable(Drawable drawable) {
        this.ag = drawable;
    }

    public synchronized void setShowProgressBackground(boolean z) {
        this.ae = z;
    }

    public void setShowShadow(boolean z) {
        if (this.d != z) {
            this.d = z;
            c();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        Label label = (Label) getTag(R.id.fab_label);
        if (label != null) {
            label.setVisibility(i);
        }
    }
}
