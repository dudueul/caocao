package com.nexon.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXFileUtil {
    public static boolean copyFile(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return copyToFile(fileInputStream, file2);
            } finally {
                fileInputStream.close();
            }
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean copyToFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[65536];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i < 0) {
                        fileOutputStream.close();
                        return true;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
