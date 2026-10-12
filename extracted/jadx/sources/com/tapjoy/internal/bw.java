package com.tapjoy.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bw extends WeakReference {
    public final Object a;

    public bw(Object obj, Object obj2, ReferenceQueue referenceQueue) {
        super(obj2, referenceQueue);
        this.a = obj;
    }
}
