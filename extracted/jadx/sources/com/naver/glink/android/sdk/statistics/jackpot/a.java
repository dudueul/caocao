package com.naver.glink.android.sdk.statistics.jackpot;

import android.content.Context;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.api.requests.d;
import com.naver.glink.android.sdk.statistics.jackpot.a;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: JackpotLog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a<T extends a> {
    private static final l a = l.a("JackpotLog");
    private String e;
    private boolean b = false;
    private boolean c = false;
    private boolean d = true;
    private Map<String, Object> f = new LinkedHashMap();
    private Map<String, Object> g = new LinkedHashMap();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.statistics.jackpot.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JackpotLog.java */
    public interface InterfaceC0036a {
        JackpotAction getAction();

        String getClassifier();

        String getSceneId();
    }

    private T d() {
        this.f.put("event_time", Long.valueOf(System.currentTimeMillis()));
        return this;
    }

    private void e() {
        if (this.b) {
            return;
        }
        a();
        d();
        if (!this.g.isEmpty()) {
            this.f.put("extra", this.g);
        }
        this.b = true;
    }

    public T a(JackpotAction jackpotAction) {
        this.f.put("action_id", jackpotAction.getId());
        return this;
    }

    public T a(String str) {
        this.f.put("scene_id", str);
        return this;
    }

    public T a(String str, Enum r4) {
        this.g.put(str, r4.name());
        return this;
    }

    public T a(String str, Integer num) {
        this.g.put(str, num);
        return this;
    }

    public T a(String str, Long l) {
        this.g.put(str, l);
        return this;
    }

    public T a(String str, String str2) {
        this.g.put(str, str2);
        return this;
    }

    public T a(String str, boolean z) {
        this.g.put(str, Boolean.valueOf(z));
        return this;
    }

    public T a(Map<String, Object> map) {
        this.g.putAll(map);
        return this;
    }

    public T a(boolean z) {
        this.d = z;
        return this;
    }

    protected void a() {
    }

    public void a(Context context) {
        if (!this.d && this.c) {
            a.a("Log already sent. does not allow multiple send.", new Object[0]);
        } else {
            d.a(b());
            this.c = true;
        }
    }

    public T b(String str) {
        this.f.put("classifier", str);
        return this;
    }

    public Map<String, Object> b() {
        e();
        try {
            return this.f;
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    public void c() {
        this.f.clear();
        this.g.clear();
        this.b = false;
        this.c = false;
    }
}
