package com.naver.glink.android.sdk.ui.streaming.animation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import com.naver.glink.android.sdk.R;
import java.util.Random;

/* JADX INFO: compiled from: LikeObject.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static int f = 3000;
    private static int g = 1000;
    private static float h = 1.016f;
    private static int i = 2;
    private static int j = 4;
    private static Bitmap o;
    boolean a;
    boolean b;
    boolean c;
    int d;
    int e;
    private float k;
    private float l;
    private int m;
    private int n;
    private RectF p;
    private float q;
    private float r;
    private Paint s;
    private Random t;
    private long u;

    public b(Resources resources, int i2, int i3, boolean z, boolean z2) {
        if (o == null) {
            o = BitmapFactory.decodeResource(resources, R.drawable.cf_icon_like1_pressed);
        }
        this.t = new Random();
        while (this.m == 0 && this.n == 0) {
            if (z2) {
                this.m = this.t.nextInt(5) - 2;
                this.n = (-this.t.nextInt(2)) - 1;
            } else {
                this.m = this.t.nextInt(7) - 3;
                this.n = (-this.t.nextInt(5)) - 3;
            }
        }
        this.d = z2 ? o.getWidth() / 4 : o.getWidth();
        this.e = z2 ? o.getHeight() / 4 : o.getHeight();
        this.q = this.d;
        this.r = this.e;
        this.k = i2 - (this.d / 2);
        this.l = i3 - (this.e / 2);
        this.p = new RectF(this.k, this.l, this.k + this.d, this.l + this.e);
        this.s = new Paint();
        if (!z) {
            this.s.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        }
        this.u = System.currentTimeMillis();
        this.b = z;
        this.c = z2;
    }

    private void a() {
        if (this.k <= 0.0f) {
            this.m = 0;
            this.k = 0.0f;
        } else if (this.k + this.q >= LikeSurfaceView.a) {
            this.m = this.t.nextInt(2) != 0 ? -1 : 0;
        }
        if (this.l + this.r >= LikeSurfaceView.b) {
            this.n *= -1;
            this.l = LikeSurfaceView.b - this.r;
        }
    }

    public void a(long j2) {
        this.k += this.m * (j2 / 20.0f);
        this.l += this.n * (j2 / 20.0f);
        float f2 = this.q * h;
        float f3 = this.r * h;
        if (System.currentTimeMillis() - this.u > g) {
            if (this.d * i < f2) {
                f2 = this.q;
            }
            this.q = f2;
            this.r = ((float) (this.e * i)) < f3 ? this.r : f3;
            this.s.setAlpha(this.s.getAlpha() - j < 1 ? 1 : this.s.getAlpha() - j);
        }
        this.p.left = this.k;
        this.p.top = this.l;
        this.p.right = this.k + this.q;
        this.p.bottom = this.l + this.r;
        a();
    }

    public void a(Canvas canvas) {
        if (System.currentTimeMillis() - this.u >= f) {
            this.a = true;
        } else {
            canvas.drawBitmap(o, (Rect) null, this.p, this.s);
        }
    }
}
