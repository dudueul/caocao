package com.nostra13.universalimageloader.cache.memory;

import com.nostra13.universalimageloader.utils.L;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class LimitedMemoryCache<K, V> extends BaseMemoryCache<K, V> {
    private static final int MAX_NORMAL_CACHE_SIZE = 16777216;
    private static final int MAX_NORMAL_CACHE_SIZE_IN_MB = 16;
    private final int sizeLimit;
    private final List<V> hardCache = Collections.synchronizedList(new LinkedList());
    private final AtomicInteger cacheSize = new AtomicInteger();

    public LimitedMemoryCache(int i) {
        this.sizeLimit = i;
        if (i > 16777216) {
            L.w("You set too large memory cache size (more than %1$d Mb)", 16);
        }
    }

    @Override // com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.hardCache.clear();
        this.cacheSize.set(0);
        super.clear();
    }

    protected abstract int getSize(V v);

    protected int getSizeLimit() {
        return this.sizeLimit;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public boolean put(K k, V v) {
        boolean z = false;
        int size = getSize(v);
        int sizeLimit = getSizeLimit();
        int iAddAndGet = this.cacheSize.get();
        if (size < sizeLimit) {
            while (iAddAndGet + size > sizeLimit) {
                V vRemoveNext = removeNext();
                if (this.hardCache.remove(vRemoveNext)) {
                    iAddAndGet = this.cacheSize.addAndGet(-getSize(vRemoveNext));
                }
            }
            this.hardCache.add(v);
            this.cacheSize.addAndGet(size);
            z = true;
        }
        super.put(k, v);
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(K k) {
        Object obj = super.get(k);
        if (obj != null && this.hardCache.remove(obj)) {
            this.cacheSize.addAndGet(-getSize(obj));
        }
        super.remove(k);
    }

    protected abstract V removeNext();
}
