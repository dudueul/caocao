package com.tapjoy.internal;

import java.io.IOException;
import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class eh implements Serializable {
    transient int a = 0;
    protected transient int b = 0;
    private final transient ej c;
    private final transient iu d;

    public static abstract class a {
        ir a;
        el b;

        protected a() {
        }

        public final a a(int i, eg egVar, Object obj) {
            if (this.b == null) {
                this.a = new ir();
                this.b = new el(this.a);
            }
            try {
                egVar.a().a(this.b, i, obj);
                return this;
            } catch (IOException e) {
                throw new AssertionError();
            }
        }

        public final a a(iu iuVar) {
            if (iuVar.c() > 0) {
                if (this.b == null) {
                    this.a = new ir();
                    this.b = new el(this.a);
                }
                try {
                    this.b.a(iuVar);
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        public final iu a() {
            return this.a != null ? new iu(this.a.clone().g()) : iu.b;
        }
    }

    protected eh(ej ejVar, iu iuVar) {
        if (ejVar == null) {
            throw new NullPointerException("adapter == null");
        }
        if (iuVar == null) {
            throw new NullPointerException("unknownFields == null");
        }
        this.c = ejVar;
        this.d = iuVar;
    }

    public final iu a() {
        iu iuVar = this.d;
        return iuVar != null ? iuVar : iu.b;
    }

    public String toString() {
        return ej.c(this);
    }
}
