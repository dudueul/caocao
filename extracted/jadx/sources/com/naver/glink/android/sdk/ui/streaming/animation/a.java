package com.naver.glink.android.sdk.ui.streaming.animation;

import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import java.util.Iterator;

/* JADX INFO: compiled from: LikeAnimationThread.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends Thread {
    private static final int a = 17;
    private static final int b = 3;
    private LikeSurfaceView c;
    private SurfaceHolder d;
    private long e;
    private boolean f;

    public a(LikeSurfaceView likeSurfaceView) {
        this.f = false;
        if (likeSurfaceView == null) {
            this.f = true;
            return;
        }
        this.c = likeSurfaceView;
        this.d = likeSurfaceView.getHolder();
        setPriority(4);
    }

    private void a(long j) throws InterruptedException {
        for (long jCurrentTimeMillis = System.currentTimeMillis(); jCurrentTimeMillis - j < 17; jCurrentTimeMillis = System.currentTimeMillis()) {
            Thread.sleep(3L);
        }
    }

    private void a(Canvas canvas, long j) {
        try {
            synchronized (this.c.c) {
                try {
                    for (b bVar : this.c.c) {
                        bVar.a(j);
                        bVar.a(canvas);
                    }
                    Iterator<b> it = this.c.c.iterator();
                    while (it.hasNext()) {
                        b next = it.next();
                        if (!next.b) {
                            this.c.d--;
                        }
                        if (next.a) {
                            it.remove();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        this.f = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            this.e = System.currentTimeMillis();
            System.currentTimeMillis();
            while (!this.f) {
                Canvas canvasLockCanvas = this.d.lockCanvas();
                if (canvasLockCanvas != null) {
                    canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    a(canvasLockCanvas, jCurrentTimeMillis - this.e);
                    this.d.unlockCanvasAndPost(canvasLockCanvas);
                    a(this.e);
                    this.e = jCurrentTimeMillis;
                    if (this.c.c.size() == 0) {
                        synchronized (this) {
                            wait();
                            this.e = System.currentTimeMillis();
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        this.c = null;
        this.d = null;
    }
}
