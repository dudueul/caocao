package com.tapjoy.internal;

import java.io.IOException;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jy {
    private static long a(Readable readable, Appendable appendable) throws IOException {
        CharBuffer charBufferAllocate = CharBuffer.allocate(2048);
        long j = 0;
        while (true) {
            int i = readable.read(charBufferAllocate);
            if (i == -1) {
                return j;
            }
            charBufferAllocate.flip();
            appendable.append(charBufferAllocate, 0, i);
            j += (long) i;
        }
    }

    public static String a(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        a(readable, sb);
        return sb.toString();
    }
}
