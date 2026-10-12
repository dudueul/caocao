package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.os.SystemClock;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hm {
    final gy a;

    @Nullable
    ScheduledFuture c;
    final AtomicBoolean b = new AtomicBoolean();
    private final Runnable d = new Runnable() { // from class: com.tapjoy.internal.hm.1
        @Override // java.lang.Runnable
        public final void run() {
            if (hm.this.b.compareAndSet(true, false)) {
                gw.a("The session ended");
                gy gyVar = hm.this.a;
                long jElapsedRealtime = SystemClock.elapsedRealtime() - gyVar.c;
                hc hcVar = gyVar.a;
                synchronized (hcVar) {
                    long jA = hcVar.c.i.a() + jElapsedRealtime;
                    hcVar.c.i.a(jA);
                    hcVar.b.i = Long.valueOf(jA);
                }
                ev.a aVarA = gyVar.a(ey.APP, "session");
                aVarA.i = Long.valueOf(jElapsedRealtime);
                gyVar.a(aVarA);
                gyVar.c = 0L;
                hc hcVar2 = gyVar.a;
                long jLongValue = aVarA.e.longValue();
                synchronized (hcVar2) {
                    SharedPreferences.Editor editorA = hcVar2.c.a();
                    hcVar2.c.j.a(editorA, jLongValue);
                    hcVar2.c.k.a(editorA, jElapsedRealtime);
                    editorA.apply();
                    hcVar2.b.j = Long.valueOf(jLongValue);
                    hcVar2.b.k = Long.valueOf(jElapsedRealtime);
                }
                final gx gxVar = gyVar.b;
                if (gxVar.b != null) {
                    gxVar.a();
                    new im() { // from class: com.tapjoy.internal.gx.1
                        AnonymousClass1() {
                        }

                        @Override // com.tapjoy.internal.im
                        public final boolean a() {
                            return !gx.this.a.c();
                        }
                    }.run();
                }
                gxVar.a.flush();
                fs.d.notifyObservers();
            }
        }
    };
    private final Runnable e = new Runnable() { // from class: com.tapjoy.internal.hm.2
        @Override // java.lang.Runnable
        public final void run() {
        }
    };

    hm(gy gyVar) {
        this.a = gyVar;
    }

    public final void a() {
        if (this.b.get()) {
            if (!Boolean.FALSE.booleanValue()) {
                this.d.run();
            } else if (this.c == null || this.c.cancel(false)) {
                this.c = hn.a.schedule(this.d, 3000L, TimeUnit.MILLISECONDS);
            }
        }
    }
}
