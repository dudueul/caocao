package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.executor.Prioritized;
import com.bumptech.glide.request.ResourceCallback;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class EngineRunnable implements Runnable, Prioritized {
    private static final String TAG = "EngineRunnable";
    private final DecodeJob<?, ?, ?> decodeJob;
    private volatile boolean isCancelled;
    private final EngineRunnableManager manager;
    private final Priority priority;
    private Stage stage = Stage.CACHE;

    interface EngineRunnableManager extends ResourceCallback {
        void submitForSource(EngineRunnable engineRunnable);
    }

    private enum Stage {
        CACHE,
        SOURCE
    }

    public EngineRunnable(EngineRunnableManager engineRunnableManager, DecodeJob<?, ?, ?> decodeJob, Priority priority) {
        this.manager = engineRunnableManager;
        this.decodeJob = decodeJob;
        this.priority = priority;
    }

    private Resource<?> decode() throws Exception {
        return isDecodingFromCache() ? decodeFromCache() : decodeFromSource();
    }

    private Resource<?> decodeFromCache() throws Exception {
        Resource<?> resourceDecodeResultFromCache = null;
        try {
            resourceDecodeResultFromCache = this.decodeJob.decodeResultFromCache();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception decoding result from cache: " + e);
            }
        }
        return resourceDecodeResultFromCache == null ? this.decodeJob.decodeSourceFromCache() : resourceDecodeResultFromCache;
    }

    private Resource<?> decodeFromSource() throws Exception {
        return this.decodeJob.decodeFromSource();
    }

    private boolean isDecodingFromCache() {
        return this.stage == Stage.CACHE;
    }

    private void onLoadComplete(Resource resource) {
        this.manager.onResourceReady(resource);
    }

    private void onLoadFailed(Exception exc) {
        if (!isDecodingFromCache()) {
            this.manager.onException(exc);
        } else {
            this.stage = Stage.SOURCE;
            this.manager.submitForSource(this);
        }
    }

    public void cancel() {
        this.isCancelled = true;
        this.decodeJob.cancel();
    }

    @Override // com.bumptech.glide.load.engine.executor.Prioritized
    public int getPriority() {
        return this.priority.ordinal();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.isCancelled) {
            return;
        }
        Exception exc = null;
        Resource<?> resourceDecode = null;
        try {
            resourceDecode = decode();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Exception decoding", e);
            }
            exc = e;
        }
        if (this.isCancelled) {
            if (resourceDecode != null) {
                resourceDecode.recycle();
            }
        } else if (resourceDecode == null) {
            onLoadFailed(exc);
        } else {
            onLoadComplete(resourceDecode);
        }
    }
}
