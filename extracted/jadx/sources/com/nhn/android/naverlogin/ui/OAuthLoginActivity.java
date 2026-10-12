package com.nhn.android.naverlogin.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.connection.OAuthLoginConnection;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.data.OAuthIntent;
import com.nhn.android.naverlogin.data.OAuthLoginData;
import com.nhn.android.naverlogin.data.OAuthLoginPreferenceManager;
import com.nhn.android.naverlogin.data.OAuthLoginString;
import com.nhn.android.naverlogin.data.OAuthResponse;
import com.nhn.android.naverlogin.util.DeviceAppInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginActivity extends Activity {
    private static int REQUESTCODE_LOGIN = 100;
    private static final String TAG = "NaverLoginOAuth|OAuthLoginActivity";
    private String mClientName;
    private Context mContext;
    private OAuthLoginDialogMng mDialogMng = new OAuthLoginDialogMng();
    private boolean mIsLoginActivityStarted = false;
    private OAuthLoginData mOAuthLoginData;

    private class GetAccessTokenTask extends AsyncTask<Void, Void, OAuthResponse> {
        private GetAccessTokenTask() {
        }

        /* synthetic */ GetAccessTokenTask(OAuthLoginActivity oAuthLoginActivity, GetAccessTokenTask getAccessTokenTask) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public OAuthResponse doInBackground(Void... voidArr) {
            try {
                return OAuthLoginConnection.requestAccessToken(OAuthLoginActivity.this.mContext, OAuthLoginActivity.this.mOAuthLoginData.getClientId(), OAuthLoginActivity.this.mOAuthLoginData.getClientSecret(), OAuthLoginActivity.this.mOAuthLoginData.getState(), OAuthLoginActivity.this.mOAuthLoginData.getCode());
            } catch (Exception e) {
                return new OAuthResponse(OAuthErrorCode.CLIENT_ERROR_CONNECTION_ERROR);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(OAuthResponse oAuthResponse) {
            try {
                OAuthLoginActivity.this.mDialogMng.hideProgressDlg();
            } catch (Exception e) {
            }
            try {
                Intent intent = new Intent();
                OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(OAuthLoginActivity.this.mContext);
                if (oAuthResponse.isSuccess()) {
                    oAuthLoginPreferenceManager.setAccessToken(oAuthResponse.getAccessToken());
                    oAuthLoginPreferenceManager.setRefreshToken(oAuthResponse.getRefreshToken());
                    oAuthLoginPreferenceManager.setExpiresAt((System.currentTimeMillis() / 1000) + oAuthResponse.getExpiresIn());
                    oAuthLoginPreferenceManager.setTokenType(oAuthResponse.getTokenType());
                    oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.NONE);
                    oAuthLoginPreferenceManager.setLastErrorDesc(OAuthErrorCode.NONE.getDesc());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_ACCESS_TOKEN, oAuthResponse.getAccessToken());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_REFRESH_TOKEN, oAuthResponse.getRefreshToken());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_EXPIRES_IN, oAuthResponse.getExpiresIn());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_TOKEN_TYPE, oAuthResponse.getTokenType());
                    OAuthLoginActivity.this.setResult(-1, intent);
                } else {
                    if (oAuthResponse.getErrorCode() == OAuthErrorCode.NONE) {
                        OAuthLoginActivity.this.finishWithErrorResult(OAuthErrorCode.CLIENT_USER_CANCEL);
                        return;
                    }
                    oAuthLoginPreferenceManager.setLastErrorCode(oAuthResponse.getErrorCode());
                    oAuthLoginPreferenceManager.setLastErrorDesc(oAuthResponse.getErrorDesc());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE, oAuthResponse.getErrorCode().getCode());
                    intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION, oAuthResponse.getErrorDesc());
                    OAuthLoginActivity.this.setResult(0, intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            OAuthLoginActivity.this.finish();
            try {
                OAuthLoginActivity.this.propagationResult(oAuthResponse.isSuccess());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            try {
                OAuthLoginActivity.this.mDialogMng.showProgressDlg(OAuthLoginActivity.this.mContext, OAuthLoginString.naveroauthlogin_string_getting_token.getString(OAuthLoginActivity.this.mContext), null);
            } catch (Exception e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithErrorResult(OAuthErrorCode oAuthErrorCode) {
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d("GILSUB", "Login finishWithErrorResult()");
        }
        Intent intent = new Intent();
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(this.mContext);
        oAuthLoginPreferenceManager.setLastErrorCode(oAuthErrorCode);
        oAuthLoginPreferenceManager.setLastErrorDesc(oAuthErrorCode.getDesc());
        intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE, oAuthErrorCode.getCode());
        intent.putExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION, oAuthErrorCode.getDesc());
        setResult(0, intent);
        finish();
        propagationResult(false);
    }

    private boolean initData(Bundle bundle) {
        this.mContext = this;
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(this.mContext);
        String clientId = oAuthLoginPreferenceManager.getClientId();
        String clientSecret = oAuthLoginPreferenceManager.getClientSecret();
        String callbackUrl = oAuthLoginPreferenceManager.getCallbackUrl();
        String string = bundle == null ? null : bundle.getString("OAuthLoginData_state");
        this.mClientName = oAuthLoginPreferenceManager.getClientName();
        if (TextUtils.isEmpty(clientId)) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_ERROR_NO_CLIENTID);
            return false;
        }
        if (TextUtils.isEmpty(clientSecret)) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_ERROR_NO_CLIENTSECRET);
            return false;
        }
        if (TextUtils.isEmpty(this.mClientName)) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_ERROR_NO_CLIENTNAME);
            return false;
        }
        if (TextUtils.isEmpty(callbackUrl)) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_ERROR_NO_CALLBACKURL);
            return false;
        }
        this.mOAuthLoginData = new OAuthLoginData(clientId, clientSecret, callbackUrl, string);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void propagationResult(boolean z) {
        if (OAuthLogin.mOAuthLoginHandler != null) {
            Message message = new Message();
            message.what = z ? 1 : 0;
            OAuthLogin.mOAuthLoginHandler.sendMessage(message);
        }
    }

    private void runOnlyOnce() {
        if (this.mOAuthLoginData == null) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_ERROR_NO_CLIENTID);
        } else {
            startLoginActivity();
        }
    }

    private void startLoginActivity() {
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "startLoginActivity()");
        }
        try {
            Intent intent = new Intent();
            intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CLIENT_ID, this.mOAuthLoginData.getClientId());
            intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CALLBACK_URL, this.mOAuthLoginData.getCallbackUrl());
            intent.putExtra("state", this.mOAuthLoginData.getInitState());
            intent.putExtra("app_name", this.mClientName);
            intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_OAUTH_SDK_VERSION, OAuthLoginDefine.VERSION);
            if (DeviceAppInfo.isIntentFilterExist(this.mContext, OAuthLoginDefine.ACTION_OAUTH_LOGIN)) {
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.d(TAG, "startLoginActivity() with naapp");
                }
                intent.setPackage(OAuthLoginDefine.NAVER_PACKAGE_NAME);
                intent.setAction(OAuthLoginDefine.ACTION_OAUTH_LOGIN);
                startActivityForResult(intent, REQUESTCODE_LOGIN);
                return;
            }
            if (DeviceAppInfo.isIntentFilterExist(this.mContext, OAuthLoginDefine.ACTION_OAUTH_LOGIN_2NDAPP)) {
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.d(TAG, "startLoginActivity() with appstore");
                }
                intent.setAction(OAuthLoginDefine.ACTION_OAUTH_LOGIN_2NDAPP);
                String primaryNaverOAuth2ndAppPackageName = DeviceAppInfo.getPrimaryNaverOAuth2ndAppPackageName(this.mContext);
                if (!TextUtils.isEmpty(primaryNaverOAuth2ndAppPackageName)) {
                    intent.setPackage(primaryNaverOAuth2ndAppPackageName);
                }
                startActivityForResult(intent, REQUESTCODE_LOGIN);
                return;
            }
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "startLoginActivity() with webview");
            }
            startLoginWebviewActivity((Activity) this.mContext, REQUESTCODE_LOGIN, this.mOAuthLoginData.getClientId(), this.mOAuthLoginData.getInitState(), this.mOAuthLoginData.getCallbackUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLoginWebviewActivity(Activity activity, int i, String str, String str2, String str3) {
        Intent intent = new Intent(activity, (Class<?>) OAuthLoginInAppBrowserActivity.class);
        intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CLIENT_ID, str);
        intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CALLBACK_URL, str3);
        intent.putExtra("state", str2);
        intent.putExtra(OAuthLoginInAppBrowserActivity.OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_OAUTH_SDK_VERSION, OAuthLoginDefine.VERSION);
        activity.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            finishWithErrorResult(OAuthErrorCode.CLIENT_USER_CANCEL);
            return;
        }
        String stringExtra = intent.getStringExtra(OAuthIntent.EXTRA_OAUTH_STATE);
        String stringExtra2 = intent.getStringExtra(OAuthIntent.EXTRA_OAUTH_CODE);
        String stringExtra3 = intent.getStringExtra(OAuthIntent.EXTRA_OAUTH_ERROR_CODE);
        String stringExtra4 = intent.getStringExtra(OAuthIntent.EXTRA_OAUTH_ERROR_DESCRIPTION);
        this.mOAuthLoginData.setMiddleResult(stringExtra2, stringExtra, stringExtra3, stringExtra4);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d("GILSUB", "Login onActivityResult()");
        }
        if (!TextUtils.isEmpty(stringExtra2)) {
            new GetAccessTokenTask(this, null).execute(new Void[0]);
            return;
        }
        OAuthLoginPreferenceManager oAuthLoginPreferenceManager = new OAuthLoginPreferenceManager(this.mContext);
        oAuthLoginPreferenceManager.setLastErrorCode(OAuthErrorCode.fromString(stringExtra3));
        oAuthLoginPreferenceManager.setLastErrorDesc(stringExtra4);
        setResult(0, intent);
        finish();
        propagationResult(false);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "onCreate()");
        }
        if (initData(bundle)) {
            if (bundle != null) {
                this.mIsLoginActivityStarted = bundle.getBoolean("IsLoginActivityStarted");
            }
            if (this.mIsLoginActivityStarted) {
                return;
            }
            this.mIsLoginActivityStarted = true;
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "onCreate() first");
            }
            runOnlyOnce();
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "onPause()");
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "onRestoreInstanceState()");
        }
        if (bundle != null) {
            this.mIsLoginActivityStarted = bundle.getBoolean("IsLoginActivityStarted");
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "onResume()");
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "Login onSaveInstanceState()");
        }
        bundle.putBoolean("IsLoginActivityStarted", this.mIsLoginActivityStarted);
        bundle.putString("OAuthLoginData_state", this.mOAuthLoginData.getInitState());
    }
}
