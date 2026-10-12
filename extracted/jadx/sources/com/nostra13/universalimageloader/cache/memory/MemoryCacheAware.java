package com.nostra13.universalimageloader.cache.memory;

import java.util.Collection;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface MemoryCacheAware<K, V> {
    void clear();

    V get(K k);

    Collection<K> keys();

    boolean put(K k, V v);

    void remove(K k);
}
