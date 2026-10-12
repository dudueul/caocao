package com.nhn.android.naverlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.GraphResponse;
import com.nhn.android.naverlogin.connection.CommonConnection;
import com.nhn.android.naverlogin.connection.NetworkState;
import com.nhn.android.naverlogin.connection.OAuthLoginConnection;
import com.nhn.android.naverlogin.connection.ResponseData;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.data.OAuthLoginPreferenceManager;
import com.nhn.android.naverlogin.data.OAuthLoginState;
import com.nhn.android.naverlogin.data.OAuthLoginString;
import com.nhn.android.naverlogin.data.OAuthResponse;
import com.nhn.android.naverlogin.ui.OAuthLoginActivity;
import com.nhn.android.naverlogin.ui.OAuthLoginDialogMng;
import com.nhn.android.naverlogin.util.DeviceAppInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLogin {
    private static final String TAG = "NaverLoginOAuth|OAuthLogin";
    public static OAuthLoginHandler mOAuthLoginHandler;
    private static OAuthLogin sInstance;

    private class OAuthLoginTask extends AsyncTask<Void, Void, String> {
        private Context _context;
        private OAuthLoginDialogMng mDialogMng = new OAuthLoginDialogMng();

        OAuthLoginTask(Context context) {
            this._context = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            return OAuthLogin.this.refreshAccessToken(this._context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            try {
                this.mDialogMng.hideProgressDlg();
            } catch (Exception e) {
            }
            if (!TextUtils.isEmpty(str)) {
                OAuthLogin.mOAuthLoginHandler.run(true);
            } else {
                this._context.startActivity(new Intent(this._context, (Class<?>) OAuthLoginActivity.class));
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mDialogMng.showProgressDlg(this._context, OAuthLoginString.naveroauthlogin_string_getting_token.getString(this._context), null);
        }
    }

    class OAuthNaverAppInstallMethod {
        static final int TYPE_INSTALL = 145;
        static final int TYPE_UPDATE = 144;

        OAuthNaverAppInstallMethod() {
        }
    }

    private OAuthLogin() {
    }

    public static OAuthLogin getInstance() {
        if (sInstance == null) {
            sInstance = new OAuthLogin();
        }
        return sInstance;
    }

    public static String getVersion() {
        return OAuthLoginDefine.VERSION;
    }

    private boolean valid(Context context) {
        if (context == null) {
            Log.i(TAG, "context is null");
            return false;
        }
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        if (TextUtils.isEmpty(oAuthLoginPreferenceManager.getClientId())) {
            Log.i(TAG, "CliendId is null");
            return false;
        }
        if (!TextUtils.isEmpty(oAuthLoginPreferenceManager.getClientSecret())) {
            return true;
        }
        Log.i(TAG, "CliendSecret is null");
        return false;
    }

    public String getAccessToken(Context context) {
        String accessToken = new OAuthLoginPreferenceManager(context).getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            return null;
        }
        return accessToken;
    }

    public long getExpiresAt(Context context) {
        return new OAuthLoginPreferenceManager(context).getExpiresAt();
    }

    public OAuthErrorCode getLastErrorCode(Context context) {
        return new OAuthLoginPreferenceManager(context).getLastErrorCode();
    }

    public String getLastErrorDesc(Context context) {
        return new OAuthLoginPreferenceManager(context).getLastErrorDesc();
    }

    public String getRefreshToken(Context context) {
        String refreshToken = new OAuthLoginPreferenceManager(context).getRefreshToken();
        if (TextUtils.isEmpty(refreshToken)) {
            return null;
        }
        return refreshToken;
    }

    public OAuthLoginState getState(Context context) {
        if (!valid(context)) {
            return OAuthLoginState.NEED_INIT;
        }
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        String accessToken = oAuthLoginPreferenceManager.getAccessToken();
        String refreshToken = oAuthLoginPreferenceManager.getRefreshToken();
        if (TextUtils.isEmpty(accessToken)) {
            return TextUtils.isEmpty(refreshToken) ? OAuthLoginState.NEED_LOGIN : OAuthLoginState.NEED_REFRESH_TOKEN;
        }
        return OAuthLoginState.OK;
    }

    public String getTokenType(Context context) {
        String tokenType = new OAuthLoginPreferenceManager(context).getTokenType();
        if (TextUtils.isEmpty(tokenType)) {
            return null;
        }
        return tokenType;
    }

    public void init(Context context, String str, String str2, String str3) {
        init(context, str, str2, str3, DeviceAppInfo.getPackageName(context));
    }

    @Deprecated
    public void init(Context context, String str, String str2, String str3, String str4) {
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        oAuthLoginPreferenceManager.setClientId(str);
        oAuthLoginPreferenceManager.setClientSecret(str2);
        oAuthLoginPreferenceManager.setClientName(str3);
        oAuthLoginPreferenceManager.setCallbackUrl(str4);
        oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.NONE);
        oAuthLoginPreferenceManager.setLastErrorDesc("");
    }

    public void logout(Context context) {
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        oAuthLoginPreferenceManager.setAccessToken("");
        oAuthLoginPreferenceManager.setRefreshToken("");
        oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.NONE);
        oAuthLoginPreferenceManager.setLastErrorDesc("");
    }

    public boolean logoutAndDeleteToken(Context context) {
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        String clientId = oAuthLoginPreferenceManager.getClientId();
        String clientSecret = oAuthLoginPreferenceManager.getClientSecret();
        String accessToken = oAuthLoginPreferenceManager.getAccessToken();
        logout(context);
        try {
            OAuthResponse oAuthResponseDeleteToken = OAuthLoginConnection.deleteToken(context, clientId, clientSecret, accessToken);
            if (GraphResponse.SUCCESS_KEY.equalsIgnoreCase(oAuthResponseDeleteToken.getResultValue())) {
                return true;
            }
            oAuthLoginPreferenceManager.setLastErrorCode(oAuthResponseDeleteToken.getErrorCode());
            oAuthLoginPreferenceManager.setLastErrorDesc(oAuthResponseDeleteToken.getErrorDesc());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.ERROR_NO_CATAGORIZED);
            oAuthLoginPreferenceManager.setLastErrorDesc(e.getMessage());
            return false;
        }
    }

    public String refreshAccessToken(Context context) {
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(context);
        OAuthResponse oAuthResponseRequestRefreshToken = OAuthLoginConnection.requestRefreshToken(context, oAuthLoginPreferenceManager.getClientId(), oAuthLoginPreferenceManager.getClientSecret(), oAuthLoginPreferenceManager.getRefreshToken());
        String accessToken = oAuthResponseRequestRefreshToken.getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            return null;
        }
        oAuthLoginPreferenceManager.setAccessToken(oAuthResponseRequestRefreshToken.getAccessToken());
        oAuthLoginPreferenceManager.setExpiresAt((System.currentTimeMillis() / 1000) + oAuthResponseRequestRefreshToken.getExpiresIn());
        return accessToken;
    }

    public String requestApi(Context context, String str, String str2) {
        String str3 = "bearer " + str;
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "at:" + str + ", url:" + str2);
            Log.d(TAG, "header:" + str3);
        }
        ResponseData responseDataRequest = CommonConnection.request(context, str2, (String) null, (String) null, str3);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "res.statuscode" + responseDataRequest.mStatusCode);
            Log.d(TAG, "res.content" + responseDataRequest.mContent);
        }
        if (responseDataRequest == null) {
            return null;
        }
        return responseDataRequest.mContent;
    }

    public void setMarketLinkWorking(boolean z) {
        OAuthLoginDefine.MARKET_LINK_WORKING = z;
    }

    public void startOauthLoginActivity(final Activity activity, final OAuthLoginHandler oAuthLoginHandler) {
        if (NetworkState.checkConnectivity(activity, true, new NetworkState.RetryListener() { // from class: com.nhn.android.naverlogin.OAuthLogin.1
            @Override // com.nhn.android.naverlogin.connection.NetworkState.RetryListener
            public void onResult(boolean z) {
                if (z) {
                    OAuthLogin.this.startOauthLoginActivity(activity, oAuthLoginHandler);
                }
            }
        })) {
            mOAuthLoginHandler = oAuthLoginHandler;
            if (TextUtils.isEmpty(getRefreshToken(activity))) {
                activity.startActivity(new Intent(activity, (Class<?>) OAuthLoginActivity.class));
            } else {
                new OAuthLoginTask(activity).execute(new Void[0]);
            }
        }
    }
}
