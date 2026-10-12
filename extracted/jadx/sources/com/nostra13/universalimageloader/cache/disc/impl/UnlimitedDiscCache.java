package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.BaseDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class UnlimitedDiscCache extends BaseDiscCache {
    public UnlimitedDiscCache(File file) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator());
    }

    public UnlimitedDiscCache(File file, FileNameGenerator fileNameGenerator) {
        super(file, fileNameGenerator);
    }

    @Override // com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public void put(String str, File file) {
    }
}
