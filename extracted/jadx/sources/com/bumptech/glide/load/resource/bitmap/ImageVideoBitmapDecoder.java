package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageVideoBitmapDecoder implements ResourceDecoder<ImageVideoWrapper, Bitmap> {
    private static final String TAG = "ImageVideoDecoder";
    private final ResourceDecoder<ParcelFileDescriptor, Bitmap> fileDescriptorDecoder;
    private final ResourceDecoder<InputStream, Bitmap> streamDecoder;

    public ImageVideoBitmapDecoder(ResourceDecoder<InputStream, Bitmap> resourceDecoder, ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoder2) {
        this.streamDecoder = resourceDecoder;
        this.fileDescriptorDecoder = resourceDecoder2;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(ImageVideoWrapper imageVideoWrapper, int i, int i2) throws IOException {
        ParcelFileDescriptor fileDescriptor;
        Resource<Bitmap> resourceDecode = null;
        InputStream stream = imageVideoWrapper.getStream();
        if (stream != null) {
            try {
                resourceDecode = this.streamDecoder.decode(stream, i, i2);
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Failed to load image from stream, trying FileDescriptor", e);
                }
            }
        }
        return (resourceDecode != null || (fileDescriptor = imageVideoWrapper.getFileDescriptor()) == null) ? resourceDecode : this.fileDescriptorDecoder.decode(fileDescriptor, i, i2);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public String getId() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
