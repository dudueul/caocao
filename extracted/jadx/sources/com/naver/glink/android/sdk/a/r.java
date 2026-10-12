package com.naver.glink.android.sdk.a;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RepeatingTasks.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class r {
    private static final l a = l.a(r.class.getSimpleName());
    private Handler b = new Handler(Looper.getMainLooper());
    private List<a> c = new ArrayList();

    /* JADX INFO: compiled from: RepeatingTasks.java */
    private static class a implements Runnable {
        final Handler a;
        final long b;
        final Runnable c;

        a(Handler handler, long j, Runnable runnable) {
            this.a = handler;
            this.b = j;
            this.c = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.c.run();
            } catch (Exception e) {
                r.a.a(e);
            } finally {
                this.a.postDelayed(this, this.b);
            }
        }
    }

    public void a() {
        Iterator<a> it = this.c.iterator();
        while (it.hasNext()) {
            this.b.removeCallbacks(it.next());
        }
    }

    public void a(long j, Runnable runnable) {
        a aVar = new a(this.b, j, runnable);
        this.c.add(aVar);
        this.b.post(aVar);
    }
}
