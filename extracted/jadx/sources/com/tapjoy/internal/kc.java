package com.tapjoy.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class kc implements kf {
    private final a b;
    private final a c;
    private final ReentrantLock a = new ReentrantLock();
    private kf.a d = kf.a.NEW;
    private boolean e = false;

    class a extends kb {
        private a() {
        }

        /* synthetic */ a(kc kcVar, byte b) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.tapjoy.internal.kb, java.util.concurrent.Future
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public kf.a get(long j, TimeUnit timeUnit) throws TimeoutException {
            try {
                return (kf.a) super.get(j, timeUnit);
            } catch (TimeoutException e) {
                throw new TimeoutException(kc.this.toString());
            }
        }
    }

    public kc() {
        byte b = 0;
        this.b = new a(this, b);
        this.c = new a(this, b);
    }

    private ke g() {
        this.a.lock();
        try {
            if (this.d == kf.a.NEW) {
                this.d = kf.a.TERMINATED;
                this.b.a(kf.a.TERMINATED);
                this.c.a(kf.a.TERMINATED);
            } else if (this.d == kf.a.STARTING) {
                this.e = true;
                this.b.a(kf.a.STOPPING);
            } else if (this.d == kf.a.RUNNING) {
                this.d = kf.a.STOPPING;
                b();
            }
        } catch (Throwable th) {
            a(th);
        } finally {
            this.a.unlock();
        }
        return this.c;
    }

    protected abstract void a();

    protected final void a(Throwable th) {
        jp.a(th);
        this.a.lock();
        try {
            if (this.d == kf.a.STARTING) {
                this.b.a(th);
                this.c.a((Throwable) new Exception("Service failed to start.", th));
            } else if (this.d == kf.a.STOPPING) {
                this.c.a(th);
            } else if (this.d == kf.a.RUNNING) {
                this.c.a((Throwable) new Exception("Service failed while running", th));
            } else if (this.d == kf.a.NEW || this.d == kf.a.TERMINATED) {
                throw new IllegalStateException("Failed while in state:" + this.d, th);
            }
            this.d = kf.a.FAILED;
            this.a.unlock();
        } catch (Throwable th2) {
            this.a.unlock();
            throw th2;
        }
    }

    protected abstract void b();

    protected final void c() {
        this.a.lock();
        try {
            if (this.d != kf.a.STARTING) {
                IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.d);
                a(illegalStateException);
                throw illegalStateException;
            }
            this.d = kf.a.RUNNING;
            if (this.e) {
                g();
            } else {
                this.b.a(kf.a.RUNNING);
            }
            this.a.unlock();
        } catch (Throwable th) {
            this.a.unlock();
            throw th;
        }
    }

    protected final void d() {
        this.a.lock();
        try {
            if (this.d != kf.a.STOPPING && this.d != kf.a.RUNNING) {
                IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + this.d);
                a(illegalStateException);
                throw illegalStateException;
            }
            this.d = kf.a.TERMINATED;
            this.c.a(kf.a.TERMINATED);
            this.a.unlock();
        } catch (Throwable th) {
            this.a.unlock();
            throw th;
        }
    }

    @Override // com.tapjoy.internal.kf
    public final ke e() {
        this.a.lock();
        try {
            if (this.d == kf.a.NEW) {
                this.d = kf.a.STARTING;
                a();
            }
        } catch (Throwable th) {
            a(th);
        } finally {
            this.a.unlock();
        }
        return this.b;
    }

    @Override // com.tapjoy.internal.kf
    public final kf.a f() {
        this.a.lock();
        try {
            return (this.e && this.d == kf.a.STARTING) ? kf.a.STOPPING : this.d;
        } finally {
            this.a.unlock();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }
}
