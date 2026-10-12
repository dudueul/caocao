package com.nexon.platform.store.internal;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.nexon.platform.store.billing.Logger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class JsonRequest extends Request<JSONObject> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private final Map<String, String> mHeader;
    private final Response.Listener<JSONObject> mListener;
    private final Map<String, String> mParameters;
    private static final String TAG = JsonRequest.class.getName();
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", "utf-8");

    public JsonRequest(int i, String str, Map<String, String> map, Map<String, String> map2, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
        this.mHeader = map;
        this.mParameters = map2;
        Logger.d(TAG, "*** [ request url:" + str + "] \n param:" + this.mParameters.toString());
    }

    private String encodeParameters(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(JSONObject jSONObject) {
        this.mListener.onResponse(jSONObject);
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() {
        return this.mHeader;
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.mParameters;
    }

    @Override // com.android.volley.Request
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Override // com.android.volley.Request
    public String getUrl() {
        return getMethod() == 0 ? super.getUrl() + "?" + encodeParameters(this.mParameters, "utf-8") : super.getUrl();
    }

    @Override // com.android.volley.Request
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e2) {
            return Response.error(new ParseError(e2));
        }
    }
}
