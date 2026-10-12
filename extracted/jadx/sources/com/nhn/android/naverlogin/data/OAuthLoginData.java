package com.nhn.android.naverlogin.data;

import android.text.TextUtils;
import android.util.Log;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginData {
    private static final String TAG = "NaverLoginOAuth|OAuthLoginData";
    private String mInOAuthCallback;
    private String mInOAuthClientId;
    private String mInOAuthClientSecret;
    private String mInOAuthState;
    private String mOAuthCode;
    private OAuthErrorCode mOAuthErrorCode;
    private String mOAuthErrorDesc;
    private String mOAuthState;

    public OAuthLoginData(String str, String str2, String str3) {
        init(str, str2, str3, null);
    }

    public OAuthLoginData(String str, String str2, String str3, String str4) {
        init(str, str2, str3, str4);
    }

    private String generateState() {
        String string = new BigInteger(130, new SecureRandom()).toString(32);
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return string;
        }
    }

    private void init(String str, String str2, String str3, String str4) {
        this.mInOAuthClientId = str;
        this.mInOAuthClientSecret = str2;
        this.mInOAuthCallback = str3;
        if (TextUtils.isEmpty(str4)) {
            this.mInOAuthState = generateState();
        } else {
            this.mInOAuthState = str4;
        }
    }

    private boolean isStateOk() {
        if (this.mInOAuthState.equalsIgnoreCase(this.mOAuthState)) {
            return true;
        }
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "state is not valid. init:" + this.mInOAuthState + ", check:" + this.mOAuthState);
        }
        return false;
    }

    public String getCallbackUrl() {
        return this.mInOAuthCallback;
    }

    public String getClientId() {
        return this.mInOAuthClientId;
    }

    public String getClientSecret() {
        return this.mInOAuthClientSecret;
    }

    public String getCode() {
        if (isStateOk()) {
            return this.mOAuthCode;
        }
        return null;
    }

    public OAuthErrorCode getErrorCode() {
        return this.mOAuthErrorCode;
    }

    public String getErrorDesc() {
        return this.mOAuthErrorDesc;
    }

    public String getInitState() {
        return this.mInOAuthState;
    }

    public String getState() {
        return this.mOAuthState;
    }

    public boolean isSuccess() {
        return TextUtils.isEmpty(this.mOAuthErrorCode.getCode()) && isStateOk() && !TextUtils.isEmpty(this.mOAuthCode);
    }

    public void setMiddleResult(String str, String str2, String str3, String str4) {
        this.mOAuthCode = str;
        this.mOAuthState = str2;
        this.mOAuthErrorCode = OAuthErrorCode.fromString(str3);
        this.mOAuthErrorDesc = str4;
    }
}
