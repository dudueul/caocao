package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class au extends at implements ax, Closeable, Flushable {
    private final ax a;
    private final LinkedList b = new LinkedList();
    private final LinkedList c = new LinkedList();
    private int d;
    private boolean e;

    private au(ax axVar) {
        this.a = axVar;
        this.d = axVar.size();
        this.e = this.d == 0;
    }

    public static au a(ax axVar) {
        return new au(axVar);
    }

    @Override // com.tapjoy.internal.ax
    public final Object a(int i) {
        if (i < 0 || i >= this.d) {
            throw new IndexOutOfBoundsException();
        }
        int size = this.b.size();
        if (i < size) {
            return this.b.get(i);
        }
        if (this.e) {
            return this.c.get(i - size);
        }
        if (i >= this.a.size()) {
            return this.c.get(i - this.a.size());
        }
        Object objA = null;
        while (size <= i) {
            objA = this.a.a(size);
            this.b.add(objA);
            size++;
        }
        if (i + 1 + this.c.size() != this.d) {
            return objA;
        }
        this.e = true;
        return objA;
    }

    @Override // com.tapjoy.internal.ax
    public final void b(int i) {
        if (i <= 0 || i > this.d) {
            throw new IndexOutOfBoundsException();
        }
        if (i <= this.b.size()) {
            aw.a(this.b, i);
            this.a.b(i);
        } else {
            this.b.clear();
            int size = (this.c.size() + i) - this.d;
            if (size < 0) {
                this.a.b(i);
            } else {
                this.a.clear();
                this.e = true;
                if (size > 0) {
                    aw.a(this.c, size);
                }
            }
        }
        this.d -= i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            flush();
        } finally {
            if (this.a instanceof Closeable) {
                ((Closeable) this.a).close();
            }
        }
    }

    protected final void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.c.isEmpty()) {
            return;
        }
        this.a.addAll(this.c);
        if (this.e) {
            this.b.addAll(this.c);
        }
        this.c.clear();
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        this.c.add(obj);
        this.d++;
        return true;
    }

    @Override // java.util.Queue
    public final Object peek() {
        if (this.d <= 0) {
            return null;
        }
        if (!this.b.isEmpty()) {
            return this.b.element();
        }
        if (this.e) {
            return this.c.element();
        }
        Object objPeek = this.a.peek();
        this.b.add(objPeek);
        if (this.d != this.b.size() + this.c.size()) {
            return objPeek;
        }
        this.e = true;
        return objPeek;
    }

    @Override // java.util.Queue
    public final Object poll() {
        Object objRemove;
        if (this.d <= 0) {
            return null;
        }
        if (!this.b.isEmpty()) {
            objRemove = this.b.remove();
            this.a.b(1);
        } else if (this.e) {
            objRemove = this.c.remove();
        } else {
            objRemove = this.a.remove();
            if (this.d == this.c.size() + 1) {
                this.e = true;
            }
        }
        this.d--;
        return objRemove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.d;
    }
}
