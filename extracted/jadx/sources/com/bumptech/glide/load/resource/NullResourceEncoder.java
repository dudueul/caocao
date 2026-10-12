package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NullResourceEncoder<T> implements ResourceEncoder<T> {
    private static final NullResourceEncoder<?> NULL_ENCODER = new NullResourceEncoder<>();

    public static <T> NullResourceEncoder<T> get() {
        return (NullResourceEncoder<T>) NULL_ENCODER;
    }

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(Resource<T> resource, OutputStream outputStream) {
        return false;
    }

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }
}
