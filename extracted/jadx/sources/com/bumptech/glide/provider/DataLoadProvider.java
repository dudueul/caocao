package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface DataLoadProvider<T, Z> {
    ResourceDecoder<File, Z> getCacheDecoder();

    ResourceEncoder<Z> getEncoder();

    ResourceDecoder<T, Z> getSourceDecoder();

    Encoder<T> getSourceEncoder();
}
