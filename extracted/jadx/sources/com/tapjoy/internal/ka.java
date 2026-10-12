package com.tapjoy.internal;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ka implements kf {
    private static final Logger a = Logger.getLogger(ka.class.getName());
    private final kf b = new kc() { // from class: com.tapjoy.internal.ka.1
        @Override // com.tapjoy.internal.kc
        protected final void a() {
            final ka kaVar = ka.this;
            new Executor() { // from class: com.tapjoy.internal.ka.2
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    new Thread(runnable, ka.this.getClass().getSimpleName()).start();
                }
            }.execute(new Runnable() { // from class: com.tapjoy.internal.ka.1.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        ka.this.b();
                        c();
                        if (f() == kf.a.RUNNING) {
                            try {
                                ka.this.d();
                            } catch (Throwable th) {
                                try {
                                    ka.this.c();
                                } catch (Exception e) {
                                    ka.a.log(Level.WARNING, "Error while attempting to shut down the service after failure.", (Throwable) e);
                                }
                                throw th;
                            }
                        }
                        ka.this.c();
                        d();
                    } catch (Throwable th2) {
                        a(th2);
                        throw jr.a(th2);
                    }
                }
            });
        }

        @Override // com.tapjoy.internal.kc
        protected final void b() {
            ka.this.a();
        }
    };

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public abstract void d();

    @Override // com.tapjoy.internal.kf
    public final ke e() {
        return this.b.e();
    }

    @Override // com.tapjoy.internal.kf
    public final kf.a f() {
        return this.b.f();
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }
}
