package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.FacebookException;
import com.facebook.places.model.PlaceFields;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static Handler handler;
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    private static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context, RequestKey requestKey, boolean z) {
            this.context = context;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    private static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context, RequestKey requestKey) {
            this.context = context;
            this.key = requestKey;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }

    private static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }
    }

    private static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;

        RequestKey(Uri uri, Object obj) {
            this.uri = uri;
            this.tag = obj;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            return requestKey.uri == this.uri && requestKey.tag == this.tag;
        }

        public int hashCode() {
            return ((this.uri.hashCode() + 1073) * 37) + this.tag.hashCode();
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z = false;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                z = true;
                if (downloaderContext.workItem.cancel()) {
                    pendingRequests.remove(requestKey);
                } else {
                    downloaderContext.isCancelled = true;
                }
            }
        }
        return z;
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void download(RequestKey requestKey, Context context) {
        InputStream inputStreamInterceptAndCacheImageStream = null;
        Exception facebookException = null;
        Bitmap bitmapDecodeStream = null;
        boolean z = true;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(requestKey.uri.toString()).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            switch (httpURLConnection.getResponseCode()) {
                case 200:
                    inputStreamInterceptAndCacheImageStream = ImageResponseCache.interceptAndCacheImageStream(context, httpURLConnection);
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamInterceptAndCacheImageStream);
                    break;
                case 301:
                case HttpResponseCode.FOUND /* 302 */:
                    z = false;
                    String headerField = httpURLConnection.getHeaderField(PlaceFields.LOCATION);
                    if (!Utility.isNullOrEmpty(headerField)) {
                        Uri uri = Uri.parse(headerField);
                        UrlRedirectCache.cacheUriRedirect(requestKey.uri, uri);
                        DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
                        if (downloaderContextRemovePendingRequest != null && !downloaderContextRemovePendingRequest.isCancelled) {
                            enqueueCacheRead(downloaderContextRemovePendingRequest.request, new RequestKey(uri, requestKey.tag), false);
                        }
                    }
                    break;
                default:
                    inputStreamInterceptAndCacheImageStream = httpURLConnection.getErrorStream();
                    StringBuilder sb = new StringBuilder();
                    if (inputStreamInterceptAndCacheImageStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStreamInterceptAndCacheImageStream);
                        char[] cArr = new char[128];
                        while (true) {
                            int i = inputStreamReader.read(cArr, 0, cArr.length);
                            if (i > 0) {
                                sb.append(cArr, 0, i);
                            } else {
                                Utility.closeQuietly(inputStreamReader);
                            }
                        }
                    } else {
                        sb.append("Unexpected error while downloading an image.");
                    }
                    facebookException = new FacebookException(sb.toString());
                    break;
            }
            Utility.closeQuietly(inputStreamInterceptAndCacheImageStream);
            Utility.disconnectQuietly(httpURLConnection);
        } catch (IOException e) {
            facebookException = e;
            Utility.closeQuietly(null);
            Utility.disconnectQuietly(null);
        } catch (Throwable th) {
            Utility.closeQuietly(null);
            Utility.disconnectQuietly(null);
            throw th;
        }
        if (z) {
            issueResponse(requestKey, facebookException, bitmapDecodeStream, false);
        }
    }

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.request = imageRequest;
                downloaderContext.isCancelled = false;
                downloaderContext.workItem.moveToFront();
            } else {
                enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
            }
        }
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    private static synchronized Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    private static void issueResponse(RequestKey requestKey, final Exception exc, final Bitmap bitmap, final boolean z) {
        final ImageRequest imageRequest;
        final ImageRequest.Callback callback;
        DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
        if (downloaderContextRemovePendingRequest == null || downloaderContextRemovePendingRequest.isCancelled || (callback = (imageRequest = downloaderContextRemovePendingRequest.request).getCallback()) == null) {
            return;
        }
        getHandler().post(new Runnable() { // from class: com.facebook.internal.ImageDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                callback.onCompleted(new ImageResponse(imageRequest, exc, z, bitmap));
            }
        });
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        Uri redirectedUri;
        InputStream cachedImageStream = null;
        boolean z2 = false;
        if (z && (redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri)) != null) {
            cachedImageStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
            z2 = cachedImageStream != null;
        }
        if (!z2) {
            cachedImageStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (cachedImageStream != null) {
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(cachedImageStream);
            Utility.closeQuietly(cachedImageStream);
            issueResponse(requestKey, null, bitmapDecodeStream, z2);
        } else {
            DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
            if (downloaderContextRemovePendingRequest == null || downloaderContextRemovePendingRequest.isCancelled) {
                return;
            }
            enqueueDownload(downloaderContextRemovePendingRequest.request, requestKey);
        }
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext downloaderContextRemove;
        synchronized (pendingRequests) {
            downloaderContextRemove = pendingRequests.remove(requestKey);
        }
        return downloaderContextRemove;
    }
}
