package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface jb extends Closeable, Flushable {
    void a(ir irVar, long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable, com.tapjoy.internal.jc
    void close();

    void flush();
}
