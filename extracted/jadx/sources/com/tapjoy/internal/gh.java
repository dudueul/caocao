package com.tapjoy.internal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class gh extends gg {
    private final ThreadPoolExecutor b;

    class a implements Runnable {
        private int b;
        private long c;
        private String d;
        private String e;
        private Map f;

        a(int i, long j, String str, String str2, Map map) {
            this.b = i;
            this.c = j;
            this.d = str;
            this.e = str2;
            this.f = map;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                switch (this.b) {
                    case 1:
                        gh.super.a(this.c);
                        break;
                    case 2:
                        gh.super.a();
                        break;
                    case 3:
                        gh.super.a(this.c, this.d, this.e, this.f);
                        break;
                }
            } catch (Throwable th) {
                gh.super.a();
            }
        }
    }

    public gh(File file, gy gyVar) {
        super(file, gyVar);
        this.b = new ThreadPoolExecutor(0, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.gg, com.tapjoy.internal.gf
    public final void a() {
        try {
            this.b.execute(new a(2, 0L, null, null, null));
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.gg, com.tapjoy.internal.gf
    public final void a(long j) {
        try {
            this.b.execute(new a(1, j, null, null, null));
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.gg, com.tapjoy.internal.gf
    public final void a(long j, String str, String str2, Map map) {
        try {
            this.b.execute(new a(3, j, str, str2, map != null ? new HashMap(map) : null));
        } catch (Throwable th) {
        }
    }

    @Override // com.tapjoy.internal.gg
    protected final void finalize() {
        try {
            this.b.shutdown();
            this.b.awaitTermination(1L, TimeUnit.SECONDS);
        } finally {
            super.finalize();
        }
    }
}
