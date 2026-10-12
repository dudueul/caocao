package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class BaseGlideUrlLoader<T> implements StreamModelLoader<T> {
    private final ModelLoader<GlideUrl, InputStream> concreteLoader;
    private final ModelCache<T, GlideUrl> modelCache;

    public BaseGlideUrlLoader(Context context) {
        this(context, (ModelCache) null);
    }

    public BaseGlideUrlLoader(Context context, ModelCache<T, GlideUrl> modelCache) {
        this((ModelLoader<GlideUrl, InputStream>) Glide.buildModelLoader(GlideUrl.class, InputStream.class, context), modelCache);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this(modelLoader, (ModelCache) null);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader, ModelCache<T, GlideUrl> modelCache) {
        this.concreteLoader = modelLoader;
        this.modelCache = modelCache;
    }

    protected Headers getHeaders(T t, int i, int i2) {
        return Headers.DEFAULT;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<InputStream> getResourceFetcher(T t, int i, int i2) {
        GlideUrl glideUrl = this.modelCache != null ? this.modelCache.get(t, i, i2) : null;
        if (glideUrl == null) {
            String url = getUrl(t, i, i2);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            glideUrl = new GlideUrl(url, getHeaders(t, i, i2));
            if (this.modelCache != null) {
                this.modelCache.put(t, i, i2, glideUrl);
            }
        }
        return this.concreteLoader.getResourceFetcher(glideUrl, i, i2);
    }

    protected abstract String getUrl(T t, int i, int i2);
}
