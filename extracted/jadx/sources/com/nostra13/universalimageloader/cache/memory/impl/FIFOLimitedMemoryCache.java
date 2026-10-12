package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FIFOLimitedMemoryCache extends LimitedMemoryCache<String, Bitmap> {
    private final List<Bitmap> queue;

    public FIFOLimitedMemoryCache(int i) {
        super(i);
        this.queue = Collections.synchronizedList(new LinkedList());
    }

    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache, com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        this.queue.clear();
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
        this.queue.add(bitmap);
        return true;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache, com.nostra13.universalimageloader.cache.memory.BaseMemoryCache, com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void remove(String str) {
        Bitmap bitmap = (Bitmap) super.get(str);
        if (bitmap != null) {
            this.queue.remove(bitmap);
        }
        super.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache
    public Bitmap removeNext() {
        return this.queue.remove(0);
    }
}
