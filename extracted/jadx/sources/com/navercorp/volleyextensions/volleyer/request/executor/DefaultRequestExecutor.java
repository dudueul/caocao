package com.navercorp.volleyextensions.volleyer.request.executor;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultRequestExecutor implements RequestExecutor {
    private <T> void deliverError(Request<T> request, String str) {
        request.deliverError(new VolleyError(str));
    }

    @Override // com.navercorp.volleyextensions.volleyer.request.executor.RequestExecutor
    public <T> void executeRequest(RequestQueue requestQueue, Request<T> request) {
        if (requestQueue == null) {
            deliverError(request, "RequestQueue is null. It cannot execute the request of " + request.toString() + ".");
        } else {
            requestQueue.add(request);
        }
    }
}
