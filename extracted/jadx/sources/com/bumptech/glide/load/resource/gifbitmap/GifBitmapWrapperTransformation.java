package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GifBitmapWrapperTransformation implements Transformation<GifBitmapWrapper> {
    private final Transformation<Bitmap> bitmapTransformation;
    private final Transformation<GifDrawable> gifDataTransformation;

    GifBitmapWrapperTransformation(Transformation<Bitmap> transformation, Transformation<GifDrawable> transformation2) {
        this.bitmapTransformation = transformation;
        this.gifDataTransformation = transformation2;
    }

    public GifBitmapWrapperTransformation(BitmapPool bitmapPool, Transformation<Bitmap> transformation) {
        this(transformation, new GifDrawableTransformation(transformation, bitmapPool));
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return this.bitmapTransformation.getId();
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<GifBitmapWrapper> transform(Resource<GifBitmapWrapper> resource, int i, int i2) {
        Resource<Bitmap> bitmapResource = resource.get().getBitmapResource();
        Resource<GifDrawable> gifResource = resource.get().getGifResource();
        if (bitmapResource != null && this.bitmapTransformation != null) {
            Resource<Bitmap> resourceTransform = this.bitmapTransformation.transform(bitmapResource, i, i2);
            return !bitmapResource.equals(resourceTransform) ? new GifBitmapWrapperResource(new GifBitmapWrapper(resourceTransform, resource.get().getGifResource())) : resource;
        }
        if (gifResource == null || this.gifDataTransformation == null) {
            return resource;
        }
        Resource<GifDrawable> resourceTransform2 = this.gifDataTransformation.transform(gifResource, i, i2);
        return !gifResource.equals(resourceTransform2) ? new GifBitmapWrapperResource(new GifBitmapWrapper(resource.get().getBitmapResource(), resourceTransform2)) : resource;
    }
}
