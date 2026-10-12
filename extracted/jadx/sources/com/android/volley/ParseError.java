package com.android.volley;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ParseError extends VolleyError {
    public ParseError() {
    }

    public ParseError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ParseError(Throwable th) {
        super(th);
    }
}
