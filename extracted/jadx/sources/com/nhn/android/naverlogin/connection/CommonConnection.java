package com.nhn.android.naverlogin.connection;

import android.content.Context;
import android.util.Log;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.util.CookieUtil;
import com.nhn.android.naverlogin.util.DeviceAppInfo;
import io.fabric.sdk.android.services.network.HttpRequest;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CommonConnection {
    private static final String TAG = "NaverLoginOAuth|CommonConnection";
    protected static boolean mCancel;
    protected static DefaultHttpClient mHttpClient = null;

    public static void cancel() {
        mCancel = true;
        if (mHttpClient != null) {
            Log.e(TAG, "httpclient operation canceled (shutdown)");
            mHttpClient.getConnectionManager().shutdown();
            mHttpClient = null;
        }
    }

    private static DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpParams params = defaultHttpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, OAuthLoginDefine.TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, OAuthLoginDefine.TIMEOUT);
        ConnManagerParams.setTimeout(params, OAuthLoginDefine.TIMEOUT);
        return defaultHttpClient;
    }

    public static DefaultHttpClient getDefaultHttpClient(Context context) {
        return getDefaultHttpClient(DeviceAppInfo.getBaseInstance().getUserAgent(context));
    }

    protected static DefaultHttpClient getDefaultHttpClient(String str) {
        DefaultHttpClient defaultHttpClient = getDefaultHttpClient();
        defaultHttpClient.getParams().setParameter("http.useragent", str);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "user-agent:" + str);
        }
        return defaultHttpClient;
    }

    public static boolean isBusy() {
        return mHttpClient != null;
    }

    public static ResponseData request(Context context, String str, String str2, String str3) {
        return request(context, str, str2, str3, false);
    }

    public static ResponseData request(Context context, String str, String str2, String str3, String str4) {
        return request(context, str, str2, str3, str4, false);
    }

    public static ResponseData request(Context context, String str, String str2, String str3, String str4, boolean z) {
        HttpResponse httpResponseExecute = null;
        ResponseData responseData = new ResponseData();
        DefaultHttpClient defaultHttpClient = null;
        synchronized (CommonConnection.class) {
            if (!z) {
                if (mHttpClient != null) {
                    responseData.setResultCode(ResponseData.ResponseDataStat.BUSY, "HttpClient already in use.");
                    return responseData;
                }
            }
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "request url : " + str);
            }
            if (str == null || str.length() == 0) {
                responseData.setResultCode(ResponseData.ResponseDataStat.URL_ERROR, "strRequestUrl is null");
                return responseData;
            }
            if (z) {
                defaultHttpClient = (str3 == null || str3.length() <= 0) ? getDefaultHttpClient(context) : getDefaultHttpClient(str3);
            } else if (str3 == null || str3.length() <= 0) {
                mHttpClient = getDefaultHttpClient(context);
            } else {
                mHttpClient = getDefaultHttpClient(str3);
            }
            mCancel = false;
            try {
                HttpGet httpGet = new HttpGet(str);
                if (str2 != null) {
                    try {
                        if (str2.length() > 0) {
                            httpGet.addHeader("Cookie", str2);
                        }
                    } catch (SSLPeerUnverifiedException e) {
                        e = e;
                        responseData.setResultCode(ResponseData.ResponseDataStat.NO_PEER_CERTIFICATE, e.getMessage());
                        e.printStackTrace();
                    } catch (ConnectTimeoutException e2) {
                        e = e2;
                        responseData.setResultCode(ResponseData.ResponseDataStat.CONNECTION_TIMEOUT, e.getMessage());
                        e.printStackTrace();
                    } catch (Exception e3) {
                        e = e3;
                        responseData.setResultCode(ResponseData.ResponseDataStat.CONNECTION_FAIL, e.getMessage());
                        e.printStackTrace();
                    }
                }
                if (str4 != null) {
                    httpGet.removeHeaders(HttpRequest.HEADER_AUTHORIZATION);
                    httpGet.setHeader(HttpRequest.HEADER_AUTHORIZATION, str4);
                    if (OAuthLoginDefine.DEVELOPER_VERSION) {
                        Log.d(TAG, "header:" + str4);
                    }
                }
                httpResponseExecute = z ? defaultHttpClient.execute(httpGet) : mHttpClient.execute(httpGet);
            } catch (SSLPeerUnverifiedException e4) {
                e = e4;
            } catch (ConnectTimeoutException e5) {
                e = e5;
            } catch (Exception e6) {
                e = e6;
            }
            if (httpResponseExecute != null) {
                try {
                    responseData.setResponseData(httpResponseExecute, CookieUtil.getCookie(httpResponseExecute));
                } catch (Exception e7) {
                    responseData.setResultCode(ResponseData.ResponseDataStat.FAIL, "setResponseData() on request() failed :" + e7.getMessage());
                    e7.printStackTrace();
                }
            }
            try {
                try {
                    if (z) {
                        defaultHttpClient.getConnectionManager().shutdown();
                    } else {
                        mHttpClient.getConnectionManager().shutdown();
                    }
                    if (!z) {
                        mHttpClient = null;
                    }
                } catch (Exception e8) {
                    e8.printStackTrace();
                    if (!z) {
                        mHttpClient = null;
                    }
                }
                if (!mCancel) {
                    return responseData;
                }
                ResponseData responseData2 = new ResponseData();
                responseData2.setResultCode(ResponseData.ResponseDataStat.CANCEL, "User cancel");
                return responseData2;
            } catch (Throwable th) {
                if (!z) {
                    mHttpClient = null;
                }
                throw th;
            }
        }
    }

    public static ResponseData request(Context context, String str, String str2, String str3, boolean z) {
        return request(context, str, str2, str3, null, z);
    }
}
