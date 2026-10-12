package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class LoadAndDisplayImageTask implements Runnable, IoUtils.CopyListener {
    private static final int BUFFER_SIZE = 32768;
    private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
    private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
    private static final String ERROR_PROCESSOR_FOR_DISC_CACHE_NULL = "Bitmap processor for disc cache returned null [%s]";
    private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
    private static final String LOG_CACHE_IMAGE_ON_DISC = "Cache image on disc [%s]";
    private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
    private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_DISC_CACHE = "Load image from disc cache [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
    private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
    private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
    private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISC = "Process image before cache on disc [%s]";
    private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disc cache [%s]";
    private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
    private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
    private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
    private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
    private final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final ImageSize targetSize;
    final String uri;
    private final boolean writeLogs;

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo;
        this.handler = handler;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.writeLogs = this.configuration.writeLogs;
        this.uri = imageLoadingInfo.uri;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.imageAware = imageLoadingInfo.imageAware;
        this.targetSize = imageLoadingInfo.targetSize;
        this.options = imageLoadingInfo.options;
        this.listener = imageLoadingInfo.listener;
        this.progressListener = imageLoadingInfo.progressListener;
    }

    private void checkTaskInterrupted() throws TaskCancelledException {
        if (isTaskInterrupted()) {
            throw new TaskCancelledException();
        }
    }

    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {
            throw new TaskCancelledException();
        }
    }

    private Bitmap decodeImage(String str) throws IOException {
        return this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options));
    }

    private boolean delayIfNeed() {
        if (!this.options.shouldDelayBeforeLoading()) {
            return false;
        }
        log(LOG_DELAY_BEFORE_LOADING, Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
        try {
            Thread.sleep(this.options.getDelayBeforeLoading());
            return isTaskNotActual();
        } catch (InterruptedException e) {
            L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
            return true;
        }
    }

    private boolean downloadImage(File file) throws IOException {
        InputStream stream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 32768);
            try {
                boolean zCopyStream = IoUtils.copyStream(stream, bufferedOutputStream, this);
                IoUtils.closeSilently(bufferedOutputStream);
                IoUtils.closeSilently(stream);
                return zCopyStream;
            } catch (Throwable th) {
                IoUtils.closeSilently(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            IoUtils.closeSilently(stream);
            throw th2;
        }
    }

    private void fireCancelEvent() {
        if (this.options.isSyncLoading() || isTaskInterrupted()) {
            return;
        }
        this.handler.post(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.3
            @Override // java.lang.Runnable
            public void run() {
                LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
            }
        });
    }

    private void fireFailEvent(final FailReason.FailType failType, final Throwable th) {
        if (this.options.isSyncLoading() || isTaskInterrupted() || isTaskNotActual()) {
            return;
        }
        this.handler.post(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.2
            @Override // java.lang.Runnable
            public void run() {
                if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                    LoadAndDisplayImageTask.this.imageAware.setImageDrawable(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
                }
                LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(failType, th));
            }
        });
    }

    private boolean fireProgressEvent(final int i, final int i2) {
        if (this.options.isSyncLoading() || isTaskInterrupted() || isTaskNotActual()) {
            return false;
        }
        this.handler.post(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.1
            @Override // java.lang.Runnable
            public void run() {
                LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
            }
        });
        return true;
    }

    private ImageDownloader getDownloader() {
        if (this.engine.isNetworkDenied()) {
            return this.networkDeniedDownloader;
        }
        return this.engine.isSlowNetwork() ? this.slowNetworkDownloader : this.downloader;
    }

    private File getImageFileInDiscCache() {
        File parentFile;
        File file = this.configuration.discCache.get(this.uri);
        File parentFile2 = file.getParentFile();
        if ((parentFile2 == null || (!parentFile2.exists() && !parentFile2.mkdirs())) && (parentFile = (file = this.configuration.reserveDiscCache.get(this.uri)).getParentFile()) != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    private boolean isTaskInterrupted() {
        if (!Thread.interrupted()) {
            return false;
        }
        log(LOG_TASK_INTERRUPTED);
        return true;
    }

    private boolean isTaskNotActual() {
        return isViewCollected() || isViewReused();
    }

    private boolean isViewCollected() {
        if (!this.imageAware.isCollected()) {
            return false;
        }
        log(LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED);
        return true;
    }

    private boolean isViewReused() {
        if (!(!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware)))) {
            return false;
        }
        log(LOG_TASK_CANCELLED_IMAGEAWARE_REUSED);
        return true;
    }

    private void log(String str) {
        if (this.writeLogs) {
            L.d(str, this.memoryCacheKey);
        }
    }

    private void log(String str, Object... objArr) {
        if (this.writeLogs) {
            L.d(str, objArr);
        }
    }

    private boolean resizeAndSaveImage(File file, int i, int i2) throws IOException {
        Bitmap bitmapDecode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath()), new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new DisplayImageOptions.Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build()));
        if (bitmapDecode != null && this.configuration.processorForDiscCache != null) {
            log(LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISC);
            bitmapDecode = this.configuration.processorForDiscCache.process(bitmapDecode);
            if (bitmapDecode == null) {
                L.e(ERROR_PROCESSOR_FOR_DISC_CACHE_NULL, this.memoryCacheKey);
            }
        }
        if (bitmapDecode != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 32768);
            try {
                bitmapDecode.compress(this.configuration.imageCompressFormatForDiscCache, this.configuration.imageQualityForDiscCache, bufferedOutputStream);
                IoUtils.closeSilently(bufferedOutputStream);
                bitmapDecode.recycle();
            } catch (Throwable th) {
                IoUtils.closeSilently(bufferedOutputStream);
                throw th;
            }
        }
        return true;
    }

    private boolean tryCacheImageOnDisc(File file) throws TaskCancelledException {
        log(LOG_CACHE_IMAGE_ON_DISC);
        boolean zDownloadImage = false;
        try {
            zDownloadImage = downloadImage(file);
            if (zDownloadImage) {
                int i = this.configuration.maxImageWidthForDiscCache;
                int i2 = this.configuration.maxImageHeightForDiscCache;
                if (i > 0 || i2 > 0) {
                    log(LOG_RESIZE_CACHED_IMAGE_FILE);
                    zDownloadImage = resizeAndSaveImage(file, i, i2);
                }
                this.configuration.discCache.put(this.uri, file);
            }
        } catch (IOException e) {
            L.e(e);
            if (file.exists()) {
                file.delete();
            }
        }
        return zDownloadImage;
    }

    private Bitmap tryLoadBitmap() throws TaskCancelledException {
        File imageFileInDiscCache = getImageFileInDiscCache();
        Bitmap bitmapDecodeImage = null;
        try {
            String strWrap = ImageDownloader.Scheme.FILE.wrap(imageFileInDiscCache.getAbsolutePath());
            if (imageFileInDiscCache.exists()) {
                log(LOG_LOAD_IMAGE_FROM_DISC_CACHE);
                this.loadedFrom = LoadedFrom.DISC_CACHE;
                checkTaskNotActual();
                bitmapDecodeImage = decodeImage(strWrap);
            }
            if (bitmapDecodeImage == null || bitmapDecodeImage.getWidth() <= 0 || bitmapDecodeImage.getHeight() <= 0) {
                log(LOG_LOAD_IMAGE_FROM_NETWORK);
                this.loadedFrom = LoadedFrom.NETWORK;
                String str = (this.options.isCacheOnDisc() && tryCacheImageOnDisc(imageFileInDiscCache)) ? strWrap : this.uri;
                checkTaskNotActual();
                bitmapDecodeImage = decodeImage(str);
                if (bitmapDecodeImage == null || bitmapDecodeImage.getWidth() <= 0 || bitmapDecodeImage.getHeight() <= 0) {
                    fireFailEvent(FailReason.FailType.DECODING_ERROR, null);
                }
            }
        } catch (TaskCancelledException e) {
            throw e;
        } catch (IOException e2) {
            L.e(e2);
            fireFailEvent(FailReason.FailType.IO_ERROR, e2);
            if (imageFileInDiscCache.exists()) {
                imageFileInDiscCache.delete();
            }
        } catch (IllegalStateException e3) {
            fireFailEvent(FailReason.FailType.NETWORK_DENIED, null);
        } catch (OutOfMemoryError e4) {
            L.e(e4);
            fireFailEvent(FailReason.FailType.OUT_OF_MEMORY, e4);
        } catch (Throwable th) {
            L.e(th);
            fireFailEvent(FailReason.FailType.UNKNOWN, th);
        }
        return bitmapDecodeImage;
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    log(LOG_WAITING_FOR_RESUME);
                    try {
                        this.engine.getPauseLock().wait();
                        log(LOG_RESUME_AFTER_PAUSE);
                    } catch (InterruptedException e) {
                        L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    String getLoadingUri() {
        return this.uri;
    }

    @Override // com.nostra13.universalimageloader.utils.IoUtils.CopyListener
    public boolean onBytesCopied(int i, int i2) {
        return this.progressListener == null || fireProgressEvent(i, i2);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (waitIfPaused() || delayIfNeed()) {
            return;
        }
        ReentrantLock reentrantLock = this.imageLoadingInfo.loadFromUriLock;
        log(LOG_START_DISPLAY_IMAGE_TASK);
        if (reentrantLock.isLocked()) {
            log(LOG_WAITING_FOR_IMAGE_LOADED);
        }
        reentrantLock.lock();
        try {
            try {
                checkTaskNotActual();
                Bitmap bitmapProcess = this.configuration.memoryCache.get(this.memoryCacheKey);
                if (bitmapProcess == null) {
                    bitmapProcess = tryLoadBitmap();
                    if (bitmapProcess == null) {
                        reentrantLock.unlock();
                        return;
                    }
                    checkTaskNotActual();
                    checkTaskInterrupted();
                    if (this.options.shouldPreProcess()) {
                        log(LOG_PREPROCESS_IMAGE);
                        bitmapProcess = this.options.getPreProcessor().process(bitmapProcess);
                        if (bitmapProcess == null) {
                            L.e(ERROR_PRE_PROCESSOR_NULL, this.memoryCacheKey);
                        }
                    }
                    if (bitmapProcess != null && this.options.isCacheInMemory()) {
                        log(LOG_CACHE_IMAGE_IN_MEMORY);
                        this.configuration.memoryCache.put(this.memoryCacheKey, bitmapProcess);
                    }
                } else {
                    this.loadedFrom = LoadedFrom.MEMORY_CACHE;
                    log(LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING);
                }
                if (bitmapProcess != null && this.options.shouldPostProcess()) {
                    log(LOG_POSTPROCESS_IMAGE);
                    bitmapProcess = this.options.getPostProcessor().process(bitmapProcess);
                    if (bitmapProcess == null) {
                        L.e(ERROR_POST_PROCESSOR_NULL, this.memoryCacheKey);
                    }
                }
                checkTaskNotActual();
                checkTaskInterrupted();
                reentrantLock.unlock();
                DisplayBitmapTask displayBitmapTask = new DisplayBitmapTask(bitmapProcess, this.imageLoadingInfo, this.engine, this.loadedFrom);
                displayBitmapTask.setLoggingEnabled(this.writeLogs);
                if (this.options.isSyncLoading()) {
                    displayBitmapTask.run();
                } else {
                    this.handler.post(displayBitmapTask);
                }
            } catch (TaskCancelledException e) {
                fireCancelEvent();
                reentrantLock.unlock();
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
