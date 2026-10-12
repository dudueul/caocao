package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.MemoryCacheUtil;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SyncImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageNonViewAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.L;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageLoader {
    private static final String ERROR_INIT_CONFIG_WITH_NULL = "ImageLoader configuration can not be initialized with null";
    private static final String ERROR_NOT_INIT = "ImageLoader must be init with configuration before using";
    private static final String ERROR_WRONG_ARGUMENTS = "Wrong arguments were passed to displayImage() method (ImageView reference must not be null)";
    static final String LOG_DESTROY = "Destroy ImageLoader";
    static final String LOG_INIT_CONFIG = "Initialize ImageLoader with configuration";
    static final String LOG_LOAD_IMAGE_FROM_MEMORY_CACHE = "Load image from memory cache [%s]";
    public static final String TAG = ImageLoader.class.getSimpleName();
    private static final String WARNING_RE_INIT_CONFIG = "Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.";
    private static volatile ImageLoader instance;
    private ImageLoaderConfiguration configuration;
    private final ImageLoadingListener emptyListener = new SimpleImageLoadingListener();
    private ImageLoaderEngine engine;

    protected ImageLoader() {
    }

    private void checkConfiguration() {
        if (this.configuration == null) {
            throw new IllegalStateException(ERROR_NOT_INIT);
        }
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public void cancelDisplayTask(ImageView imageView) {
        this.engine.cancelDisplayTaskFor(new ImageViewAware(imageView));
    }

    public void cancelDisplayTask(ImageAware imageAware) {
        this.engine.cancelDisplayTaskFor(imageAware);
    }

    public void clearDiscCache() {
        checkConfiguration();
        this.configuration.discCache.clear();
    }

    public void clearMemoryCache() {
        checkConfiguration();
        this.configuration.memoryCache.clear();
    }

    public void denyNetworkDownloads(boolean z) {
        this.engine.denyNetworkDownloads(z);
    }

    public void destroy() {
        if (this.configuration != null && this.configuration.writeLogs) {
            L.d(LOG_DESTROY, new Object[0]);
        }
        stop();
        this.engine = null;
        this.configuration = null;
    }

    public void displayImage(String str, ImageView imageView) {
        displayImage(str, new ImageViewAware(imageView), (DisplayImageOptions) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageView imageView, DisplayImageOptions displayImageOptions) {
        displayImage(str, new ImageViewAware(imageView), displayImageOptions, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        displayImage(str, imageView, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        displayImage(str, new ImageViewAware(imageView), displayImageOptions, imageLoadingListener, imageLoadingProgressListener);
    }

    public void displayImage(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        displayImage(str, new ImageViewAware(imageView), (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageAware imageAware) {
        displayImage(str, imageAware, (DisplayImageOptions) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions) {
        displayImage(str, imageAware, displayImageOptions, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        displayImage(str, imageAware, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void displayImage(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        checkConfiguration();
        if (imageAware == null) {
            throw new IllegalArgumentException(ERROR_WRONG_ARGUMENTS);
        }
        if (imageLoadingListener == null) {
            imageLoadingListener = this.emptyListener;
        }
        if (displayImageOptions == null) {
            displayImageOptions = this.configuration.defaultDisplayImageOptions;
        }
        if (TextUtils.isEmpty(str)) {
            this.engine.cancelDisplayTaskFor(imageAware);
            imageLoadingListener.onLoadingStarted(str, imageAware.getWrappedView());
            if (displayImageOptions.shouldShowImageForEmptyUri()) {
                imageAware.setImageDrawable(displayImageOptions.getImageForEmptyUri(this.configuration.resources));
            } else {
                imageAware.setImageDrawable(null);
            }
            imageLoadingListener.onLoadingComplete(str, imageAware.getWrappedView(), null);
            return;
        }
        ImageSize imageSizeDefineTargetSizeForView = ImageSizeUtils.defineTargetSizeForView(imageAware, this.configuration.getMaxImageSize());
        String strGenerateKey = MemoryCacheUtil.generateKey(str, imageSizeDefineTargetSizeForView);
        this.engine.prepareDisplayTaskFor(imageAware, strGenerateKey);
        imageLoadingListener.onLoadingStarted(str, imageAware.getWrappedView());
        Bitmap bitmap = this.configuration.memoryCache.get(strGenerateKey);
        if (bitmap == null || bitmap.isRecycled()) {
            if (displayImageOptions.shouldShowImageOnLoading()) {
                imageAware.setImageDrawable(displayImageOptions.getImageOnLoading(this.configuration.resources));
            } else if (displayImageOptions.isResetViewBeforeLoading()) {
                imageAware.setImageDrawable(null);
            }
            LoadAndDisplayImageTask loadAndDisplayImageTask = new LoadAndDisplayImageTask(this.engine, new ImageLoadingInfo(str, imageAware, imageSizeDefineTargetSizeForView, strGenerateKey, displayImageOptions, imageLoadingListener, imageLoadingProgressListener, this.engine.getLockForUri(str)), displayImageOptions.getHandler());
            if (displayImageOptions.isSyncLoading()) {
                loadAndDisplayImageTask.run();
                return;
            } else {
                this.engine.submit(loadAndDisplayImageTask);
                return;
            }
        }
        if (this.configuration.writeLogs) {
            L.d(LOG_LOAD_IMAGE_FROM_MEMORY_CACHE, strGenerateKey);
        }
        if (!displayImageOptions.shouldPostProcess()) {
            displayImageOptions.getDisplayer().display(bitmap, imageAware, LoadedFrom.MEMORY_CACHE);
            imageLoadingListener.onLoadingComplete(str, imageAware.getWrappedView(), bitmap);
            return;
        }
        ProcessAndDisplayImageTask processAndDisplayImageTask = new ProcessAndDisplayImageTask(this.engine, bitmap, new ImageLoadingInfo(str, imageAware, imageSizeDefineTargetSizeForView, strGenerateKey, displayImageOptions, imageLoadingListener, imageLoadingProgressListener, this.engine.getLockForUri(str)), displayImageOptions.getHandler());
        if (displayImageOptions.isSyncLoading()) {
            processAndDisplayImageTask.run();
        } else {
            this.engine.submit(processAndDisplayImageTask);
        }
    }

    public void displayImage(String str, ImageAware imageAware, ImageLoadingListener imageLoadingListener) {
        displayImage(str, imageAware, (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public DiscCacheAware getDiscCache() {
        checkConfiguration();
        return this.configuration.discCache;
    }

    public String getLoadingUriForView(ImageView imageView) {
        return this.engine.getLoadingUriForView(new ImageViewAware(imageView));
    }

    public String getLoadingUriForView(ImageAware imageAware) {
        return this.engine.getLoadingUriForView(imageAware);
    }

    public MemoryCacheAware<String, Bitmap> getMemoryCache() {
        checkConfiguration();
        return this.configuration.memoryCache;
    }

    public void handleSlowNetwork(boolean z) {
        this.engine.handleSlowNetwork(z);
    }

    public synchronized void init(ImageLoaderConfiguration imageLoaderConfiguration) {
        try {
            if (imageLoaderConfiguration == null) {
                throw new IllegalArgumentException(ERROR_INIT_CONFIG_WITH_NULL);
            }
            if (this.configuration == null) {
                if (imageLoaderConfiguration.writeLogs) {
                    L.d(LOG_INIT_CONFIG, new Object[0]);
                }
                this.engine = new ImageLoaderEngine(imageLoaderConfiguration);
                this.configuration = imageLoaderConfiguration;
            } else {
                L.w(WARNING_RE_INIT_CONFIG, new Object[0]);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean isInited() {
        return this.configuration != null;
    }

    public void loadImage(String str, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        loadImage(str, null, displayImageOptions, imageLoadingListener, null);
    }

    public void loadImage(String str, ImageLoadingListener imageLoadingListener) {
        loadImage(str, null, null, imageLoadingListener, null);
    }

    public void loadImage(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        loadImage(str, imageSize, displayImageOptions, imageLoadingListener, null);
    }

    public void loadImage(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        checkConfiguration();
        if (imageSize == null) {
            imageSize = this.configuration.getMaxImageSize();
        }
        if (displayImageOptions == null) {
            displayImageOptions = this.configuration.defaultDisplayImageOptions;
        }
        displayImage(str, new ImageNonViewAware(str, imageSize, ViewScaleType.CROP), displayImageOptions, imageLoadingListener, imageLoadingProgressListener);
    }

    public void loadImage(String str, ImageSize imageSize, ImageLoadingListener imageLoadingListener) {
        loadImage(str, imageSize, null, imageLoadingListener, null);
    }

    public Bitmap loadImageSync(String str) {
        return loadImageSync(str, null, null);
    }

    public Bitmap loadImageSync(String str, DisplayImageOptions displayImageOptions) {
        return loadImageSync(str, null, displayImageOptions);
    }

    public Bitmap loadImageSync(String str, ImageSize imageSize) {
        return loadImageSync(str, imageSize, null);
    }

    public Bitmap loadImageSync(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions) {
        if (displayImageOptions == null) {
            displayImageOptions = this.configuration.defaultDisplayImageOptions;
        }
        DisplayImageOptions displayImageOptionsBuild = new DisplayImageOptions.Builder().cloneFrom(displayImageOptions).syncLoading(true).build();
        SyncImageLoadingListener syncImageLoadingListener = new SyncImageLoadingListener();
        loadImage(str, imageSize, displayImageOptionsBuild, syncImageLoadingListener);
        return syncImageLoadingListener.getLoadedBitmap();
    }

    public void pause() {
        this.engine.pause();
    }

    public void resume() {
        this.engine.resume();
    }

    public void stop() {
        this.engine.stop();
    }
}
