package com.bumptech.glide.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface GlideModule {
    void applyOptions(Context context, GlideBuilder glideBuilder);

    void registerComponents(Context context, Glide glide);
}
