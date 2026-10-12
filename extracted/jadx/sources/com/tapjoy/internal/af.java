package com.tapjoy.internal;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class af extends Animation {
    private final boolean a;
    private final float b;
    private final float c;
    private final int d;
    private final float e;
    private final int f;
    private final float g;
    private float h;
    private float i;
    private Camera j;

    public af(boolean z, float f, float f2, int i, float f3, int i2, float f4) {
        this.a = z;
        this.b = f;
        this.c = f2;
        this.d = i;
        this.e = f3;
        this.f = i2;
        this.g = f4;
    }

    @Override // android.view.animation.Animation
    protected final void applyTransformation(float f, Transformation transformation) {
        float f2 = this.b;
        float f3 = f2 + ((this.c - f2) * f);
        Matrix matrix = transformation.getMatrix();
        Camera camera = this.j;
        camera.save();
        if (this.a) {
            camera.rotateX(f3);
        } else {
            camera.rotateY(f3);
        }
        camera.getMatrix(matrix);
        camera.restore();
        float f4 = this.h;
        float f5 = this.i;
        matrix.preTranslate(-f4, -f5);
        matrix.postTranslate(f4, f5);
    }

    @Override // android.view.animation.Animation
    public final void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.h = resolveSize(this.d, this.e, i, i3);
        this.i = resolveSize(this.f, this.g, i2, i4);
        this.j = new Camera();
    }
}
