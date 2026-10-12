package com.naver.glink.android.sdk.ui.profile.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import com.naver.glink.android.sdk.a.x;
import com.squareup.otto.Bus;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageEditView extends ImageView {
    public static final String a = ImageType.DEFAULT.getType();
    public static final String b = ImageType.ROUND.getType();
    public static final int c = 2000;
    public static final int d = 3001;
    private static final int g = 50;
    private static final int h = 200;
    private static final int i = 1;
    private static final int j = 5;
    private static final int k = 7;
    private static final int l = 9;
    private static final int m = 3;
    private int A;
    private int B;
    private Matrix C;
    private Matrix D;
    private float E;
    private float F;
    private float G;
    private boolean H;
    private boolean I;
    private Rect J;
    private Path K;
    private boolean L;
    private int M;
    private int N;
    private int O;
    private Bitmap P;
    private Canvas Q;
    private boolean R;
    private boolean S;
    private ImageType T;
    private Rect U;
    public int e;
    public int f;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private Paint s;
    private Paint t;
    private Paint u;
    private Paint v;
    private Bitmap w;
    private Bitmap x;
    private int y;
    private int z;

    public enum ImageType {
        DEFAULT(Bus.DEFAULT_IDENTIFIER, 1, 1),
        ROUND("round", 1, 1);

        private int aspectX;
        private int aspectY;
        private String type;

        ImageType(String str, int i, int i2) {
            this.type = str;
            this.aspectX = i;
            this.aspectY = i2;
        }

        public static ImageType findImageType(String str) {
            for (ImageType imageType : values()) {
                if (imageType.getType().equals(str)) {
                    return imageType;
                }
            }
            return DEFAULT;
        }

        public int getAspectX() {
            return this.aspectX;
        }

        public int getAspectY() {
            return this.aspectY;
        }

        public String getType() {
            return this.type;
        }
    }

    private class a extends Animation {
        int a;
        int b;
        int c;
        int d;

        public a() {
            this.a = ImageEditView.this.J.left;
            this.b = ImageEditView.this.J.top;
            this.c = ImageEditView.this.J.right;
            this.d = ImageEditView.this.J.bottom;
            setInterpolator(new DecelerateInterpolator(1.0f));
            setDuration(250L);
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = 1.0f - f;
            ImageEditView.this.J.left = (int) (this.a * f);
            ImageEditView.this.J.top = (int) (this.b * f);
            ImageEditView.this.J.right = (int) (this.c + (this.a * f2));
            ImageEditView.this.J.bottom = (int) ((f2 * this.b) + this.d);
            ImageEditView.this.s.setAlpha((int) (255.0f * f));
            ImageEditView.this.invalidate();
        }
    }

    private class b extends Animation {
        int a;
        int b;
        int c;
        int d;

        public b() {
            this.a = ImageEditView.this.J.left;
            this.b = ImageEditView.this.J.top;
            this.c = ImageEditView.this.J.right;
            this.d = ImageEditView.this.J.bottom;
            setInterpolator(new DecelerateInterpolator(2.0f));
            setDuration(100L);
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = 1.0f - f;
            if (this.a > 0) {
                ImageEditView.this.J.left = (int) (this.a * f2);
            }
            if (this.b > 0) {
                ImageEditView.this.J.top = (int) (this.b * f2);
            }
            int width = ImageEditView.this.getWidth();
            if (this.c < width) {
                ImageEditView.this.J.right = (int) (((width - this.c) * f) + this.c);
            }
            int height = ImageEditView.this.getHeight();
            if (this.d < height) {
                ImageEditView.this.J.bottom = (int) (((height - this.d) * f) + this.d);
            }
            ImageEditView.this.s.setAlpha((int) (f2 * 255.0f));
            ImageEditView.this.invalidate();
        }
    }

    public ImageEditView(Context context) {
        super(context);
        this.n = 2000;
        this.o = 200;
        this.p = 200;
        this.M = 2;
        this.N = 10;
        this.O = 32;
        this.R = false;
        this.S = false;
        this.T = ImageType.DEFAULT;
        h();
    }

    public ImageEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = 2000;
        this.o = 200;
        this.p = 200;
        this.M = 2;
        this.N = 10;
        this.O = 32;
        this.R = false;
        this.S = false;
        this.T = ImageType.DEFAULT;
        h();
    }

    public ImageEditView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.n = 2000;
        this.o = 200;
        this.p = 200;
        this.M = 2;
        this.N = 10;
        this.O = 32;
        this.R = false;
        this.S = false;
        this.T = ImageType.DEFAULT;
        h();
    }

    private void a(int i2, int i3) {
        float f = this.f / i2;
        if (i3 * f < this.e) {
            this.E = f;
            this.F = i2 / this.f;
        } else {
            this.E = this.e / i3;
            this.F = i3 / this.e;
        }
        forceLayout();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (int) (i3 * this.E);
        layoutParams.width = (int) (i2 * this.E);
        requestLayout();
    }

    private void a(int i2, int i3, int i4, int i5) {
        int i6 = i2 < i4 ? i2 : i4;
        int i7 = i3 < i5 ? i3 : i5;
        int iAbs = Math.abs(i4 - i2);
        int iAbs2 = Math.abs(i5 - i3);
        Bitmap mergedBitmap = getMergedBitmap();
        if (mergedBitmap == null) {
            return;
        }
        int width = mergedBitmap.getWidth();
        int height = mergedBitmap.getHeight();
        if (i6 + iAbs > width) {
            iAbs = width - i6;
        }
        if (i7 + iAbs2 > height) {
            iAbs2 = height - i7;
        }
        a(Bitmap.createBitmap(mergedBitmap, i6, i7, iAbs, iAbs2));
        this.L = true;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00ad A[PHI: r0 r3
      0x00ad: PHI (r0v15 int) = (r0v14 int), (r0v18 int) binds: [B:28:0x0062, B:44:0x00a9] A[DONT_GENERATE, DONT_INLINE]
      0x00ad: PHI (r3v4 int) = (r3v3 int), (r3v8 int) binds: [B:28:0x0062, B:44:0x00a9] A[DONT_GENERATE, DONT_INLINE]] */
    private void a(int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = this.J.right + i4;
        int i14 = this.J.left + i2;
        if (i13 - i14 < this.o) {
            if (i2 > 0) {
                i2 = 0;
            }
            if (i4 < 0) {
                i4 = 0;
            }
        }
        int i15 = this.J.bottom + i5;
        int i16 = this.J.top + i3;
        if (i15 - i16 < this.p) {
            i7 = i5 < 0 ? 0 : i5;
            i8 = i3 > 0 ? 0 : i3;
        } else {
            i7 = i5;
            i8 = i3;
        }
        if (i14 < 0) {
            i2 = 0;
            i4 = 0;
            if (i6 == 7) {
                Log.d("imgedit", "move => 왼쪽으로 붙은경우 7방향 크롭은 처리하면 안됨.");
                i8 = 0;
            } else if (i6 == 1) {
                Log.d("imgedit", "move => 왼쪽으로 붙은경우 1방향 크롭은 처리하면 안됨.");
                i7 = 0;
            }
        }
        if (i13 > getWidth()) {
            i4 = 0;
            i2 = 0;
            if (i6 == 9) {
                Log.d("imgedit", "move => 오른쪽으로 붙은경우 9방향 크롭은 처리하면 안됨.");
                i8 = 0;
            } else if (i6 == 3) {
                Log.d("imgedit", "move => 오른쪽으로 붙은경우 9방향 크롭은 처리하면 안됨.");
                i7 = 0;
            }
        }
        if (i15 > getHeight()) {
            i7 = 0;
            i10 = 0;
            if (i6 == 1) {
                i9 = i4;
                i11 = 0;
            } else if (i6 == 3) {
                i9 = 0;
                i11 = i2;
            } else {
                i9 = i4;
                i11 = i2;
            }
        } else {
            i9 = i4;
            i10 = i8;
            i11 = i2;
        }
        if (i16 < 0) {
            i10 = 0;
            i7 = 0;
            if (i6 == 7) {
                i11 = 0;
                i12 = i9;
            } else if (i6 == 9) {
                i12 = 0;
            } else {
                i12 = i9;
            }
        } else {
            i12 = i9;
        }
        Rect rect = this.J;
        rect.left = i11 + rect.left;
        Rect rect2 = this.J;
        rect2.top = i10 + rect2.top;
        Rect rect3 = this.J;
        rect3.right = i12 + rect3.right;
        Rect rect4 = this.J;
        rect4.bottom = i7 + rect4.bottom;
    }

    private void a(Bitmap bitmap) {
        switch (this.T) {
            case DEFAULT:
                setBitmap(bitmap);
                break;
            case ROUND:
                setBitmap(bitmap);
                setRoundEffectedBitmap(bitmap);
                break;
        }
    }

    private void a(Canvas canvas) {
        switch (this.T) {
            case DEFAULT:
                a(canvas, this.T);
                break;
            case ROUND:
                a(canvas, this.T);
                b(canvas);
                break;
        }
    }

    private void a(Canvas canvas, Matrix matrix) {
        if (this.w == null || this.w.isRecycled()) {
            return;
        }
        canvas.concat(matrix);
        canvas.drawBitmap(this.w, 0.0f, 0.0f, (Paint) null);
    }

    private void a(Canvas canvas, ImageType imageType) {
        if (this.n != 3001) {
            return;
        }
        this.P.eraseColor(0);
        this.Q.drawColor(1275068416);
        canvas.drawBitmap(this.P, 0.0f, 0.0f, (Paint) null);
        this.U = new Rect(this.J.left, this.J.top, this.J.right, this.J.bottom);
        canvas.drawRect(this.U, this.s);
        int i2 = this.U.top;
        int i3 = this.U.left;
        int i4 = this.U.right;
        int i5 = this.U.bottom;
        if (!this.I) {
            int i6 = (int) ((this.U.right - this.U.left) / 3.0f);
            int i7 = (int) ((this.U.bottom - this.U.top) / 3.0f);
            int i8 = i2 + i7;
            canvas.drawLine(i3, i8, i4, i8, this.t);
            int i9 = i8 + i7;
            canvas.drawLine(i3, i9, i4, i9, this.t);
            int i10 = i3 + i6;
            canvas.drawLine(i10, i2, i10, i5, this.t);
            int i11 = i10 + i6;
            canvas.drawLine(i11, i2, i11, i5, this.t);
        }
        int i12 = (int) (this.N / 2.0f);
        int i13 = i3 - i12;
        int i14 = i2 - i12;
        int i15 = i5 + i12;
        int i16 = i12 + i4;
        this.K.reset();
        int i17 = this.O;
        this.K.moveTo(i13, i14 + i17);
        this.K.lineTo(i13, i14);
        this.K.lineTo(i13 + i17, i14);
        this.K.moveTo(i13, i15 - i17);
        this.K.lineTo(i13, i15);
        this.K.lineTo(i13 + i17, i15);
        this.K.moveTo(i16 - i17, i14);
        this.K.lineTo(i16, i14);
        this.K.lineTo(i16, i14 + i17);
        this.K.moveTo(i16, i15 - i17);
        this.K.lineTo(i16, i15);
        this.K.lineTo(i16 - i17, i15);
        canvas.drawPath(this.K, this.v);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0128  */
    private void b(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = 7;
        int i11 = i2 - this.A;
        int i12 = i3 - this.B;
        if (i2 >= this.J.right - 50 || i2 <= this.J.left + 50 || i3 <= this.J.top + 50 || i3 >= this.J.bottom - 50) {
            int i13 = ((this.J.bottom - 50) - (this.J.top + 50)) / 2;
            if (this.S) {
                if (i2 < this.J.left + 50) {
                    if (i3 < i13 + this.J.top + 50) {
                        Log.e("imgedit", " touchMoveCrop => 왼쪽 위=7");
                        i4 = 7;
                        i5 = 0;
                        i6 = 0;
                        i7 = i11;
                        i8 = i11;
                    } else {
                        Log.e("imgedit", " touchMoveCrop => 왼쪽 아래=1");
                        i4 = 1;
                        i5 = -i11;
                        i6 = 0;
                        i7 = 0;
                        i8 = i11;
                    }
                } else if (i2 > this.J.right - 50) {
                    Log.i("imgedit", " touchMoveCrop => 오른쪽");
                    if (i3 < i13 + this.J.top + 50) {
                        Log.e("imgedit", " touchMoveCrop => 오른쪽 위=9");
                        i4 = 9;
                        i5 = 0;
                        i6 = i11;
                        i7 = -i11;
                        i8 = 0;
                    } else {
                        Log.e("imgedit", " touchMoveCrop => 오른쪽 아래=3");
                        i4 = 3;
                        i5 = i11;
                        i6 = i11;
                        i7 = 0;
                        i8 = 0;
                    }
                } else {
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                    i7 = 0;
                    i8 = 0;
                }
                int i14 = ((this.J.right - 50) - (this.J.left + 50)) / 2;
                if (i3 < this.J.top + 50) {
                    Log.i("imgedit", " touchMoveCrop => 위쪽");
                    if (i2 < this.J.left + 50 + i14) {
                        Log.e("imgedit", " touchMoveCrop => 위쪽 왼쪽=7");
                        i7 = i12;
                        i9 = i12;
                    } else {
                        Log.e("imgedit", " touchMoveCrop => 위쪽 오른쪽=9");
                        i6 = -i12;
                        i10 = 9;
                        i7 = i12;
                        i9 = i8;
                    }
                } else if (i3 > this.J.bottom - 50) {
                    Log.i("imgedit", " touchMoveCrop => 아래쪽");
                    if (i2 < this.J.left + 50 + i14) {
                        Log.e("imgedit", " touchMoveCrop => 아래쪽 왼쪽=1");
                        i10 = 1;
                        i5 = i12;
                        i9 = -i12;
                    } else {
                        Log.e("imgedit", " touchMoveCrop => 아래쪽 오른쪽=3");
                        i10 = 3;
                        i5 = i12;
                        i6 = i12;
                        i9 = i8;
                    }
                } else {
                    i10 = i4;
                    i9 = i8;
                }
            } else {
                i10 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i9 = 0;
            }
        } else if (this.R) {
            i10 = 5;
            i5 = i12;
            i6 = i11;
            i7 = i12;
            i9 = i11;
        } else {
            i10 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i9 = 0;
        }
        a(i9, i7, i6, i5, i10);
    }

    private void b(Canvas canvas) {
        if (this.n != 3001) {
            return;
        }
        int i2 = this.J.top;
        int i3 = this.J.left;
        int i4 = this.J.right;
        int i5 = this.J.bottom;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i4 - i3, i5 - i2, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1442840576);
        canvas2.drawRect(0.0f, 0.0f, i4 - i3, i5 - i2, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(-1);
        paint2.setFilterBitmap(false);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas2.drawCircle((i4 - i3) / 2, (i5 - i2) / 2, (i4 - i3) / 2, paint2);
        canvas.drawBitmap(bitmapCreateBitmap, i3, i2, (Paint) null);
    }

    private void c(Canvas canvas) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        canvas2.drawColor(ViewCompat.MEASURED_SIZE_MASK);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setFilterBitmap(false);
        canvas2.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2, paint);
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, paint2);
    }

    private boolean c(int i2, int i3) {
        return (i2 > this.J.left + 50 && i2 < this.J.right + (-50)) && (i3 > this.J.top + 50 && i3 < this.J.bottom + (-50));
    }

    private void h() {
        if (getContext() != null) {
            this.M = x.a(1.0f);
            this.N = x.a(5.0f);
            this.O = x.a(16.0f);
        }
        this.C = new Matrix();
        this.D = new Matrix();
        this.K = new Path();
        this.s = new Paint(4);
        this.s.setColor(-1710619);
        this.s.setStyle(Paint.Style.STROKE);
        this.s.setStrokeWidth(2.0f);
        this.t = new Paint(4);
        this.t.setColor(-857348635);
        this.t.setStyle(Paint.Style.STROKE);
        this.t.setStrokeWidth(this.M);
        this.u = new Paint(4);
        this.u.setColor(-1305267405);
        this.u.setStyle(Paint.Style.FILL);
        this.u.setStrokeWidth(this.M);
        this.v = new Paint(4);
        this.v.setStrokeJoin(Paint.Join.MITER);
        this.v.setStrokeCap(Paint.Cap.SQUARE);
        this.v.setColor(-1051914);
        this.v.setStyle(Paint.Style.STROKE);
        this.v.setStrokeWidth(this.N);
    }

    private void i() {
        int i2;
        int i3;
        int width = getWidth();
        int height = getHeight();
        int iMin = (Math.min(width, height) * 4) / 5;
        if ((this.q == 0) | (this.r == 0)) {
            Log.d("imgedit", "CROP_ASPECTX & CROP_ASPECTY is == 0");
        }
        if (this.q > this.r) {
            i3 = (this.r * iMin) / this.q;
            i2 = iMin;
        } else {
            i2 = (this.q * iMin) / this.r;
            i3 = iMin;
        }
        int i4 = (width - i2) / 2;
        int i5 = (height - i3) / 2;
        this.J = new Rect(i4, i5, i2 + i4, i3 + i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.C == null) {
            this.C = new Matrix();
        } else {
            this.C.reset();
        }
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float f = this.y / 2.0f;
        this.C.postTranslate(width - f, height - (this.z / 2.0f));
        this.C.postScale(this.E, this.E, width, height);
        this.C.postRotate(this.G, width, height);
        if (this.D == null) {
            this.D = new Matrix();
        } else {
            this.D.reset();
        }
        this.C.invert(this.D);
    }

    private void setRoundEffectedBitmap(Bitmap bitmap) {
        this.x = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.x);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        c(canvas);
    }

    public void a() {
        if (this.H) {
            return;
        }
        this.H = true;
        this.G -= 90.0f;
        if (this.G >= 360.0f || this.G <= -360.0f) {
            this.G = 0.0f;
        }
        int height = getHeight();
        int width = getWidth();
        float f = width / height;
        float f2 = height * f;
        float f3 = width * f;
        if (f3 > this.e && f2 <= this.f) {
            f = this.e / width;
        } else if (f2 < this.f && f3 <= this.e) {
            f = this.f / height;
        } else if (f3 > this.e && f2 > this.f) {
            f = this.e / width;
            if (height * f > this.e) {
                f = this.f / height;
            }
        }
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(250L);
        animationSet.setInterpolator(new DecelerateInterpolator(2.0f));
        animationSet.setFillAfter(true);
        animationSet.addAnimation(new ScaleAnimation(1.0f, f, 1.0f, f, 1, 0.5f, 1, 0.5f));
        animationSet.addAnimation(new RotateAnimation(0.0f, -90.0f, 1, 0.5f, 1, 0.5f));
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ImageEditView.this.setRotate();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        startAnimation(animationSet);
    }

    public void b() {
        RectF rectF = new RectF(this.J);
        Matrix matrix = new Matrix();
        matrix.setRotate(-this.G, getWidth() / 2.0f, getWidth() / 2.0f);
        matrix.setScale(this.F, this.F);
        matrix.mapRect(rectF);
        a((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        setEditMode(2000);
    }

    public void c() {
        if (ImageType.ROUND.equals(this.T)) {
            setRoundEffectedBitmap(this.w);
        }
    }

    public boolean d() {
        return this.G != 0.0f || this.L;
    }

    public void e() {
        this.L = false;
    }

    public boolean f() {
        return this.w != null && !this.w.isRecycled() && this.w.getWidth() >= 50 && this.w.getHeight() >= 50;
    }

    public void g() {
        Bitmap bitmap;
        if (this.w != null && !this.w.isRecycled()) {
            this.w.recycle();
        }
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null) {
            return;
        }
        bitmap.recycle();
    }

    public int getEditMode() {
        return this.n;
    }

    public Bitmap getMergedBitmap() {
        Matrix matrix = new Matrix();
        int i2 = this.y;
        int i3 = this.z;
        if (this.G % 360.0f != 0.0f) {
            matrix.setRotate(this.G, this.y / 2.0f, this.z / 2.0f);
            if (this.G % 180.0f != 0.0f) {
                int i4 = (int) ((this.z - this.y) / 2.0f);
                matrix.postTranslate(i4, -i4);
                i2 = this.z;
                i3 = this.y;
            }
        }
        if (i3 == 0 || i2 == 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        a(new Canvas(bitmapCreateBitmap), matrix);
        return bitmapCreateBitmap;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.concat(this.C);
        if (this.x != null && !this.x.isRecycled()) {
            canvas.drawBitmap(this.x, 0.0f, 0.0f, (Paint) null);
        } else if (this.w != null && !this.w.isRecycled()) {
            canvas.drawBitmap(this.w, 0.0f, 0.0f, (Paint) null);
        }
        canvas.restore();
        a(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f == 0) {
            this.f = View.MeasureSpec.getSize(i2);
        }
        if (this.e == 0) {
            this.e = View.MeasureSpec.getSize(i3);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        int i2 = (int) fArr[0];
        int i3 = (int) fArr[1];
        this.D.mapPoints(fArr);
        switch (action) {
            case 0:
                if (this.J != null) {
                    this.R = c(i2, i3);
                    this.S = this.R ? false : true;
                    this.A = i2;
                    this.B = i3;
                }
                break;
            case 1:
                this.S = false;
                this.R = false;
                break;
            case 2:
                if (this.n == 3001) {
                    b(i2, i3);
                }
                this.A = i2;
                this.B = i3;
                break;
        }
        invalidate();
        return true;
    }

    public void setBitmap(Bitmap bitmap) {
        if (this.w != null) {
            this.w = null;
        }
        this.w = bitmap;
        this.y = bitmap.getWidth();
        this.z = bitmap.getHeight();
        this.G = 0.0f;
        a(this.y, this.z);
        this.C.setScale(this.E, this.E);
        this.C.invert(this.D);
    }

    public void setCropRectAspects(int i2, int i3) {
        this.q = i2;
        this.r = i3;
        this.o = i2 < i3 ? 200 : (i2 * 200) / i3;
        this.p = i2 <= i3 ? (i3 * 200) / i2 : 200;
    }

    public void setEditMode(final int i2) {
        if (i2 != 3001) {
            if (this.n != 3001) {
                this.n = i2;
                return;
            }
            b bVar = new b();
            bVar.setAnimationListener(new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditView.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    ImageEditView.this.n = i2;
                    ImageEditView.this.invalidate();
                    ImageEditView.this.I = false;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    ImageEditView.this.I = true;
                }
            });
            startAnimation(bVar);
            return;
        }
        if (this.P != null) {
            this.P.recycle();
        }
        if (this.x != null) {
            this.x.recycle();
        }
        this.P = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        this.Q = new Canvas(this.P);
        this.n = i2;
        i();
        startAnimation(new a());
    }

    public void setRotate() {
        if (((int) this.G) % 180 != 0) {
            a(this.z, this.y);
        } else {
            a(this.y, this.z);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ImageEditView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ImageEditView.this.j();
                ImageEditView.this.setAnimation(null);
                ImageEditView.this.invalidate();
                ImageEditView.this.H = false;
            }
        });
    }

    public void setType(ImageType imageType) {
        this.T = imageType;
    }
}
