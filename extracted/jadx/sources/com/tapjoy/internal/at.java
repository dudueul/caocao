package com.tapjoy.internal;

import java.util.AbstractQueue;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class at extends AbstractQueue implements ax {
    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new Iterator() { // from class: com.tapjoy.internal.at.1
            private int b = 0;

            @Override // java.util.Iterator
            public final boolean hasNext() {
                return this.b < at.this.size();
            }

            @Override // java.util.Iterator
            public final Object next() {
                at atVar = at.this;
                int i = this.b;
                this.b = i + 1;
                return atVar.a(i);
            }

            @Override // java.util.Iterator
            public final void remove() {
                if (this.b != 1) {
                    throw new UnsupportedOperationException("For the first element only");
                }
                at.this.b(1);
                this.b = 0;
            }
        };
    }
}
