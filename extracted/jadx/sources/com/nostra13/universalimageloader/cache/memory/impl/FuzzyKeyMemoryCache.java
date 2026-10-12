package com.nostra13.universalimageloader.cache.memory.impl;

import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.Collection;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FuzzyKeyMemoryCache<K, V> implements MemoryCacheAware<K, V> {
    private final MemoryCacheAware<K, V> cache;
    private final Comparator<K> keyComparator;

    public FuzzyKeyMemoryCache(MemoryCacheAware<K, V> memoryCacheAware, Comparator<K> comparator) {
        this.cache = memoryCacheAware;
        this.keyComparator = comparator;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.cache.clear();
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public V get(K k) {
        return this.cache.get(k);
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public Collection<K> keys() {
        return this.cache.keys();
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public boolean put(K k, V v) {
        synchronized (this.cache) {
            K k2 = null;
            for (K k3 : this.cache.keys()) {
                if (this.keyComparator.compare(k, k3) == 0) {
                    k2 = k3;
                    break;
                }
            }
            if (k2 != null) {
                this.cache.remove(k2);
            }
        }
        return this.cache.put(k, v);
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(K k) {
        this.cache.remove(k);
    }
}
