package com.naver.glink.android.sdk.ui.streaming.animation;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.naver.glink.android.sdk.a.x;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LikeSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    static int a = 0;
    static int b = 0;
    private static final int e = 30;
    List<b> c;
    int d;
    private a f;
    private boolean g;

    public LikeSurfaceView(Context context) {
        super(context);
        this.c = new ArrayList();
        c();
    }

    public LikeSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList();
        c();
    }

    public LikeSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        c();
    }

    @TargetApi(21)
    public LikeSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = new ArrayList();
        c();
    }

    private void c() {
        getHolder().addCallback(this);
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
        this.g = false;
    }

    private void d() {
        try {
            if (this.f != null) {
                synchronized (this.f) {
                    try {
                        this.f.notify();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
        }
    }

    public void a() {
        setVisibility(0);
    }

    public void a(boolean z) {
        synchronized (this.c) {
            if (!z) {
                if (this.d > 30) {
                    return;
                }
            }
            this.d = this.d < 0 ? 0 : this.d;
            this.d = (z ? 0 : 1) + this.d;
            this.c.add(new b(getResources(), a - x.a(this.g ? 5.0f : 50.0f), b - x.a(this.g ? 8.0f : 80.0f), z, this.g));
            d();
        }
    }

    public void b() {
        setVisibility(8);
        if (this.f != null) {
            this.f.a();
        }
    }

    public void b(boolean z) {
        this.g = z;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        a = i2;
        b = i3;
        Log.d("yang", "yang surfaceChanged width " + i2 + " height " + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.f = new a(this);
            this.f.start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        b();
    }
}
