package com.dikra.simple_unity_diskutils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.math.BigInteger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DiskUtils {
    private static final long MEGA_BYTE = 1048576;

    public static int busySpace(boolean z) {
        BigInteger bigIntegerMultiply;
        BigInteger bigIntegerMultiply2;
        StatFs stats = getStats(z);
        if (Build.VERSION.SDK_INT < 18) {
            bigIntegerMultiply = BigInteger.valueOf(stats.getBlockCount()).multiply(BigInteger.valueOf(stats.getBlockSize()));
            bigIntegerMultiply2 = BigInteger.valueOf(stats.getFreeBlocks()).multiply(BigInteger.valueOf(stats.getBlockSize()));
        } else {
            bigIntegerMultiply = BigInteger.valueOf(stats.getBlockCountLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong()));
            bigIntegerMultiply2 = BigInteger.valueOf(stats.getFreeBlocksLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong()));
        }
        return bigIntegerMultiply.subtract(bigIntegerMultiply2).divide(BigInteger.valueOf(1048576L)).intValue();
    }

    public static int freeSpace(boolean z) {
        long availableBlocksLong;
        long blockSizeLong;
        StatFs stats = getStats(z);
        if (Build.VERSION.SDK_INT < 18) {
            availableBlocksLong = stats.getAvailableBlocks();
            blockSizeLong = stats.getBlockSize();
        } else {
            availableBlocksLong = stats.getAvailableBlocksLong();
            blockSizeLong = stats.getBlockSizeLong();
        }
        return BigInteger.valueOf(availableBlocksLong).multiply(BigInteger.valueOf(blockSizeLong)).divide(BigInteger.valueOf(1048576L)).intValue();
    }

    private static StatFs getStats(boolean z) {
        return new StatFs(z ? Environment.getExternalStorageDirectory().getAbsolutePath() : Environment.getRootDirectory().getAbsolutePath());
    }

    public static int totalSpace(boolean z) {
        long blockCountLong;
        long blockSizeLong;
        StatFs stats = getStats(z);
        if (Build.VERSION.SDK_INT < 18) {
            blockCountLong = stats.getBlockCount();
            blockSizeLong = stats.getBlockSize();
        } else {
            blockCountLong = stats.getBlockCountLong();
            blockSizeLong = stats.getBlockSizeLong();
        }
        return BigInteger.valueOf(blockCountLong).multiply(BigInteger.valueOf(blockSizeLong)).divide(BigInteger.valueOf(1048576L)).intValue();
    }
}
