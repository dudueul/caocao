package com.nostra13.universalimageloader.core.assist;

import android.graphics.Bitmap;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SyncImageLoadingListener extends SimpleImageLoadingListener {
    private Bitmap loadedImage;

    public Bitmap getLoadedBitmap() {
        return this.loadedImage;
    }

    @Override // com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.assist.ImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        this.loadedImage = bitmap;
    }
}
