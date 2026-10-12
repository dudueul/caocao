package com.tapjoy.internal;

import android.os.SystemClock;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class gf {
    private static gf c;
    private static final ThreadLocal b = new ThreadLocal() { // from class: com.tapjoy.internal.gf.1
        @Override // java.lang.ThreadLocal
        protected final /* synthetic */ Object initialValue() {
            return new HashMap();
        }
    };
    private static volatile boolean d = false;

    @Nullable
    static Set a = null;

    public static final class a {
        final String a;
        private final TreeMap b = new TreeMap();
        private final Map c = new HashMap();
        private volatile long d;

        a(String str) {
            this.a = str;
        }

        public final a a() {
            try {
                this.d = SystemClock.elapsedRealtime();
            } catch (NullPointerException e) {
                this.d = -1L;
            }
            return this;
        }

        public final a a(String str) {
            this.b.put("failure", str);
            return this;
        }

        public final a a(String str, long j) {
            this.c.put(str, Long.valueOf(j));
            return this;
        }

        public final a a(String str, Object obj) {
            this.b.put(str, obj);
            return this;
        }

        public final a a(Map map) {
            if (map != null) {
                this.b.putAll(map);
            }
            return this;
        }

        public final a b() {
            long j = this.d;
            if (j != -1) {
                try {
                    a("spent_time", SystemClock.elapsedRealtime() - j);
                } catch (NullPointerException e) {
                }
            }
            return this;
        }

        public final a b(String str) {
            this.b.put("misuse", str);
            return this;
        }

        public final a b(Map map) {
            if (map != null) {
                this.c.putAll(map);
            }
            return this;
        }

        public final void c() {
            gf.b(this.a, this.b.size() > 0 ? bh.a((Object) this.b) : null, this.c.size() > 0 ? this.c : null);
        }
    }

    public static a a(String str) {
        a aVarA = new a(str).a();
        ((Map) b.get()).put(str, aVarA);
        return aVarA;
    }

    public static void a(gh ghVar) {
        if (c == null) {
            c = ghVar;
            if (d) {
                ghVar.a(v.b());
            }
        }
    }

    public static void a(String str, a aVar) {
        if (aVar == null) {
            new Object[1][0] = str;
        } else if (str.equals(aVar.a)) {
            ((Map) b.get()).put(str, aVar);
        } else {
            Object[] objArr = {str, aVar.a};
        }
    }

    public static void a(String str, @Nullable TreeMap treeMap, @Nullable Map map) {
        b(str, treeMap != null ? bh.a((Object) treeMap) : null, map);
    }

    public static void a(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            a = null;
        } else {
            a = new HashSet(collection);
        }
    }

    public static void a(boolean z) {
        if (d != z) {
            d = z;
            if (c != null) {
                if (z) {
                    c.a(v.b());
                } else {
                    c.a();
                }
            }
        }
    }

    public static a b(String str) {
        a aVar = (a) ((Map) b.get()).remove(str);
        return aVar != null ? aVar.b() : new a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, @Nullable String str2, @Nullable Map map) {
        Set set = a;
        if ((set == null || !set.contains(str)) && d && c != null) {
            c.a(v.b(), str, str2, map);
        }
    }

    public static a c(String str) {
        return (a) ((Map) b.get()).get(str);
    }

    public static a d(String str) {
        return (a) ((Map) b.get()).remove(str);
    }

    public static a e(String str) {
        return new a(str);
    }

    protected abstract void a();

    protected abstract void a(long j);

    protected abstract void a(long j, String str, @Nullable String str2, @Nullable Map map);
}
