package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class Util {
    private static final char[] HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray();
    private static final char[] SHA_256_CHARS = new char[64];
    private static final char[] SHA_1_CHARS = new char[40];

    /* JADX INFO: renamed from: com.bumptech.glide.util.Util$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private Util() {
    }

    public static void assertBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("YOu must call this method on a background thread");
        }
    }

    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    private static String bytesToHex(byte[] bArr, char[] cArr) {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = HEX_CHAR_ARRAY[i2 >>> 4];
            cArr[(i * 2) + 1] = HEX_CHAR_ARRAY[i2 & 15];
        }
        return new String(cArr);
    }

    public static <T> Queue<T> createQueue(int i) {
        return new ArrayDeque(i);
    }

    public static int getBitmapByteSize(int i, int i2, Bitmap.Config config) {
        return i * i2 * getBytesPerPixel(config);
    }

    @TargetApi(19)
    public static int getBitmapByteSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException e) {
            }
        }
        return bitmap.getHeight() * bitmap.getRowBytes();
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        switch (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()]) {
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                return 4;
        }
    }

    @Deprecated
    public static int getSize(Bitmap bitmap) {
        return getBitmapByteSize(bitmap);
    }

    public static <T> List<T> getSnapshot(Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static boolean isValidDimension(int i) {
        return i > 0 || i == Integer.MIN_VALUE;
    }

    public static boolean isValidDimensions(int i, int i2) {
        return isValidDimension(i) && isValidDimension(i2);
    }

    public static String sha1BytesToHex(byte[] bArr) {
        String strBytesToHex;
        synchronized (SHA_1_CHARS) {
            strBytesToHex = bytesToHex(bArr, SHA_1_CHARS);
        }
        return strBytesToHex;
    }

    public static String sha256BytesToHex(byte[] bArr) {
        String strBytesToHex;
        synchronized (SHA_256_CHARS) {
            strBytesToHex = bytesToHex(bArr, SHA_256_CHARS);
        }
        return strBytesToHex;
    }
}
