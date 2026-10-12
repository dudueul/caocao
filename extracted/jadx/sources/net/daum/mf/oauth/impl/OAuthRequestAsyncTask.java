package net.daum.mf.oauth.impl;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.daum.mf.oauth.MobileOAuthLibrary;
import net.daum.mf.oauth.OAuthError;
import net.daum.mf.oauth.gen.DaumOAuthSDKAndroidMeta;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class OAuthRequestAsyncTask extends AsyncTask<Void, Void, OAuthRequestResult> {
    private final String accessToken;
    private final Context context;
    private boolean isCanceled;
    private final MobileOAuthLibrary.OAuthListener oAuthListener;
    private final String path;
    private String userAgent;

    class OAuthRequestResult {
        String contentString;
        int statusCode;

        OAuthRequestResult() {
        }

        public String toString() {
            return "OAuthRequestResult{statusCode=" + this.statusCode + ", contentString='" + this.contentString + "'}";
        }
    }

    public OAuthRequestAsyncTask(Context context, MobileOAuthLibrary.OAuthListener oAuthListener, String str, String str2) {
        this.context = context.getApplicationContext();
        this.oAuthListener = oAuthListener;
        this.path = str;
        this.accessToken = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public OAuthRequestResult doInBackground(Void... voidArr) {
        OAuthRequestResult oAuthRequestResult = new OAuthRequestResult();
        if (NetworkConnectionUtils.isNetworkConnected(this.context)) {
            String str = this.path.startsWith("http") ? this.path : Constant.URL_BASE_OPEN_API + this.path;
            String str2 = (str.contains("?") ? "&" : "?") + String.format(Constant.PARAM_TEMPLITE, "access_token", this.accessToken);
            Logging.debug("url : " + str + str2);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            try {
                HttpParams params = defaultHttpClient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 30000);
                HttpConnectionParams.setSoTimeout(params, 30000);
                HttpGet httpGet = new HttpGet(str + str2);
                httpGet.setHeader("User-Agent", this.userAgent);
                HttpResponse httpResponseExecute = defaultHttpClient.execute(httpGet);
                HttpEntity entity = httpResponseExecute.getEntity();
                oAuthRequestResult.statusCode = httpResponseExecute.getStatusLine().getStatusCode();
                if (oAuthRequestResult.statusCode >= 200 && oAuthRequestResult.statusCode < 300) {
                    oAuthRequestResult.contentString = getContentString(entity.getContent(), Constant.DEFAULT_ENCODING, (int) entity.getContentLength());
                }
            } catch (ClientProtocolException e) {
                oAuthRequestResult.statusCode = HttpResponseCode.BAD_REQUEST;
            } catch (IOException e2) {
                oAuthRequestResult.statusCode = 408;
            }
        } else {
            oAuthRequestResult.statusCode = 0;
        }
        return oAuthRequestResult;
    }

    public String getContentString(InputStream inputStream, String str, int i) {
        String string = null;
        try {
            StringBuilder sb = i > 0 ? new StringBuilder(i) : new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\n");
            }
            string = sb.toString();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("DaumOAuthSDK", null, e);
                }
            }
        } catch (Exception e2) {
            Log.e("DaumOAuthSDK", null, e2);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    Log.e("DaumOAuthSDK", null, e3);
                }
            }
        }
        return string;
    }

    public String getUserAgent(Context context) {
        WebView webView = new WebView(context);
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.destroy();
        return String.format("%s DaumOAuthSDK/%s", userAgentString, DaumOAuthSDKAndroidMeta.getVersion());
    }

    public String getVersion(Context context) {
        Application application = (Application) context.getApplicationContext();
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
        } catch (Exception e) {
            Log.e("DaumOAuthSDK", null, e);
            return null;
        }
    }

    public boolean isSuccessStatusCode(int i) {
        return i >= 200 && i < 300;
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        this.isCanceled = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(OAuthRequestResult oAuthRequestResult) {
        Logging.debug("onPostExecute : " + oAuthRequestResult);
        if (this.isCanceled) {
            Logging.debug("OAuthRequestResult is canceled");
        }
        switch (oAuthRequestResult.statusCode) {
            case 0:
            case 408:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorNetwork, OAuthError.OAuthErrorMessageNework);
                break;
            case 200:
                this.oAuthListener.onRequestResourceSuccess(oAuthRequestResult.contentString);
                break;
            case HttpResponseCode.BAD_REQUEST /* 400 */:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorInvalidResourceRequest, OAuthError.OAuthErrorMessageInvalidResourceRequest);
                break;
            case HttpResponseCode.UNAUTHORIZED /* 401 */:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorInvalidToken, OAuthError.OAuthErrorMessageInvalidToken);
                break;
            case HttpResponseCode.FORBIDDEN /* 403 */:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorInsufficientScope, OAuthError.OAuthErrorMessageInsufficientScope);
                break;
            case HttpResponseCode.NOT_FOUND /* 404 */:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorServiceNotFound, OAuthError.OAuthErrorMessageServiceNotFounde);
                break;
            case 500:
            case 501:
            case HttpResponseCode.BAD_GATEWAY /* 502 */:
            case HttpResponseCode.SERVICE_UNAVAILABLE /* 503 */:
            case HttpResponseCode.GATEWAY_TIMEOUT /* 504 */:
            case 505:
            case 507:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorServer, OAuthError.OAuthErrorMessageServer);
                break;
            default:
                this.oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorUnknown, OAuthError.OAuthErrorMessageUnknown);
                break;
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.userAgent = getUserAgent(this.context);
    }
}
