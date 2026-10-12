package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hl implements Flushable {
    final Object a = this;
    ax b;
    private final File c;

    public hl(File file) {
        this.c = file;
        try {
            this.b = au.a(new g(file, new bd() { // from class: com.tapjoy.internal.hl.1
                @Override // com.tapjoy.internal.bf
                public final /* bridge */ /* synthetic */ void a(OutputStream outputStream, Object obj) {
                    ev.c.a(outputStream, (ev) obj);
                }

                @Override // com.tapjoy.internal.be
                public final /* synthetic */ Object b(InputStream inputStream) {
                    return (ev) ev.c.a(inputStream);
                }
            }));
        } catch (Exception e) {
            a();
        }
    }

    final void a() {
        this.c.delete();
        if (this.b instanceof Closeable) {
            try {
                ((Closeable) this.b).close();
            } catch (Exception e) {
            }
        }
        this.b = new av(new LinkedList());
    }

    public final void a(int i) {
        synchronized (this.a) {
            try {
                this.b.b(i);
            } catch (Exception e) {
                a();
            }
        }
    }

    public final int b() {
        int size;
        synchronized (this.a) {
            try {
                size = this.b.size();
            } catch (Exception e) {
                a();
                size = 0;
            }
        }
        return size;
    }

    @Nullable
    public final ev b(int i) {
        ev evVar;
        synchronized (this.a) {
            try {
                evVar = (ev) this.b.a(i);
            } catch (Exception e) {
                a();
                evVar = null;
            }
        }
        return evVar;
    }

    public final boolean c() {
        boolean zIsEmpty;
        synchronized (this.a) {
            try {
                zIsEmpty = this.b.isEmpty();
            } catch (Exception e) {
                a();
                zIsEmpty = true;
            }
        }
        return zIsEmpty;
    }

    @Override // java.io.Flushable
    public final void flush() {
        synchronized (this.a) {
            if (this.b instanceof Flushable) {
                try {
                    ((Flushable) this.b).flush();
                } catch (Exception e) {
                    a();
                }
            }
        }
    }
}
