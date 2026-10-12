package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.BaseDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LimitedAgeDiscCache extends BaseDiscCache {
    private final Map<File, Long> loadingDates;
    private final long maxFileAge;

    public LimitedAgeDiscCache(File file, long j) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator(), j);
    }

    public LimitedAgeDiscCache(File file, FileNameGenerator fileNameGenerator, long j) {
        super(file, fileNameGenerator);
        this.loadingDates = Collections.synchronizedMap(new HashMap());
        this.maxFileAge = 1000 * j;
    }

    @Override // com.nostra13.universalimageloader.cache.disc.BaseDiscCache, com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public File get(String str) {
        boolean z;
        File file = super.get(str);
        if (file.exists()) {
            Long lValueOf = this.loadingDates.get(file);
            if (lValueOf == null) {
                z = false;
                lValueOf = Long.valueOf(file.lastModified());
            } else {
                z = true;
            }
            if (System.currentTimeMillis() - lValueOf.longValue() > this.maxFileAge) {
                file.delete();
                this.loadingDates.remove(file);
            } else if (!z) {
                this.loadingDates.put(file, lValueOf);
            }
        }
        return file;
    }

    @Override // com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public void put(String str, File file) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        file.setLastModified(jCurrentTimeMillis);
        this.loadingDates.put(file, Long.valueOf(jCurrentTimeMillis));
    }
}
