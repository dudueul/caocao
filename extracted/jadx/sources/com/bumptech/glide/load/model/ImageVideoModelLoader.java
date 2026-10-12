package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cancel();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cleanup();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cleanup();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public String getId() {
            return this.streamFetcher != null ? this.streamFetcher.getId() : this.fileDescriptorFetcher.getId();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.data.DataFetcher
        public ImageVideoWrapper loadData(Priority priority) throws Exception {
            InputStream inputStreamLoadData = null;
            if (this.streamFetcher != null) {
                try {
                    inputStreamLoadData = this.streamFetcher.loadData(priority);
                } catch (Exception e) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e);
                    }
                    if (this.fileDescriptorFetcher == null) {
                        throw e;
                    }
                }
            }
            ParcelFileDescriptor parcelFileDescriptorLoadData = null;
            if (this.fileDescriptorFetcher != null) {
                try {
                    parcelFileDescriptorLoadData = this.fileDescriptorFetcher.loadData(priority);
                } catch (Exception e2) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching ParcelFileDescriptor", e2);
                    }
                    if (inputStreamLoadData == null) {
                        throw e2;
                    }
                }
            }
            return new ImageVideoWrapper(inputStreamLoadData, parcelFileDescriptorLoadData);
        }
    }

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a, int i, int i2) {
        DataFetcher<InputStream> resourceFetcher = this.streamLoader != null ? this.streamLoader.getResourceFetcher(a, i, i2) : null;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = this.fileDescriptorLoader != null ? this.fileDescriptorLoader.getResourceFetcher(a, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }
}
