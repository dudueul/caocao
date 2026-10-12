package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ca {
    public static ExecutorService a;
    public static cd b;
    private Future c;

    public abstract Object a(URI uri, InputStream inputStream);

    public Map a() {
        return Collections.emptyMap();
    }

    public final synchronized void a(@Nullable cf cfVar, ExecutorService executorService) {
        if (!(this.c == null || this.c.isDone())) {
            throw new IllegalStateException(String.valueOf("Call has not completed"));
        }
        this.c = executorService.submit(new cc(this, cfVar));
    }

    public abstract String b();

    public abstract String c();

    public String d() {
        return null;
    }

    public Map e() {
        return new LinkedHashMap();
    }

    public Object f() {
        return b.a(this);
    }
}
