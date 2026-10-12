package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VideoBitmapDecoder implements BitmapDecoder<ParcelFileDescriptor> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    private static final int NO_FRAME = -1;
    private MediaMetadataRetrieverFactory factory;
    private int frame;

    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    public VideoBitmapDecoder() {
        this(DEFAULT_FACTORY, -1);
    }

    public VideoBitmapDecoder(int i) {
        this(DEFAULT_FACTORY, checkValidFrame(i));
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory) {
        this(mediaMetadataRetrieverFactory, -1);
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory, int i) {
        this.factory = mediaMetadataRetrieverFactory;
        this.frame = i;
    }

    private static int checkValidFrame(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Requested frame must be non-negative");
        }
        return i;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
    public Bitmap decode(ParcelFileDescriptor parcelFileDescriptor, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws IOException {
        MediaMetadataRetriever mediaMetadataRetrieverBuild = this.factory.build();
        mediaMetadataRetrieverBuild.setDataSource(parcelFileDescriptor.getFileDescriptor());
        Bitmap frameAtTime = this.frame >= 0 ? mediaMetadataRetrieverBuild.getFrameAtTime(this.frame) : mediaMetadataRetrieverBuild.getFrameAtTime();
        mediaMetadataRetrieverBuild.release();
        parcelFileDescriptor.close();
        return frameAtTime;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
    public String getId() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
