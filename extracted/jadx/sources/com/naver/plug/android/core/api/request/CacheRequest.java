package com.naver.plug.android.core.api.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class CacheRequest<T extends Response> {
    private T cachedResponse;
    private final List<RequestListener<T>> pendingListeners = new ArrayList();
    private Request<T> pendingRequest;

    private boolean isTarget(String str, Object obj) {
        return TextUtils.isEmpty(str) || TextUtils.equals((String) obj, str);
    }

    public abstract Request<T> createRequest();

    public void execute(Context context, RequestListener<T> requestListener) {
        execute(context, false, requestListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void execute(Context context, boolean z, final RequestListener<T> requestListener) {
        final String cacheKey = getCacheKey();
        if (this.cachedResponse != null && isTarget(cacheKey, this.cachedResponse.getTag())) {
            requestListener.onSuccess(this.cachedResponse);
            return;
        }
        if (this.pendingRequest != null && !isTarget(cacheKey, this.pendingRequest.getTag())) {
            this.pendingRequest.cancel();
            this.pendingRequest = null;
        }
        if (this.pendingRequest != null && !this.pendingRequest.isCanceled()) {
            this.pendingListeners.add(requestListener);
            return;
        }
        this.pendingListeners.clear();
        this.pendingListeners.add(requestListener);
        this.pendingRequest = createRequest();
        this.pendingRequest.setTag(cacheKey);
        this.pendingRequest.showProgress(z).execute(context, new RequestListener<T>() { // from class: com.naver.plug.android.core.api.request.CacheRequest.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(PlugError plugError) {
                try {
                    Iterator it = CacheRequest.this.pendingListeners.iterator();
                    while (it.hasNext()) {
                        ((RequestListener) it.next()).onFailure(plugError);
                    }
                } catch (Exception e) {
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFinally(T t, PlugError plugError) {
                requestListener.onFinally(t, plugError);
                try {
                    Iterator it = CacheRequest.this.pendingListeners.iterator();
                    while (it.hasNext()) {
                        ((RequestListener) it.next()).onFinally(t, plugError);
                    }
                } catch (Exception e) {
                }
                CacheRequest.this.pendingListeners.clear();
                CacheRequest.this.pendingRequest = null;
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onSuccess(@NonNull T t) {
                t.setTag(cacheKey);
                CacheRequest.this.cachedResponse = t;
                try {
                    Iterator it = CacheRequest.this.pendingListeners.iterator();
                    while (it.hasNext()) {
                        ((RequestListener) it.next()).onSuccess(t);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    protected String getCacheKey() {
        return null;
    }

    public T getCachedResponse() {
        return this.cachedResponse;
    }

    public void setCachedResponse(T t) {
        if (this.pendingRequest != null) {
            this.pendingRequest.cancel();
            this.pendingRequest = null;
        }
        this.cachedResponse = t;
    }
}
