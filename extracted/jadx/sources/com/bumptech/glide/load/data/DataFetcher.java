package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface DataFetcher<T> {
    void cancel();

    void cleanup();

    String getId();

    T loadData(Priority priority) throws Exception;
}
