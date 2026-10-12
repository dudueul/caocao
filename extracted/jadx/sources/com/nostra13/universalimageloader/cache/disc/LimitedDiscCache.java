package com.nostra13.universalimageloader.cache.disc;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class LimitedDiscCache extends BaseDiscCache {
    private static final int INVALID_SIZE = -1;
    private final AtomicInteger cacheSize;
    private final Map<File, Long> lastUsageDates;
    private final int sizeLimit;

    public LimitedDiscCache(File file, int i) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator(), i);
    }

    public LimitedDiscCache(File file, FileNameGenerator fileNameGenerator, int i) {
        super(file, fileNameGenerator);
        this.lastUsageDates = Collections.synchronizedMap(new HashMap());
        this.sizeLimit = i;
        this.cacheSize = new AtomicInteger();
        calculateCacheSizeAndFillUsageMap();
    }

    private void calculateCacheSizeAndFillUsageMap() {
        new Thread(new Runnable() { // from class: com.nostra13.universalimageloader.cache.disc.LimitedDiscCache.1
            @Override // java.lang.Runnable
            public void run() {
                int size = 0;
                File[] fileArrListFiles = LimitedDiscCache.this.cacheDir.listFiles();
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        size += LimitedDiscCache.this.getSize(file);
                        LimitedDiscCache.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                    }
                    LimitedDiscCache.this.cacheSize.set(size);
                }
            }
        }).start();
    }

    private int removeNext() {
        if (this.lastUsageDates.isEmpty()) {
            return -1;
        }
        Long value = null;
        File key = null;
        Set<Map.Entry<File, Long>> setEntrySet = this.lastUsageDates.entrySet();
        synchronized (this.lastUsageDates) {
            for (Map.Entry<File, Long> entry : setEntrySet) {
                if (key == null) {
                    key = entry.getKey();
                    value = entry.getValue();
                } else {
                    Long value2 = entry.getValue();
                    if (value2.longValue() < value.longValue()) {
                        value = value2;
                        key = entry.getKey();
                    }
                }
            }
        }
        if (key == null) {
            return 0;
        }
        if (!key.exists()) {
            this.lastUsageDates.remove(key);
            return 0;
        }
        int size = getSize(key);
        if (!key.delete()) {
            return size;
        }
        this.lastUsageDates.remove(key);
        return size;
    }

    @Override // com.nostra13.universalimageloader.cache.disc.BaseDiscCache, com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public void clear() {
        this.lastUsageDates.clear();
        this.cacheSize.set(0);
        super.clear();
    }

    @Override // com.nostra13.universalimageloader.cache.disc.BaseDiscCache, com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public File get(String str) {
        File file = super.get(str);
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(lValueOf.longValue());
        this.lastUsageDates.put(file, lValueOf);
        return file;
    }

    protected abstract int getSize(File file);

    @Override // com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public void put(String str, File file) {
        int iRemoveNext;
        int size = getSize(file);
        int iAddAndGet = this.cacheSize.get();
        while (iAddAndGet + size > this.sizeLimit && (iRemoveNext = removeNext()) != -1) {
            iAddAndGet = this.cacheSize.addAndGet(-iRemoveNext);
        }
        this.cacheSize.addAndGet(size);
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(lValueOf.longValue());
        this.lastUsageDates.put(file, lValueOf);
    }
}
