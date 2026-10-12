package com.naver.plug.android.core.api.request;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.navercorp.volleyextensions.volleyer.factory.DefaultRequestQueueFactory;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VolleyQueue {
    private static VolleyQueue sharedInstance;
    private Context applicationContext;
    private RequestQueue requestQueue = getRequestQueue();

    private VolleyQueue(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    public static synchronized VolleyQueue getInstance(Context context) {
        if (sharedInstance == null) {
            sharedInstance = new VolleyQueue(context);
        }
        return sharedInstance;
    }

    static RequestQueue getRequestQueue(Context context) {
        return getInstance(context).getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = DefaultRequestQueueFactory.create(this.applicationContext);
            this.requestQueue.start();
        }
        return this.requestQueue;
    }
}
