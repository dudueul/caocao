package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ChildLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R>, Cloneable {
    private ResourceDecoder<File, Z> cacheDecoder;
    private ResourceEncoder<Z> encoder;
    private final LoadProvider<A, T, Z, R> parent;
    private ResourceDecoder<T, Z> sourceDecoder;
    private Encoder<T> sourceEncoder;
    private ResourceTranscoder<Z, R> transcoder;

    public ChildLoadProvider(LoadProvider<A, T, Z, R> loadProvider) {
        this.parent = loadProvider;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ChildLoadProvider<A, T, Z, R> m5clone() {
        try {
            return (ChildLoadProvider) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.bumptech.glide.provider.DataLoadProvider
    public ResourceDecoder<File, Z> getCacheDecoder() {
        return this.cacheDecoder != null ? this.cacheDecoder : this.parent.getCacheDecoder();
    }

    @Override // com.bumptech.glide.provider.DataLoadProvider
    public ResourceEncoder<Z> getEncoder() {
        return this.encoder != null ? this.encoder : this.parent.getEncoder();
    }

    @Override // com.bumptech.glide.provider.LoadProvider
    public ModelLoader<A, T> getModelLoader() {
        return this.parent.getModelLoader();
    }

    @Override // com.bumptech.glide.provider.DataLoadProvider
    public ResourceDecoder<T, Z> getSourceDecoder() {
        return this.sourceDecoder != null ? this.sourceDecoder : this.parent.getSourceDecoder();
    }

    @Override // com.bumptech.glide.provider.DataLoadProvider
    public Encoder<T> getSourceEncoder() {
        return this.sourceEncoder != null ? this.sourceEncoder : this.parent.getSourceEncoder();
    }

    @Override // com.bumptech.glide.provider.LoadProvider
    public ResourceTranscoder<Z, R> getTranscoder() {
        return this.transcoder != null ? this.transcoder : this.parent.getTranscoder();
    }

    public void setCacheDecoder(ResourceDecoder<File, Z> resourceDecoder) {
        this.cacheDecoder = resourceDecoder;
    }

    public void setEncoder(ResourceEncoder<Z> resourceEncoder) {
        this.encoder = resourceEncoder;
    }

    public void setSourceDecoder(ResourceDecoder<T, Z> resourceDecoder) {
        this.sourceDecoder = resourceDecoder;
    }

    public void setSourceEncoder(Encoder<T> encoder) {
        this.sourceEncoder = encoder;
    }

    public void setTranscoder(ResourceTranscoder<Z, R> resourceTranscoder) {
        this.transcoder = resourceTranscoder;
    }
}
