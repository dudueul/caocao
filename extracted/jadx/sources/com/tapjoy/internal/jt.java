package com.tapjoy.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jt {
    static final jw a = new jw() { // from class: com.tapjoy.internal.jt.1
        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            throw new NoSuchElementException();
        }
    };
    private static final Iterator b = new Iterator() { // from class: com.tapjoy.internal.jt.2
        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new IllegalStateException();
        }
    };

    public static Object a(Iterator it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
