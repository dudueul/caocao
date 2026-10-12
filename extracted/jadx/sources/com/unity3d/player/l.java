package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.ViewCompat;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class l extends View {
    final int a;
    final int b;
    Bitmap c;
    Bitmap d;

    /* JADX INFO: renamed from: com.unity3d.player.l$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.a().length];

        static {
            try {
                a[a.a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class a {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        private static final /* synthetic */ int[] d = {a, b, c};

        public static int[] a() {
            return (int[]) d.clone();
        }
    }

    public l(Context context, int i) {
        super(context);
        this.a = i;
        this.b = getResources().getIdentifier("unity_static_splash", "drawable", getContext().getPackageName());
        if (this.b != 0) {
            forceLayout();
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        if (this.b == 0) {
            return;
        }
        if (this.c == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            this.c = BitmapFactory.decodeResource(getResources(), this.b, options);
        }
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        int width2 = getWidth();
        int height2 = getHeight();
        if (width2 == 0 || height2 == 0) {
            return;
        }
        float f = width / height;
        boolean z2 = ((float) width2) / ((float) height2) <= f;
        switch (AnonymousClass1.a[this.a - 1]) {
            case 1:
                if (width2 < width) {
                    height2 = (int) (width2 / f);
                } else {
                    height2 = height;
                    width2 = width;
                }
                i5 = height2 >= height2 ? width2 : (int) (height2 * f);
                break;
            case 2:
            case 3:
                if (z2 ^ (this.a == a.c)) {
                    height2 = (int) (width2 / f);
                    i5 = width2;
                }
                break;
            default:
                height2 = height;
                i5 = width;
                break;
        }
        if (this.d != null) {
            if (this.d.getWidth() == i5 && this.d.getHeight() == height2) {
                return;
            }
            if (this.d != this.c) {
                this.d.recycle();
                this.d = null;
            }
        }
        this.d = Bitmap.createScaledBitmap(this.c, i5, height2, true);
        this.d.setDensity(getResources().getDisplayMetrics().densityDpi);
        ColorDrawable colorDrawable = new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), this.d);
        bitmapDrawable.setGravity(17);
        setBackground(new LayerDrawable(new Drawable[]{colorDrawable, bitmapDrawable}));
    }
}
