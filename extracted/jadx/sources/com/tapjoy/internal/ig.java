package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ig extends ImageView implements Runnable {
    private ic a;
    private Bitmap b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private boolean f;
    private Thread g;
    private b h;
    private long i;
    private a j;
    private final Runnable k;
    private final Runnable l;

    public interface a {
    }

    public interface b {
        Bitmap a();
    }

    public ig(Context context) {
        super(context);
        this.c = new Handler(Looper.getMainLooper());
        this.h = null;
        this.i = -1L;
        this.j = null;
        this.k = new Runnable() { // from class: com.tapjoy.internal.ig.1
            @Override // java.lang.Runnable
            public final void run() {
                if (ig.this.b == null || ig.this.b.isRecycled()) {
                    return;
                }
                ig.this.setImageBitmap(ig.this.b);
            }
        };
        this.l = new Runnable() { // from class: com.tapjoy.internal.ig.2
            @Override // java.lang.Runnable
            public final void run() {
                ig.b(ig.this);
                ig.c(ig.this);
                ig.d(ig.this);
                ig.e(ig.this);
            }
        };
    }

    static /* synthetic */ Bitmap b(ig igVar) {
        igVar.b = null;
        return null;
    }

    static /* synthetic */ ic c(ig igVar) {
        igVar.a = null;
        return null;
    }

    static /* synthetic */ Thread d(ig igVar) {
        igVar.g = null;
        return null;
    }

    private void d() {
        boolean z;
        if (this.a.a == 0) {
            return;
        }
        ic icVar = this.a;
        if (-1 >= icVar.c.c) {
            z = false;
        } else {
            icVar.a = -1;
            z = true;
        }
        if (!z || this.d) {
            return;
        }
        this.e = true;
        e();
    }

    private void e() {
        if ((this.d || this.e) && this.a != null && this.g == null) {
            this.g = new Thread(this);
            this.g.start();
        }
    }

    static /* synthetic */ boolean e(ig igVar) {
        igVar.f = false;
        return false;
    }

    public final void a() {
        this.d = true;
        e();
    }

    public final void a(ie ieVar, byte[] bArr) {
        try {
            this.a = new ic(new ih(), ieVar, ByteBuffer.wrap(bArr));
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e) {
            this.a = null;
            new Object[1][0] = e;
        }
    }

    public final void b() {
        this.d = false;
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
    }

    public final void c() {
        this.d = false;
        this.e = false;
        this.f = true;
        b();
        this.c.post(this.l);
    }

    public final long getFramesDisplayDuration() {
        return this.i;
    }

    public final int getGifHeight() {
        return this.a.c.g;
    }

    public final int getGifWidth() {
        return this.a.c.f;
    }

    public final a getOnAnimationStop() {
        return this.j;
    }

    public final b getOnFrameAvailable() {
        return this.h;
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        long jNanoTime;
        int i;
        do {
            if (!this.d && !this.e) {
                break;
            }
            ic icVar = this.a;
            if (icVar.c.c <= 0) {
                z = false;
            } else {
                if (icVar.a == icVar.c.c - 1) {
                    icVar.b++;
                }
                if (icVar.c.m == -1 || icVar.b <= icVar.c.m) {
                    icVar.a = (icVar.a + 1) % icVar.c.c;
                    z = true;
                } else {
                    z = false;
                }
            }
            try {
                long jNanoTime2 = System.nanoTime();
                this.b = this.a.a();
                if (this.h != null) {
                    this.b = this.h.a();
                }
                jNanoTime = (System.nanoTime() - jNanoTime2) / 1000000;
                try {
                    this.c.post(this.k);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e = e;
                    new Object[1][0] = e;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    new Object[1][0] = e;
                }
            } catch (ArrayIndexOutOfBoundsException e3) {
                e = e3;
                jNanoTime = 0;
            } catch (IllegalArgumentException e4) {
                e = e4;
                jNanoTime = 0;
            }
            this.e = false;
            if (!this.d || !z) {
                this.d = false;
                break;
            }
            try {
                ic icVar2 = this.a;
                if (icVar2.c.c <= 0 || icVar2.a < 0) {
                    i = 0;
                } else {
                    int i2 = icVar2.a;
                    i = (i2 < 0 || i2 >= icVar2.c.c) ? -1 : ((id) icVar2.c.e.get(i2)).i;
                }
                int i3 = (int) (((long) i) - jNanoTime);
                if (i3 > 0) {
                    Thread.sleep(this.i > 0 ? this.i : i3);
                }
            } catch (InterruptedException e5) {
            }
        } while (this.d);
        if (this.f) {
            this.c.post(this.l);
        }
        this.g = null;
    }

    public final void setBytes(byte[] bArr) {
        this.a = new ic();
        try {
            this.a.a(bArr);
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e) {
            this.a = null;
            new Object[1][0] = e;
        }
    }

    public final void setFramesDisplayDuration(long j) {
        this.i = j;
    }

    public final void setOnAnimationStop(a aVar) {
        this.j = aVar;
    }

    public final void setOnFrameAvailable(b bVar) {
        this.h = bVar;
    }
}
