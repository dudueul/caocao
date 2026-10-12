package com.tapjoy.internal;

import com.squareup.otto.Bus;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fy extends gj {
    static final Map a = Collections.unmodifiableMap(new HashMap());
    private final gj.a c = a("BuildConfig");
    private final gj.a d = a("ServerFinal");
    private final gj.a e = a("AppRuntime");
    private final gj.a f;
    private final gj.a g;

    fy() {
        this.e.b = new ConcurrentHashMap();
        this.f = a("ConnectFlags");
        this.g = a("ServerDefault");
        gj.a aVarA = a("SDKDefault");
        if (!"".isEmpty()) {
            try {
                this.c.b = bn.b("").d();
            } catch (IOException e) {
                throw new Error("BuildConfig.TJC_CONFIGURATION malformed", e);
            }
        }
        HashMap map = new HashMap();
        map.put("placement_request_content_retry_timeout", -1);
        map.put("placement_request_content_retry_backoff", Arrays.asList(0L, 500L, Long.valueOf(TapjoyConstants.TIMER_INCREMENT), Double.valueOf(2.0d)));
        aVarA.b = map;
    }

    public final void a(Hashtable hashtable) {
        HashMap map = new HashMap();
        for (Map.Entry entry : hashtable.entrySet()) {
            String str = (String) a.get(entry.getKey());
            if (str == null) {
                str = (String) entry.getKey();
            }
            this.e.b.remove(str);
            map.put(str, entry.getValue());
        }
        this.f.b = map;
        setChanged();
    }

    public final void a(@Nullable Map map) {
        Map map2;
        Map map3;
        if (map != null) {
            Map map4 = (Map) map.get("final");
            map2 = (Map) map.get(Bus.DEFAULT_IDENTIFIER);
            map3 = map4;
        } else {
            map2 = null;
            map3 = null;
        }
        this.d.b = map3;
        this.g.b = map2;
        setChanged();
    }
}
