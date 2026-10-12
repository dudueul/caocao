package com.android.volley;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NetworkError extends VolleyError {
    public NetworkError() {
    }

    public NetworkError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public NetworkError(Throwable th) {
        super(th);
    }
}
