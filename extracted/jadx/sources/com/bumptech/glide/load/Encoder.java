package com.bumptech.glide.load;

import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Encoder<T> {
    boolean encode(T t, OutputStream outputStream);

    String getId();
}
