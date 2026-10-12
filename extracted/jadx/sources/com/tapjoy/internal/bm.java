package com.tapjoy.internal;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bm implements bl {
    public final String a;

    public bm(String str) {
        this.a = str;
    }

    @Override // com.tapjoy.internal.bl
    public final void a(Writer writer) throws IOException {
        writer.write(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bm) {
            return this.a.equals(((bm) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
