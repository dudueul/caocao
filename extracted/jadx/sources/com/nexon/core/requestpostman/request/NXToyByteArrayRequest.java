package com.nexon.core.requestpostman.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyByteArrayRequest extends Request<byte[]> {
    private final Response.Listener<byte[]> mListener;

    public NXToyByteArrayRequest(int i, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(byte[] bArr) {
        this.mListener.onResponse(bArr);
    }

    @Override // com.android.volley.Request
    protected Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
