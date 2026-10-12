package com.nostra13.universalimageloader.cache.disc;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class BaseDiscCache implements DiscCacheAware {
    private static final String ERROR_ARG_NULL = "\"%s\" argument must be not null";
    protected File cacheDir;
    private FileNameGenerator fileNameGenerator;

    public BaseDiscCache(File file) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator());
    }

    public BaseDiscCache(File file, FileNameGenerator fileNameGenerator) {
        if (file == null) {
            throw new IllegalArgumentException(String.format(ERROR_ARG_NULL, "cacheDir"));
        }
        if (fileNameGenerator == null) {
            throw new IllegalArgumentException(String.format(ERROR_ARG_NULL, "fileNameGenerator"));
        }
        this.cacheDir = file;
        this.fileNameGenerator = fileNameGenerator;
    }

    @Override // com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public void clear() {
        File[] fileArrListFiles = this.cacheDir.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }
    }

    @Override // com.nostra13.universalimageloader.cache.disc.DiscCacheAware
    public File get(String str) {
        return new File(this.cacheDir, this.fileNameGenerator.generate(str));
    }
}
