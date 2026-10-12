package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class ep extends AbstractList implements Serializable, RandomAccess {
    List a;
    private final List b;

    ep(List list) {
        this.b = list;
        this.a = list;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        this.a.add(i, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return this.a.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.remove(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.set(i, obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.a.size();
    }
}
