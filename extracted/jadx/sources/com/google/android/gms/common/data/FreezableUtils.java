package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;
import twitter4j.PagableResponseListImpl;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        PagableResponseListImpl pagableResponseListImpl = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            pagableResponseListImpl.add(arrayList.get(i).freeze());
        }
        return pagableResponseListImpl;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        PagableResponseListImpl pagableResponseListImpl = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            pagableResponseListImpl.add(e.freeze());
        }
        return pagableResponseListImpl;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        PagableResponseListImpl pagableResponseListImpl = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            pagableResponseListImpl.add(it.next().freeze());
        }
        return pagableResponseListImpl;
    }
}
