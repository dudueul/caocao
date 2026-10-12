package com.bumptech.glide.util;

import android.util.Log;
import java.util.Queue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ByteArrayPool {
    private static final ByteArrayPool BYTE_ARRAY_POOL = new ByteArrayPool();
    private static final int MAX_BYTE_ARRAY_COUNT = 32;
    private static final int MAX_SIZE = 2146304;
    private static final String TAG = "ByteArrayPool";
    private static final int TEMP_BYTES_SIZE = 65536;
    private final Queue<byte[]> tempQueue = Util.createQueue(0);

    private ByteArrayPool() {
    }

    public static ByteArrayPool get() {
        return BYTE_ARRAY_POOL;
    }

    public void clear() {
        synchronized (this.tempQueue) {
            this.tempQueue.clear();
        }
    }

    public byte[] getBytes() {
        byte[] bArrPoll;
        synchronized (this.tempQueue) {
            bArrPoll = this.tempQueue.poll();
        }
        if (bArrPoll == null) {
            bArrPoll = new byte[65536];
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Created temp bytes");
            }
        }
        return bArrPoll;
    }

    public boolean releaseBytes(byte[] bArr) {
        if (bArr.length != 65536) {
            return false;
        }
        boolean z = false;
        synchronized (this.tempQueue) {
            if (this.tempQueue.size() < 32) {
                z = true;
                this.tempQueue.offer(bArr);
            }
        }
        return z;
    }
}
