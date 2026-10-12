package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestFuture<T> implements Future<T>, Response.Listener<T>, Response.ErrorListener {
    private VolleyError mException;
    private Request<?> mRequest;
    private T mResult;
    private boolean mResultReceived = false;

    private RequestFuture() {
    }

    private synchronized T doGet(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        T t;
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        if (this.mResultReceived) {
            t = this.mResult;
        } else {
            if (l == null) {
                wait(0L);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.mException != null) {
                throw new ExecutionException(this.mException);
            }
            if (!this.mResultReceived) {
                throw new TimeoutException();
            }
            t = this.mResult;
        }
        return t;
    }

    public static <E> RequestFuture<E> newFuture() {
        return new RequestFuture<>();
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (this.mRequest != null && !isDone()) {
                this.mRequest.cancel();
                z2 = true;
            }
        }
        return z2;
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        try {
            return doGet(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return doGet(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        if (this.mRequest == null) {
            return false;
        }
        return this.mRequest.isCanceled();
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isDone() {
        return this.mResultReceived || this.mException != null || isCancelled();
    }

    @Override // com.android.volley.Response.ErrorListener
    public synchronized void onErrorResponse(VolleyError volleyError) {
        this.mException = volleyError;
        notifyAll();
    }

    @Override // com.android.volley.Response.Listener
    public synchronized void onResponse(T t) {
        this.mResultReceived = true;
        this.mResult = t;
        notifyAll();
    }

    public void setRequest(Request<?> request) {
        this.mRequest = request;
    }
}
