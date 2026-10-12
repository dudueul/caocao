package com.nhn.android.naverlogin.connection;

import android.util.Log;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ResponseData {
    private final String TAG = "NaverLoginOAuth|ResponseData";
    public ResponseDataStat mStat = ResponseDataStat.SUCCESS;
    public int mStatusCode = -1;
    public String mContent = "";
    public String mCookie = "";
    public String mErrorDetail = "";

    public enum ResponseDataStat {
        SUCCESS("SUCCESS", null),
        BUSY("BUSY", "BUSY"),
        CANCEL("CANCEL", "CANCEL"),
        URL_ERROR("URL_ERROR", "URL_ERROR"),
        CONNECTION_TIMEOUT("CONNECTION_TIMEOUT", "CONNECTION_TIMEOUT"),
        CONNECTION_FAIL("CONNECTION_FAIL", "CONNECTION_FAIL"),
        EXCEPTION_FAIL("EXCEPTION_FAIL", "EXCEPTION_FAIL"),
        NO_PEER_CERTIFICATE("NO_PEER_CERTIFICATE", "NO_PEER_CERTIFICATE"),
        FAIL("FAIL", "FAIL");

        private String stat;

        ResponseDataStat(String str, String str2) {
            this.stat = str;
        }

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static ResponseDataStat[] valuesCustom() {
            ResponseDataStat[] responseDataStatArrValuesCustom = values();
            int length = responseDataStatArrValuesCustom.length;
            ResponseDataStat[] responseDataStatArr = new ResponseDataStat[length];
            System.arraycopy(responseDataStatArrValuesCustom, 0, responseDataStatArr, 0, length);
            return responseDataStatArr;
        }

        public String getValue() {
            return this.stat;
        }
    }

    private String getContent(InputStream inputStream) {
        BufferedReader bufferedReader;
        StringBuilder sb;
        char[] cArr = new char[1024];
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                try {
                    int i = bufferedReader.read(cArr, 0, 1024);
                    if (i <= 0) {
                        break;
                    }
                    sb.append(cArr, 0, i);
                } catch (IOException e) {
                    setResultCode(ResponseDataStat.EXCEPTION_FAIL, "getContent()-IOException:" + e.getMessage());
                } catch (RuntimeException e2) {
                    setResultCode(ResponseDataStat.EXCEPTION_FAIL, "getContent()-RuntimeException:" + e2.getMessage());
                }
            }
        } catch (UnsupportedEncodingException e3) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        sb = new StringBuilder();
        bufferedReader.close();
        return sb.toString();
    }

    public void setResponseData(HttpResponse httpResponse, String str) {
        if (str != null) {
            this.mCookie = str;
        }
        if (httpResponse == null) {
            Log.e("NaverLoginOAuth|ResponseData", "error : httpResponse is null !!");
            setResultCode(ResponseDataStat.FAIL, "setResponseData() - httpResponse is null");
            return;
        }
        try {
            this.mContent = getContent(httpResponse.getEntity().getContent());
            this.mStatusCode = httpResponse.getStatusLine().getStatusCode();
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d("NaverLoginOAuth|ResponseData", "headers:" + httpResponse.getAllHeaders().toString());
                Log.d("NaverLoginOAuth|ResponseData", "status:" + httpResponse.getStatusLine().toString());
            }
        } catch (IllegalStateException e) {
            setResultCode(ResponseDataStat.EXCEPTION_FAIL, "setResponseData()-IllegalStateException:" + e.getMessage());
        } catch (Exception e2) {
            setResultCode(ResponseDataStat.EXCEPTION_FAIL, "setResponseData()-Exception:" + e2.getMessage());
        }
    }

    public void setResultCode(ResponseDataStat responseDataStat, String str) {
        this.mStat = responseDataStat;
        this.mErrorDetail = str;
    }

    public String toString() {
        return "Statuscode:" + this.mStatusCode + ", Content:" + this.mContent + ", Cookie:" + this.mCookie + ", ErrorDetail:" + this.mErrorDetail;
    }
}
