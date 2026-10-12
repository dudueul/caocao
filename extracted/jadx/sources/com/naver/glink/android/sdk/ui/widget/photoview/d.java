package com.naver.glink.android.sdk.ui.widget.photoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: PhotoViewAttacher.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, com.naver.glink.android.sdk.ui.widget.photoview.a.e, com.naver.glink.android.sdk.ui.widget.photoview.c {
    static final int f = -1;
    static final int g = 0;
    static final int h = 1;
    static final int i = 2;
    private InterfaceC0065d A;
    private f B;
    private View.OnLongClickListener C;
    private e D;
    private int E;
    private int F;
    private int G;
    private int H;
    private b I;
    private int J;
    private boolean K;
    private ImageView.ScaleType L;
    int j;
    private final Matrix m;
    private final Matrix n;
    private final Matrix o;
    private final RectF p;
    private final float[] q;
    private float r;
    private float s;
    private float t;
    private boolean u;
    private boolean v;
    private WeakReference<ImageView> w;
    private GestureDetector x;
    private com.naver.glink.android.sdk.ui.widget.photoview.a.d y;
    private c z;
    static final Interpolator e = new AccelerateDecelerateInterpolator();
    private static final String k = "PhotoViewAttacher";
    private static final boolean l = Log.isLoggable(k, 3);

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.widget.photoview.d$2, reason: invalid class name */
    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                a[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    private class a implements Runnable {
        private final float b;
        private final float c;
        private final long d = System.currentTimeMillis();
        private final float e;
        private final float f;

        public a(float f, float f2, float f3, float f4) {
            this.b = f3;
            this.c = f4;
            this.e = f;
            this.f = f2;
        }

        private float a() {
            return d.e.getInterpolation(Math.min(1.0f, ((System.currentTimeMillis() - this.d) * 1.0f) / d.this.j));
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageViewC = d.this.c();
            if (imageViewC == null) {
                return;
            }
            float fA = a();
            d.this.a((this.e + ((this.f - this.e) * fA)) / d.this.getScale(), this.b, this.c);
            if (fA < 1.0f) {
                com.naver.glink.android.sdk.ui.widget.photoview.a.a(imageViewC, this);
            }
        }
    }

    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    private class b implements Runnable {
        private final com.naver.glink.android.sdk.ui.widget.photoview.c.d b;
        private int c;
        private int d;

        public b(Context context) {
            this.b = com.naver.glink.android.sdk.ui.widget.photoview.c.d.a(context);
        }

        public void a() {
            if (d.l) {
                com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(d.k, "Cancel Fling");
            }
            this.b.a(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            int iRound;
            int i5;
            int iRound2;
            int i6;
            RectF displayRect = d.this.getDisplayRect();
            if (displayRect == null) {
                return;
            }
            int iRound3 = Math.round(-displayRect.left);
            if (i < displayRect.width()) {
                iRound = Math.round(displayRect.width() - i);
                i5 = 0;
            } else {
                iRound = iRound3;
                i5 = iRound3;
            }
            int iRound4 = Math.round(-displayRect.top);
            if (i2 < displayRect.height()) {
                iRound2 = Math.round(displayRect.height() - i2);
                i6 = 0;
            } else {
                iRound2 = iRound4;
                i6 = iRound4;
            }
            this.c = iRound3;
            this.d = iRound4;
            if (d.l) {
                com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(d.k, "fling. StartX:" + iRound3 + " StartY:" + iRound4 + " MaxX:" + iRound + " MaxY:" + iRound2);
            }
            if (iRound3 == iRound && iRound4 == iRound2) {
                return;
            }
            this.b.a(iRound3, iRound4, i3, i4, i5, iRound, i6, iRound2, 0, 0);
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageViewC;
            if (this.b.b() || (imageViewC = d.this.c()) == null || !this.b.a()) {
                return;
            }
            int iC = this.b.c();
            int iD = this.b.d();
            if (d.l) {
                com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(d.k, "fling run(). CurrentX:" + this.c + " CurrentY:" + this.d + " NewX:" + iC + " NewY:" + iD);
            }
            d.this.o.postTranslate(this.c - iC, this.d - iD);
            d.this.c(d.this.e());
            this.c = iC;
            this.d = iD;
            com.naver.glink.android.sdk.ui.widget.photoview.a.a(imageViewC, this);
        }
    }

    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    public interface c {
        void a(RectF rectF);
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.widget.photoview.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    public interface InterfaceC0065d {
        void a(View view, float f, float f2);
    }

    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    public interface e {
        void a(float f, float f2, float f3);
    }

    /* JADX INFO: compiled from: PhotoViewAttacher.java */
    public interface f {
        void a(View view, float f, float f2);
    }

    public d(ImageView imageView) {
        this(imageView, true);
    }

    public d(ImageView imageView, boolean z) {
        this.m = new Matrix();
        this.n = new Matrix();
        this.o = new Matrix();
        this.p = new RectF();
        this.q = new float[9];
        this.j = 200;
        this.r = 1.0f;
        this.s = 1.75f;
        this.t = 3.0f;
        this.u = true;
        this.v = false;
        this.J = 2;
        this.L = ImageView.ScaleType.FIT_CENTER;
        this.w = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        b(imageView);
        if (imageView.isInEditMode()) {
            return;
        }
        this.y = com.naver.glink.android.sdk.ui.widget.photoview.a.f.a(imageView.getContext(), this);
        this.x = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.naver.glink.android.sdk.ui.widget.photoview.d.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                if (d.this.C != null) {
                    d.this.C.onLongClick(d.this.c());
                }
            }
        });
        this.x.setOnDoubleTapListener(new com.naver.glink.android.sdk.ui.widget.photoview.b(this));
        setZoomable(z);
    }

    private float a(Matrix matrix, int i2) {
        matrix.getValues(this.q);
        return this.q[i2];
    }

    private void a(Drawable drawable) {
        ImageView imageViewC = c();
        if (imageViewC == null || drawable == null) {
            return;
        }
        float fC = c(imageViewC);
        float fD = d(imageViewC);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.m.reset();
        float f2 = fC / intrinsicWidth;
        float f3 = fD / intrinsicHeight;
        if (this.L != ImageView.ScaleType.CENTER) {
            if (this.L != ImageView.ScaleType.CENTER_CROP) {
                if (this.L != ImageView.ScaleType.CENTER_INSIDE) {
                    RectF rectF = new RectF(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
                    RectF rectF2 = new RectF(0.0f, 0.0f, fC, fD);
                    switch (AnonymousClass2.a[this.L.ordinal()]) {
                        case 2:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                            break;
                        case 3:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                            break;
                        case 4:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                            break;
                        case 5:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                            break;
                    }
                } else {
                    float fMin = Math.min(1.0f, Math.min(f2, f3));
                    this.m.postScale(fMin, fMin);
                    this.m.postTranslate((fC - (intrinsicWidth * fMin)) / 2.0f, (fD - (intrinsicHeight * fMin)) / 2.0f);
                }
            } else {
                float fMax = Math.max(f2, f3);
                this.m.postScale(fMax, fMax);
                this.m.postTranslate((fC - (intrinsicWidth * fMax)) / 2.0f, (fD - (intrinsicHeight * fMax)) / 2.0f);
            }
        } else {
            this.m.postTranslate((fC - intrinsicWidth) / 2.0f, (fD - intrinsicHeight) / 2.0f);
        }
        k();
    }

    private static boolean a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (AnonymousClass2.a[scaleType.ordinal()]) {
            case 1:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private RectF b(Matrix matrix) {
        Drawable drawable;
        ImageView imageViewC = c();
        if (imageViewC == null || (drawable = imageViewC.getDrawable()) == null) {
            return null;
        }
        this.p.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(this.p);
        return this.p;
    }

    private static void b(float f2, float f3, float f4) {
        if (f2 >= f3) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        }
        if (f3 >= f4) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private static void b(ImageView imageView) {
        if (imageView == null || (imageView instanceof com.naver.glink.android.sdk.ui.widget.photoview.c) || ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            return;
        }
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
    }

    private int c(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Matrix matrix) {
        RectF rectFB;
        ImageView imageViewC = c();
        if (imageViewC != null) {
            i();
            imageViewC.setImageMatrix(matrix);
            if (this.z == null || (rectFB = b(matrix)) == null) {
                return;
            }
            this.z.a(rectFB);
        }
    }

    private int d(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void g() {
        if (this.I != null) {
            this.I.a();
            this.I = null;
        }
    }

    private void h() {
        if (j()) {
            c(e());
        }
    }

    private void i() {
        ImageView imageViewC = c();
        if (imageViewC != null && !(imageViewC instanceof com.naver.glink.android.sdk.ui.widget.photoview.c) && !ImageView.ScaleType.MATRIX.equals(imageViewC.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean j() {
        RectF rectFB;
        float f2;
        float f3 = 0.0f;
        ImageView imageViewC = c();
        if (imageViewC != null && (rectFB = b(e())) != null) {
            float fHeight = rectFB.height();
            float fWidth = rectFB.width();
            int iD = d(imageViewC);
            if (fHeight <= iD) {
                switch (AnonymousClass2.a[this.L.ordinal()]) {
                    case 2:
                        f2 = -rectFB.top;
                        break;
                    case 3:
                        f2 = (iD - fHeight) - rectFB.top;
                        break;
                    default:
                        f2 = ((iD - fHeight) / 2.0f) - rectFB.top;
                        break;
                }
            } else if (rectFB.top > 0.0f) {
                f2 = -rectFB.top;
            } else {
                f2 = rectFB.bottom < ((float) iD) ? iD - rectFB.bottom : 0.0f;
            }
            int iC = c(imageViewC);
            if (fWidth <= iC) {
                switch (AnonymousClass2.a[this.L.ordinal()]) {
                    case 2:
                        f3 = -rectFB.left;
                        break;
                    case 3:
                        f3 = (iC - fWidth) - rectFB.left;
                        break;
                    default:
                        f3 = ((iC - fWidth) / 2.0f) - rectFB.left;
                        break;
                }
                this.J = 2;
            } else if (rectFB.left > 0.0f) {
                this.J = 0;
                f3 = -rectFB.left;
            } else if (rectFB.right < iC) {
                f3 = iC - rectFB.right;
                this.J = 1;
            } else {
                this.J = -1;
            }
            this.o.postTranslate(f3, f2);
            return true;
        }
        return false;
    }

    private void k() {
        this.o.reset();
        c(e());
        j();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.e
    public void a(float f2, float f3) {
        if (this.y.a()) {
            return;
        }
        if (l) {
            com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(k, String.format("onDrag: dx: %.2f. dy: %.2f", Float.valueOf(f2), Float.valueOf(f3)));
        }
        ImageView imageViewC = c();
        this.o.postTranslate(f2, f3);
        h();
        ViewParent parent = imageViewC.getParent();
        if (!this.u || this.y.a() || this.v) {
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        } else if ((this.J == 2 || ((this.J == 0 && f2 >= 1.0f) || (this.J == 1 && f2 <= -1.0f))) && parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.e
    public void a(float f2, float f3, float f4) {
        if (l) {
            com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(k, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        if (getScale() < this.t || f2 < 1.0f) {
            if (this.D != null) {
                this.D.a(f2, f3, f4);
            }
            this.o.postScale(f2, f2, f3, f4);
            h();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.e
    public void a(float f2, float f3, float f4, float f5) {
        if (l) {
            com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().b(k, "onFling. sX: " + f2 + " sY: " + f3 + " Vx: " + f4 + " Vy: " + f5);
        }
        ImageView imageViewC = c();
        this.I = new b(imageViewC.getContext());
        this.I.a(c(imageViewC), d(imageViewC), (int) f4, (int) f5);
        imageViewC.post(this.I);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public boolean a() {
        return this.K;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public boolean a(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        ImageView imageViewC = c();
        if (imageViewC == null || imageViewC.getDrawable() == null) {
            return false;
        }
        this.o.set(matrix);
        c(e());
        j();
        return true;
    }

    public void b() {
        if (this.w == null) {
            return;
        }
        ImageView imageView = this.w.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            imageView.setOnTouchListener(null);
            g();
        }
        if (this.x != null) {
            this.x.setOnDoubleTapListener(null);
        }
        this.z = null;
        this.A = null;
        this.B = null;
        this.w = null;
    }

    public ImageView c() {
        ImageView imageView = this.w != null ? this.w.get() : null;
        if (imageView == null) {
            b();
            com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().c(k, "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    public void d() {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            if (!this.K) {
                k();
            } else {
                b(imageViewC);
                a(imageViewC.getDrawable());
            }
        }
    }

    public Matrix e() {
        this.n.set(this.m);
        this.n.postConcat(this.o);
        return this.n;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public Matrix getDisplayMatrix() {
        return new Matrix(e());
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public RectF getDisplayRect() {
        j();
        return b(e());
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public com.naver.glink.android.sdk.ui.widget.photoview.c getIPhotoViewImplementation() {
        return this;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMaximumScale() {
        return this.t;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMediumScale() {
        return this.s;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMinimumScale() {
        return this.r;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public InterfaceC0065d getOnPhotoTapListener() {
        return this.A;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public f getOnViewTapListener() {
        return this.B;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getScale() {
        return (float) Math.sqrt(((float) Math.pow(a(this.o, 0), 2.0d)) + ((float) Math.pow(a(this.o, 3), 2.0d)));
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public ImageView.ScaleType getScaleType() {
        return this.L;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageViewC = c();
        if (imageViewC == null) {
            return null;
        }
        return imageViewC.getDrawingCache();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            if (!this.K) {
                a(imageViewC.getDrawable());
                return;
            }
            int top = imageViewC.getTop();
            int right = imageViewC.getRight();
            int bottom = imageViewC.getBottom();
            int left = imageViewC.getLeft();
            if (top == this.E && bottom == this.G && left == this.H && right == this.F) {
                return;
            }
            a(imageViewC.getDrawable());
            this.E = top;
            this.F = right;
            this.G = bottom;
            this.H = left;
        }
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean zC;
        RectF displayRect;
        boolean z = false;
        if (!this.K || !a((ImageView) view)) {
            return false;
        }
        ViewParent parent = view.getParent();
        switch (motionEvent.getAction()) {
            case 0:
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                } else {
                    com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().c(k, "onTouch getParent() returned null");
                }
                g();
                zC = false;
                break;
            case 1:
            case 3:
                if (getScale() < this.r && (displayRect = getDisplayRect()) != null) {
                    view.post(new a(getScale(), this.r, displayRect.centerX(), displayRect.centerY()));
                    zC = true;
                } else {
                    zC = false;
                }
                break;
            case 2:
            default:
                zC = false;
                break;
        }
        if (this.y != null) {
            boolean zA = this.y.a();
            boolean zB = this.y.b();
            zC = this.y.c(motionEvent);
            boolean z2 = (zA || this.y.a()) ? false : true;
            boolean z3 = (zB || this.y.b()) ? false : true;
            if (z2 && z3) {
                z = true;
            }
            this.v = z;
        }
        if (this.x == null || !this.x.onTouchEvent(motionEvent)) {
            return zC;
        }
        return true;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.u = z;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMaxScale(float f2) {
        setMaximumScale(f2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMaximumScale(float f2) {
        b(this.r, this.s, f2);
        this.t = f2;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMediumScale(float f2) {
        b(this.r, f2, this.t);
        this.s = f2;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMidScale(float f2) {
        setMediumScale(f2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMinScale(float f2) {
        setMinimumScale(f2);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMinimumScale(float f2) {
        b(f2, this.s, this.t);
        this.r = f2;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.x.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.x.setOnDoubleTapListener(new com.naver.glink.android.sdk.ui.widget.photoview.b(this));
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.C = onLongClickListener;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnMatrixChangeListener(c cVar) {
        this.z = cVar;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnPhotoTapListener(InterfaceC0065d interfaceC0065d) {
        this.A = interfaceC0065d;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnScaleChangeListener(e eVar) {
        this.D = eVar;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnViewTapListener(f fVar) {
        this.B = fVar;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setPhotoViewRotation(float f2) {
        this.o.setRotate(f2 % 360.0f);
        h();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setRotationBy(float f2) {
        this.o.postRotate(f2 % 360.0f);
        h();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setRotationTo(float f2) {
        this.o.setRotate(f2 % 360.0f);
        h();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f2) {
        setScale(f2, false);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f2, float f3, float f4, boolean z) {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            if (f2 < this.r || f2 > this.t) {
                com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().c(k, "Scale must be within the range of minScale and maxScale");
            } else if (z) {
                imageViewC.post(new a(getScale(), f2, f3, f4));
            } else {
                this.o.setScale(f2, f2, f3, f4);
                h();
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f2, boolean z) {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            setScale(f2, imageViewC.getRight() / 2, imageViewC.getBottom() / 2, z);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScaleLevels(float f2, float f3, float f4) {
        b(f2, f3, f4);
        this.r = f2;
        this.s = f3;
        this.t = f4;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!a(scaleType) || scaleType == this.L) {
            return;
        }
        this.L = scaleType;
        d();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setZoomTransitionDuration(int i2) {
        if (i2 < 0) {
            i2 = 200;
        }
        this.j = i2;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setZoomable(boolean z) {
        this.K = z;
        d();
    }
}
