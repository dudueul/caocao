package com.tapjoy.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class bn implements bk, bp {
    private HashMap a;

    public static abstract class a {
        private static a a;

        public static a a() {
            a aVar = a;
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = bo.a;
            a = aVar2;
            return aVar2;
        }

        public final bn a(InputStream inputStream) {
            return a(new InputStreamReader(inputStream, jm.c));
        }

        public bn a(Reader reader) {
            return a(jy.a(reader));
        }

        public bn a(String str) {
            return a(new ByteArrayInputStream(str.getBytes(jm.c.name())));
        }
    }

    public static bn a(InputStream inputStream) {
        return a.a().a(inputStream);
    }

    private void a(List list) {
        f();
        while (j()) {
            list.add(u());
        }
        g();
    }

    public static bn b(String str) {
        return a.a().a(str);
    }

    private static URI d(String str) throws bv {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw new bv(e);
        }
    }

    private boolean t() {
        if (k() != bs.NULL) {
            return false;
        }
        o();
        return true;
    }

    private Object u() {
        bs bsVarK = k();
        switch (bsVarK) {
            case BEGIN_ARRAY:
                return c();
            case BEGIN_OBJECT:
                return d();
            case NULL:
                o();
                return null;
            case BOOLEAN:
                return Boolean.valueOf(n());
            case NUMBER:
                return new ci(m());
            case STRING:
                return m();
            default:
                throw new IllegalStateException("Expected a value but was " + bsVarK);
        }
    }

    @Nullable
    public final Object a(bi biVar) {
        if (t()) {
            return null;
        }
        return biVar.a(this);
    }

    @Override // com.tapjoy.internal.bk
    public final Object a(String str) {
        if (this.a != null) {
            return this.a.get(str);
        }
        return null;
    }

    @Override // com.tapjoy.internal.bk
    public final void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj);
    }

    public final void a(List list, bi biVar) {
        f();
        while (j()) {
            list.add(biVar.a(this));
        }
        g();
    }

    public final void a(Map map) {
        h();
        while (j()) {
            map.put(l(), u());
        }
        i();
    }

    public final boolean a() {
        return k() == bs.BEGIN_OBJECT;
    }

    public final String b() {
        if (t()) {
            return null;
        }
        return m();
    }

    public final String c(String str) {
        return t() ? str : m();
    }

    public final List c() {
        LinkedList linkedList = new LinkedList();
        a(linkedList);
        return linkedList;
    }

    public final Map d() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(linkedHashMap);
        return linkedHashMap;
    }

    public final URL e() {
        URI uri = (URI) a("BASE_URI");
        return uri != null ? uri.resolve(d(m())).toURL() : new URL(m());
    }
}
