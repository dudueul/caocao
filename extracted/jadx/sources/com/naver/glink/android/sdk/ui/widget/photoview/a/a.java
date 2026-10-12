package com.naver.glink.android.sdk.ui.widget.photoview.a;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: CupcakeGestureDetector.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a implements d {
    private static final String f = "CupcakeGestureDetector";
    final float a;
    final float b;
    protected e c;
    float d;
    float e;
    private VelocityTracker g;
    private boolean h;

    public a(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.b = viewConfiguration.getScaledMinimumFlingVelocity();
        this.a = viewConfiguration.getScaledTouchSlop();
    }

    float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public void a(e eVar) {
        this.c = eVar;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean a() {
        return false;
    }

    float b(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean b() {
        return this.h;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean c(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.g = VelocityTracker.obtain();
                if (this.g != null) {
                    this.g.addMovement(motionEvent);
                } else {
                    com.naver.glink.android.sdk.ui.widget.photoview.b.a.a().c(f, "Velocity tracker is null");
                }
                this.d = a(motionEvent);
                this.e = b(motionEvent);
                this.h = false;
                return true;
            case 1:
                if (this.h && this.g != null) {
                    this.d = a(motionEvent);
                    this.e = b(motionEvent);
                    this.g.addMovement(motionEvent);
                    this.g.computeCurrentVelocity(1000);
                    float xVelocity = this.g.getXVelocity();
                    float yVelocity = this.g.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.b) {
                        this.c.a(this.d, this.e, -xVelocity, -yVelocity);
                    }
                }
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                }
                return true;
            case 2:
                float fA = a(motionEvent);
                float fB = b(motionEvent);
                float f2 = fA - this.d;
                float f3 = fB - this.e;
                if (!this.h) {
                    this.h = Math.sqrt((double) ((f2 * f2) + (f3 * f3))) >= ((double) this.a);
                }
                if (this.h) {
                    this.c.a(f2, f3);
                    this.d = fA;
                    this.e = fB;
                    if (this.g != null) {
                        this.g.addMovement(motionEvent);
                    }
                }
                return true;
            case 3:
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                }
                return true;
            default:
                return true;
        }
    }
}
