package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class iv {
    static final Logger a = Logger.getLogger(iv.class.getName());

    private iv() {
    }

    public static is a(jb jbVar) {
        if (jbVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        return new iw(jbVar);
    }

    public static it a(jc jcVar) {
        if (jcVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        return new ix(jcVar);
    }

    public static jb a(final OutputStream outputStream) {
        final jd jdVar = new jd();
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        return new jb() { // from class: com.tapjoy.internal.iv.1
            @Override // com.tapjoy.internal.jb
            public final void a(ir irVar, long j) throws IOException {
                je.a(irVar.b, 0L, j);
                while (j > 0) {
                    jdVar.a();
                    iy iyVar = irVar.a;
                    int iMin = (int) Math.min(j, iyVar.c - iyVar.b);
                    outputStream.write(iyVar.a, iyVar.b, iMin);
                    iyVar.b += iMin;
                    j -= (long) iMin;
                    irVar.b -= (long) iMin;
                    if (iyVar.b == iyVar.c) {
                        irVar.a = iyVar.a();
                        iz.a(iyVar);
                    }
                }
            }

            @Override // com.tapjoy.internal.jb, java.io.Closeable, java.lang.AutoCloseable, com.tapjoy.internal.jc
            public final void close() throws IOException {
                outputStream.close();
            }

            @Override // com.tapjoy.internal.jb, java.io.Flushable
            public final void flush() throws IOException {
                outputStream.flush();
            }

            public final String toString() {
                return "sink(" + outputStream + ")";
            }
        };
    }

    public static jc a(final InputStream inputStream) {
        final jd jdVar = new jd();
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        return new jc() { // from class: com.tapjoy.internal.iv.2
            @Override // com.tapjoy.internal.jc
            public final long b(ir irVar, long j) throws IOException {
                if (j < 0) {
                    throw new IllegalArgumentException("byteCount < 0: " + j);
                }
                if (j == 0) {
                    return 0L;
                }
                try {
                    jdVar.a();
                    iy iyVarC = irVar.c(1);
                    int i = inputStream.read(iyVarC.a, iyVarC.c, (int) Math.min(j, 8192 - iyVarC.c));
                    if (i == -1) {
                        return -1L;
                    }
                    iyVarC.c += i;
                    irVar.b += (long) i;
                    return i;
                } catch (AssertionError e) {
                    if (iv.a(e)) {
                        throw new IOException(e);
                    }
                    throw e;
                }
            }

            @Override // com.tapjoy.internal.jc, java.lang.AutoCloseable
            public final void close() throws IOException {
                inputStream.close();
            }

            public final String toString() {
                return "source(" + inputStream + ")";
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
