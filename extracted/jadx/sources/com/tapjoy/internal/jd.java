package com.tapjoy.internal;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class jd {
    public static final jd a = new jd() { // from class: com.tapjoy.internal.jd.1
        @Override // com.tapjoy.internal.jd
        public final void a() {
        }
    };
    private boolean b;
    private long c;

    public void a() throws InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.b && this.c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
