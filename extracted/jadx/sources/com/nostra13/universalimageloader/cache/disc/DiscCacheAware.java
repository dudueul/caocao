package com.nostra13.universalimageloader.cache.disc;

import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface DiscCacheAware {
    void clear();

    File get(String str);

    void put(String str, File file);
}
