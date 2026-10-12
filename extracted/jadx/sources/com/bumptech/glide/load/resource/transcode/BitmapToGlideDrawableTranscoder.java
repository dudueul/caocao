package com.bumptech.glide.load.resource.transcode;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BitmapToGlideDrawableTranscoder implements ResourceTranscoder<Bitmap, GlideDrawable> {
    private final GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;

    public BitmapToGlideDrawableTranscoder(Context context) {
        this(new GlideBitmapDrawableTranscoder(context));
    }

    public BitmapToGlideDrawableTranscoder(GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder) {
        this.glideBitmapDrawableTranscoder = glideBitmapDrawableTranscoder;
    }

    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    public String getId() {
        return this.glideBitmapDrawableTranscoder.getId();
    }

    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    public Resource<GlideDrawable> transcode(Resource<Bitmap> resource) {
        return this.glideBitmapDrawableTranscoder.transcode(resource);
    }
}
