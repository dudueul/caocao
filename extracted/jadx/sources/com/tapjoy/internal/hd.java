package com.tapjoy.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hd {
    private final File a;

    public hd(File file) {
        this.a = file;
    }

    public final synchronized boolean a() {
        boolean z = false;
        synchronized (this) {
            if (b() == null) {
                try {
                    File file = this.a;
                    String string = UUID.randomUUID().toString();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        bg.a(fileOutputStream, string);
                        jz.a(fileOutputStream);
                        if (b() != null) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        jz.a(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e) {
                    try {
                        this.a.delete();
                        throw e;
                    } catch (IOException e2) {
                    }
                }
            }
        }
        return z;
    }

    @Nullable
    final String b() {
        if (this.a.exists()) {
            try {
                String strA = bg.a(this.a, ak.c);
                if (strA.length() > 0) {
                    return strA;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}
