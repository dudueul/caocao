package com.naver.plug.android.core.api.request;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.b;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Request<T extends Response> {
    private static final int DEFAULT_MAX_RETRY_COUNT = 1;
    private static final String TAG = Request.class.getSimpleName();
    private final RequestBuilder<T> builder;
    private PendingRequest<T> pendingRequest;
    private boolean retryLoginError = true;
    private Object tag;

    private static class PendingRequest<T extends Response> {
        final RequestListener<T> listener;
        final Request<T> request;
        final int retryCount;
        com.android.volley.Request<T> volleyRequest;
        final WeakReference<Context> weakContext;

        PendingRequest(Request<T> request, Context context, RequestListener<T> requestListener, int i) {
            this.request = request;
            this.weakContext = new WeakReference<>(context);
            this.listener = requestListener;
            this.retryCount = i;
        }

        void cancel() {
            if (this.volleyRequest != null) {
                this.volleyRequest.deliverError(new VolleyError());
            }
        }

        void retry() {
            Context context = this.weakContext.get();
            if (this.request == null || context == null) {
                return;
            }
            this.request.execute(context, this.retryCount - 1, this.listener);
        }
    }

    public Request(RequestBuilder<T> requestBuilder) {
        this.builder = requestBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterExecute(RequestListener<T> requestListener, T t, PlugError plugError) {
        boolean zShowProgress;
        if (requestListener != null) {
            if (plugError == null) {
                try {
                    plugError = t.getError();
                } catch (Exception e) {
                    Log.d(TAG, "exception raised during request", e);
                    if (zShowProgress) {
                        return;
                    } else {
                        return;
                    }
                } finally {
                    if (this.builder.showProgress()) {
                        b.b(requestListener.uuid);
                    }
                }
            }
            if (plugError == null) {
                requestListener.onSuccess(t);
                requestListener.onFinally(t, null);
            } else {
                requestListener.onFailure(plugError);
                requestListener.onFinally(null, plugError);
            }
        }
    }

    private void beforeExecute(RequestListener<T> requestListener) {
        if (requestListener == null || !this.builder.showProgress()) {
            return;
        }
        b.a(requestListener.uuid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canRetryError(int i, PlugError plugError) {
        return i > 0 && this.retryLoginError && plugError != null && plugError.isAuthenticationError();
    }

    public static void cancelAllRequests(Context context) {
        VolleyQueue.getInstance(context).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() { // from class: com.naver.plug.android.core.api.request.Request.1
            @Override // com.android.volley.RequestQueue.RequestFilter
            public boolean apply(com.android.volley.Request<?> request) {
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRetry(Context context, PlugError plugError) {
        if (this.retryLoginError && plugError.isAuthenticationError()) {
            handleAuthenticationError(context, plugError);
        }
        b.c();
    }

    private void handleAuthenticationError(Context context, PlugError plugError) {
        if (context == null || plugError == null) {
            return;
        }
        String str = plugError.errorMessage;
        if (!TextUtils.isEmpty(str) && str.startsWith("Scope")) {
            b.a(context);
        }
        final PendingRequest<T> pendingRequest = this.pendingRequest;
        b.a(context, this.builder.url(), new b.InterfaceC0067b() { // from class: com.naver.plug.android.core.api.request.Request.4
            @Override // com.naver.plug.android.core.b.InterfaceC0067b
            public void onLoggedIn(boolean z) {
                if (pendingRequest != null) {
                    if (z) {
                        pendingRequest.retry();
                    } else {
                        pendingRequest.cancel();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlugError makePlugError(VolleyError volleyError) {
        PlugError errorBody = parseErrorBody(volleyError);
        return errorBody != null ? errorBody : PlugError.newNetworkError();
    }

    private static PlugError parseErrorBody(VolleyError volleyError) {
        if (volleyError == null || volleyError.networkResponse == null || volleyError.networkResponse.data == null || volleyError.networkResponse.headers == null) {
            return null;
        }
        try {
            return ((Response) new Gson().fromJson(new String(volleyError.networkResponse.data, HttpHeaderParser.parseCharset(volleyError.networkResponse.headers)), Response.class)).getError();
        } catch (Exception e) {
            Log.d(TAG, "", e);
            return null;
        }
    }

    public void cancel() {
        if (this.pendingRequest == null || this.pendingRequest.volleyRequest == null) {
            return;
        }
        this.pendingRequest.volleyRequest.cancel();
    }

    public Request<T> checkNetworkConnected(boolean z) {
        this.builder.checkNetworkConnected(z);
        return this;
    }

    public void execute(Context context) {
        execute(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void execute(final Context context, final int i, final RequestListener<T> requestListener) {
        if (context == null) {
            return;
        }
        if (this.builder.checkNetworkConnected() && !com.naver.plug.android.core.a.b.a(context)) {
            b.a(context, b.b());
            return;
        }
        beforeExecute(requestListener);
        this.pendingRequest = new PendingRequest<>(this, context, requestListener, i);
        this.pendingRequest.volleyRequest = this.builder.buildRequest(VolleyQueue.getRequestQueue(context), new com.android.volley.Response.Listener<T>() { // from class: com.naver.plug.android.core.api.request.Request.2
            @Override // com.android.volley.Response.Listener
            public void onResponse(T t) {
                if (t == null) {
                    Request.this.pendingRequest = null;
                } else if (Request.this.canRetryError(i, t.getError())) {
                    Request.this.executeRetry(context, t.getError());
                    Request.this.pendingRequest = null;
                } else {
                    Request.this.afterExecute(requestListener, t, null);
                    Request.this.pendingRequest = null;
                }
            }
        }, new com.android.volley.Response.ErrorListener() { // from class: com.naver.plug.android.core.api.request.Request.3
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                PlugError plugErrorMakePlugError = Request.this.makePlugError(volleyError);
                if (Request.this.canRetryError(i, plugErrorMakePlugError)) {
                    Request.this.executeRetry(context, plugErrorMakePlugError);
                } else {
                    Request.this.afterExecute(requestListener, null, plugErrorMakePlugError);
                }
                Request.this.pendingRequest = null;
            }
        });
    }

    public void execute(Context context, RequestListener<T> requestListener) {
        execute(context, 1, requestListener);
    }

    public Map<String, String> getHeaders() {
        return this.builder.headers();
    }

    public Object getTag() {
        return this.tag;
    }

    public String getUrl() {
        return this.builder.url();
    }

    public boolean isCanceled() {
        return (this.pendingRequest == null || this.pendingRequest.volleyRequest == null || !this.pendingRequest.volleyRequest.isCanceled()) ? false : true;
    }

    public void setRetryLoginError(boolean z) {
        this.retryLoginError = z;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Request<T> showProgress(boolean z) {
        this.builder.showProgress(z);
        return this;
    }
}
