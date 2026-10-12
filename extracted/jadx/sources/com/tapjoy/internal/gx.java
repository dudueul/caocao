package com.tapjoy.internal;

import java.io.File;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class gx implements Runnable {
    final hl a;
    cd b;
    private final Object c;
    private final Thread d;
    private boolean e;

    public gx(File file) {
        this.a = new hl(file);
        this.c = this.a;
        new Object[1][0] = Integer.valueOf(this.a.b());
        this.d = new Thread(this, "5Rocks");
        this.d.start();
    }

    public final void a() {
        if (this.b == null || this.a.c()) {
            return;
        }
        a(true);
    }

    final void a(boolean z) {
        synchronized (this.c) {
            this.e = z;
            this.c.notify();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 1;
        while (true) {
            long jMin = 0;
            while (this.b != null && this.a.b() > 0 && jMin <= 0) {
                try {
                    try {
                        if (this.a.b() > 10000) {
                            this.a.a(this.a.b() - 10000);
                        }
                        ev evVarB = this.a.b(0);
                        if (evVarB == null) {
                            break;
                        }
                        fh fhVar = evVarB.w;
                        if (fhVar != null && fhVar.G == null) {
                            hn.c.await(3L, TimeUnit.SECONDS);
                        }
                        if (!v.c()) {
                            hn.b.await(3L, TimeUnit.SECONDS);
                        }
                        jMin = (this.e || evVarB.n == ey.APP || this.a.b() >= 100 || evVarB.p.longValue() > System.currentTimeMillis()) ? 0L : Math.min(Math.max((evVarB.p.longValue() + 60000) - System.currentTimeMillis(), 0L), 60000L);
                        if (jMin <= 0) {
                            ik ikVar = new ik();
                            ikVar.a(evVarB);
                            new Object[1][0] = evVarB;
                            for (int i2 = 1; i2 < 100 && i2 < this.a.b(); i2++) {
                                ev evVarB2 = this.a.b(i2);
                                if (evVarB2 == null || !ikVar.a(evVarB2)) {
                                    break;
                                }
                                new Object[1][0] = evVarB2;
                            }
                            i++;
                            try {
                                Object[] objArr = {Integer.valueOf(ikVar.g()), Integer.valueOf(i)};
                                this.b.a(ikVar);
                                this.a.a(ikVar.g());
                                try {
                                    new Object[1][0] = Integer.valueOf(ikVar.g());
                                    i = 0;
                                } catch (Exception e) {
                                    e = e;
                                    i = 0;
                                    Object[] objArr2 = {Integer.valueOf(ikVar.g()), e};
                                    jMin = 300000;
                                }
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                    } catch (Exception e3) {
                        return;
                    }
                } catch (InterruptedException e4) {
                    return;
                }
            }
            this.a.flush();
            if (jMin > 0) {
                synchronized (this.c) {
                    this.e = false;
                    new Object[1][0] = Long.valueOf(jMin);
                    this.c.wait(jMin);
                }
            } else {
                synchronized (this.c) {
                    this.e = false;
                    if (this.b == null || this.a.c()) {
                        this.c.wait();
                    }
                }
            }
        }
    }
}
