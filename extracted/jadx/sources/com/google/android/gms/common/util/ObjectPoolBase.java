package com.google.android.gms.common.util;

import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ObjectPoolBase<T> {
    private final ArrayList<T> zzaag;
    private final int zzaah;

    public ObjectPoolBase(int i) {
        this.zzaag = new ArrayList<>(i);
        this.zzaah = i;
    }

    public final T aquire() {
        T tRemove;
        synchronized (this.zzaag) {
            int size = this.zzaag.size();
            tRemove = size > 0 ? this.zzaag.remove(size - 1) : newObject();
        }
        return tRemove;
    }

    protected boolean cleanUpObject(T t) {
        return true;
    }

    protected abstract T newObject();

    public final boolean release(T t) {
        boolean z = false;
        synchronized (this.zzaag) {
            int size = this.zzaag.size();
            for (int i = 0; i < size; i++) {
                if (this.zzaag.get(i) == t) {
                    String strValueOf = String.valueOf(t);
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 25).append("Object released already: ").append(strValueOf).toString());
                }
            }
            if (size < this.zzaah && cleanUpObject(t)) {
                this.zzaag.add(t);
                z = true;
            }
            return z;
        }
    }
}
