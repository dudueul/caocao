package com.bumptech.glide.request;

import com.bumptech.glide.load.engine.Resource;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ResourceCallback {
    void onException(Exception exc);

    void onResourceReady(Resource<?> resource);
}
