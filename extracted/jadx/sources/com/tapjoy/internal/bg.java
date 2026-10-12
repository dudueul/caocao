package com.tapjoy.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bg {
    @Nullable
    public static String a(File file) {
        try {
            return a(file, ak.c);
        } catch (IOException e) {
            return null;
        }
    }

    public static String a(File file, Charset charset) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return jy.a(new InputStreamReader(fileInputStream, charset));
        } finally {
            jz.a(fileInputStream);
        }
    }

    public static void a(OutputStream outputStream, String str) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ak.c);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }
}
