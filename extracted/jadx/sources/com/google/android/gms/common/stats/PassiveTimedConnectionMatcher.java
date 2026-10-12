package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PassiveTimedConnectionMatcher {
    private final long zzym;
    private final int zzyn;
    private final SimpleArrayMap<String, Long> zzyo;

    public PassiveTimedConnectionMatcher() {
        this.zzym = 60000L;
        this.zzyn = 10;
        this.zzyo = new SimpleArrayMap<>(10);
    }

    public PassiveTimedConnectionMatcher(int i, long j) {
        this.zzym = j;
        this.zzyn = i;
        this.zzyo = new SimpleArrayMap<>();
    }

    public Long get(String str) {
        Long l;
        synchronized (this) {
            l = this.zzyo.get(str);
        }
        return l;
    }

    public Long put(String str) {
        Long lPut;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.zzym;
        synchronized (this) {
            long j2 = j;
            while (this.zzyo.size() >= this.zzyn) {
                for (int size = this.zzyo.size() - 1; size >= 0; size--) {
                    if (jElapsedRealtime - this.zzyo.valueAt(size).longValue() > j2) {
                        this.zzyo.removeAt(size);
                    }
                }
                long j3 = j2 / 2;
                Log.w("ConnectionTracker", new StringBuilder(94).append("The max capacity ").append(this.zzyn).append(" is not enough. Current durationThreshold is: ").append(j3).toString());
                j2 = j3;
            }
            lPut = this.zzyo.put(str, Long.valueOf(jElapsedRealtime));
        }
        return lPut;
    }

    public boolean remove(String str) {
        boolean z;
        synchronized (this) {
            z = this.zzyo.remove(str) != null;
        }
        return z;
    }

    public boolean removeByPrefix(String str) {
        boolean z;
        boolean z2;
        synchronized (this) {
            int i = 0;
            z = false;
            while (i < size()) {
                String strKeyAt = this.zzyo.keyAt(i);
                if (strKeyAt == null || !strKeyAt.startsWith(str)) {
                    z2 = z;
                } else {
                    this.zzyo.remove(strKeyAt);
                    z2 = true;
                }
                i++;
                z = z2;
            }
        }
        return z;
    }

    public int size() {
        int size;
        synchronized (this) {
            size = this.zzyo.size();
        }
        return size;
    }
}
