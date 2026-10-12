package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.DataLoadProvider;
import com.bumptech.glide.util.LogTime;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DecodeJob<A, T, Z> {
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private static final String TAG = "DecodeJob";
    private final DiskCacheProvider diskCacheProvider;
    private final DiskCacheStrategy diskCacheStrategy;
    private final DataFetcher<A> fetcher;
    private final FileOpener fileOpener;
    private final int height;
    private volatile boolean isCancelled;
    private final DataLoadProvider<A, T> loadProvider;
    private final Priority priority;
    private final EngineKey resultKey;
    private final ResourceTranscoder<T, Z> transcoder;
    private final Transformation<T> transformation;
    private final int width;

    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    static class FileOpener {
        FileOpener() {
        }

        public OutputStream open(File file) throws FileNotFoundException {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }

    class SourceWriter<DataType> implements DiskCache.Writer {
        private final DataType data;
        private final Encoder<DataType> encoder;

        public SourceWriter(Encoder<DataType> encoder, DataType datatype) {
            this.encoder = encoder;
            this.data = datatype;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskCache.Writer
        public boolean write(File file) {
            boolean zEncode = false;
            OutputStream outputStreamOpen = null;
            try {
                outputStreamOpen = DecodeJob.this.fileOpener.open(file);
                zEncode = this.encoder.encode(this.data, outputStreamOpen);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable(DecodeJob.TAG, 3)) {
                    Log.d(DecodeJob.TAG, "Failed to find file to write to disk cache", e);
                }
            } finally {
                if (outputStreamOpen != null) {
                    try {
                        outputStreamOpen.close();
                    } catch (IOException e2) {
                    }
                }
            }
            return zEncode;
        }
    }

    public DecodeJob(EngineKey engineKey, int i, int i2, DataFetcher<A> dataFetcher, DataLoadProvider<A, T> dataLoadProvider, Transformation<T> transformation, ResourceTranscoder<T, Z> resourceTranscoder, DiskCacheProvider diskCacheProvider, DiskCacheStrategy diskCacheStrategy, Priority priority) {
        this(engineKey, i, i2, dataFetcher, dataLoadProvider, transformation, resourceTranscoder, diskCacheProvider, diskCacheStrategy, priority, DEFAULT_FILE_OPENER);
    }

    DecodeJob(EngineKey engineKey, int i, int i2, DataFetcher<A> dataFetcher, DataLoadProvider<A, T> dataLoadProvider, Transformation<T> transformation, ResourceTranscoder<T, Z> resourceTranscoder, DiskCacheProvider diskCacheProvider, DiskCacheStrategy diskCacheStrategy, Priority priority, FileOpener fileOpener) {
        this.resultKey = engineKey;
        this.width = i;
        this.height = i2;
        this.fetcher = dataFetcher;
        this.loadProvider = dataLoadProvider;
        this.transformation = transformation;
        this.transcoder = resourceTranscoder;
        this.diskCacheProvider = diskCacheProvider;
        this.diskCacheStrategy = diskCacheStrategy;
        this.priority = priority;
        this.fileOpener = fileOpener;
    }

    private Resource<T> cacheAndDecodeSourceData(A a) throws IOException {
        long logTime = LogTime.getLogTime();
        this.diskCacheProvider.getDiskCache().put(this.resultKey.getOriginalKey(), new SourceWriter(this.loadProvider.getSourceEncoder(), a));
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Wrote source to cache", logTime);
        }
        long logTime2 = LogTime.getLogTime();
        Resource<T> resourceLoadFromCache = loadFromCache(this.resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, 2) && resourceLoadFromCache != null) {
            logWithTimeAndKey("Decoded source from cache", logTime2);
        }
        return resourceLoadFromCache;
    }

    private Resource<T> decodeFromSourceData(A a) throws IOException {
        if (this.diskCacheStrategy.cacheSource()) {
            return cacheAndDecodeSourceData(a);
        }
        long logTime = LogTime.getLogTime();
        Resource<T> resourceDecode = this.loadProvider.getSourceDecoder().decode(a, this.width, this.height);
        if (!Log.isLoggable(TAG, 2)) {
            return resourceDecode;
        }
        logWithTimeAndKey("Decoded from source", logTime);
        return resourceDecode;
    }

    private Resource<T> decodeSource() throws Exception {
        try {
            long logTime = LogTime.getLogTime();
            A aLoadData = this.fetcher.loadData(this.priority);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Fetched data", logTime);
            }
            if (this.isCancelled) {
                return null;
            }
            return decodeFromSourceData(aLoadData);
        } finally {
            this.fetcher.cleanup();
        }
    }

    private Resource<T> loadFromCache(Key key) throws IOException {
        File file = this.diskCacheProvider.getDiskCache().get(key);
        if (file == null) {
            return null;
        }
        try {
            Resource<T> resourceDecode = this.loadProvider.getCacheDecoder().decode(file, this.width, this.height);
            return resourceDecode == null ? resourceDecode : resourceDecode;
        } finally {
            if (0 == 0) {
                this.diskCacheProvider.getDiskCache().delete(key);
            }
        }
    }

    private void logWithTimeAndKey(String str, long j) {
        Log.v(TAG, str + " in " + LogTime.getElapsedMillis(j) + ", key: " + this.resultKey);
    }

    private Resource<Z> transcode(Resource<T> resource) {
        if (resource == null) {
            return null;
        }
        return this.transcoder.transcode(resource);
    }

    private Resource<T> transform(Resource<T> resource) {
        if (resource == null) {
            return null;
        }
        Resource<T> resourceTransform = this.transformation.transform(resource, this.width, this.height);
        if (resource.equals(resourceTransform)) {
            return resourceTransform;
        }
        resource.recycle();
        return resourceTransform;
    }

    private Resource<Z> transformEncodeAndTranscode(Resource<T> resource) {
        long logTime = LogTime.getLogTime();
        Resource<T> resourceTransform = transform(resource);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Transformed resource from source", logTime);
        }
        writeTransformedToCache(resourceTransform);
        long logTime2 = LogTime.getLogTime();
        Resource<Z> resourceTranscode = transcode(resourceTransform);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Transcoded transformed from source", logTime2);
        }
        return resourceTranscode;
    }

    private void writeTransformedToCache(Resource<T> resource) {
        if (resource == null || !this.diskCacheStrategy.cacheResult()) {
            return;
        }
        long logTime = LogTime.getLogTime();
        this.diskCacheProvider.getDiskCache().put(this.resultKey, new SourceWriter(this.loadProvider.getEncoder(), resource));
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Wrote transformed from source to cache", logTime);
        }
    }

    public void cancel() {
        this.isCancelled = true;
        this.fetcher.cancel();
    }

    public Resource<Z> decodeFromSource() throws Exception {
        return transformEncodeAndTranscode(decodeSource());
    }

    public Resource<Z> decodeResultFromCache() throws Exception {
        if (!this.diskCacheStrategy.cacheResult()) {
            return null;
        }
        long logTime = LogTime.getLogTime();
        Resource<T> resourceLoadFromCache = loadFromCache(this.resultKey);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Decoded transformed from cache", logTime);
        }
        long logTime2 = LogTime.getLogTime();
        Resource<Z> resourceTranscode = transcode(resourceLoadFromCache);
        if (!Log.isLoggable(TAG, 2)) {
            return resourceTranscode;
        }
        logWithTimeAndKey("Transcoded transformed from cache", logTime2);
        return resourceTranscode;
    }

    public Resource<Z> decodeSourceFromCache() throws Exception {
        if (!this.diskCacheStrategy.cacheSource()) {
            return null;
        }
        long logTime = LogTime.getLogTime();
        Resource<T> resourceLoadFromCache = loadFromCache(this.resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Decoded source from cache", logTime);
        }
        return transformEncodeAndTranscode(resourceLoadFromCache);
    }
}
