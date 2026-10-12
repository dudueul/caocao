package com.nostra13.universalimageloader.cache.memory;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class BaseMemoryCache<K, V> implements MemoryCacheAware<K, V> {
    private final Map<K, Reference<V>> softMap = Collections.synchronizedMap(new HashMap());

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.softMap.clear();
    }

    protected abstract Reference<V> createReference(V v);

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public V get(K k) {
        Reference<V> reference = this.softMap.get(k);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public Collection<K> keys() {
        HashSet hashSet;
        synchronized (this.softMap) {
            hashSet = new HashSet(this.softMap.keySet());
        }
        return hashSet;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public boolean put(K k, V v) {
        this.softMap.put(k, createReference(v));
        return true;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(K k) {
        this.softMap.remove(k);
    }
}
