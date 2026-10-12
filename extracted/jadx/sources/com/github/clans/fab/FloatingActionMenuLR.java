package com.github.clans.fab;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.d;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FloatingActionMenuLR extends AbsFloatingActionMenu {
    private int A;
    private int B;
    private int C;
    private int D;
    private ColorStateList E;
    private float F;
    private int G;
    private boolean H;
    private int I;
    private int J;
    private int K;
    private boolean L;
    private int M;
    private float N;
    private float O;
    private float P;
    private int Q;
    private int R;
    private int S;
    private Drawable T;
    private int U;
    private Interpolator V;
    private Interpolator W;
    private boolean aa;
    private boolean ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    private ImageView ah;
    private Animation ai;
    private Animation aj;
    private boolean ak;
    private boolean al;
    private int am;
    private ValueAnimator an;
    private ValueAnimator ao;
    private int ap;
    private int aq;
    private Context ar;
    private String as;
    private boolean at;
    GestureDetector l;
    private AnimatorSet m;
    private AnimatorSet n;
    private AnimatorSet o;
    private int p;
    private FloatingActionButton q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private Handler x;
    private int y;
    private int z;

    public FloatingActionMenuLR(Context context) {
        this(context, null);
    }

    public FloatingActionMenuLR(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingActionMenuLR(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new AnimatorSet();
        this.n = new AnimatorSet();
        this.p = a.a(getContext(), 0.0f);
        this.s = a.a(getContext(), 0.0f);
        this.t = a.a(getContext(), 0.0f);
        this.x = new Handler();
        this.A = a.a(getContext(), 4.0f);
        this.B = a.a(getContext(), 5.0f);
        this.C = a.a(getContext(), 4.0f);
        this.D = a.a(getContext(), 5.0f);
        this.G = a.a(getContext(), 3.0f);
        this.N = 4.0f;
        this.O = 1.0f;
        this.P = 3.0f;
        this.aa = true;
        this.ag = true;
        this.l = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.github.clans.fab.FloatingActionMenuLR.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return FloatingActionMenuLR.this.al && FloatingActionMenuLR.this.a();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                FloatingActionMenuLR.this.c(FloatingActionMenuLR.this.aa);
                return true;
            }
        });
        a(context, attributeSet);
    }

    private void a(int i) {
        this.A = i;
        this.B = i;
        this.C = i;
        this.D = i;
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionMenu, 0, 0);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_buttonSpacing, this.p);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_margin, this.s);
        this.aq = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_labels_position, 0);
        this.y = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FloatingActionMenu_menu_labels_showAnimation, this.aq == 0 ? R.anim.fab_slide_in_from_right : R.anim.fab_slide_in_from_left);
        this.z = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FloatingActionMenu_menu_labels_hideAnimation, this.aq == 0 ? R.anim.fab_slide_out_to_right : R.anim.fab_slide_out_to_left);
        this.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingTop, this.A);
        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingRight, this.B);
        this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingBottom, this.C);
        this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingLeft, this.D);
        this.E = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.FloatingActionMenu_menu_labels_textColor);
        if (this.E == null) {
            this.E = ColorStateList.valueOf(-1);
        }
        this.F = typedArrayObtainStyledAttributes.getDimension(R.styleable.FloatingActionMenu_menu_labels_textSize, getResources().getDimension(R.dimen.labels_text_size));
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_cornerRadius, this.G);
        this.H = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionMenu_menu_labels_showShadow, true);
        this.I = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_labels_colorNormal, -13421773);
        this.J = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_labels_colorPressed, -12303292);
        this.K = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_labels_colorRipple, 1728053247);
        this.L = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionMenu_menu_showShadow, true);
        this.M = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_shadowColor, 1711276032);
        this.N = typedArrayObtainStyledAttributes.getDimension(R.styleable.FloatingActionMenu_menu_shadowRadius, this.N);
        this.O = typedArrayObtainStyledAttributes.getDimension(R.styleable.FloatingActionMenu_menu_shadowXOffset, this.O);
        this.P = typedArrayObtainStyledAttributes.getDimension(R.styleable.FloatingActionMenu_menu_shadowYOffset, this.P);
        this.Q = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_colorNormal, -2473162);
        this.R = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_colorPressed, -1617853);
        this.S = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_colorRipple, -1711276033);
        this.U = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_animationDelayPerItem, 50);
        this.T = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FloatingActionMenu_menu_icon);
        if (this.T == null) {
            this.T = getResources().getDrawable(R.drawable.cf_btn_wdclose_normal);
        }
        this.ab = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionMenu_menu_labels_singleLine, false);
        this.ac = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_labels_ellipsize, 0);
        this.ad = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_labels_maxLines, -1);
        this.ae = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_fab_size, 0);
        this.af = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FloatingActionMenu_menu_labels_style, 0);
        this.am = typedArrayObtainStyledAttributes.getInt(R.styleable.FloatingActionMenu_menu_openDirection, 2);
        this.ap = typedArrayObtainStyledAttributes.getColor(R.styleable.FloatingActionMenu_menu_backgroundColor, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.FloatingActionMenu_menu_fab_label)) {
            this.at = true;
            this.as = typedArrayObtainStyledAttributes.getString(R.styleable.FloatingActionMenu_menu_fab_label);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.FloatingActionMenu_menu_labels_padding)) {
            a(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_padding, 0));
        }
        this.V = new OvershootInterpolator();
        this.W = new AnticipateInterpolator();
        this.ar = new ContextThemeWrapper(getContext(), this.af);
        h();
        j();
        a(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void a(TypedArray typedArray) {
        setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getContext(), typedArray.getResourceId(R.styleable.FloatingActionMenu_menu_fab_show_animation, R.anim.fab_scale_up)));
        setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getContext(), typedArray.getResourceId(R.styleable.FloatingActionMenu_menu_fab_hide_animation, R.anim.fab_scale_down)));
    }

    private int b(int i) {
        return (int) ((((double) i) * 0.03d) + ((double) i));
    }

    private void c(FloatingActionButton floatingActionButton) {
        String labelText = floatingActionButton.getLabelText();
        if (TextUtils.isEmpty(labelText)) {
            return;
        }
        Label label = new Label(this.ar);
        label.setClickable(true);
        label.setFab(floatingActionButton);
        label.setShowAnimation(AnimationUtils.loadAnimation(getContext(), this.y));
        label.setHideAnimation(AnimationUtils.loadAnimation(getContext(), this.z));
        if (this.af > 0) {
            label.setTextAppearance(getContext(), this.af);
            label.setShowShadow(false);
            label.setUsingStyle(true);
        } else {
            label.a(this.I, this.J, this.K);
            label.setShowShadow(this.H);
            label.setCornerRadius(this.G);
            if (this.ac > 0) {
                setLabelEllipsize(label);
            }
            label.setMaxLines(this.ad);
            label.c();
            label.setTextSize(0, this.F);
            label.setTextColor(this.E);
            int shadowRadius = this.D;
            int shadowRadius2 = this.A;
            if (this.H) {
                shadowRadius += floatingActionButton.getShadowRadius() + Math.abs(floatingActionButton.getShadowXOffset());
                shadowRadius2 += floatingActionButton.getShadowRadius() + Math.abs(floatingActionButton.getShadowYOffset());
            }
            label.setPadding(shadowRadius, shadowRadius2, this.D, this.A);
        }
        label.setMaxLines(4);
        label.setMaxWidth(getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal) + x.a(10.0f));
        label.setGravity(17);
        label.setText(labelText);
        label.setOnClickListener(floatingActionButton.getOnClickListener());
        addView(label);
        floatingActionButton.setTag(R.id.fab_label, label);
    }

    private void h() {
        int iAlpha = Color.alpha(this.ap);
        final int iRed = Color.red(this.ap);
        final int iGreen = Color.green(this.ap);
        final int iBlue = Color.blue(this.ap);
        this.an = ValueAnimator.ofInt(0, iAlpha);
        this.an.setDuration(200L);
        this.an.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.clans.fab.FloatingActionMenuLR.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FloatingActionMenuLR.this.setBackgroundColor(Color.argb(((Integer) valueAnimator.getAnimatedValue()).intValue(), iRed, iGreen, iBlue));
            }
        });
        this.ao = ValueAnimator.ofInt(iAlpha, 0);
        this.ao.setDuration(200L);
        this.ao.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.clans.fab.FloatingActionMenuLR.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FloatingActionMenuLR.this.setBackgroundColor(Color.argb(((Integer) valueAnimator.getAnimatedValue()).intValue(), iRed, iGreen, iBlue));
            }
        });
    }

    private boolean i() {
        return this.ap != 0;
    }

    private void j() {
        this.q = new FloatingActionButton(getContext());
        this.q.d = this.L;
        if (this.L) {
            this.q.f = a.a(getContext(), this.N);
            this.q.g = a.a(getContext(), this.O);
            this.q.h = a.a(getContext(), this.P);
        }
        this.q.a(this.Q, this.R, this.S);
        this.q.e = this.M;
        this.q.c = this.ae;
        this.q.c();
        this.q.setLabelText(this.as);
        this.ah = new ImageView(getContext());
        d.e().a(getResources(), getId(), this.ah);
        addView(this.q, super.generateDefaultLayoutParams());
        addView(this.ah);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(boolean z) {
        if (f()) {
            return;
        }
        this.q.b(z);
        if (z) {
            this.ah.startAnimation(this.aj);
        }
        this.ah.setVisibility(4);
        this.ak = false;
    }

    private void k() {
        if (this.am == 2) {
            if (this.aq == 0) {
            }
            if (this.aq == 0) {
            }
        } else {
            if (this.aq == 0) {
            }
            if (this.aq == 0) {
            }
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.ah, "rotation", 180.0f, 0.0f);
        this.m.play(ObjectAnimator.ofFloat(this.ah, "rotation", 180.0f, 0.0f));
        this.n.play(objectAnimatorOfFloat);
        this.m.setInterpolator(this.V);
        this.n.setInterpolator(this.W);
        this.m.setDuration(200L);
        this.n.setDuration(200L);
    }

    private void k(boolean z) {
        if (f()) {
            this.q.a(z);
            if (z) {
                this.ah.startAnimation(this.ai);
            }
            this.ah.setVisibility(0);
        }
    }

    private void l() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.u) {
                return;
            }
            if (getChildAt(i2) != this.ah) {
                FloatingActionButton floatingActionButton = (FloatingActionButton) getChildAt(i2);
                if (floatingActionButton.getTag(R.id.fab_label) == null) {
                    c(floatingActionButton);
                    if (floatingActionButton == this.q) {
                        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.github.clans.fab.FloatingActionMenuLR.5
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                FloatingActionMenuLR.this.a(FloatingActionMenuLR.this.aa);
                            }
                        });
                    }
                }
            }
            i = i2 + 1;
        }
    }

    private void setLabelEllipsize(Label label) {
        switch (this.ac) {
            case 1:
                label.setEllipsize(TextUtils.TruncateAt.START);
                break;
            case 2:
                label.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            case 3:
                label.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case 4:
                label.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                break;
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ViewGroup.MarginLayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ViewGroup.MarginLayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    public void a(FloatingActionButton floatingActionButton) {
        addView(floatingActionButton, this.u - 2);
        this.u++;
        c(floatingActionButton);
    }

    public void a(FloatingActionButton floatingActionButton, int i) {
        int i2 = this.u - 2;
        if (i < 0) {
            i = 0;
        } else if (i > i2) {
            i = i2;
        }
        addView(floatingActionButton, i);
        this.u++;
        c(floatingActionButton);
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public void a(boolean z) {
        if (a()) {
            c(z);
        } else {
            b(z);
        }
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public boolean a() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ViewGroup.MarginLayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    public void b(FloatingActionButton floatingActionButton) {
        removeView(floatingActionButton.getLabelView());
        removeView(floatingActionButton);
        this.u--;
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public void b(final boolean z) {
        int i;
        if (a()) {
            return;
        }
        if (i()) {
        }
        if (this.ag) {
            if (this.o != null) {
                this.o.start();
            } else {
                this.n.cancel();
                this.m.start();
            }
        }
        this.w = true;
        int childCount = getChildCount() - 1;
        int i2 = 0;
        int i3 = 0;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            if (!(childAt instanceof FloatingActionButton) || childAt.getVisibility() == 8) {
                i = i2;
            } else {
                int i4 = i2 + 1;
                final FloatingActionButton floatingActionButton = (FloatingActionButton) childAt;
                this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (FloatingActionMenuLR.this.a()) {
                            return;
                        }
                        if (floatingActionButton != FloatingActionMenuLR.this.q) {
                            floatingActionButton.a(z);
                        }
                        Label label = (Label) floatingActionButton.getTag(R.id.fab_label);
                        if (label == null || !label.f()) {
                            return;
                        }
                        label.a(z);
                    }
                }, floatingActionButton.j ? 80 : i3);
                i3 += this.U;
                i = i4;
            }
            childCount--;
            i2 = i;
        }
        this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.7
            @Override // java.lang.Runnable
            public void run() {
                FloatingActionMenuLR.this.v = true;
                if (FloatingActionMenuLR.this.k != null) {
                    FloatingActionMenuLR.this.k.a(true);
                }
            }
        }, (i2 + 1) * this.U);
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public void c(final boolean z) {
        if (a()) {
            if (i()) {
            }
            if (this.ag) {
                if (this.o != null) {
                    this.o.start();
                } else {
                    this.n.start();
                    this.m.cancel();
                }
            }
            this.w = false;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if ((childAt instanceof FloatingActionButton) && childAt.getVisibility() != 8) {
                    i++;
                    final FloatingActionButton floatingActionButton = (FloatingActionButton) childAt;
                    this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.8
                        @Override // java.lang.Runnable
                        public void run() {
                            if (FloatingActionMenuLR.this.a()) {
                                if (floatingActionButton != FloatingActionMenuLR.this.q) {
                                    floatingActionButton.b(z);
                                }
                                Label label = (Label) floatingActionButton.getTag(R.id.fab_label);
                                if (label == null || !label.f()) {
                                    return;
                                }
                                label.b(z);
                            }
                        }
                    }, i2);
                    i2 += this.U;
                }
            }
            this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.9
                @Override // java.lang.Runnable
                public void run() {
                    FloatingActionMenuLR.this.v = false;
                    if (FloatingActionMenuLR.this.k != null) {
                        FloatingActionMenuLR.this.k.a(false);
                    }
                }
            }, (i + 1) * this.U);
        }
    }

    public boolean c() {
        return this.aa;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    public void d(boolean z) {
        if (e()) {
            if (z) {
                startAnimation(this.ai);
            }
            setVisibility(0);
        }
    }

    public boolean d() {
        return this.ag;
    }

    public void e(final boolean z) {
        if (e() || this.ak) {
            return;
        }
        this.ak = true;
        if (a()) {
            c(z);
            this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.10
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        FloatingActionMenuLR.this.startAnimation(FloatingActionMenuLR.this.aj);
                    }
                    FloatingActionMenuLR.this.setVisibility(4);
                    FloatingActionMenuLR.this.ak = false;
                }
            }, this.U * this.u);
        } else {
            if (z) {
                startAnimation(this.aj);
            }
            setVisibility(4);
            this.ak = false;
        }
    }

    public boolean e() {
        return getVisibility() == 4;
    }

    public void f(boolean z) {
        if (e()) {
            d(z);
        } else {
            e(z);
        }
    }

    public boolean f() {
        return this.q.i();
    }

    public void g() {
        c(true);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt != this.q && childAt != this.ah && (childAt instanceof FloatingActionButton)) {
                arrayList.add((FloatingActionButton) childAt);
            }
            i = i2 + 1;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b((FloatingActionButton) it.next());
        }
    }

    public void g(boolean z) {
        if (f()) {
            k(z);
        }
    }

    public int getAnimationDelayPerItem() {
        return this.U;
    }

    public AnimatorSet getIconToggleAnimatorSet() {
        return this.o;
    }

    public int getMenuButtonColorNormal() {
        return this.Q;
    }

    public int getMenuButtonColorPressed() {
        return this.R;
    }

    public int getMenuButtonColorRipple() {
        return this.S;
    }

    public String getMenuButtonLabelText() {
        return this.as;
    }

    public ImageView getMenuIconView() {
        return this.ah;
    }

    public void h(final boolean z) {
        if (f() || this.ak) {
            return;
        }
        this.ak = true;
        if (!a()) {
            j(z);
        } else {
            c(z);
            this.x.postDelayed(new Runnable() { // from class: com.github.clans.fab.FloatingActionMenuLR.2
                @Override // java.lang.Runnable
                public void run() {
                    FloatingActionMenuLR.this.j(z);
                }
            }, this.U * this.u);
        }
    }

    public void i(boolean z) {
        if (f()) {
            g(z);
        } else {
            h(z);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        bringChildToFront(this.q);
        bringChildToFront(this.ah);
        this.u = getChildCount();
        l();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        int measuredWidth2;
        int paddingRight = this.aq == 0 ? ((i3 - i) - (this.r / 2)) - getPaddingRight() : (this.r / 2) + getPaddingLeft();
        boolean z2 = this.am == 2;
        int measuredHeight = (i4 / 2) - (this.q.getMeasuredHeight() / 2);
        int measuredWidth3 = z2 ? (i3 - this.q.getMeasuredWidth()) - getPaddingRight() : getPaddingLeft();
        this.q.layout(measuredWidth3, measuredHeight, this.q.getMeasuredWidth() + measuredWidth3, this.q.getMeasuredHeight() + measuredHeight);
        int iA = x.a(54.0f);
        int iA2 = x.a(54.0f);
        int paddingRight2 = z2 ? (i3 - iA) - getPaddingRight() : paddingRight - (iA / 2);
        this.ah.layout(paddingRight2, measuredHeight, iA + paddingRight2, iA2 + measuredHeight);
        if (z2) {
            int measuredHeight2 = this.q.getMeasuredHeight() + measuredHeight + this.p;
        }
        int measuredWidth4 = z2 ? measuredWidth3 + this.q.getMeasuredWidth() + this.p : 0;
        int i5 = this.u - 1;
        int i6 = measuredWidth4;
        while (i5 >= 0) {
            View childAt = getChildAt(i5);
            if (childAt == this.ah) {
                measuredWidth2 = i6;
            } else {
                FloatingActionButton floatingActionButton = (FloatingActionButton) childAt;
                if (floatingActionButton.getVisibility() == 8) {
                    measuredWidth2 = i6;
                } else {
                    int measuredWidth5 = z2 ? (i6 - floatingActionButton.getMeasuredWidth()) - this.p : this.p + i6;
                    if (floatingActionButton.j) {
                        measuredWidth = z2 ? this.p + 0 : (i3 - floatingActionButton.getMeasuredWidth()) - this.p;
                    } else {
                        measuredWidth = measuredWidth5;
                    }
                    if (floatingActionButton != this.q) {
                        floatingActionButton.layout(measuredWidth, measuredHeight, floatingActionButton.getMeasuredWidth() + measuredWidth, floatingActionButton.getMeasuredHeight() + measuredHeight);
                        if (!this.w) {
                            floatingActionButton.b(false);
                        }
                    }
                    View view = (View) floatingActionButton.getTag(R.id.fab_label);
                    if (view != null) {
                        int measuredWidth6 = (this.at ? this.r / 2 : floatingActionButton.getMeasuredWidth() / 2) + this.s;
                        int i7 = this.aq == 0 ? paddingRight - measuredWidth6 : measuredWidth6 + paddingRight;
                        if (this.aq == 0) {
                            int measuredWidth7 = i7 - view.getMeasuredWidth();
                        } else {
                            int measuredWidth8 = i7 + view.getMeasuredWidth();
                        }
                        int measuredWidth9 = (((measuredWidth + measuredWidth) + floatingActionButton.getMeasuredWidth()) / 2) - (view.getMeasuredWidth() / 2);
                        int measuredWidth10 = view.getMeasuredWidth() + measuredWidth9;
                        int measuredHeight3 = floatingActionButton.getMeasuredHeight() + measuredHeight + this.s;
                        view.layout(measuredWidth9, measuredHeight3, measuredWidth10, view.getMeasuredHeight() + measuredHeight3);
                        if (!this.w) {
                            view.setVisibility(4);
                        }
                    }
                    if (floatingActionButton.j) {
                        measuredWidth2 = i6;
                    } else {
                        measuredWidth2 = z2 ? measuredWidth - this.p : childAt.getMeasuredWidth() + measuredWidth + this.p;
                        if (z2) {
                            int i8 = measuredHeight - this.p;
                        } else {
                            int measuredHeight4 = childAt.getMeasuredHeight() + measuredHeight + this.p;
                        }
                    }
                }
            }
            i5--;
            i6 = measuredWidth2;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMax;
        this.r = 0;
        int i3 = 0;
        measureChildWithMargins(this.ah, i, 0, i2, 0);
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= this.u) {
                break;
            }
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8 && childAt != this.ah) {
                measureChildWithMargins(childAt, i, 0, i2, 0);
                this.r = Math.max(this.r, childAt.getMeasuredWidth());
            }
            i4 = i5 + 1;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.u) {
            View childAt2 = getChildAt(i6);
            if (childAt2.getVisibility() == 8) {
                iMax = i3;
            } else if (childAt2 == this.ah) {
                iMax = i3;
            } else {
                int measuredWidth = 0 + childAt2.getMeasuredWidth();
                int measuredHeight = i7 + childAt2.getMeasuredHeight();
                Label label = (Label) childAt2.getTag(R.id.fab_label);
                if (label != null) {
                    int measuredWidth2 = (this.r - childAt2.getMeasuredWidth()) / (this.at ? 1 : 2);
                    measureChildWithMargins(label, i, childAt2.getMeasuredWidth() + label.a() + this.s + measuredWidth2, i2, 0);
                    iMax = Math.max(i3, label.getMeasuredWidth() + measuredWidth + measuredWidth2);
                    i7 = measuredHeight;
                } else {
                    iMax = i3;
                    i7 = measuredHeight;
                }
            }
            i6++;
            i3 = iMax;
        }
        int iMax2 = Math.max(this.r, this.s + i3) + getPaddingLeft() + getPaddingRight();
        int iB = b((this.p * (this.u - 1)) + getPaddingTop() + getPaddingBottom() + i7);
        if (getLayoutParams().width == -1) {
            iMax2 = getDefaultSize(getSuggestedMinimumWidth(), i);
        }
        if (getLayoutParams().height == -1) {
            iB = getDefaultSize(getSuggestedMinimumHeight(), i2);
        }
        setMeasuredDimension(iMax2, iB);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.al ? this.l.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public void setAnimated(boolean z) {
        this.aa = z;
        this.m.setDuration(z ? 200L : 0L);
        this.n.setDuration(z ? 200L : 0L);
    }

    public void setAnimationDelayPerItem(int i) {
        this.U = i;
    }

    public void setClosedOnTouchOutside(boolean z) {
        this.al = z;
    }

    public void setIconAnimated(boolean z) {
        this.ag = z;
    }

    public void setIconAnimationCloseInterpolator(Interpolator interpolator) {
        this.n.setInterpolator(interpolator);
    }

    public void setIconAnimationInterpolator(Interpolator interpolator) {
        this.m.setInterpolator(interpolator);
        this.n.setInterpolator(interpolator);
    }

    public void setIconAnimationOpenInterpolator(Interpolator interpolator) {
        this.m.setInterpolator(interpolator);
    }

    public void setIconToggleAnimatorSet(AnimatorSet animatorSet) {
        this.o = animatorSet;
    }

    public void setMenuButtonColorNormal(int i) {
        this.Q = i;
        this.q.setColorNormal(i);
    }

    public void setMenuButtonColorNormalResId(int i) {
        this.Q = ContextCompat.getColor(getContext(), i);
        this.q.setColorNormalResId(i);
    }

    public void setMenuButtonColorPressed(int i) {
        this.R = i;
        this.q.setColorPressed(i);
    }

    public void setMenuButtonColorPressedResId(int i) {
        this.R = ContextCompat.getColor(getContext(), i);
        this.q.setColorPressedResId(i);
    }

    public void setMenuButtonColorRipple(int i) {
        this.S = i;
        this.q.setColorRipple(i);
    }

    public void setMenuButtonColorRippleResId(int i) {
        this.S = ContextCompat.getColor(getContext(), i);
        this.q.setColorRippleResId(i);
    }

    public void setMenuButtonHideAnimation(Animation animation) {
        this.aj = animation;
        this.q.setHideAnimation(animation);
    }

    public void setMenuButtonLabelText(String str) {
        this.q.setLabelText(str);
    }

    public void setMenuButtonShowAnimation(Animation animation) {
        this.ai = animation;
        this.q.setShowAnimation(animation);
    }

    public void setOnMenuButtonClickListener(View.OnClickListener onClickListener) {
        this.q.setOnClickListener(onClickListener);
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public void setOpenDirection(int i) {
        this.am = i;
    }

    @Override // com.github.clans.fab.AbsFloatingActionMenu
    public void setOpenLabel(int i) {
        this.aq = i;
    }
}
