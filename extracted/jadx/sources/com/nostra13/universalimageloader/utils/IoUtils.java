package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class IoUtils {
    public static final int CONTINUE_LOADING_PERCENTAGE = 75;
    public static final int DEFAULT_BUFFER_SIZE = 32768;

    public interface CopyListener {
        boolean onBytesCopied(int i, int i2);
    }

    private IoUtils() {
    }

    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }

    public static boolean copyStream(InputStream inputStream, OutputStream outputStream, CopyListener copyListener) throws IOException {
        return copyStream(inputStream, outputStream, copyListener, 32768);
    }

    public static boolean copyStream(InputStream inputStream, OutputStream outputStream, CopyListener copyListener, int i) throws IOException {
        int i2 = 0;
        int iAvailable = inputStream.available();
        byte[] bArr = new byte[i];
        if (shouldStopLoading(copyListener, 0, iAvailable)) {
            return false;
        }
        do {
            int i3 = inputStream.read(bArr, 0, i);
            if (i3 == -1) {
                return true;
            }
            outputStream.write(bArr, 0, i3);
            i2 += i3;
        } while (!shouldStopLoading(copyListener, i2, iAvailable));
        return false;
    }

    private static boolean shouldStopLoading(CopyListener copyListener, int i, int i2) {
        return (copyListener == null || copyListener.onBytesCopied(i, i2) || (i * 100) / i2 >= 75) ? false : true;
    }
}
