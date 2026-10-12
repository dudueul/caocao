package com.nostra13.universalimageloader.cache.memory.impl;

import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LimitedAgeMemoryCache<K, V> implements MemoryCacheAware<K, V> {
    private final MemoryCacheAware<K, V> cache;
    private final Map<K, Long> loadingDates = Collections.synchronizedMap(new HashMap());
    private final long maxAge;

    public LimitedAgeMemoryCache(MemoryCacheAware<K, V> memoryCacheAware, long j) {
        this.cache = memoryCacheAware;
        this.maxAge = 1000 * j;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.cache.clear();
        this.loadingDates.clear();
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public V get(K k) {
        Long l = this.loadingDates.get(k);
        if (l != null && System.currentTimeMillis() - l.longValue() > this.maxAge) {
            this.cache.remove(k);
            this.loadingDates.remove(k);
        }
        return this.cache.get(k);
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public Collection<K> keys() {
        return this.cache.keys();
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public boolean put(K k, V v) {
        boolean zPut = this.cache.put(k, v);
        if (zPut) {
            this.loadingDates.put(k, Long.valueOf(System.currentTimeMillis()));
        }
        return zPut;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(K k) {
        this.cache.remove(k);
        this.loadingDates.remove(k);
    }
}
