package com.naver.glink.android.sdk.configure;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.x;

/* JADX INFO: compiled from: SdkSize.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends com.naver.plug.android.core.a.a {
    private static final l j = l.a(c.class.getSimpleName());
    public final boolean a;
    public final boolean b;
    public final int c;
    public final int d;
    public final float e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    private final Point k;

    private c() {
        this.k = new Point();
        this.a = false;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = 0.0f;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }

    public c(Context context) {
        this(b(context), a(context), false);
    }

    public c(Context context, boolean z) {
        this(b(context), a(context), z);
    }

    private c(Point point, boolean z, boolean z2) {
        this.k = point;
        this.a = z;
        this.b = z2;
        int iA = x.a(12.0f);
        int iA2 = x.a(15.0f);
        float fA = x.a(48.0f);
        if (z) {
            int iMax = (Math.max(point.x, point.y) - iA) / 13;
            if (Math.max(point.x, point.y) / Math.min(point.x, point.y) >= 1.98d) {
                this.f = Math.max(point.x, point.y) / 15;
            } else if (Math.min(point.x, point.y) >= 1.85d) {
                this.f = Math.max(point.x, point.y) / 14;
            } else {
                this.f = iMax;
            }
            this.e = this.f / fA;
            this.g = this.f;
            this.c = (this.f * 13) + iA;
            this.d = (this.f * 6) + iA2;
            this.h = this.f * 12;
            this.i = this.h / 2;
        } else {
            this.g = (int) (((double) Math.max(point.x, point.y)) / 13.5d);
            this.e = this.g / fA;
            if (Math.max(point.x, point.y) / Math.min(point.x, point.y) >= 1.85d) {
                int iA3 = x.a(20.0f);
                this.c = ((this.g * 6) + iA) + iA3 > point.x ? point.x : (this.g * 6) + iA + iA3;
                this.f = ((int) Math.ceil(((double) (this.g * 6)) / ((double) (z2 ? 6 : 5)))) + (iA3 / 6);
            } else {
                this.c = (this.g * 7) + iA > point.x ? point.x : (this.g * 7) + iA;
                this.f = (this.g * 7) / (z2 ? 6 : 5);
            }
            this.d = (this.g * 12) + iA2;
            this.h = this.c;
            this.i = this.h - iA;
        }
        d();
    }

    public static c a() {
        return new c();
    }

    private static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private static Point b(Context context) {
        WindowManager windowManager;
        Point point = new Point();
        if (context != null && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
            windowManager.getDefaultDisplay().getSize(point);
            if (Build.MODEL.contains("LM-G710N") && !a(context)) {
                point.y -= c(context);
            }
            return point;
        }
        return point;
    }

    private static int c(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return (int) Math.ceil((Build.VERSION.SDK_INT >= 23 ? 24 : 25) * resources.getDisplayMetrics().density);
    }

    private void d() {
        j.a("device size x: " + this.k.x + ", y: " + this.k.y, new Object[0]);
        j.a("buttonWidth: " + this.f + ", buttonHeight: " + this.g, new Object[0]);
        j.a("width: " + this.c + ", height: " + this.d, new Object[0]);
        j.a("contentWidth: " + this.h, new Object[0]);
        j.a("bannerWidth: " + this.i, new Object[0]);
        j.a("ratio: " + this.e, new Object[0]);
    }

    public int a(int i) {
        return (i == -1 || i == -2) ? i : (int) (x.a(i) * this.e);
    }

    public void a(View view, int i, int i2) {
        x.a(view, a(i), a(i2));
    }

    public Point b() {
        return this.k;
    }

    public int c() {
        if (com.naver.glink.android.sdk.d.i()) {
            return this.h;
        }
        return (this.b ? 6 : 5) * this.f;
    }
}
