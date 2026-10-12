package com.naver.glink.android.sdk.ui.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import com.google.android.gms.common.util.CrashUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HorizontalListView extends AdapterView<ListAdapter> {
    private static final int e = -1;
    private static final int f = 0;
    private static final float g = 30.0f;
    private static final float h = 0.009f;
    private static final String i = "BUNDLE_ID_CURRENT_X";
    private static final String j = "BUNDLE_ID_PARENT_STATE";
    private boolean A;
    private OnScrollStateChangedListener B;
    private OnScrollStateChangedListener.ScrollState C;
    private EdgeEffectCompat D;
    private EdgeEffectCompat E;
    private int F;
    private boolean G;
    private boolean H;
    private View.OnClickListener I;
    private d J;
    private DataSetObserver K;
    private Runnable L;
    protected Scroller a;
    protected ListAdapter b;
    protected int c;
    protected int d;
    private final a k;
    private GestureDetector l;
    private int m;
    private List<Queue<View>> n;
    private boolean o;
    private Rect p;
    private View q;
    private int r;
    private Drawable s;
    private Integer t;
    private int u;
    private int v;
    private int w;
    private int x;
    private e y;
    private int z;

    public interface OnScrollStateChangedListener {

        public enum ScrollState {
            SCROLL_STATE_IDLE,
            SCROLL_STATE_TOUCH_SCROLL,
            SCROLL_STATE_FLING
        }

        void a(ScrollState scrollState);
    }

    private class a extends GestureDetector.SimpleOnGestureListener {
        private a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (HorizontalListView.this.J != null) {
                HorizontalListView.this.J.a();
            }
            return HorizontalListView.this.a(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return HorizontalListView.this.a(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            HorizontalListView.this.f();
            int iC = HorizontalListView.this.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iC < 0 || HorizontalListView.this.G) {
                return;
            }
            View childAt = HorizontalListView.this.getChildAt(iC);
            AdapterView.OnItemLongClickListener onItemLongClickListener = HorizontalListView.this.getOnItemLongClickListener();
            if (onItemLongClickListener != null) {
                int i = HorizontalListView.this.v + iC;
                if (onItemLongClickListener.onItemLongClick(HorizontalListView.this, childAt, i, HorizontalListView.this.b.getItemId(i))) {
                    HorizontalListView.this.performHapticFeedback(0);
                }
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            HorizontalListView.this.a((Boolean) true);
            HorizontalListView.this.setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_TOUCH_SCROLL);
            HorizontalListView.this.f();
            HorizontalListView.this.d += (int) f;
            HorizontalListView.this.j(Math.round(f));
            HorizontalListView.this.requestLayout();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            HorizontalListView.this.f();
            AdapterView.OnItemClickListener onItemClickListener = HorizontalListView.this.getOnItemClickListener();
            int iC = HorizontalListView.this.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iC >= 0 && !HorizontalListView.this.G) {
                View childAt = HorizontalListView.this.getChildAt(iC);
                int i = HorizontalListView.this.v + iC;
                if (childAt.isFocusable()) {
                    return true;
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(HorizontalListView.this, childAt, i, HorizontalListView.this.b.getItemId(i));
                    return true;
                }
            }
            if (HorizontalListView.this.I != null && !HorizontalListView.this.G) {
                HorizontalListView.this.I.onClick(HorizontalListView.this);
            }
            return false;
        }
    }

    @TargetApi(11)
    private static final class b {
        static {
            if (Build.VERSION.SDK_INT < 11) {
                throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
            }
        }

        private b() {
        }

        public static void a(Scroller scroller, float f) {
            if (scroller != null) {
                scroller.setFriction(f);
            }
        }
    }

    @TargetApi(14)
    private static final class c {
        static {
            if (Build.VERSION.SDK_INT < 14) {
                throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
            }
        }

        private c() {
        }

        public static float a(Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }

    public interface d {
        void a();
    }

    public interface e {
        void a();
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Scroller(getContext());
        this.k = new a();
        this.n = new ArrayList();
        this.o = false;
        this.p = new Rect();
        this.q = null;
        this.r = 0;
        this.s = null;
        this.t = null;
        this.u = Integer.MAX_VALUE;
        this.y = null;
        this.z = 0;
        this.A = false;
        this.B = null;
        this.C = OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE;
        this.G = false;
        this.H = false;
        this.K = new DataSetObserver() { // from class: com.naver.glink.android.sdk.ui.widget.HorizontalListView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                HorizontalListView.this.o = true;
                HorizontalListView.this.A = false;
                HorizontalListView.this.f();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                HorizontalListView.this.A = false;
                HorizontalListView.this.f();
                HorizontalListView.this.c();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }
        };
        this.L = new Runnable() { // from class: com.naver.glink.android.sdk.ui.widget.HorizontalListView.3
            @Override // java.lang.Runnable
            public void run() {
                HorizontalListView.this.requestLayout();
            }
        };
        this.D = new EdgeEffectCompat(context);
        this.E = new EdgeEffectCompat(context);
        this.l = new GestureDetector(context, this.k);
        a();
        b();
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 11) {
            b.a(this.a, h);
        }
    }

    private void a() {
        setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.widget.HorizontalListView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return HorizontalListView.this.l.onTouchEvent(motionEvent);
            }
        });
    }

    private void a(int i2, int i3) {
        while (i2 + i3 + this.r < getWidth() && this.w + 1 < this.b.getCount()) {
            this.w++;
            if (this.v < 0) {
                this.v = this.w;
            }
            View view = this.b.getView(this.w, c(this.w), this);
            a(view, -1);
            i2 += (this.w == 0 ? 0 : this.r) + view.getMeasuredWidth();
            h();
        }
    }

    private void a(int i2, View view) {
        int itemViewType = this.b.getItemViewType(i2);
        if (d(itemViewType)) {
            this.n.get(itemViewType).offer(view);
        }
    }

    private void a(Canvas canvas) {
        if (this.D != null && !this.D.isFinished() && i()) {
            int iSave = canvas.save();
            int height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((-height) + getPaddingBottom(), 0.0f);
            this.D.setSize(getRenderHeight(), getRenderWidth());
            if (this.D.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(iSave);
            return;
        }
        if (this.E == null || this.E.isFinished() || !i()) {
            return;
        }
        int iSave2 = canvas.save();
        int width = getWidth();
        canvas.rotate(90.0f, 0.0f, 0.0f);
        canvas.translate(getPaddingTop(), -width);
        this.E.setSize(getRenderHeight(), getRenderWidth());
        if (this.E.draw(canvas)) {
            invalidate();
        }
        canvas.restoreToCount(iSave2);
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.s != null) {
            this.s.setBounds(rect);
            this.s.draw(canvas);
        }
    }

    private void a(View view) {
        ViewGroup.LayoutParams layoutParamsB = b(view);
        view.measure(layoutParamsB.width > 0 ? View.MeasureSpec.makeMeasureSpec(layoutParamsB.width, CrashUtils.ErrorDialogData.SUPPRESSED) : View.MeasureSpec.makeMeasureSpec(0, 0), ViewGroup.getChildMeasureSpec(this.F, getPaddingTop() + getPaddingBottom(), layoutParamsB.height));
    }

    private void a(View view, int i2) {
        addViewInLayout(view, i2, b(view), true);
        a(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Boolean bool) {
    }

    private ViewGroup.LayoutParams b(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-2, -1) : layoutParams;
    }

    private void b() {
        this.v = -1;
        this.w = -1;
        this.m = 0;
        this.c = 0;
        this.d = 0;
        this.u = Integer.MAX_VALUE;
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        this.a.setFriction(0.055f);
    }

    private void b(int i2) {
        this.n.clear();
        for (int i3 = 0; i3 < i2; i3++) {
            this.n.add(new LinkedList());
        }
    }

    private void b(int i2, int i3) {
        while ((i2 + i3) - this.r > 0 && this.v >= 1) {
            this.v--;
            View view = this.b.getView(this.v, c(this.v), this);
            a(view, 0);
            i2 -= this.v == 0 ? view.getMeasuredWidth() : this.r + view.getMeasuredWidth();
            this.m -= i2 + i3 == 0 ? view.getMeasuredWidth() : this.r + view.getMeasuredWidth();
        }
    }

    private void b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.p;
        this.p.top = getPaddingTop();
        this.p.bottom = this.p.top + getRenderHeight();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i2 != childCount - 1 || !i(this.w)) {
                View childAt = getChildAt(i2);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.r;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                a(canvas, rect);
                if (i2 == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    a(canvas, rect);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).getHitRect(this.p);
            if (this.p.contains(i2, i3)) {
                return i4;
            }
        }
        return -1;
    }

    private View c(int i2) {
        int itemViewType = this.b.getItemViewType(i2);
        if (d(itemViewType)) {
            return this.n.get(itemViewType).poll();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        b();
        removeAllViewsInLayout();
        requestLayout();
    }

    private float d() {
        return Build.VERSION.SDK_INT >= 14 ? c.a(this.a) : g;
    }

    private boolean d(int i2) {
        return i2 < this.n.size();
    }

    private void e(int i2) {
        View rightmostChild = getRightmostChild();
        a(rightmostChild != null ? rightmostChild.getRight() : 0, i2);
        View leftmostChild = getLeftmostChild();
        b(leftmostChild != null ? leftmostChild.getLeft() : 0, i2);
    }

    private boolean e() {
        View rightmostChild;
        if (!i(this.w) || (rightmostChild = getRightmostChild()) == null) {
            return false;
        }
        int i2 = this.u;
        this.u = ((rightmostChild.getRight() - getPaddingLeft()) + this.c) - getRenderWidth();
        if (this.u < 0) {
            this.u = 0;
        }
        return this.u != i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.q != null) {
            this.q.setPressed(false);
            refreshDrawableState();
            this.q = null;
        }
    }

    private void f(int i2) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i2 <= 0) {
            this.m = (i(this.v) ? leftmostChild.getMeasuredWidth() : this.r + leftmostChild.getMeasuredWidth()) + this.m;
            a(this.v, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.v++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i2 >= getWidth()) {
            a(this.w, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.w--;
            rightmostChild = getRightmostChild();
        }
    }

    private void g() {
        if (this.D != null) {
            this.D.onRelease();
        }
        if (this.E != null) {
            this.E.onRelease();
        }
    }

    private void g(int i2) {
        int childCount = getChildCount();
        if (childCount > 0) {
            this.m += i2;
            int measuredWidth = this.m;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + measuredWidth;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                measuredWidth += childAt.getMeasuredWidth() + this.r;
            }
        }
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private View h(int i2) {
        if (i2 < this.v || i2 > this.w) {
            return null;
        }
        return getChildAt(i2 - this.v);
    }

    private void h() {
        if (this.y == null || this.b == null || this.b.getCount() - (this.w + 1) >= this.z || this.A) {
            return;
        }
        this.A = true;
        this.y.a();
    }

    private boolean i() {
        return (this.b == null || this.b.isEmpty() || this.u <= 0) ? false : true;
    }

    private boolean i(int i2) {
        return i2 == this.b.getCount() + (-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2) {
        if (this.D == null || this.E == null) {
            return;
        }
        int i3 = this.c + i2;
        if (this.a == null || this.a.isFinished()) {
            if (i3 < 0) {
                this.D.onPull(Math.abs(i2) / getRenderWidth());
                if (this.E.isFinished()) {
                    return;
                }
                this.E.onRelease();
                return;
            }
            if (i3 > this.u) {
                this.E.onPull(Math.abs(i2) / getRenderWidth());
                if (this.D.isFinished()) {
                    return;
                }
                this.D.onRelease();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentScrollState(OnScrollStateChangedListener.ScrollState scrollState) {
        if (this.C != scrollState && this.B != null) {
            this.B.a(scrollState);
        }
        this.C = scrollState;
    }

    public void a(int i2) {
        this.a.startScroll(this.d, 0, i2 - this.d, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    protected boolean a(MotionEvent motionEvent) {
        int iC;
        this.G = !this.a.isFinished();
        this.a.forceFinished(true);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        f();
        if (!this.G && (iC = c((int) motionEvent.getX(), (int) motionEvent.getY())) >= 0) {
            this.q = getChildAt(iC);
            if (this.q != null && !this.q.isFocusable()) {
                this.q.setPressed(true);
                refreshDrawableState();
            }
        }
        return true;
    }

    protected boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.a.fling(this.d, 0, (int) (-f2), 0, 0, this.u, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        a(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean z) {
    }

    @Override // android.widget.AdapterView
    public ListAdapter getAdapter() {
        return this.b;
    }

    @Override // android.widget.AdapterView
    public int getFirstVisiblePosition() {
        return this.v;
    }

    @Override // android.widget.AdapterView
    public int getLastVisiblePosition() {
        return this.w;
    }

    @Override // android.view.View
    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == 0) {
            return 0.0f;
        }
        if (this.c < horizontalFadingEdgeLength) {
            return this.c / horizontalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.view.View
    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == this.u) {
            return 0.0f;
        }
        if (this.u - this.c < horizontalFadingEdgeLength) {
            return (this.u - this.c) / horizontalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.widget.AdapterView
    public View getSelectedView() {
        return h(this.x);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.b == null) {
            return;
        }
        invalidate();
        if (this.o) {
            int i6 = this.c;
            b();
            removeAllViewsInLayout();
            this.d = i6;
            this.o = false;
        }
        if (this.t != null) {
            this.d = this.t.intValue();
            this.t = null;
        }
        if (this.a.computeScrollOffset()) {
            this.d = this.a.getCurrX();
        }
        if (this.d < 0) {
            this.d = 0;
            if (this.D.isFinished()) {
                this.D.onAbsorb((int) d());
            }
            this.a.forceFinished(true);
            setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        } else if (this.d > this.u) {
            this.d = this.u;
            if (this.E.isFinished()) {
                this.E.onAbsorb((int) d());
            }
            this.a.forceFinished(true);
            setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        }
        int i7 = this.c - this.d;
        f(i7);
        e(i7);
        g(i7);
        this.c = this.d;
        if (e()) {
            onLayout(z, i2, i3, i4, i5);
        } else if (!this.a.isFinished()) {
            ViewCompat.postOnAnimation(this, this.L);
        } else if (this.C == OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING) {
            setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.F = i3;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.t = Integer.valueOf(bundle.getInt(i));
            super.onRestoreInstanceState(bundle.getParcelable(j));
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(j, super.onSaveInstanceState());
        bundle.putInt(i, this.c);
        return bundle;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.a == null || this.a.isFinished()) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
            a((Boolean) false);
            g();
        } else if (motionEvent.getAction() == 3) {
            f();
            g();
            a((Boolean) false);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(this.K);
        }
        if (listAdapter != null) {
            this.A = false;
            this.b = listAdapter;
            this.b.registerDataSetObserver(this.K);
        }
        b(this.b.getViewTypeCount());
        c();
    }

    public void setDivider(Drawable drawable) {
        this.s = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(0);
        }
    }

    public void setDividerWidth(int i2) {
        this.r = i2;
        requestLayout();
        invalidate();
    }

    @Override // android.widget.AdapterView, android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.I = onClickListener;
    }

    public void setOnCustomTouchEvent(d dVar) {
        this.J = dVar;
    }

    public void setOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.B = onScrollStateChangedListener;
    }

    public void setRunningOutOfDataListener(e eVar, int i2) {
        this.y = eVar;
        this.z = i2;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i2) {
        this.x = i2;
    }
}
