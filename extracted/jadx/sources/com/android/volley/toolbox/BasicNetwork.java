package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BasicNetwork implements Network {
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    private static int DEFAULT_POOL_SIZE = 4096;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry == null) {
            return;
        }
        if (entry.etag != null) {
            map.put(HttpRequest.HEADER_IF_NONE_MATCH, entry.etag);
        }
        if (entry.lastModified > 0) {
            map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws ServerError, IOException {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] buf = this.mPool.getBuf(1024);
            while (true) {
                int i = content.read(buf);
                if (i == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, i);
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException e2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > SLOW_REQUEST_THRESHOLD_MS) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    protected void logError(String str, String str2, long j) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        byte[] bArrEntityToBytes;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            HttpResponse httpResponsePerformRequest = null;
            Map<String, String> mapEmptyMap = Collections.emptyMap();
            try {
                HashMap map = new HashMap();
                addCacheHeaders(map, request.getCacheEntry());
                httpResponsePerformRequest = this.mHttpStack.performRequest(request, map);
                StatusLine statusLine = httpResponsePerformRequest.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                mapEmptyMap = convertHeaders(httpResponsePerformRequest.getAllHeaders());
                if (statusCode == 304) {
                    Cache.Entry cacheEntry = request.getCacheEntry();
                    if (cacheEntry == null) {
                        return new NetworkResponse(HttpResponseCode.NOT_MODIFIED, null, mapEmptyMap, true, SystemClock.elapsedRealtime() - jElapsedRealtime);
                    }
                    cacheEntry.responseHeaders.putAll(mapEmptyMap);
                    return new NetworkResponse(HttpResponseCode.NOT_MODIFIED, cacheEntry.data, cacheEntry.responseHeaders, true, SystemClock.elapsedRealtime() - jElapsedRealtime);
                }
                bArrEntityToBytes = httpResponsePerformRequest.getEntity() != null ? entityToBytes(httpResponsePerformRequest.getEntity()) : new byte[0];
                try {
                    logSlowRequests(SystemClock.elapsedRealtime() - jElapsedRealtime, request, bArrEntityToBytes, statusLine);
                    if (statusCode < 200 || statusCode > 299) {
                        throw new IOException();
                    }
                    return new NetworkResponse(statusCode, bArrEntityToBytes, mapEmptyMap, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                } catch (MalformedURLException e) {
                    e = e;
                    String strValueOf = String.valueOf(request.getUrl());
                    throw new RuntimeException(strValueOf.length() != 0 ? "Bad URL ".concat(strValueOf) : new String("Bad URL "), e);
                } catch (SocketTimeoutException e2) {
                    attemptRetryOnException("socket", request, new TimeoutError());
                } catch (ConnectTimeoutException e3) {
                    attemptRetryOnException("connection", request, new TimeoutError());
                } catch (IOException e4) {
                    e = e4;
                    if (httpResponsePerformRequest == null) {
                        throw new NoConnectionError(e);
                    }
                    int statusCode2 = httpResponsePerformRequest.getStatusLine().getStatusCode();
                    VolleyLog.e("Unexpected response code %d for %s", Integer.valueOf(statusCode2), request.getUrl());
                    if (bArrEntityToBytes == null) {
                        throw new NetworkError((NetworkResponse) null);
                    }
                    NetworkResponse networkResponse = new NetworkResponse(statusCode2, bArrEntityToBytes, mapEmptyMap, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                    if (statusCode2 != 401 && statusCode2 != 403) {
                        throw new ServerError(networkResponse);
                    }
                    attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
                }
            } catch (MalformedURLException e5) {
                e = e5;
            } catch (SocketTimeoutException e6) {
            } catch (ConnectTimeoutException e7) {
            } catch (IOException e8) {
                e = e8;
                bArrEntityToBytes = null;
            }
        }
    }
}
