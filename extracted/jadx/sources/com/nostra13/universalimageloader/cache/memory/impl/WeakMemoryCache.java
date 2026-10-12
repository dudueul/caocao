package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.BaseMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WeakMemoryCache extends BaseMemoryCache<String, Bitmap> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nostra13.universalimageloader.cache.memory.BaseMemoryCache
    public Reference<Bitmap> createReference(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
