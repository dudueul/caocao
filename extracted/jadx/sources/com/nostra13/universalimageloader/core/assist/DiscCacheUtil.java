package com.nostra13.universalimageloader.core.assist;

import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class DiscCacheUtil {
    private DiscCacheUtil() {
    }

    public static File findInCache(String str, DiscCacheAware discCacheAware) {
        File file = discCacheAware.get(str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static boolean removeFromCache(String str, DiscCacheAware discCacheAware) {
        return discCacheAware.get(str).delete();
    }
}
