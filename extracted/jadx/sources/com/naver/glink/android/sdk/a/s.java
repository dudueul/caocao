package com.naver.glink.android.sdk.a;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/* JADX INFO: compiled from: ScaleErrorImageViewTarget.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class s extends GlideDrawableImageViewTarget {
    ImageView.ScaleType a;

    public s(ImageView imageView, ImageView.ScaleType scaleType) {
        super(imageView);
        this.a = scaleType;
    }

    @NonNull
    public static s a(ImageView imageView) {
        return new s(imageView, ImageView.ScaleType.CENTER);
    }

    @Override // com.bumptech.glide.request.target.ImageViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    public void onLoadFailed(Exception exc, Drawable drawable) {
        getView().setScaleType(this.a);
        super.onLoadFailed(exc, drawable);
    }
}
