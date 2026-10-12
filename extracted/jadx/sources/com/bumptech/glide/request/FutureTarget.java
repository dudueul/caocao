package com.bumptech.glide.request;

import com.bumptech.glide.request.target.Target;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface FutureTarget<R> extends Future<R>, Target<R> {
    void clear();
}
