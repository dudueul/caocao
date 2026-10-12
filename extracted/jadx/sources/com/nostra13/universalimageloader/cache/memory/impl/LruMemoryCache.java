package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LruMemoryCache implements MemoryCacheAware<String, Bitmap> {
    private final LinkedHashMap<String, Bitmap> map;
    private final int maxSize;
    private int size;

    public LruMemoryCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int sizeOf(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    private void trimToSize(int i) {
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
                    break;
                }
                if (this.size <= i || this.map.isEmpty()) {
                    return;
                }
                Map.Entry<String, Bitmap> next = this.map.entrySet().iterator().next();
                if (next == null) {
                    return;
                }
                String key = next.getKey();
                Bitmap value = next.getValue();
                this.map.remove(key);
                this.size -= sizeOf(key, value);
            }
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public void clear() {
        trimToSize(-1);
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public final Bitmap get(String str) {
        Bitmap bitmap;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            bitmap = this.map.get(str);
        }
        return bitmap;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public Collection<String> keys() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.map.keySet());
        }
        return hashSet;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public final boolean put(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.size += sizeOf(str, bitmap);
            Bitmap bitmapPut = this.map.put(str, bitmap);
            if (bitmapPut != null) {
                this.size -= sizeOf(str, bitmapPut);
            }
        }
        trimToSize(this.maxSize);
        return true;
    }

    @Override // com.nostra13.universalimageloader.cache.memory.MemoryCacheAware
    public final void remove(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Bitmap bitmapRemove = this.map.remove(str);
            if (bitmapRemove != null) {
                this.size -= sizeOf(str, bitmapRemove);
            }
        }
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", Integer.valueOf(this.maxSize));
    }
}
