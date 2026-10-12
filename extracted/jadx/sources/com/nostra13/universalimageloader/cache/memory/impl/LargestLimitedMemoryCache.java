package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LargestLimitedMemoryCache extends LimitedMemoryCache<String, Bitmap> {
    private final Map<Bitmap, Integer> valueSizes;

    public LargestLimitedMemoryCache(int i) {
        super(i);
        this.valueSizes = Collections.synchronizedMap(new HashMap());
    }

    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache, com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.valueSizes.clear();
        super.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nostra13.universalimageloader.cache.memory.BaseMemoryCache
    public Reference<Bitmap> createReference(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache
    public int getSize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache, com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public boolean put(String str, Bitmap bitmap) {
        if (!super.put(str, bitmap)) {
            return false;
        }
        this.valueSizes.put(bitmap, Integer.valueOf(getSize(bitmap)));
        return true;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache, com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(String str) {
        Bitmap bitmap = (Bitmap) super.get(str);
        if (bitmap != null) {
            this.valueSizes.remove(bitmap);
        }
        super.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache
    public Bitmap removeNext() {
        Integer value = null;
        Bitmap key = null;
        Set<Map.Entry<Bitmap, Integer>> setEntrySet = this.valueSizes.entrySet();
        synchronized (this.valueSizes) {
            for (Map.Entry<Bitmap, Integer> entry : setEntrySet) {
                if (key == null) {
                    key = entry.getKey();
                    value = entry.getValue();
                } else {
                    Integer value2 = entry.getValue();
                    if (value2.intValue() > value.intValue()) {
                        value = value2;
                        key = entry.getKey();
                    }
                }
            }
        }
        this.valueSizes.remove(key);
        return key;
    }
}
