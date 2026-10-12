package com.tapjoy.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class az extends AbstractMap {
    private final HashMap a = new HashMap();
    private final bx b = new bx();

    public static az a() {
        return new az();
    }

    private static Object a(bw bwVar) {
        if (bwVar != null) {
            return bwVar.get();
        }
        return null;
    }

    private void b() {
        while (true) {
            bw bwVarA = this.b.a();
            if (bwVarA == null) {
                return;
            } else {
                this.a.remove(bwVarA.a);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.a.clear();
        while (this.b.a() != null) {
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        b();
        return this.a.containsKey(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        b();
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            if (obj.equals(((bw) it.next()).get())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        b();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        b();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        b();
        return a((bw) this.a.get(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        b();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        b();
        return this.a.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        b();
        return a((bw) this.a.put(obj, new bw(obj, obj2, this.b)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        b();
        return a((bw) this.a.remove(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        b();
        return this.a.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        b();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        b();
        throw new UnsupportedOperationException();
    }
}
