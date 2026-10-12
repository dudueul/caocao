package com.tapjoy.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class av implements ax {
    private final List a;

    public av(List list) {
        this.a = list;
    }

    @Override // com.tapjoy.internal.ax
    public final Object a(int i) {
        return this.a.get(i);
    }

    @Override // java.util.Queue, java.util.Collection
    public final boolean add(Object obj) {
        return this.a.add(obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        return this.a.addAll(collection);
    }

    @Override // com.tapjoy.internal.ax
    public final void b(int i) {
        aw.a(this.a, i);
    }

    @Override // java.util.Collection
    public final void clear() {
        this.a.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.a.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.a.containsAll(collection);
    }

    @Override // java.util.Queue
    public final Object element() {
        Object objPeek = peek();
        if (objPeek != null) {
            return objPeek;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.a.iterator();
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        return this.a.add(obj);
    }

    @Override // java.util.Queue
    public final Object peek() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.get(0);
    }

    @Override // java.util.Queue
    public final Object poll() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.remove(0);
    }

    @Override // java.util.Queue
    public final Object remove() {
        Object objPoll = poll();
        if (objPoll != null) {
            return objPoll;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.a.removeAll(collection);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.a.retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return this.a.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return this.a.toArray();
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.a.toArray(objArr);
    }
}
